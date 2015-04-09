package modelo.datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.mundo.Linea;
import modelo.mundo.Marca;


/**
 * Clase encargada de almacenar informacion de las lineas de las marcas en la base de datos
 */
public class LineaDAO {
	
	
	/**
	 * Atributo encargado de enlazar la clase FachadaDB
	 */
	private FachadaDB fachada;
	
	
	/**
	 * Metodo constructor de la clase LineaDAO
	 */
	public LineaDAO(){
		
	}
	
	
	/**
	 * Lista almacenadora de objetos de tipo Marca
	 * @param nMarca != null Objeto de la clase Marca
	 * @return
         * @throws java.lang.ClassNotFoundException
         * @throws java.sql.SQLException
	 */
	public ArrayList<Linea> seleccionar(Marca nMarca)throws ClassNotFoundException, SQLException{
            ArrayList<Linea> Lineas;
                Lineas = new ArrayList();
		String seleccionar="select linea.nombre from linea where linea.marca_nombre = " + nMarca.getNombre() + ";";
		Connection conexion;
            try {
                conexion = fachada.conectarDB();
                if(conexion!=null)
		{
			Statement instruccion;
                    try {
                        instruccion = (Statement) conexion.createStatement();
                        ResultSet resultado = null;
			resultado = (ResultSet) instruccion.executeQuery(seleccionar);
			while (resultado.next()) 
			{
				
                            String nNombre = resultado.getString("linea.nombre");
                            Linea linea = new Linea(nNombre);
                            Lineas.add(linea);

			}
			fachada.desconectarDB(conexion);
                    } catch (SQLException ex) {
                        Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		return Lineas;
	
	}
	
	
	/**
	 * Metodo encargado de actualizar la información de una línea en la base de datos
	 * @param nMarca != null Objeto de la clase Marca
	 * @param nLinea != null Objeto de la clase Linea
         * @param vNombre != null && != "" Nombre de la linea a cambiar
         * @throws java.lang.ClassNotFoundException
         * @throws java.sql.SQLException
	 */
	public void actualizar(Marca nMarca, Linea nLinea, String vNombre)throws ClassNotFoundException, SQLException{
            String actualizar= "update linea set linea.nombre = " + nLinea.getNombre()
                                + " where linea.nombre = " + vNombre  
                                + " and linea.marca_nombre = " + nMarca.getNombre() + ";";
		Connection conexion;
            try {
                conexion = fachada.conectarDB();
                if(conexion!=null)
		{
			Statement instruccion;
                    try {
                        instruccion = (Statement) conexion.createStatement();
                        instruccion.executeUpdate(actualizar);
			fachada.desconectarDB(conexion);
                    } catch (SQLException ex) {
                        Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	
	}
	
	
	/**
	 * Metodo encargado de agregar una línea en la base de datos
	 * @param nMarca != null Objeto de la clase Marca
	 * @param nLinea != null Objeto de la clase Linea
         * @throws java.lang.ClassNotFoundException
         * @throws java.sql.SQLException
	 */
	public void agregar(Marca nMarca, Linea nLinea) throws ClassNotFoundException, SQLException{
            String agregar= "insert into linea (nombre, marca_nombre) "
                                + "values ( " + nLinea.getNombre() + " ,"+ nMarca.getNombre()+");";
		Connection conexion;
            try {
                conexion = fachada.conectarDB();
                if(conexion!=null)
		{
			Statement instruccion;
                    try {
                        instruccion = (Statement) conexion.createStatement();
                        instruccion.executeUpdate(agregar);
			fachada.desconectarDB(conexion);
                    } catch (SQLException ex) {
                        Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
	
	
	/**
	 * Metodo encargado de eliminar una línea en la base de datos
	 * @param nMarca != null Objeto de la clase Marca
	 * @param nLinea != null Objeto de la clase Linea
         * @throws java.lang.ClassNotFoundException
         * @throws java.sql.SQLException
	 */
	public void eliminar(Marca nMarca, Linea nLinea) throws ClassNotFoundException, SQLException{
		String eliminar= "delete from linea "
                                + "where linea.mara_nombre = " + nMarca.getNombre()
                                + " and linea.nombre = " + nLinea.getNombre() + ";";
		Connection conexion;
            try {
                conexion = fachada.conectarDB();
                if(conexion!=null)
		{
			Statement instruccion;
                    try {
                        instruccion = (Statement) conexion.createStatement();
                        instruccion.executeUpdate(eliminar);
			fachada.desconectarDB(conexion);
                    } catch (SQLException ex) {
                        Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LineaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}