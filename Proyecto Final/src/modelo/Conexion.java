package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Conexion {

	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/proyectoprogramacion?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "");
		return connection;
	}

	public static ObservableList<Propietario> getPropietarios() throws SQLException {

		Connection conn = getConnection();
		ObservableList<Propietario> Propietarios = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM `propietario`");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Propietarios.add(new Propietario(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		conn.close();
		return Propietarios;
	}

	public static ObservableList<Vehiculo> getVehiculos(String documentacionPropietario) throws SQLException {
		Connection conn = getConnection();
		ObservableList<Vehiculo> Vehiculos = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM `vehiculo` WHERE `propietario` = '" + documentacionPropietario + "'");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vehiculos.add(new Vehiculo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		conn.close();
		return Vehiculos;
	}

	public static ObservableList<Mantenimiento> getMantenimientos(String matricula) throws SQLException {
		Connection conn = getConnection();
		ObservableList<Mantenimiento> Mantenimientos = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM `mantenimiento` WHERE `matricula` = '" + matricula + "'");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Mantenimientos.add(new Mantenimiento(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		conn.close();
		return Mantenimientos;
	}
}
