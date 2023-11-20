package co.edu.uniquindio.subastaq.subastaq.controllerModel.service;

import co.edu.uniquindio.subastaq.subastaq.exception.AnuncianteExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.BuscarUsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.UsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.*;
import co.edu.uniquindio.subastaq.subastaq.model.Anunciante;
import co.edu.uniquindio.subastaq.subastaq.model.Producto;
import co.edu.uniquindio.subastaq.subastaq.model.Usuario;
import javafx.event.ActionEvent;

import java.util.List;

public interface IModelFactoryControllerService {
    List<UsuarioDto> obtenerUsuarios();
    List<CompradorDto> obtenerCompradores();
    List<AnuncianteDto> obtenerAnunciantes();
    List<AnuncioDto> obtenerAnuncios();
    boolean agregarAnuncio(AnuncioDto anuncioDto, UsuarioDto usuarioDto) throws AnuncianteExepcion;
    boolean agregarUsuario(UsuarioDto usuarioDto);
    boolean actualizarUsuario (String cedula, UsuarioDto usuarioDto);
    boolean eliminarUsuario(String cedula);
    void iniciarSesion(String nombreUsuario, String contrasenia, ActionEvent eventoMouse) throws BuscarUsuarioExepcion;
    void cerrarSesion(ActionEvent actionEvent);
    void cargarVistaComprador(ActionEvent actionEvent);
    void cargarVistaAnunciante(ActionEvent actionEvent);
    AnuncianteDto buscarAnuncianteCedula(String cedula);
    AnuncianteDto crearAnuncianteDto(Anunciante anunciante);
    UsuarioDto userToDto(Usuario usuario);
    Producto productoDtoToProducto (ProductoDto productoDto);
    void producirMensaje(String queue, String message);
}
