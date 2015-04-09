package modelo.datos;

import java.util.ArrayList;
import modelo.mundo.*;


/**
 * Clase encargada de almacenar informacion de los tiquetes en la base de datos
 */
public class VehiculoDAO {
	
	
	/**
	 * Atributo encargado de enlazar la clase FachadaDB
	 */
	private FachadaDB fachada;
	
	
	/**
	 * Metodo constructor de la clase VehiculoDAO
	 */
	public VehiculoDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Lista almacenadora de objetos de tipo Vehiculo
	 * @param nMarca
	 * @param nLinea
	 * @return
	 */
	public ArrayList<Vehiculo> seleccionar(Marca nMarca, Linea nLinea){
	}
	
	
	/**
	 * Metodo encargado de actualizar la información de un vehículo en la base de datos
	 * @param nMarca
	 * @param nLinea
	 * @param nVehiculo
	 */
	public void actualizar(Marca nMarca, Linea nLinea, Vehiculo nVehiculo){
		
	}
	
	
	/**
	 * Metodo encargado de agregar un vehículo en la base de datos
	 * @param nMarca
	 * @param nLinea
	 * @param nVehiculo
	 */
	public void agregar(Marca nMarca, Linea nLinea, Vehiculo nVehiculo){
		
	}
	
	
	/**
	 * Metodo encargado de eliminar un vehículo en la base de datos
	 * @param nMarca
	 * @param nLinea
	 * @param nVehiculo
	 */
	public void eliminar(Marca nMarca, Linea nLinea, Vehiculo nVehiculo){
		
	}
}