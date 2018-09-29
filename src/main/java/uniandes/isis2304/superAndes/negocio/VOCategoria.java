package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos de CATEGORIA.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Geovanny Andres Gonzalez
 */
public interface VOCategoria {

	/* ****************************************************************
	 * 			M�todos 
	 *****************************************************************/
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	
	public long getId();

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	
	public void setId(long id);

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	
	public String getNombre();

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	
	public void setNombre(String nombre);

}
