package co.edu.uniquindio.subastaq.subastaq.model;

import java.io.Serializable;
import java.util.Objects;

public class Producto implements Serializable {
    private String nombreProducto;
    private TipoProducto tipoProducto;
    private String descripcion;
    private String rutaImagen;

    public Producto() {
    }

    public Producto(String nombreProducto, TipoProducto tipoProducto, String descripcion, String rutaImagen) {
        this.nombreProducto = nombreProducto;
        this.tipoProducto = tipoProducto;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(nombreProducto, producto.nombreProducto) && Objects.equals(tipoProducto, producto.tipoProducto) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(rutaImagen, producto.rutaImagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreProducto, tipoProducto, descripcion, rutaImagen);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombreProducto='" + nombreProducto + '\'' +
                ", tipoProducto=" + tipoProducto +
                ", descripcion='" + descripcion + '\'' +
                ", rutaImagen='" + rutaImagen + '\'' +
                '}';
    }
}
