package uniandes.isis2304.superAndes.negocio;
import java.util.*;

/**
 * Modela el concepto de un supermercado en el mundo de negocio de SuperAndes
 * @author Geovanny Andres Gonzalez
 */

public class Supermercado implements VOSupermercado{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/** Numero de identificacion tributario del supermercado */
	
	private int NIT;
	
	/** Nombre del supermercado */
	
	private String nombre;
	
	/** Lista de todos los productos ofrecidos por el supermercado 
	 *  Cada espacio de Object es una dupla de: [Producto, Ofrecen] que representa el producto
	 *  y su costo en un supermercado.
	 */
	
	private List<Object []> productos;
	
	/** Lista de todos los proveedores que posee el supermercado  
	 */
	
	private List<Proveedor> proveedores;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Instantiates a new supermercado.
	 */
	
	public Supermercado() {
		NIT = 0;
		nombre = "";
		productos = new LinkedList<Object []>();
		proveedores = new LinkedList<>();
	}
	
	/**
	 * Instantiates a new supermercado.
	 *
	 * @param pNIT the nit
	 * @param pNombre the nombre
	 */
	
	public Supermercado(int pNIT, String pNombre) {
		NIT = pNIT;
		nombre = pNombre;
		productos = new LinkedList<Object []>(); //Los productos son desconocidos.
		proveedores = new LinkedList<>();
	}

	/**
	 * Gets the nit.
	 *
	 * @return the nit
	 */
	
	public int getNIT() {
		return NIT;
	}

	/**
	 * Sets the nit.
	 *
	 * @param nIT the new nit
	 */
	
	public void setNIT(int nIT) {
		NIT = nIT;
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
	 * Gets the productos.
	 *
	 * @return the productos
	 */
	
	public List<Object[]> getProductos() {
		return productos;
	}

	/**
	 * Sets the productos.
	 *
	 * @param productos the new productos
	 */
	
	public void setProductos(List<Object[]> productos) {
		this.productos = productos;
	}
	
	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	@Override
	public String toString() {
		return "Supermercado [NIT =" + NIT + ", nombre =" + nombre + "]";
	}
	
	/**
	 * Devuelve la informacion del supermercado con todos sus productos
	 * @return El supermercado y la informacion de todos sus productos
	 */
	
	public String toStringCompleto() {
		String infoSuper = "Supermercado [NIT =" + NIT + ", nombre =" + nombre;
		infoSuper += "\n\n -- Productos ofrecidos \n";
		int conteo = 1;
		
		for(Object[] elemento : productos) {
			String infoProducto = ((Producto) elemento[0]).toString();
			String infoPrecioCU = "" +((Ofrecen) elemento[1]).getPrecioUnitario();
			String infoPrecioUnidad = "" +((Ofrecen) elemento[1]).getPrecioUnidadMedida();
			infoSuper += conteo++ + "." + infoProducto + "precioUnitario = " + infoPrecioCU
					+ "precioUnidadMedida =" + infoPrecioUnidad +"\n";
		}
		
		infoSuper += "\n\n -- Proveedores de productos \n";
		conteo = 1; //Contar ahora los proveedores
		for (Proveedor elemento : proveedores) {
			infoSuper += conteo++ + "." + elemento.toString() +"\n";
		}
		
		infoSuper += "]";
		return infoSuper;
	}	
}
