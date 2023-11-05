package co.edu.uniquindio.subastaq.subastaq.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Puja implements Serializable {
    private String codigo;
    private Date fecha;
    private Double ofertaActual;

    public Puja() {
    }

    public Puja(String codigo, Date fecha, Double ofertaActual) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.ofertaActual = ofertaActual;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getOfertaActual() {
        return ofertaActual;
    }

    public void setOfertaActual(Double ofertaActual) {
        this.ofertaActual = ofertaActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Puja puja)) return false;
        return Objects.equals(codigo, puja.codigo) && Objects.equals(fecha, puja.fecha) && Objects.equals(ofertaActual, puja.ofertaActual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, fecha, ofertaActual);
    }

    @Override
    public String toString() {
        return "Puja{" +
                "codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", ofertaActual=" + ofertaActual +
                '}';
    }
}
