-- Requerimiento de consulta 2

select *
from (
     select pr.id_producto_suc, pr.id_promocion, SUM(d.precio_cu) AS DINERO, SUM(d.cantidad) AS VENDIDO
     from productos_promocion pr,
                                (select p.id, p.precio_cu, v.cantidad
                                from productos_sucursal p, ventas_producto v
                                where v.id_producto_suc = p.id) d
     where pr.id_producto_suc = d.id
     group by pr.id_producto_suc, pr.id_promocion
     )
order by dinero ASC

/* Requerimiento 10 */

--Buscar en producto en la BD y cambiar el estado a entregado.
UPDATE PEDIDOS_SUCURSAL
SET ESTADO = 'ENTREGADO', FECHA_ENTREGA = ?, CALIFICACION = ? --La fecha de entrega y valor de calificacion.
WHERE ID = ? -- Lo que llegue por parametro

--Busca la existencia y la cantidad a sumar
select a.id_producto_suc, a.id_espacio_acomo, c.cantidad
from existencias a, espacio_acomodacion b, orden_productos c, productos_sucursal d
where c.id_pedido = ? AND b.id_sucursal = ? AND a.id_espacio_acomo = b.id AND c.id_producto = d.id_producto AND a.id_producto_suc = d.id

--Actualizar existencias
UPDATE existencias
SET CANTIDAD = CANTIDAD + ?
where ID_PRODUCTO_SUC = ? AND ID_ESPACIO_ACOMO = ?


/* Requerimiento 11 */
select c.id, c.precio_cu --Se necesita el producto de la sucursal que cumple el requerimiento
from espacio_acomodacion a, existencias b, productos_sucursal c
where a.id = b.id_espacio_acomo AND b.id_producto_suc = c.id -- Y los datos de entrada a.sucursal  = ? y c.idProducto = ?

--Mirar si hay promocion.
select b.id ,b.tipo --Que tipo de promocion se va a aplicar y el id para actualizar unidades
from productos_promocion a, promociones b
where a.id_promocion = b.id AND b.unidades_disponibles > 0 AND b.fecha_inicio > b.fecha_final --La promocion sigue disponible

update promociones p
set p.unidades_disponibles = p.unidades_disponibles - cantidad
where p.id = ? --Actualizar unidades disponibles

--Con el tipo de la promocion y el costo obtenido por producto mirar cuanto cobrar.

--Crear factura
INSERT INTO FACTURA(ID, FECHA, TOTAL,ID_CLIENTE, ID_SUCURSAL) VALUES (?,?,?,?,?); --Valores ya obtenidos.

-- Dejar record de la venta del producto
INSERT INTO ventas_producto (ID_PRODUCTO_SUC, ID_FACTURA, CANTIDAD) VALUES (?,?,?) -- Los valores necesarioa

