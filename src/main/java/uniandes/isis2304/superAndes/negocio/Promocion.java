package uniandes.isis2304.superAndes.negocio;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Promocion {

	/**
	 * Fecha de inicio de una promocion
	 */
	
	private Timestamp fechaInicio;
	
	/**
	 * Fecha final de una promocion
	 */
	
	private Timestamp fechaFinal;
	
	/**
	 * Identificador unico de una promocion.
	 */
	
	private long idPromocion;
	
	/**
	 * Tipo de promocion a aplicar sobre el producto
	 */
	
	private String tipoPromocion;
	
	/**
	 * Numero de unidades disponibles para promocionar.
	 */
	
	private int unidadesDisponibles;
	
	/**
	 *  Permite darle el formato a la fecha - ATENCION: Si la fecha presenta formatos para
	 *  agregarse preste atencion al formato que esto aplica, en especial en que antes y despues
	 *  del guion HAY UN ESPACIO VACIO.
	 */
	
	private static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd '-' HH:mm");
	
	
	
	private ProductoSucursal productoPromocion;
	
	/**
	 * Instantiates a new promociones.
	 */
	
	public Promocion() {
		fechaInicio = new Timestamp(0);
		fechaFinal = new Timestamp(0);
		idPromocion = 0;
		tipoPromocion = "";
		unidadesDisponibles = 0;
		productoPromocion = null;
	}
	
	/**
	 * Instantiates a new promociones.
	 *
	 * @param pFechaInicio the fecha inicio
	 * @param pFechaFinal the fecha final
	 * @param pIdPromocion the id promocion
	 * @param pTipo the tipo
	 * @param pUnidades the unidades
	 */
	
	public Promocion (Timestamp pFechaInicio, Timestamp pFechaFinal, long pIdPromocion, String pTipo,
			int pUnidades, ProductoSucursal pProducto) {
		
		fechaInicio = pFechaInicio;
		fechaFinal = pFechaFinal;
		idPromocion = pIdPromocion;
		tipoPromocion = pTipo;
		unidadesDisponibles = pUnidades;
		productoPromocion = pProducto;
	}

	/**
	 * @return the fechaInicio
	 */
	
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFinal
	 */
	public Timestamp getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the idPromocion
	 */
	public long getIdPromocion() {
		return idPromocion;
	}

	/**
	 * @param idPromocion the idPromocion to set
	 */
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	/**
	 * @return the tipoPromocion
	 */
	public String getTipoPromocion() {
		return tipoPromocion;
	}

	/**
	 * @param tipoPromocion the tipoPromocion to set
	 */
	public void setTipoPromocion(String tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}

	/**
	 * @return the unidadesDisponibles
	 */
	public int getUnidadesDisponibles() {
		return unidadesDisponibles;
	}

	/**
	 * @param unidadesDisponibles the unidadesDisponibles to set
	 */
	
	public void setUnidadesDisponibles(int unidadesDisponibles) {
		this.unidadesDisponibles = unidadesDisponibles;
	}
	
	/**
	 * @return the productoPromocion
	 */
	public ProductoSucursal getProductoPromocion() {
		return productoPromocion;
	}

	/**
	 * @param productoPromocion the productoPromocion to set
	 */
	public void setProductoPromocion(ProductoSucursal productoPromocion) {
		this.productoPromocion = productoPromocion;
	}

	public String toString() {
		return "Promociones [idPromocion =" + idPromocion + ", tipo =" + tipoPromocion + ", fechaInicio ="
				+ formatoFecha.format(fechaInicio) + ", fechaFinal =" + formatoFecha.format(fechaFinal) + ", unidadesDisponibles ="
				+ unidadesDisponibles + ", producto =" + productoPromocion.toString();
	}	
}
