package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto CATEGORIA del negocio de los Parranderos
 * @author Geovanny Andres Gonzalez
 */

public class Categoria implements VOCategoria{

	/**
	 * Identificador de la categoria
	 */

	private long id;	

	/**
	 * Nombre de la categoria
	 */

	private String nombre;

	/* ****************************************************************
	 * 			M�todos 
	 *****************************************************************/

	//=======================================================
	// Constructor
	//=======================================================

	/**
	 * Constructor por defecto
	 */

	public Categoria() {
		id = 0;		
		nombre = "";
	}

	/**
	 * Instantiates a new categoria.
	 *
	 * @param pId the id
	 * @param pNombre the nombre
	 */

	public Categoria(long pId, String pNombre) {
		id = pId;
		nombre = pNombre;
	}

	//=======================================================
	// Getters and Setters
	//=======================================================

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	
	public void setId(long id) {
		this.id = id;
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
		return "Categoria [id =" + id + ", nombre =" + nombre +"]"; 
	}
}
