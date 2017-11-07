package entidades;

import java.time.LocalDate;

public class Inscripcion {
	private LocalDate fechaInscripcion;
	private int estado,IDAlumno,IDCurso;
	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(LocalDate fechaInscripcion) {
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
