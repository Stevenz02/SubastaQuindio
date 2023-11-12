package co.edu.uniquindio.subastaq.subastaq.model;

import co.edu.uniquindio.subastaq.subastaq.model.service.IAnuncianteService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Anunciante extends Usuario implements Serializable, IAnuncianteService {
    private Integer cantidadAnuncios;
    private Date fechaLimite;
    private List<Anuncio> listaAnuncios = new ArrayList<>();

    public Anunciante() {
    }

    public Anunciante(Integer cantidadAnuncios, Date fechaLimite, List<Anuncio> listaAnuncios) {
        this.cantidadAnuncios = cantidadAnuncios;
        this.fechaLimite = fechaLimite;
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
        if (!super.equals(o)) return false;
        return Objects.equals(cantidadAnuncios, that.cantidadAnuncios) && Objects.equals(fechaLimite, that.fechaLimite) && Objects.equals(listaAnuncios, that.listaAnuncios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cantidadAnuncios, fechaLimite, listaAnuncios);
    }

    @Override
    public String toString() {
        return "Anunciante{" +
                "cantidadAnuncios=" + cantidadAnuncios +
                ", fechaLimite=" + fechaLimite +
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
