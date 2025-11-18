/*Creacion BD y Tablas*/

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS ejercito /!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /!80016 DEFAULT ENCRYPTION='N' */;
USE ejercito;

CREATE TABLE IF NOT EXISTS compania (
  id_compania int NOT NULL AUTO_INCREMENT,
  denominacion_compania varchar(100) NOT NULL,
  PRIMARY KEY (id_compania)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS cuartel (
  id_cuartel int NOT NULL AUTO_INCREMENT,
  nombre_cuartel varchar(100) NOT NULL,
  ubicacion varchar(150) DEFAULT NULL,
  PRIMARY KEY (id_cuartel)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS cuerpo (
  id_cuerpo int NOT NULL AUTO_INCREMENT,
  denominacion_cuerpo varchar(100) NOT NULL,
  PRIMARY KEY (id_cuerpo)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS servicios (
  id_servicio int NOT NULL AUTO_INCREMENT,
  nombre_servicio varchar(100) NOT NULL,
  descripcion varchar(200) DEFAULT NULL,
  PRIMARY KEY (id_servicio)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS usuario (
  id_user int NOT NULL AUTO_INCREMENT,
  nombre varchar(100) NOT NULL,
  apellido varchar(100) NOT NULL,
  rol enum('SOLDADO','SUBOFICIAL','OFICIAL') NOT NULL,
  id_compania int NOT NULL,
  id_cuerpo int NOT NULL,
  id_cuartel int NOT NULL,
  contrasenia varchar(255) DEFAULT NULL,
  email varchar(30) DEFAULT NULL,
  PRIMARY KEY (id_user),
  KEY id_compania (id_compania),
  KEY id_cuerpo (id_cuerpo),
  KEY id_cuartel (id_cuartel),
  CONSTRAINT usuario_ibfk_1 FOREIGN KEY (id_compania) REFERENCES compania (id_compania),
  CONSTRAINT usuario_ibfk_2 FOREIGN KEY (id_cuerpo) REFERENCES cuerpo (id_cuerpo),
  CONSTRAINT usuario_ibfk_3 FOREIGN KEY (id_cuartel) REFERENCES cuartel (id_cuartel)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS realizaservicio (
  id_user int NOT NULL,
  id_servicio int NOT NULL,
  fecha_realizacion date NOT NULL,
  fecha_completado datetime NOT NULL,
  estado boolean DEFAULT NULL,
  PRIMARY KEY (id_user,id_servicio,fecha_realizacion,fecha_completado),
  KEY id_servicio (id_servicio),
  CONSTRAINT realizaservicio_ibfk_1 FOREIGN KEY (id_user) REFERENCES usuario (id_user),
  CONSTRAINT realizaservicio_ibfk_2 FOREIGN KEY (id_servicio) REFERENCES servicios (id_servicio)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
