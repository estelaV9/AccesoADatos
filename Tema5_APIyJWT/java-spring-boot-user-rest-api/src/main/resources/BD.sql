drop database if exists api;
Create database api;
use api;
CREATE TABLE  user (
                       id integer auto_increment PRIMARY KEY,
                       email VARCHAR(50) NOT NULL ,
                       password VARCHAR(50) NOT NULL,
                       created_at date NOT NULL,
                       updated_at date NOT NULL

);


INSERT INTO user  VALUES (1, 'juan@gmail.com', 'juan', '2010-01-29','2022-01-29'),
 (2, 'ana@gmail.com', 'ana', '2009-01-29','2021-01-29'),
(3, 'luis@gmail.com', 'luis', '2009-03-29','2020-04-29'),
 (4, 'sara@gmail.com', 'sara', '2008-05-29','2017-06-29'),
 (5, 'mariano@gmail.com', 'mariano', '2001-09-29','2020-01-29'),
 (6, 'angel@gmail.com', 'angel', '2002-04-29','2020-05-29'),
(7, 'lucia@gmail.com', 'lucia', '2009-09-29','2020-07-29'),
(8, 'javier@gmail.com', 'javier', '2005-03-29','2019-04-29');