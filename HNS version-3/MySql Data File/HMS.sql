-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: hms
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `name` varchar(30) DEFAULT NULL,
  `mail` varchar(30) DEFAULT NULL,
  `phoneNO` varchar(30) DEFAULT NULL,
  `roomNO` varchar(30) DEFAULT NULL,
  `qualification` varchar(30) DEFAULT NULL,
  `din` varchar(30) NOT NULL,
  `activeHour` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`din`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES ('Dr. Steven D. Wolff','wolff@sample.com','(212) 369-9200','1A','Diagnostic Radiology','DR-01','3 pm to 6 pm'),(' Dr. Eamonn A. Vitt',' eamonn@sample.com','(332) 895-4133','4A','Family Medicine','FM-01','9 am to 3pm'),('Dr. JP Mohr',' jpMohr@sample.com','(212) 305-8033','2A','Neurology','N-01','5 pm to 8 pm');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctorbooking`
--

DROP TABLE IF EXISTS `doctorbooking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctorbooking` (
  `doctorName` varchar(30) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `fee` varchar(20) DEFAULT NULL,
  `paymentOption` varchar(20) DEFAULT NULL,
  `CNIC` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctorbooking`
--

LOCK TABLES `doctorbooking` WRITE;
/*!40000 ALTER TABLE `doctorbooking` DISABLE KEYS */;
INSERT INTO `doctorbooking` VALUES ('Dr. Mark Emil Horowitz','4 pm','10$','cash','190204093'),('Dr. Eamonn A. Vitt','10 am','5$','cash','190204100');
/*!40000 ALTER TABLE `doctorbooking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency`
--

DROP TABLE IF EXISTS `emergency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emergency` (
  `Type` varchar(20) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency`
--

LOCK TABLES `emergency` WRITE;
/*!40000 ALTER TABLE `emergency` DISABLE KEYS */;
INSERT INTO `emergency` VALUES ('Immediate Danger','911'),('NYPD','646-610-5000 '),('Fire Service','01730-002227');
/*!40000 ALTER TABLE `emergency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hmslogin`
--

DROP TABLE IF EXISTS `hmslogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hmslogin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hmslogin`
--

LOCK TABLES `hmslogin` WRITE;
/*!40000 ALTER TABLE `hmslogin` DISABLE KEYS */;
INSERT INTO `hmslogin` VALUES ('admin','admin');
/*!40000 ALTER TABLE `hmslogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `name` varchar(30) DEFAULT NULL,
  `fatherName` varchar(20) DEFAULT NULL,
  `CNIC` varchar(20) NOT NULL,
  `illness` varchar(20) DEFAULT NULL,
  `doctor` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('MD Fardin Jaman Aranyak','Jaman Talukder','190204093','covid-19','Dr. Mark Emil Horowitz'),('MD Symum Hossain','Belal Hossain','190204105','Muscle pain','Dr. JP Mohr'),('Aninda Mazumder Adi','Mazumder','190204100','skin','Dr. Eamonn A. Vitt');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worker` (
  `name` varchar(20) DEFAULT NULL,
  `work_type` varchar(30) DEFAULT NULL,
  `contact` varchar(20) NOT NULL,
  `salary` varchar(20) DEFAULT NULL,
  `active_hour` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`contact`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES ('Simon Fraser','Chief Operating Officer','01710001','30000','8 am to 5 pm'),('Matthew Rycroft','Director Human Resources','01710002','25000','8 am to 5 pm'),('Iain Walker','Director Finance','01710003','20000','8 am to 5 pm'),('Paul','Computer Operator','01724615130','23000','8 am to 8 pm');
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-28 20:34:01
