package co.edu.uniquindio.subastaq.subastaq.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Usuario extends Persona implements Serializable{
    private String nombreUsuario;
    private String contrasenia;
    private String tipo;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String contrasenia, String tipo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(nombreUsuario, usuario.nombreUsuario) && Objects.equals(contrasenia, usuario.contrasenia) && Objects.equals(tipo, usuario.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nombreUsuario, contrasenia, tipo);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
