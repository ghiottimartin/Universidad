package data;

import java.sql.*;
import java.util.ArrayList;

import entidades.*;
import utils.ApplicationException;

public class DataCurso {
	public DataCurso()
	{
		
	}
	
	public int consultarMax() {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int idMax = 0;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select max(id_curso) from cursos");
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

	public void add(Curso c) throws ApplicationException{
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into cursos(id_curso,asignatura,cupo_maximo,docente)"+
					" values(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
						
			stmt.setInt(1, c.getIDCurso());
			stmt.setString(2, c.getAsignatura());
			stmt.setInt(3, c.getCupoMaximo());
			stmt.setString(4, c.getDocente());
			stmt.execute();
			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.getGeneratedKeys();
			if(rs!=null && rs.next()){
				c.setIDCurso(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al crear el Curso",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();;
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
	
	public Curso getById(Curso cursoABuscar) throws ApplicationException{
		Curso cursoEncontrado=null;
		
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_curso,asignatura,cupo_maximo,docente from cursos where id_curso=?");
			stmt.setInt(1, cursoABuscar.getIDCurso());
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				cursoEncontrado=new Curso();
				cursoEncontrado.setIDCurso(rs.getInt("id_curso"));
				cursoEncontrado.setAsignatura(rs.getString("asignatura"));
				cursoEncontrado.setCupoMaximo(rs.getInt("cupo_maximo"));
				cursoEncontrado.setDocente(rs.getString("docente"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException("Error en el sql al buscar el Curso",e);
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Curso no encontrado");
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
		
		return cursoEncontrado;
	}	

	public void update(Curso c) throws ApplicationException{
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"update cursos set id_curso=?,asignatura=?,cupo_maximo=?,docente=?"+
					" where id_curso=?");
			
			stmt.setInt(1, c.getIDCurso());
			stmt.setString(2, c.getAsignatura());
			stmt.setInt(3, c.getCupoMaximo());
			stmt.setString(4, c.getDocente());
			stmt.setInt(5, c.getIDCurso());
			stmt.execute();
			
			
		} catch (SQLException e) {
			throw new ApplicationException("Error en el sql al modificar el Curso",e);
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

	public Curso getByName(String asignatura) throws ApplicationException{
		Curso cursoEncontrado=null;
		
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_curso,asignatura,cupo_maximo,docente from cursos where asignatura=?");
			stmt.setString(1, asignatura);
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				cursoEncontrado=new Curso();
				cursoEncontrado.setIDCurso(rs.getInt("id_curso"));
				cursoEncontrado.setAsignatura(rs.getString("asignatura"));
				cursoEncontrado.setCupoMaximo(rs.getInt("cupo_maximo"));
				cursoEncontrado.setDocente(rs.getString("docente"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException("Error en el sql al buscar el Curso",e);
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Curso no encontrado");
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
		
		return cursoEncontrado;
	}
	
	public void delete(Curso cursoABorrar) throws ApplicationException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"delete from cursos where id_curso=?");
			stmt.setInt(1, cursoABorrar.getIDCurso());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al eliminar el Curso");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Curso inexistente");
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

	public boolean coincideNombre(Curso c) throws ApplicationException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean coincide = false;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select asignatura from cursos where asignatura like ?");
			stmt.setString(1, c.getAsignatura());
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				if( ((String) rs.getObject(1)).matches(c.getAsignatura()) ) 
				{
					coincide =  true;
				}
				else { coincide = false; }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al buscar Curso");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Curso inexistente");
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

	public ArrayList<Curso> getAll() throws ApplicationException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from cursos");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Curso c = new Curso();
				c.setIDCurso(rs.getInt(1));
				c.setAsignatura(rs.getString(2));
				c.setCupoMaximo(rs.getInt(3));
				c.setDocente(rs.getString(4));
				cursos.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al buscar cursos");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("No hay cursos");
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
		
		return cursos;
	}	

}
