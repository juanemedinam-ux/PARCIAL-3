package co.edu.uniquindio.edu.poo.parcial3.viewController;

import co.edu.uniquindio.edu.poo.parcial3.App;
import co.edu.uniquindio.edu.poo.parcial3.controller.PanaderiaController;
import co.edu.uniquindio.edu.poo.parcial3.model.Pan;
import co.edu.uniquindio.edu.poo.parcial3.model.Pedido;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PanaderiaViewController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtCliente;
    @FXML private ComboBox<Pan> cbTipoPan;
    @FXML private TextField txtCantidad;

    @FXML private TableView<Pedido> tblPedidos;
    @FXML private TableColumn<Pedido, String> colCodigo;
    @FXML private TableColumn<Pedido, String> colCliente;
    @FXML private TableColumn<Pedido, String> colPan;
    @FXML private TableColumn<Pedido, Integer> colCantidad;
    @FXML private TableColumn<Pedido, String> colEstado;
    @FXML private TableColumn<Pedido, String> colAsignado;
    @FXML private TableColumn<Pedido, Integer> colTiempo;

    private PanaderiaController controller;

    private final ObservableList<Pedido> listaPedidos =
            FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        cbTipoPan.setItems(
                FXCollections.observableArrayList(Pan.values())
        );
        cbTipoPan.getSelectionModel().selectFirst();

        colCodigo.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getCodigo()
                )
        );

        colCliente.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getNombreCliente()
                )
        );

        colPan.setCellValueFactory(cellData -> {
            Pan pan = cellData.getValue().getTipoPan();
            return new SimpleStringProperty(
                    pan != null ? pan.toString() : ""
            );
        });

        colCantidad.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(
                        cellData.getValue().getCantidadPan()
                ).asObject()
        );

        colEstado.setCellValueFactory(cellData -> {
            var estado = cellData.getValue().getEstado();
            return new SimpleStringProperty(
                    estado != null ? estado.toString() : ""
            );
        });

        colAsignado.setCellValueFactory(cellData -> {
            String asignado =
                    cellData.getValue().getCodigoPanaderoAsignado();

            return new SimpleStringProperty(
                    asignado != null ? asignado : "Ninguno"
            );
        });

        colTiempo.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(
                        cellData.getValue().getMinutosReservados()
                ).asObject()
        );

        tblPedidos.setItems(listaPedidos);
    }

    public void setApp(App app) {
        this.controller = new PanaderiaController(App.panaderia);
        actualizarTabla();
    }

    @FXML
    private void onRegistrarPedido() {
        try {
            String codigo = txtCodigo.getText().trim();
            String cliente = txtCliente.getText().trim();
            Pan tipo = cbTipoPan.getValue();
            String textoCantidad = txtCantidad.getText().trim();

            if (codigo.isEmpty() || cliente.isEmpty()) {
                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Advertencia",
                        "Debe llenar el código y el cliente."
                );
                return;
            }

            if (textoCantidad.isEmpty()) {
                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Advertencia",
                        "Debe ingresar una cantidad."
                );
                return;
            }

            int cantidad = Integer.parseInt(textoCantidad);

            controller.registrarPedido(
                    codigo, cliente, tipo, cantidad
            );

            actualizarTabla();

            txtCodigo.clear();
            txtCliente.clear();
            txtCantidad.clear();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Éxito",
                    "Pedido registrado exitosamente."
            );

        } catch (NumberFormatException e) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "La cantidad debe ser un número entero."
            );

        } catch (IllegalArgumentException e) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    e.getMessage()
            );
        }
    }

    @FXML
    private void onAsignarPedido() {

        Pedido seleccionado =
                tblPedidos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Advertencia",
                    "Seleccione un pedido."
            );
            return;
        }

        try {
            controller.asignarPedido(seleccionado.getCodigo());
            actualizarTabla();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Éxito",
                    "Pedido ASIGNADO."
            );

        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    e.getMessage()
            );
        }
    }

    @FXML
    private void onCancelarPedido() {

        Pedido seleccionado =
                tblPedidos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Advertencia",
                    "Seleccione un pedido."
            );
            return;
        }

        try {
            controller.cancelarPedido(seleccionado.getCodigo());
            actualizarTabla();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Éxito",
                    "Pedido CANCELADO."
            );

        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    e.getMessage()
            );
        }
    }

    private void actualizarTabla() {

        if (controller != null) {
            listaPedidos.setAll(controller.obtenerPedidos());
            tblPedidos.refresh();
        }
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}