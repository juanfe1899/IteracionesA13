package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Sucursal;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperandes;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Sucursal de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Geovanny Andres Gonzalez
 */

class SQLSucursales {

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
	
	public SQLSucursales (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Agrega una nueva Sucursal a la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param id identificador de dicha Sucursal
	 * @param ciudad ciudad de la sucursal
	 * @param direccion direccion de la sucursal
	 * @param nombre nombre de con el que se identifica la sucursal
	 * @param nitSupermercado identificador del superMercado de dicha Sucursal
	 * @return El número de tuplas insertadas
	 */
	
	public long agregarSucursal(PersistenceManager pm, int id, String ciudad, String direccion, String nombre, int nitSuper) {
		System.out.println("Crear Query de insercion");	
		Query qSucursal = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursales() + "(id, ciudad, direccion, nombre, nit_supermercado) values (?,?,?,?,?)");
		qSucursal.setParameters(id, ciudad, direccion, nombre, nitSuper);
		System.out.println("Inicio de ejecucion de Query" + "Consulta: " + "INSERT INTO " + pp.darTablaSucursales() + "(id, fecha_inicio, fecha_final, tipo, udidades_disponibles) values (?,?,?,?,?)");
		long tuplas = (long) qSucursal.executeUnique();
		System.out.println("[SQLSucursal] Tuplas modificadas: " + tuplas);
		return tuplas;
	}
	
	/**
	 * Elimina una Sucursal de la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param id identificador de la Sucursal a eliminar.
	 * @return El numero de tuplas eliminadas.
	 */
	
	public long eliminarSucursal(PersistenceManager pm, int id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursales() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();           
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una Sucursal de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la Sucursal
	 * @return El objeto Sucursal que tiene el identificador dado
	 */
	
	public Sucursal darSucursal(PersistenceManager pm, int id) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursales () + " WHERE id = ?");
        q.setParameters(id);
        q.setResultClass(Sucursal.class);
        return (Sucursal) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS Sucursales de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Sucursal
	 */
	
	public List<Sucursal> darSucursales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursales ());
		q.setResultClass(Sucursal.class);
		List<Sucursal> executeList = (List<Sucursal>) q.executeList();		
		return executeList;
	}
	
}
