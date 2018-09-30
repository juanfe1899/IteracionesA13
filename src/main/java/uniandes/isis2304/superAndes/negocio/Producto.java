package uniandes.isis2304.superAndes.negocio;

/**
 * Value Object para manejar la informacion de un producto entre las capas
 * Modela el concepto de PRODUCTO en el mundo de negocio de SuperAndes
 * @author Geovanny Andres Gonzalez
 */

public class Producto implements VOProducto{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	/**
	 * Identificador unico del producto.
	 */

	private String codigo;

	/**
	 * Cantidad de volumen en la presentacion
	 */

	private int cantidadPresentacion;

	/**
	 * Marca del producto
	 */

	private String marca;

	/**
	 * Presentacion del producto
	 */

	private String presentacion;

	/**
	 * Volumen total del producto.
	 */

	private int especificacionEmpacado;

	/**
	 * Unidad de medida del volumen del producto
	 */

	private String unidadMedida;
	
	/**
	 * Nombre del producto
	 */
	
	private String nombre;
	
	/**
	 * Categoria del producto
	 */
	
	private Categoria categoria;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	//=======================================================
	// Constructor
	//=======================================================
	
	public Producto(){
		codigo = "";
		cantidadPresentacion = 0;
		marca = "";
		presentacion = "";
		especificacionEmpacado = 0;
		unidadMedida = "";
		categoria = null;
	}

	/**
	 * Instantiates a new producto.
	 *
	 * @param pId the id
	 * @param pCantidadPresentacion the cantidad presentacion
	 * @param pMarca the marca
	 * @param pPresentacion the presentacion
	 * @param pEspecificacion the especificacion
	 * @param pUnidadMedida the unidad medida
	 */
	
	public Producto(String pCodigo, int pCantidadPresentacion, String pMarca, 
			String pPresentacion, int pEspecificacion, String pUnidadMedida, Categoria pCategoria)
	{
		codigo = pCodigo;
		cantidadPresentacion = pCantidadPresentacion;
		marca = pMarca;
		presentacion = pPresentacion;
		especificacionEmpacado = pEspecificacion; 
		unidadMedida = pUnidadMedida;
		categoria = pCategoria;
	}	
	
	//=======================================================
	// Getters and Setters
	//=======================================================

	/**
	 * Gets the id.
	 * @return the id
	 */
	
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the cantidad en presentacion.
	 *
	 * @return the cantidad en presentacion
	 */
	public int getCantidadPresentacion() {
		return cantidadPresentacion;
	}

	/**
	 * Sets the cantidad en presentacion.
	 *
	 * @param cantidadEnPresentacion the new cantidad en presentacion
	 */
	public void setCantidadPresentacion(int cantidadEnPresentacion) {
		this.cantidadPresentacion = cantidadEnPresentacion;
	}

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the presentacion.
	 *
	 * @return the presentacion
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * Sets the presentacion.
	 *
	 * @param presentacion the new presentacion
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * Gets the especificacion empacado.
	 *
	 * @return the especificacion empacado
	 */
	public int getEspecificacionEmpacado() {
		return especificacionEmpacado;
	}

	/**
	 * Sets the especificacion empacado.
	 *
	 * @param especificacionEmpacado the new especificacion empacado
	 */
	public void setEspecificacionEmpacado(int especificacionEmpacado) {
		this.especificacionEmpacado = especificacionEmpacado;
	}

	/**
	 * Gets the unidad medida.
	 *
	 * @return the unidad medida
	 */
	public String getUnidadMedida() {
		return unidadMedida;
	}

	/**
	 * Sets the unidad medida.
	 *
	 * @param unidadMedida the new unidad medida
	 */
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Sets the categoria.
	 *
	 * @param categoria the new categoria
	 */
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
	/** 
	 * @return Los valores de un producto.
	 */
	@Override
	public String toString()
	{
		return "Producto [codigo =" + codigo + ", nombre =" + nombre + ", marca =" + marca + 
				", presentacion =" + presentacion + ", unidad_medida =" + unidadMedida + ", cantidad_presentacion =" + cantidadPresentacion +
				", ";
	}
}
