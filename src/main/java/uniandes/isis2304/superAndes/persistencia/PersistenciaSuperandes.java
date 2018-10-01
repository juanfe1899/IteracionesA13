package uniandes.isis2304.superAndes.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


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
	
	
	
	
	
	
}
