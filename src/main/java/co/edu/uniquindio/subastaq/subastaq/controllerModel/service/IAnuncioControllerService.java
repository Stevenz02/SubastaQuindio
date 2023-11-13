package co.edu.uniquindio.subastaq.subastaq.controllerModel.service;

import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;

import java.util.List;

public interface IAnuncioControllerService {
    List<AnuncioDto> obtenerAnuncios();
    boolean agregarAnuncio(AnuncioDto anuncioDto, UsuarioDto usuarioDto);
    void registrarAcciones(String mensaje, int nivel, String accion);
}
