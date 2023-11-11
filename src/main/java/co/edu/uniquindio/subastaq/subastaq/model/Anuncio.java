package co.edu.uniquindio.subastaq.subastaq.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Anuncio implements Serializable {
    private Anunciante anunciante;
    private Producto producto;
    private Date fechaPublicacion;
    private Date FechaLimite;
    private Double valorInicial;
    private List<Puja> listaPujas = new ArrayList<>();

    public Anuncio() {
    }

    public Anuncio(Anunciante anunciante, Producto producto, Date fechaPublicacion, Date fechaLimite, Double valorInicial, List<Puja> listaPujas) {
        this.anunciante = anunciante;
        this.producto = producto;
        this.fechaPublicacion = fechaPublicacion;
        FechaLimite = fechaLimite;
        this.valorInicial = valorInicial;
        this.listaPujas = listaPujas;
    }

    public Anunciante getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaLimite() {
        return FechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        FechaLimite = fechaLimite;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public List<Puja> getListaPujas() {
        return listaPujas;
    }

    public void setListaPujas(List<Puja> listaPujas) {
        this.listaPujas = listaPujas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anuncio anuncio)) return false;
        return Objects.equals(anunciante, anuncio.anunciante) && Objects.equals(producto, anuncio.producto) && Objects.equals(fechaPublicacion, anuncio.fechaPublicacion) && Objects.equals(FechaLimite, anuncio.FechaLimite) && Objects.equals(valorInicial, anuncio.valorInicial) && Objects.equals(listaPujas, anuncio.listaPujas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anunciante, producto, fechaPublicacion, FechaLimite, valorInicial, listaPujas);
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "anunciante=" + anunciante +
                ", producto=" + producto +
                ", fechaPublicacion=" + fechaPublicacion +
                ", FechaLimite=" + FechaLimite +
                ", valorInicial=" + valorInicial +
                ", listaPujas=" + listaPujas +
                '}';
    }
}
