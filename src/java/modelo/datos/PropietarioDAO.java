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
import modelo.mundo.Propietario;
import modelo.mundo.Vehiculo;

/**
 * Clase encargada de almacenar información de los propietarios de las vehículos en la base de datos
 */
public class PropietarioDAO {
	
	/**
	 * Atributo encargado de enlazar la clase FachadaDB
	 */
	private FachadaDB fachada;
	
	/**
	 * Metodo constructor de la clase PropietarioDAO
         * <post:> Inicializar los atributos
	 */
	public PropietarioDAO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Metodo encargado de instanciar un objeto de la clase Propietario
	 * @param nVehiculo != null Objeto de la clase Vehiculo
	 * @return  Debe ser un objeto de tipo Propietario
         * <pre:> Tener inicializado el enlace a la clase FachadaDB <br>
         * <post:> Realizar seleccion de un propietario de la base de datos <br>
	 */
	public ArrayList<Propietario> seleccionar(Marca nMarca, Linea nLinea, Vehiculo nVehiculo) {
		ArrayList<Propietario> propietarios = new ArrayList<>();
                String seleccionar = "select propietario.identificacion, propetario.nombres, propetario.apellidos, propetario.direccion, propetario.telefono"
                                    + " from propietario, marca, linea, vehiculo "
                                    + " where propietario.vehiculo_placa = " + nVehiculo.getPlaca()
                                    + " and vehiculo.nombre_linea = " + nLinea.getNombre()
                                    + " and linea.nombre_marca = " + nMarca.getNombre()
                                    + " order by propietario.nombres asc;";
                FachadaDB conexion;
            try {
                conexion = (FachadaDB) fachada.conectarDB();
                if(conexion!=null)
		{
			Statement instruccion;
                    try {
                        instruccion = (Statement)conexion.conectarDB();
                        ResultSet resultado=(ResultSet)instruccion.executeQuery(seleccionar);
			while(resultado.next())
			{
				int nIdentificacionP = resultado.getInt("propietario.identificacion");
				String nNombresP = resultado.getString("propietario.nombres");
				String nApellidosP = resultado.getString("propietario.apellidos");
                                String nDireccionP = resultado.getString("propietario.direccion");
                                int nTelefonoP = resultado.getInt("propietario.telefono");
				
				propietarios.add(new Propietario(nIdentificacionP, nNombresP, nApellidosP, nDireccionP, nTelefonoP));
			}
                        fachada.desconectarDB((Connection) conexion);
                    } catch (SQLException ex) {
                        Logger.getLogger(PropietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PropietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PropietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		
                return propietarios;  
	}
	
	/**
	 * Metodo encargado de actualizar la información de un propietario en la base de datos
	 * @param nVehiculo != null Objeto de la clase Vehiculo
	 * @param nPropietario != null Objeto de la clase Propietario
	 */
	public void actualizar(Vehiculo nVehiculo, Propietario nPropietario) {
		String actualizar = "update propietario "
                                    + "set propietario.nombre = " + nPropietario.getNombres() + ", propietario.apellidos = " + nPropietario.getApellidos()
                                    + ", propietario.direccion = " + nPropietario.getDireccion()
                                    + ", propietario.telefono = " + nPropietario.getTelefono() 
                                    + " where propietario.identificacion = " + nPropietario.getIdentificacion() 
                                    + " and propietario.vehiculo_placa = " + nVehiculo.getPlaca() + ";";
		Connection conexion;
            try {
                conexion = fachada.conectarDB();
                if(conexion!=null)
		{
			java.sql.Statement instruccion;
                    try {
                        instruccion = (java.sql.Statement) conexion.createStatement();
                        instruccion.executeUpdate(actualizar);
			fachada.desconectarDB(conexion);
                    } catch (SQLException ex) {
                        Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
	/**
	 * Metodo encargado de agregar un propietario a la base de datos
	 * @param nVehiculo != null Objeto de la clase Vehiculo
	 * @param nPropietario != null Objeto de la clase Propietario
	 */
	public void agregar(Vehiculo nVehiculo, Propietario nPropietario) {
		String agregar = "insert into propietario (propietario.vehiculo_placa, propietario.nombres, propietario.apellidos, propietario.identificacion, propietario.direccion, propietario.telefono) "
                                + "values (" + nVehiculo.getPlaca() + ", " + nPropietario.getNombres() + ", " + nPropietario.getApellidos() + ", " + nPropietario.getIdentificacion() + ", " + nPropietario.getDireccion() + ", " + nPropietario.getTelefono() + ");";
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
                        Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
	
	/**
	 * Metodo encargado de eliminar un propietario a la base de datos
	 * @param nVehiculo != null Objeto de la clase Vehiculo
	 * @param nPropietario != null Objeto de la clase Propietario
	 */
	public void eliminar(Vehiculo nVehiculo, Propietario nPropietario) {
		String eliminar= "delete from propietario "
                                + "where propietario.identificacion = " + nPropietario.getIdentificacion()
                                + " and propietario.vehiculo_placa = " + nVehiculo.getPlaca() + ";";
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
                        Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
}
