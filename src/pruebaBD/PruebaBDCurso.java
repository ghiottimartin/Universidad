package pruebaBD;

import data.DataCurso;
import entidades.Curso;
import utils.ApplicationException;

public class PruebaBDCurso {
	static DataCurso cursoData = new DataCurso();
	static Curso c = new Curso();
	public static void main(String[] args) throws ApplicationException {
		// TODO Auto-generated method stub
		
		/* PRUEBA CONSULTAR MAX para saber cual es el id del proximo curso
		System.out.println(cursoData.consultarMax());*/
		
		/* PRUEBA ADD
		c.setIDCurso(cursoData.consultarMax());
		c.setAsignatura("Probabilidad y Estadistica");
		c.setCupoMaximo(23);
		c.setDocente("Graciela Carnevalli");
		cursoData.add(c);*/
		
		/* PRUEBA GETBYID
		c.setIDCurso(2);
		System.out.println(cursoData.getById(c).getAsignatura());
		*/
		
		/* PRUEBA UPDATE
		c.setIDCurso(5);
		c = cursoData.getById(c);
		c.setCupoMaximo(23);
		c.setDocente("Susana Leone");
		cursoData.update(c);
		*/
		
		/* PRUEBA GET BY NAME
		System.out.println(cursoData.getByName("Analisis de Sistemas").getDocente());
		*/
		
		/* PRUEBA DELETE
		c.setIDCurso(11);
		cursoData.delete(c);
		*/
		
		/* PRUEBA COINCIDE NOMBRE
		c.setAsignatura("Matematica Superior");
		System.out.println(cursoData.coincideNombre(c));
		*/
	}

}
