USE gestionhotel;
INSERT INTO usuario(name, password)
VALUES ('juan', 'juan');

-- INSERTS PARA LA TABLA "hotel"
INSERT INTO hotel (id_hotel, nombre_hotel, descripcion, categoria, piscina, localidad)
VALUES (1, 'Hotel Playa Dorada', 'Un hotel con vista al mar.', '5 estrellas', true, 'Cancún'),
       (2, 'Montaña Serena', 'Perfecto para una escapada tranquila.', '4 estrellas', false, 'Bariloche'),
       (3, 'Ciudad Luz', 'Ubicado en el corazón de la ciudad.', '3 estrellas', false, 'Madrid'),
       (4, 'Resort Paraíso', 'Ideal para vacaciones familiares.', '5 estrellas', true, 'Punta Cana'),
       (5, 'Hotel Colonial', 'Encanto histórico con comodidades modernas.', '4 estrellas', false, 'Cartagena'),
       (6, 'EcoLodge Amazonia', 'Experiencia ecológica única.', '3 estrellas', true, 'Iquitos');

-- INSERTS PARA LA TABLA "habitacion"
INSERT INTO habitacion (id_habitacion, tamanio, precio_noche, desayuno, ocupada, id_hotel)
VALUES (1, 30, 100.00, true, false, 1),
       (2, 25, 80.00, false, true, 1),
       (3, 35, 120.00, true, false, 2),
       (4, 28, 90.00, false, true, 2),
       (5, 40, 150.00, true, false, 3),
       (6, 20, 70.00, false, true, 3),
       (7, 50, 200.00, true, false, 4),
       (8, 45, 180.00, true, false, 4),
       (9, 30, 100.00, true, true, 5),
       (10, 25, 75.00, false, false, 5),
       (11, 15, 50.00, false, true, 6),
       (12, 18, 60.00, true, false, 6);

SELECT * FROM habitacion;