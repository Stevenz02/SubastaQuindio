package co.edu.uniquindio.subastaq.subastaq.utils;

import co.edu.uniquindio.subastaq.subastaq.exception.UsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.model.*;

import java.beans.XMLEncoder;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    public static final String RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO = "src/main/resources/persistencia/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_SUBASTA_XML = "src/main/resources/persistencia/model.xml";
    public static final String RUTA_ARCHIVO_COMPRADORES = "src/main/resources/persistencia/archivoCompradores.txt";
    public static final String RUTA_ARCHIVO_ANUNCIANTES = "src/main/resources/persistencia/archivoAnunciantes.txt";
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/persistencia/SubastaLog.txt";

    //	---------------------- Saves ------------------------   //

    public static void guardarSubasta(SubastaUniquindio subasta) {
        guardarSubastaUniquindio(subasta);
        guardarUsuarios(subasta.getListaUsuarios());
        guardarAnunciantes(subasta.getListaAnunciantes());
        guardarCompradores(subasta.getListaCompradores());
    }
    public static void guardarRecursoSubastaXML(SubastaUniquindio subastaUniquindio) {
        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SUBASTA_XML, subastaUniquindio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void guardarSubastaUniquindio(SubastaUniquindio subasta) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO))) {
            oos.writeObject(subasta);
            System.out.println("SubastaUniquindio guardada exitosamente en " + RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void guardarUsuarios(List<Usuario> listaUsuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO_USUARIOS))) {
            oos.writeObject(listaUsuarios);
            System.out.println("Usuarios guardados exitosamente en " + RUTA_ARCHIVO_USUARIOS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarAnunciantes(List<Anunciante> listaAnunciantes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO_ANUNCIANTES))) {
            oos.writeObject(listaAnunciantes);
            System.out.println("Anunciantes guardados exitosamente en " + RUTA_ARCHIVO_ANUNCIANTES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarCompradores(List<Comprador> listaCompradores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO_COMPRADORES))) {
            oos.writeObject(listaCompradores);
            System.out.println("Compradores guardados exitosamente en " + RUTA_ARCHIVO_COMPRADORES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void salvarRecursoSerializadoXML(String rutaArchivo, Object objeto) throws IOException {
        XMLEncoder codificadorXML;
        codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
        codificadorXML.writeObject(objeto);
        codificadorXML.close();
    }

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }
    //	---------------------- Loads ------------------------   //

    public static SubastaUniquindio cargarDatosArchivos(SubastaUniquindio subastaUq) throws FileNotFoundException, IOException {
        return cargarSubastaUniquindio(subastaUq);
    }
    public static SubastaUniquindio cargarSubastaUniquindio(SubastaUniquindio subasta) {
        List<Anunciante> anunciantes = cargarAnunciantes();
        List<Usuario> usuarios = cargarUsuarios();
        List<Comprador> compradores = cargarCompradores();
        subasta.setListaAnunciantes(anunciantes);
        subasta.setListaUsuarios(usuarios);
        subasta.setListaCompradores(compradores);
        return subasta;
    }
    public static SubastaUniquindio cargarSubasta() {
        SubastaUniquindio subasta = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO))) {
            subasta = (SubastaUniquindio) ois.readObject();
            System.out.println("SubastaUniquindio cargada exitosamente desde " + RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return subasta;
    }

    public static List<Usuario> cargarUsuarios() {
        List<Usuario> listaUsuarios = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO_USUARIOS))) {
            listaUsuarios = (List<Usuario>) ois.readObject();
            System.out.println("Usuarios cargados exitosamente desde " + RUTA_ARCHIVO_USUARIOS);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public static List<Anunciante> cargarAnunciantes() {
        List<Anunciante> listaAnunciantes = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO_ANUNCIANTES))) {
            listaAnunciantes = (List<Anunciante>) ois.readObject();
            System.out.println("Anunciantes cargados exitosamente desde " + RUTA_ARCHIVO_ANUNCIANTES);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaAnunciantes;
    }

    public static List<Comprador> cargarCompradores() {
        List<Comprador> listaCompradores = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO_COMPRADORES))) {
            listaCompradores = (List<Comprador>) ois.readObject();
            System.out.println("Compradores cargados exitosamente desde " + RUTA_ARCHIVO_COMPRADORES);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaCompradores;
    }

    public static SubastaUniquindio cargarRecursoSubastaXML() {
        SubastaUniquindio subastaUniquindio = null;
        try {
            subastaUniquindio = (SubastaUniquindio) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SUBASTA_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subastaUniquindio;
    }


    //	---------------------- Metodos ------------------------   //

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
        List<Usuario> usuarios = Persistencia.cargarUsuarios();
        for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++)
        {
            Usuario usuarioAux = usuarios.get(indiceUsuario);
            if(usuarioAux.getNombreUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioExepcion, UsuarioExepcion {

        if(validarUsuario(usuario,contrasenia)) {
            return true;
        }else {
            throw new UsuarioExepcion ("Usuario no existe");
        }

    }
}