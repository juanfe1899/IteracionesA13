package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.OrdenProducto;
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
		Query qPromocion = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromociones() + "(id, fecha_inicio, fecha_final, tipo, UNIDADES_DISPONIBLES) values (?,?,?,?,?)");
		qPromocion.setParameters(id, fechaInicio, fechaFinal, tipo, unidadesDisponibles);
		System.out.println("Inicio de ejecucion de Query" + "Consulta: " + "INSERT INTO " + pp.darTablaPromociones() + "(id, fecha_inicio, fecha_final, tipo, UNIDADES_DISPONIBLES) values (?,?,?,?,?)");
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
        
        Object result = q.executeUnique();        
        Object[] resultados = (Object[]) result;
        
        //Casteo - ruego a Dios Padre que sirva, ya estoy que no puedo del cansancio, llevo 6 horas con eso.
        long idPromo =  ((BigDecimal) resultados [0]).longValue ();		
		Timestamp fechaInicio = (Timestamp) resultados [1];
		Timestamp fechaFinal = (Timestamp) resultados [2];
		String tipo = (String)resultados[3];
		int unidades = ((BigDecimal)resultados[4]).intValue();
		
		Promocion promo = new Promocion();
		promo.setFechaInicio(fechaInicio);
		promo.setFechaFinal(fechaFinal);
		promo.setIdPromocion(idPromo);
		promo.setTipoPromocion(tipo);
		promo.setUnidadesDisponibles(unidades);
         
        return promo;
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
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS PROMOCIONES de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Promocion
	 */
	
	public List<Promocion> dar20PromocionesMasPopulares (PersistenceManager pm)
	{
		String consulta = "SELECT * FROM (";
		consulta += "SELECT pr.id_producto_suc, pr.id_promocion, SUM(d.precio_cu) AS DINERO, SUM(d.cantidad) AS VENDIDO";
		consulta += " FROM productos_promocion pr, (";
		consulta += " SELECT p.id, p.precio_cu, v.cantidad";
		consulta += " FROM productos_sucursal p, ventas_producto v";
		consulta += " WHERE v.id_producto_suc = p.id) d";
		consulta += " WHERE pr.id_producto_suc = d.id";
		consulta += " GROUP BY pr.id_producto_suc, pr.id_promocion";
		consulta += ")";
		consulta += " ORDER BY dinero ASC";
		
		Query q = pm.newQuery(SQL, consulta);
		List<Promocion> promociones = new LinkedList<>();
		List executeList = q.executeList();
		
		for (Object obj : executeList)
		{
			Object [] datos = (Object []) obj;
			int idPromocion = ((BigDecimal)datos[1]).intValue();
			Promocion promo = darPromocion(pm, idPromocion);
			promociones.add(promo);
		}	
		return promociones;
	}	
}
