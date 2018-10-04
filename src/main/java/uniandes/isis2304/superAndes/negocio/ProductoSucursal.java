package uniandes.isis2304.superAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase para modelar la relación PRODUCTO_SUCURSAL del negocio SuperAndes. 
 * @author Geovanny Andres Gonzalez
 */

public class ProductoSucursal implements VOProductosSucursal{
		
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
	
	private String producto;
	
	private long idSucursal;
	
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
	
	public ProductoSucursal(int pCantidad, long pIdProducto, long idSucursal,int pPrecioUnitario, String codProducto) {
		cantidadReorden = pCantidad;
		idProductoSucursal = pIdProducto;
		precioUnitario = pPrecioUnitario;
		producto = codProducto;
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

	public long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}
	
	public String getProducto(){
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Producto Sucursal [idProductoSucursal =" + idProductoSucursal + ", precioUnitario"
				+ ", cantidadReorden =" + cantidadReorden + "\n\n Informacion detallada \n" + producto.toString() + "]";
	}
	
	public int verificarPromo(String tipo)
	{
		if(tipo.startsWith("P"))
		{
			String[] rsp = tipo.split("R");
			String o2 = rsp[1];
			String[] o3 = o2.split("X");
			return Integer.parseInt(o3[1]);
		}
		
		else
		{
			String o2 = tipo.split("R")[1];
			int descuento = Integer.parseInt(o2);
			precioUnitario = precioUnitario * descuento;
			return -1;
		}
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
