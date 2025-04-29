package com.example.repository; // Define el paquete donde se encuentra esta clase

<<<<<<< HEAD
import com.example.model.Actor;                // Importa la clase Actor del modelo
import com.example.utils.DatabaseConnection;   // Importa la clase que gestiona la conexión con la base de datos

import java.sql.*;                             // Importa las clases necesarias para trabajar con SQL
import java.util.ArrayList;                    // Importa la clase ArrayList para manejar listas dinámicas
import java.util.List;                         // Importa la interfaz List
=======
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Actor;
import com.example.utils.DatabaseConnection;
>>>>>>> 9f433ce9eb60c7217909ff954225d69007b22b11

public class ActorRepository implements Repository<Actor> { // Implementa la interfaz Repository con tipo Actor

<<<<<<< HEAD
    @Override
    public List<Actor> findAll() {             // Método para obtener todos los actores
        List<Actor> actors = new ArrayList<>(); // Lista donde se almacenarán los actores
        try {
            Connection conn = DatabaseConnection.getInstance(); // Obtiene la conexión a la base de datos
            Statement stmt = conn.createStatement();             // Crea una sentencia SQL
            ResultSet rs = stmt.executeQuery("SELECT actor_id, first_name, last_name FROM actor"); // Ejecuta la consulta

            // Recorre los resultados y crea objetos Actor
            while (rs.next()) {
                Actor actor = new Actor(
                    rs.getInt("actor_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
                actors.add(actor); // Agrega el actor a la lista
            }

            rs.close();  // Cierra el ResultSet
            stmt.close(); // Cierra el Statement
        } catch (Exception e) {
            e.printStackTrace(); // Imprime errores si ocurren
        }
        return actors; // Devuelve la lista de actores
    }

    @Override
    public Actor getByID(Integer id) { // Método para obtener un actor por su ID
        Actor actor = null;
        try {
            Connection conn = DatabaseConnection.getInstance(); // Obtiene la conexión
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?" // Consulta con parámetro
            );
            stmt.setInt(1, id); // Establece el ID en la consulta
            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta

            // Si encuentra un resultado, crea el objeto Actor
            if (rs.next()) {
                actor = new Actor(
                    rs.getInt("actor_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
            }

            rs.close();  // Cierra el ResultSet
            stmt.close(); // Cierra el Statement
        } catch (Exception e) {
            e.printStackTrace(); // Imprime errores si ocurren
        }
        return actor; // Devuelve el actor o null si no se encontró
    }

    @Override
    public void save(Actor actor) { // Método para guardar (insertar o actualizar) un actor
        try {
            Connection conn = DatabaseConnection.getInstance(); // Obtiene la conexión

            // Verifica si el actor ya existe en la base de datos
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM actor WHERE actor_id = ?");
            checkStmt.setInt(1, actor.getActorID()); // Establece el ID del actor
            ResultSet rs = checkStmt.executeQuery(); // Ejecuta la consulta

            boolean exists = false;
            if (rs.next()) {
                exists = rs.getInt(1) > 0; // Si el resultado es mayor que 0, el actor existe
            }

            rs.close();      // Cierra el ResultSet
            checkStmt.close(); // Cierra el Statement

            if (exists) {
                // Si el actor ya existe, se actualiza
                PreparedStatement updateStmt = conn.prepareStatement(
                    "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?"
                );
                updateStmt.setString(1, actor.getFirstName()); // Establece el nuevo nombre
                updateStmt.setString(2, actor.getLastName());  // Establece el nuevo apellido
                updateStmt.setInt(3, actor.getActorID());      // Establece el ID
                updateStmt.executeUpdate(); // Ejecuta la actualización
                updateStmt.close();         // Cierra el Statement
                System.out.println("Actor actualizado correctamente.");
            } else {
                // Si no existe, se inserta un nuevo actor
                PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO actor (actor_id, first_name, last_name) VALUES (?, ?, ?)"
                );
                insertStmt.setInt(1, actor.getActorID());         // Establece el ID
                insertStmt.setString(2, actor.getFirstName());    // Establece el nombre
                insertStmt.setString(3, actor.getLastName());     // Establece el apellido
                insertStmt.executeUpdate(); // Ejecuta la inserción
                insertStmt.close();         // Cierra el Statement
                System.out.println("Actor insertado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Imprime errores si ocurren
        }
=======
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Actor> findAll() throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String sql = "SELECT * FROM sakila.actor";

        try (Statement myStatement = getConnection().createStatement()) {
            ResultSet myResultSet = myStatement.executeQuery(sql);
            while (myResultSet.next()) {
                Actor actor = createActor(myResultSet);
                actors.add(actor);
            }
        }
        return actors;
    }

    @Override
    public Actor getByID(Integer id) throws SQLException {
        Actor actor = null;
        String sql = "SELECT * FROM sakila.actor WHERE actor_id = ?";

        try (PreparedStatement myPreparedStatement = getConnection().prepareStatement(sql)) {
            myPreparedStatement.setInt(1, id);
            try (ResultSet myResultSet = myPreparedStatement.executeQuery()) {
                if (myResultSet.next()) {
                    actor = createActor(myResultSet);
                }
            }
        }
        return actor;

    }

    private Actor createActor(ResultSet myResultSet) throws SQLException {
        Actor a = new Actor();
        a.setActorID(myResultSet.getInt("actor_id"));
        a.setFirstName(myResultSet.getString("first_name"));
        a.setLastName(myResultSet.getString("last_name"));

        return a;
    }

    @Override
    public void save(Actor t) {
        return;
>>>>>>> 9f433ce9eb60c7217909ff954225d69007b22b11
    }

    @Override
    public void delete(Integer id) { // Método para eliminar un actor por ID
        try {
            Connection conn = DatabaseConnection.getInstance(); // Obtiene la conexión
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM actor WHERE actor_id = ?"); // Consulta de eliminación
            stmt.setInt(1, id); // Establece el ID a eliminar
            int rows = stmt.executeUpdate(); // Ejecuta la eliminación
            stmt.close(); // Cierra el Statement

            // Verifica si se eliminó alguna fila
            if (rows > 0) {
                System.out.println("Actor eliminado correctamente.");
            } else {
                System.out.println("No se encontró el actor con ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Imprime errores si ocurren
        }
    }
}
