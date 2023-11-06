package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.ICompradorControllerService;

public class CompradorController implements ICompradorControllerService {
    ModelFactoryController modelFactoryController;
    public CompradorController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }
}
