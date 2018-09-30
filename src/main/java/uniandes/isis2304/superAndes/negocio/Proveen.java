package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar la relación PROVEEN del negocio SuperAndes
 * Cada objeto de esta clase representa el hecho que un producto es ofrecido porun proveedor
 * y posee un precio unitario y precio por unidad de medida acordados para su venta.
 * Se modela mediante los identificadores del proveedor y del producto
 * Debe existir un proveedor con el identificador dado
 * Debe existir un producto con el identificador dado 
 * 
 * @author Geovanny Andres Gonzalez
 */

public class Proveen implements VOOfrecen{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/** El precio unitario de un producto ofrecido por un proveedor*/
	
	private int precioUnitario;	
	
	/** El precio por unidad de medida de un producto ofrecido por un proveedor*/
	
	private int precioUnidadMedida;
	
	/** Identificador del proveedor que ofrece el producto */
	
	private int nitProveedor;
	
	/** Codigo hexadecimal, identificador unico del producto */
	
	private String codigoProducto;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	
	public Proveen() {
		precioUnidadMedida = 0;
		precioUnitario = 0;
		nitProveedor = 0;
		codigoProducto = "";
	}
	
	/**
	 * Instantiates a new ofrecen.
	 *
	 * @param pPrecioUnitario the precio unitario
	 * @param pPrecioUniMedida the precio uni medida
	 * @param pProveedor the proveedor
	 * @param pProducto the producto
	 */
	
	public Proveen(int pPrecioUnitario, int pPrecioUniMedida, int pProveedor, String pProducto) {
		precioUnidadMedida = pPrecioUniMedida;
		precioUnitario = pPrecioUnitario;		
		nitProveedor = pProveedor;
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
	 * Gets the precio unidad medida.
	 *
	 * @return the precio unidad medida
	 */
	public int getPrecioUnidadMedida() {
		return precioUnidadMedida;
	}

	/**
	 * Sets the precio unidad medida.
	 *
	 * @param precioUnidadMedida the new precio unidad medida
	 */
	public void setPrecioUnidadMedida(int precioUnidadMedida) {
		this.precioUnidadMedida = precioUnidadMedida;
	}

	/**
	 * Gets the id supermercado.
	 *
	 * @return the id supermercado
	 */
	public long getNitProveedor() {
		return nitProveedor;
	}

	/**
	 * Sets the id supermercado.
	 *
	 * @param idSupermercado the new id supermercado
	 */
	public void setNitProveedor(int idSupermercado) {
		this.nitProveedor = idSupermercado;
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
		return "Proveen [nitProveedor =" + nitProveedor + ", codigoProducto =" + codigoProducto +
				", precioUnitario =" + precioUnitario + ", precioUnidadMedida =" + precioUnidadMedida + "]";
	}
}
