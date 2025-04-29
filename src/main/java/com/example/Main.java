package com.example; // Paquete principal del proyecto

import com.example.model.Actor;               // Importa la clase Actor del modelo
import com.example.repository.ActorRepository; // Importa la clase que maneja operaciones con la base de datos
import java.util.List;                        // Importa la interfaz List para manejar listas

import com.example.model.Actor;
import com.example.repository.ActorRepository;
import com.example.repository.Repository;
import com.example.utils.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
        try {
            // Crea una instancia del repositorio que permite interactuar con la tabla 'actor'
            ActorRepository repository = new ActorRepository();

            System.out.println("==== TODOS LOS ACTORES ====");
            // Obtiene todos los actores de la base de datos
            List<Actor> allActors = repository.findAll();
            // Recorre la lista y muestra cada actor por consola
            for (Actor actor : allActors) {
                System.out.println(actor.getActorID() + " - " + actor.getFirstName() + " " + actor.getLastName());
            }

            System.out.println("\n==== ACTOR POR ID ====");
            // Obtiene un actor por ID (en este caso, ID = 100)
            Actor actorById = repository.getByID(100); // Cambia el ID si quieres probar otro
            if (actorById != null) {
                // Muestra los datos del actor si se encontró
                System.out.println(actorById.getActorID() + " - " + actorById.getFirstName() + " " + actorById.getLastName());
            } else {
                // Muestra mensaje si no se encontró el actor
                System.out.println("Actor no encontrado.");
            }

            // ===== PRUEBA INSERTAR NUEVO ACTOR =====
            System.out.println("\n==== INSERTANDO NUEVO ACTOR ====");
            // Crea un nuevo objeto Actor con ID 202 y lo guarda en la base de datos
            Actor nuevo = new Actor(202, "DANILO", "PERRA");
            repository.save(nuevo);

            // ===== PRUEBA ACTUALIZAR ACTOR EXISTENTE =====
            System.out.println("\n==== ACTUALIZANDO ACTOR EXISTENTE ====");
            // Crea un actor existente con ID 200 y actualiza su información
            Actor actualizado = new Actor(200, "WER", "QWE");
            repository.save(actualizado);

            // ===== PRUEBA ELIMINAR ACTOR POR ID =====
            System.out.println("\n==== ELIMINANDO ACTOR ====");
            // Elimina al actor con ID 201 de la base de datos
            repository.delete(201);

        } catch (Exception e) {
            // Captura cualquier excepción y muestra el mensaje de error
            System.out.println("Error de conexión o consulta: " + e.getMessage());
            e.printStackTrace(); // Imprime el seguimiento del error para depuración
=======
        try (Connection myConnection = DatabaseConnection.getInstance()){
          
            Repository<Actor> repository =  new ActorRepository();
            Actor actor1 = repository.getByID(1);

          System.out.println(actor1);
        } catch (Exception e) {
           System.out.println(e);
>>>>>>> 9f433ce9eb60c7217909ff954225d69007b22b11
        }
    }
}
