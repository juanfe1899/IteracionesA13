package uniandes.isis2304.superAndes.negocio;

public interface VOExistencias {

	/**
	 * Devuelve la cantidad de productos en existencia en un espacio
	 */
	
	public int getCantidadProducto();


	/** 
	 * Actualiza la cantidad de productos en existencia en un espacio
	 */
	
	public void setCantidadProducto(int cantidadProducto);


	/**
	 * Gets the id espacio.
	 *
	 * @return the id espacio
	 */
	
	public long getIdEspacio();


	/**
	 * Sets the id espacio.
	 *
	 * @param idEspacio the new id espacio
	 */
	
	public void setIdEspacio(long idEspacio);


	/**
	 * Gets the id producto sucursal.
	 *
	 * @return the id producto sucursal
	 */
	
	public long getIdProductoSucursal();


	/**
	 * Sets the id producto sucursal.
	 *
	 * @param idProductoSucursal the new id producto sucursal
	 */
	
	public void setIdProductoSucursal(long idProductoSucursal);


	@Override
	public String toString();	
}
