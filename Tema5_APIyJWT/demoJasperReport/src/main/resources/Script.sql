create DATABASE mascotas;
USE mascotas;
--  OJO poner los nombre de los campos en minuscalas sino da problemas
create TABLE mascota(
                        idmascota int primary key auto_increment,
                        razamascota VARCHAR(20),
                        namemascota VARCHAR(25),
                        birthdaymascota date

);

insert into mascota values (1,'CAT','BHOOMI', '2024/10/01' );
insert into mascota values (2,'CAT','AYUSHI', '2024/11/01' );
insert into mascota values (3,'BIRD','KRIMA', '2024/09/01' );
insert into mascota values (4,'HAMSTER','VRUNDA', '2024/01/09' );
insert into mascota values (5,'BIRD','NEEMOI', '2024/08/05' );
insert into mascota values (6,'BIRD','NANDINI', '2024/04/08' );
insert into mascota values (7,'SNAKE','DIYA', '2024/12/10' );