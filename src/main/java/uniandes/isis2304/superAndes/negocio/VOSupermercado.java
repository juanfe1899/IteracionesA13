package uniandes.isis2304.superAndes.negocio;

import java.util.List;

public interface VOSupermercado {

	public int getNIT();

	/**
	 * Sets the nit.
	 *
	 * @param nIT the new nit
	 */
	
	public void setNIT(int nIT);

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	
	public String getNombre();

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	
	public void setNombre(String nombre);

	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 */
	
	public List<Object[]> getProductos();

	/**
	 * Sets the productos.
	 *
	 * @param productos the new productos
	 */
	
	public void setProductos(List<Object[]> productos);
	
	@Override
	public String toString();
	
	/**
	 * Devuelve la informacion del supermercado con todos sus productos
	 * @return El supermercado y la informacion de todos sus productos
	 */
	
	public String toStringCompleto();	
}
