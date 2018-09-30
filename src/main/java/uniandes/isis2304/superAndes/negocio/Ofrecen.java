package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar la relación OFRECEN del negocio SuperAndes
 * Cada objeto de esta clase representa el hecho que un producto es ofrecido en una cadena
 * de supermercados y posee un precio unitario y precio por unidad de medida
 * Se modela mediante los identificadores del supermercado y del producto
 * Debe existir un supermercado con el identificador dado
 * Debe existir un producto con el identificador dado 
 * 
 * @author Geovanny Andres Gonzalez
 */

public class Ofrecen implements VOOfrecen{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/** El precio unitario de un producto */
	
	private int precioUnitario;	
	
	/** El precio por unidad de medida de un producto */
	
	private int precioUnidadMedida;
	
	/** Identificador del supermercado que ofrece el producto */
	
	private long idSupermercado;
	
	/** Codigo hexadecimal, identificador unico del producto */
	
	private String codigoProducto;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	
	public Ofrecen() {
		precioUnidadMedida = 0;
		precioUnitario = 0;
		idSupermercado = 0;
		codigoProducto = "";
	}
	
	/**
	 * Instantiates a new ofrecen.
	 *
	 * @param pPrecioUnitario the precio unitario
	 * @param pPrecioUniMedida the precio uni medida
	 * @param pSupermercado the supermercado
	 * @param pProducto the producto
	 */
	
	public Ofrecen(int pPrecioUnitario, int pPrecioUniMedida, long pSupermercado, String pProducto) {
		precioUnidadMedida = pPrecioUniMedida;
		precioUnitario = pPrecioUnitario;		
		idSupermercado = pSupermercado;
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
	public long getIdSupermercado() {
		return idSupermercado;
	}

	/**
	 * Sets the id supermercado.
	 *
	 * @param idSupermercado the new id supermercado
	 */
	public void setIdSupermercado(long idSupermercado) {
		this.idSupermercado = idSupermercado;
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
		return "Ofrecen [idSupermercado =" + idSupermercado + ", codigoProducto =" + codigoProducto +
				", precioUnitario =" + precioUnitario + ", precioUnidadMedida =" + precioUnidadMedida + "]";
	}
}
