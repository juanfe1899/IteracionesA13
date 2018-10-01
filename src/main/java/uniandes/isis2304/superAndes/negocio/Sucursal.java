package uniandes.isis2304.superAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase para modelar la relación SUCURSAL del negocio SuperAndes
 * @author Geovanny Andres Gonzalez
 */

public class Sucursal implements VOSucursal{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/** Ciudad de la sucursal */
	
	private String ciudad;
	
	/** Direccion de la sucursal */
	
	private String direccion;
	
	/** Identificacion de la sucursal */
	
	private long idSucursal;
	
	/** Nombre de la sucursal. */
	
	private String nombre;
	
	/** Son todo el registro historico de las ordenes de pedido hechas por una sucursal. */
	
	private List<OrdenProducto> ordenesSucursal;
	
	/** Ventas realizadas */
	
	private List<Factura> ventas;
	
	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/
	
	/**
	 * Instantiates a new sucursal.
	 */
	
	public Sucursal () {
		ciudad = "";
		direccion = "";
		idSucursal = 0;
		nombre = null; //Según las reglas de negocio el nombre puede ser nulo.
		ordenesSucursal = new LinkedList<>();
		ventas = new LinkedList<>();
	}
	
	/**
	 * Instantiates a new sucursal.
	 *
	 * @param pCiudad the ciudad
	 * @param pDireccion the direccion
	 * @param pIdSucursal the id sucursal
	 * @param pNombre the nombre
	 */
	
	public Sucursal (String pCiudad, String pDireccion, long pIdSucursal, String pNombre) {
		ciudad = pCiudad;
		direccion = pDireccion;
		idSucursal = pIdSucursal;
		nombre = pNombre;
		ordenesSucursal = new LinkedList<>();
		ventas = new LinkedList<>();
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
	
	public List<OrdenProducto> getOrdenesSucursal() {
		return ordenesSucursal;
	}

	public void setOrdenesSucursal(List<OrdenProducto> ordenesSucursal) {
		this.ordenesSucursal = ordenesSucursal;
	}

	/**
	 * @return the ventas
	 */
	public List<Factura> getVentas() {
		return ventas;
	}

	/**
	 * @param ventas the ventas to set
	 */
	public void setVentas(List<Factura> ventas) {
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "Sucursal [idSucursal =" + idSucursal + ", nombre ="+ nombre +", ciudad =" + ciudad + ", direccion =" + direccion + "]";				
	}
	
	/**
	 * Devuelve la informacion de la sucursal y de las ordenes de pedido realizadas.
	 * @return Informacion completa de las compras realizadas por una sucursal.
	 */	
	
	public String toStringCompleto() {
		String info = "Sucursal [idSucursal =" + idSucursal + ", nombre ="+ nombre +", ciudad =" + ciudad + ", direccion =" + direccion;
		info += "\n\n Ordenes solicitadas \n";
		int conteo = 1;
		
		for (OrdenProducto orden : ordenesSucursal) {
			info += conteo++ + "." + orden.toStringCompleto() + "\n";
		}
		info += "]";
		return info;
	}
}
