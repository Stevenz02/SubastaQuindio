package co.edu.uniquindio.subastaq.subastaq.mapping.dto;


import java.util.Date;
import java.util.List;

public record AnuncioDto(
        AnuncianteDto anuncianteDto,
        ProductoDto productoDto,
        Date fechaPublicacion,
        Date FechaLimite,
        Double valorInicial,
        List<PujaDto>listaPujasDto
) {
}
