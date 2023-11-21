package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.ISubastaControllerService;

public class SubastaController implements ISubastaControllerService {
    /**
     * Controller de la clase subasta
     */
    ModelFactoryController modelFactoryController;
    public SubastaController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

}
