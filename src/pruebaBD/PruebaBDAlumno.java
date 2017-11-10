package pruebaBD;

import javax.swing.JComboBox;

import data.DataAlumno;
import entidades.Alumno;
import utils.ApplicationException;

public class PruebaBDAlumno {
	
	static DataAlumno aluData = new DataAlumno();
	static Alumno a = new Alumno();
	public static void main(String[] args) throws ApplicationException {
		// TODO Auto-generated method stub
		
		/* PRUEBA CONSULTARMAX para saber el id del nuevo alumno
		System.out.println(aluData.consultarMax());
		*/
		
		/* PRUEBA ADD
		 * Aclaracion: Luego sera probado en la interfaz mediante los inputs
		 * a.setNombre("Juan Lucas Pajin");
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = null;
		try {
			d1 = df.parse("21/06/1995");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.setFechaNacimiento(d1);
		a.setEdad();
		a.setLegajo(00000);
		a.setIDAlumno(aluData.consultarMax());
		aluData.add(a);*/
		
		
		/* PRUEBA GETBYID
		a.setIDAlumno(2);
		a = aluData.getById(a);
		System.out.println(a.getNombre());*/
		
		
		/* PRUEBA UPDATE
		 * 
		a.setIDAlumno(2);
		a = aluData.getById(a);
		a.setLegajo(11111);
		aluData.update(a);*/
		
		
		/*PRUEBA GETBYNAME
		 * a.setNombre("Juan Perez");
		System.out.println(aluData.getByName(a.getNombre()).getEdad());*/
		
		/* PRUEBA DELETE
		 * a.setIDAlumno(11);
		   aluData.delete(a);*/
		
		/* PRUEBA GETMAXLEGAJO
		System.out.println(aluData.getMaxLegajo());
		*/
		
		/* PRUEBA GETALL
		for (int j = 0; j < aluData.getAll().size(); j++) {
			System.out.println(aluData.getAll().get(j).getNombre());
		}*/
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("Juan Perez");
		combo.setSelectedItem("Juan Perez");
		Alumno a = aluData.getByName(combo.getSelectedItem().toString());
		System.out.println(a.getEdad());
		
	}

}
