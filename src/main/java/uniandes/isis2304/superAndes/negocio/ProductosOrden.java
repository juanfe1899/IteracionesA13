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
	
	private int precio_cu_acordado;	
	
	/** Numero de unidades a comprar del producto */
	
	private int cantidad;
	
	/** Identificador de la orden de pedido */
	
	private long id_pedido;
	
	/** Codigo hexadecimal, identificador unico del producto */
	
	private String id_producto;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	
	public ProductosOrden() {
		cantidad = 0;
		precio_cu_acordado = 0;
		id_pedido = 0;
		id_producto = "";
	}
	
		
	public ProductosOrden(long pIdPedido, String pProducto, int pPrecioUnitario, int pCantidad) {
		cantidad = pCantidad;
		precio_cu_acordado = pPrecioUnitario;		
		id_pedido = pIdPedido;
		id_producto = pProducto;
	}
	
	/**
	 * Gets the precio unitario.
	 *
	 * @return the precio unitario
	 */
	public int getPrecioUnitario() {
		return precio_cu_acordado;
	}

	/**
	 * Sets the precio unitario.
	 *
	 * @param precioUnitario the new precio unitario
	 */
	public void setPrecioUnitario(int precioUnitario) {
		this.precio_cu_acordado = precioUnitario;
	}

    /**
     * Gets the cantidad producto.
     *
     * @return the cantidad producto
     */
	
    public int getCantidadProducto() {
		return cantidad;
	}


	/**
	 * Sets the cantidad producto.
	 *
	 * @param cantidadProducto the new cantidad producto
	 */
    
	public void setCantidadProducto(int cantidadProducto) {
		this.cantidad = cantidadProducto;
	}

	/**
	 * Gets the id pedido.
	 *
	 * @return the id pedido
	 */
	
	public long getIdPedido() {
		return id_pedido;
	}

	/**
	 * Sets the id pedido.
	 *
	 * @param idPedido the new id pedido
	 */
	
	public void setIdPedido(long idPedido) {
		this.id_pedido = idPedido;
	}

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigo producto
	 */
	public String getCodigoProducto() {
		return id_producto;
	}

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto the new codigo producto
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.id_producto = codigoProducto;
	}
	
	@Override
	public String toString() {
		return "Productos Ordenados [idPedido =" + id_pedido + ", codigoProducto =" + id_producto +
				", precioUnitario =" + precio_cu_acordado + ", cantidad =" + cantidad + "]";
	}
}
