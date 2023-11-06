package co.edu.uniquindio.subastaq.subastaq.model;

import co.edu.uniquindio.subastaq.subastaq.model.service.IAnuncianteService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Anunciante extends Usuario implements Serializable, IAnuncianteService {
    private Integer cantidadAnuncios;
    private Date fechaLimite;
    private List<Producto> listaProductos;
    private List<Anuncio> listaAnuncios;

    public Anunciante() {
    }

    public Anunciante(Integer cantidadAnuncios, Date fechaLimite, List<Producto> listaProductos, List<Anuncio> listaAnuncios) {
        this.cantidadAnuncios = cantidadAnuncios;
        this.fechaLimite = fechaLimite;
        this.listaProductos = listaProductos;
        this.listaAnuncios = listaAnuncios;
    }

    public Integer getCantidadAnuncios() {
        return cantidadAnuncios;
    }

    public void setCantidadAnuncios(Integer cantidadAnuncios) {
        this.cantidadAnuncios = cantidadAnuncios;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anunciante that)) return false;
        return Objects.equals(cantidadAnuncios, that.cantidadAnuncios) && Objects.equals(fechaLimite, that.fechaLimite) && Objects.equals(listaProductos, that.listaProductos) && Objects.equals(listaAnuncios, that.listaAnuncios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidadAnuncios, fechaLimite, listaProductos, listaAnuncios);
    }

    @Override
    public String toString() {
        return "Anunciante{" +
                "cantidadAnuncios=" + cantidadAnuncios +
                ", fechaLimite=" + fechaLimite +
                ", listaProductos=" + listaProductos +
                ", listaAnuncios=" + listaAnuncios +
                '}';
    }

    @Override
    public Anuncio crearAnuncio() {
        return null;
    }

    @Override
    public boolean eliminarAnuncio() {
        return false;
    }
}
