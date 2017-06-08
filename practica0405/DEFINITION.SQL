CREATE TABLE Proveedor(
-- Attributes --
codProv String,
nombre String,
apellidos String,
direccion String);

CREATE TABLE Piezas(
-- Attributes --
codPieza String,
nombre String,
precio int,
descripcion String);

CREATE TABLE Proyectos(
-- Attributes --
codProy String,
nombre String,
ciudad String);

CREATE TABLE Gestion(
-- Attributes --
codProv  String,
codPiez String,
codProy String,
cantidad int);

