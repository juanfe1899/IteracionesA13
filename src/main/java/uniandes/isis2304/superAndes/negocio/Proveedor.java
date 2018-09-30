package uniandes.isis2304.superAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * Modela el concepto de un proveedor de productos en el mundo de negocio de SuperAndes
 * @author Geovanny Andres Gonzalez
 */

public class Proveedor implements VOProveedor{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/** La calificacion del proveedor según la calidad de entregas de productos*/
	
	private int calificacion;
	
	/** Identificacion del proveedor */
	
	private int NIT;
	
	/** Nombre del proveedor */
	
	private String nombre;	
	
	/** Lista de todos los productos ofrecidos por el proveedor 
	 *  Cada espacio de Object es una dupla de: [Producto, Proveen] que representa el producto
	 *  y el costo a que lo vende el proveedor.
	 */
	
	private List<Object []> productos;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
		
	/**
	 * Constructor por defecto.
	 */
	
	public Proveedor() {
		calificacion = 0;
		NIT = 0;
		nombre = "";
		productos = new LinkedList<Object[]>();
	}
	
	/**
	 * Instantiates a new proveedor.
	 *
	 * @param pCalificacion the calificacion
	 * @param pNIT the nit
	 * @param pNombre the nombre
	 */
	
	public Proveedor(int pCalificacion, int pNIT, String pNombre) {
		calificacion = pCalificacion;
		NIT = pNIT;
		nombre = pNombre;
		productos = new LinkedList<Object[]>();
	}

	/**
	 * Gets the calificacion.
	 *
	 * @return the calificacion
	 */
	
	public int getCalificacion() {
		return calificacion;
	}

	/**
	 * Sets the calificacion.
	 *
	 * @param calificacion the new calificacion
	 */
	
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
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

	/** 
	 * Retorna en formato de texto la informacion de un proveedor
	 */
	
	@Override
	public String toString() {
		return "Proveedor [NIT =" + NIT + ", calificacion =" + calificacion + ", nombre =" +
				nombre + "]";
	}
	
	/**
	 * Devuelve la informacion del proveedor con todos los productos que suministra
	 * @return El proveedor y la informacion de todos sus productos
	 */
	
	public String toStringCompleto() {
		String infoSuper = "Proveedor [NIT =" + NIT + ", nombre =" + nombre + ", calificacion =" + calificacion;
		infoSuper += "\n\n -- Productos que provee \n";
		int conteo = 1;
		
		for(Object[] elemento : productos) {
			String infoProducto = ((Producto) elemento[0]).toString();
			String infoPrecioCU = "" + ((Proveen) elemento[1]).getPrecioUnitario();
			String infoPrecioUnidad = "" +((Proveen) elemento[1]).getPrecioUnidadMedida();
			infoSuper += conteo++ + "." + infoProducto + "precioUnitario = " + infoPrecioCU
					+ "precioUnidadMedida =" + infoPrecioUnidad +"\n";
		}
		
		return infoSuper;
	}	
}

