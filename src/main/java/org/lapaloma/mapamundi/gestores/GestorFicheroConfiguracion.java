/**
 * 
 */
package org.lapaloma.mapamundi.gestores;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 
* @author Isidoro Nevares Martín - IES Virgen de la Paloma
* @date 20 feb 2026
* 
*/ 
public class GestorFicheroConfiguracion {
    private static final Properties PROPIEDADES = new Properties();

    static {
        try (InputStream is = GestorFicheroConfiguracion.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is != null) {
                PROPIEDADES.load(is);
            } else {
                throw new IllegalStateException("No se encontró application.properties en el classpath");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error al cargar application.properties", e);
        }
    }

    /**
     * Obtiene el valor asociado a la clave especificada.
     *
     * @param clave la clave a buscar
     * @return el valor o {@code null} si no existe
     */
    public static String obtenerValor(String clave) {
        return PROPIEDADES.getProperty(clave);
    }
}
