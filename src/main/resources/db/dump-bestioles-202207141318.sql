-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: bestioles
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `color` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `species_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ht4up5vd7qxcx9okgsdj4538` (`species_id`),
  CONSTRAINT `FK8ht4up5vd7qxcx9okgsdj4538` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES (1,'Gris tacheté','Max','M',1),(2,'Blanc','Médor','M',2),(3,'Noir','Loulou','F',1),(4,'Brun','Zia','F',2),(5,'Blanc','Lou','F',3),(6,'Roux','Minouchette','M',1);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `age` int DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,22,'Henri','Lamarque'),(2,24,'Sylvie','Lamarque'),(3,55,'Jean','Vintroi'),(4,80,'Paul','Demaine'),(5,45,'Sophie','Nero'),(6,17,'Pierre','Sansane'),(7,33,'John','Mangolo'),(8,26,'Bill','Wagner');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_animals`
--

DROP TABLE IF EXISTS `person_animals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_animals` (
  `person_id` int NOT NULL,
  `animals_id` int NOT NULL,
  PRIMARY KEY (`person_id`,`animals_id`),
  KEY `FKpt8c3kig54emvkm6a1gcq1ury` (`animals_id`),
  CONSTRAINT `FKpt8c3kig54emvkm6a1gcq1ury` FOREIGN KEY (`animals_id`) REFERENCES `animal` (`id`),
  CONSTRAINT `FKq2c74s7eeeoy8tq0sqsjmvbmw` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_animals`
--

LOCK TABLES `person_animals` WRITE;
/*!40000 ALTER TABLE `person_animals` DISABLE KEYS */;
INSERT INTO `person_animals` VALUES (2,1),(7,1),(4,2),(3,3),(5,4),(2,5),(8,6);
/*!40000 ALTER TABLE `person_animals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `species` (
  `id` int NOT NULL AUTO_INCREMENT,
  `common_name` varchar(50) DEFAULT NULL,
  `latin_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` VALUES (1,'Chat','Felis silvestris catus'),(2,'Chien','Canis lupus familiaris'),(3,'Lapin','Oryctolagus cuniculus');
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-14 11:21:49
