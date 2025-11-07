DELETE FROM empleats_tasques;
DELETE FROM tasques;
DELETE FROM valoracions;
DELETE FROM reserves;
DELETE FROM factures;
DELETE FROM clients;
DELETE FROM empleats;
DELETE FROM habitacions;
DELETE FROM hotels;
DELETE FROM categories;
DELETE FROM usuaris;



INSERT INTO categories (id, nom) VALUES (1, 'Motel');
INSERT INTO categories (id, nom) VALUES (2, '1 estrella');
INSERT INTO categories (id, nom) VALUES (3, '2 estrelles');
INSERT INTO categories (id, nom) VALUES (4, '3 estrelles');
INSERT INTO categories (id, nom) VALUES (5, '4 estrelles');
INSERT INTO categories (id, nom) VALUES (6, '5 estrelles');



INSERT INTO hotels (id, nom, adreca, ciutat, categoria_id) VALUES (1, 'Hotel del Sol', 'Carrer del Sol, 7', 'Tarragona', 3);
INSERT INTO hotels (id, nom, adreca, ciutat, categoria_id) VALUES (2, 'Hotel Miramar', 'Carrer de Sant Joan 1', 'Barcelona', 2);
INSERT INTO hotels (id, nom, adreca, ciutat, categoria_id) VALUES (3, 'Hotel San Vicente', 'Carrer de Bernat 6', 'Valencia', 3);

INSERT INTO habitacions (id, numero, capacitat, descripcio, tipus, estat, preunitad, preunitmp, hotel_id) VALUES
(1, 101, 2, 'Habitación doble con vistas al jardín y terraza privada', 'DOBLE', 'DISPONIBLE', 80.00, 60.00, 1),
(2, 102, 2, 'Habitación doble con balcón y vistas a la montaña', 'DOBLE', 'DISPONIBLE', 85.00, 65.00, 1),
(3, 103, 3, 'Habitación triple con terraza amplia y jardín', 'SUITE', 'DISPONIBLE', 100.00, 80.00, 1),
(4, 104, 1, 'Habitación individual acogedora con decoración moderna', 'SIMPLE', 'DISPONIBLE', 60.00, 45.00, 1),
(5, 105, 2, 'Habitación doble con bañera de hidromasaje y vistas al mar', 'DOBLE', 'DISPONIBLE', 120.00, 100.00, 1),
(6, 106, 4, 'Suite familiar con sala de estar y cocina equipada', 'SUITE', 'DISPONIBLE', 150.00, 130.00, 1),
(7, 107, 2, 'Habitación doble estándar con vistas a la ciudad', 'DOBLE', 'DISPONIBLE', 75.00, 55.00, 1),
(8, 108, 1, 'Habitación individual con escritorio y vistas al parque', 'SIMPLE', 'DISPONIBLE', 65.00, 50.00, 1),
(9, 109, 3, 'Habitación triple con vistas a la piscina y balcón', 'SUITE', 'DISPONIBLE', 110.00, 90.00, 1),
(10, 110, 2, 'Habitación doble superior con balcón y vistas al jardín', 'DOBLE', 'DISPONIBLE', 95.00, 75.00, 1),

(11, 201, 2, 'Habitación doble moderna con vistas a la ciudad y balcón', 'DOBLE', 'DISPONIBLE', 150.00, 130.00, 2),
(12, 202, 3, 'Habitación triple con terraza privada y jardín', 'SUITE', 'DISPONIBLE', 180.00, 160.00, 2),
(13, 203, 1, 'Habitación individual con diseño minimalista y vistas al mar', 'SIMPLE', 'DISPONIBLE', 100.00, 85.00, 2),
(14, 204, 4, 'Suite ejecutiva con sala de estar y vistas panorámicas', 'SUITE', 'DISPONIBLE', 250.00, 220.00, 2),
(15, 205, 2, 'Habitación doble con jacuzzi y vistas al jardín', 'DOBLE', 'DISPONIBLE', 200.00, 180.00, 2),
(16, 206, 3, 'Habitación triple con escritorio, minibar y vistas al parque', 'SUITE', 'DISPONIBLE', 175.00, 155.00, 2),
(17, 207, 2, 'Habitación doble clásica con detalles elegantes y vistas a la ciudad', 'DOBLE', 'DISPONIBLE', 160.00, 140.00, 2),
(18, 208, 1, 'Habitación individual con vistas al parque y decoración rústica', 'SIMPLE', 'DISPONIBLE', 105.00, 90.00, 2),
(19, 209, 2, 'Habitación doble de lujo con iluminación ambiental y vistas al mar', 'DOBLE', 'DISPONIBLE', 190.00, 170.00, 2),
(20, 210, 4, 'Suite presidencial con terraza, jacuzzi y vistas panorámicas', 'SUITE', 'DISPONIBLE', 300.00, 270.00, 2),

(21, 301, 1, 'Habitación individual con decoración rústica y vistas al jardín', 'SIMPLE', 'DISPONIBLE', 55.00, 40.00, 3),
(22, 302, 2, 'Habitación doble con cama king-size y vistas a la montaña', 'DOBLE', 'DISPONIBLE', 90.00, 70.00, 3),
(23, 303, 3, 'Habitación triple con balcón privado y vistas al mar', 'SUITE', 'DISPONIBLE', 110.00, 85.00, 3),
(24, 304, 4, 'Suite familiar con cocina, terraza y vistas al jardín', 'SUITE', 'DISPONIBLE', 170.00, 140.00, 3),
(25, 305, 2, 'Habitación doble con baño de mármol y vistas a la ciudad', 'DOBLE', 'DISPONIBLE', 95.00, 75.00, 3),
(26, 306, 3, 'Habitación triple con vistas al mar y balcón privado', 'SUITE', 'DISPONIBLE', 130.00, 110.00, 3),
(27, 307, 1, 'Habitación individual con escritorio, wifi gratuito y vistas al parque', 'SIMPLE', 'DISPONIBLE', 60.00, 50.00, 3),
(28, 308, 2, 'Habitación doble con mobiliario clásico y vistas al jardín', 'DOBLE', 'DISPONIBLE', 85.00, 65.00, 3),
(29, 309, 3, 'Habitación triple con decoración moderna y vistas a la ciudad', 'SUITE', 'DISPONIBLE', 120.00, 95.00, 3),
(30, 310, 2, 'Habitación doble estándar con aire acondicionado y vistas al parque', 'DOBLE', 'DISPONIBLE', 80.00, 60.00, 3);

INSERT INTO usuaris (id, actiu, email, password, rol, username) VALUES (4, true, "admin@admin.com", "secret", "ROLE_ADMIN", "admin");
INSERT INTO usuaris (id, actiu, email, password, rol, username) VALUES (5, true, "client@client.com", "secret", "ROLE_CLIENT", "client");
INSERT INTO usuaris (id, actiu, email, password, rol, username)
VALUES (6, true, 'james@gmail.com', 'password123', 'ROLE_CLIENT', 'jamesfraser');


INSERT INTO clients (id, adreça, cognom, data_naixement, document_identitat, email, nom, telefon, usuari_id, data_registre, targeta_credit, tipus)
VALUES
(501, 'Carrer Major 123, Barcelona', 'Fraser', '1982-09-25', '12345678A', 'james@gmail.com', 'James', '612345678', 6, '2023-10-26', '675478984', 'REGULAR');


INSERT INTO empleats (id, adreça, cognom, data_naixement, document_identitat, email, nom, telefon, data_contractacio, estat, lloc_feina, salari_brut)
VALUES
(501, 'Carrer de la Pau 10, Barcelona', 'Smith', '1991-02-16', '12345678Z', 'john.smith@gmail.com', 'John', '612345679', '2021-04-02', 'ACTIU', 'Recepcionista', 31000),
(502, 'Avinguda de la Llum 20, Girona', 'Johnson', '1986-07-23', '98765432Y', 'ana.johnson@gmail.com', 'Ana', '698765433', '2020-10-16', 'ACTIU', 'Cambrer', 29000),
(503, 'Plaça de la Llibertat 30, Tarragona', 'Williams', '1996-12-11', '56789012X', 'luis.williams@gmail.com', 'Luis', '656789013', '2022-02-06', 'PERMIS', 'Conserge', 33000),
(504, 'Rambla de la Independència 40, Lleida', 'Brown', '1983-05-04', '01234567W', 'maria.brown@gmail.com', 'Maria', '601234568', '2019-12-21', 'ACTIU', 'Gerent', 46000),
(505, 'Gran Via de les Corts 50, Manresa', 'Jones', '1979-10-19', '43210987V', 'jose.jones@gmail.com', 'Jose', '643210988', '2018-08-11', 'BAIXA', 'Comptable', 36000),
(506, 'Carrer de la Llibertat 60, Vic', 'Miller', '1999-03-01', '87654321U', 'laura.miller@gmail.com', 'Laura', '687654322', '2023-06-02', 'ACTIU', 'Manteniment', 30000),
(507, 'Avinguda de la Pau 70, Igualada', 'Davis', '1988-08-06', '21098765T', 'david.davis@gmail.com', 'David', '621098766', '2021-01-16', 'ACTIU', 'Cuiner', 28000),
(508, 'Plaça de la Pau 80, Olot', 'Garcia', '1994-01-09', '65432109S', 'silvia.garcia@gmail.com', 'Silvia', '665432110', '2022-09-02', 'PERMIS', 'Administratiu', 26000),
(509, 'Rambla de la Pau 90, Reus', 'Martinez', '1981-06-13', '98765432R', 'carlos.martinez@gmail.com', 'Carlos', '698765434', '2020-06-21', 'ACTIU', 'Venedor', 27000),
(510, 'Gran Avinguda de la Pau 100, Mataró', 'Taylor', '1976-11-26', '32109876Q', 'isabel.taylor@gmail.com', 'Isabel', '632109877', '2019-03-11', 'BAIXA', 'Jardiner', 39000);





INSERT INTO tasques (id, data_creacio, data_ejecucio, descripcio, estat)
VALUES
(50, '2025-03-12', '2025-03-13', 'Neteja lavabos', 'PENDENT'),
(51, '2025-03-18', '2025-03-19', 'Revisión del sistema de climatización', 'COMPLETADA'),
(53, '2025-03-21', '2025-04-23', 'Reparación de cerraduras en habitaciones', 'EN_PROGRÉS'),
(54, '2025-05-19', '2025-06-21', 'Inventario de suministros de limpieza', 'COMPLETADA'),
(55, '2025-06-22', '2025-07-24', 'Mantenimiento del ascensor principal', 'PENDENT');




