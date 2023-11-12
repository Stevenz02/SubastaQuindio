package co.edu.uniquindio.subastaq.subastaq.model.service;

import co.edu.uniquindio.subastaq.subastaq.exception.AnuncianteExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.CompradorExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.UsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.model.*;

import java.util.ArrayList;
import java.util.List;

public interface ISubastaService {
    public Usuario crearUsuario(String nombre, String apellido, Integer edad, String cedula, String NombreUsuario, String contrasenia, String tipo) throws UsuarioExepcion, CompradorExepcion, AnuncianteExepcion;
    public boolean isMayor(Integer edad);
    public boolean usuarioExiste(String cedula);
    public Boolean eliminarUsuario(String cedula)throws UsuarioExepcion;
    boolean actualizarUsuario(String cedulaActual, Usuario usuario) throws UsuarioExepcion;
    public boolean  verificarUsuarioExistente(String cedula) throws UsuarioExepcion;
    public Usuario obtenerUsuario(String cedula);
    public List<Usuario> obtenerUsuarios();
    public boolean verificarCredenciales(String nombreUsuario, String contrasenia);
    public Usuario buscarUsuario (String nombreUsuario, String contrasenia) throws UsuarioExepcion;


    public void crearComprador(Usuario usuario) throws CompradorExepcion;
    public Boolean eliminarComprador(String cedula)throws CompradorExepcion;
    boolean actualizarComprador(String cedulaActual, Comprador comprador) throws CompradorExepcion;
    public Comprador obtenerComprador(String cedula);
    public Puja crearPuja(Double oferta);


    public void crearAnunciante(Usuario usuario) throws AnuncianteExepcion;
    public Boolean eliminarAnunciante(String cedula)throws AnuncianteExepcion;
    boolean actualizarAnunciante(String cedulaActual, Anunciante anunciante) throws AnuncianteExepcion;
    public Anunciante obtenerAnunciante(String cedula);
    public Anuncio crearAnuncio();
}
