package co.edu.uniquindio.subastaq.subastaq.utils;

import co.edu.uniquindio.subastaq.subastaq.model.Anunciante;
import co.edu.uniquindio.subastaq.subastaq.model.Comprador;
import co.edu.uniquindio.subastaq.subastaq.model.SubastaUniquindio;
import co.edu.uniquindio.subastaq.subastaq.model.Usuario;

public class SubastaUtils {

    public static SubastaUniquindio inicializarDatos() {
        SubastaUniquindio subastaUniquindio = new SubastaUniquindio();
        subastaUniquindio = Persistencia.cargarRecursoSubastaXML();

        System.out.println("Información de la subasta creada");
        return subastaUniquindio;
    }
}

