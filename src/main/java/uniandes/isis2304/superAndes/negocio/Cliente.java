package uniandes.isis2304.superAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * Representa un cliente de un supermercado
 * @author Geovanny Andres Gonzalez
 */

public class Cliente implements VOCliente{

	/** Identificador del cliente. */
	
	private long idCliente;
	
	/** Tipo identificacion. */
	
	private String tipoIdentificacion;
	
	/** Numero identificacion. */
	
	private int numeroIdentificacion;
	
	/** Correo electronico */
	
	private String correo;
	
	/** Nombre del cliente. */
	
	private String nombre;
	
	/** Direccion */
	
	private String direccion;
	
	/** Lista con las compras realizadas por el cliente */
	
	private List<Factura> comprasRealizadas;
	
	/**
	 * Instantiates a new cliente.
	 */
	
	public Cliente() {
		idCliente = 0;
		tipoIdentificacion = "";
		nombre = "";
		correo = "";
		direccion = "";
		numeroIdentificacion = 0;
		comprasRealizadas = new LinkedList<>();
	}
	
	/**
	 * Instantiates a new cliente.
	 *
	 * @param pId the id
	 * @param pTipoIdentificacion the tipo identificacion
	 * @param pNumero the numero
	 * @param pCorreo the correo
	 * @param pNombre the nombre
	 * @param pDireccion the direccion
	 */
	
	public Cliente (long pId, String pTipoIdentificacion, int pNumero, String pCorreo, String pNombre,
			String pDireccion) {
		
		idCliente = pId;
		tipoIdentificacion = pTipoIdentificacion;
		nombre = pNombre;
		correo = pCorreo;
		direccion = pDireccion;
		numeroIdentificacion = pNumero;
		comprasRealizadas = new LinkedList<>();
	}

	/**
	 * @return the idCliente
	 */
	
	public long getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * @param tipoIdentificacion the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * @return the numeroIdentificacion
	 */
	public int getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	/**
	 * @param numeroIdentificacion the numeroIdentificacion to set
	 */
	public void setNumeroIdentificacion(int numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * @return the comprasRealizadas
	 */
	public List<Factura> getComprasRealizadas() {
		return comprasRealizadas;
	}

	/**
	 * @param comprasRealizadas the comprasRealizadas to set
	 */
	public void setComprasRealizadas(List<Factura> comprasRealizadas) {
		this.comprasRealizadas = comprasRealizadas;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente =" + idCliente + ", tipoIdentificacion =" + tipoIdentificacion + ", numeroIdentificacion =" +
	           numeroIdentificacion + ", correo =" + correo + ", nombre =" + nombre + ", direccion =" + direccion + "]";
	}	
}
