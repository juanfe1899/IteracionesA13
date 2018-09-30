package uniandes.isis2304.superAndes.negocio;

/**
 * Modela el concepto de un proveedor de productos en el mundo de negocio de SuperAndes
 * @author Geovanny Andres Gonzalez
 */

public class Proveedor {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private int calificacion;
	
	private int NIT;
	
	private String nombre;	
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	public Proveedor() {
		calificacion = 0;
		NIT = 0;
		nombre = "";
	}
	
	public Proveedor(int pCalificacion, int pNIT, String pNombre) {
		calificacion = pCalificacion;
		NIT = pNIT;
		nombre = pNombre;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public int getNIT() {
		return NIT;
	}

	public void setNIT(int nIT) {
		NIT = nIT;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Proveedor [NIT =" + NIT + ", calificacion =" + calificacion + ", nombre =" +
				nombre + "]";
	}
	
	
}

