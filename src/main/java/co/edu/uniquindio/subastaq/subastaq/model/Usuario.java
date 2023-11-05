package co.edu.uniquindio.subastaq.subastaq.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Usuario implements Serializable{
    private String nombreUsuario;
    private String contrasenia;
    private Integer edad;
    private List<Anunciante> listaAnunciantes;
    private List<Comprador> listaComprador;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String contrasenia, Integer edad, List<Anunciante> listaAnunciantes, List<Comprador> listaComprador) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.edad = edad;
        this.listaAnunciantes = listaAnunciantes;
        this.listaComprador = listaComprador;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public List<Anunciante> getListaAnunciantes() {
        return listaAnunciantes;
    }

    public void setListaAnunciantes(List<Anunciante> listaAnunciantes) {
        this.listaAnunciantes = listaAnunciantes;
    }

    public List<Comprador> getListaComprador() {
        return listaComprador;
    }

    public void setListaComprador(List<Comprador> listaComprador) {
        this.listaComprador = listaComprador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(nombreUsuario, usuario.nombreUsuario) && Objects.equals(contrasenia, usuario.contrasenia) && Objects.equals(edad, usuario.edad) && Objects.equals(listaAnunciantes, usuario.listaAnunciantes) && Objects.equals(listaComprador, usuario.listaComprador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario, contrasenia, edad, listaAnunciantes, listaComprador);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", edad=" + edad +
                ", listaAnunciantes=" + listaAnunciantes +
                ", listaComprador=" + listaComprador +
                '}';
    }
}
