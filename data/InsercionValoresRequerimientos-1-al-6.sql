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
INSERT INTO CLIENTES (ID, NOMBRE, CORREO, DIRECCION, NUM_IDENTIFICACION, TIPO_IDENTIFICACION) VALUES ('2', 'Geovanny', 'geovanny@example.com', 'Cll 2 #2-02', '12432', 'CC')
