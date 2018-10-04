package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.OrdenProducto;
import uniandes.isis2304.superAndes.negocio.ProductoSucursal;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto PRODUCTOS_SUCURSAL de SuperAndes
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 * 
 * @author Geovanny Andres Gonzalez
 */

class SQLProductosSucursal {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */

	private final static String SQL = PersistenciaSuperandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperandes pp;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	
	public SQLProductosSucursal (PersistenciaSuperandes pp)
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
	
	public long agregarProductoSuc(PersistenceManager pm, long id , String codProducto, long idSucursal, int cantidadReorden ,int precioUnitaio) {
		Query qPedido = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosSucursal() + "(ID, ID_PRODUCTO, ID_SUCURSAL, CANTIDAD_REORDER, PRECIO_CU) VALUES (?,?,?,?,?)");		
		qPedido.setParameters(id, codProducto, idSucursal, cantidadReorden, precioUnitaio);
		long tuplas = (long) qPedido.executeUnique();
		return tuplas;
	}
	
	/**
	 * Elimina a un pedido
	 * @param pm the pm Manejador de persistencia
	 * @param id the id Identificador unico del pedido
	 * @return El numero de tuplas eliminadas.
	 */
	
	public long eliminarProductoSuc(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosSucursal () + " WHERE " + pp.darTablaProductosSucursal ()+ ".id = ?");
        q.setParameters(id);        
        System.out.println("Donde ID es :" + id);
        return (long) q.executeUnique();           
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de una orden de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id the id Identificador unico del pedido
	 * @return El objeto ORDEN_PRODUCTO a buscar.
	 */
	
	public ProductoSucursal darProductoSuc (PersistenceManager pm, String idProducto, long idSucursal) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductosSucursal () + " WHERE ID_PRODUCTO = ? AND id_sucursal = ?");
        q.setParameters(idProducto, idSucursal);        
        q.setUnique(true);        
        Object result = q.executeUnique();        
        Object[] resultados = (Object[]) result;
        
        //Casteo - ruego a Dios Padre que sirva, ya estoy que no puedo del cansancio, llevo 6 horas con eso.
        long idProductoSuc =  ((BigDecimal) resultados [0]).longValue ();
		String codProducto = (String) resultados [1];
		long idSucursal2 =  ((BigDecimal) resultados [2]).longValue ();
		int cantReorden = ((BigDecimal) resultados[3]).intValue();
		int precioUnitario = ((BigDecimal) resultados[4]).intValue();
		ProductoSucursal rsp = new ProductoSucursal(cantReorden, idProductoSuc, idSucursal2, precioUnitario, codProducto);		
		
        return rsp;
	}	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de LOS PROVEEDORES de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROVEEDOR
	 */
	
	public List<ProductoSucursal> darProductosSucursal (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductosSucursal ());
		System.out.println("Consulta: " + "SELECT * FROM " + pp.darTablaProductosSucursal ());
		List<ProductoSucursal> rsp = new LinkedList<>();
		List executeList = q.executeList();		
		
		for (Object obj : executeList)
		{
			Object[] resultados = (Object[]) obj;
			long idProductoSuc =  ((BigDecimal) resultados [0]).longValue ();
			String codProducto = (String) resultados [1];
			long idSucursal2 =  ((BigDecimal) resultados [2]).longValue ();
			int cantReorden = ((BigDecimal) resultados[3]).intValue();
			int precioUnitario = ((BigDecimal) resultados[4]).intValue();
			ProductoSucursal otro = new ProductoSucursal(cantReorden, idProductoSuc, idSucursal2, precioUnitario, codProducto);
			rsp.add(otro);
		}
		
		return rsp;	
	}	
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar los datos de la orden para culminarla.
	 * @param pm - El manejador de persistencia
	 * @param nit - El identificador del proveedor.
	 * @param calificacion - La nueva calificacion para el proveedor.
	 * @return El n�mero de tuplas modificadas
	 */
	public long actualizarOrden (PersistenceManager pm, long idOrden, String nuevoEstado, Timestamp fechaEntrega, int calificacion) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPedidosSucursal() + " SET estado = ?, fecha_entrega = ?, calificacion = ? WHERE id = ?");
	     q.setParameters(nuevoEstado, fechaEntrega, calificacion, idOrden);
	     return (long) q.executeUnique();            
	}	
}
