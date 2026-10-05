-- ============================================
-- Base de datos: red_social
-- Examen práctico - Spring Boot y Arquitectura Hexagonal
-- Fernando Antonio García Ruiz - 7CM1
-- ============================================

CREATE DATABASE IF NOT EXISTS red_social
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE red_social;

DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios (
    id               BIGINT       NOT NULL AUTO_INCREMENT,
    nombre           VARCHAR(50)  NOT NULL,
    apellido_paterno VARCHAR(50)  NOT NULL,
    apellido_materno VARCHAR(50)  NOT NULL,
    correo           VARCHAR(100) NOT NULL,
    usuario          VARCHAR(50)  NOT NULL,
    password         VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE         NOT NULL,
    CONSTRAINT pk_usuarios PRIMARY KEY (id),
    CONSTRAINT uk_usuarios_correo UNIQUE (correo),
    CONSTRAINT uk_usuarios_usuario UNIQUE (usuario)
);

-- Indices para que la busqueda parcial sea mas rapida
CREATE INDEX idx_usuarios_nombre ON usuarios (nombre);
CREATE INDEX idx_usuarios_apellidos ON usuarios (apellido_paterno, apellido_materno);

-- Datos de prueba (password = "password123" cifrado con BCrypt)
INSERT INTO usuarios (nombre, apellido_paterno, apellido_materno, correo, usuario, password, fecha_nacimiento) VALUES
('Juan',        'Pérez',     'López',    'juan.perez@correo.com',   'juanperez',  '$2a$10$dfuC5Y3MQSpXUdIxF5z/Q.433hvXq6EBB.71NSrtrpOtTOTHDXQYe', '2001-03-15'),
('Juan Carlos', 'Hernández', 'Ramírez',  'juancarlos@correo.com',   'juancarlos', '$2a$10$dfuC5Y3MQSpXUdIxF5z/Q.433hvXq6EBB.71NSrtrpOtTOTHDXQYe', '2000-07-22'),
('Juan',        'Rodríguez', 'Martínez', 'juan.r@correo.com',       'juanr2003',  '$2a$10$dfuC5Y3MQSpXUdIxF5z/Q.433hvXq6EBB.71NSrtrpOtTOTHDXQYe', '2003-11-02'),
('Carla',       'Sánchez',   'Torres',   'carla.st@correo.com',     'carlast',    '$2a$10$dfuC5Y3MQSpXUdIxF5z/Q.433hvXq6EBB.71NSrtrpOtTOTHDXQYe', '2002-01-30'),
('Efrén',       'Castillo',  'Muñoz',    'efren.castillo@correo.com','efrencm',   '$2a$10$dfuC5Y3MQSpXUdIxF5z/Q.433hvXq6EBB.71NSrtrpOtTOTHDXQYe', '1999-05-10');

-- Consulta para ver los usuarios guardados (sin la contraseña)
SELECT id, nombre, apellido_paterno, apellido_materno, correo, usuario, fecha_nacimiento FROM usuarios;
