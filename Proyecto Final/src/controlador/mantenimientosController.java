package controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Conexion;
import modelo.Mantenimiento;

public class mantenimientosController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<Mantenimiento> tablaMantenimientos;

	@FXML
	private TableColumn<Mantenimiento, String> tcID;

	@FXML
	private TableColumn<Mantenimiento, String> tcTipo;

	@FXML
	private TableColumn<Mantenimiento, String> tcRevision;

	@FXML
	private TableColumn<Mantenimiento, String> tcNextRevision;

	@FXML
	private TableColumn<Mantenimiento, String> tcDescripcion;

	@FXML
	private Button btAnadirRevision;

	@FXML
	private Button btEliminarRevision;

	@FXML
	private ChoiceBox<String> tiposMantenimientos;

	@FXML
	private TextArea descripcion;

	@FXML
	private DatePicker fechaRevision;

	@FXML
	private DatePicker fechaNextRevision;

	ObservableList<Mantenimiento> listaMantenimiento;

	@FXML
	void anadirRevision(ActionEvent event) {

		try {
			
			String txtDescripcion = this.descripcion.getText();
			String txtFechaRevision= this.fechaRevision.getValue().toString();
			String txtFechaNextRevision=this.fechaNextRevision.getValue().toString();
			String txtTipo= tiposMantenimientos.getSelectionModel().getSelectedItem();
			
			if(txtFechaRevision.isBlank()||txtFechaRevision.isEmpty() || txtDescripcion.isBlank()||txtDescripcion.isEmpty()) {
			}
			
			Connection conn = Conexion.getConnection();
			System.out.println("Base de datos Conectada");
			Statement statement = conn.createStatement();

			statement.executeUpdate(
					"INSERT INTO `mantenimiento` (`id`, `matricula`, `tipo`, `descripcion`, `fechaRevision`, `fechaNextRevision`) VALUES (NULL, '"
							+ verVehiculosController.matricula + "', '"+txtTipo+"', '" + txtDescripcion + "', '"
							+ txtFechaRevision+ "', '" + txtFechaNextRevision + "');");

			System.out.println("El Vehiculo se ha creado correctamente");
			conn.close();
		} catch (Exception e) {
			System.out.println("El Vehiculo no se ha podido crear");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error al introducir los datos");
			alert.showAndWait();
		}
		this.fechaRevision.setValue(null);
		this.fechaNextRevision.getEditor().clear();
		this.fechaNextRevision.setValue(null);
		this.fechaNextRevision.getEditor().clear();
		updateMantenimientos();
	}

	@FXML
	void eliminarRevision(ActionEvent event) {
		try {
			Connection conn = Conexion.getConnection();
			PreparedStatement pst = conn.prepareStatement("DELETE FROM mantenimiento WHERE id=?");
			pst.setString(1, tablaMantenimientos.getSelectionModel().getSelectedItem().getId());
			pst.execute();
			conn.close();

		} catch (Exception e) {
			System.out.println("No hay ningun mantenimiento seleccionado");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No hay ningun mantenimiento seleccionado");
			alert.showAndWait();
		}
		updateMantenimientos();

	}

	@FXML
	void initialize() {
		updateMantenimientos();
		
		tiposMantenimientos.getItems().add("Pre-ITV");
		tiposMantenimientos.getItems().add("Reparación");
		tiposMantenimientos.getItems().add("Cambios de Filtros");
		tiposMantenimientos.getItems().add("Neumaticos");
		tiposMantenimientos.getItems().add("Frenos");
		assert tablaMantenimientos != null
				: "fx:id=\"tablaMantenimientos\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert tcID != null : "fx:id=\"tcID\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert tcTipo != null : "fx:id=\"tcTipo\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert tcRevision != null : "fx:id=\"tcRevision\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert tcNextRevision != null
				: "fx:id=\"tcNextRevision\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert tcDescripcion != null
				: "fx:id=\"tcDescripcion\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert btAnadirRevision != null
				: "fx:id=\"btAnadirRevision\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert btEliminarRevision != null
				: "fx:id=\"btEliminarRevision\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert tiposMantenimientos != null
				: "fx:id=\"tiposMantenimientos\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert descripcion != null
				: "fx:id=\"descripcion\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert fechaRevision != null
				: "fx:id=\"fechaRevision\" was not injected: check your FXML file 'mantenimiento.fxml'.";
		assert fechaNextRevision != null
				: "fx:id=\"fechaNextRevision\" was not injected: check your FXML file 'mantenimiento.fxml'.";

	}

	public void updateMantenimientos() {
		tcID.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("id"));
		tcDescripcion.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("descripcion"));
		tcNextRevision.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("fechaNextRevision"));
		tcRevision.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("fechaRevision"));
		tcTipo.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("tipo"));

		try {
			listaMantenimiento = Conexion.getMantenimientos(verVehiculosController.matricula);
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error al conectarse a la base datos");
			alert.showAndWait();
		}
		tablaMantenimientos.setItems(listaMantenimiento);
	}
}
