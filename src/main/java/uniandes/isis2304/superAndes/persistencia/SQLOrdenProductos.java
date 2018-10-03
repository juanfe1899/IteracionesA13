package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.OrdenProducto;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.VOOrdenProducto;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperandes;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROVEEDOR de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Geovanny Andres Gonzalez
 */

class SQLOrdenProductos {

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
	
	public SQLOrdenProductos (PersistenciaSuperandes pp)
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
	
	public long agregarOrden(PersistenceManager pm, long id ,long idSucursal, int nit ,String estado, Timestamp fechaEsperada, Timestamp fechaEntrega, int calificacion) {
		Query qPedido = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPedidosSucursal() + "(id, id_sucursal, id_proveedor, estado, fecha_esperada, fecha_entrega, calificacion) VALUES (?,?,?,?,?,?,?)");		
		qPedido.setParameters(id, idSucursal, nit, estado, fechaEsperada, fechaEntrega, calificacion);
		long tuplas = (long) qPedido.executeUnique();
		return tuplas;
	}
	
	/**
	 * Elimina a un pedido
	 * @param pm the pm Manejador de persistencia
	 * @param id the id Identificador unico del pedido
	 * @return El numero de tuplas eliminadas.
	 */
	
	public long eliminarOrden(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPedidosSucursal () + " WHERE " + pp.darTablaPedidosSucursal ()+ ".id = ?");
        q.setParameters(id);
        System.out.println("Pasando a eliminar el pedido, Consulta: " + "DELETE FROM " + pp.darTablaPedidosSucursal () + " WHERE " + pp.darTablaPedidosSucursal ()+ ".id = ?");
        System.out.println("Donde ID es :" + id);
        return (long) q.executeUnique();           
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una orden de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id the id Identificador unico del pedido
	 * @return El objeto ORDEN_PRODUCTO a buscar.
	 */
	
	public OrdenProducto darOrden (PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPedidosSucursal () + " WHERE id = ?");
        q.setParameters(id);        
        q.setUnique(true);
        
//        List result = (List) q.executeUnique();
//        Object[] resultados = (Object[])result.iterator().next();
//        //Casteo - ruego a Dios Padre que sirva, ya estoy que no puedo del cansancio, llevo 6 horas con eso.
//        long idOrden = (long) resultados[0];
//        String pEstado = (String) resultados[3];
//        Timestamp fechaEsperada = (Timestamp) resultados[4];
//        Timestamp fechaEntrega = (Timestamp) resultados[5];
//        int calificacion = (int) resultados[6];
//        
        OrdenProducto rsp = (OrdenProducto) q.executeResultUnique(OrdenProducto.class);
        return rsp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS ORDENES de la 
	 * base de datos de SuperAndes, por sucursal
	 * @param pm - El manejador de persistencia
	 * @param id_sucursa - Identificador de la sucursak.
	 * @return Una lista de objetos ORDEN_PRODUCTO pertenecientes a una sucursal.
	 */
	
	public List<OrdenProducto> darOrdenesPorSucursal (PersistenceManager pm, long id_sucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPedidosSucursal () + " WHERE id_sucursal = ?");
		q.setResultClass(OrdenProducto.class);
		q.setParameters(id_sucursal);
		return (List<OrdenProducto>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PROVEEDORES de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROVEEDOR
	 */
	
	public List<OrdenProducto> darOrdenes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPedidosSucursal ());
		System.out.println("Consulta: " + "SELECT * FROM " + pp.darTablaPedidosSucursal ());
		q.setResultClass(OrdenProducto.class);
		List<OrdenProducto> executeList = (List<OrdenProducto>) q.executeList();		
		return executeList;
	}	
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la calificacion de un proveedor en la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param nit - El identificador del proveedor.
	 * @param calificacion - La nueva calificacion para el proveedor.
	 * @return El número de tuplas modificadas
	 */
	public long cambiarEstadoOrden (PersistenceManager pm, long idOrden, String nuevoEstado) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProveedores () + " SET estado = ? WHERE id = ?");
	     q.setParameters(nuevoEstado, idOrden);
	     return (long) q.executeUnique();            
	}	
}
