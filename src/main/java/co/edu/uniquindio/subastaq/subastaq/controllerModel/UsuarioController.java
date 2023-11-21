package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IUsuarioControllerService;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;

import java.util.List;

public class UsuarioController implements IUsuarioControllerService {
    /**
     * Controller de la clase usuario
     */
    ModelFactoryController modelFactoryController;
    public UsuarioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return modelFactoryController.obtenerUsuarios();
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        return modelFactoryController.agregarUsuario(usuarioDto);
    }

    @Override
    public boolean eliminarUsuario(String cedula) {
        return modelFactoryController.eliminarUsuario(cedula);
    }

    @Override
    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }

    @Override
    public boolean actualizarUsuario(String cedulaActual, UsuarioDto usuarioDto) {
        return modelFactoryController.actualizarUsuario(cedulaActual, usuarioDto);
    }
}
