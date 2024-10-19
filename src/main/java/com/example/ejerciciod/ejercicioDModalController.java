package com.example.ejerciciod;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Persona;

/**
 * Controlador para la ventana modal que permite agregar una nueva persona. (y futuramente mas funciones)
 */
public class ejercicioDModalController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField apellidosField;

    @FXML
    private TextField edadField;

    @FXML
    private Button agregarButton;

    private ObservableList<Persona> personasList;

    /**
     * Establece la lista de personas a la que se agregará la nueva persona.
     *
     * @param personasList Lista de personas.
     */
    public void setPersonasList(ObservableList<Persona> personasList) {
        this.personasList = personasList;
    }

    /**
     * Agrega una nueva persona a la lista de personas y cierra la ventana modal.
     */
    @FXML
    private void agregarPersona() {
        String nombre = nombreField.getText().trim();
        String apellidos = apellidosField.getText().trim();
        String edadText = edadField.getText().trim();
        StringBuilder errores = new StringBuilder();

        if (nombre.isEmpty()) {
            errores.append("El campo 'Nombre' no puede estar vacío.\n");
        }
        if (apellidos.isEmpty()) {
            errores.append("El campo 'Apellidos' no puede estar vacío.\n");
        }

        int edad = -1;
        try {
            edad = Integer.parseInt(edadText);
            if (edad < 0) {
                errores.append("La edad debe ser un número positivo.\n");
            }
        } catch (NumberFormatException e) {
            errores.append("El campo 'Edad' debe ser un número entero válido.\n");
        }

        if (errores.length() > 0) {
            mostrarError(errores.toString());
            return;
        }

        Persona nuevaPersona = new Persona(nombre, apellidos, edad);

        // Verificar que la persona no sea duplicada
        for (Persona persona : personasList) {
            if (persona.equals(nuevaPersona)) {
                mostrarError("Persona duplicada: Ya existe una persona con los mismos datos.");
                return;
            }
        }

        // Agregar la nueva persona
        personasList.add(nuevaPersona);

        // Cerrar la ventana modal
        cerrarVentana();
    }

    /**
     * Muestra un mensaje de error en una alerta emergente.
     *
     * @param mensaje Mensaje de error a mostrar.
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error en los datos");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Cierra la ventana modal.
     */
    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) agregarButton.getScene().getWindow();
        stage.close();
    }
}
