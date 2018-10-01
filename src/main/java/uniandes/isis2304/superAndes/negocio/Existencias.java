package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar la relación EXISTENCIAS del negocio SuperAndes
 * Cada objeto de esta clase representa el hecho que un producto de sucursal es asociado a un espacio de acomodacion
 * Se modela mediante los identificadores del producto en sucursal y del espacio de acomodacion
 * Debe existir un espacio de acomodacion con el identificador dado
 * Debe existir un producto de sucursal con el identificador dado 
 * 
 * @author Geovanny Andres Gonzalez
 */

public class Existencias implements VOExistencias{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
			
	/** Numero de unidades a comprar del producto */
	
	private int cantidadProducto;
	
	/** Identificador del espacio */
	
	private long idEspacio;
	
	/** Identificador unico del producto de sucursal */
	
	private long idProductoSucursal;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	
	public Existencias() {
		cantidadProducto = 0;
		idEspacio = 0;
		idProductoSucursal = 0;
	}
	
		
	/**
	 * Instantiates a new existencias.
	 *
	 * @param pCantidad the cantidad
	 * @param pEspacio the espacio
	 * @param pProducto the producto
	 */
	
	public Existencias(int pCantidad, long pEspacio, long pProducto) {
		cantidadProducto = pCantidad;
		idEspacio = pEspacio;
		idProductoSucursal = pProducto;
	}		
	
	/**
	 * Devuelve la cantidad de productos en existencia en un espacio
	 */
	
	public int getCantidadProducto() {
		return cantidadProducto;
	}


	/** 
	 * Actualiza la cantidad de productos en existencia en un espacio
	 */
	
	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}


	/**
	 * Gets the id espacio.
	 *
	 * @return the id espacio
	 */
	
	public long getIdEspacio() {
		return idEspacio;
	}


	/**
	 * Sets the id espacio.
	 *
	 * @param idEspacio the new id espacio
	 */
	
	public void setIdEspacio(long idEspacio) {
		this.idEspacio = idEspacio;
	}


	/**
	 * Gets the id producto sucursal.
	 *
	 * @return the id producto sucursal
	 */
	
	public long getIdProductoSucursal() {
		return idProductoSucursal;
	}


	/**
	 * Sets the id producto sucursal.
	 *
	 * @param idProductoSucursal the new id producto sucursal
	 */
	
	public void setIdProductoSucursal(long idProductoSucursal) {
		this.idProductoSucursal = idProductoSucursal;
	}


	@Override
	public String toString() {
		return "Existencias [idProductoSucursal =" + idProductoSucursal + ", idEspacio =" + idEspacio +
				", cantidad =" + cantidadProducto + "]";
	}
}
