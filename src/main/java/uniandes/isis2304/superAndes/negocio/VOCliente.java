package uniandes.isis2304.superAndes.negocio;

import java.util.List;

public interface VOCliente {

	/**
	 * @return the idCliente
	 */
	
	public long getIdCliente();

	/**
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion();	

	/**
	 * @return the numeroIdentificacion
	 */
	public int getNumeroIdentificacion();	

	/**
	 * @return the correo
	 */
	public String getCorreo();	
	
	/**
	 * @return the nombre
	 */
	
	public String getNombre();	

	/**
	 * @return the direccion
	 */
	
	public String getDireccion();	
	
	/**
	 * @return the comprasRealizadas
	 */
	public List<Factura> getComprasRealizadas();
	
	
	@Override
	public String toString();	
}
