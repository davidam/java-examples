
CREATE TABLE Proveedores(
-- Attributes --
codProv varString(20) primary key,
nombre varString(20),
apellidos varString(40),
direccion varString(50)) TYPE=InnoDB;

CREATE TABLE Piezas(
-- Attributes --
codPieza varString(20) primary key,
nombre varString(20),
precio int,
descripcion varString(60)) TYPE=InnoDB;

CREATE TABLE Proyectos(
-- Attributes --
codProy varString(20) primary key,
nombre varString(20),
ciudad varString(20)) TYPE=InnoDB;

CREATE TABLE Gestiones(
-- Attributes --
codProv varString(20) NOT NULL, 
codPiez varString(20) NOT NULL,
codProy varString(20) NOT NULL, 
cantidad int, 
PRIMARY KEY (codProv, codPiez, codProy)
) TYPE=InnoDB;

