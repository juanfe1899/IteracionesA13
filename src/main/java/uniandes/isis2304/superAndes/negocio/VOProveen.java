package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos de PROVEEN.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Geovanny Andres Gonzalez
 */

public interface VOProveen {

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
	 * Gets the precio unidad medida.
	 *
	 * @return the precio unidad medida
	 */
	public int getPrecioUnidadMedida();

	/**
	 * Sets the precio unidad medida.
	 *
	 * @param precioUnidadMedida the new precio unidad medida
	 */
	public void setPrecioUnidadMedida(int precioUnidadMedida);

	/**
	 * Gets the id supermercado.
	 *
	 * @return the id supermercado
	 */
	public long getNitProveedor();

	/**
	 * Sets the id supermercado.
	 *
	 * @param idSupermercado the new id supermercado
	 */
	public void setNitProveedor(int idSupermercado);

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
	 * Devuelve en formato de texto la informacion de un producto suministrado proveedor
	 * @return Informacion de los productos suministrados por un proveedor.
	 */
	
	@Override
	public String toString();
}
