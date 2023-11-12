package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IAnuncioControllerService;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;

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
    public boolean agregarAnuncio(AnuncioDto anuncioDto) {
        return modelFactoryController.agregarAnuncio(anuncioDto);
    }

    @Override
    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }
}
