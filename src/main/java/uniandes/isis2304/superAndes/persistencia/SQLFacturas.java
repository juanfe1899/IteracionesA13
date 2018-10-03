package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Factura;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperandes;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Factura de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Geovanny Andres Gonzalez
 */

class SQLFacturas {

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
	
	public SQLFacturas (PersistenciaSuperandes pp)
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
	
	public long agregarFactura(PersistenceManager pm, Timestamp fecha, int total, int id, int idSucursal, int idCliente) {
		System.out.println("Crear Query de insercion");	
		Query qFactura = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaFacturas() + "(id, fecha, total, id_cliente, id_sucursal) values (?,?,?,?,?)");
		qFactura.setParameters(id, fecha, total, idCliente, idSucursal);
		System.out.println("Inicio de ejecucion de Query" + "Consulta: " + "INSERT INTO " + pp.darTablaFacturas() + "(id, fecha, total, id_cliente, id_sucursal) values (?,?,?,?,?)");
		long tuplas = (long) qFactura.executeUnique();
		System.out.println("[SQLFactura] Tuplas modificadas: " + tuplas);
		return tuplas;
	}
	
	/**
	 * Elimina una Factura de la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param id identificador de la Factura a eliminar.
	 * @return El numero de tuplas eliminadas.
	 */
	
	public long eliminarFactura(PersistenceManager pm, int id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFacturas() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();           
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una Factura de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la Factura
	 * @return El objeto Factura que tiene el identificador dado
	 */
	
	public Factura darFactura(PersistenceManager pm, int id) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFacturas () + " WHERE id = ?");
        q.setParameters(id);
        q.setResultClass(Factura.class);
        return (Factura) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS Facturas de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Factura
	 */
	
	public List<Factura> darFacturaes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFacturas ());
		q.setResultClass(Factura.class);
		List<Factura> executeList = (List<Factura>) q.executeList();		
		return executeList;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información del total de dinero recolectado 
	 * por cada sucursal (dentro de lsa fechas dadas) de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio fecha desde la cual se quieren realizar las cuentas
	 * @param fehcaFin fecha hasta la cual se quieren realizar las cuentas
	 * @return Una lista de objetos Factura
	 */
	
	public List<Factura> darTotalDineroRecolectado (PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin)
	{
		Query q = pm.newQuery(SQL, "SELECT SUM(TOTAL), ID_SUCURSAL FROM " + pp.darTablaFacturas () + "WHERE FECHA BETWEEN ? AND ? 	GROUP BY ID_SUCURSAL");
		q.setResultClass(Factura.class);
		List<Factura> executeList = (List<Factura>) q.executeList();		
		return executeList;
	}
	
}
