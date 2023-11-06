package co.edu.uniquindio.subastaq.subastaq.utils;


import co.edu.uniquindio.subastaq.subastaq.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Persistencia {


    public static final String RUTA_ARCHIVO_SUBASTA ="";
    public static final String RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO = "bancoUq/src/main/resources/persistencia/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_SUBASTA_XML = "bancoUq/src/main/resources/persistencia/model.xml";
    public static final String RUTA_ARCHIVO_COMPRADORES = "bancoUq/src/main/resources/persistencia/archivoClientes.txt";
    public static final String RUTA_ARCHIVO_VENDEDORES = "bancoUq/src/main/resources/persistencia/archivoEmpleados.txt";
    public static final String RUTA_ARCHIVO_ANUNCIOS = "co.edu.uniquindio.programacion3/src/main/resources/persistencia/archivoObjetos.txt";
    public static final String RUTA_ARCHIVO_ANUNCIANTES = "";
    public static final String RUTA_ARCHIVO_USUARIOS = "";
    public static final String RUTA_ARCHIVO_PRODUCTOS = "";
    public static final String RUTA_ARCHIVO_PUJAS = "/src/main/resources/persistencia/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_LOG = "bancoUq/src/main/resources/persistencia/log/BancoLog.txt";



    public static void cargarDatosArchivos(SubastaUniquindio subastaUq) throws FileNotFoundException, IOException {
        //cargar archivo de clientes
        ArrayList<Usuario> usuariosCargados = cargarClientes();
        if(clientesCargados.size() > 0)
            banco.getListaClientes().addAll(clientesCargados);

        //cargar archivos empleados
        ArrayList<Empleado> empleadosCargados = cargarEmpleados();
        if(empleadosCargados.size() > 0)
            banco.getListaEmpleados().addAll(empleadosCargados);

        //cargar archivo transcciones

        //cargar archivo empleados

        //cargar archivo prestamo

    }

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     * @param
     * @param
     * @throws IOException
     */
    public static void guardarClientes(ArrayList<Cliente> listaClientes) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Cliente cliente:listaClientes)
        {
            contenido+= cliente.getNombre()+","+cliente.getApellido()+","+cliente.getCedula()+","+cliente.getDireccion()
                    +","+cliente.getCorreo()+","+cliente.getFechaNacimiento()+","+cliente.getTelefono()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CLIENTES, contenido, false);
    }


    public static void guardarEmpleados(ArrayList<Empleado> listaEmpleados) throws IOException {
        String contenido = "";
        for(Empleado empleado:listaEmpleados)
        {
            contenido+= empleado.getNombre()+
                    ","+empleado.getApellido()+
                    ","+empleado.getCedula()+
                    ","+empleado.getFechaNacimiento()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, false);
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
    public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException
    {
        ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(linea.split(",")[0]);
            usuario.setContrasenia(linea.split(",")[1]);
            usuario.setNombre(linea.split(",")[2]);
            usuario.setApellido(linea.split(",")[3]);
            usuario.setCedula(linea.split(",")[4]);
            usuario.setEdad(Integer.parseInt(linea.split(",")[5]));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public static ArrayList<Anunciante> cargarAnunciante() throws FileNotFoundException, IOException
    {
        ArrayList<Anunciante> anunciantes =new ArrayList<Anunciante>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIANTES);
        String linea="";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Anunciante anunciante = new Anunciante();
            anunciante.setNombreUsuario(linea.split(",")[0]);
            anunciante.setContrasenia(linea.split(",")[1]);
            anunciante.setNombre(linea.split(",")[2]);
            anunciante.setApellido(linea.split(",")[3]);
            anunciante.setCedula(linea.split(",")[4]);
            anunciante.setEdad(Integer.parseInt(linea.split(",")[5]));
            anunciantes.add(anunciante);
        }
        return anunciantes;
    }

    public static ArrayList<Puja> cargarPujas() throws FileNotFoundException, IOException
    {
        ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(linea.split(",")[0]);
            usuario.setContrasenia(linea.split(",")[1]);
            usuario.setNombre(linea.split(",")[2]);
            usuario.setApellido(linea.split(",")[3]);
            usuario.setCedula(linea.split(",")[4]);
            usuario.setEdad(Integer.parseInt(linea.split(",")[5]));
            usuarios.add(usuario);
        }
        return usuarios;
    }
    public static ArrayList<Comprador> cargarCompradores() throws FileNotFoundException, IOException {
        ArrayList<Comprador> compradores =new ArrayList<Comprador>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_COMPRADORES);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Comprador comprador = new Comprador();
            comprador.setNombreUsuario(linea.split(",")[0]);
            comprador.setContrasenia(linea.split(",")[1]);
            comprador.setNombre(linea.split(",")[2]);
            comprador.setApellido(linea.split(",")[3]);
            comprador.setCedula(linea.split(",")[4]);
            comprador.setEdad(Integer.parseInt(linea.split(",")[5]));
            compradores.add(comprador);
        }
        return compradores;
    }


    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }


    public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioExcepcion {

        if(validarUsuario(usuario,contrasenia)) {
            return true;
        }else {
            throw new UsuarioExcepcion("Usuario no existe");
        }

    }

    private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException
    {
        ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);

        for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++)
        {
            Usuario usuarioAux = usuarios.get(indiceUsuario);
            if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Usuario> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
        ArrayList<Usuario> usuarios =new ArrayList<Usuario>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
        String linea="";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);

            Usuario usuario = new Usuario();
            usuario.setUsuario(linea.split(",")[0]);
            usuario.setContrasenia(linea.split(",")[1]);

            usuarios.add(usuario);
        }
        return usuarios;
    }


//	----------------------SAVES------------------------

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     * @param
     * @param ruta
     * @throws IOException
     */

    public static void guardarObjetos(ArrayList<Cliente> listaClientes, String ruta) throws IOException  {
        String contenido = "";

        for(Cliente clienteAux:listaClientes) {
            contenido+= clienteAux.getNombre()+","+clienteAux.getApellido()+","+clienteAux.getCedula()+clienteAux.getDireccion()
                    +","+clienteAux.getCorreo()+","+clienteAux.getFechaNacimiento()+","+clienteAux.getTelefono()+"\n";
        }
        ArchivoUtil.guardarArchivo(ruta, contenido, true);
    }





    //------------------------------------SERIALIZACIÓN  y XML


    public static Banco cargarRecursoBancoBinario() {

        Banco banco = null;

        try {
            banco = (Banco)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_BANCO_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return banco;
    }

    public static void guardarRecursoBancoBinario(Banco banco) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_BANCO_BINARIO, banco);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static Banco cargarRecursoBancoXML() {

        Banco banco = null;

        try {
            banco = (Banco)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_BANCO_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return banco;

    }


    public static void guardarRecursoBancoXML(Banco banco) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_BANCO_XML, banco);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}