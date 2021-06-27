package modelo;

public class Mantenimiento {
	String id;
	String matricula;
	String tipo;
	String descripcion;
	String fechaRevision;
	String fechaNextRevision;

	public Mantenimiento() {

	}

	public Mantenimiento(String id, String matricula, String tipo, String descripcion, String fechaRevision,
			String fechaNextRevision) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.fechaRevision = fechaRevision;
		this.fechaNextRevision = fechaNextRevision;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(String fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public String getFechaNextRevision() {
		return fechaNextRevision;
	}

	public void setFechaNextRevision(String fechaNextRevision) {
		this.fechaNextRevision = fechaNextRevision;
	}

}
