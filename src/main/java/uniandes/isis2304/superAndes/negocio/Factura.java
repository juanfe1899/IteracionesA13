package uniandes.isis2304.superAndes.negocio;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class Factura implements VOFactura{
	
	/** Fecha de compra */
	
	private Timestamp fechaFacturacion;
	
	/** Identificador de la factura */
	
	private long idFactura;
	
	/** Total de la compra */
	
	private int total;
	
	/** Cliente que realizo la compra */
	
	private Cliente cliente;
	
	/** Sucursal en la que se realizo la compra */
	
	private Sucursal sucursalCompra;
	
	/**
	 * Representa los productos facturados y la cantidad de productos a comprar.
	 * Dupla[ProductoSucursal, VentaProducto]
	 */
	
	private List<Object[]> productosFacturados;
	
	/**
	 * Formato de las fechas
	 */
	
	private static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd '-' HH:mm");	
	
	/**
	 * Instantiates a new factura.
	 */
	
	public Factura() {
		fechaFacturacion = new Timestamp(0);
		idFactura = 0;
		total = 0;
		cliente = null;
		sucursalCompra = null;		
		productosFacturados = new LinkedList<>();
	}
	
	/**
	 * Instantiates a new factura.
	 *
	 * @param pFecha the fecha
	 * @param pId the id
	 * @param pTotal the total
	 * @param pCliente the cliente
	 * @param pSucursal the sucursal
	 */
	
	public Factura (Timestamp pFecha, long pId, int pTotal, Cliente pCliente, Sucursal pSucursal) {
		fechaFacturacion = pFecha;
		idFactura = pId;
		total = pTotal;
		cliente = pCliente;
		sucursalCompra = pSucursal;
		productosFacturados = new LinkedList<>();
	}

	/**
	 * @return the fechaFacturacion
	 */
	public Timestamp getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(Timestamp fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the idFactura
	 */
	public long getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the sucursalCompra
	 */
	public Sucursal getSucursalCompra() {
		return sucursalCompra;
	}

	/**
	 * @param sucursalCompra the sucursalCompra to set
	 */
	public void setSucursalCompra(Sucursal sucursalCompra) {
		this.sucursalCompra = sucursalCompra;
	}

	/**
	 * @return the productosFacturados
	 */
	public List<Object[]> getProductosFacturados() {
		return productosFacturados;
	}

	/**
	 * @param productosFacturados the productosFacturados to set
	 */
	public void setProductosFacturados(List<Object[]> productosFacturados) {
		this.productosFacturados = productosFacturados;
	}
	
	@Override
	public String toString() {
		return "Factura [idFactura =" + idFactura + ", fecha =" + formatoFecha.format(fechaFacturacion) + 
				", total =" + total + "\n Cliente =" + cliente.toString() + "\n Sucursal =" + sucursalCompra.toString() + "]";
	}
	
	/**
	 * Retorna la informacion propia de la factura y de los produtos comprados.
	 * @return Informacion de la factura junto con la descripcion de los productos comprados.
	 */
	
	public String toStringCompleto() {
		String info = toString().replace("]", "");
		info += "\n\n Productos comprados \n";
		int conteo = 1;
		
		for(Object[] elemento : productosFacturados) {
			String producto = ((ProductoSucursal) elemento[0]).toString();
			String cantidad = ((VentaProducto) elemento[1]).toString();
			info += conteo++ + producto + cantidad;
		}
		
		info += "]";
		return info;
	}
}
