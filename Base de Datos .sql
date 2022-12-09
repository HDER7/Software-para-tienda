create database FINAL
use FINAL
GO 
CREATE TABLE ARTICULOS(
codigoArticulos int primary key,
nombreA varchar(30),
categoria varchar(40),
precio money,
cantidadBodega int,
);

CREATE TABLE EMPLEADOS(
    codigoEmpleados int primary key,
nombreE varchar(30),
apellido varchar(30),
edad int,
telefono bigint,
fechaNacimiento date,
contraseña varchar(20)
--codVentas int,
);

CREATE TABLE CLIENTES(
    codigoClientes int primary key,
nombreC varchar(30),
apellido varchar(30),
edad int,
telefono bigint,
direccion varchar(60),
--codCompra int,
);

CREATE TABLE VENTAS(
--codVenta int,
codigoArticulo int,
codigoEmpleado int,
codigoCliente int,
cantidad int,
valorVenta int,
fechaVenta varchar(60),
horaVenta varchar(60),
Primary Key(codigoArticulo, codigoEmpleado, codigoCliente),
Constraint fk_CodArt foreign key (codigoArticulo) references ARTICULOS(codigoArticulos),
Constraint fk_CodEmpl foreign key (codigoEmpleado) references EMPLEADOS(codigoEmpleados),
Constraint fk_CodClie foreign key (codigoCliente) references CLIENTES(codigoClientes),
);

select * from ARTICULOS;
select * from ARTICULOS;
select * from VENTAS;

Select * from EMPLEADOS
select cantidadBodega from ARTICULOS;
drop table VENTAS;