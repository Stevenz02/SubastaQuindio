package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IAnuncianteControllerService;

public class AnuncianteController implements IAnuncianteControllerService {
    ModelFactoryController modelFactoryController;
    public AnuncianteController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }
}
