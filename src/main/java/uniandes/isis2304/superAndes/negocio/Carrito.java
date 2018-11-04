package uniandes.isis2304.superAndes.negocio;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


public class Carrito implements VOCarrito{
	
	
	/** Identificador del carrito */
	
	private long idCarrito;
	
	/** Total de la compra */
	
	private int total;
	
	/** Cliente que usa el carrito */
	
	private long cliente;
	
	/** Sucursal del carrito */
	
	private long sucursal;
	
	/**
	 * Representa los productos y la cantidad de productos que estan en el carrito.
	 * Dupla[ProductoSucursal, VentaProducto]
	 */
	
	private List<Object[]> productosEnCarrito;
		
	
	/**
	 * Instantiates a new carrito.
	 */
	
	public Carrito() {
		idCarrito = 0;
		total = 0;
		cliente = 0;
		sucursal = 0;		
		productosEnCarrito = new LinkedList<>();
	}
	
	/**
	 * Instantiates a new carrito.
	 * @param pId the id
	 * @param pTotal the total
	 * @param pCliente the cliente
	 * @param pSucursal the sucursal
	 */
	
	public Carrito ( long pId, int pTotal, long pCliente, long pSucursal) {
		idCarrito = pId;
		total = pTotal;
		cliente = pCliente;
		sucursal = pSucursal;
		productosEnCarrito = new LinkedList<>();
	}


	/**
	 * @return the idCarrito
	 */
	public long getIdCarrito() {
		return idCarrito;
	}

	/**
	 * @param idCarrito the idCarrito to set
	 */
	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
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
	public long getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(long cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the sucursal
	 */
	public long getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(long sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the productosFacturados
	 */
	public List<Object[]> getProductosEnCarrito() {
		return productosEnCarrito;
	}

	/**
	 * @param productosFacturados the productosFacturados to set
	 */
	public void setProductosEnCarrito(List<Object[]> productosEnCarrito) {
		this.productosEnCarrito = productosEnCarrito;
	}
	
	@Override
	public String toString() {
		return "Factura [idFactura =" + idCarrito + ", total =" + total + "\n Cliente =" + cliente + "\n Sucursal =" + sucursal + "]";
	}
	
	/**
	 * Retorna la informacion propia de la factura y de los produtos comprados.
	 * @return Informacion de la factura junto con la descripcion de los productos comprados.
	 */
	
	public String toStringCompleto() {
		String info = toString().replace("]", "");
		info += "\n\n Productos en carrito \n";
		int conteo = 1;
		
		for(Object[] elemento : productosEnCarrito) {
			String producto = ((ProductoSucursal) elemento[0]).toString();
			String cantidad = ((VentaProducto) elemento[1]).toString();
			info += conteo++ + producto + cantidad;
		}
		
		info += "]";
		return info;
	}
}
