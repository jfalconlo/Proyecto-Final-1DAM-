package controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import modelo.Conexion;

public class editarUsuarioController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField introducirIdentificador;

	@FXML
	private TextField introducirNombre;

	@FXML
	private RadioButton btUsuario;

	@FXML
	private RadioButton btEmpresa;

	@FXML
	private Button btguardaDatosIntroducidos;

	@FXML
	void guardaDatosIntroducidos(ActionEvent event) {
		try {
			String nombre = this.introducirNombre.getText();
			String tipoPropietario = null;

			if (btUsuario.isSelected() == true) {
				tipoPropietario = "Usuario";
			} else {
				tipoPropietario = "Empresa";
			}

			if (nombre.isBlank()) {
				throw new Exception("Usuario No introducido");
			}

			Connection conn = Conexion.getConnection();
			System.out.println("Base de datos Conectada");

			// Crea una declaración
			Statement statement = conn.createStatement();

			statement.executeUpdate("UPDATE `propietario` SET `NOMBRE` = '" + nombre + "', `TIPO` = '" + tipoPropietario
					+ "' WHERE `propietario`.`DOCUMENTACION` = '" + mainController.documentacion + "';");

			System.out.println("El Usuario se ha editado Correctamente");
			conn.close();
		} catch (Exception e) {
			System.out.println("El Usuario no se ha podido editar");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("ERROR A LA HORA DE EDITAR EL USUARIO");
			alert.showAndWait();
		}
	}

	@FXML
	void initialize() {

		introducirIdentificador.setText(mainController.documentacion);

		ToggleGroup tg = new ToggleGroup();
		this.btEmpresa.setToggleGroup(tg);
		this.btUsuario.setToggleGroup(tg);

		assert introducirIdentificador != null
				: "fx:id=\"introducirIdentificador\" was not injected: check your FXML file 'añadirUsuario.fxml'.";
		assert introducirNombre != null
				: "fx:id=\"introducirNombre\" was not injected: check your FXML file 'añadirUsuario.fxml'.";
		assert btUsuario != null : "fx:id=\"btUsuario\" was not injected: check your FXML file 'añadirUsuario.fxml'.";
		assert btEmpresa != null : "fx:id=\"btEmpresa\" was not injected: check your FXML file 'añadirUsuario.fxml'.";
		assert btguardaDatosIntroducidos != null
				: "fx:id=\"btguardaDatosIntroducidos\" was not injected: check your FXML file 'añadirUsuario.fxml'.";

	}
}
