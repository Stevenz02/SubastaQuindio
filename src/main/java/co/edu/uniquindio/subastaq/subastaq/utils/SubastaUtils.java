package co.edu.uniquindio.subastaq.subastaq.utils;

import co.edu.uniquindio.subastaq.subastaq.model.Anunciante;
import co.edu.uniquindio.subastaq.subastaq.model.Comprador;
import co.edu.uniquindio.subastaq.subastaq.model.SubastaUniquindio;
import co.edu.uniquindio.subastaq.subastaq.model.Usuario;
/**
 * Clase utilitaria para operaciones relacionadas con la inicialización de datos de subasta.
 */
public class SubastaUtils {

    /**
     * Inicializa y carga datos para una instancia de SubastaUniquindio desde un recurso XML.
     *
     * @return Instancia de SubastaUniquindio con datos cargados desde el recurso XML.
     */
    public static SubastaUniquindio inicializarDatos() {
        // Crear una nueva instancia de SubastaUniquindio
        SubastaUniquindio subastaUniquindio = new SubastaUniquindio();

        // Cargar datos desde el recurso XML utilizando la clase Persistencia
        subastaUniquindio = Persistencia.cargarRecursoSubastaXML();

        // Imprimir un mensaje indicando que la información de la subasta ha sido creada
        System.out.println("Información de la subasta creada");

        // Devolver la instancia de SubastaUniquindio con los datos cargados
        return subastaUniquindio;
    }
}


