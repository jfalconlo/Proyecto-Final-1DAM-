package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Conexion;
import modelo.Propietario;

public class mainController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btanadirUsuario;

	@FXML
	private Button btEliminarUsuario;

	@FXML
	private Button btEditarUsuario;

	@FXML
	private Button btEditarVehiculo;

	@FXML
	private Button btActualizarTabla;

	@FXML
	private TableView<Propietario> verDatos;

	@FXML
	private TableColumn<Propietario, String> tcID;

	@FXML
	private TableColumn<Propietario, String> tcNombre;

	@FXML
	private TableColumn<Propietario, String> tcEU;

	public ObservableList<Propietario> listaPropietarios;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	public static String documentacion;

	@FXML
	void ActualizarTabla(ActionEvent event) {
		updateTable();
	}

	@FXML
	void añadirUsuario(ActionEvent event) {
		FXMLLoader añadirusuario = new FXMLLoader(getClass().getResource("/vista/añadirUsuario.fxml"));

		try {
			Parent root = añadirusuario.load();

			Scene scene = new Scene(root);
			Stage añadirUsuario = new Stage();
			añadirUsuario.setScene(scene);
			añadirUsuario.setResizable(false);
			añadirUsuario.initModality(Modality.APPLICATION_MODAL);
			añadirUsuario.getIcons().add(new Image("imagenes/icono.png"));
			añadirUsuario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void editarUsuario(ActionEvent event) {

		try {
			documentacion = verDatos.getSelectionModel().getSelectedItem().getDocumentacion();

			FXMLLoader editUsuario = new FXMLLoader(getClass().getResource("/vista/editarUsuario.fxml"));

			try {
				Parent root = editUsuario.load();

				Scene scene = new Scene(root);
				Stage editarUsuario = new Stage();
				editarUsuario.setScene(scene);
				editarUsuario.initModality(Modality.APPLICATION_MODAL);
				editarUsuario.setResizable(false);
				editarUsuario.getIcons().add(new Image("imagenes/icono.png"));
				editarUsuario.show();

			} catch (IOException e) {
			}
		} catch (Exception e) {
			alerta();
		}
	}

	@FXML
	void eliminarUsuario(ActionEvent event) throws Exception {
		try {
			conn = Conexion.getConnection();
			pst = conn.prepareStatement("DELETE FROM PROPIETARIO WHERE DOCUMENTACION=?");
			pst.setString(1, verDatos.getSelectionModel().getSelectedItem().getDocumentacion());
			pst.execute();
			conn.close();
			updateTable();
		} catch (Exception e) {
			alerta();
		}
	}

	@FXML
	void iniciarEditorVehiculos(ActionEvent event) {
		try {
			documentacion = verDatos.getSelectionModel().getSelectedItem().getDocumentacion();
			FXMLLoader cargarVerVehiculos = new FXMLLoader(getClass().getResource("/vista/verVehiculos.fxml"));

			try {
				Parent root = cargarVerVehiculos.load();

				Scene scene = new Scene(root);
				Stage verVehiculos = new Stage();
				verVehiculos.initModality(Modality.APPLICATION_MODAL);
				verVehiculos.setScene(scene);
				verVehiculos.getIcons().add(new Image("imagenes/icono.png"));
				verVehiculos.setResizable(false);
				verVehiculos.show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			alerta();
		}

	}

	public void updateTable() {
		tcID.setCellValueFactory(cellData -> cellData.getValue().documentacionProperty());
		tcNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		tcEU.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
		try {
			listaPropietarios = Conexion.getPropietarios();
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error al conectarse a la base datos");
			alert.showAndWait();
		}
		verDatos.setItems(listaPropietarios);
		verDatos.refresh();

	}

	public void alerta() {
		System.out.println("No hay ningun usuario seleccionado");
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText("No hay ningun usuario seleccionado");
		alert.showAndWait();
	}

	@FXML
	public void initialize() {
		updateTable();

	}
}
