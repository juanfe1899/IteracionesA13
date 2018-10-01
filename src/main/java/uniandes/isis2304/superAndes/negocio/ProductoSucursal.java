package uniandes.isis2304.superAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase para modelar la relación PRODUCTO_SUCURSAL del negocio SuperAndes. 
 * @author Geovanny Andres Gonzalez
 */

public class ProductoSucursal {
		
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/** Cantidad a la cual se debe realizar un pedido del producto */
	
	private int cantidadReorden;
	
	/** Identificador unico del producto en la sucursal */
	
	private long idProductoSucursal;
	
	/** Precio unitario */
	
	private int precioUnitario;
	
	/** Informacion del producto en catalogo general. */
	
	private Producto producto;
	
	/** Record historico de promociones del producto */
	
	private List<Promocion> promociones;
	
	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/
	
	public ProductoSucursal() {
		cantidadReorden = 0;
		idProductoSucursal = 0;
		precioUnitario = 0;
		promociones = new LinkedList<>();
	}
	
	public ProductoSucursal(int pCantidad, long pIdProducto, int pPrecioUnitario, Producto pProducto) {
		cantidadReorden = pCantidad;
		idProductoSucursal = pIdProducto;
		precioUnitario = pPrecioUnitario;
		producto = pProducto;
		promociones = new LinkedList<>();
	}

	/**
	 * Gets the cantidad reorden.
	 *
	 * @return the cantidad reorden
	 */
	
	public int getCantidadReorden() {
		return cantidadReorden;
	}

	/**
	 * Sets the cantidad reorden.
	 *
	 * @param cantidadReorden the new cantidad reorden
	 */
	
	public void setCantidadReorden(int cantidadReorden) {
		this.cantidadReorden = cantidadReorden;
	}

	/**
	 * Gets the id producto sucursal.
	 *
	 * @return the id producto sucursal
	 */
	
	public long getIdProductoSucursal() {
		return idProductoSucursal;
	}

	/**
	 * Sets the id producto sucursal.
	 *
	 * @param idProductoSucursal the new id producto sucursal
	 */
	
	public void setIdProductoSucursal(long idProductoSucursal) {
		this.idProductoSucursal = idProductoSucursal;
	}

	/**
	 * Gets the precio unitario.
	 *
	 * @return the precio unitario
	 */
	
	public int getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * Sets the precio unitario.
	 *
	 * @param precioUnitario the new precio unitario
	 */
	
	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	
	public Producto getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto the new producto
	 */
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}	
	
	/**
	 * @return the promociones
	 */
	public List<Promocion> getPromociones() {
		return promociones;
	}

	/**
	 * @param promociones the promociones to set
	 */
	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}

	@Override
	public String toString() {
		return "Producto Sucursal [idProductoSucursal =" + idProductoSucursal + ", precioUnitario"
				+ ", cantidadReorden =" + cantidadReorden + "\n\n Informacion detallada \n" + producto.toString() + "]";
	}
	
	/**
	 * Devuelve la informacion completa del producto de la sucursal y sus promociones
	 * @return Informacion con promociones.
	 */
	
	public String toStringCompleto() {
		String info = toString();
		int conteo = 1;
		
		info += "\n\n Promociones historicas \n";
		for (Promocion elemento : promociones) {
			info += conteo++ + elemento.toString() + "\n";
		}
		
		info += "]";
		return info;
	}
}
