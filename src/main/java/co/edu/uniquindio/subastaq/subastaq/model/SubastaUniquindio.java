package co.edu.uniquindio.subastaq.subastaq.model;


import co.edu.uniquindio.subastaq.subastaq.exception.AnuncianteExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.CompradorExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.UsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.model.service.ISubastaService;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SubastaUniquindio implements ISubastaService, Serializable {
    private List<Anunciante> listaAnunciantes;
    private List<Usuario> listaUsuarios;
    private List<Comprador> listaCompradores;

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

    public void crearAnunciante(Usuario usuario) throws AnuncianteExepcion {
        Anunciante anunciante = new Anunciante();
        anunciante.setNombre(usuario.getNombre());
        anunciante.setApellido(usuario.getApellido());
        anunciante.setCedula(usuario.getCedula());
        anunciante.setNombreUsuario(usuario.getNombreUsuario());
        anunciante.setContrasenia(usuario.getContrasenia());
        getListaAnunciantes().add(anunciante);
    }


    public void crearComprador(Usuario usuario) throws CompradorExepcion {
        Comprador comprador = new Comprador();
        comprador.setNombre(usuario.getNombre());
        comprador.setApellido(usuario.getApellido());
        comprador.setCedula(usuario.getCedula());
        comprador.setNombreUsuario(usuario.getNombreUsuario());
        comprador.setContrasenia(usuario.getContrasenia());
        getListaCompradores().add(comprador);
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
            usuario = new Usuario(NombreUsuario,contrasenia);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCedula(cedula);
            usuario.setEdad(edad);
            getListaUsuarios().add(usuario);
            crearComprador(usuario);
        }
        else if(tipo.equalsIgnoreCase("Anunciante")){
            usuario = new Usuario(NombreUsuario,contrasenia);
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
    public boolean actualizarUsuario(String cedulaActual, Usuario usuario) throws UsuarioExepcion {
        Usuario usuarioActual = obtenerUsuario(cedulaActual);
        if (usuarioActual == null){
            throw new UsuarioExepcion("El usuario no existe");
        }
        else {
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
    public boolean verificarUsuarioExistente(String cedula) throws UsuarioExepcion {
        if (usuarioExiste(cedula)){
            throw new UsuarioExepcion("El usuario con cedula: "+ cedula+" ya existe");
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

    public void agregarUsuario(Usuario nuevoUsuario) throws UsuarioExepcion {
        getListaUsuarios().add(nuevoUsuario);
    }
}
