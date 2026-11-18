-- ========================================================
-- INSERCIÓN DE REGIONES (COMUNIDADES AUTÓNOMAS)
-- ========================================================
INSERT INTO regions (id, code, name) VALUES
                                         (1, '01', 'Andalucía'),
                                         (2, '02', 'Aragón'),
                                         (3, '03', 'Principado de Asturias'),
                                         (4, '04', 'Illes Balears'),
                                         (5, '05', 'Canarias'),
                                         (6, '06', 'Cantabria'),
                                         (7, '07', 'Castilla y León'),
                                         (8, '08', 'Castilla-La Mancha'),
                                         (9, '09', 'Cataluña'),
                                         (10, '10', 'Comunitat Valenciana'),
                                         (11, '11', 'Extremadura'),
                                         (12, '12', 'Galicia'),
                                         (13, '13', 'Comunidad de Madrid'),
                                         (14, '14', 'Región de Murcia'),
                                         (15, '15', 'Comunidad Foral de Navarra'),
                                         (16, '16', 'País Vasco'),
                                         (17, '17', 'La Rioja'),
                                         (18, '18', 'Ceuta'),
                                         (19, '19', 'Melilla')
    ON DUPLICATE KEY UPDATE name = VALUES(name);

-- ========================================================
-- INSERCIÓN DE PROVINCIAS
-- ========================================================
INSERT INTO provinces (code, name, region_id) VALUES
-- Andalucía (id: 1)
('04', 'Almería', 1),
('11', 'Cádiz', 1),
('14', 'Córdoba', 1),
('18', 'Granada', 1),
('21', 'Huelva', 1),
('23', 'Jaén', 1),
('29', 'Málaga', 1),
('41', 'Sevilla', 1),

-- Aragón (id: 2)
('22', 'Huesca', 2),
('44', 'Teruel', 2),
('50', 'Zaragoza', 2),

-- Asturias (id: 3)
('33', 'Asturias', 3),

-- Illes Balears (id: 4)
('07', 'Illes Balears', 4),

-- Canarias (id: 5)
('35', 'Las Palmas', 5),
('38', 'Santa Cruz de Tenerife', 5),

-- Cantabria (id: 6)
('39', 'Cantabria', 6),

-- Castilla y León (id: 7)
('05', 'Ávila', 7),
('09', 'Burgos', 7),
('24', 'León', 7),
('34', 'Palencia', 7),
('37', 'Salamanca', 7),
('40', 'Segovia', 7),
('42', 'Soria', 7),
('47', 'Valladolid', 7),
('49', 'Zamora', 7),

-- Castilla-La Mancha (id: 8)
('02', 'Albacete', 8),
('13', 'Ciudad Real', 8),
('16', 'Cuenca', 8),
('19', 'Guadalajara', 8),
('45', 'Toledo', 8),

-- Cataluña (id: 9)
('08', 'Barcelona', 9),
('17', 'Girona', 9),
('25', 'Lleida', 9),
('43', 'Tarragona', 9),

-- Comunitat Valenciana (id: 10)
('03', 'Alicante/Alacant', 10),
('12', 'Castellón/Castelló', 10),
('46', 'Valencia/València', 10),

-- Extremadura (id: 11)
('06', 'Badajoz', 11),
('10', 'Cáceres', 11),

-- Galicia (id: 12)
('15', 'A Coruña', 12),
('27', 'Lugo', 12),
('32', 'Ourense', 12),
('36', 'Pontevedra', 12),

-- Madrid (id: 13)
('28', 'Madrid', 13),

-- Murcia (id: 14)
('30', 'Murcia', 14),

-- Navarra (id: 15)
('31', 'Navarra', 15),

-- País Vasco (id: 16)
('01', 'Araba/Álava', 16),
('48', 'Bizkaia', 16),
('20', 'Gipuzkoa', 16),

-- La Rioja (id: 17)
('26', 'La Rioja', 17),

-- Ceuta y Melilla (ids: 18 y 19)
('51', 'Ceuta', 18),
('52', 'Melilla', 19)
    ON DUPLICATE KEY UPDATE name = VALUES(name), region_id = VALUES(region_id);