package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

public interface VOPromocion {

	/**
	 * @return the fechaInicio
	 */
	
	public Timestamp getFechaInicio();

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Timestamp fechaInicio);

	/**
	 * @return the fechaFinal
	 */
	public Timestamp getFechaFinal();

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Timestamp fechaFinal);

	/**
	 * @return the idPromocion
	 */
	public long getIdPromocion();

	/**
	 * @param idPromocion the idPromocion to set
	 */
	public void setIdPromocion(long idPromocion);

	/**
	 * @return the tipoPromocion
	 */
	public String getTipoPromocion();

	/**
	 * @param tipoPromocion the tipoPromocion to set
	 */
	public void setTipoPromocion(String tipoPromocion);

	/**
	 * @return the unidadesDisponibles
	 */
	public int getUnidadesDisponibles();

	/**
	 * @param unidadesDisponibles the unidadesDisponibles to set
	 */
	
	public void setUnidadesDisponibles(int unidadesDisponibles);
	
	/**
	 * @return the productoPromocion
	 */
	public ProductoSucursal getProductoPromocion();

	/**
	 * @param productoPromocion the productoPromocion to set
	 */
	public void setProductoPromocion(ProductoSucursal productoPromocion);

	public String toString();	
}
