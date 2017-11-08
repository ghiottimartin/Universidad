package logic;

import java.util.ArrayList;

import data.DataCurso;
import entidades.Curso;
import utils.ApplicationException;

public class CtrlCurso {
	ArrayList<Curso> cursos;
	private DataCurso dataCursos;
	
	public CtrlCurso() {
		cursos = new ArrayList<Curso>();
		dataCursos = new DataCurso();
	}
	
	public ArrayList<Curso> getAll() throws ApplicationException {
		return dataCursos.getAll();
	}

}
