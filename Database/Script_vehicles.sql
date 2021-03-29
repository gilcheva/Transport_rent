-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.12-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for vehicles
DROP DATABASE IF EXISTS `vehicles`;
CREATE DATABASE IF NOT EXISTS `vehicles` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `vehicles`;


-- Dumping structure for table vehicles.vehicle_types
DROP TABLE IF EXISTS `vehicle_types`;
CREATE TABLE IF NOT EXISTS `vehicle_types` (
  `vehicle_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_type` nvarchar(20) NOT NULL,
  PRIMARY KEY (`vehicle_type_id`),
  UNIQUE KEY `vehicle_types_vehicle_type_uindex` (`vehicle_type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


-- Dumping structure for table vehicles.vehicles
DROP TABLE IF EXISTS `vehicles` ;
CREATE TABLE IF NOT EXISTS `vehicles` (
  `vehicle_id` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_type_id` int(11) DEFAULT NULL,
  `registration_number` nvarchar(30) NOT NULL,
  `load_capacity` int(11) DEFAULT 0,
  `price_per_km` double DEFAULT 0,
  `charter` tinyint(4) DEFAULT 0,
  `carts` int(4) DEFAULT 0,
  PRIMARY KEY (`vehicle_id`),
  UNIQUE KEY `vehicles_registration_number_uindex` (`registration_number`),
  KEY `vehicles_vehicle_types_vehicle_type_id_fk` (`vehicle_type_id`),
  CONSTRAINT `vehicles_vehicle_types_vehicle_type_id_fk` FOREIGN KEY (`vehicle_type_id`) REFERENCES `vehicle_types` (`vehicle_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

-- Dumping structure for table vehicles.courses
DROP TABLE IF EXISTS `courses`;
CREATE TABLE IF NOT EXISTS `courses` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_id` int(11) DEFAULT NULL,
  `start_location` nvarchar(30) NOT NULL,
  `destination` nvarchar(30) NOT NULL,
  `distance` int(11) NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `courses_vehicles_vehicle_id_fk` (`vehicle_id`),
  CONSTRAINT `courses_vehicles_vehicle_id_fk` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping structure for table vehicles.parkings
DROP TABLE IF EXISTS `parkings`;
CREATE TABLE IF NOT EXISTS `parkings` (
  `parking_id` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_type_id` int(11) DEFAULT NULL,
  `name` nvarchar(30) NOT NULL,
  `capacity` int(11) DEFAULT 1,
  `hourly_rate` double DEFAULT 0,
  PRIMARY KEY (`parking_id`),
  UNIQUE KEY `parkings_name_uindex` (`name`),
  KEY `parkings_vehicle_types_vehicle_type_id_fk` (`vehicle_type_id`),
  CONSTRAINT `parkings_vehicle_types_vehicle_type_id_fk` FOREIGN KEY (`vehicle_type_id`) REFERENCES `vehicle_types` (`vehicle_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping structure for table vehicles.parking_tickets
DROP TABLE IF EXISTS `parking_tickets`;
CREATE TABLE IF NOT EXISTS `parking_tickets` (
  `parking_ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_id` int(11) DEFAULT NULL,
  `parking_id` int(11) DEFAULT NULL,
  `entrance_time` datetime DEFAULT NULL,
  `exit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`parking_ticket_id`),
  KEY `parking_tickets_parkings_parking_id_fk` (`parking_id`),
  KEY `parking_tickets_vehicles_vehicle_id_fk` (`vehicle_id`),
  CONSTRAINT `parking_tickets_parkings_parking_id_fk` FOREIGN KEY (`parking_id`) REFERENCES `parkings` (`parking_id`),
  CONSTRAINT `parking_tickets_vehicles_vehicle_id_fk` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping structure for table vehicles.rents
DROP TABLE IF EXISTS `rents`;
CREATE TABLE IF NOT EXISTS `rents` (
  `rent_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `additional_costs` double DEFAULT 0,
  PRIMARY KEY (`rent_id`),
  KEY `rents_courses_course_id_fk` (`course_id`),
  CONSTRAINT `rents_courses_course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
