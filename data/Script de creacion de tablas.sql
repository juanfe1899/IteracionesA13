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
CONSTRAINT FK_CATEGORIA FOREIGN KEY (CATEGORIA) REFERENCES Categorias(ID),
CONSTRAINT CHK_ESPECIFICACION_EMPACADO CHECK (ESPECIFICACION_EMPACADO >0)
)

--Tabla Categoria
CREATE TABLE Categorias(
ID INT NOT NULL,
NOMBRE VARCHAR(255) NOT NULL,
--Restricciones sobre la tabla
CONSTRAINT PK_CATEGORIA PRIMARY KEY (ID)
)

--Tabla Proveedores
CREATE TABLE Proveedores(
NIT NUMBER NOT NULL,
NOMBRE VARCHAR(255) NOT NULL,
CALIFICACION NUMBER NOT NULL,
--Restricciones sobre la tabla
CONSTRAINT PK_PROVEEDORES PRIMARY KEY (NIT),
CONSTRAINT CHK_CALIFICACION CHECK (CALIFICACION BETWEEN 0 AND 10)
)

--Tabla Provee
CREATE TABLE Proveen(
ID_PROVEEDOR NUMBER NOT NULL,
ID_PRODUCTO NUMBER NOT NULL,
PRECIO_CU NUMBER NOT NULL,
PRECIO_UNIDAD_MEDIDA NUMBER NOT NULL,
--Restricciones sobre la tabla
CONSTRAINT PK_PROVEE PRIMARY KEY (ID_PROVEEDOR, ID_PRODUCTO),
CONSTRAINT FK_PRODUCTO FOREIGN KEY (ID_PRODUCTO) REFERENCES Productos(ID),
CONSTRAINT FK_PROVEEDOR FOREIGN KEY (ID_PROVEEDOR) REFERENCES Proveedores(NIT),
CONSTRAINT CHK_PRECIO_CU CHECK (PRECIO_CU >0),
CONSTRAINT CHK_PRECIO_UNIDAD_MEDIDA CHECK (PRECIO_UNIDAD_MEDIDA >0)
)

--Tabla Productos de una sucursal
CREATE TABLE Productos_Sucursal(
ID NUMBER NOT NULL,
ID_PRODUCTO NUMBER NOT NULL,
CANTIDAD_REORDER NUMBER NOT NULL,
PRECIO_CU NUMBER NOT NULL,
--Restricciones sobre la tabla
CONSTRAINT PK_PRODUCTO_SUCURSA PRIMARY KEY (ID),
CONSTRAINT FK_PRODUCTO FOREIGN KEY (ID_PRODUCTO) REFERENCES Productos(ID)
)

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
CONSTRAINT FK_PRODUCTO_SUCURSAL FOREIGN KEY (ID_PRODUCTO_SUCURSAL) REFERENCES Productos_Sucursal(ID),
CONSTRAINT CHK_FECHA CHECK(FECHA_FINAL > FECHA_INICIO)
)
