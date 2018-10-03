package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Existencias;
import uniandes.isis2304.superAndes.negocio.OrdenProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto EXISTENCIAS de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Geovanny Andres Gonzalez
 */

class SQLExistencias {

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
	
	public SQLExistencias (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	
	/**
	 * Agregar una nuevo pedido en la base de datos.
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
	
	public long agregarExistencia(PersistenceManager pm, long idProductoSuc, long idEspacio, int cantidad) {
		Query qPedido = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaExistencias() + "(ID_PRODUCTO_SUC, ID_ESPACIO_ACOMO, CANTIDAD) VALUES (?,?,?)");		
		qPedido.setParameters(idProductoSuc, idEspacio, cantidad);
		long tuplas = (long) qPedido.executeUnique();
		return tuplas;
	}
	
	/**
	 * Elimina a un pedido
	 * @param pm the pm Manejador de persistencia
	 * @param id the id Identificador unico del pedido
	 * @return El numero de tuplas eliminadas.
	 */
	
	public long eliminarExistencia(PersistenceManager pm, long idProductoSuc, long idEspacio) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaExistencias () + " WHERE " + pp.darTablaExistencias ()+ ".ID_PRODUCTO_SUC = ? AND " + pp.darTablaExistencias ()+ ".ID_ESPACIO_ACOMO = ?");
        q.setParameters(idProductoSuc, idEspacio);
        System.out.println("Pasando a eliminar el pedido, Consulta: " + "DELETE FROM " + pp.darTablaExistencias () + " WHERE " + pp.darTablaExistencias ()+ ".ID_PRODUCTO_SUC = ? AND " + pp.darTablaExistencias ()+ ".ID_ESPACIO_ACOMO = ?");        
        return (long) q.executeUnique();           
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una orden de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id the id Identificador unico del pedido
	 * @return El objeto ORDEN_PRODUCTO a buscar.
	 */
	
	public Existencias darOrden (PersistenceManager pm, long idProductoSuc, long idEspacio) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaExistencias () + " WHERE " + pp.darTablaExistencias ()+ ".ID_PRODUCTO_SUC = ? AND " + pp.darTablaExistencias ()+ ".ID_ESPACIO_ACOMO = ?");
        q.setParameters(idProductoSuc, idEspacio);        
        q.setUnique(true);        
        Object result = q.executeUnique();        
        Object[] resultados = (Object[]) result;
        
        //Casteo - ruego a Dios Padre que sirva, ya estoy que no puedo del cansancio, llevo 6 horas con eso.
        long idProducto =  ((BigDecimal) resultados [0]).longValue ();
        long idEspacioR =  ((BigDecimal) resultados [1]).longValue ();        
		int cantidad = ((BigDecimal) resultados[2]).intValue();
		Existencias rsp = new Existencias(cantidad, idEspacioR, idProducto);        
        return rsp;
	}	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PROVEEDORES de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROVEEDOR
	 */
	
	public List<Existencias> darExistencias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaExistencias ());
		System.out.println("Consulta: " + "SELECT * FROM " + pp.darTablaExistencias ());
		List<Existencias> rsp = new LinkedList<>();
		List executeList = q.executeList();		
		
		for (Object obj : executeList)
		{
			Object [] datos = (Object []) obj;
			
			long idProducto =  ((BigDecimal) datos [0]).longValue ();
	        long idEspacioR =  ((BigDecimal) datos [1]).longValue ();        
			int cantidad = ((BigDecimal) datos[2]).intValue();
			
			rsp.add(new Existencias(cantidad, idEspacioR, idProducto));
		}
		
		return rsp;	
	}	
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar los datos de la orden para culminarla.
	 * @param pm - El manejador de persistencia
	 * @param nit - El identificador del proveedor.
	 * @param calificacion - La nueva calificacion para el proveedor.
	 * @return El número de tuplas modificadas
	 */
	public long actualizarExistencia (PersistenceManager pm, long idProductoSuc, long idEspacio, int nuevaCantidad) 
	{		
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaExistencias () + " SET CANTIDAD = CANTIDAD + ? WHERE ID_PRODUCTO_SUC = ? AND ID_ESPACIO_ACOMO = ?");
	     q.setParameters(nuevaCantidad, idProductoSuc, idEspacio);
	     return (long) q.executeUnique();            
	}	
}
