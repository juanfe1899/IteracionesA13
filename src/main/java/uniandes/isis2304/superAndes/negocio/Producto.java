package uniandes.isis2304.superAndes.negocio;

/**
 * Value Object para manejar la informacion de un producto entre las capas
 * @author Geovanny Andres Gonzalez
 */

public class Producto {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	/**
	 * Identificador unico del producto.
	 */

	private long id;

	/**
	 * Cantidad de volumen en la presentacion
	 */

	private int cantidadEnPresentacion;

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

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	//=======================================================
	// Constructor
	//=======================================================

	public Producto(){
		this.id = 0;
		cantidadEnPresentacion = 0;
		marca = "";
		presentacion = "";
		especificacionEmpacado = 0;
		unidadMedida = "";		
	}

	//=======================================================
	// Getters and Setters
	//=======================================================

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCantidadEnPresentacion() {
		return cantidadEnPresentacion;
	}

	public void setCantidadEnPresentacion(int cantidadEnPresentacion) {
		this.cantidadEnPresentacion = cantidadEnPresentacion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public int getEspecificacionEmpacado() {
		return especificacionEmpacado;
	}

	public void setEspecificacionEmpacado(int especificacionEmpacado) {
		this.especificacionEmpacado = especificacionEmpacado;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
}
