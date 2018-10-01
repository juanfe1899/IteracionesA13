package uniandes.isis2304.superAndes.negocio;

import java.util.List;

public interface VOSucursal {

	/**
	 * Gets the ciudad.
	 *
	 * @return the ciudad
	 */
	
	public String getCiudad();

	/**
	 * Sets the ciudad.
	 *
	 * @param ciudad the new ciudad
	 */
	
	public void setCiudad(String ciudad);

	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	
	public String getDireccion();

	/**
	 * Sets the direccion.
	 *
	 * @param direccion the new direccion
	 */
	
	public void setDireccion(String direccion);

	/**
	 * Gets the id sucursal.
	 *
	 * @return the id sucursal
	 */
	
	public long getIdSucursal();

	/**
	 * Sets the id sucursal.
	 *
	 * @param idSucursal the new id sucursal
	 */
	
	public void setIdSucursal(long idSucursal);

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
	 * @return the ventas
	 */
	
	public List<Factura> getVentas();

	/**
	 * @param ventas the ventas to set
	 */
	
	public void setVentas(List<Factura> ventas);

	
	@Override
	public String toString();	
}
