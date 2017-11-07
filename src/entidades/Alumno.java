package entidades;

import java.time.LocalDate;
import java.time.Period;


public class Alumno {
	private int IDAlumno, legajo, edad;
	private String nombre;
	private LocalDate fechaNacimiento;
	
	public int getIDAlumno() {
		return IDAlumno;
	}
	public void setIDAlumno(int iDAlumno) {
		IDAlumno = iDAlumno;
	}
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = calcularEdad();
	}
	private int calcularEdad() {
		//Convertir string a LocalDate
		//DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//LocalDate fechaNac = LocalDate.parse("15/08/1993", fmt);
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(this.fechaNacimiento, ahora);
		return periodo.getYears();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
