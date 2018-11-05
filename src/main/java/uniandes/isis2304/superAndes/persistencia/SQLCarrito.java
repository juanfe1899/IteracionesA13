package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import java.math.BigDecimal;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;



import uniandes.isis2304.superAndes.negocio.Carrito;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperandes;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto Carrito de SuperAndes
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 * 
 * 
 */

class SQLCarrito {

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
	
	public SQLCarrito (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Agrega una nueva carrito a la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param total precio total en el carrito
	 * @param id identificador de dicho carrito
	 * @param idSucursal identificador de la sucursal que crea el carrito
	 * @param idCliente identificador del cliente al que se le hace el carrito
	 * @return El n�mero de tuplas insertadas
	 */
	
	public long agregarCarrito(PersistenceManager pm, int total, long id, long idSucursal, long idCliente) {
		System.out.println("Crear Query de insercion");	
		Query qCarrito = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarrito() + "(id, fecha, total, id_cliente, id_sucursal) values (?,?,?,?)");
		qCarrito.setParameters(id, total, idCliente, idSucursal);
		System.out.println("Inicio de ejecucion de Query" + "Consulta: " + "INSERT INTO " + pp.darTablaCarrito() + "(id, total, id_cliente, id_sucursal) values (?,?,?,?)");
		long tuplas = (long) qCarrito.executeUnique();
		System.out.println("[SQLCarrito] Tuplas modificadas: " + tuplas);
		return tuplas;
	}
	
	/**
	 * Elimina un Carrito de la base de datos
	 * @param pm Administrador de la unidad de persistencia
	 * @param id identificador del carrtio a eliminar.
	 * @return El numero de tuplas eliminadas.
	 */
	
	public long eliminarCarrito(PersistenceManager pm, int id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarrito() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();           
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de un carrito de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del carrito
	 * @return El objeto Carrito que tiene el identificador dado
	 */
	
	public Carrito darCarrito(PersistenceManager pm, int id) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito() + " WHERE id = ?");
        q.setParameters(id);

        Object result = q.executeUnique();        
        Object[] resultados = (Object[]) result;
        
        long idCarrito =  ((BigDecimal) resultados[0]).longValue ();
        int total = ((BigDecimal) resultados[1]).intValue();
        long idCliente =((BigDecimal) resultados[2]).longValue();
        long idSucursal = ((BigDecimal) resultados[3]).longValue ();
		Carrito rsp = new Carrito( idCarrito, total, idCliente, idSucursal);      
         
        return rsp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de los Carritos de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Carrito
	 */
	
	public List<Carrito> darCarrito (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito ());
		q.setResultClass(Carrito.class);
		List<Carrito> executeList = (List<Carrito>) q.executeList();		
		return executeList;
	}
	

    public Carrito darCarritoDisponible( PersistenceManager pm, long idSuc){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito() + " WHERE ID_SUCURSAL = ? AND ID_CLIENTE IS NULL AND ROWNUM = 1");
        q.setParameters(idSuc);

        Object result = q.executeUnique();        
        Object[] resultados = (Object[]) result;
        
        long idCarrito =  ((BigDecimal) resultados[0]).longValue ();
        int total = ((BigDecimal) resultados[1]).intValue();
        long idCliente =((BigDecimal) resultados[2]).longValue();
        long idSucursal = ((BigDecimal) resultados[3]).longValue ();
		Carrito rsp = new Carrito( idCarrito, total, idCliente, idSucursal);      
         
        return rsp;
	}

	public long asignarACliente (PersistenceManager pm, long pIdCliente, long id){
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCarrito() + " SET ID_CLIENTE = ? WHERE ID_CARRITO_COMPRA = ?");
        q.setParameters(pIdCliente, id);
        return (long) q.executeUnique();       
	}

	public void vaciarCarrito(PersistenceManager pm, long idCarrito){
		Query q = pm.newQuery(SQL, "SELECT ID_PRODUCTO_SUCURSAL FROM"+pp.darTablaContenidoCarrito()+"WHERE ID_CARRITO_COMPRAS ="+ idCarrito);
	}
	

	
	
}
