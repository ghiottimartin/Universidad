package data;

import java.sql.*;

import entidades.*;
import utils.ApplicationException;

public class DataAlumno {
	public DataAlumno()
	{
		
	}
	
	public int consultarMax() {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int idMax = 0;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select max(id_alumno) from alumnos");
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				idMax =  ((Number) rs.getObject(1)).intValue();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
		
		return idMax + 1;
		
	}
	
	public void add(Alumno a) throws ApplicationException{
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into alumnos(id_alumno,nombre,legajo,edad,fecha_nacimiento)"+
					" values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
						
			stmt.setInt(1, a.getIDAlumno());
			stmt.setString(2, a.getNombre());
			stmt.setInt(3, a.getLegajo());
			stmt.setInt(4, a.getEdad());
			stmt.setDate(5, new java.sql.Date(a.getFechaNacimiento().getTime()));
			stmt.execute();
			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.getGeneratedKeys();
			if(rs!=null && rs.next()){
				a.setIDAlumno(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al crear Alumno",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	
	public Alumno getById(Alumno alumnoABuscar) throws ApplicationException{
		Alumno alumnoEncontrado=null;
		
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_alumno,nombre,legajo,edad,fecha_nacimiento from alumnos where id_alumno=?");
			stmt.setInt(1, alumnoABuscar.getIDAlumno());
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				alumnoEncontrado=new Alumno();
				alumnoEncontrado.setIDAlumno(rs.getInt("id_alumno"));
				alumnoEncontrado.setNombre(rs.getString("nombre"));
				alumnoEncontrado.setLegajo(rs.getInt("legajo"));
				//Casteo de java.sql.Date a java.util.Date
				alumnoEncontrado.setFechaNacimiento(new java.util.Date((rs.getDate("fecha_nacimiento")).getTime()));
				//Utilizo el método que calcula la edad a partir de la fecha de nacimiento
				alumnoEncontrado.setEdad();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException("Error en el sql al buscar el alumno",e);
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Alumno no encontrado");
		}
		finally {
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
		
		return alumnoEncontrado;
	}	
	
	public void update(Alumno a) throws ApplicationException{
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"update alumnos set id_alumno=?,nombre=?,legajo=?,edad=?,fecha_nacimiento=?"+
					" where id_alumno=?");
			
			stmt.setInt(1, a.getIDAlumno());
			stmt.setString(2, a.getNombre());
			stmt.setInt(3, a.getLegajo());
			stmt.setInt(4, a.getEdad());
			//Casteo de java.util.Date a java.sql.Date
			stmt.setDate(5, new java.sql.Date(a.getFechaNacimiento().getTime()));
			stmt.setInt(6, a.getIDAlumno());
			stmt.execute();
			
			
		} catch (SQLException e) {
			throw new ApplicationException("Error en el sql al modificar el alumno",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
	
	public Alumno getByName(String nombre) throws ApplicationException{
		Alumno a=null;
		
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_alumno,nombre,legajo,edad,fecha_nacimiento from alumnos where nombre=?");
			stmt.setString(1, nombre);
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				a=new Alumno();
				a.setIDAlumno(rs.getInt("id_alumno"));
				a.setNombre(rs.getString("nombre"));
				a.setLegajo(rs.getInt("legajo"));
				//Convierto Date to Localdate, ya que en la BD es Date y en Alumno es LocalDate
				a.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				//Utilizo el método que calcula la edad a partir de la fecha de nacimiento
				a.setEdad();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException("Error en el sql al buscar el alumno",e);
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Alumno no encontrado");
		}
		finally {
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
		
		return a;
	}

	public void delete(Alumno a) throws ApplicationException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"delete from alumnos where id_alumno=?");
			stmt.setInt(1, a.getIDAlumno());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al borrar Alumno");
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Alumno inexistente");
			
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

	public boolean coincideNombre(Alumno a) throws ApplicationException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean coincide = false;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select nombre from alumnos where nombre like ?");
			stmt.setString(1, a.getNombre());
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				if( ((String) rs.getObject(1)).matches(a.getNombre()) ) 
				{
					coincide =  true;
				}
				else { coincide = false; }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al buscar Alumno");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Alumno no encontrado");
		}
		finally {
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
		
		return coincide;		
	}
	
	

}
