package co.edu.uniquindio.subastaq.subastaq.model;

import co.edu.uniquindio.subastaq.subastaq.model.service.ISubastaService;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SubastaUniquindio implements Serializable, ISubastaService {
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
}
