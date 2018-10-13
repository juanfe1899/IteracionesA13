 --Requerimiento #1
/* Creacion de un Proveedor en la base de datos
   La relacion de un proveedor y sus productos requiere de
   la existencia de valores en la tabla Productos. Por ende 
   solo crearemos en este apartado la informacion basica */

INSERT INTO PROVEEDORES (nit, nombre, calificacion) VALUES (123, 'Geovanny', 7);
INSERT INTO PROVEEDORES (nit, nombre, calificacion) VALUES (124, 'Felipe', 3);
INSERT INTO PROVEEDORES (nit, nombre, calificacion) VALUES (125, 'Jhonny', 9);

-- Requerimiento #2
/* Crear un nuevo producto en la base de datos, como se necesita conocer la categoria del producto, el cual es una 
   llave foranea a otra tabla, se crean primero las categorias
*/

-- Solo incluiremos 3 categorias unicamente.
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES (1, 'PERECEDEROS');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES (2, 'ABARROTES');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES (3, 'ELECTRODOMESTICOS');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES (4, 'TODOS');

-- Siguiendo los lineamientos del enunciado solo crearemos 4 produtos por categoria.

/* Creando un producto en categoria PERECEDEROS*/
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('F0F0', 'PERA', 'DOLE', 'UNIDAD', 'KG', 1, 1, 1);
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('00F4', 'DURAZNO', 'DOLE', 'UNIDAD', 'KG', 1, 1, 1);
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('0023', 'CARNE', 'NP', 'LIBRA', 'LB', 1, 1, 1);
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('0021', 'POLLO', 'NP', 'LIBRA', 'LB', 1, 1, 1);

/* Creando productos para la categoria ABARROTES */
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('0034', 'ESCOBA', 'ESCOBAR', 'UNIDAD', 'UNIDAD' , 1, 1, 2);
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('0033', 'RECOGEDOR', 'ESCOBAR', 'UNIDAD', 'UNIDAD' , 1, 1, 2);
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('0032', 'BALDE', 'PLASTICOS JUANITO', 'UNIDAD', 'UNIDAD' , 1, 1, 2);
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('0030', 'DOLEX', 'GENFAR', 'UNIDAD', 'UNIDAD' , 1, 1, 2);

/* Creando productos para la categoria ELECTRODOMESTICOS */
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('00A3', 'NEVERA', 'MABE', 'UNIDAD', 'UNIDAD' , 1, 1, 3);
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('00A2', 'HORNO', 'HACEB', 'UNIDAD', 'UNIDAD' , 1, 1, 3);
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('00A0', 'MICROONDAS', 'ELECTROLUX', 'UNIDAD', 'UNIDAD' , 1, 1, 3);
INSERT INTO PRODUCTOS (CODIGO, NOMBRE, MARCA, PRESENTACION, UNIDAD_MEDIDA, CANTIDAD_EN_PRESENTACION, ESPECIFICACION_EMPACADO, CATEGORIA) VALUES ('01A0', 'LAVADORA', 'ELECTROALPES', 'UNIDAD', 'UNIDAD' , 1, 1, 3);

-- Requerimiento #3
-- Crear clientes
-- Se crean dos clientes con la informacion necesaria para facturacion.


INSERT INTO CLIENTES (ID, NOMBRE, CORREO, DIRECCION, NUM_IDENTIFICACION, TIPO_IDENTIFICACION) VALUES ('1', 'Javier', 'javier@example.com', 'Cll 1 #1-01', '12345', 'CC');
INSERT INTO CLIENTES (ID, NOMBRE, CORREO, DIRECCION, NUM_IDENTIFICACION, TIPO_IDENTIFICACION) VALUES ('2', 'Geovanny', 'geovanny@example.com', 'Cll 2 #2-02', '12432', 'CC');

-- Requerimiento #4
-- Crear sucursal
-- Se crean el supermercado superAndes con una sucursal en chia.

INSERT INTO "ISIS2304A341820"."SUPERMERCADOS" (NIT,NOMBRE) VALUES ('123456','superAndes');
INSERT INTO "ISIS2304A341820"."SUCURSALES" (ID, CIUDAD, DIRECCION, NOMBRE, NIT_SUPERMERCADO) VALUES ('1', 'Chia', 'Cra 3 No 1 9', 'superAndes chia', '123456');
INSERT INTO "ISIS2304A341820"."SUCURSALES" (ID, CIUDAD, DIRECCION, NOMBRE, NIT_SUPERMERCADO) VALUES ('2', 'Chia', 'Cra 1 No 12 3', 'superAndes centroChia', '123456');
INSERT INTO "ISIS2304A341820"."SUCURSALES" (ID, CIUDAD, DIRECCION, NOMBRE, NIT_SUPERMERCADO) VALUES ('3', 'Bogota', 'Cra 7 No 19 9', 'superAndes 7a', '123456');

-- Requerimiento #5
-- Crear bodega
-- Se crean dos bodegas en la sucursal de chia.

INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('1', 'BODEGA', '10000', '4000', '1', '1');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('2', 'BODEGA', '10000', '4000', '4', '1');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('6', 'BODEGA', '10000', '4000', '4', '2');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('7', 'BODEGA', '10000', '4000', '4', '3');

-- Requerimiento #6
-- Crear estante
-- Se crea un estante por cada categoria de producto.

INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('3', 'ESTANTE', '1000', '400', '1', '1');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('4', 'ESTANTE', '1000', '400', '2', '1');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('5', 'ESTANTE', '1000', '400', '3', '1');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('8', 'ESTANTE', '1000', '400', '1', '2');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('9', 'ESTANTE', '1000', '400', '2', '2');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('10', 'ESTANTE', '1000', '400', '3', '2');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('11', 'ESTANTE', '1000', '400', '1', '3');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('12', 'ESTANTE', '1000', '400', '2', '3');
INSERT INTO "ISIS2304A341820"."ESPACIO_ACOMODACION" (ID, TIPO, VOLUMEN_MAXIMO, VOLUMEN_MINIMO, ID_CATEGORIA, ID_SUCURSAL) VALUES ('13', 'ESTANTE', '1000', '400', '3', '3');

-- Requerimiento #7
-- Crear promocion
-- Se crea la promocione.
INSERT INTO "ISIS2304A341820"."PROMOCIONES" (ID, FECHA_INICIO, FECHA_FINAL, TIPO, UNIDADES_DISPONIBLES) VALUES (?,?,?,?,?); 
-- Se agregan los productos incluidos en la promocion
INSERT INTO "ISIS2304A341820"."PRODUCTOS_PROMOCION" (ID_PRODUCTO, ID_PROMOCION) VALUES (?,?);

-- Requerimiento #8
-- Eliminar promocion
-- Se eliminan las relaciones con los productos incluidos en la promocion.
DELETE FROM "ISIS2304A341820"."PRODUCTOS_PROMOCION" WHERE ID_PROMOCION = ?;
-- Se elimina la promocione.
DELETE FROM "ISIS2304A341820"."PROMOCIONES" WHERE ID = ?; 
COMMIT;

/* Requerimiento 9: Insertar una nueva orden de pedido de un producto para una sucursal
   Geovanny Gonzalez
*/

--Insertar la nueva orden de pedido
INSERT INTO pedidos_sucursal (ID, ID_SUCURSAL, ID_PROVEEDOR, ESTADO, FECHA_ESPERADA, FECHA_ENTREGA, CALIFICACION) VALUES (?, ?, ?, ?, ?, ?, ?);
COMMIT;

--Insertar producto en la orden de pedido
INSERT INTO ORDEN_PRODUCTOS (ID_PEDIDO, ID_PRODUCTO, PRECIO_CU_ACORDADO, CANTIDAD) VALUES (?, ?, ?, ?);
COMMIT;

/*
	Requerimiento #10: Registrar la llegada de una orden de producto a una bodega y actualizar inventario
	Geovanny Gonzalez
*/

--Buscar en producto en la BD y cambiar el estado a entregado.
UPDATE PEDIDOS_SUCURSAL
SET ESTADO = 'ENTREGADO', FECHA_ENTREGA = ?, CALIFICACION = ? --La fecha de entrega y valor de calificacion.
WHERE ID = ? -- Lo que llegue por parametro

--Busca la existencia y la cantidad a sumar
SELECT a.id_producto_suc, a.id_espacio_acomo, c.cantidad
FROM existencias a, espacio_acomodacion b, orden_productos c, productos_sucursal d
WHERE c.id_pedido = ? AND b.id_sucursal = ? AND a.id_espacio_acomo = b.id AND c.id_producto = d.id_producto AND a.id_producto_suc = d.id

--Actualizar existencias
UPDATE existencias
SET CANTIDAD = CANTIDAD + ?
WHERE ID_PRODUCTO_SUC = ? AND ID_ESPACIO_ACOMO = ?

/* Requerimiento 11: Realizar la venta de un producto en una sucursal
   Geovanny Gonzalez
 */

SELECT c.id, c.precio_cu --Se necesita el producto de la sucursal que cumple el requerimiento
FROM espacio_acomodacion a, existencias b, productos_sucursal c
WHERE a.id = b.id_espacio_acomo AND b.id_producto_suc = c.id -- Y los datos de entrada a.sucursal  = ? y c.idProducto = ?

--Mirar si hay promocion.
SELECT b.id ,b.tipo --Que tipo de promocion se va a aplicar y el id para actualizar unidades
FROM productos_promocion a, promociones b
WHERE a.id_promocion = b.id AND b.unidades_disponibles > 0 AND b.fecha_inicio > b.fecha_final --La promocion sigue disponible

UPDATE promociones p
SET p.unidades_disponibles = p.unidades_disponibles - cantidad
WHERE p.id = ? --Actualizar unidades disponibles

--Con el tipo de la promocion y el costo obtenido por producto mirar cuanto cobrar.

--Crear factura
INSERT INTO FACTURA(ID, FECHA, TOTAL,ID_CLIENTE, ID_SUCURSAL) VALUES (?,?,?,?,?); --Valores ya obtenidos.

-- Dejar record de la venta del producto
INSERT INTO ventas_producto (ID_PRODUCTO_SUC, ID_FACTURA, CANTIDAD) VALUES (?,?,?) -- Los valores necesarioa

-- Requerimiento de consulta #2 Obtener las 20 promociones mas populares, las que mas vendieron
-- Geovanny Gonzalez

SELECT *
FROM (
     SELECT pr.id_producto_suc, pr.id_promocion, SUM(d.precio_cu) AS DINERO, SUM(d.cantidad) AS VENDIDO
     FROM productos_promocion pr,
                                (SELECT p.id, p.precio_cu, v.cantidad
                                FROM productos_sucursal p, ventas_producto v
                                WHERE v.id_producto_suc = p.id) d
     WHERE pr.id_producto_suc = d.id
     GROUP BY pr.id_producto_suc, pr.id_promocion
     )
ORDER BY dinero ASC

/* REQUERIMIENTOS DE CONSULTA: */

--RFC1:
	SELECT SUM(TOTAL) AS DINERO, ID_SUCURSAL AS SUCURSAL
	FROM FACTURA
	WHERE FECHA BETWEEN ? AND ?
	GROUP BY ID_SUCURSAL;
--RFC3:
	SELECT EX.CANTIDAD, ES.VOLUMEN_MAXIMO
	FROM ESPACIO_ACOMODACION ES, EXISTENICAS EX
	WHERE EX.ID_ESPCAIO_ACOMO = ES.ID;
--RFC4:
	--Busqueda por precio (entre todas las sucursales)
	SELECT *
	FROM PRODUCTOS , 
		(SELECT UNIQUE ID_PRODUCTO
		FROM PRODUCTOS_SUCURSAL
		WHERE PRECIO_CU BETWEEN ? AND ?) B
	WHERE PRODUCTOS.CODIGO = B.ID_PRODUCTO;
	--Busqueda por Cantidad en presentacion (peso o volumen)
	SELECT *
	FROM PRODUCTO
	WHERE CANTIDAD_EN_PRESENTACION BETWEEN ? AND ?;
	--Disponibles en cierta cantidad
	SELECT *
	FROM PRODUCTOS ,
		(SELECT SUM(E.CANTIDAD) CANTIDAD, ID_PRODUCTO
		FROM EXISTENCIAS E, PRODUCTO_DE_SUCURSAL P
		WHERE E.ID_PRODUCTO_SUC = P.ID
		GROUP BY P.ID_PRODUCTO) B
	WHERE PRODUCTOS.CODIGO = B.ID_PRODUCTO
	  AND B.CANTIDAD > ?;
	--Disponibles en cierta sucursal
	SELECT * 
	FROM PRODUCTOS,
		(SELECT P_S.ID_PRODUCTO
		FROM PRODUCTOS_SUCURSAL P_S, EXISTENCIAS EX, ESPACIO_ACOMODACION ES
		WHERE P_S.ID = EX.ID_PRODUCTO_SUC
		  AND EX.ID_ESPACIO_ACOMO = ES.ID
		  AND ES.ID_SUCURSAL = ?) B
	WHERE PRODUCTOS.CODIGO = B.ID_PRODUCTO;
  
