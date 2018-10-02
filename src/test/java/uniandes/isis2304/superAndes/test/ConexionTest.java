package uniandes.isis2304.superAndes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import uniandes.isis2304.superAndes.negocio.SuperAndes;

/**
 * Clase con metodos de prueba de conexion a la base de datos
 * @author Geovanny Andres Gonzalez
 *
 */

public class ConexionTest
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ConexionTest.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe, pero el esquema de la BD no se ha creado
	 */
	private static final String CONFIG_TABLAS_B = "./src/main/resources/config/TablasBD_B.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia NO existe
	 */
	private static final String CONFIG_TABLAS_ERR_DS = "./src/main/resources/config/TablasBD_ErrorDataStore.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia NO existe
	 */
	private static final String CONFIG_TABLAS_ERR_USER = "./src/main/resources/config/TablasBD_ErrorInvalidUser.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
	
    private JsonObject tableConfig;
    
	/**
	 * La clase que se quiere probar
	 */
    private SuperAndes superAndes;
	
	/* ****************************************************************
	 * 			Métodos de prueba de acceso a la BD
	 *****************************************************************/  
    /**
     * Método de prueba para acceso correcto a una base de datos
     */
    @Test
    public void normalAccessTest ()
  	{
  	  	try
		{
			log.info ("Probando el acceso a la base de datos con datos validos (BD, credenciales, esquema");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			log.info ("Conexión realizada correstamente");
			log.info ("Cerrando la conexión");
			
			superAndes.cerrarUnidadPersistencia ();
			log.info ("Conexión cerrada");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.info ("Prueba de acceso normal FALLA !!. La excepcion generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de acceso normal a la base de datos falló !! Revise persistence.xml.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
  	}
  	  
    /**
     * Metodo que prueba el intento de acceso a una base de datos inaccesible, por alguna de las siguientes causas:
     * 1. No existe la unidad de persistencia
     * 2. La unidad de persistencia esta caida
     */
    
    @Test
    public void baseDatosInaccesible ()
    {
		try
		{
	    	log.info ("Probando el acceso a la base de datos con una base de datos que no existe");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_ERR_DS));
			fail ("Debería fallar. La base de datos no existe !!");
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			log.info ("Prueba realizada exitosamente. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de base de datos inaccesible correcta.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			//System.out.println (msg);
		}
    }
    
    /**
     * Metodo que prueba el intento de acceso a una base de datos inaccesible, por causa:
     * 1. Credenciales de usuario invalidas (nombre de usuario / contrase�a)
     */
    
    @Test
    public void usuarioInvalidoTest ()
    {
		try
		{
	    	log.info ("Probando el acceso a la base de datos con datos de usuario incorrectos");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_ERR_USER));
			fail ("Debería fallar. Las credenciales del usuario no son válidas");
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			log.info ("Prueba realizada exitosamente. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de credenciales incorrectas correcta.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			//System.out.println (msg);
		}
    }

    /**
     * Metodo que prueba el intento de acceso a una base de datos inaccesible, por causa:
     * 1. El esquema no ha sido creado o es erroneo - Intentar acceder a una tabla inexistente
     */
    
    @Test
    public void tablaInexistenteTest ()
    {
    	// Probar primero la conexión a la base de datos
		try
		{
	    	log.info ("Probando el acceso a la base de datos con datos de usuario correctos, pero sin crear el esquema");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_B));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.info ("Prueba de tabla inexistente incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de tabla inexistente incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se puede probar si la tabla existe o no...
		try
		{
			superAndes.darProveedores();
			fail ("Debería fallar. La tabla consultada no existe en la BD");
		}
		
		catch (Exception e)
		{
			//e.printStackTrace();
			log.info ("Prueba de tabla inexistente correcta. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de tabla inexistente correcta.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
		}
		finally
		{
			superAndes.limpiarSuperAndes();
    		superAndes.cerrarUnidadPersistencia ();    		
		}
    }

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			log.info ("No se encontro un archivo de configuracion valido");			
			JOptionPane.showMessageDialog(null, "No se encontro un archivo de configuracion valido: ", "TipoBebidaTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
