package co.edu.uniquindio.subastaq.subastaq.model;


import co.edu.uniquindio.subastaq.subastaq.exception.AnuncianteExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.CompradorExepcion;
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
    public Usuario crearUsuario(String nombre, String apellido, Integer edad, String cedula, String NombreUsuario, String contrasenia, String tipo) throws CompradorExepcion, AnuncianteExepcion {
        Usuario usuario = null;
        if(usuarioExiste(cedula) && isMayor(edad)){
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
                System.out.println("Ingrese correctamente el tipo de usuario");
            }
        }
        return usuario;
    }

    @Override
    public boolean isMayor(Integer edad) {
        return edad >= 18;
    }

    @Override
    public boolean usuarioExiste(String cedula) {
        boolean flag = true;
        for (Usuario usuario : listaUsuarios){
            if (usuario.getCedula().equalsIgnoreCase(cedula)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
