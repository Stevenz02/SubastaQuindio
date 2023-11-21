package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IAnuncioControllerService;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.PujaDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;

import java.util.List;

public class AnuncioController implements IAnuncioControllerService {
    /**
     * Controller de la clase anuncio
     */
    ModelFactoryController modelFactoryController;
    public AnuncioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    /**
     * Metodo que llama al model factory para obtener la lista de anuncios
     * @return
     */
    @Override
    public List<AnuncioDto> obtenerAnuncios() {
        return modelFactoryController.obtenerAnuncios();
    }

    /**
     * Metodo que llama al model factory para crear un anuncio
     * @param anuncioDto
     * @param usuarioDto
     * @return
     */
    @Override
    public boolean agregarAnuncio(AnuncioDto anuncioDto, UsuarioDto usuarioDto) {
        return modelFactoryController.agregarAnuncio(anuncioDto, usuarioDto);
    }

    /**
     * Metodo que se usa para registrar las acciones en el log
     * @param mensaje
     * @param nivel
     * @param accion
     */
    @Override
    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }

    /**
     * Metodo que se usa para obtener la lista de pujas de un anuncio
     * @param anuncioDto
     * @return
     */
    @Override
    public List<PujaDto> obtenerPujas(AnuncioDto anuncioDto) {
        return modelFactoryController.leerListaPujasAnuncio(anuncioDto);
    }
}
