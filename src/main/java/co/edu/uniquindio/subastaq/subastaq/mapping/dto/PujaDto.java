package co.edu.uniquindio.subastaq.subastaq.mapping.dto;

import java.util.Date;

public record PujaDto(
        String codigo,
        Date fecha,
        Double ofertaActual
) {
}
