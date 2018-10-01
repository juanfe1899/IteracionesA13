/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los metodos que hacen acceso a la base de datos para acceder desde el secuenciador y
 * limpiar la base de datos
 * 
 * @author Geovanny Andres Gonzalez
 */

class SQLUtil
{
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
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUtil (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqSuperAndes() + ".nextval FROM DUAL");
		q.setResultClass(Long.class);
		long resp = (long) q.executeUnique();
		return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 17 numeros que indican el numero de tuplas borradas en las tablas CATEGORIAS, PRODUCTOS
	 * PROVEEDORES, PROVEEN, PRODUCTOS_SUCURSAL, PROMOCIONES, SUPERMERCADOS, SUCURSALES, OFRECEN, SUPERMERCADO_PROVEEDORES,
	 * PEDIDOS_SUCURSAL, ORDEN_PRODUCTOS, CLIENTES, FACTURAS, VENTAS_PRODUCTO, ESPACIO_ACOMODACION, EXISTENCIAS
	 */

	public long [] limpiarParranderos (PersistenceManager pm)
	{		
		Query qCategorias = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoria());
		Query qProductos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductos());
		Query qProveedores = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedores());
		Query qProveen = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveen());
		Query qProductosSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosSucursal());
		Query qPromociones = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromociones());
		Query qSupermercados = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSupermercados());
		Query qSucursales = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursales());
		Query qOfrecen = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOfrecen());
		Query qSupermercadoProveedores = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSupermercadoProveedores());
		Query qPedidosSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPedidosSucursal());
		Query qOrdenProductos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenProductos());
		Query qClientes = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaClientes());
		Query qFacturas = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFacturas());
		Query qVentaProductos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVentasProducto());
		Query qEspacioAcomodacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEspacioAcomodacion());
		Query qExistencias = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaExistencias());

		long lCategoria = (long)qCategorias.executeUnique();
		long lProducto = (long)qProductos.executeUnique();
		long lProveedores = (long)qProveedores.executeUnique();
		long lProveen = (long)qProveen.executeUnique();
		long lProductosSucursal = (long)qProductosSucursal.executeUnique();
		long lPromociones = (long)qPromociones.executeUnique();
		long lSupermercados = (long)qSupermercados.executeUnique();
		long lSucursales = (long)qSucursales.executeUnique();
		long lOfrecen = (long)qOfrecen.executeUnique();
		long lSupermercadoProveedores = (long)qSupermercadoProveedores.executeUnique();
		long lPedidosSucursal = (long)qPedidosSucursal.executeUnique();
		long lOrdenProductos = (long)qOrdenProductos.executeUnique();
		long lClientes = (long)qClientes.executeUnique();
		long lFacturas = (long)qFacturas.executeUnique();
		long lVentaProductos = (long)qVentaProductos.executeUnique();
		long lEspacioAcomodacion = (long)qEspacioAcomodacion.executeUnique();
		long lExistencias = (long)qExistencias.executeUnique();

		return new long[] {lProductosSucursal,lPromociones,lSupermercados,lSucursales,lOfrecen, 
				lSupermercadoProveedores,lPedidosSucursal,lOrdenProductos,lClientes,lFacturas, lVentaProductos,
				lEspacioAcomodacion, lExistencias};
	}
}
