package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar la relación SUCURSAL del negocio SuperAndes
 * @author Geovanny Andres Gonzalez
 */

public class Sucursal {
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private String ciudad;
	
	private String direccion;
	
	private long idSucursal;
	
	private String nombre;
	
	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/
	
	public Sucursal () {
		ciudad = "";
		direccion = "";
		idSucursal = 0;
		nombre = "";
	}
	
	public Sucursal (String pCiudad, String pDireccion, long pIdSucursal, String pNombre) {
		ciudad = pCiudad;
		direccion = pDireccion;
		idSucursal = pIdSucursal;
		nombre = pNombre;
	}

	/**
	 * Gets the ciudad.
	 *
	 * @return the ciudad
	 */
	
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Sets the ciudad.
	 *
	 * @param ciudad the new ciudad
	 */
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Sets the direccion.
	 *
	 * @param direccion the new direccion
	 */
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Gets the id sucursal.
	 *
	 * @return the id sucursal
	 */
	
	public long getIdSucursal() {
		return idSucursal;
	}

	/**
	 * Sets the id sucursal.
	 *
	 * @param idSucursal the new id sucursal
	 */
	
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
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
	
	@Override
	public String toString() {
		return "Sucursal [idSucursal =" + idSucursal + ", nombre ="+ nombre +", ciudad =" + ciudad + ", direccion =" + direccion + "]";				
	}
}
