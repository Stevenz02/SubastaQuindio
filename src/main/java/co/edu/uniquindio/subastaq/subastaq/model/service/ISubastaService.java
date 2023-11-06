package co.edu.uniquindio.subastaq.subastaq.model.service;

import co.edu.uniquindio.subastaq.subastaq.exception.AnuncianteExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.CompradorExepcion;
import co.edu.uniquindio.subastaq.subastaq.exception.UsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.model.Usuario;

public interface ISubastaService {
    public Usuario crearUsuario(String nombre, String apellido, Integer edad, String cedula, String NombreUsuario, String contrasenia, String tipo) throws UsuarioExepcion, CompradorExepcion, AnuncianteExepcion;
    public boolean isMayor(Integer edad);
    public boolean usuarioExiste(String cedula);
}
