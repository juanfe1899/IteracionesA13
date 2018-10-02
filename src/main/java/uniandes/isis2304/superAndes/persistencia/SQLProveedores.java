package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperandes;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROVEEDOR de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Geovanny Andres Gonzalez
 */

class SQLProveedores {

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
	
	public SQLProveedores (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Agrega un nuevo proveedor a la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param nombre Nombre del proveedor
	 * @param calificacion Calificacion del proveedor
	 * @param nit NIT del proveedor, identificador unico
	 * @return El número de tuplas insertadas
	 */
	
	public long agregarProveedor(PersistenceManager pm, String nombre, int calificacion, int nit) {
		Query qProveedor = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedores() + "(nit, nombre, calificacion) values (?,?,?)");
		qProveedor.setParameters(nit, nombre, calificacion);
		return (long) qProveedor.executeUnique();
	}
	
	/**
	 * Elimina a un proveedor de la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param nit NIT del proveedor a eliminar. Recordar que el NIT es el numero unico de identificador para cada tupla proveedor
	 * @return El numero de tuplas eliminadas.
	 */
	
	public long eliminarProveedor(PersistenceManager pm, int nit) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedores () + " WHERE nit = ?");
        q.setParameters(nit);
        return (long) q.executeUnique();           
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un proveedor de la 
	 * base de datos de SuperAndes, por su NIT
	 * @param pm - El manejador de persistencia
	 * @param NIT - El identificador del proveedor
	 * @return El objeto PROVEEDOR que tiene el identificador dado
	 */
	
	public Proveedor darProveedor(PersistenceManager pm, int nit) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedores () + " WHERE nit = ?");
        q.setParameters(nit);
        q.setResultClass(Proveedor.class);
        return (Proveedor) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PROVEEDORES de la 
	 * base de datos de SuperAndes, por calificacion
	 * @param pm - El manejador de persistencia
	 * @param calificacion - La calificacion del proveedor.
	 * @return Una lista de objetos PROVEEDOR que tienen la calificacion dada.
	 */
	
	public List<Proveedor> darProveedoresPorCalificacion (PersistenceManager pm, int calificacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedores () + " WHERE calificacion = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(calificacion);
		return (List<Proveedor>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PROVEEDORES de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROVEEDOR
	 */
	
	public List<Proveedor> darProveedores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedores ());
		q.setResultClass(Proveedor.class);
		List<Proveedor> executeList = (List<Proveedor>) q.executeList();
		return executeList;
	}
	
	/**
	 * Permite devolver los productos ofrecidos por un proveedor
	 * @param pm Manejador de la persistencia
	 * @param nit Identificador del proveedor
	 * @return Los valores de las columnas respuesta de la consulta siendo:
	 * productos.codigo, productos.nombre, productos.marca, productos.presentacion, productos.unidad_medida, productos.cantidad_en_presentacion, productos.especificacion_empacado, 
	   productos.categoria, proveen.id_proveedor, proveen.id_producto, proveen.precio_cu, proveen.precio_unidad_medida	
	 */
	
	public List<Object[]> darProductosOfrecidos (PersistenceManager pm, int nit) {
		String sql = "SELECT productos.codigo, productos.nombre, productos.marca, productos.presentacion," + 
					 "productos.unidad_medida, productos.cantidad_en_presentacion, productos.especificacion_empacado," + 
					 "productos.categoria, proveen.id_proveedor, proveen.id_producto, proveen.precio_cu, proveen.precio_unidad_medida"; 
		sql += " FROM ";
		sql += pp.darTablaProveedores() + ",";
		sql += pp.darTablaProductos() + ",";
		sql += pp.darTablaProveen();
		sql += " WHERE ";
		sql += "proveedores.id = ?";
		sql += " AND proveedores.nit = proveen.id_proveedor";
		sql += " AND proveen.id_producto = productos.codigo";
		Query consulta = pm.newQuery(SQL, sql);
		consulta.setParameters(nit);
		return consulta.executeList();		
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
	public long cambiarCalificacionProveedor (PersistenceManager pm, int nit, int calificacion) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProveedores () + " SET calificacion = ? WHERE nit = ?");
	     q.setParameters(calificacion, nit);
	     return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para:
	 * Eliminar un proveedor y los productos que este ofrezca 
	 * En caso que el proveedor esté referenciado por otra relación, NO SE BORRA NI EL PROVEEDOR, NI SUS PRODUCTOS
	 * Adiciona entradas al log de la aplicación
	 * @param pm - El manejador de persistencia
	 * @param nit - El identificador del proveedor que se quiere eliminar
	 * @return Una pareja de números [número de proveedores eliminados, número de productos eliminadas]
	 */
	
	public long [] eliminarProveedorYProductos (PersistenceManager pm, int nit) 
	{
      Query q1 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedores () + " WHERE nit = ?");
      q1.setParameters(nit);
      Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveen () + " WHERE id_proveedor = ?");
      q2.setParameters(nit);
      
      long productosEliminados = (long) q1.executeUnique ();
      long proveedoresEliminados = (long) q2.executeUnique ();
      return new long[] {proveedoresEliminados, productosEliminados};
	}
}
