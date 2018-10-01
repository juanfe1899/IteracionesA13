package uniandes.isis2304.superAndes.negocio;

public interface VOProductosOrden {

	/**
	 * Gets the precio unitario.
	 *
	 * @return the precio unitario
	 */
	public int getPrecioUnitario();

	/**
	 * Sets the precio unitario.
	 *
	 * @param precioUnitario the new precio unitario
	 */
	public void setPrecioUnitario(int precioUnitario);

    /**
     * Gets the cantidad producto.
     *
     * @return the cantidad producto
     */
	
    public int getCantidadProducto();


	/**
	 * Sets the cantidad producto.
	 *
	 * @param cantidadProducto the new cantidad producto
	 */
    
	public void setCantidadProducto(int cantidadProducto);

	/**
	 * Gets the id pedido.
	 *
	 * @return the id pedido
	 */
	
	public long getIdPedido();

	/**
	 * Sets the id pedido.
	 *
	 * @param idPedido the new id pedido
	 */
	
	public void setIdPedido(long idPedido);

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigo producto
	 */
	public String getCodigoProducto();

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto the new codigo producto
	 */
	public void setCodigoProducto(String codigoProducto);
	
	/**
	 * Devuelve la informacion relacionada con los productos ordenados para un reabastecimiento.
	 * @return Productos encargados para reabastecimiento
	 */
	
	@Override
	public String toString();	
}
