package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.OrdenProducto;
import uniandes.isis2304.superAndes.negocio.ProductosOrden;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperandes;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ORDEN_PRODUCTO de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Geovanny Andres Gonzalez
 */

class SQLProductosOrden {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acÃ¡ para facilitar la escritura de las sentencias
	 */

	private final static String SQL = PersistenciaSuperandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaciÃ³n
	 */
	private PersistenciaSuperandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	
	public SQLProductosOrden (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	
	/**
	 * Agregar un nuevo pedido en la base de datos.
	 * @param pm the pm Manejador de persistencia
	 * @param id the id Identificador unico del pedido
	 * @param idSucursal the id sucursal Identificador de la sucursal que realiza el pedido
	 * @param nit the nit Identificador del proveedor destino
	 * @param estado the estado Estado actual del pedido
	 * @param fechaEsperada the fecha esperada Fecha en la que se espera llegue el pedido
	 * @param fechaEntrega the fecha entrega Fecha de entrega del pedido por parte del proveedor.
	 * @param calificacion the calificacion Calificacion del pedido
	 * @return Numero de tuplas agregadas a la BD
	 */
	
	public long agregarProductosOrden(PersistenceManager pm, long idPedido , String codProducto, int precioUnitario, int cantidad) {
		Query qProductosOrden = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOrdenProductos() + "(id_pedido, id_producto, precio_cu_acordado, cantidad) VALUES (?, ?, ?, ?)");		
		qProductosOrden.setParameters(idPedido, codProducto, precioUnitario, cantidad);
		long tuplas = (long) qProductosOrden.executeUnique();
		return tuplas;
	}
	
	/**
	 * Elimina a un pedido
	 * @param pm the pm Manejador de persistencia
	 * @param id the id Identificador unico del pedido
	 * @return El numero de tuplas eliminadas.
	 */
	
	public long eliminarProductosOrden(PersistenceManager pm, long idPedido) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenProductos () + " WHERE id_pedido = ? ");
        q.setParameters(idPedido);
        return (long) q.executeUnique();           
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una orden de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id the id Identificador unico del pedido
	 * @return El objeto ORDEN_PRODUCTO a buscar.
	 */
	
	public ProductosOrden darProductoOrden (PersistenceManager pm, long idPedido, String codProducto) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenProductos () + " WHERE id_pedido = ? AND id_producto = ?");
        q.setParameters(idPedido, codProducto);
        q.setResultClass(ProductosOrden.class);
        return (ProductosOrden) q.executeUnique();
	}	
	
	public List<ProductosOrden> darProductosOrden (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPedidosSucursal ());
		q.setResultClass(ProductosOrden.class);
		List<ProductosOrden> executeList = (List<ProductosOrden>) q.executeList();		
		return executeList;
	}			
}
