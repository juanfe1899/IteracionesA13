package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperandes;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROMOCION de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Geovanny Andres Gonzalez
 */

class SQLPromocion {

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
	
	public SQLPromocion (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Agrega una nueva promocion a la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param fechaInicio fehca en la que inicia la promocion
	 * @param fechaFinal fecha en la que termina la promocion
	 * @param tipo tipo (descripcion) de la promocion
	 * @param unidadesDisponibles unidades Disponibles de la promocion
	 * @param id identificador de dicha promocion
	 * @param nit NIT del proveedor, identificador unico
	 * @return El número de tuplas insertadas
	 */
	
	public long agregarPromocion(PersistenceManager pm, Timestamp fechaFinal, Timestamp fechaInicio, String tipo, int unidadesDisponibles, int id) {
		System.out.println("Crear Query de insercion");	
		Query qPromocion = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromociones() + "(id, fecha_inicio, fecha_final, tipo, udidades_disponibles) values (?,?,?,?,?)");
		qPromocion.setParameters(id, fechaInicio, fechaFinal, tipo, unidadesDisponibles);
		System.out.println("Inicio de ejecucion de Query" + "Consulta: " + "INSERT INTO " + pp.darTablaPromociones() + "(id, fecha_inicio, fecha_final, tipo, udidades_disponibles) values (?,?,?,?,?)");
		long tuplas = (long) qPromocion.executeUnique();
		System.out.println("[SQLPromocion] Tuplas modificadas: " + tuplas);
		return tuplas;
	}
	
	/**
	 * Elimina una promocion de la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param id identificador de la promocion a eliminar.
	 * @return El numero de tuplas eliminadas.
	 */
	
	public long eliminarPromocion(PersistenceManager pm, int id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromociones() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();           
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una promocion de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la promocion
	 * @return El objeto PROMOCION que tiene el identificador dado
	 */
	
	public Promocion darPromocion(PersistenceManager pm, int id) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromociones () + " WHERE id = ?");
        q.setParameters(id);
        q.setResultClass(Promocion.class);
        return (Promocion) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS PROMOCIONES de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Promocion
	 */
	
	public List<Promocion> darPromociones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromociones ());
		q.setResultClass(Promocion.class);
		List<Promocion> executeList = (List<Promocion>) q.executeList();		
		return executeList;
	}
	
}
