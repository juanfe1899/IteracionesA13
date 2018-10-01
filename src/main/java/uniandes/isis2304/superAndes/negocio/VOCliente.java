package uniandes.isis2304.superAndes.negocio;

import java.util.List;

public interface VOCliente {

	/**
	 * @return the idCliente
	 */
	
	public long getIdCliente();

	/**
	 * @param idCliente the idCliente to set
	 */
	
	public void setIdCliente(long idCliente);

	/**
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion();

	/**
	 * @param tipoIdentificacion the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(String tipoIdentificacion);

	/**
	 * @return the numeroIdentificacion
	 */
	public int getNumeroIdentificacion();

	/**
	 * @param numeroIdentificacion the numeroIdentificacion to set
	 */
	public void setNumeroIdentificacion(int numeroIdentificacion);

	/**
	 * @return the correo
	 */
	public String getCorreo();

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo);
	
	/**
	 * @return the nombre
	 */
	
	public String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	
	public void setNombre(String nombre);

	/**
	 * @return the direccion
	 */
	
	public String getDireccion();

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion);
	
	/**
	 * @return the comprasRealizadas
	 */
	public List<Factura> getComprasRealizadas();

	/**
	 * @param comprasRealizadas the comprasRealizadas to set
	 */
	
	public void setComprasRealizadas(List<Factura> comprasRealizadas);
	
	@Override
	public String toString();	
}
