package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos de PROVEEDOR.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Geovanny Andres Gonzalez
 */

public interface VOProveedor {

	public int getCalificacion();

	public void setCalificacion(int calificacion);

	public int getNIT();

	public void setNIT(int nIT);

	public String getNombre();

	public void setNombre(String nombre);
	
	@Override
	public String toString();
	
	public String toStringCompleto();
}
