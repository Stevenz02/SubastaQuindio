package co.edu.uniquindio.subastaq.subastaq.model;

import co.edu.uniquindio.subastaq.subastaq.model.service.ICompradorService;

import java.io.Serializable;
import java.util.Objects;

public class Comprador extends Usuario implements Serializable, ICompradorService {
    private Integer limitePujas;
    private Double cantidadPujada;
    private Puja pujaActual;

    public Comprador() {
    }

    public Comprador(Integer limitePujas, Double cantidadPujada, Puja pujaActual) {
        this.limitePujas = limitePujas;
        this.cantidadPujada = cantidadPujada;
        this.pujaActual = pujaActual;
    }

    public Integer getLimitePujas() {
        return limitePujas;
    }

    public void setLimitePujas(Integer limitePujas) {
        this.limitePujas = limitePujas;
    }

    public Double getCantidadPujada() {
        return cantidadPujada;
    }

    public void setCantidadPujada(Double cantidadPujada) {
        this.cantidadPujada = cantidadPujada;
    }

    public Puja getPujaActual() {
        return pujaActual;
    }

    public void setPujaActual(Puja pujaActual) {
        this.pujaActual = pujaActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comprador comprador)) return false;
        return Objects.equals(limitePujas, comprador.limitePujas) && Objects.equals(cantidadPujada, comprador.cantidadPujada) && Objects.equals(pujaActual, comprador.pujaActual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limitePujas, cantidadPujada, pujaActual);
    }

    @Override
    public String toString() {
        return "Comprador{" +
                "limitePujas=" + limitePujas +
                ", cantidadPujada=" + cantidadPujada +
                ", pujaActual=" + pujaActual +
                '}';
    }

}
