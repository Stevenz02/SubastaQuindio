package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.aplicacion.aplicacion;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IModelFactoryControllerService;
import co.edu.uniquindio.subastaq.subastaq.exception.ActualizarUsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.BuscarUsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.UsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncianteDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.CompradorDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.mappers.SubastaMapper;
import co.edu.uniquindio.subastaq.subastaq.model.Anunciante;
import co.edu.uniquindio.subastaq.subastaq.model.Anuncio;
import co.edu.uniquindio.subastaq.subastaq.model.SubastaUniquindio;
import co.edu.uniquindio.subastaq.subastaq.model.Usuario;
import co.edu.uniquindio.subastaq.subastaq.utils.Persistencia;
import javafx.event.ActionEvent;

import java.io.IOException;
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
    aplicacion Aplicacion;
    SubastaMapper mapper = SubastaMapper.INSTANCE;

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

        // Registrar acciones del sistema
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");
    }

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
    private void cargarDatosDesdeArchivos() {
        subastaUniquindio = new SubastaUniquindio();
        try {
            Persistencia.cargarDatosArchivos(subastaUniquindio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void salvarDatosPrueba() {
        Persistencia.guardarSubastaUniquindio(getSubastaUniquindio());
        Persistencia.guardarAnunciantes(getSubastaUniquindio().getListaAnunciantes());
        Persistencia.guardarCompradores(getSubastaUniquindio().getListaCompradores());
        Persistencia.guardarUsuarios(getSubastaUniquindio().getListaUsuarios());
    }

    private void cargarDatosBase() {
        subastaUniquindio = inicializarDatos();
    }

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
        return true;
    }
    public static UsuarioDto getUsuarioActual() {
        return usuarioActual;
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try{
            if (!subastaUniquindio.usuarioExiste(usuarioDto.cedula()) && subastaUniquindio.isMayor(usuarioDto.edad())){
                Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
                getSubastaUniquindio().agregarUsuario(usuario);
                guardarResourceXML();
                return true;
            }
            return false;
        }catch (UsuarioExepcion e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean actualizarUsuario(String cedula, UsuarioDto usuarioDto) {
        try{
            Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
            getSubastaUniquindio().actualizarUsuario(cedula,usuario);
            return true;
        } catch (ActualizarUsuarioExepcion e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(String cedula) {
        boolean flag = false;
        try{
            flag = getSubastaUniquindio().eliminarUsuario(cedula);
        }catch (UsuarioExepcion e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void iniciarSesion(String nombreUsuario, String contrasenia, ActionEvent eventoMouse) throws BuscarUsuarioExepcion {
        Usuario usuario = null;
        if(subastaUniquindio.verificarCredenciales(nombreUsuario, contrasenia)){
            usuario = subastaUniquindio.buscarUsuario(nombreUsuario, contrasenia);
            System.out.println(("El usuario es: " + usuario));
            if(usuario.getTipo().equalsIgnoreCase("Anunciante")){
                cargarVistaAnunciante(eventoMouse);
            }
            else if(usuario.getTipo().equalsIgnoreCase("Comprador")){
                cargarVistaComprador(eventoMouse);
            }
        }
    }
    public void cerrarSesion(ActionEvent actionEvent) {
        Aplicacion.volverALogin();
    }
    @Override
    public void cargarVistaComprador(ActionEvent actionEvent) {
        Aplicacion.cambiarPanelComprador();
    }
    @Override
    public void cargarVistaAnunciante(ActionEvent actionEvent) {
        Aplicacion.cambiarPanelAnunciante();
    }

    @Override
    public AnuncianteDto buscarAnuncianteNombre(String nombre) {
        Anunciante anunciante = getSubastaUniquindio().buscarAnuncianteNombre(nombre);
        return crearAnuncianteDto(anunciante);
    }

    @Override
    public AnuncianteDto crearAnuncianteDto(Anunciante anunciante) {
        return mapper.anuncianteToAnuncianteDto(anunciante);
    }

    @Override
    public UsuarioDto userToDto(Usuario usuario) {
        return mapper.usuarioToUsuarioDto(usuario);
    }

}
