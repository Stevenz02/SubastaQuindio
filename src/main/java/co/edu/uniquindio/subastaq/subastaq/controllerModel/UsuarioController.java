package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IUsuarioControllerService;

public class UsuarioController implements IUsuarioControllerService {
    ModelFactoryController modelFactoryController;
    public UsuarioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }
}
