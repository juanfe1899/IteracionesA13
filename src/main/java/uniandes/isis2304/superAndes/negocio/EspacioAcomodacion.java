package uniandes.isis2304.superAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase para modelar el concepto de un espacio de acomodación en una sucursal
 * Entiendase por ello como el lugar donde se acomodan los productos. 
 * Puede ser una bodega o un estante
 * @author Geovanny Andres Gonzalez
 */

public class EspacioAcomodacion implements VOEspacioAcomodacion{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	/** Identificador del espacio de acomodacion. */
	
	private long idEspacio;
	
	/** Tipo del espacio, solo puede ser bodega o estanteria */
	
	private String tipo;
	
	/** Volumen maximo del espacio. */
	
	private int volumenMaximo;
	
	/** Volumen minimo del espacio. */
	
	private int volumenMinimo;
	
	/** Categoria del producto a guardar. */
	
	private Categoria categoriaProducto;	
	
	/** The Constant BODEGA. */
	
	public final static String BODEGA = "BODEGA";
	
	/** The Constant ESTANTE. */
	
	public final static String ESTANTE = "ESTANTE";
	
	/** 
	 * Los productos almacenados en el espacio de acomodacion. Se recibe una dupla del tipo
	 * [ProductoSucursal, Existencias] 
	 */
	
	private List<Object[]> productosEspacio;
	
	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/
	
	/**
	 * Instantiates a new espacio acomodacion.
	 */
	
	public EspacioAcomodacion() {
		idEspacio = 0;
		tipo = "";
		volumenMaximo = 0;
		volumenMinimo = 0;	
		productosEspacio = new LinkedList<>();
	}
	
	/**
	 * Instantiates a new espacio acomodacion.
	 *
	 * @param pIdEspacio the id espacio
	 * @param pTipo the tipo
	 * @param pVolumenMax the volumen max
	 * @param pVolumenMin the volumen min
	 */
	
	public EspacioAcomodacion(long pIdEspacio, String pTipo, int pVolumenMax, int pVolumenMin, Categoria pCategoria) {
		idEspacio = pIdEspacio;
		tipo = pTipo;
		volumenMaximo = pVolumenMax;
		volumenMinimo = pVolumenMin;
		categoriaProducto = pCategoria;
		assert tipo == BODEGA || tipo == ESTANTE : "El tipo del espacio de acomodacion no es correcto";
		productosEspacio = new LinkedList<>();
	}

	/**
	 * Gets the id espacio.
	 *
	 * @return the id espacio
	 */
	
	public long getIdEspacio() {
		return idEspacio;
	}

	/**
	 * Sets the id espacio.
	 *
	 * @param idEspacio the new id espacio
	 */
	
	public void setIdEspacio(long idEspacio) {
		this.idEspacio = idEspacio;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the volumen maximo.
	 *
	 * @return the volumen maximo
	 */
	
	public int getVolumenMaximo() {
		return volumenMaximo;
	}

	/**
	 * Sets the volumen maximo.
	 *
	 * @param volumenMaximo the new volumen maximo
	 */
	
	public void setVolumenMaximo(int volumenMaximo) {
		this.volumenMaximo = volumenMaximo;
	}

	/**
	 * Gets the volumen minimo.
	 *
	 * @return the volumen minimo
	 */
	
	public int getVolumenMinimo() {
		return volumenMinimo;
	}

	/**
	 * Sets the volumen minimo.
	 *
	 * @param volumenMinimo the new volumen minimo
	 */
	
	public void setVolumenMinimo(int volumenMinimo) {
		this.volumenMinimo = volumenMinimo;
	}

	/**
	 * Gets the categoria producto.
	 *
	 * @return the categoria producto
	 */
	
	public Categoria getCategoriaProducto() {
		return categoriaProducto;
	}

	/**
	 * Sets the categoria producto.
	 *
	 * @param categoriaProducto the new categoria producto
	 */
	
	public void setCategoriaProducto(Categoria categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}
	
	/**
	 * Gets the productos espacio.
	 *
	 * @return the productos espacio
	 */
	
	public List<Object[]> getProductosEspacio() {
		return productosEspacio;
	}

	/**
	 * Sets the productos espacio.
	 *
	 * @param productosEspacio the new productos espacio
	 */
	
	public void setProductosEspacio(List<Object[]> productosEspacio) {
		this.productosEspacio = productosEspacio;
	}

	@Override
	public String toString() {
		return "Espacio Acomodacion [idEspacio =" + idEspacio + ", tipo =" + ", volumenMinimo =" + volumenMinimo
				+ ", volumenMaximo =" + volumenMaximo + ", categoria =" + categoriaProducto.toString() + "]";
	}
	
	public String toStringCompleto() {
		String info = "Espacio Acomodacion [idEspacio =" + idEspacio + ", tipo =" + ", volumenMinimo =" + volumenMinimo
				+ ", volumenMaximo =" + volumenMaximo + ", categoria =" + categoriaProducto.toString();
		
		info += "\n\n Productos almacenados \n";
		int conteo = 1;
		
		for (Object[] elemento : productosEspacio) {
			String productoSucursal = ((ProductoSucursal) elemento[0]).toString();
			String existencias = ((Existencias) elemento[1]).toString();
			info += conteo++ + "." + productoSucursal +  existencias +"\n";
		}
		
		info += "]";
		return info;
	}
	
}
