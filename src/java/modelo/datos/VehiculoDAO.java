package modelo.datos;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.mundo.*;


/**
 * Clase encargada de almacenar informacion de los vehiculos en la base de datos
 */
public class VehiculoDAO {
	
	
	/**
	 * Atributo encargado de enlazar la clase FachadaDB
	 */
	private FachadaDB fachada;
	
	
	/**
	 * Metodo constructor de la clase VehiculoDAO
         * <post:> Inicializar los atributos
	 */
	public VehiculoDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Lista almacenadora de objetos de tipo Vehiculo
	 * @param nMarca != null Objeto de la clase Marca
	 * @param nLinea != null Objeto de la clase Linea
	 * @return ArrayList<Vehiculo> Lista de vehículos.
         * <pre:> Tener inicializado el enlace a la clase FachadaDB <br>
         * <post:> Realizar seleccion de los vehículos de una marca y una linea dada de la base de datos <br>
         */
	public ArrayList<Vehiculo> seleccionar(Marca nMarca, Linea nLinea)  {
                ArrayList<Vehiculo> vehiculos;
                vehiculos = new ArrayList();
		String seleccionar="select * from vehiculo, linea "
                                + " where vehiculo.linea_nombre = " + nLinea.getNombre()
                                + " and linea.nombre_marca = " + nMarca.getNombre() + ";";
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
				int nModelo = resultado.getInt("vehiculo.modelo");
				String nPlaca = resultado.getString("vehiculo.placa");
                                int nNumPasajeros = resultado.getInt("vehiculo.numero_pasajeros");
                                BufferedImage nImagen = (BufferedImage) resultado.getBlob("vehiculo.fotografia");
                                Vehiculo vehiculo = new Vehiculo(nModelo, nPlaca, nNumPasajeros, nImagen);
                                vehiculos.add(vehiculo);

			}
			fachada.desconectarDB(conexion);
                    } catch (SQLException ex) {
                        Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		return vehiculos;
	}
	
	
	/**
	 * Metodo encargado de actualizar la información de un vehículo en la base de datos
	 * @param nMarca != null Objeto de la clase Marca
	 * @param nLinea != null Objeto de la clase Linea
	 * @param nVehiculo != null Objeto de la clase Vehiculo
         */
	public void actualizar(Marca nMarca, Linea nLinea, Vehiculo nVehiculo)  {
		String actualizar= "update vehiculo "
                                    + "set vehiculo.modelo = " + nVehiculo.getModelo() + ", vehiculo.numero_pasajeros = " + nVehiculo.getNumeroPasajeros() + ", vehiculo.fotografia = " + nVehiculo.getFotografia()
                                    + " where vehiculo.linea_nombre = " + nLinea.getNombre() 
                                    + " and vehiculo.placa = " + nVehiculo.getPlaca() + ";";
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
                        Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
	
	
	/**
	 * Metodo encargado de agregar un vehículo en la base de datos
	 * @param nLinea != null Objeto de la clase Linea
	 * @param nVehiculo != null Objeto de la clase Vehiculo
         */
	public void agregar(Marca nMarca, Linea nLinea, Vehiculo nVehiculo) {
		String agregar= "insert into vehiculo (vehiculo.placa, vehiculo.modelo, vehiculo.numero_pasajeros, vehiculo.fotografia, vehiculo.linea_nombre) "
                                + "values ( " + nVehiculo.getPlaca() + ", " + nVehiculo.getModelo() + ", " + nVehiculo.getNumeroPasajeros() + ", " + nVehiculo.getFotografia() + ", " + nLinea.getNombre() + ");";
		Connection conexion;
            try {
                conexion = fachada.conectarDB();
                if(conexion!=null)
		{
			java.sql.Statement instruccion;
                    try {
                        instruccion = (java.sql.Statement) conexion.createStatement();
                        instruccion.executeUpdate(agregar);
			fachada.desconectarDB(conexion);
                    } catch (SQLException ex) {
                        Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
	
	
	/**
	 * Metodo encargado de eliminar un vehículo en la base de datos
	 * @param nMarca != null Objeto de la clase Marca
	 * @param nLinea != null Objeto de la clase Linea
	 * @param nVehiculo != null Objeto de la clase Vehiculo
         */
	public void eliminar(Marca nMarca, Linea nLinea, Vehiculo nVehiculo) {
		String eliminar= "delete from vehiculo "
                                + "where vehiculo.placa = " + nVehiculo.getPlaca()
                                + " and vehiculo.linea_nombre = " + nLinea.getNombre() +";";
		Connection conexion;
            try {
                conexion = fachada.conectarDB();
                if(conexion!=null)
		{
			java.sql.Statement instruccion;
                    try {
                        instruccion = (java.sql.Statement) conexion.createStatement();
                        instruccion.executeUpdate(eliminar);
			fachada.desconectarDB(conexion);
                    } catch (SQLException ex) {
                        Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
}
