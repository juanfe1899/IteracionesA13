package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de PRODUCTO
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz o
 * desde otras capas
 *  
 * @author Geovanny Andres Gonzalez
 */

public interface VOProducto {
	
	/**
	 * Gets the codigo.
	 * @return the codigo
	 */
	public String getCodigo();

		
	/**
	 * Sets the codigo.
	 * @param id the new codigo
	 */
	
	public void setCodigo(String id);

	/**
	 * Gets the cantidad en presentacion.
	 *
	 * @return the cantidad en presentacion
	 */
	
	public int getCantidadPresentacion();
	

	/**
	 * Sets the cantidad en presentacion.
	 *
	 * @param cantidadEnPresentacion the new cantidad en presentacion
	 */
	
	public void setCantidadPresentacion(int cantidadEnPresentacion);

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	
	public String getMarca();

	/**
	 * Sets the marca.
	 *
	 * @param marca the new marca
	 */
	
	public void setMarca(String marca);

	/**
	 * Gets the presentacion.
	 *
	 * @return the presentacion
	 */
	
	public String getPresentacion();

	/**
	 * Sets the presentacion.
	 *
	 * @param presentacion the new presentacion
	 */
	
	public void setPresentacion(String presentacion);

	/**
	 * Gets the especificacion empacado.
	 *
	 * @return the especificacion empacado
	 */
	
	public int getEspecificacionEmpacado();

	/**
	 * Sets the especificacion empacado.
	 *
	 * @param especificacionEmpacado the new especificacion empacado
	 */
	
	public void setEspecificacionEmpacado(int especificacionEmpacado);

	/**
	 * Gets the unidad medida.
	 *
	 * @return the unidad medida
	 */
	
	public String getUnidadMedida();

	/**
	 * Sets the unidad medida.
	 *
	 * @param unidadMedida the new unidad medida
	 */
	
	public void setUnidadMedida(String unidadMedida);
	
	@Override
	public String toString();
	
	
}
