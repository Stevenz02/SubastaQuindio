package co.edu.uniquindio.subastaq.subastaq.mapping.dto;

import co.edu.uniquindio.subastaq.subastaq.model.TipoProducto;

public record ProductoDto(
        String nombreProducto,
        TipoProducto tipoProducto,
        String descripcion,
        String rutaImagen
) {
}
