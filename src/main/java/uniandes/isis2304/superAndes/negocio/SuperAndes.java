package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperandes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Geovanny Andres Gonzalez
 */
 
public class SuperAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci贸n
	 */
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperandes pp;
	
	/* ****************************************************************
	 * 			M茅todos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public SuperAndes ()
	{
		pp = PersistenciaSuperandes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public SuperAndes (JsonObject tableConfig)
	{
		pp = PersistenciaSuperandes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexion con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Metodos para manejar los PROVEEDORES
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un proveedor 
	 * Adiciona entradas al log de la aplicacion
	 * @param nit Numero de identificacion tributaria del proveedor
	 * @param nombre Nombre del proveedor
	 * @param calificacion Calificacion asignada al proveedor	 * 
	 * @return El objeto PROVEEDOR adicionado. null si ocurre alguna Excepcion
	 */
	
	public Proveedor adicionarProveedor (int nit, String nombre, int calificacion)
	{
        log.info ("Adicionando proveedor con NIT: " + nit);
        Proveedor proveedor = pp.agregarProveedor(nit, nombre, calificacion);
        log.info ("Adicionando proveedor" + proveedor);
        return proveedor;
	}	

	/**
	 * Elimina un proveedor por su identificador
	 * Adiciona entradas al log de la aplicacion
	 * @param nit - El identificador del proveedor a eliminar
	 * @return El numero de tuplas eliminadas
	 */
	
	public long eliminarProveedor (int nit)
	{
        log.info ("Eliminando bebedor por nit: " + nit);
        long resp = pp.eliminarProveedor(nit);
        log.info ("Eliminando bebedor por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un proveedor y su informacion basica, segun su identificador
	 * @param nit - El identificador del proveedor buscado
	 * @return Un objeto Proveedor que corresponde con el identificador buscado y lleno con su informacion basica
	 * 			null, si un bebedor con dicho identificador no existe
	 */
	
	public Proveedor darProveedor (int nit)
	{
        log.info ("Dar informaci贸n de un bebedor por id: " + nit);
        Proveedor buscado = pp.darProveedor(nit);
        log.info ("Buscando proveedor por NIT " + buscado != null ? buscado : "NO EXISTE");
        return buscado;
	}
	
	/**
	 * Encuentra un proveedor, su informacion basica y los productos suministrados 
	 * con las que esta directamente relacionado, segun su identificador
	 * @param nit - El identificador del bebedor buscado
	 * @return Un objeto Proveedor que corresponde con el identificador buscado y lleno con su informacion basica y 
	 * 		   y los productos suministrados
	 * 		   null, si un bebedor con dicho identificador no existe
	 */
	
	public Proveedor darProveedorCompleto (int nit)
	{
        log.info ("Dar informaci贸n COMPLETA de un bebedor por id: " + nit);
        Proveedor proveedor = pp.darProveedorCompleto(nit);
        log.info ("Buscando proveedor por Id: " + proveedor.toStringCompleto() != null ? proveedor : "NO EXISTE");
        return proveedor;
	}

	/**
	 * Encuentra todos los proveedores en SuperAndes
	 * Adiciona entradas al log de la aplicacion
	 * @return Una lista de objetos Proveedor con todos las proveedores que conoce la aplicacion, llenos con su informacion basica
	 */
	
	public List<Proveedor> darProveedores ()
	{
        log.info ("Listando proveedores");
        List<Proveedor> bebedores = pp.darProveedores();	
        log.info ("Listando proveedores: " + bebedores.size() + " proveedores existentes");
        return bebedores;
	}
	
	/**
	 * Encuentra todos los proveedores en SuperAndes
	 * Adiciona entradas al log de la aplicacion
	 * @return Una lista de objetos VOProveedor con todos los proveedores que conoce la aplicacion, llenos con su informacion basica
	 */
	
	public List<VOProveedor> darVOProveedores ()
	{
        log.info ("Generando los VO de Proveedor");
        List<VOProveedor> voProveedores = new LinkedList<> ();
        List<Proveedor> lista = pp.darProveedores();
        for (int i = 0; i < lista.size(); i++)
        {        	
        	voProveedores.add (lista.get(i));
        }
       
       log.info ("Generando los VO de Proveedores: " + voProveedores.size() + " proveedores existentes");
       return voProveedores;
	}	
	
	/**
	 * Cambia la calificacion de un proveedor dado su identificador
	 * Adiciona entradas al log de la aplicacion
	 * @param nit - El identificador del proveedor que va a cambiar de calificacion
	 * @param calificacion - La nueva calificacion
	 * @return El numero de tuplas modificadas: 1 o 0. 0 significa que un bebedor con ese identificador no existe
	 */
	
	public long cambiarCalificacionProveedor (int nit, int calificacion)
	{
        log.info ("Cambiando calificacion de proveedor con NIT: " + nit);
        long cambios = pp.cambiarCalificacionProveedor(nit, calificacion);
        return cambios;
	}
	
	/**
	 * Elimina un proveedor y los productos suministrados: 
	 * En caso que el bebedor este referenciado por otra relacion, NO SE BORRA NI EL PROVEEDOR, NI SUS PRODUCTOS
	 * Adiciona entradas al log de la aplicacion
	 * @param nit - El nit del proveedor que se quiere eliminar
	 * @return Una pareja de numeros [numero de proveedores eliminados, numero de productos eliminados]
	 */
	
	public long [] eliminarProveedorYProductos (int nit)
	{
        log.info ("Eliminando proveedor con NIT con sus productos: " + nit);
        long [] resp = pp.eliminarProveedorYProductos(nit);
        log.info ("Eliminando proveedor con sus productos: " + resp [0] + " proveedor y " + resp [1] + " productos");
        return resp;
	}
	
	/* ****************************************************************
	 * 			Metodos para la tabla PEDIDOS_SUCURSAL
	 *****************************************************************/
	
	/**
	 * Elimina un proveedor por su identificador
	 * Adiciona entradas al log de la aplicacion
	 * @param nit - El identificador del proveedor a eliminar
	 * @return El numero de tuplas eliminadas
	 */
	
	public long eliminarOrdenPedido (long idOrden)
	{
        log.info ("Eliminando orden de pedido con ID: " + idOrden);
        long resp = pp.eliminarOrdenSucursal(idOrden);
        log.info ("Eliminando orden de pedido con ID: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra un proveedor y su informacion basica, segun su identificador
	 * @param nit - El identificador del proveedor buscado
	 * @return Un objeto Proveedor que corresponde con el identificador buscado y lleno con su informacion basica
	 * 			null, si un bebedor con dicho identificador no existe
	 */
	
	public OrdenProducto darOrdenProducto (long idOrden)
	{
        log.info ("Dar orden de pedido con id: " + idOrden);
        OrdenProducto buscado = pp.darOrdenPorId(idOrden);
        log.info ("Buscando orden de pedido: " + buscado != null ? buscado : "NO EXISTE");
        return buscado;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos y los devuelve como una lista de VOTipoBebida
	 * Adiciona entradas al log de la aplicaci髇
	 * @return Una lista de objetos VOTipoBebida con todos los tipos de bebida que conoce la aplicaci髇, llenos con su informaci髇 b醩ica
	 */
	public List<VOOrdenProducto> darVOOrdenes ()
	{
		log.info ("Generando los VO de Tipos de bebida");        
        List<VOOrdenProducto> voTipos = new LinkedList<VOOrdenProducto> ();
        for (VOOrdenProducto tb : pp.darOrdenesSucursal())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	public List<OrdenProducto> darOrdenes ()
	{
		return pp.darOrdenesSucursal();
	}
	
	/* ****************************************************************
	 * 			Metodos para la tabla ORDEN_PRODUCTOS
	 *****************************************************************/
	
	public long eliminarProductosPedido (long idOrden)
	{
        log.info ("Eliminando orden de pedido con ID: " + idOrden);
        long resp = pp.eliminarProductosOrden(idOrden);
        log.info ("Eliminando orden de pedido con ID: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra un proveedor y su informacion basica, segun su identificador
	 * @param nit - El identificador del proveedor buscado
	 * @return Un objeto Proveedor que corresponde con el identificador buscado y lleno con su informacion basica
	 * 			null, si un bebedor con dicho identificador no existe
	 */
	
	public ProductosOrden darProductoOrden (long idOrden, String codProducto)
	{
        log.info ("Dar orden de pedido con id: " + idOrden);
        ProductosOrden buscado = pp.darProductosOrdenPorId(idOrden, codProducto);
        log.info ("Buscando orden de pedido: " + buscado != null ? buscado : "NO EXISTE");
        return buscado;
	}
	
	public List<ProductosOrden> darProductosOrdenes()
	{
		return pp.darProductosOrden();
	}
	
	public List<Existencias> darExistencias()
	{
		return pp.darExistencias();
	}
	
	/* ****************************************************************
	 * 			M茅todos para administraci贸n
	 *****************************************************************/

	public long [] requerimientoFuncional9 (long rPedido, long idSucursal, int nitProveedor, Timestamp fechaEsperada, Timestamp fechaEntrega, int calificacion,
			String codProducto, int precioUnitario, int cantidad)
	{
		log.info ("Agregando una nueva orden de pedido del producto: " + codProducto + " para la sucursal :" + idSucursal + " entregada por el proveedor :" + nitProveedor);
        long [] resp = pp.requerimientoFuncional9(rPedido, idSucursal, nitProveedor, fechaEsperada, fechaEntrega, calificacion, codProducto, precioUnitario, cantidad);        		
        log.info ("Pedido agregado: " + resp [0] + " Productos encargados " + resp [1]);
        return resp;
	}
	
	public long [] requerimientoFuncional10 (long idPedido, long idSucursal, Timestamp fechaEntrega, int calificacion)
	{
		log.info ("Cumpliendo pedido del producto: " + idPedido + " para la sucursal :" + idSucursal);
        long [] resp = pp.requerimientoFuncional10(idPedido, idSucursal, fechaEntrega, calificacion);        		
        log.info ("Actualizacion pedido: " + resp [0] + " Actualizacion existencias" + resp [2]);
        return resp;
	}
	
	public long[] requerimientoFuncional11(long idFactura, long idCliente, long idSucursal, String codProducto, int cantidad, Timestamp fecha)
	{
		long[] resp = pp.requerimientoFuncional11(idFactura, idCliente, idSucursal, codProducto, cantidad, fecha);
		return resp;
	}
	
	public List<Promocion> requerimientoConsulta2()
	{
		return pp.dar20PromocionesMasPopulares();
	}
	
	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de SuperAndes
	 * @return Un arreglo con 17 numeros que indican el numero de tuplas borradas en las tablas:
	 * CATEGORIAS, PRODUCTOS,
	 * PROVEEDORES, PROVEEN, PRODUCTOS_SUCURSAL, PROMOCIONES, SUPERMERCADOS, SUCURSALES, OFRECEN, SUPERMERCADO_PROVEEDORES,
	 * PEDIDOS_SUCURSAL, ORDEN_PRODUCTOS, CLIENTES, FACTURAS, VENTAS_PRODUCTO, ESPACIO_ACOMODACION, EXISTENCIAS
	 */
	
	public long [] limpiarSuperAndes ()
	{
        log.info ("Limpiando la BD de SuperAndes");
        long [] borrrados = pp.limpiarSuperAndes();	
        log.info ("Limpiando la BD de SuperAndes: Listo!");
        return borrrados;
	}
}
