package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Factura;
import uniandes.isis2304.superAndes.negocio.OrdenProducto;
import uniandes.isis2304.superAndes.negocio.VentaProducto;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperandes;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto VENTAS_PRODUCTO de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Geovanny Andres Gonzalez
 */

class SQLVentasProducto {

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
	
	public SQLVentasProducto (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Agrega una nueva factura a la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param fecha fehca en la que se realiza la factura
	 * @param total precio total de la factura
	 * @param id identificador de dicha factura
	 * @param idSucursal identificador de la sucursal que crea la factura
	 * @param idCliente identificador del cliente al que se le hace la factura
	 * @return El número de tuplas insertadas
	 */
	
	public long agregarVenta(PersistenceManager pm, long idProductoSuc, long idFactura, int cantidad) {
		Query qFactura = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVentasProducto() + "(ID_PRODUCTO_SUC, ID_FACTURA, CANTIDAD) values (?,?,?)");
		qFactura.setParameters(idProductoSuc, idFactura, cantidad);		
		long tuplas = (long) qFactura.executeUnique();		
		return tuplas;
	}
	
	/**
	 * Elimina una Factura de la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param id identificador de la Factura a eliminar.
	 * @return El numero de tuplas eliminadas.
	 */
	
	public long eliminarVenta(PersistenceManager pm, int idFactura) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVentasProducto() + " WHERE ID_FACTURA = ?");
        q.setParameters(idFactura);
        return (long) q.executeUnique();           
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una Factura de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la Factura
	 * @return El objeto Factura que tiene el identificador dado
	 */
	
	public VentaProducto darVenta(PersistenceManager pm, int id) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVentasProducto () + " WHERE id = ?");
        q.setParameters(id);        
        
        Object result = q.executeUnique();        
        Object[] resultados = (Object[]) result;
        
        //Casteo - ruego a Dios Padre que sirva, ya estoy que no puedo del cansancio, llevo 6 horas con eso.
        long idProductoSuc =  ((BigDecimal) resultados[0]).longValue ();
        int idFactura =  ((BigDecimal) resultados[1]).intValue ();
        int cantidad = ((BigDecimal) resultados[2]).intValue();		
		VentaProducto rsp = new VentaProducto(idFactura, cantidad, idProductoSuc);      
         
        return rsp;        
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS Facturas de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Factura
	 */
	
	public List<VentaProducto> darVentas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFacturas ());
		q.setResultClass(Factura.class);
		List<VentaProducto> rsp = new LinkedList<>();
		List executeList = q.executeList();		
		
		for (Object obj : executeList)
		{
			Object [] datos = (Object []) obj;
			long idProductoSuc =  ((BigDecimal) datos[0]).longValue ();
	        int idFactura =  ((BigDecimal) datos[1]).intValue ();
	        int cantidad = ((BigDecimal) datos[2]).intValue();		
			VentaProducto otro = new VentaProducto(idFactura, cantidad, idProductoSuc);
			rsp.add(otro);
		}
		
		return rsp;
	}	
}
