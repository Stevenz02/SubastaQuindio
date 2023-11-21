package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.aplicacion.aplicacion;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IModelFactoryControllerService;
import co.edu.uniquindio.subastaq.subastaq.exception.*;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.*;
import co.edu.uniquindio.subastaq.subastaq.mapping.mappers.SubastaMapper;
import co.edu.uniquindio.subastaq.subastaq.model.*;
import co.edu.uniquindio.subastaq.subastaq.producer.RabbitProducer;
import co.edu.uniquindio.subastaq.subastaq.utils.Persistencia;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static co.edu.uniquindio.subastaq.subastaq.utils.SubastaUtils.inicializarDatos;


public class ModelFactoryController implements IModelFactoryControllerService {
    private ExecutorService executorService = Executors.newFixedThreadPool(6);
    private static UsuarioDto usuarioActual;
    SubastaUniquindio subastaUniquindio;
    RabbitProducer rabbitFactory;
    ConnectionFactory connectionFactory;
    aplicacion Aplicacion;
    SubastaMapper mapper = SubastaMapper.INSTANCE;
    public static final String QUEUE_NUEVA_PUBLICACION = "nueva_publicacion";
    Thread hiloServicioConsumer1;

    //------------------------------  Singleton ------------------------------------------------
    // Clase estatica oculta. Tan solo se instanciara el singleton una vez
    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    private ModelFactoryController() {
        System.out.println("Invocación clase singleton");

        // Cargar los datos de forma asíncrona
        cargarDatosAsync();

        // Metodo para crear la conexion con rabbit
        //initRabbitConnection();

        // Registrar acciones del sistema
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");
    }
    /**
     * Crea la conexión con RabbitMQ
     */
    private void initRabbitConnection() {
        rabbitFactory = new RabbitProducer();
        connectionFactory = rabbitFactory.getConnectionFactory();
        System.out.println("conexion establecidad");
    }
    /**
     * Método para consumir mensajes de RabbitMQ
     */
    private void consumirMensajes() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NUEVA_PUBLICACION, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Mensaje recibido: " + message);
                //actualizarEstado(message);
            };
            while (true) {
                channel.basicConsume(QUEUE_NUEVA_PUBLICACION, true, deliverCallback, consumerTag -> { });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicia el proceso de consumir mensajes de RabbitMQ
     */
    public void consumirMensajesServicio1(){
        hiloServicioConsumer1 = new Thread(this::run);
        hiloServicioConsumer1.start();
    }

    /**
     * Método ejecutado por el hilo de consumir mensajes
     */
    public void run() {
        Thread currentThread = Thread.currentThread();
        if(currentThread == hiloServicioConsumer1){
            consumirMensajes();
        }
    }
    /**
     * Método para cargar datos de manera asíncrona
     */
    private void cargarDatosAsync() {
        // Cargar datos de archivos
        executorService.submit(this::cargarDatosDesdeArchivos);

        // Cargar datos desde el recurso serializable binario
        executorService.submit(this::cargarResourceBinario);

        // Cargar datos desde el recurso serializable XML
        executorService.submit(this::cargarResourceXML);

        // Esperar a que todos los hilos de carga terminen antes de continuar
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        // Una vez que la carga ha terminado, configurar los hilos para guardar los datos
        ExecutorService savingExecutor = Executors.newFixedThreadPool(3);

        // Hilo para guardar datos después de la carga
        savingExecutor.submit(() -> {
            if (subastaUniquindio == null) {
                cargarDatosBase();
            }
            guardarResourceBinario();
            guardarResourceXML();
        });

        // Esperar a que todos los hilos de guardado terminen antes de continuar
        savingExecutor.shutdown();
        try {
            if (!savingExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                savingExecutor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            savingExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Carga datos desde archivos
     */
    private void cargarDatosDesdeArchivos() {
        subastaUniquindio = new SubastaUniquindio();
        try {
            Persistencia.cargarDatosArchivos(subastaUniquindio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Guarda datos de prueba
     */
    private void salvarDatosPrueba() {
        Persistencia.guardarSubastaUniquindio(getSubastaUniquindio());
        Persistencia.guardarAnunciantes(getSubastaUniquindio().getListaAnunciantes());
        Persistencia.guardarCompradores(getSubastaUniquindio().getListaCompradores());
        Persistencia.guardarUsuarios(getSubastaUniquindio().getListaUsuarios());
    }

    /**
     * Carga los datos base
     */
    private void cargarDatosBase() {
        subastaUniquindio = inicializarDatos();
    }
    /**
     * Obtiene la instancia de SubastaUniquindio
     */
    public SubastaUniquindio getSubastaUniquindio() {
        return subastaUniquindio;
    }

    public void setSubastaUniquindio(SubastaUniquindio subastaUniquindio) {
        this.subastaUniquindio = subastaUniquindio;
    }
    public void setAplicacion(aplicacion Aplicacion){
        this.Aplicacion = Aplicacion;
    }

    private void cargarResourceXML() {
        subastaUniquindio = Persistencia.cargarRecursoSubastaXML();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoSubastaXML(subastaUniquindio);
    }

    private void cargarResourceBinario() {
        subastaUniquindio = Persistencia.cargarSubasta();
    }

    private void guardarResourceBinario() {
        Persistencia.guardarSubasta(subastaUniquindio);
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return mapper.getUsuariosDto(subastaUniquindio.getListaUsuarios());
    }

    @Override
    public List<CompradorDto> obtenerCompradores() {
        return mapper.getCompradoresDto(subastaUniquindio.getListaCompradores());
    }

    @Override
    public List<AnuncianteDto> obtenerAnunciantes() {
        return mapper.getAnunciantesDto(subastaUniquindio.getListaAnunciantes());
    }

    @Override
    public List<AnuncioDto> obtenerAnuncios() {
        List<Anunciante> listaAnunciantes = subastaUniquindio.getListaAnunciantes();
        // Verifica si la lista de anunciantes tiene elementos.
        if (listaAnunciantes != null && !listaAnunciantes.isEmpty()) {
            // Obtén el primer anunciante y su lista de anuncios.
            Anunciante primerAnunciante = listaAnunciantes.get(0);
            if (primerAnunciante != null && primerAnunciante.getListaAnuncios() != null) {
                return mapper.getAnunciosDto(primerAnunciante.getListaAnuncios());
            }
        }
        // Si no hay anunciantes o el primer anunciante no tiene anuncios, retorna una lista vacía.
        return new ArrayList<>();
    }


    @Override
    public boolean agregarAnuncio(AnuncioDto anuncioDto, UsuarioDto usuarioDto) {
        Anuncio anuncio = mapper.anuncioDtoToAnuncio(anuncioDto);
        Anunciante anunciante = subastaUniquindio.obtenerAnunciante(usuarioDto.cedula());
        getSubastaUniquindio().crearAnuncio(anunciante, anuncio);
        guardarResourceBinario();
        guardarResourceXML();
        return true;
    }
    /**
     * Obtiene el usuario actual autenticado en la aplicación
     * @return El objeto UsuarioDto del usuario actual
     */
    public static UsuarioDto getUsuarioActual() {
        return usuarioActual;
    }
    /**
     * Establece el usuario actual autenticado en la aplicación
     * @param usuario El objeto UsuarioDto a establecer como usuario actual
     */
    public static void setUsuarioActual(UsuarioDto usuario) {
        usuarioActual = usuario;
    }
    /**
     * Convierte un objeto Usuario a su representación DTO (Data Transfer Object)
     * @param usuario El objeto Usuario a convertir
     * @return El objeto UsuarioDto correspondiente
     */
    @Override
    public UsuarioDto userToDto(Usuario usuario) {
        return mapper.usuarioToUsuarioDto(usuario);
    }
    /**
     * Convierte un objeto ProductoDto a su representación de Producto
     * @param productoDto El objeto ProductoDto a convertir
     * @return El objeto Producto correspondiente
     */
    @Override
    public Producto productoDtoToProducto(ProductoDto productoDto) {
        return mapper.productoDtoToProducto(productoDto);
    }
    /**
     * Produce un mensaje y lo envía a una cola específica en RabbitMQ
     * @param queue   La cola de destino del mensaje
     * @param message El mensaje a enviar
     */
    @Override
    public void producirMensaje(String queue, String message) {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queue, false, false, false, null);
            channel.basicPublish("", queue, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Lee la lista de pujas asociadas a un anuncio específico
     * @param anuncioDto El objeto AnuncioDto del anuncio
     * @return Lista de objetos PujaDto asociados al anuncio
     */
    @Override
    public List<PujaDto> leerListaPujasAnuncio(AnuncioDto anuncioDto) {
        Anuncio anuncio = mapper.anuncioDtoToAnuncio(anuncioDto);
        List<Puja> listaPujas = getSubastaUniquindio().obtenerListaPujas(anuncio);
        List<PujaDto> listaPujasDto = pujaToPujaDto(listaPujas);
        guardarResourceBinario();
        guardarResourceXML();
        return listaPujasDto;
    }
    /**
     * Convierte una lista de objetos Puja a su representación DTO
     * @param listaPujas Lista de objetos Puja a convertir
     * @return Lista de objetos PujaDto correspondientes
     */
    private List<PujaDto> pujaToPujaDto(List<Puja> listaPujas){
        return mapper.getPujasDto(listaPujas);
    }

    /**
     * Agrega un nuevo usuario a la aplicación
     * @param usuarioDto El objeto UsuarioDto del usuario a agregar
     * @return true si se agregó exitosamente, false si el usuario ya existe o no cumple con los requisitos
     */
    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try{
            if (!subastaUniquindio.usuarioExiste(usuarioDto.cedula()) && subastaUniquindio.isMayor(usuarioDto.edad())){
                Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
                getSubastaUniquindio().agregarUsuario(usuario);
                if (usuario.getTipo().equalsIgnoreCase("Comprador")){
                    Comprador comprador = getSubastaUniquindio().crearCompradorRetorna(usuario);
                    getSubastaUniquindio().agregarComprador(comprador);
                }
                if (usuario.getTipo().equalsIgnoreCase("Anunciante")){
                    Anunciante anunciante = getSubastaUniquindio().crearAnuncianteRetorna(usuario);
                    getSubastaUniquindio().agregarAnunciante(anunciante);
                }
                guardarResourceBinario();
                guardarResourceXML();
                return true;
            }
            return false;
        }catch (UsuarioExepcion e){
            e.getMessage();
            return false;
        } catch (CompradorExepcion e) {
            throw new RuntimeException(e);
        } catch (AnuncianteExepcion e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Agrega una puja a un anuncio específico por parte de un comprador
     * @param anuncioDto El objeto AnuncioDto del anuncio
     * @param pujaDto    El objeto PujaDto de la puja
     * @param usuarioDto El objeto UsuarioDto del comprador que realiza la puja
     * @return true si se agregó exitosamente, false si hay un error
     * @throws CompradorExepcion Si hay un error relacionado con el comprador
     */
    @Override
    public boolean agregarPuja(AnuncioDto anuncioDto, PujaDto pujaDto, UsuarioDto usuarioDto) throws CompradorExepcion {
        Puja puja = mapper.pujaDtoToPuja(pujaDto);
        Anuncio anuncio = mapper.anuncioDtoToAnuncio(anuncioDto);
        Comprador comprador = subastaUniquindio.obtenerComprador(usuarioDto.cedula());
        getSubastaUniquindio().crearPuja(anuncio,comprador, puja);
        guardarResourceBinario();
        guardarResourceXML();
        return true;
    }

    /**
     * Genera un número aleatorio para propósitos específicos en la aplicación
     * @return Cadena que representa el número aleatorio generado
     */
    public String crearAleatorio(){
        return SubastaUniquindio.generarNumeroAleatorio();
    }
    /**
     * Actualiza la información de un usuario existente en la aplicación
     * @param cedula      La cédula del usuario a actualizar
     * @param usuarioDto  El objeto UsuarioDto con la nueva información
     * @return true si la actualización fue exitosa, false si hubo un error
     */
    @Override
    public boolean actualizarUsuario(String cedula, UsuarioDto usuarioDto) {
        try{
            Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
            getSubastaUniquindio().actualizarUsuario(cedula,usuario);
            guardarResourceBinario();
            guardarResourceXML();
            return true;
        } catch (ActualizarUsuarioExepcion e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Elimina un usuario de la aplicación
     * @param cedula La cédula del usuario a eliminar
     * @return true si se eliminó exitosamente, false si hubo un error
     */
    @Override
    public boolean eliminarUsuario(String cedula) {
        boolean flag = false;
        try{
            flag = getSubastaUniquindio().eliminarUsuario(cedula);
            guardarResourceBinario();
            guardarResourceXML();
        }catch (UsuarioExepcion e){
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * Inicia sesión de un usuario en la aplicación
     * @param nombreUsuario Nombre de usuario del usuario
     * @param contrasenia   Contraseña del usuario
     * @param eventoMouse    Evento del mouse asociado a la acción
     * @throws BuscarUsuarioExepcion Si hay un error al buscar el usuario
     */
    @Override
    public void iniciarSesion(String nombreUsuario, String contrasenia, ActionEvent eventoMouse) throws BuscarUsuarioExepcion {
        Usuario usuario = null;
        if(subastaUniquindio.verificarCredenciales(nombreUsuario, contrasenia)){
            usuario = subastaUniquindio.buscarUsuario(nombreUsuario, contrasenia);
            UsuarioDto usuarioAutenticado = userToDto(usuario);
            ModelFactoryController.setUsuarioActual(usuarioAutenticado);
            System.out.println(("El usuario es: " + usuario));
            if(usuario.getTipo().equalsIgnoreCase("Anunciante")){
                cargarVistaAnunciante(eventoMouse);
            }
            else if(usuario.getTipo().equalsIgnoreCase("Comprador")){
                cargarVistaComprador(eventoMouse);
            }
        }
    }
    /**
     * Cierra la sesión actual del usuario en la aplicación
     * @param actionEvent Evento asociado al cierre de sesión
     */
    public void cerrarSesion(ActionEvent actionEvent) {
        Aplicacion.volverALogin();
    }
    /**
     * Carga la vista del panel del comprador en la aplicación
     * @param actionEvent Evento asociado a la carga del panel del comprador
     */
    @Override
    public void cargarVistaComprador(ActionEvent actionEvent) {
        Aplicacion.cambiarPanelComprador();
    }
    /**
     * Carga la vista del panel del anunciante en la aplicación
     * @param actionEvent Evento asociado a la carga del panel del anunciante
     */
    @Override
    public void cargarVistaAnunciante(ActionEvent actionEvent) {
        Aplicacion.cambiarPanelAnunciante();
    }
    /**
     * Busca un anunciante por su cédula
     * @param cedula La cédula del anunciante a buscar
     * @return El objeto AnuncianteDto correspondiente a la búsqueda
     */
    @Override
    public AnuncianteDto buscarAnuncianteCedula(String cedula) {
        Anunciante anunciante = getSubastaUniquindio().obtenerAnunciante(cedula);
        return crearAnuncianteDto(anunciante);
    }
    /**
     * Busca un comprador por su cédula
     * @param cedula La cédula del comprador a buscar
     * @return El objeto CompradorDto correspondiente a la búsqueda
     */
    @Override
    public CompradorDto buscarCompradorCedula(String cedula) {
        Comprador comprador = getSubastaUniquindio().obtenerComprador(cedula);
        return crearCompradorDto(comprador);
    }
    /**
     * Crea un AnuncianteDto a partir de un Anunciante
     */
    @Override
    public AnuncianteDto crearAnuncianteDto(Anunciante anunciante) {
        return mapper.anuncianteToAnuncianteDto(anunciante);
    }
    /**
     * Crea un CompradorDto a partir de un Comprador
     */
    @Override
    public CompradorDto crearCompradorDto(Comprador comprador) {
        return mapper.compradorToCompradorDto(comprador);
    }

}
