-- =============================================================================================================================
--                                                         SUPER MERCADO
-- =============================================================================================================================
INSERT INTO SUPERMERCADOS (NIT, NOMBRE) VALUES ('123456', 'SuperAlpes');
-- =============================================================================================================================
--                                                         SUCURSALES
-- =============================================================================================================================
INSERT INTO SUCURSALES (ID, CIUDAD, DIRECCION, NOMBRE, NIT_SUPERMERCADO) VALUES ('1', 'Bogota', 'Cll 1 # 1-1', 'alpiBogota', '123456');
INSERT INTO SUCURSALES (ID, CIUDAD, DIRECCION, NOMBRE, NIT_SUPERMERCADO) VALUES ('2', 'Bogota', 'Cll 2 # 2-2', 'alpiBogota2', '123456');
INSERT INTO SUCURSALES (ID, CIUDAD, DIRECCION, NOMBRE, NIT_SUPERMERCADO) VALUES ('3', 'Chia', 'Cra 3 # 3-3', 'alpiChia', '123456');
-- =============================================================================================================================
--                                                         PROVEEDORES
-- =============================================================================================================================
INSERT INTO PROVEEDORES (NIT, NOMBRE, CALIFICACION) VALUES ('654321', 'SurtiTodo', '3');
-- =============================================================================================================================
--                                                         CATEGORIAS
-- =============================================================================================================================
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES ('1', 'perecederos');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES ('2', 'noPerecederos');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES ('3', 'aseo');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES ('4', 'abarrotes');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES ('5', 'congelados');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES ('6', 'prendasVestir');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES ('7', 'muebles');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES ('8', 'herramientas');
INSERT INTO CATEGORIAS (ID, NOMBRE) VALUES ('9', 'electrodomesticos');

-- =============================================================================================================================
--                                                         CARRITOS DE COMPRAS
-- =============================================================================================================================
INSERT INTO CARRITO_COMPRAS (ID_CARRITO_COMPRAS, ID_SUCURSAL, TOTAL) VALUES ('11', '1', '0');
INSERT INTO CARRITO_COMPRAS (ID_CARRITO_COMPRAS, ID_SUCURSAL, TOTAL) VALUES ('12', '1', '0');
INSERT INTO CARRITO_COMPRAS (ID_CARRITO_COMPRAS, ID_SUCURSAL, TOTAL) VALUES ('13', '1', '0');
INSERT INTO CARRITO_COMPRAS (ID_CARRITO_COMPRAS, ID_SUCURSAL, TOTAL) VALUES ('21', '2', '0');
INSERT INTO CARRITO_COMPRAS (ID_CARRITO_COMPRAS, ID_SUCURSAL, TOTAL) VALUES ('22', '2', '0');
INSERT INTO CARRITO_COMPRAS (ID_CARRITO_COMPRAS, ID_SUCURSAL, TOTAL) VALUES ('23', '2', '0');
INSERT INTO CARRITO_COMPRAS (ID_CARRITO_COMPRAS, ID_SUCURSAL, TOTAL) VALUES ('31', '3', '0');
INSERT INTO CARRITO_COMPRAS (ID_CARRITO_COMPRAS, ID_SUCURSAL, TOTAL) VALUES ('32', '3', '0');
INSERT INTO CARRITO_COMPRAS (ID_CARRITO_COMPRAS, ID_SUCURSAL, TOTAL) VALUES ('33', '3', '0');
