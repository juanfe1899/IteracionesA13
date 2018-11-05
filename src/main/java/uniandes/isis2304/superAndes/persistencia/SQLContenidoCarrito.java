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

class SQLContenidoCarrito {

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
	
	public SQLContenidoCarrito(PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}


	public void vaciarCarrito(PersistenceManager pm, long idCarrito){
		Query q = pm.newQuery(SQL, "SELECT * FROM"+pp.darTablaContenidoCarrito()+"WHERE ID_CARRITO_COMPRAS ="+ idCarrito);

		Object result = q.executeUnique();
		Object[] resultados = (Object[]) result;
		long idProducto = ((BigDecimal) resultados[0]).longValue();
		Query q2= pm.newQuery(SQL, "SELECT * FROM"+ pp.darTablaProductosSucursal()+"WHERE ID = "+idProducto);
		Object result2 = q.executeUnique();
		Object[] resultados2 = (Object[] )result2;

		


	}
	
	
	
}
