package entidades;

public class Curso {
	private int IDCurso,cupoMaximo;
	private String asignatura,docente;
	public int getIDCurso() {
		return IDCurso;
	}
	public void setIDCurso(int iDCurso) {
		IDCurso = iDCurso;
	}
	public int getCupoMaximo() {
		return cupoMaximo;
	}
	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	
}
