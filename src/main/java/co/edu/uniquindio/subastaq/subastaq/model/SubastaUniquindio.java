package co.edu.uniquindio.subastaq.subastaq.model;


import co.edu.uniquindio.subastaq.subastaq.exception.*;
import co.edu.uniquindio.subastaq.subastaq.model.service.ISubastaService;
import javafx.scene.control.Alert;

import java.io.Serializable;
import java.util.*;

public class SubastaUniquindio implements ISubastaService, Serializable {
    private List<Anunciante> listaAnunciantes = new ArrayList<>();
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private List<Comprador> listaCompradores = new ArrayList<>();

    public SubastaUniquindio() {
    }

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

    public static String generarNumeroAleatorio() {
        Random random = new Random();
        return String.valueOf(random.nextInt(500) + 1);  // Genera un número entre 1 y 500 (ambos inclusive)
    }
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

    @Override
    public boolean isMayor(Integer edad) {
        return edad >= 18;
    }

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

    @Override
    public boolean verificarUsuarioExistente(String cedula) throws VerificarUsuarioExepcion {
        if (usuarioExiste(cedula)){
            throw new VerificarUsuarioExepcion("El usuario con cedula: "+ cedula+" ya existe");
        }
        else {
            return true;
        }
    }

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

    @Override
    public List<Usuario> obtenerUsuarios() {
        return getListaUsuarios();
    }

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
    public void agregarAnunciante(Anunciante nuevoAnunciante) throws AnuncianteExepcion {
        getListaAnunciantes().add(nuevoAnunciante);
    }
    public void agregarComprador(Comprador nuevoComprador) throws CompradorExepcion {
        getListaCompradores().add(nuevoComprador);
    }
    public void agregarUsuario(Usuario nuevoUsuario) throws UsuarioExepcion {
        getListaUsuarios().add(nuevoUsuario);
    }
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

    @Override
    public Boolean eliminarAnunciante(String cedula) throws EliminarAnuncianteExepcion {
        return null;
    }

    @Override
    public boolean actualizarAnunciante(String cedulaActual, Anunciante anunciante) throws ActualizarAnuncianteExepcion {
        return false;
    }

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

    @Override
    public void crearAnuncio(Anunciante anunciante, Anuncio anuncio) {
        anunciante.getListaAnuncios().add(anuncio);
    }

    @Override
    public Anunciante buscarAnuncianteCedula(String cedula) {
        for (Anunciante anunciante : getListaAnunciantes()) {
            if (anunciante.getCedula().equals(cedula)) {
                return anunciante;
            }
        }
        return null;
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

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

    public List<Puja> obtenerListaPujas(Anuncio anuncio) {
        return anuncio.getListaPujas();
    }
}
