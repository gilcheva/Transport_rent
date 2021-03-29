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


-- Dumping data for table vehicles.vehicle_types: ~5 rows (approximately)
/*!40000 ALTER TABLE `vehicle_types` DISABLE KEYS */;
REPLACE INTO `vehicle_types` (`vehicle_type_id`, `vehicle_type`) VALUES
	(2, 'BUS'),
	(1, 'CAR'),
	(3, 'PLANE'),
	(5, 'SHIP'),
	(4, 'TRAIN');
/*!40000 ALTER TABLE `vehicle_types` ENABLE KEYS */;


-- Dumping data for table vehicles.vehicles: ~4 rows (approximately)
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
REPLACE INTO `vehicles` (`vehicle_id`, `vehicle_type_id`, `registration_number`, `load_capacity`, `price_per_km`, `charter`, `carts`) VALUES
	(1, 3, '1234', 80, 20, 0, 0),
	(2, 1, '5678', 40, 6, 0, 0),
	(3, 5, '0112', 40, 2, 0, 0),
	(4, 4, '5060', 40, 2, 0, 3);
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;


-- Dumping data for table vehicles.courses: ~4 rows (approximately)
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
REPLACE INTO `courses` (`course_id`, `vehicle_id`, `start_location`, `destination`, `distance`) VALUES
	(1, 1, 'Sofia', 'Plovdiv', 30),
	(2, 2, 'Sofia', 'Varna', 30),
	(3, 1, 'Plovdiv', 'Sofia', 30),
	(4, 2, 'Varna', 'Sofia', 30);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;

-- Dumping data for table vehicles.parkings: ~2 rows (approximately)
/*!40000 ALTER TABLE `parkings` DISABLE KEYS */;
REPLACE INTO `parkings` (`parking_id`, `vehicle_type_id`, `name`, `capacity`, `hourly_rate`) VALUES
	(1, 3, 'Terminal1', 30, 700),
	(2, 1, 'Terminal2', 30, 700);
/*!40000 ALTER TABLE `parkings` ENABLE KEYS */;

-- Dumping data for table vehicles.rents: ~1 rows (approximately)
/*!40000 ALTER TABLE `rents` DISABLE KEYS */;
REPLACE INTO `rents` (`rent_id`, `course_id`, `additional_costs`) VALUES
	(1, 1, 20);
/*!40000 ALTER TABLE `rents` ENABLE KEYS */;


/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
