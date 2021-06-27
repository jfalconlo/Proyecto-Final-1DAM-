package modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehiculo {

	private final StringProperty propietario;
	private final StringProperty marca;
	private final StringProperty modelo;
	private final StringProperty matricula;
	
	public Vehiculo() {
		 propietario = new SimpleStringProperty();
	       marca = new  SimpleStringProperty();
	       modelo = new SimpleStringProperty();
	       matricula = new SimpleStringProperty();
	}

	public Vehiculo(String Propietario, String Marca, String Modelo, String Matricula) {
		this.propietario = new SimpleStringProperty(Propietario);
		this.marca = new SimpleStringProperty(Marca);
		this.modelo = new SimpleStringProperty(Modelo);
		this.matricula = new SimpleStringProperty(Matricula);
	}

	public String getPropietario() {
		return propietario.get();
	}

	public void setPropietario(String propietario) {
		this.propietario.set(propietario);
	}

	public StringProperty propietarioProperty() {
		return propietario;
	}

	// -------------------------------------------------------

	public String getMarca() {
		return marca.get();
	}

	public void setMarca(String marca) {
		this.marca.set(marca);
	}

	public StringProperty marcaProperty() {
		return marca;
	}
	// ---------------------------------------------------------

	public String getModelo() {
		return modelo.get();
	}

	public void setModelo(String modelo) {
		this.modelo.set(modelo);
	}

	public StringProperty modeloProperty() {
		return modelo;
	}
	// ---------------------------------------------------------

	public String getMatricula() {
		return matricula.get();
	}

	public void setMatricula(String Matricula) {
		this.matricula.set(Matricula);
	}

	public StringProperty matriculaProperty() {
		return matricula;
	}
}
