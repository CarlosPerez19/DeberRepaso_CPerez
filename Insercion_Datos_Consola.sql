create database estudiantes;

use estudiantes;

create table estudiantes(
nombre varchar(15) not null,
apellido varchar(15) not null,
cedula varchar(10) not null primary key,
correo varchar(50) not null unique,
nota_1 decimal(4,2) not null,
nota_2 decimal(4,2) not null,
promedio decimal(4,2) not null);

select * from estudiantes;