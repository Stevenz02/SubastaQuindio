package co.edu.uniquindio.subastaq.subastaq.utils;


import co.edu.uniquindio.subastaq.subastaq.exception.UsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public static final String RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO = "src/main/resources/persistencia/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_SUBASTA_XML = "src/main/resources/persistencia/model.xml";
    public static final String RUTA_ARCHIVO_COMPRADORES = "src/main/resources/persistencia/archivoCompradores.txt";
    public static final String RUTA_ARCHIVO_ANUNCIOS = "src/main/resources/persistencia/archivoAnuncios.txt";
    public static final String RUTA_ARCHIVO_ANUNCIANTES = "src/main/resources/persistencia/archivoAnunciantes.txt";
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_PRODUCTOS = "src/main/resources/persistencia/archivoProductos.txt";
    public static final String RUTA_ARCHIVO_PUJAS = "src/main/resources/persistencia/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/persistencia/SubastaLog.txt";



    public static void cargarDatosArchivos(SubastaUniquindio subastaUq) throws FileNotFoundException, IOException {
        cargarSubastaUniquindio(subastaUq);//cargar subasta
    }


    /**
     *
     * @param subasta
     */
    public static void guardarSubastaUniquindio(SubastaUniquindio subasta) {
        try {
            guardarUsuarios(subasta.getListaUsuarios());
            guardarAnunciantes(subasta.getListaAnunciantes());
            guardarCompradores(subasta.getListaCompradores());
        } catch (IOException e) {
            e.printStackTrace(); // Manejo del error al guardar los datos
        }
    }

    /**
     *
     * @param listaUsuarios
     * @throws IOException
     */
    public static void guardarUsuarios(List<Usuario> listaUsuarios) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Usuario usuario:listaUsuarios) {
            contenido += usuario.getNombre()+","+usuario.getApellido()+","+usuario.getCedula()+","+usuario.getNombreUsuario()
                    +","+usuario.getContrasenia()+","+usuario.getEdad()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
    }

    /**
     *
     * @param listaAnunciantes
     * @throws IOException
     */
    public static void guardarAnunciantes(List<Anunciante> listaAnunciantes) throws IOException {
        String contenido = "";
        for(Anunciante anunciante:listaAnunciantes)
        {
            contenido+= anunciante.getNombre()+","+anunciante.getApellido()+","+anunciante.getCedula()+","+anunciante.getNombreUsuario()
                    +","+anunciante.getContrasenia()+","+anunciante.getEdad()+","+anunciante.getCantidadAnuncios()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ANUNCIANTES, contenido, false);
    }

    /**
     *
     * @param listaCompradores
     * @throws IOException
     */
    public static void guardarCompradores(List<Comprador> listaCompradores) throws IOException {
        String contenido = "";
        for(Comprador comprador:listaCompradores)
        {
            contenido+= comprador.getNombre()+","+comprador.getApellido()+","+comprador.getCedula()+","+comprador.getNombreUsuario()
                    +","+comprador.getContrasenia()+","+comprador.getEdad()+","+comprador.getPujaActual().getCodigo()+ comprador.getPujaActual().getFecha()+comprador.getPujaActual().getOfertaActual()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_COMPRADORES, contenido, false);
    }

//	----------------------LOADS------------------------

    /**
     *
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static SubastaUniquindio cargarSubastaUniquindio(SubastaUniquindio subasta) {
        try {
            List<Anunciante> anunciantes = cargarAnunciante();
            List<Usuario> usuarios = cargarUsuarios();
            List<Comprador> compradores = cargarCompradores();

            subasta.setListaAnunciantes(anunciantes);
            subasta.setListaUsuarios(usuarios);
            subasta.setListaCompradores(compradores);
        } catch (IOException e) {
            e.printStackTrace(); // Manejo del error al cargar los datos
        }
        return subasta;
    }

    /**
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<Usuario> cargarUsuarios() throws FileNotFoundException, IOException
    {
        List<Usuario> usuarios =new ArrayList<Usuario>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Usuario usuario = new Usuario();
            usuario.setNombre(linea.split(",")[0]);
            usuario.setApellido(linea.split(",")[1]);
            usuario.setCedula(linea.split(",")[2]);
            usuario.setNombreUsuario(linea.split(",")[3]);
            usuario.setContrasenia(linea.split(",")[4]);
            usuario.setEdad(Integer.parseInt(linea.split(",")[5]));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    /**
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<Anunciante> cargarAnunciante() throws FileNotFoundException, IOException
    {
        List<Anunciante> anunciantes =new ArrayList<Anunciante>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIANTES);
        String linea="";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Anunciante anunciante = new Anunciante();
            anunciante.setNombre(linea.split(",")[0]);
            anunciante.setApellido(linea.split(",")[1]);
            anunciante.setCedula(linea.split(",")[2]);
            anunciante.setNombreUsuario(linea.split(",")[3]);
            anunciante.setContrasenia(linea.split(",")[4]);
            anunciante.setEdad(Integer.parseInt(linea.split(",")[5]));
            anunciante.setCantidadAnuncios(Integer.valueOf(linea.split(",")[6]));
            anunciantes.add(anunciante);
        }
        return anunciantes;
    }

    /**
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<Puja> cargarPujas() throws FileNotFoundException, IOException
    {
        List<Puja> pujas =new ArrayList<Puja>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PUJAS);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); // Define el formato de la fecha del archivo
        for (String linea : contenido) {
            String[] datos = linea.split(",");
            Puja puja = new Puja();
            puja.setCodigo(datos[0]);
            // Conversión de String a Date
            try {
                puja.setFecha(formatoFecha.parse(datos[1])); // Aquí se realiza la conversión a Date
            } catch (ParseException e) {
                e.printStackTrace(); // Manejo de errores si la conversión falla
            }
            puja.setOfertaActual(Double.valueOf(datos[2]));
            pujas.add(puja);
        }
        return pujas;
    }

    /**
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<Comprador> cargarCompradores() throws FileNotFoundException, IOException {
        List<Comprador> compradores = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_COMPRADORES);

        for (String linea : contenido) {
            String[] datos = linea.split(",");
            Comprador comprador = new Comprador();

            comprador.setNombreUsuario(datos[0]);
            comprador.setContrasenia(datos[1]);
            comprador.setNombre(datos[2]);
            comprador.setApellido(datos[3]);
            comprador.setCedula(datos[4]);
            comprador.setEdad(Integer.parseInt(datos[5]));

            // Cargar pujas asociadas al comprador
            List<Puja> pujasComprador = cargarPujas(); // Aquí llamas al método para cargar las pujas

            // Asignar pujas al comprador
            for (Puja puja : pujasComprador) {
                if (puja.getCodigo().equals(datos[6])) {
                    comprador.setPujaActual(puja);
                    break; // Asumiendo que solo hay una puja actual por comprador
                }
            }

            compradores.add(comprador);
        }
        return compradores;
    }

    /**
     *
     * @param mensajeLog
     * @param nivel
     * @param accion
     */
    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    /**
     *
     * @param usuario
     * @param contrasenia
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws UsuarioExepcion
     * @throws UsuarioExepcion
     */
    public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioExepcion, UsuarioExepcion {

        if(validarUsuario(usuario,contrasenia)) {
            return true;
        }else {
            throw new UsuarioExepcion ("Usuario no existe");
        }

    }

    /**
     *
     * @param usuario
     * @param contrasenia
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException
    {
        List<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);

        for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++)
        {
            Usuario usuarioAux = usuarios.get(indiceUsuario);
            if(usuarioAux.getNombreUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param ruta
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<Usuario> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
        List<Usuario> usuarios =new ArrayList<Usuario>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
        String linea="";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);

            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(linea.split(",")[0]);
            usuario.setContrasenia(linea.split(",")[1]);

            usuarios.add(usuario);
        }
        return usuarios;
    }


//	----------------------SAVES------------------------

    /**
     *
     * @param listaUsuarios
     * @param ruta
     * @throws IOException
     */
    public static void guardarObjetos(List<Usuario> listaUsuarios, String ruta) throws IOException  {
        String contenido = "";

        for(Usuario usuarioAux:listaUsuarios) {
            contenido+= usuarioAux.getNombre()+","+usuarioAux.getApellido()+","+usuarioAux.getCedula()+usuarioAux.getNombreUsuario()
                    +","+usuarioAux.getContrasenia()+","+usuarioAux.getEdad()+"\n";
        }
        ArchivoUtil.guardarArchivo(ruta, contenido, true);
    }





    //------------------------------------SERIALIZACIÓN  y XML


    public static SubastaUniquindio cargarRecursoSubastaBinario() {

        SubastaUniquindio subastaUniquindio = null;

        try {
            subastaUniquindio = (SubastaUniquindio) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return subastaUniquindio;
    }

    public static void guardarRecursoSubastaBinario(SubastaUniquindio subastaUniquindio) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO, subastaUniquindio);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static SubastaUniquindio cargarRecursoSubastaXML() {

        SubastaUniquindio subastaUniquindio = null;

        try {
            subastaUniquindio = (SubastaUniquindio) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SUBASTA_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return subastaUniquindio;

    }


    public static void guardarRecursoSubastaXML(SubastaUniquindio subastaUniquindio) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SUBASTA_XML, subastaUniquindio);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}