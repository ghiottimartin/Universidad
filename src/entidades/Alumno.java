package entidades;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;


public class Alumno {
	private int IDAlumno, legajo, edad;
	private String nombre;
	private java.util.Date fechaNacimiento;
	
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
	public void setEdad() {
		//Convierto la propiedad FechaNacimiento de java.sql.Date a java.util.Date
		java.util.Date fechaNac =  new java.util.Date(this.getFechaNacimiento().getTime());
		//Con la conversion anterior me permite usar toInstant.atZone.... y calucular edad
		LocalDate fechaNacimiento = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, ahora);
		this.edad = periodo.getYears();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(java.util.Date date) {
		this.fechaNacimiento =  date;
	}

}
