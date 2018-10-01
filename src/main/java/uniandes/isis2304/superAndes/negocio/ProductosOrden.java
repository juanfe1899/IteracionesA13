package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar la relación ORDEN_PRODUCTOS del negocio SuperAndes
 * Cada objeto de esta clase representa el hecho que un producto es asociado a una orden de compra
 * Se modela mediante los identificadores de la orden de pedido y del producto
 * Debe existir un pedido con el identificador dado
 * Debe existir un producto con el identificador dado 
 * 
 * @author Geovanny Andres Gonzalez
 */

public class ProductosOrden implements VOProductosOrden{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/** El precio unitario acordado para la compra de un producto */
	
	private int precioUnitario;	
	
	/** Numero de unidades a comprar del producto */
	
	private int cantidadProducto;
	
	/** Identificador de la orden de pedido */
	
	private long idPedido;
	
	/** Codigo hexadecimal, identificador unico del producto */
	
	private String codigoProducto;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	
	public ProductosOrden() {
		cantidadProducto = 0;
		precioUnitario = 0;
		idPedido = 0;
		codigoProducto = "";
	}
	
		
	public ProductosOrden(int pPrecioUnitario, int pCantidad, long pIdPedido, String pProducto) {
		cantidadProducto = pCantidad;
		precioUnitario = pPrecioUnitario;		
		idPedido = pIdPedido;
		codigoProducto = pProducto;
	}
	
	/**
	 * Gets the precio unitario.
	 *
	 * @return the precio unitario
	 */
	public int getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * Sets the precio unitario.
	 *
	 * @param precioUnitario the new precio unitario
	 */
	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

    /**
     * Gets the cantidad producto.
     *
     * @return the cantidad producto
     */
	
    public int getCantidadProducto() {
		return cantidadProducto;
	}


	/**
	 * Sets the cantidad producto.
	 *
	 * @param cantidadProducto the new cantidad producto
	 */
    
	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	/**
	 * Gets the id pedido.
	 *
	 * @return the id pedido
	 */
	
	public long getIdPedido() {
		return idPedido;
	}

	/**
	 * Sets the id pedido.
	 *
	 * @param idPedido the new id pedido
	 */
	
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigo producto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto the new codigo producto
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	
	@Override
	public String toString() {
		return "Productos Ordenados [idPedido =" + idPedido + ", codigoProducto =" + codigoProducto +
				", precioUnitario =" + precioUnitario + ", cantidad =" + cantidadProducto + "]";
	}
}
