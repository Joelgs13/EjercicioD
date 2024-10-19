package com.example.ejerciciod;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Persona;

/**
 * Controlador para la vista del ejercicio D que gestiona una lista de personas.
 * Permite agregar personas en una nueva pantalla modal.
 */
public class ejercicioDController {

    @FXML
    private Button agregarButton;

    @FXML
    private TableView<Persona> personTable;

    @FXML
    private TableColumn<Persona, String> nombreColumn;

    @FXML
    private TableColumn<Persona, String> apellidosColumn;

    @FXML
    private TableColumn<Persona, Integer> edadColumn;

    private ObservableList<Persona> personasList = FXCollections.observableArrayList();

    /**
     * Inicializa la vista y vincula las columnas de la tabla con los datos de las personas.
     * Hace que cada columna de la tabla se corresponda con los valores de los campos de texto.
     */
    @FXML
    public void initialize() {
        nombreColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        apellidosColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getApellido()));
        edadColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());

        personTable.setItems(personasList);
    }

    /**
     * Abre una nueva ventana modal para agregar una persona.
     *
     * @param actionEvent Evento disparado por el botón Agregar.
     */
    @FXML
    private void abrirVentanaAgregar(ActionEvent actionEvent) {
        try {
            // Cargar el archivo FXML de la ventana modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ejercicioDmodal.fxml"));
            Parent modalRoot = loader.load();

            // Crear una nueva ventana (Stage) para la ventana modal
            Stage modalStage = new Stage();
            modalStage.setTitle("Agregar Persona");
            modalStage.setScene(new Scene(modalRoot));

            // Establecer la ventana como modal
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(agregarButton.getScene().getWindow());

            // Obtener el controlador de la ventana modal para pasarle la lista de personas
            ejercicioDModalController modalController = loader.getController();
            modalController.setPersonasList(personasList);

            // Mostrar la ventana modal y esperar hasta que se cierre
            modalStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

