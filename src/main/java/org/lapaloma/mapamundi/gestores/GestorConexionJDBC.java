package org.lapaloma.mapamundi.gestores;

import java.sql.Connection;
import java.sql.DriverManager;

public class GestorConexionJDBC {

    // Evita que pueda construirse un objeto de la clase.
    private GestorConexionJDBC() {
    }

    public static Connection getConexionSGDB() throws Exception {
        Connection conexionSGDB = null;

        // Datos URL
        String urlBBDD = GestorFicheroConfiguracion.obtenerValor("jdbc.url");

        String usuario = GestorFicheroConfiguracion.obtenerValor("jdbc.usuario");
        String contrasenya = GestorFicheroConfiguracion.obtenerValor("jdbc.password");

        String claseDriver = GestorFicheroConfiguracion.obtenerValor("jdbc.driver");
        Class.forName(claseDriver);

        conexionSGDB = DriverManager.getConnection(urlBBDD, usuario, contrasenya);

        return conexionSGDB;
    }

}
