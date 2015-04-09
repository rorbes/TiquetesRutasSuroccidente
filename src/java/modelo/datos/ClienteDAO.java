package modelo.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.mundo.*;

/**
 * Clase encargada de almacenar la información de los clientes en la la base de datos
 * @author juandiego
 */
public class ClienteDAO {
	
	
	/**
	 * Atributo encargado de enlazar con la clase FachadaDB
	 */
	private FachadaDB fachada;
	
	
	/**
	 * Metodo constructor de la clase ClienteDAO
         * <post:> Inicializar los atributos
	 */
	public ClienteDAO() {
		// TODO Auto-generated constructor stub
            fachada = new FachadaDB();
	}

	
	/**
	 * Metodo encargado de seleccionar un cliente de la base de datos dado su identificación
	 * @return  Debe ser de tipo Cliente
         * <pre:> Tener inicializado el enlace a la clase Fachada DB <br>
         * <post:> Realizar seleccion de un cliente de la base de datos <br>
         * @throws java.lang.ClassNotFoundException Excepción causada porque no se ha realizado una correcta conexión a la base de datos.
	 */
	public ArrayList<Cliente> seleccionar() throws ClassNotFoundException {
            ArrayList<Cliente> clientes = new ArrayList<>();
            String seleccionar = "select * from cliente order by cliente.nombre asc;";
            FachadaDB conexion;         
                
            try {
                conexion = (FachadaDB) fachada.conectarDB();
                if(conexion!=null)
                {
                    PreparedStatement ps;
                    try {
                        
                        ps = conexion.conectarDB().prepareStatement(seleccionar);
                        ResultSet resultado= ps.executeQuery();
                        while(resultado.next())
                        {
                            String nApellidosC = resultado.getString("apellidos");
                            int nIdentificacionC = resultado.getInt("identificacion");
                            String nNombreC = resultado.getString("nombre");

                            clientes.add(new Cliente(nIdentificacionC, nNombreC, nApellidosC));
                        }
                        fachada.desconectarDB((Connection) conexion);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }              
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return clientes;
	}
	
	
	/**
	 * Metodo encargado de actualizar la información del cliente en la base de datos
         * @param nCliente != null Objeto de la clase Cliente
         * <pre:> Tener inicializado el enlace a la clase FachadaDB. <br>
         * <post:> Se ha actualizado el cliente en la base de datos. <br>
         * @throws java.lang.ClassNotFoundException Excepción causada porque no se ha realizado una correcta conexión a la base de datos.
	 */
	public void actualizar(Cliente nCliente) throws ClassNotFoundException {
            String actualizar= "update cliente "
                                + "set cliente.nombre = " + nCliente.getNombres() + ", cliente.apellidos = " + nCliente.getApellidos()
                                + " where cliente.identificacion = " + nCliente.getIdentificacion() + ";";
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
	 * Metodo encargado de agregar un cliente a la base de datos
         * @param nCliente != null Objeto de la clase Cliente
         * <pre:> Tener inicializado el enlace a la clase FachadaDB. <br>
         * <post:> Se ha agregado un nuevo cliente en la base de datos. <br>
	 */
	public void agregar(Cliente nCliente) {
            Connection conexion;
            try {
                conexion = fachada.conectarDB();
                String agregar= "insert into cliente (cliente.identificacion, cliente.nombre, cliente.apellidos) "
                            + "values ( " + nCliente.getIdentificacion() + ", " + nCliente.getNombres() + ", " + nCliente.getApellidos() + " );" ;
                if(conexion!=null)
                {
                        Statement instruccion;
                    try {
                        instruccion = (Statement)conexion.createStatement();
                        instruccion.execute(agregar);
                        conexion.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            	
	}
	
	
	/**
	 * Metodo encargado de eliminar un cliente de la base de datos
         * @param nCliente
         * <pre:> Tener inicializado el enlace a la clase FachadaDB. <br>
         * <post:> Se ha eliminado el cliente de la base de datos. <br>   
	 */
	public void eliminar(Cliente nCliente) {
            Connection conexion;
            try {
                conexion = fachada.conectarDB();
                String eliminar= "delete from cliente "
                            + "where cliente.identifiacacion = " + nCliente.getIdentificacion() + ";";
            if(conexion!=null)
            {
                    Statement instruccion;
                try {
                    instruccion = (Statement)conexion.createStatement();
                    instruccion.execute(eliminar);
                    conexion.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
	}
}