package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;
import java.util.List;

public interface VOCarrito {
	/**
	 * @return the idCarrito
	 */
	public long getIdCarrito();

	/**
	 * @param idCarrito the idCarrito to set
	 */
	public void setIdCarrito(long idCarrito);

	/**
	 * @return the total
	 */
	public int getTotal();

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total);

	/**
	 * @return the cliente
	 */
	public long getCliente();

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(long cliente);

	/**
	 * @return the sucursal
	 */
	public long getSucursal();

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(long sucursal);

	/**
	 * @return the productosEnCarrito
	 */
	public List<Object[]> getProductosEnCarrito();

	/**
	 * @param productosFacturados the productosEnCarrito to set
	 */
	public void setProductosEnCarrito(List<Object[]> productosEnCarrito);
	
	@Override
	public String toString();
	
	/**
	 * Retorna la informacion propia del carrito y de los produtos dentro de el.
	 * @return Informacion de la factura junto con la descripcion de los productos comprados.
	 */
	
	public String toStringCompleto();	
}
