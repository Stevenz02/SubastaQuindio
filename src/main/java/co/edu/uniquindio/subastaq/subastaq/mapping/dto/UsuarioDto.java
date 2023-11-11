package co.edu.uniquindio.subastaq.subastaq.mapping.dto;

public record UsuarioDto(
        String nombre,
        String apellido,
        String cedula,
        Integer edad,
        String nombreUsuario,
        String contrasenia,
        String tipo
) {
}
