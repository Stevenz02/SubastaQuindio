package co.edu.uniquindio.subastaq.subastaq.mapping.dto;

import java.util.Date;
import java.util.List;

public record AnuncianteDto(
        String tipo,
        String nombreUsuario,
        String contrasenia,
        String nombre,
        String apellido,
        String cedula,
        Integer edad,
        Integer cantidadAnuncios,
        Date fechaLimite,
        List<AnuncioDto> listaAnunciosDto

) {
}
