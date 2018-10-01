package uniandes.isis2304.superAndes.negocio;

import java.util.List;

/**
 * Interfaz para los métodos de PRODUCTOS_SUCURSAL.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Geovanny Andres Gonzalez
 */

public interface VOProductosSucursal {
	
	/**
	 * Gets the cantidad reorden.
	 *
	 * @return the cantidad reorden
	 */
	
	public int getCantidadReorden();

	/**
	 * Sets the cantidad reorden.
	 *
	 * @param cantidadReorden the new cantidad reorden
	 */
	
	public void setCantidadReorden(int cantidadReorden);

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
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	
	public Producto getProducto();

	/**
	 * Sets the producto.
	 *
	 * @param producto the new producto
	 */
	
	public void setProducto(Producto producto);
	
	/**
	 * @return the promociones
	 */
	public List<Promocion> getPromociones();

	/**
	 * @param promociones the promociones to set
	 */
	public void setPromociones(List<Promocion> promociones);

	@Override
	public String toString();
	
	/**
	 * Devuelve la informacion completa del producto de la sucursal y sus promociones
	 * @return Informacion con promociones.
	 */
	
	public String toStringCompleto();
}
