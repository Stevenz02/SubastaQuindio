package co.edu.uniquindio.subastaq.subastaq.model;


import co.edu.uniquindio.subastaq.subastaq.exception.*;
import co.edu.uniquindio.subastaq.subastaq.model.service.ISubastaService;
import javafx.scene.control.Alert;

import java.io.Serializable;
import java.util.*;
/**
 * Clase que representa el sistema de subastas de la Universidad del Quindío.
 * Implementa la interfaz ISubastaService y Serializable.
 */
public class SubastaUniquindio implements ISubastaService, Serializable {
    private List<Anunciante> listaAnunciantes = new ArrayList<>();
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private List<Comprador> listaCompradores = new ArrayList<>();
    /**
     * Constructor por defecto de la clase SubastaUniquindio.
     * Crea una instancia de SubastaUniquindio con listas vacías.
     */
    public SubastaUniquindio() {
    }
    /**
     * Constructor de la clase SubastaUniquindio que permite inicializar sus listas.
     *
     * @param listaAnunciantes Lista de anunciantes en el sistema.
     * @param listaUsuarios    Lista de usuarios en el sistema.
     * @param listaCompradores Lista de compradores en el sistema.
     */
    public SubastaUniquindio(List<Anunciante> listaAnunciantes, List<Usuario> listaUsuarios, List<Comprador> listaCompradores) {
        this.listaAnunciantes = listaAnunciantes;
        this.listaUsuarios = listaUsuarios;
        this.listaCompradores = listaCompradores;
    }

    public List<Anunciante> getListaAnunciantes() {
        return listaAnunciantes;
    }

    public void setListaAnunciantes(List<Anunciante> listaAnunciantes) {
        this.listaAnunciantes = listaAnunciantes;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Comprador> getListaCompradores() {
        return listaCompradores;
    }

    public void setListaCompradores(List<Comprador> listaCompradores) {
        this.listaCompradores = listaCompradores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubastaUniquindio that)) return false;
        return Objects.equals(listaAnunciantes, that.listaAnunciantes) && Objects.equals(listaUsuarios, that.listaUsuarios) && Objects.equals(listaCompradores, that.listaCompradores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaAnunciantes, listaUsuarios, listaCompradores);
    }

    @Override
    public String toString() {
        return "SubastaUniquindio{" +
                "listaAnunciantes=" + listaAnunciantes +
                ", listaUsuarios=" + listaUsuarios +
                ", listaCompradores=" + listaCompradores +
                '}';
    }
    public Comprador crearCompradorRetorna(Usuario usuario){
        Comprador comprador = new Comprador(3, 0.0, new Puja());
        comprador.setTipo(usuario.getTipo());
        comprador.setNombre(usuario.getNombre());
        comprador.setApellido(usuario.getApellido());
        comprador.setCedula(usuario.getCedula());
        comprador.setNombreUsuario(usuario.getNombreUsuario());
        comprador.setContrasenia(usuario.getContrasenia());
        return comprador;
    }


    @Override
    public void crearComprador(Usuario usuario) throws CompradorExepcion {
        if (getListaCompradores() == null) {
            setListaCompradores(new ArrayList<>());
        }
        Comprador comprador = new Comprador(3, 0.0, new Puja());
        comprador.setTipo(usuario.getTipo());
        comprador.setNombre(usuario.getNombre());
        comprador.setApellido(usuario.getApellido());
        comprador.setCedula(usuario.getCedula());
        comprador.setNombreUsuario(usuario.getNombreUsuario());
        comprador.setContrasenia(usuario.getContrasenia());
        getListaCompradores().add(comprador);
    }

    @Override
    public Boolean eliminarComprador(String cedula) throws EliminarCompradorExepcion {
        Comprador comprador = null;
        boolean flag = false;
        comprador = obtenerComprador(cedula);
        if (comprador == null){
            throw new EliminarCompradorExepcion("El comprador a eliminar no existe");
        }else {
            getListaUsuarios().remove(comprador);
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean actualizarComprador(String cedulaActual, Comprador comprador) throws ActualizarCompradorExepcion {
        Comprador compradorActual = obtenerComprador(cedulaActual);
        if (compradorActual == null){
            throw new ActualizarCompradorExepcion("El comprador no existe");
        }
        else {
            compradorActual.setTipo(comprador.getTipo());
            compradorActual.setNombre(comprador.getNombre());
            compradorActual.setApellido(comprador.getApellido());
            compradorActual.setCedula(comprador.getCedula());
            compradorActual.setEdad(comprador.getEdad());
            compradorActual.setNombreUsuario(comprador.getNombreUsuario());
            compradorActual.setContrasenia(comprador.getContrasenia());
        }
        return true;
    }

    @Override
    public Comprador obtenerComprador(String cedula) {
        for (Comprador comprador: getListaCompradores()){
            if (comprador.getCedula().equalsIgnoreCase(cedula)){
                return comprador;
            }
        }
        return null;
    }

    @Override
    public void crearPuja(Anuncio anuncio,Comprador comprador,Puja puja) {
        comprador.setPujaActual(puja);
        comprador.setLimitePujas(comprador.getLimitePujas()-1);
        anuncio.getListaPujas().add(puja);
    }
    /**
     * Método estático que genera un número aleatorio entre 1 y 500 (inclusive).
     *
     * @return Número aleatorio generado como una cadena de texto.
     */
    public static String generarNumeroAleatorio() {
        Random random = new Random();
        return String.valueOf(random.nextInt(500) + 1);  // Genera un número entre 1 y 500 (ambos inclusive)
    }

    /**
     * Metodo que se usa para crear un usuario
     * @param nombre
     * @param apellido
     * @param edad
     * @param cedula
     * @param NombreUsuario
     * @param contrasenia
     * @param tipo
     * @return
     * @throws CompradorExepcion
     * @throws AnuncianteExepcion
     * @throws UsuarioExepcion
     */
    @Override
    public Usuario crearUsuario(String nombre, String apellido, Integer edad, String cedula, String NombreUsuario, String contrasenia, String tipo) throws CompradorExepcion, AnuncianteExepcion, UsuarioExepcion {
        Usuario usuario = null;
        if(usuarioExiste(cedula)){
            throw new UsuarioExepcion("El usuario con cedula: "+ cedula + "ya existe");
        } else if (!isMayor(edad)) {
            throw new UsuarioExepcion("El usuario es menor de edad, tiene "+ edad + " años");
        }
        if (tipo.equalsIgnoreCase("Comprador")){
            usuario = new Usuario(NombreUsuario,contrasenia, tipo);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCedula(cedula);
            usuario.setEdad(edad);
            getListaUsuarios().add(usuario);
            crearComprador(usuario);
        }
        else if(tipo.equalsIgnoreCase("Anunciante")){
            usuario = new Usuario(NombreUsuario,contrasenia, tipo);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCedula(cedula);
            usuario.setEdad(edad);
            getListaUsuarios().add(usuario);
            crearAnunciante(usuario);
        }
        else {
            throw new UsuarioExepcion("Ingrese correctamente el tipo de usuario");
        }
        return usuario;
    }

    /**
     * metodo para identificar si un usuario es mayor de edad
     * @param edad
     * @return
     */
    @Override
    public boolean isMayor(Integer edad) {
        return edad >= 18;
    }

    /**
     * metodo que se usa para identificar si un usuario existe
     * @param cedula
     * @return retorna true si existe
     */
    @Override
    public boolean usuarioExiste(String cedula) {
        boolean flag = false;
        for (Usuario usuario : getListaUsuarios()){
            if (usuario.getCedula().equalsIgnoreCase(cedula)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * Metodo que se usa para eliminar un usuario
     * @param cedula
     * @return
     * @throws UsuarioExepcion
     */
    @Override
    public Boolean eliminarUsuario(String cedula) throws UsuarioExepcion {
        Usuario usuario = null;
        boolean flag = false;
        usuario = obtenerUsuario(cedula);
        if (usuario == null){
            throw new UsuarioExepcion("El usuario a eliminar no existe");
        }else {
            getListaUsuarios().remove(usuario);
            flag = true;
        }
        return flag;
    }

    /**
     * metodo que se usa para actualizar un usuario
     * @param cedulaActual
     * @param usuario
     * @return
     * @throws ActualizarUsuarioExepcion
     */
    @Override
    public boolean actualizarUsuario(String cedulaActual, Usuario usuario) throws ActualizarUsuarioExepcion {
        Usuario usuarioActual = obtenerUsuario(cedulaActual);
        if (usuarioActual == null){
            throw new ActualizarUsuarioExepcion("El usuario no existe");
        }
        else {
            usuarioActual.setTipo(usuario.getTipo());
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setApellido(usuario.getApellido());
            usuarioActual.setCedula(usuarioActual.getCedula());
            usuarioActual.setEdad(usuario.getEdad());
            usuarioActual.setNombreUsuario(usuario.getNombreUsuario());
            usuarioActual.setContrasenia(usuario.getContrasenia());
        }
        return true;
    }

    /**
     * metodo que se usa para verificar si un usuario existe
     * @param cedula
     * @return
     * @throws VerificarUsuarioExepcion
     */
    @Override
    public boolean verificarUsuarioExistente(String cedula) throws VerificarUsuarioExepcion {
        if (usuarioExiste(cedula)){
            throw new VerificarUsuarioExepcion("El usuario con cedula: "+ cedula+" ya existe");
        }
        else {
            return true;
        }
    }

    /**
     * Metodo que se usa oara obtener un usuario
     * @param cedula
     * @return
     */
    @Override
    public Usuario obtenerUsuario(String cedula) {
        Usuario usuarioEncontrado = null;
        for (Usuario usuario: getListaUsuarios()){
            if (usuario.getCedula().equalsIgnoreCase(cedula)){
                usuarioEncontrado = usuario;
                break;
            }
        }
        return usuarioEncontrado;
    }

    /**
     * Metodo que se usa para obtener una lista de usuarios
     * @return
     */
    @Override
    public List<Usuario> obtenerUsuarios() {
        return getListaUsuarios();
    }

    /**
     * Metodo que se usa para verificar las credenciales del usuario
     * @param nombreUsuario
     * @param contrasenia
     * @return
     */
    @Override
    public boolean verificarCredenciales(String nombreUsuario, String contrasenia) {
        for(Usuario usuario : getListaUsuarios()){
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasenia().equals(contrasenia)){
                mostrarMensaje("Notificación usuario", "Usuario ha iniciado sesión", "Ha iniciado sesión correctamente", Alert.AlertType.CONFIRMATION);
                return true;
            }
        }
        mostrarMensaje("Notificación usuario", "Usuario NO ha iniciado sesión", "Por favor verifique si ha ingresado correctamente las credenciales", Alert.AlertType.WARNING);
        return false;
    }

    /**
     * Metodo que busca un usuario
     * @param nombreUsuario
     * @param contrasenia
     * @return
     * @throws BuscarUsuarioExepcion
     */
    @Override
    public Usuario buscarUsuario(String nombreUsuario, String contrasenia) throws BuscarUsuarioExepcion {
        Usuario usuarioEncontrado = null;
        for(Usuario usuario: getListaUsuarios()){
            if(usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasenia().equals(contrasenia)){
                usuarioEncontrado = usuario;
                return  usuarioEncontrado;
            }
        }
        throw new BuscarUsuarioExepcion("El usuario no existe");
    }

    /**
     * Meotodo que agrega un anunciante
     * @param nuevoAnunciante
     * @throws AnuncianteExepcion
     */
    public void agregarAnunciante(Anunciante nuevoAnunciante) throws AnuncianteExepcion {
        getListaAnunciantes().add(nuevoAnunciante);
    }

    /**
     * metodo que agrega un comprador
     * @param nuevoComprador
     * @throws CompradorExepcion
     */
    public void agregarComprador(Comprador nuevoComprador) throws CompradorExepcion {
        getListaCompradores().add(nuevoComprador);
    }

    /**
     * Metodo que agrega un usuario
     * @param nuevoUsuario
     * @throws UsuarioExepcion
     */
    public void agregarUsuario(Usuario nuevoUsuario) throws UsuarioExepcion {
        getListaUsuarios().add(nuevoUsuario);
    }

    /**
     * metodo que se usa para crear un anunciante
     * @param usuario
     * @throws AnuncianteExepcion
     */
    public void crearAnunciante(Usuario usuario) throws AnuncianteExepcion {
        if (getListaAnunciantes() == null) {
            setListaAnunciantes(new ArrayList<>());
        }
        Anunciante anunciante = new Anunciante(10, new Date(),new ArrayList<>());
        anunciante.setTipo(usuario.getTipo());
        anunciante.setNombre(usuario.getNombre());
        anunciante.setApellido(usuario.getApellido());
        anunciante.setCedula(usuario.getCedula());
        anunciante.setNombreUsuario(usuario.getNombreUsuario());
        anunciante.setContrasenia(usuario.getContrasenia());
        getListaAnunciantes().add(anunciante);
    }

    /**
     * metodo que se usa para eliminar un anunciante
     * @param cedula
     * @return
     * @throws EliminarAnuncianteExepcion
     */
    @Override
    public Boolean eliminarAnunciante(String cedula) throws EliminarAnuncianteExepcion {
        return null;
    }

    /**
     * Metodo que se usa para actualizar un anunciante
     * @param cedulaActual
     * @param anunciante
     * @return
     * @throws ActualizarAnuncianteExepcion
     */
    @Override
    public boolean actualizarAnunciante(String cedulaActual, Anunciante anunciante) throws ActualizarAnuncianteExepcion {
        return false;
    }

    /**
     * Metodo que se usa para obtener un anunciante a traves de la cedula
     * @param cedula
     * @return
     */
    @Override
    public Anunciante obtenerAnunciante(String cedula) {
        for (Anunciante anunciante : getListaAnunciantes()) {
            if (anunciante.getCedula().equalsIgnoreCase(cedula)) {
                return anunciante;
            }
        }
        Anunciante nuevoAnunciante = crearAnunciantePorCedula(cedula);
        getListaAnunciantes().add(nuevoAnunciante);
        return nuevoAnunciante;
    }

    /**
     * Metodo que se usa para crear un anunciante por cedula
     * @param cedula
     * @return
     */
    private Anunciante crearAnunciantePorCedula(String cedula) {
        if (getListaAnunciantes() == null) {
            setListaAnunciantes(new ArrayList<>());
        }
        Anunciante anunciante = new Anunciante(10, new Date(), new ArrayList<>());
        anunciante.setCedula(cedula); // Establecer la cédula proporcionada
        // Aquí puedes establecer otros valores predeterminados o dejarlos en null según tus necesidades
        getListaAnunciantes().add(anunciante);
        return anunciante;
    }

    /**
     * Metodo que se usa para crear un anuncio
     * @param anunciante
     * @param anuncio
     */
    @Override
    public void crearAnuncio(Anunciante anunciante, Anuncio anuncio) {
        anunciante.getListaAnuncios().add(anuncio);
    }

    /**
     * Metodo que se usa para buscar un anunciante por cedula
     * @param cedula
     * @return
     */
    @Override
    public Anunciante buscarAnuncianteCedula(String cedula) {
        for (Anunciante anunciante : getListaAnunciantes()) {
            if (anunciante.getCedula().equals(cedula)) {
                return anunciante;
            }
        }
        return null;
    }

    /**
     * Metodo que se usa para mostrar un mensaje
     * @param titulo
     * @param header
     * @param contenido
     * @param alertType
     */
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    /**
     * Metodo que se usa para crear un anunciante
     * @param usuario
     * @return
     */
    public Anunciante crearAnuncianteRetorna(Usuario usuario) {
        Anunciante anunciante = new Anunciante(10, new Date(),new ArrayList<>());
        anunciante.setTipo(usuario.getTipo());
        anunciante.setNombre(usuario.getNombre());
        anunciante.setApellido(usuario.getApellido());
        anunciante.setCedula(usuario.getCedula());
        anunciante.setNombreUsuario(usuario.getNombreUsuario());
        anunciante.setContrasenia(usuario.getContrasenia());
        return anunciante;
    }

    /**
     * Metodo que se usa para obtener la lista de pujas de un anuncio
     * @param anuncio
     * @return
     */
    public List<Puja> obtenerListaPujas(Anuncio anuncio) {
        return anuncio.getListaPujas();
    }
}
