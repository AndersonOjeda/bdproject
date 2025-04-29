package com.example.utils; // Paquete donde se encuentra esta clase utilitaria

import java.sql.Connection;        // Clase para manejar conexiones a bases de datos
import java.sql.DriverManager;     // Clase que permite crear conexiones con la base de datos
import java.sql.SQLException;      // Clase para manejar excepciones relacionadas con SQL

public class DatabaseConnection {
<<<<<<< HEAD
=======
    
    private static String url = "jdbc:mysql://localhost:3306/sakila";
    private static String user = "root";
    private static String pass = "root";


    // URL de conexión a la base de datos 'sakila' alojada en localhost, puerto 3306
    private static String url = "jdbc:mysql://localhost:3306/sakila";

    // Usuario de la base de datos
    private static String user = "root";

    // Contraseña del usuario de la base de datos
    private static String pass = "Socorro378*Cal";

    // Objeto que representa la conexión activa. Es estático para aplicar el patrón Singleton
    private static Connection myConn;

    /**
     * Método estático que devuelve una instancia única de la conexión a la base de datos.
     * Si la conexión aún no ha sido creada, se inicializa con DriverManager.
     * 
     * @return una instancia de Connection activa
     * @throws SQLException si ocurre un error al establecer la conexión
     */
    public static Connection getInstance() throws SQLException {
        // Si la conexión aún no existe, se crea una nueva
        if (myConn == null) {
            myConn = DriverManager.getConnection(url, user, pass);
        }
        // Devuelve la conexión existente o recién creada
        return myConn;
    }
}
