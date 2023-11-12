package co.edu.uniquindio.subastaq.subastaq.controllerModel;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IModelFactoryControllerService;
import co.edu.uniquindio.subastaq.subastaq.exception.UsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncianteDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.CompradorDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.mappers.SubastaMapper;
import co.edu.uniquindio.subastaq.subastaq.model.SubastaUniquindio;
import co.edu.uniquindio.subastaq.subastaq.model.Usuario;
import co.edu.uniquindio.subastaq.subastaq.utils.Persistencia;
import java.io.IOException;
import java.util.List;
import static co.edu.uniquindio.subastaq.subastaq.utils.SubastaUtils.inicializarDatos;


public class ModelFactoryController implements IModelFactoryControllerService {
    SubastaUniquindio subastaUniquindio;
    SubastaMapper mapper = SubastaMapper.INSTANCE;

    //------------------------------  Singleton ------------------------------------------------
    // Clase estatica oculta. Tan solo se instanciara el singleton una vez
    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("invocación clase singleton");
        cargarDatosBase();
        salvarDatosPrueba();

        //2. Cargar los datos de los archivos
        cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
        cargarResourceBinario();
        guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
        cargarResourceXML();
        guardarResourceXML();

        //Siempre se debe verificar si la raiz del recurso es null

        if(subastaUniquindio == null){
            cargarDatosBase();
            guardarResourceXML();
        }
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");
    }

    private void cargarDatosDesdeArchivos() {
        subastaUniquindio = new SubastaUniquindio();
        try {
            Persistencia.cargarDatosArchivos(subastaUniquindio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void salvarDatosPrueba() {
        Persistencia.guardarSubastaUniquindio(getSubastaUniquindio());
        Persistencia.guardarAnunciantes(getSubastaUniquindio().getListaAnunciantes());
        Persistencia.guardarCompradores(getSubastaUniquindio().getListaCompradores());
        Persistencia.guardarUsuarios(getSubastaUniquindio().getListaUsuarios());
    }

    private void cargarDatosBase() {
        subastaUniquindio = inicializarDatos();
    }

    public SubastaUniquindio getSubastaUniquindio() {
        return subastaUniquindio;
    }

    public void setSubastaUniquindio(SubastaUniquindio subastaUniquindio) {
        this.subastaUniquindio = subastaUniquindio;
    }


    private void cargarResourceXML() {
        subastaUniquindio = Persistencia.cargarRecursoSubastaXML();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoSubastaXML(subastaUniquindio);
    }

    private void cargarResourceBinario() {
        subastaUniquindio = Persistencia.cargarSubasta();
    }

    private void guardarResourceBinario() {
        Persistencia.guardarSubasta(subastaUniquindio);
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return mapper.getUsuariosDto(subastaUniquindio.getListaUsuarios());
    }

    @Override
    public List<CompradorDto> obtenerCompradores() {
        return mapper.getCompradoresDto(subastaUniquindio.getListaCompradores());
    }

    @Override
    public List<AnuncianteDto> obtenerAnunciantes() {
        return mapper.getAnunciantesDto(subastaUniquindio.getListaAnunciantes());
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try{
            if (!subastaUniquindio.usuarioExiste(usuarioDto.cedula()) && subastaUniquindio.isMayor(usuarioDto.edad())){
                Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
                getSubastaUniquindio().agregarUsuario(usuario);
                guardarResourceXML();
                return true;
            }
            return false;
        }catch (UsuarioExepcion e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean actualizarUsuario(String cedula, UsuarioDto usuarioDto) {
        try{
            Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
            getSubastaUniquindio().actualizarUsuario(cedula,usuario);
            return true;
        } catch (UsuarioExepcion e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(String cedula) {
        boolean flag = false;
        try{
            flag = getSubastaUniquindio().eliminarUsuario(cedula);
        }catch (UsuarioExepcion e){
            e.printStackTrace();
        }
        return flag;
    }

}
