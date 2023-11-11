package co.edu.uniquindio.subastaq.subastaq.utils;

import co.edu.uniquindio.subastaq.subastaq.model.Anunciante;
import co.edu.uniquindio.subastaq.subastaq.model.Comprador;
import co.edu.uniquindio.subastaq.subastaq.model.SubastaUniquindio;
import co.edu.uniquindio.subastaq.subastaq.model.Usuario;

public class SubastaUtils {

    public static SubastaUniquindio inicializarDatos() {
        SubastaUniquindio subastaUniquindio = new SubastaUniquindio();

        Usuario usuario = new Usuario();
        usuario.setNombre("juan 2");
        usuario.setApellido("arias");
        usuario.setCedula("125454");
        usuario.setEdad(18);
        usuario.setTipo("Comprador");
        usuario.setNombreUsuario("admins");
        usuario.setContrasenia("contra");
        subastaUniquindio.getListaUsuarios().add(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("juan 3");
        usuario2.setApellido("ari2s");
        usuario2.setCedula("125454");
        usuario2.setEdad(32);
        usuario2.setTipo("Anunciante");
        usuario2.setNombreUsuario("admins");
        usuario2.setContrasenia("contra");
        subastaUniquindio.getListaUsuarios().add(usuario2);

        Comprador comprador = new Comprador();
        comprador.setNombre("juan 3");
        comprador.setApellido("ari2s");
        comprador.setCedula("125454");
        comprador.setEdad(32);
        comprador.setNombreUsuario("admins");
        comprador.setContrasenia("contra");
        subastaUniquindio.getListaCompradores().add(comprador);


        Anunciante anunciante = new Anunciante();
        anunciante.setNombre("juan 4");
        anunciante.setApellido("ari2s");
        anunciante.setCedula("125454");
        anunciante.setEdad(32);
        anunciante.setNombreUsuario("admins");
        anunciante.setContrasenia("contra");
        subastaUniquindio.getListaAnunciantes().add(anunciante);

        System.out.println("Información de la subasta creada");
        return subastaUniquindio;
    }
}

