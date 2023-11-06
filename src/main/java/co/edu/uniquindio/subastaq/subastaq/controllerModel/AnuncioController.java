package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IAnuncioControllerService;

public class AnuncioController implements IAnuncioControllerService {
    ModelFactoryController modelFactoryController;
    public AnuncioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }
}
