package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;
import java.util.List;

public interface VOFactura {

	/**
	 * @return the fechaFacturacion
	 */
	public Timestamp getFechaFacturacion();

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(Timestamp fechaFacturacion);

	/**
	 * @return the idFactura
	 */
	public long getIdFactura();

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(long idFactura);

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
	public Cliente getCliente();

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente);

	/**
	 * @return the sucursalCompra
	 */
	public Sucursal getSucursalCompra();

	/**
	 * @param sucursalCompra the sucursalCompra to set
	 */
	public void setSucursalCompra(Sucursal sucursalCompra);

	/**
	 * @return the productosFacturados
	 */
	public List<Object[]> getProductosFacturados();

	/**
	 * @param productosFacturados the productosFacturados to set
	 */
	public void setProductosFacturados(List<Object[]> productosFacturados);
	
	@Override
	public String toString();
	
	/**
	 * Retorna la informacion propia de la factura y de los produtos comprados.
	 * @return Informacion de la factura junto con la descripcion de los productos comprados.
	 */
	
	public String toStringCompleto();	
}
