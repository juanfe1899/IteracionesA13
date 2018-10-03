package uniandes.isis2304.superAndes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.Existencias;
import uniandes.isis2304.superAndes.negocio.OrdenProducto;
import uniandes.isis2304.superAndes.negocio.ProductosOrden;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOOrdenProducto;
/**
 * Clase con los métdos de prueba de funcionalidad sobre TIPOBEBIDA
 * @author Germán Bravo
 *
 */
public class ReqFuncional_10
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ReqFuncional_10.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 
	
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
    private SuperAndes Superandes;
	
    /* ****************************************************************
	 * 		Metodos de prueba para verificar el requerimiento 9
	 *****************************************************************/
    
	/**
     * Metodo de prueba de la restriccion de unicidad sobre las tablas PEDIDOS_SUCURSAL y ORDEN_PEDIDO
     */
    
	@Test
	public void unicidadReqFuncional_10() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del nombre del tipo de bebida");
			Superandes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de tablas PEDIDO_SUCURSAL y ORDEN_PEDIDO incompleta. No se pudo conectar a la base de datos !!. La excepcion generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de tablas PEDIDO_SUCURSAL y ORDEN_PEDIDO incompleta. No se pudo conectar a la base de datos !!.";
			msg += "Revise el log de Superandes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		
		long idPedido = 1; //El pedido ya existe en la BD.		
		try
		{
			//Agregar dato inicial a la consulta.			
			long idSucursal = 1; //La sucursal ya existe en la BD.
			Date fEntrega = new Date("23/10/2018");
			Timestamp fechaEntrega = new Timestamp(fEntrega.getTime());
			int cantidadExistencias = 5;
			
			//Obtener las existencias anteriores
			Existencias ePre = Superandes.darExistencias().get(0);			
			
			long [] resultados = Superandes.requerimientoFuncional10(idPedido, idSucursal, fechaEntrega, cantidadExistencias);
			assertTrue("No se esperaba una excepcion ! resultados[0] " + resultados[0] + " resultados[1]" + resultados[1], resultados[0] > 0 && resultados[1] > 0 && resultados[1] > 0);
			
			//Verificar si el estado paso a 
			OrdenProducto orden = Superandes.darOrdenProducto(idPedido);			
			assertEquals ("La orden deberia tener un estado de entregado", "ENTREGADO", orden.getEstado());
			
			//Verificacion de cantidad en existencias.
			Existencias ePost = Superandes.darExistencias().get(0);
			int iPre = ePre.getCantidadProducto();
			int iPost = ePost.getCantidadProducto();
			assertTrue("La existencia no aumento, Existencias Pre-Operacion: " + iPre + " Existencias Post-Operacion: " + iPost, iPost > iPre);			
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			log.info ("Prueba de insercion repetida completa. La excepcion generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());
			String msg = "Prueba correcta se espera que debia fallar";			
			System.out.println (msg);    		
		}    				
		
		finally
		{			
    		Superandes.cerrarUnidadPersistencia ();    		
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
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "ReqFuncional_9", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
