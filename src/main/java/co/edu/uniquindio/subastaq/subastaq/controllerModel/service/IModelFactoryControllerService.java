package co.edu.uniquindio.subastaq.subastaq.controllerModel.service;

import co.edu.uniquindio.subastaq.subastaq.exception.UsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncianteDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.CompradorDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;
import co.edu.uniquindio.subastaq.subastaq.model.Anunciante;
import co.edu.uniquindio.subastaq.subastaq.model.Comprador;
import co.edu.uniquindio.subastaq.subastaq.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IModelFactoryControllerService {
    List<UsuarioDto> obtenerUsuarios();
    List<CompradorDto> obtenerCompradores();
    List<AnuncianteDto> obtenerAnunciantes();
    boolean agregarUsuario(UsuarioDto usuarioDto);
    boolean actualizarUsuario (String cedula, UsuarioDto usuarioDto);
    boolean eliminarUsuario(String cedula);
    void iniciarSesion(String nombreUsuario, String contrasenia) throws UsuarioExepcion;
    void cargarVistaComprador();
    void cargarVistaAnunciante();
}
