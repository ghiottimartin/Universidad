package data;

import java.sql.*;


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
			throw new ApplicationException("No existe el curso");
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
}
