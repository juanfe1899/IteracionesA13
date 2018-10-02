package uniandes.isis2304.superAndes.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.Proveen;


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
}
