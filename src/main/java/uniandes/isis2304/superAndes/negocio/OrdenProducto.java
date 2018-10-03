package uniandes.isis2304.superAndes.negocio;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa la relacion PRODUCTOS_SUCURSAL 
 * @author Geovanny Andres Gonzalez
 */

public class OrdenProducto implements VOOrdenProducto{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/** Calificacion del pedido */
	
	private int calificacionOrden;
	
	/** Estado de la orden, puede ser EN_ESPERA o ENTREGADO. */
	
	private String estado;
	
	/** Fecha esperada de entrega del producto. */
	
	private Timestamp fechaEsperada;
	
	/** Fecha de entrega del producto */
	
	private Timestamp fechaEntrega;
	
	/** Identificador unico de la orden. */
	
	private long idOrden;
	
	/**
	 *  Permite darle el formato a la fecha - ATENCION: Si la fecha presenta formatos para
	 *  agregarse preste atencion al formato que esto aplica, en especial en que antes y despues
	 *  del guion HAY UN ESPACIO VACIO.
	 */
	
	private static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd '-' HH:mm");
	
	/** Lista de todos los productos ofrecidos por el proveedor 
	 *  Cada espacio de Object es una dupla de: [Producto, ProductosOrden] que representa el producto,
	 *  la cantidad y el costo acordado de compra con el que lo vende el proveedor.
	 */
	
	private List<Object[]> listaProductos;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructo por defecto.
	 */
	
	public OrdenProducto() {
		calificacionOrden = 0;
		estado = "";
		fechaEsperada = new Timestamp(0);
		fechaEntrega = new Timestamp(0);
		idOrden = 0;
		listaProductos = new LinkedList<>();
	}
	
	/**
	 * Instantiates a new orden producto.
	 *
	 * @param pCalificacion the calificacion
	 * @param pEstado the estado
	 * @param pFechaEsperada the fecha esperada
	 * @param pFechaEntrega the fecha entrega
	 * @param pIdOrden the id orden
	 */
	
	public OrdenProducto(int pCalificacion, String pEstado, Timestamp pFechaEsperada, Timestamp pFechaEntrega, long pIdOrden) {
		calificacionOrden = pCalificacion;
		estado = pEstado;
		fechaEsperada = pFechaEsperada;
		fechaEntrega = pFechaEntrega;
		idOrden = pIdOrden;
		listaProductos = new LinkedList<>();
	}

	/**
	 * Gets the calificacion producto.
	 *
	 * @return the calificacion producto
	 */
	
	public int getCalificacionOrden() {
		return calificacionOrden;
	}

	/**
	 * Sets the calificacion producto.
	 *
	 * @param calificacionProducto the new calificacion producto
	 */
	
	public void setCalificacionOrden(int calificacionProducto) {
		this.calificacionOrden = calificacionProducto;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the fecha esperada.
	 *
	 * @return the fecha esperada
	 */
	
	public Timestamp getFechaEsperada() {
		return fechaEsperada;
	}

	/**
	 * Sets the fecha esperada.
	 *
	 * @param fechaEsperada the new fecha esperada
	 */
	
	public void setFechaEsperada(Timestamp fechaEsperada) {
		this.fechaEsperada = fechaEsperada;
	}

	/**
	 * Gets the fecha entrega.
	 *
	 * @return the fecha entrega
	 */
	
	public Timestamp getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * Sets the fecha entrega.
	 *
	 * @param fechaEntrega the new fecha entrega
	 */
	
	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * Gets the id orden.
	 *
	 * @return the id orden
	 */
	
	public long getIdOrden() {
		return idOrden;
	}

	/**
	 * Sets the id orden.
	 *
	 * @param idOrden the new id orden
	 */
	
	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}
	
	/**
	 * Gets the lista productos.
	 *
	 * @return the lista productos
	 */
	
	public List<Object[]> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Sets the lista productos.
	 *
	 * @param listaProductos the new lista productos
	 */
	
	public void setListaProductos(List<Object[]> listaProductos) {
		this.listaProductos = listaProductos;
	}

	@Override
	public String toString() {
		String sFechaEsperada = formatoFecha.format(fechaEsperada);
		String sFechaEntrega = formatoFecha.format(fechaEntrega);
		return "Orden Producto [idOrden =" + idOrden + ", estado =" + estado + ", fechaEsperada ="
				+ sFechaEsperada + ", fechaEntrega =" + sFechaEntrega + ", calificacion =" + calificacionOrden + "]";
	}
	
	/**
	 * Devuelve la informacion de la orden de producto junto con la informacion de los productos ordenados
	 * @return Informacion completa y detallada de la orden.
	 */
	
	public String toStringCompleto() {
		String sFechaEsperada = formatoFecha.format(fechaEsperada);
		String sFechaEntrega = formatoFecha.format(fechaEntrega);
		String infoSuper = "Orden Producto [idOrden =" + idOrden + ", estado =" + estado + ", fechaEsperada ="
							+ sFechaEsperada + ", fechaEntrega =" + sFechaEntrega + ", calificacion =" + calificacionOrden;
		infoSuper += "\n\n -- Productos ordenados \n";
		int conteo = 1;
		
		for(Object[] elemento : listaProductos) {
			String infoProducto = ((Producto) elemento[0]).toString();
			String infoOrden = "" + ((ProductosOrden) elemento[1]).toString();			
			infoSuper += conteo++ + "." + infoProducto + "\n" + infoOrden + "\n";
		}
		
		infoSuper += "]";
		return infoSuper;
	}
}
