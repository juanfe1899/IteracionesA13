package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Interfaz para los métodos get de ORDEN_PRODUCTO
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz o
 * desde otras capas
 *  
 * @author Geovanny Andres Gonzalez
 */

public interface VOOrdenProducto {

	/**
	 * Gets the calificacion producto.
	 *
	 * @return the calificacion producto
	 */
	
	public int getCalificacionOrden();

	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	
	public String getEstado();	

	/**
	 * Gets the fecha esperada.
	 *
	 * @return the fecha esperada
	 */
	
	public Timestamp getFechaEsperada();	

	/**
	 * Gets the fecha entrega.
	 *
	 * @return the fecha entrega
	 */
	
	public Timestamp getFechaEntrega();
	
	/**
	 * Gets the id orden.
	 *
	 * @return the id orden
	 */	
	
	public long getIdOrden();	
	
	/**
	 * Devuelve la informacion de una orden de un producto
	 * @return Informacion de una orden de producto.
	 */
	
	@Override
	public String toString();
	
	/**
	 * Define la igualdad dos Tipos de bebida
	 * @param tb - El tipo de bebida a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	
	@Override
	public boolean equals (Object tb); 
}
