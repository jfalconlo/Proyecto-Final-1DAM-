package modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Propietario {

	private final StringProperty documentacion;
	private final StringProperty nombre;
	private final StringProperty tipo;

	public Propietario() {
		 documentacion = new SimpleStringProperty();
	       nombre = new  SimpleStringProperty();
	       tipo = new SimpleStringProperty();
	}

	public Propietario(String Documentacion, String Nombre, String Tipo) {
		this.documentacion = new SimpleStringProperty(Documentacion);
		this.nombre = new SimpleStringProperty(Nombre);
		this.tipo = new SimpleStringProperty(Tipo);
	}

	public String getDocumentacion() {
		return documentacion.get();
	}

	public void setDocumentacion(String documentacion) {
		this.documentacion.set(documentacion);
	}

	public StringProperty documentacionProperty() {
		return documentacion;
	}

	// -------------------------------------------------------

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}

	public StringProperty nombreProperty() {
		return nombre;
	}
	// ---------------------------------------------------------

	public String getTipo() {
		return tipo.get();
	}

	public void setTipo(String tipo) {
		this.tipo.set(tipo);
	}

	public StringProperty tipoProperty() {
		return tipo;
	}

}
