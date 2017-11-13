package data;

import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.core.appender.db.jdbc.FactoryMethodConnectionSource;

import entidades.Alumno;
import entidades.Curso;
import entidades.Inscripcion;
import utils.ApplicationException;

public class DataInscripcion  {
	
	public DataInscripcion() {
	
	}
	
	public void add(Inscripcion ins) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
			"insert into inscripciones(id_alumno,id_curso,fecha_inscripcion,estado) "+
			"values(?,?,?,?)");
			
			stmt.setInt(1,ins.getIDAlumno());
			stmt.setInt(2, ins.getIDCurso());
			stmt.setDate(3, new java.sql.Date(ins.getFechaInscripcion().getTime()));
			stmt.setInt(4, ins.getEstado());
			stmt.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al crear la inscripcion",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void update(Inscripcion ins) throws ApplicationException {
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"update inscripciones set id_alumno=?,id_curso=?,fecha_inscripcion=?,estado=?"+
			" where id_alumno=? and id_curso =?");
			stmt.setInt(1, ins.getIDAlumno());
			stmt.setInt(2, ins.getIDCurso());
			stmt.setDate(3, new java.sql.Date(ins.getFechaInscripcion().getTime()));
			stmt.setInt(4, ins.getEstado());
			stmt.setInt(5, ins.getIDAlumno());
			stmt.setInt(6, ins.getIDCurso());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al modificar la inscripcion",e);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void delete(Inscripcion ins) throws ApplicationException {
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"delete from inscripciones where id_alumno=? and id_curso=?");
			stmt.setInt(1, ins.getIDAlumno());
			stmt.setInt(2, ins.getIDCurso());
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al borrar la inscripcion",e);
		} catch(ApplicationException e) {
			throw new ApplicationException("No existe la inscripcion");
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Inscripcion> buscarMateriasAlumno(Alumno a) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from inscripciones where id_alumno=?");
			stmt.setInt(1, a.getIDAlumno());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Inscripcion ins = new Inscripcion();
				ins.setIDAlumno(rs.getInt("id_alumno"));
				ins.setIDCurso(rs.getInt("id_curso"));
				ins.setFechaInscripcion(rs.getDate("fecha_inscripcion"));
				ins.setEstado(rs.getInt("estado"));
				inscripciones.add(ins);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al buscar las inscripciones",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("No hay inscripciones");
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return inscripciones;
		
	}

	public ArrayList<Curso> getMateriasRegulares(Alumno alumnoABuscar) throws ApplicationException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Curso> nombreMateriasRegulares = new ArrayList<Curso>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select c.asignatura\r\n" + 
					"from inscripciones i\r\n" + 
					"	inner join cursos c\r\n" + 
					"	on i.id_curso = c.id_curso\r\n" + 
					"where i.id_alumno=? and i.estado=2;");
			stmt.setInt(1, alumnoABuscar.getIDAlumno());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Curso c = new Curso();
				c.setAsignatura(rs.getString("c.asignatura"));
				nombreMateriasRegulares.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al buscar las materias regulares",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("No hay materias regulares");
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nombreMateriasRegulares;			
	}

	public ArrayList<Curso> getMateriasNoRegulares(Alumno alumnoABuscar) throws ApplicationException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Curso> nombreMateriasNoRegulares = new ArrayList<Curso>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select c.asignatura\r\n" + 
					"from inscripciones i\r\n" + 
					"	inner join cursos c\r\n" + 
					"	on i.id_curso = c.id_curso\r\n" + 
					"where i.id_alumno=? and i.estado=3;");
			stmt.setInt(1, alumnoABuscar.getIDAlumno());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Curso c = new Curso();
				c.setAsignatura(rs.getString("c.asignatura"));
				nombreMateriasNoRegulares.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al buscar las materias regulares",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("No hay materias regulares");
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nombreMateriasNoRegulares;			
	}
	
	public ArrayList<Curso> getMateriasInscripto(Alumno alumnoABuscar) throws ApplicationException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Curso> nombreMateriasInscripto = new ArrayList<Curso>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select c.asignatura\r\n" + 
					"from inscripciones i\r\n" + 
					"	inner join cursos c\r\n" + 
					"	on i.id_curso = c.id_curso\r\n" + 
					"where i.id_alumno=? and i.estado=1;");
			stmt.setInt(1, alumnoABuscar.getIDAlumno());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Curso c = new Curso();
				c.setAsignatura(rs.getString("c.asignatura"));
				nombreMateriasInscripto.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al buscar las materias regulares",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("No hay materias regulares");
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nombreMateriasInscripto;			
	}

	public ArrayList<Alumno> getInscriptos(String nombreAsignatura) throws ApplicationException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select a.id_alumno, a.nombre, a.legajo, a.edad, a.fecha_nacimiento \r\n" + 
					"from inscripciones i\r\n" + 
					"	inner join alumnos a\r\n" + 
					"    on i.id_alumno = a.id_alumno\r\n" + 
					"    inner join cursos c\r\n" + 
					"    on i.id_curso = c.id_curso\r\n" + 
					"    where c.asignatura=? and i.estado=1;");
			stmt.setString(1, nombreAsignatura);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Alumno a = new Alumno();
				a.setIDAlumno(rs.getInt("id_alumno"));
				a.setNombre(rs.getString("nombre"));
				a.setLegajo(rs.getInt("legajo"));
				a.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				a.setEdad();
				alumnos.add(a);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al buscar los inscriptos",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("No hay inscriptos",e);
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alumnos;
	}

	public ArrayList<Alumno> getNoRegulares(String nombreAsignatura) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select a.id_alumno, a.nombre, a.fecha_nacimiento, a.legajo "
					+ "from inscripciones i "
						+ "inner join alumnos a "
						+ "on i.id_alumno = a.id_alumno "
						+ "inner join cursos c "
						+ "on i.id_curso = c.id_curso "
					+ "where i.estado=3 and c.asignatura=?");
			stmt.setString(1, nombreAsignatura);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Alumno a = new Alumno();
				a.setIDAlumno(rs.getInt("id_alumno"));
				a.setNombre(rs.getString("nombre"));
				a.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				a.setLegajo(rs.getInt("legajo"));
				a.setEdad();
				alumnos.add(a);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al buscar los recursantes",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("No hay recursantes",e);
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alumnos;
	}

	public ArrayList<Alumno> getRecursantes(ArrayList<Alumno> alumnosNoRegulares) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
				for (int i = 0; i < alumnosNoRegulares.size(); i++) {			
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						  "select a.nombre, a.legajo, a.fecha_nacimiento, a.edad "
						+ "from inscripciones i "
							+ "inner join alumnos a "
							+ "on i.id_alumno = a.id_alumno "
							+ "inner join cursos c "
							+ "on i.id_curso = c.id_curso "
						+ "where a.id_alumno=? and i.estado=1");
				stmt.setInt(1, alumnosNoRegulares.get(i).getIDAlumno());
				if(!stmt.execute()) {
					alumnosNoRegulares.remove(alumnosNoRegulares.get(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumnosNoRegulares;
	}
}
