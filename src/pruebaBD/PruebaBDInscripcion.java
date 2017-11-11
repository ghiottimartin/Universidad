package pruebaBD;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;

import data.DataAlumno;
import data.DataInscripcion;
import entidades.Alumno;
import entidades.Curso;
import entidades.Inscripcion;
import utils.ApplicationException;

public class PruebaBDInscripcion {
	
	static DataInscripcion inscripcionData = new DataInscripcion();
	static Inscripcion i = new Inscripcion();
	public static void main(String[] args) throws ApplicationException {
		// TODO Auto-generated method stub
		
		/* PRUEBA ADD
		i.setIDCurso(9);
		i.setIDAlumno(9);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = null;
		try {
			d1 = df.parse("21/06/1995");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i.setFechaInscripcion(d1);
		i.setEstado(3);
		inscripcionData.add(i);
		*/
		
		/* PRUEBA UPDATE
		 * Aclaracion: Luego sera probado en la interfaz mediante los inputs 
		 
		i.setIDCurso(6);
		i.setIDAlumno(5);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = null;
		try {
			d1 = df.parse("21/06/2010");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i.setFechaInscripcion(d1);
		i.setEstado(2);
		inscripcionData.update(i);
		*/
		
		/* PRUEBA DELETE
		i.setIDAlumno(9);
		i.setIDCurso(9);
		inscripcionData.delete(i);
		*/
		

		/* PRUEBA BUSCARMATERIASALUMNO
		 * Alumno a = new Alumno();
		ArrayList<Inscripcion> ins;
		a.setNombre("Juan Perez");
		ins = inscripcionData.buscarMateriasAlumno(a);
		System.out.println(ins.get(0).getEstado());*/
		
		/* PRUEBA GETMATERIASREGULARES
		Alumno alumnoABuscar = new Alumno();
		ArrayList<Curso> materiasRegulares = new ArrayList<Curso>();
		alumnoABuscar.setIDAlumno(1);
		materiasRegulares = inscripcionData.getMateriasRegulares(alumnoABuscar);
		for (int i = 0; i < materiasRegulares.size(); i++) {
			System.out.println(materiasRegulares.get(i).getAsignatura());			
		}*/
		
		

	}

}
