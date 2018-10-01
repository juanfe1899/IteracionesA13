package uniandes.isis2304.superAndes.negocio;

import java.util.List;

public interface VOEspacioAcomodacion {

	/**
	 * Gets the id espacio.
	 *
	 * @return the id espacio
	 */
	
	public long getIdEspacio();

	/**
	 * Sets the id espacio.
	 *
	 * @param idEspacio the new id espacio
	 */
	
	public void setIdEspacio(long idEspacio);

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	
	public String getTipo();

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	
	public void setTipo(String tipo);

	/**
	 * Gets the volumen maximo.
	 *
	 * @return the volumen maximo
	 */
	
	public int getVolumenMaximo();

	/**
	 * Sets the volumen maximo.
	 *
	 * @param volumenMaximo the new volumen maximo
	 */
	
	public void setVolumenMaximo(int volumenMaximo);

	/**
	 * Gets the volumen minimo.
	 *
	 * @return the volumen minimo
	 */
	
	public int getVolumenMinimo();

	/**
	 * Sets the volumen minimo.
	 *
	 * @param volumenMinimo the new volumen minimo
	 */
	
	public void setVolumenMinimo(int volumenMinimo);

	/**
	 * Gets the categoria producto.
	 *
	 * @return the categoria producto
	 */
	
	public Categoria getCategoriaProducto();

	/**
	 * Sets the categoria producto.
	 *
	 * @param categoriaProducto the new categoria producto
	 */
	
	public void setCategoriaProducto(Categoria categoriaProducto);
	
	/**
	 * Gets the productos espacio.
	 *
	 * @return the productos espacio
	 */
	
	public List<Object[]> getProductosEspacio();

	/**
	 * Sets the productos espacio.
	 *
	 * @param productosEspacio the new productos espacio
	 */
	
	public void setProductosEspacio(List<Object[]> productosEspacio);
	
	@Override
	public String toString();
	
	public String toStringCompleto();
}
