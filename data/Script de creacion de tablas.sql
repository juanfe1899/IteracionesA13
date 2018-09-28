--Tabla Categoria
CREATE TABLE Categorias(
ID INT NOT NULL,
NOMBRE VARCHAR(255) NOT NULL,
--Restricciones sobre la tabla
CONSTRAINT PK_CATEGORIA PRIMARY KEY (ID)
);

--Tabla productos
CREATE TABLE Productos(
ID NUMBER NOT NULL,
NOMBRE VARCHAR(255) NOT NULL,
MARCA VARCHAR(255) NOT NULL,
PRESENTACION VARCHAR(255) NOT NULL,
UNIDAD_MEDIDA VARCHAR(255) NOT NULL,
CANTIDAD_EN_PRESENTACION VARCHAR(255) NOT NULL,
ESPECIFICACION_EMPACADO NUMBER NOT NULL,
CATEGORIA NUMBER NOT NULL,
--Restricciones sobre la tabla
CONSTRAINT PK_PRODUCTO PRIMARY KEY (ID),
CONSTRAINT FK_CATEGORIA_PRODUCTO FOREIGN KEY (CATEGORIA) REFERENCES Categorias(ID),
CONSTRAINT CHK_ESPECIFICACION_EMPACADO CHECK (ESPECIFICACION_EMPACADO >0)
);

--Tabla Proveedores
CREATE TABLE Proveedores(
NIT NUMBER NOT NULL,
NOMBRE VARCHAR(255) NOT NULL,
CALIFICACION NUMBER NOT NULL,
--Restricciones sobre la tabla
CONSTRAINT PK_PROVEEDORES PRIMARY KEY (NIT),
CONSTRAINT CHK_CALIFICACION CHECK (CALIFICACION BETWEEN 0 AND 10)
);

--Tabla Provee
CREATE TABLE Proveen(
ID_PROVEEDOR NUMBER NOT NULL,
ID_PRODUCTO NUMBER NOT NULL,
PRECIO_CU NUMBER NOT NULL,
PRECIO_UNIDAD_MEDIDA NUMBER NOT NULL,
--Restricciones sobre la tabla
CONSTRAINT PK_PROVEE PRIMARY KEY (ID_PROVEEDOR, ID_PRODUCTO),
CONSTRAINT FK_PRODUCTO_PROVEEN FOREIGN KEY (ID_PRODUCTO) REFERENCES Productos(ID),
CONSTRAINT FK_PROVEEDOR_PROVEEN FOREIGN KEY (ID_PROVEEDOR) REFERENCES Proveedores(NIT),
CONSTRAINT CHK_PRECIO_CU CHECK (PRECIO_CU >0),
CONSTRAINT CHK_PRECIO_UNIDAD_MEDIDA CHECK (PRECIO_UNIDAD_MEDIDA >0)
);

--Tabla Productos de una sucursal
CREATE TABLE Productos_Sucursal(
ID NUMBER NOT NULL,
ID_PRODUCTO NUMBER NOT NULL,
CANTIDAD_REORDER NUMBER NOT NULL,
PRECIO_CU NUMBER NOT NULL,
--Restricciones sobre la tabla
CONSTRAINT PK_PRODUCTO_SUCURSAL PRIMARY KEY (ID),
CONSTRAINT FK_PRODUCTO_PRODUCTO_SUCURSAL FOREIGN KEY (ID_PRODUCTO) REFERENCES Productos(ID)
);

--Tabla Promociones
CREATE TABLE Promociones(
ID NUMBER NOT NULL,
FECHA_INICIO DATE NOT NULL,
FECHA_FINAL DATE NOT NULL,
TIPO VARCHAR(255) NOT NULL,
UNIDADES_DISPONIBLES NUMBER NOT NULL,
ID_PRODUCTO_SUCURSAL NUMBER NOT NULL,
--Restricciones sobre la tabla
CONSTRAINT PK_PROMOCIONES PRIMARY KEY (ID),
CONSTRAINT FK_PRODUCTO_SUCURSAL_PROMOCION FOREIGN KEY (ID_PRODUCTO_SUCURSAL) REFERENCES Productos_Sucursal(ID),
CONSTRAINT CHK_FECHA CHECK(FECHA_FINAL > FECHA_INICIO)
);

CREATE TABLE Supermercados(
NIT NUMBER NOT NULL,
NOMBRE VARCHAR(255) NOT NULL,

--Restricciones sobre la tabla
CONSTRAINT PK_SUPERMERCADO PRIMARY KEY (NIT)
);

CREATE TABLE Sucursales(
ID NUMBER NOT NULL,
CIUDAD VARCHAR(255) NOT NULL,
DIRECCION VARCHAR(255) NOT NULL,
NOMBRE VARCHAR(255) NOT NULL,
NIT_SUPERMERCADO NUMBER NOT NULL,

--Restricciones sobre la tabla
CONSTRAINT PK_SUCURSAL PRIMARY KEY (ID),
CONSTRAINT FK_SUPERMERCADO_SUCURSAL FOREIGN KEY (NIT_SUPERMERCADO) REFERENCES Supermercados(NIT)
);

CREATE TABLE Ofrecen(
ID_SUPERMERCADO NUMBER NOT NULL,
ID_PRODUCTO NUMBER NOT NULL,
PRECIO_CU NUMBER NOT NULL,
PRECIO_UNIDAD_MEDIDA NUMBER NOT NULL,

--Restricciones de la tabla

CONSTRAINT PK_OFRECEN PRIMARY KEY (ID_SUPERMERCADO, ID_PRODUCTO),
CONSTRAINT FK_SUPERMERCADO_OFRECEN FOREIGN KEY (ID_SUPERMERCADO) REFERENCES Supermercados(NIT),
CONSTRAINT FK_PRODUCTO_OFRECEN FOREIGN KEY (ID_PRODUCTO) REFERENCES Productos(ID),
CONSTRAINT CHK_PRECIO_CU_OFRECE CHECK(PRECIO_CU > 0),
CONSTRAINT CHK_PRECIO_UN_OFRECE CHECK(PRECIO_UNIDAD_MEDIDA > 0)
);

CREATE TABLE Supermercado_Proveedores(
ID_SUPERMERCADO NUMBER NOT NULL,
ID_PROVEEDOR NUMBER NOT NULL,

--Restricciones sobre la tabla
CONSTRAINT PK_SUPER_PROVEEDOR PRIMARY KEY (ID_SUPERMERCADO, ID_PROVEEDOR),
CONSTRAINT FK_SUPER_SUPER_PROVEEDOR FOREIGN KEY (ID_SUPERMERCADO) REFERENCES Supermercados(NIT),
CONSTRAINT FK_PROVEED_SUPER_PROVEEDOR FOREIGN KEY (ID_PROVEEDOR) REFERENCES Proveedores(NIT)
);

CREATE TABLE Pedidos_Sucursal(
ID NUMBER NOT NULL,
ID_SUCURSAL NUMBER NOT NULL,
ID_PROVEEDOR NUMBER NOT NULL,
ESTADO VARCHAR(30) NOT NULL,
FECHA_ESPERADA DATE NOT NULL,
FECHA_ENTREGA DATE NOT NULL,
CALIFICACION NUMBER NOT NULL,

--Restricciones sobre la tabla
CONSTRAINT PK_PEDIDOS PRIMARY KEY (ID),
CONSTRAINT FK_SUCURSAL_PEDIDOS FOREIGN KEY (ID_SUCURSAL) REFERENCES Sucursales(ID),
CONSTRAINT FK_PROVEEDOR_PEDIDOS FOREIGN KEY (ID_PROVEEDOR) REFERENCES Proveedores(NIT),
CONSTRAINT CHK_FECHA_PEDIDOS CHECK(FECHA_ESPERADA >= FECHA_ENTREGA),
CONSTRAINT CHK_ESTADO_PEDIDOS CHECK(ESTADO IN ('EN_ESPERA', 'ENTREGADO')),
CONSTRAINT CHK_CALIFICACION_PEDIDOS CHECK (CALIFICACION BETWEEN 0 AND 10)
);

CREATE TABLE Orden_Productos(
ID_PEDIDO NUMBER NOT NULL,
ID_PRODUCTO NUMBER NOT NULL,
PRECIO_CU_ACORDADO NUMBER NOT NULL,
CANTIDAD NUMBER NOT NULL,

--Restricciones sobre la tabla
CONSTRAINT PK_ORDEN PRIMARY KEY (ID_PEDIDO, ID_PRODUCTO),
CONSTRAINT FK_PEDIDO_ORDEN FOREIGN KEY (ID_PEDIDO) REFERENCES Pedidos_Sucursal (ID),
CONSTRAINT FK_PRODUCTO_ORDEN FOREIGN KEY (ID_PRODUCTO) REFERENCES Productos(ID),
CONSTRAINT CHK_PRECIO_ACORDADO CHECK (PRECIO_CU_ACORDADO > 0),
CONSTRAINT CHK_CANTIDAD CHECK (CANTIDAD > 0)
);

CREATE TABLE Clientes(
ID NUMBER NOT NULL,
NOMBRE VARCHAR(255) NOT NULL,
CORREO NVARCHAR2(255) NOT NULL,
DIRECCION NVARCHAR2(255),
NUM_IDENTIFICACION NUMBER NOT NULL,
TIPO_IDENTIFICACION VARCHAR2(255),

--Restricciones de la tabla
CONSTRAINT PK_CLIENTE PRIMARY KEY (ID),
CONSTRAINT CHK_NUM_IDENTI CHECK (NUM_IDENTIFICACION > 0),
CONSTRAINT CHK_CORREO CHECK (CORREO LIKE '%@%')
);

CREATE TABLE Facturas(
ID NUMBER NOT NULL,
FECHA DATE NOT NULL,
TOTAL NUMBER NOT NULL,
ID_CLIENTE NUMBER NOT NULL,
ID_SUCURSAL NUMBER NOT NULL,

--Restricciones de la tabla
CONSTRAINT PK_FACTURA PRIMARY KEY (ID),
CONSTRAINT FK_CLIENTE_FACTURA FOREIGN KEY (ID_CLIENTE) REFERENCES Clientes(ID),
CONSTRAINT FK_SUCURSAL_FACTURA FOREIGN KEY (ID_SUCURSAL) REFERENCES Sucursales(ID),
CONSTRAINT CHK_TOTAL CHECK (TOTAL > 0)
);

CREATE TABLE Ventas_Producto(
ID_PRODUCTO_SUC NUMBER NOT NULL,
ID_FACTURA NUMBER NOT NULL,
CANTIDAD NUMBER NOT NULL,

--Restricciones tabla
CONSTRAINT PK_VENTAS PRIMARY KEY (ID_PRODUCTO_SUC, ID_FACTURA),
CONSTRAINT FK_PRODUCTO_SUC_VENTA FOREIGN KEY (ID_PRODUCTO_SUC) REFERENCES Productos_Sucursal(ID),
CONSTRAINT FK_FACTURA_VENTA FOREIGN KEY (ID_FACTURA) REFERENCES Facturas(ID),
CONSTRAINT CHK_CANTIDAD_VENTA CHECK (CANTIDAD > 0)
);

COMMIT;