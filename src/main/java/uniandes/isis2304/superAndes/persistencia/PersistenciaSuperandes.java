package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import javafx.scene.control.TableSelectionModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.Existencias;
import uniandes.isis2304.superAndes.negocio.OrdenProducto;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.ProductoSucursal;
import uniandes.isis2304.superAndes.negocio.ProductosOrden;
import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.Proveen;
import uniandes.isis2304.superAndes.negocio.Carrito;


public class PersistenciaSuperandes {

	/**
	 * Clase para el manejador de persistencia del proyecto SuperAndes
	 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
	 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
	 * con la base de datos 
	 * @author Geovanny Andres Gonzalez
	 */

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/

	/**
	 * Logger para escribir la traza de la ejecución
	 */

	private static Logger log = Logger.getLogger(PersistenciaSuperandes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */

	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */

	private static PersistenciaSuperandes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */

	private PersistenceManagerFactory pmf;

	/** Clase con las consultas para las operaciones generales en la base de datos. */

	private SQLUtil sqlUtil;

	/** Clase con las consultas para las operaciones de la clase Proveedores. */

	private SQLProveedores sqlProveedores;

	private SQLProductosOrden sqlProductosOrden;

	private SQLOrdenProductos sqlOrdenProductos;	

	private SQLFacturas sqlFacturas;
	
	private SQLPromocion sqlPromocion;	

	private SQLExistencias sqlExistencias;
	
	private SQLVentasProducto sqlVentas;
	
	private SQLProductosSucursal sqlProductosSucursal;
	
	private SQLCarrito sqlCarrito;
  
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos.
	 * Comienza con el Sequence seguido de: Categorias, Producto, Proveedores, Proveen,
	 * Productos_Sucursal, Promociones, Supermercados, Sucursales, Ofrecen, Supermercado_Proveedores,
	 * Pedidos_Sucursal, Orden_Productos, Clientes, Facturas, Venta_Producto, Espacio_Acomodacion, 
	 * Existencias. 
	 */

	private List <String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaSuperAndes
	 */	

	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaSuperandes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");		
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add("Superandes_sequence");
		tablas.add("CATEGORIAS");
		tablas.add("PRODUCTOS");
		tablas.add("PROVEEDORES");
		tablas.add("PROVEEN");
		tablas.add("PRODUCTOS_SUCURSAL");
		tablas.add("PROMOCIONES");
		tablas.add("SUPERMERCADOS");
		tablas.add("SUCURSALES");
		tablas.add("OFRECEN");
		tablas.add("SUPERMERCADO_PROVEEDORES");
		tablas.add("PEDIDOS_SUCURSAL");
		tablas.add("ORDEN_PRODUCTOS");
		tablas.add("CLIENTES");
		tablas.add("FACTURAS");
		tablas.add("VENTAS_PRODUCTO");
		tablas.add("ESPACIO_ACOMODACION");
		tablas.add("EXISTENCIAS");	
		tablas.add("CARRITO_COMPRAS");
		tablas.add("CONTENIDO_CARRITO");
	}
	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */

	private PersistenciaSuperandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		System.out.println("Nombre de la unidad persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaSuperandes existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperandes ();
		}

		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaSuperandes existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperandes (tableConfig);
		}

		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */

	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */

	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}	

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		//TODO Hacer clases SQL
		sqlUtil = new SQLUtil(this);
		sqlProveedores = new SQLProveedores(this);
		sqlProductosOrden = new SQLProductosOrden(this);
		sqlProductosSucursal = new SQLProductosSucursal(this);
		sqlOrdenProductos = new SQLOrdenProductos(this);
		sqlFacturas = new SQLFacturas(this);
		sqlPromocion = new SQLPromocion(this);
		sqlExistencias = new SQLExistencias(this);
		sqlVentas = new SQLVentasProducto(this);
		sqlCarrito = new SQLCarrito(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqSuperAndes ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Categoria de parranderos
	 */
	public String darTablaCategoria ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Productos de parranderos
	 */
	public String darTablaProductos ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Proveedores de parranderos
	 */
	public String darTablaProveedores ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Proveen de parranderos
	 */
	public String darTablaProveen ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Productos_Sucursal de parranderos
	 */
	public String darTablaProductosSucursal ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Promociones de parranderos
	 */
	public String darTablaPromociones ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Supermercados de parranderos
	 */
	public String darTablaSupermercados ()
	{
		return tablas.get (7);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sucursales de parranderos
	 */
	public String darTablaSucursales ()
	{
		return tablas.get (8);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Ofrecen de parranderos
	 */
	public String darTablaOfrecen ()
	{
		return tablas.get (9);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Supermercado_Proveedores de parranderos
	 */
	public String darTablaSupermercadoProveedores ()
	{
		return tablas.get (10);
	}


	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Pedidos_Sucursal de parranderos
	 */
	public String darTablaPedidosSucursal ()
	{
		return tablas.get (11);
	}


	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Orden_Productos de parranderos
	 */
	public String darTablaOrdenProductos ()
	{
		return tablas.get (12);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Clientes de parranderos
	 */
	public String darTablaClientes ()
	{
		return tablas.get (13);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Facturas de parranderos
	 */
	public String darTablaFacturas ()
	{
		return tablas.get (14);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Ventas_Producto de parranderos
	 */
	public String darTablaVentasProducto ()
	{
		return tablas.get (15);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Espacio_Acomodacion de parranderos
	 */
	public String darTablaEspacioAcomodacion ()
	{
		return tablas.get (16);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Existencias de parranderos
	 */
	public String darTablaExistencias ()
	{
		return tablas.get (17);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Carrito_Compras de parranderos
	 */
	public String darTablaCarrito()
	{
		return tablas.get(18);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Contenido_Carrito de parranderos
	 */
	public String darTablaContenidoCarrito(){
		return tablas.get(19);
	}
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */

	private long nextval ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de SuperAndes
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos en el orden:
	 * CATEGORIAS, PRODUCTOS,
	 * PROVEEDORES, PROVEEN, PRODUCTOS_SUCURSAL, PROMOCIONES, SUPERMERCADOS, SUCURSALES, OFRECEN, SUPERMERCADO_PROVEEDORES,
	 * PEDIDOS_SUCURSAL, ORDEN_PRODUCTOS, CLIENTES, FACTURAS, VENTAS_PRODUCTO, ESPACIO_ACOMODACION, EXISTENCIAS
	 * 	 
	 * @return Numero de lineas eliminadas por cada tabla	 
	 */

	public long [] limpiarSuperAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long [] resp = sqlUtil.limpiarParranderos (pm);
			tx.commit ();
			log.info ("Borrada la base de datos");
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,};
		}

		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}
	/* ****************************************************************
	 * 			Métodos para manejar los CarritosCompras
	 *****************************************************************/
	public List<Carrito> darCarritosAbandonados(){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			System.out.println("Iniciando transaccion");
			tx.begin();
			
		}

		//Deja registro de los posibles errores que se presenten.
		catch(Exception e) {
			e.printStackTrace();
			log.error("Exception :" + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}

		//Hace Rollback de la transaccion y cierra la peticion.
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}

			pm.close();
		}       
	}

	/* ****************************************************************
	 * 			Métodos para manejar los PROVEEDORES
	 *****************************************************************/

	/**
	 * Agrega un nuevo proveedor en la base de datos
	 * @param nit Numero de identificacion tributaria del proveedor
	 * @param nombre Nombre del proveedor
	 * @param calificacion Calificacion asignada al proveedor
	 * @return El nuevo proveedor agregado a la base de datos.
	 */

	public Proveedor agregarProveedor(int nit, String nombre, int calificacion) {

		//Inicio de la transaccion.
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try
		{
			System.out.println("Iniciando transaccion");
			tx.begin();
			long tuplasInsertadas = sqlProveedores.agregarProveedor(pm, nombre, calificacion, nit);
			tx.commit(); //Guarda el proceso de la transaccion en la base de datos.
			System.out.println("Guardando transaccion");
			log.trace("Insercion del Proveedor con NIT: " + nit + ": " + tuplasInsertadas + " tuplas insertadas ");

			return new Proveedor(calificacion, nit, nombre);
		}

		//Deja registro de los posibles errores que se presenten.
		catch(Exception e) {
			e.printStackTrace();
			log.error("Exception :" + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}

		//Hace Rollback de la transaccion y cierra la peticion.
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}

			pm.close();
		}       
	}

	/**
	 * Elimina un proveedor en la base de datos.
	 * @param nit Numero de identificacion tributaria del proveedor
	 * @return El numero de tuplas eliminadas de la base de datos. -1 si hay errores.
	 */

	public long eliminarProveedor(int nit) {

		//Inicio de la transaccion.
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try
		{
			tx.begin();
			long tuplas = sqlProveedores.eliminarProveedor(pm, nit);
			tx.commit(); //Guarda el proceso de la transaccion en la base de datos.
			log.trace("Eliminacion del Proveedor con NIT: " + nit + ": " + tuplas + " tuplas eliminadas ");

			return tuplas;
		}

		//Deja registro de los posibles errores que se presenten.
		catch(Exception e) {
			log.error("Exception :" + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}

		//Hace Rollback de la transaccion y cierra la peticion.
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}

			pm.close();
		}       
	}

	/**
	 * Retorna el proveedor que posee el nit dado por parametro
	 * @param nit Nit del proveedor
	 * @return El proveedor existente en la base de datos con el nit dado por parametro
	 */

	public Proveedor darProveedor (int nit) {
		return (Proveedor) sqlProveedores.darProveedor(pmf.getPersistenceManager(), nit);
	}

	/**
	 * Método que consulta TODA LA INFORMACIÓN DE UN PROVEEDOR con el identificador dado. Incluye la información básica del bebedor,
	 * y los productos que este suministra
	 * @param nit - El identificador del bebedor
	 * @return El objeto PROVEEDOR, construido con base en las tuplas de la tablas PROVEEDORES, PROVEEN, PRODUCTOS
	 * relacionadas con el identificador de bebedor dado
	 */
	public Proveedor darProveedorCompleto (int nit) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Proveedor proveedor = (Proveedor)sqlProveedores.darProveedor(pm, nit);
		proveedor.setProductos(armarProductosSuministrados(sqlProveedores.darProductosOfrecidos(pm, nit)));
		return proveedor;
	}

	/**
	 * Método privado para generar las información completa de loss productos suministrados por un proveedor: 
	 * La información básica del producto suministrado, y la fecha y hora provistos en el formato esperado por los objetos BEBEDOR
	 * @param tuplas - Una lista de arreglos de 12 objetos, con la información del producto y de proveen, en el siguiente orden:
	 * productos.codigo, productos.nombre, productos.marca, productos.presentacion, productos.unidad_medida, productos.cantidad_en_presentacion, productos.especificacion_empacado, 
	   productos.categoria, proveen.id_proveedor, proveen.id_producto, proveen.precio_cu, proveen.precio_unidad_medida

	 * @return Una lista de arreglos de 2 objetos. El primero es un objeto PRODUCTO, el segundo corresponde a un objeto PROVEEN
	 * 
	 * Los valores de las columnas respuesta de la consulta siendo:
	 * productos.codigo, productos.nombre, productos.marca, productos.presentacion, productos.unidad_medida, productos.cantidad_en_presentacion, productos.especificacion_empacado, 
	   productos.categoria, proveen.id_proveedor, proveen.id_producto, proveen.precio_cu, proveen.precio_unidad_medida
	 */
	private List<Object []> armarProductosSuministrados (List<Object []> tuplas)
	{
		List<Object []> productosSuministrados = new LinkedList <Object []> ();
		for (Object [] tupla : tuplas)
		{
			Object [] productosSuministro = new Object [2];

			//Obtencion del objeto PRODUCTO
			String codigo = ((String) tupla [0]);
			String nombre = ((String) tupla [1]);
			String marca = ((String) tupla [2]);
			String presentacion = ((String) tupla [3]);
			String unidad_medida = ((String) tupla [4]);
			int cantidadPresentacion = ((int) tupla[5]);
			int especificacionEmpacado = ((int) tupla[6]);
			long categoria = ((long) tupla [7]);

			//TODO Asignar la categoria a buscar obteniendo la FK categoria (Numero).
			productosSuministro[0] = new Producto(codigo, cantidadPresentacion, marca, presentacion, especificacionEmpacado, unidad_medida, null);

			//Obtencion del objeto PROVEEN
			int id_proveedor = ((int) tupla[8]);
			String codigo_producto = ((String) tupla[9]);
			int precio_cu = ((int) tupla[10]);
			int precio_unidad_medida = ((int) tupla[11]);

			productosSuministro[1] = new Proveen(precio_cu, precio_unidad_medida, id_proveedor, codigo_producto);

			productosSuministrados.add (productosSuministro);
		}

		return productosSuministrados;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla PROVEEDORES
	 * @return La lista de objetos PROVEEDOR, construidos con base en las tuplas de la tabla PROVEEDORES
	 */

	public List<Proveedor> darProveedores ()
	{
		return sqlProveedores.darProveedores(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla PROVEEDORES
	 * @return La lista de objetos PROVEEDOR, construidos con base en las tuplas de la tabla PROVEEDORES
	 */

	public List<Proveedor> darProveedoresCalificacion(int calificacion)
	{
		return sqlProveedores.darProveedoresPorCalificacion(pmf.getPersistenceManager(), calificacion);
	}

	/**
	 * Cambia la calificacion de un proveedor en la base de datos
	 * @param nit Numero de identificacion tributaria del proveedor
	 * @param calificacion Nueva calificacion asignada al proveedor
	 * @return El numero de tuplas modificadas en la base de datos.
	 */

	public long cambiarCalificacionProveedor(int nit, int calificacion) {

		//Inicio de la transaccion.
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try
		{
			tx.begin();
			long tuplas = sqlProveedores.cambiarCalificacionProveedor(pm, nit, calificacion);
			tx.commit(); //Guarda el proceso de la transaccion en la base de datos.
			log.trace("Actualizacion de la calificacion del Proveedor con NIT: " + nit + ": " +  ": nueva calificacion: " + calificacion + " "+ tuplas + " tuplas modificadas ");

			return tuplas;        	
		}

		//Deja registro de los posibles errores que se presenten.
		catch(Exception e) {
			log.error("Exception :" + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}

		//Hace Rollback de la transaccion y cierra la peticion.
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}

			pm.close();
		}       
	}

	/**
	 * Método que elimima, de manera transaccional, un PROVEEDOR y los PRODUCTOS que suministra.
	 * Si el proveedor está referenciado en alguna otra relación, no se borra ni el proveedor NI los productos
	 * @param nit - El identificador del proveedor
	 * @return Un arreglo de dos números que representan el número de proveedores eliminados y 
	 * el número de productos eliminadas, respectivamente. [-1, -1] si ocurre alguna Excepción
	 */

	public long [] eliminarProveedorYProductos (int nit)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long [] resp = sqlProveedores.eliminarProveedorYProductos(pm, nit);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1, -1};
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/* ****************************************************************
	 * 			Métodos para manejar PEDIDOS_SUCURSAL
	 *****************************************************************/

	/**
	 * Método que consulta todas las tuplas en la tabla PEDIDOS_SUCURSAL
	 * @return La lista de objetos ORDEN_PEDIDO, construidos con base en las tuplas de la tabla PEDIDOS_SUCURSAL
	 */

	public List<OrdenProducto> darOrdenesSucursal()
	{
		return sqlOrdenProductos.darOrdenes(pmf.getPersistenceManager());		
	}

	public OrdenProducto darOrdenPorId (long idPedido)	
	{
		return sqlOrdenProductos.darOrden(pmf.getPersistenceManager(), idPedido);
	}

	public long eliminarOrdenSucursal (long idPedido)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlOrdenProductos.eliminarOrden(pm, idPedido);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}	

	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTOS_ORDEN
	 *****************************************************************/

	public List<ProductosOrden> darProductosOrden()
	{
		return sqlProductosOrden.darProductosOrden(pmf.getPersistenceManager());
	}

	public long eliminarProductosOrden (long idPedido)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductosOrden.eliminarProductosOrden(pm, idPedido);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}	

	public ProductosOrden darProductosOrdenPorId (long idPedido, String codProducto)	
	{
		return sqlProductosOrden.darProductoOrden(pmf.getPersistenceManager(), idPedido, codProducto);
	}

	/* ****************************************************************
	 * 			Requerimientos funcionales
	 *****************************************************************/

	/**
	 * Permite añadir una nueva orden de pedido de un producto a un proveedor
	 */	

	public long [] requerimientoFuncional9 (long rPedido, long idSucursal, int nitProveedor, Timestamp fechaEsperada, Timestamp fechaEntrega, int calificacion,
			String codProducto, int precioUnitario, int cantidad) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long idPedido = (rPedido >= 0) ? rPedido : nextval();
			long agregarPedido = sqlOrdenProductos.agregarOrden(pm, idPedido, idSucursal, nitProveedor, "EN_ESPERA", fechaEsperada, fechaEntrega, calificacion);
			long agregarProductosOrden = sqlProductosOrden.agregarProductosOrden(pm, idPedido, codProducto, precioUnitario, cantidad);
			tx.commit();
			return new long[] {idPedido, agregarPedido, agregarProductosOrden};
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1, -1, -1};
		}

		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}		
	} 

	public long [] requerimientoFuncional10 (long idPedido, long idSucursal, Timestamp fechaEntrega, int calificacion) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		
		try
		{
			tx.begin();			
			long actualizacionPedido = sqlOrdenProductos.actualizarOrden(pm, idPedido, "ENTREGADO", fechaEntrega, calificacion);
			long[] obtenerDatosConsulta = consultaReq10(pm, idPedido, idSucursal);
			long actualizarExistencias = sqlExistencias.actualizarExistencia(pm, obtenerDatosConsulta[0], obtenerDatosConsulta[1], (int) obtenerDatosConsulta[2]);
			tx.commit();
			return new long[] {actualizacionPedido, 1, actualizarExistencias};
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1, -1, -1};
		}

		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}		
	}	 

	private long[] consultaReq10(PersistenceManager pm, long idPedido, long idSucursal)
	{
		String consulta = "SELECT a.id_producto_suc, a.id_espacio_acomo, c.cantidad ";
		consulta += "FROM " + darTablaExistencias() + " a, " + darTablaEspacioAcomodacion() + " b, " + darTablaOrdenProductos() +  " c, " + darTablaProductosSucursal() + " d";
		consulta += " WHERE c.id_pedido = ? AND b.id_sucursal = ? AND a.id_espacio_acomo = b.id AND c.id_producto = d.id_producto AND a.id_producto_suc = d.id"; 
		Query q = pm.newQuery(SQL, consulta);
		q.setParameters(idPedido, idSucursal);

		Object result = q.executeUnique();        
		Object[] resultados = (Object[]) result;

		//Casteo - ruego a Dios Padre que sirva, ya estoy que no puedo del cansancio, llevo 6 horas con eso.
		long idProductoSuc =  ((BigDecimal) resultados [0]).longValue ();
		long idEspacio =  ((BigDecimal) resultados [1]).longValue ();		
		long cantidad = ((BigDecimal) resultados[2]).longValue();		      

		return new long[] {idProductoSuc, idEspacio, cantidad};
	}
	
	public List<Existencias> darExistencias()
	{
		return sqlExistencias.darExistencias(pmf.getPersistenceManager());
	}
	
	public long [] requerimientoFuncional11 (long idFactura, long idCliente, long idSucursal, String codProducto, int cantidad, Timestamp fecha) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		
		try
		{
			tx.begin();
			//Buscar el producto en la sucursal.
			ProductoSucursal p = sqlProductosSucursal.darProductoSuc(pm, codProducto, idSucursal);
			Object[] o = consultaReq11Promocion(pm, p.getIdProductoSucursal(), cantidad);			
			
			//Verificar promociones
			String tipo = (String) o[1];			
			if (!tipo.startsWith("-")) {
				int e = p.verificarPromo(tipo);
				cantidad = (e == -1) ? cantidad : e;
			}			
			
			int total = cantidad * p.getPrecioUnitario();
			long crearFactura = sqlFacturas.agregarFactura(pm, fecha, total, idFactura, idSucursal, idCliente);
			long venta = sqlVentas.agregarVenta(pm, p.getIdProductoSucursal(), idFactura, cantidad);
			tx.commit();
			return new long[] {crearFactura, venta};
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1, -1, -1};
		}

		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}		
	}	 

	private Object[] consultaReq11Promocion(PersistenceManager pm, long idProductoSuc, int cantidad)
	{
		String consulta = "select b.id ,b.tipo ";
		consulta += "FROM " + "PRODUCTOS_PROMOCION" + " a, " + darTablaPromociones() + " b";
		consulta += " WHERE a.ID_PRODUCTO_SUC = ? AND a.id_promocion = b.id AND b.unidades_disponibles > 0 AND b.fecha_inicio > b.fecha_final"; 
		Query q = pm.newQuery(SQL, consulta);
		q.setParameters(idProductoSuc);

		Object result = q.executeUnique();        
		Object[] resultados = (Object[]) result;
		
		long idPromocion = -1;
		long update = -1;
		String tipoPromo = "-";
		
		if (resultados != null && resultados.length > 0) {
  		 //Casteo - ruego a Dios Padre que sirva, ya estoy que no puedo del cansancio, llevo 6 horas con eso.			
		 idPromocion =  ((BigDecimal) resultados [0]).longValue ();
		 tipoPromo =  (String) resultados [1];
		 
		 String consulta2 = "UPDATE PROMOCIONES";
		 consulta2 += "SET UNIDADES_DISPONIBLES = UNIDADES_DISPONIBLES - ?";
		 consulta2 += " WHERE ID = ?";		 
		 
		 Query q2 = pm.newQuery(SQL, consulta2);
		 q2.setParameters(idPromocion, cantidad);
		 update = (long) q2.executeUnique();
		}
		
		return new Object[] {update, tipoPromo};
	}

	
	
	/** RFC1
	 * Método que consulta el dinero recolectado por cada sucursal
	 * @return La lista de parejas de objetos, construidos con base en las tuplas de la tabla FACTURAS. 
	 * El primer elemento de la pareja es el total del dinero recolectado; 
	 * el segundo elemento es el identificador de la sucursal que le corresponde
	 */
	public List<Object []> darDineroRecolectadoPorCadaSucursal (Timestamp fechaInicio, Timestamp fechaFin)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Object> tuplas = sqlFacturas.darTotalDineroRecolectado(pm, fechaInicio, fechaFin);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			int totalDinero = ((BigDecimal) datos [0]).intValue ();
			int idSucursal = ((BigDecimal) datos [1]).intValue ();

			Object [] resp = new Object [2];
			resp [0] = totalDinero;
			resp [1] = idSucursal;	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	
	/**
	 * RFC2
	 * Dar las 20 promociones mas populares
	 * Las promociones son aquellas que mas dinero vendieron.
	 */
	
	public List<Promocion> dar20PromocionesMasPopulares()
	{
		return sqlPromocion.dar20PromocionesMasPopulares(pmf.getPersistenceManager());
	}

	public long requerimientoFuncional12 (long idCliente, long idSucursal) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		
		try
		{
			tx.begin();
			//Buscar carrito disponible en la sucursal.
			Carrito c = sqlCarrito.darCarritoDisponible(pm, idSucursal);

			

			//asignar cliente al carrito
			long idCarro = c.getIdCarrito();		
			long asignar = sqlCarrito.asignarACliente(pm, idCliente, idCarro);
			
			
			tx.commit();
			return asignar;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}

		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}		
	}	 
	
}
