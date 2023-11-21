package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IAnuncioControllerService;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.PujaDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;

import java.util.List;

public class AnuncioController implements IAnuncioControllerService {
    ModelFactoryController modelFactoryController;
    public AnuncioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    @Override
    public List<AnuncioDto> obtenerAnuncios() {
        return modelFactoryController.obtenerAnuncios();
    }

    @Override
    public boolean agregarAnuncio(AnuncioDto anuncioDto, UsuarioDto usuarioDto) {
        return modelFactoryController.agregarAnuncio(anuncioDto, usuarioDto);
    }

    @Override
    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }


    @Override
    public List<PujaDto> obtenerPujas(AnuncioDto anuncioDto) {
        return modelFactoryController.leerListaPujasAnuncio(anuncioDto);
    }
}
