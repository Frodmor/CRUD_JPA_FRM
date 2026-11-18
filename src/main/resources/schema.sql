-- Desactivar temporalmente la comprobación de claves foráneas para recrear tablas limpiamente
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS provinces;
DROP TABLE IF EXISTS regions;

SET FOREIGN_KEY_CHECKS = 1;

-- Tabla de Regiones (Comunidades Autónomas)
CREATE TABLE regions (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         code VARCHAR(2) NOT NULL UNIQUE,
                         name VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de Provincias
CREATE TABLE provinces (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           code VARCHAR(2) NOT NULL UNIQUE,
                           name VARCHAR(100) NOT NULL,
                           region_id BIGINT NOT NULL,
                           CONSTRAINT fk_provinces_regions FOREIGN KEY (region_id)
                               REFERENCES regions (id)
                               ON DELETE CASCADE
                               ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;