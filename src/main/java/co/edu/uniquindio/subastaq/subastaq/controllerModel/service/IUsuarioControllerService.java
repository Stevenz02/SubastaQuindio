package co.edu.uniquindio.subastaq.subastaq.controllerModel.service;

import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;

import java.util.List;

public interface IUsuarioControllerService {
    List<UsuarioDto> obtenerUsuarios();
    boolean agregarUsuario(UsuarioDto usuarioDto);
    boolean eliminarUsuario(String cedula);
    void registrarAcciones(String mensaje, int nivel, String accion);
    public boolean actualizarUsuario(String cedulaActual, UsuarioDto usuarioDto);
}
