package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar la relación VENTA_PRODUCTO del negocio SuperAndes
 * Cada objeto de esta clase representa el hecho que un producto es facturado para ser vendido.
 * Se modela mediante los identificadores de la factura y del producto
 * Debe existir una factura con el identificador dado
 * Debe existir un producto con el identificador dado 
 * 
 * @author Geovanny Andres Gonzalez
 */

public class VentaProducto implements VOVentaProducto{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/		
	
	/** Numero de unidades a comprar del producto */
	
	private int cantidadProducto;
	
	/** Identificador de la factura */
	
	private long idFactura;
	
	/** Identificador del producto de surcursal a ser vendido */
	
	private long idProductoSucursal;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	
	public VentaProducto() {
		cantidadProducto = 0;
		idProductoSucursal = 0;
		idFactura = 0;		
	}
	
		
	public VentaProducto(int pFactura, int pCantidad, long pIdProductoSucursal) {
		cantidadProducto = pCantidad;
		idProductoSucursal = pIdProductoSucursal;		
		idFactura = pFactura;		
	}	
		
	/**
	 * @return the cantidadProducto
	 */
	public int getCantidadProducto() {
		return cantidadProducto;
	}


	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
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
	 * @return the idProductoSucursal
	 */
	public long getIdProductoSucursal() {
		return idProductoSucursal;
	}


	/**
	 * @param idProductoSucursal the idProductoSucursal to set
	 */
	public void setIdProductoSucursal(long idProductoSucursal) {
		this.idProductoSucursal = idProductoSucursal;
	}


	@Override
	public String toString() {
		return "Venta [idFactura =" + idFactura + ", idProducto =" + idProductoSucursal +
				", cantidad =" + cantidadProducto + "]";
	}
}
