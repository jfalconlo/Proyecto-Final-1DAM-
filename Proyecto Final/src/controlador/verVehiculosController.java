package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Conexion;
import modelo.Vehiculo;

public class verVehiculosController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<Vehiculo> tablaGestionVehiculo;

	@FXML
	private TableColumn<Vehiculo, String> mostrarMatricula;

	@FXML
	private TableColumn<Vehiculo, String> mostrarMarca;

	@FXML
	private TableColumn<Vehiculo, String> mostrarModelo;

	@FXML
	private Button btEliminarVehiculo;

	@FXML
	private Button btAnadirVehiculo;

	@FXML
	private TextField txtModelo;

	@FXML
	private TextField txtMarca;

	@FXML
	private TextField txtMatricula;

	ObservableList<Vehiculo> listaVehiculos;

	@FXML
	private Button btverMantenimientos;

	public static String matricula;

	@FXML
	void verMantenimientos(ActionEvent event) {

		matricula = tablaGestionVehiculo.getSelectionModel().getSelectedItem().getMatricula();
		

		FXMLLoader añadirusuario = new FXMLLoader(getClass().getResource("/vista/mantenimiento.fxml"));

		try {
			Parent root = añadirusuario.load();

			Scene scene = new Scene(root);
			Stage añadirUsuario = new Stage();
			añadirUsuario.setScene(scene);
			añadirUsuario.setResizable(false);
			añadirUsuario.getIcons().add(new Image("imagenes/icono.png"));
			añadirUsuario.initModality(Modality.APPLICATION_MODAL);
			añadirUsuario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void anadirVehiculo(ActionEvent event) {
		try {
			String modelo = txtModelo.getText();
			String marca = txtMarca.getText();
			String matricula = txtMatricula.getText();

			if (modelo.isBlank() || marca.isBlank() || matricula.isBlank()) {
				throw new Exception("Error al introducir los datos");
			}

			Connection conn = Conexion.getConnection();
			System.out.println("Base de datos Conectada");

			// Crea una declaración
			Statement statement = conn.createStatement();

			statement.executeUpdate("INSERT INTO `vehiculo` (`propietario`, `marca`, `modelo`, `matricula`) VALUES ('"
					+ mainController.documentacion + "', '" + marca + "', '" + modelo + "', '" + matricula + "')");

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
		updateVehiculos();
	}

	@FXML
	void eliminarVehiculo(ActionEvent event) {
		try {
			Connection conn = Conexion.getConnection();
			PreparedStatement pst = conn.prepareStatement("DELETE FROM VEHICULO WHERE MATRICULA=?");
			pst.setString(1, tablaGestionVehiculo.getSelectionModel().getSelectedItem().getMatricula());
			pst.execute();
			conn.close();
			updateVehiculos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateVehiculos();
	}

	@FXML
	void initialize() {
		updateVehiculos();
		assert tablaGestionVehiculo != null
				: "fx:id=\"tablaGestionVehiculo\" was not injected: check your FXML file 'verVehiculos.fxml'.";
		assert mostrarMatricula != null
				: "fx:id=\"mostrarMatricula\" was not injected: check your FXML file 'verVehiculos.fxml'.";
		assert mostrarMarca != null
				: "fx:id=\"mostrarMarca\" was not injected: check your FXML file 'verVehiculos.fxml'.";
		assert mostrarModelo != null
				: "fx:id=\"mostrarModelo\" was not injected: check your FXML file 'verVehiculos.fxml'.";
		assert btEliminarVehiculo != null
				: "fx:id=\"btEliminarVehiculo\" was not injected: check your FXML file 'verVehiculos.fxml'.";
		assert btAnadirVehiculo != null
				: "fx:id=\"btAnadirVehiculo\" was not injected: check your FXML file 'verVehiculos.fxml'.";
		assert txtModelo != null : "fx:id=\"txtModelo\" was not injected: check your FXML file 'verVehiculos.fxml'.";
		assert txtMarca != null : "fx:id=\"txtMarca\" was not injected: check your FXML file 'verVehiculos.fxml'.";
		assert txtMatricula != null
				: "fx:id=\"txtMatricula\" was not injected: check your FXML file 'verVehiculos.fxml'.";

	}

	public void updateVehiculos() {
		mostrarMarca.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("marca"));
		mostrarMatricula.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("matricula"));
		mostrarModelo.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("modelo"));

		try {
			listaVehiculos = Conexion.getVehiculos(mainController.documentacion);
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error al conectarse a la base datos");
			alert.showAndWait();
		}
		tablaGestionVehiculo.setItems(listaVehiculos);
	}
}
