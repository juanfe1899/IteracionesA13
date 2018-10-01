package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;
import java.util.List;

public interface VOVentaProducto {

	/**
	 * @return the cantidadProducto
	 */
	public int getCantidadProducto();

	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setCantidadProducto(int cantidadProducto);


	/**
	 * @return the idFactura
	 */
	public long getIdFactura();


	/**
	 * @param idFactura the idFactura to set
	 */
	
	public void setIdFactura(long idFactura);

	/**
	 * @return the idProductoSucursal
	 */
	public long getIdProductoSucursal();


	/**
	 * @param idProductoSucursal the idProductoSucursal to set
	 */
	
	public void setIdProductoSucursal(long idProductoSucursal);


	@Override
	public String toString();
}
