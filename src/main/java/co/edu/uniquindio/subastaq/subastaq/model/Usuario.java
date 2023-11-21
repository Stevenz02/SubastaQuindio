package co.edu.uniquindio.subastaq.subastaq.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
/**
 * Clase que representa a un usuario en el sistema de subastas.
 * Extiende la clase abstracta Persona e implementa la interfaz Serializable.
 */
public class Usuario extends Persona implements Serializable{
    private String nombreUsuario;
    private String contrasenia;
    private String tipo;
    /**
     * Constructor por defecto de la clase Usuario.
     * Crea una instancia de Usuario con valores predeterminados.
     */
    public Usuario() {
    }
    /**
     * Constructor de la clase Usuario que permite inicializar sus atributos.
     *
     * @param nombreUsuario El nombre de usuario del usuario.
     * @param contrasenia   La contraseña del usuario.
     * @param tipo          El tipo de usuario (Comprador, Anunciante, etc.).
     */
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
