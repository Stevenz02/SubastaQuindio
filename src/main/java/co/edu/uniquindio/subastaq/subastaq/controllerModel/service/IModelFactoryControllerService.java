package co.edu.uniquindio.subastaq.subastaq.controllerModel.service;

import co.edu.uniquindio.subastaq.subastaq.exception.BuscarUsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncianteDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.CompradorDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;
import javafx.event.ActionEvent;

import java.util.List;

public interface IModelFactoryControllerService {
    List<UsuarioDto> obtenerUsuarios();
    List<CompradorDto> obtenerCompradores();
    List<AnuncianteDto> obtenerAnunciantes();
    boolean agregarUsuario(UsuarioDto usuarioDto);
    boolean actualizarUsuario (String cedula, UsuarioDto usuarioDto);
    boolean eliminarUsuario(String cedula);
    void iniciarSesion(String nombreUsuario, String contrasenia, ActionEvent eventoMouse) throws BuscarUsuarioExepcion;
    void cargarVistaComprador(ActionEvent actionEvent);
    void cargarVistaAnunciante(ActionEvent actionEvent);
}
