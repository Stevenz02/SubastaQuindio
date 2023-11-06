package co.edu.uniquindio.subastaq.subastaq.mapping.dto;

import co.edu.uniquindio.subastaq.subastaq.model.Puja;

public record CompradorDto(
        String nombre,
        String apellido,
        String cedula,
        Integer edad,
        Integer limitePujas,
        Double cantidadPujada,
        PujaDto pujaDtoActual
) {
}
