/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `ejercito` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ejercito`;

CREATE TABLE IF NOT EXISTS `compania` (
  `id_compania` int NOT NULL AUTO_INCREMENT,
  `denominacion_compania` varchar(100) NOT NULL,
  PRIMARY KEY (`id_compania`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `compania` (`id_compania`, `denominacion_compania`) VALUES
	(1, 'Alpha'),
	(2, 'Bravo'),
	(3, 'Charlie'),
	(4, 'Delta'),
	(5, 'Echo'),
	(6, 'Foxtrot'),
	(7, 'Gamma'),
	(8, 'Omega'),
	(9, 'Sierra'),
	(10, 'Zulu');

CREATE TABLE IF NOT EXISTS `cuartel` (
  `id_cuartel` int NOT NULL AUTO_INCREMENT,
  `nombre_cuartel` varchar(100) NOT NULL,
  `ubicacion` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id_cuartel`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `cuartel` (`id_cuartel`, `nombre_cuartel`, `ubicacion`) VALUES
	(1, 'Cuartel Central', 'Buenos Aires'),
	(2, 'Base Norte', 'Salta'),
	(3, 'Base Sur', 'Ushuaia'),
	(4, 'Base Oeste', 'Mendoza'),
	(5, 'Base Este', 'Mar del Plata'),
	(6, 'Fortaleza Azul', 'Córdoba'),
	(7, 'Campo Militar', 'Santa Fe'),
	(8, 'Regimiento Rojo', 'La Plata'),
	(9, 'Área Militar 9', 'San Juan'),
	(10, 'División Verde', 'Rosario');

CREATE TABLE IF NOT EXISTS `cuerpo` (
  `id_cuerpo` int NOT NULL AUTO_INCREMENT,
  `denominacion_cuerpo` varchar(100) NOT NULL,
  PRIMARY KEY (`id_cuerpo`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `cuerpo` (`id_cuerpo`, `denominacion_cuerpo`) VALUES
	(1, 'Infantería'),
	(2, 'Artillería'),
	(3, 'Caballería'),
	(4, 'Ingenieros'),
	(5, 'Comunicaciones'),
	(6, 'Sanidad'),
	(7, 'Inteligencia'),
	(8, 'Logística'),
	(9, 'Blindados'),
	(10, 'Aeronáutica');

CREATE TABLE IF NOT EXISTS `realizaservicio` (
  `id_user` int NOT NULL,
  `id_servicio` int NOT NULL,
  `fecha_realizacion` date NOT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `fecha_completado` datetime DEFAULT NULL,
  PRIMARY KEY (`id_user`,`id_servicio`,`fecha_realizacion`),
  KEY `id_servicio` (`id_servicio`),
  CONSTRAINT `realizaservicio_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `usuario` (`id_user`),
  CONSTRAINT `realizaservicio_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `realizaservicio` (`id_user`, `id_servicio`, `fecha_realizacion`, `estado`, `fecha_completado`) VALUES
	(1, 1, '2025-01-10', 1, '2025-11-18 16:17:10'),
	(1, 2, '2025-11-17', 1, '2025-11-17 21:52:42'),
	(1, 3, '2025-11-17', 1, '2025-11-17 21:52:51'),
	(2, 1, '2025-01-11', 0, '2025-01-11 00:00:00'),
	(3, 4, '2025-01-15', 0, '2025-01-15 00:00:00'),
	(4, 1, '2025-11-17', 0, '2025-11-17 00:00:00'),
	(4, 4, '2025-11-17', 1, '2025-11-17 22:33:31'),
	(4, 7, '2025-01-14', 0, '2025-01-14 00:00:00'),
	(5, 8, '2025-01-13', 0, '2025-01-13 00:00:00'),
	(6, 9, '2025-01-16', 0, '2025-01-16 00:00:00'),
	(7, 6, '2025-01-16', 0, '2025-01-16 00:00:00'),
	(8, 10, '2025-01-18', 0, '2025-01-18 00:00:00'),
	(9, 3, '2025-01-19', 0, '2025-01-19 00:00:00');

CREATE TABLE IF NOT EXISTS `servicios` (
  `id_servicio` int NOT NULL AUTO_INCREMENT,
  `nombre_servicio` varchar(100) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_servicio`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `servicios` (`id_servicio`, `nombre_servicio`, `descripcion`) VALUES
	(1, 'Guardia', 'Servicio de vigilancia'),
	(2, 'Patrulla', 'Recorrido del perímetro'),
	(3, 'Mantenimiento', 'Revisión de instalaciones'),
	(4, 'Cocina', 'Preparación de alimentos'),
	(5, 'Comunicaciones', 'Monitoreo de equipos de radio'),
	(6, 'Atención médica', 'Apoyo sanitario'),
	(7, 'Transporte', 'Traslado de personal/equipos'),
	(8, 'Entrenamiento', 'Prácticas militares'),
	(9, 'Armamento', 'Control y limpieza de armas'),
	(10, 'Logística', 'Gestión de suministros'),
	(11, 'asd', 'asd');

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `rol` enum('SOLDADO','SUBOFICIAL','OFICIAL') NOT NULL,
  `id_compania` int NOT NULL,
  `id_cuerpo` int NOT NULL,
  `id_cuartel` int NOT NULL,
  `contrasenia` varchar(255) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `id_compania` (`id_compania`),
  KEY `id_cuerpo` (`id_cuerpo`),
  KEY `id_cuartel` (`id_cuartel`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_compania`) REFERENCES `compania` (`id_compania`),
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`id_cuerpo`) REFERENCES `cuerpo` (`id_cuerpo`),
  CONSTRAINT `usuario_ibfk_3` FOREIGN KEY (`id_cuartel`) REFERENCES `cuartel` (`id_cuartel`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuario` (`id_user`, `nombre`, `apellido`, `rol`, `id_compania`, `id_cuerpo`, `id_cuartel`, `contrasenia`, `email`) VALUES
	(1, 'Juan', 'Perez', 'SOLDADO', 1, 1, 1, '$2a$12$ShnqL3rxrRwRY7.7XlY64exOl54YZLeVzeMdNZDJIAHgpDv0dvfgK', 'c@c.com'),
	(2, 'Carlos', 'Gómez', 'SOLDADO', 2, 2, 2, '$2a$12$ShnqL3rxrRwRY7.7XlY64exOl54YZLeVzeMdNZDJIAHgpDv0dvfgK', 'Carlos@gmail.com'),
	(3, 'Luis', 'Martínez', 'SOLDADO', 3, 3, 3, '$2a$12$ShnqL3rxrRwRY7.7XlY64exOl54YZLeVzeMdNZDJIAHgpDv0dvfgK', 'Luis@gmail.com'),
	(4, 'Ana', 'Ledesma', 'SUBOFICIAL', 4, 4, 4, '$2a$12$ShnqL3rxrRwRY7.7XlY64exOl54YZLeVzeMdNZDJIAHgpDv0dvfgK', 'b@b.com'),
	(5, 'Sofía', 'Arias', 'SUBOFICIAL', 5, 5, 5, '$2a$12$ShnqL3rxrRwRY7.7XlY64exOl54YZLeVzeMdNZDJIAHgpDv0dvfgK', 'Sofía@gmail.com'),
	(6, 'Diego', 'Ramos', 'SUBOFICIAL', 6, 6, 6, '$2a$12$ShnqL3rxrRwRY7.7XlY64exOl54YZLeVzeMdNZDJIAHgpDv0dvfgK', 'Diego@gmail.com'),
	(7, 'Mariano', 'Ruiz', 'OFICIAL', 7, 7, 7, '$2a$12$ShnqL3rxrRwRY7.7XlY64exOl54YZLeVzeMdNZDJIAHgpDv0dvfgK', 'a@a.com'),
	(8, 'Lucía', 'Molina', 'OFICIAL', 8, 8, 8, '$2a$12$ShnqL3rxrRwRY7.7XlY64exOl54YZLeVzeMdNZDJIAHgpDv0dvfgK', 'Lucía@gmail.com'),
	(9, 'Esteban', 'Silva', 'OFICIAL', 9, 9, 9, '$2a$12$ShnqL3rxrRwRY7.7XlY64exOl54YZLeVzeMdNZDJIAHgpDv0dvfgK', 'Esteban@gmail.com'),
	(10, 'Paula', 'Fernández', 'SOLDADO', 10, 10, 10, '$2a$12$ShnqL3rxrRwRY7.7XlY64exOl54YZLeVzeMdNZDJIAHgpDv0dvfgK', 'Paula@gmail.com');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
