drop database if exists EuromapDb;
create database EuromapDb;
use EuromapDb;

create table if not exists Mapa(
id integer primary key auto_increment,
tipo_mapa varchar(255) not null,
imagen_mapa mediumblob
);

create table if not exists Usuario(
id integer primary key auto_increment,
nombre_usuario varchar(255) not null,
correo_usuario varchar(255) not null,
contrasena_usuario varchar(255) not null,
imagen_usuario mediumblob,
idioma varchar(255) not null,
modo_oscuro boolean default false
);

create table if not exists Nombre_Mapa(
id_mapa integer,
nombre_mapa varchar(255),
idioma varchar(255) not null,
primary key(id_mapa,nombre_mapa),
foreign key (id_mapa) references Mapa(id)
on update cascade 
on delete cascade
);

create table if not exists Puntuacion(
id_mapa integer,
id_usuario integer,
tiempo time not null,
aciertos integer not null,
fallos integer not null,
puntuacion integer not null,
rango char(1) not null,
primary key(id_mapa,id_usuario),
foreign key (id_mapa) references Mapa(id)
on update cascade 
on delete cascade,
foreign key (id_usuario) references Usuario(id)
on update cascade 
on delete cascade
);