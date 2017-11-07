package entidades;


public class Inscripcion {
	private java.util.Date fechaInscripcion;
	private int estado,IDAlumno,IDCurso;
	public java.util.Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(java.util.Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getIDAlumno() {
		return IDAlumno;
	}
	public void setIDAlumno(int iDAlumno) {
		IDAlumno = iDAlumno;
	}
	public int getIDCurso() {
		return IDCurso;
	}
	public void setIDCurso(int iDCurso) {
		IDCurso = iDCurso;
	}

}
