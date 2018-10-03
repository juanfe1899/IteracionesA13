package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

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
	 * Sets the calificacion producto.
	 *
	 * @param calificacionProducto the new calificacion producto
	 */
	
	public void setCalificacionOrden(int calificacionProducto);

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	
	public String getEstado();

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	
	public void setEstado(String estado);

	/**
	 * Gets the fecha esperada.
	 *
	 * @return the fecha esperada
	 */
	
	public Timestamp getFechaEsperada();

	/**
	 * Sets the fecha esperada.
	 *
	 * @param fechaEsperada the new fecha esperada
	 */
	
	public void setFechaEsperada(Timestamp fechaEsperada);

	/**
	 * Gets the fecha entrega.
	 *
	 * @return the fecha entrega
	 */
	
	public Timestamp getFechaEntrega();

	/**
	 * Sets the fecha entrega.
	 *
	 * @param fechaEntrega the new fecha entrega
	 */
	
	public void setFechaEntrega(Timestamp fechaEntrega);

	/**
	 * Gets the id orden.
	 *
	 * @return the id orden
	 */
	
	public long getIdOrden();

	/**
	 * Sets the id orden.
	 *
	 * @param idOrden the new id orden
	 */
	
	public void setIdOrden(long idOrden);
	
	/**
	 * Devuelve la informacion de una orden de un producto
	 * @return Informacion de una orden de producto.
	 */
	
	@Override
	public String toString();
	
	public String toStringCompleto();
}
