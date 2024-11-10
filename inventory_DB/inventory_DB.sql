CREATE DATABASE  IF NOT EXISTS `inventory_management_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `inventory_management_system`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: inventory_management_system
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `productName` varchar(100) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,1,'Drinks','Coca-cola','Sprite',1,25,'2024-10-29'),(105,1,'Personal Product','Zara','T-Shirt',1,500,'2024-10-29'),(106,1,'Drinks','Coca-cola','Sprite',1,25,'2024-10-29'),(107,1,'Dessert','Ros','Premium Sweet',7,3150,'2024-11-07'),(108,2,'Drinks','Coca-cola','Sprite',1,25,'2024-11-07'),(109,2,'Drinks','Coca-cola','Sprite',1,25,'2024-11-07'),(110,3,'Dessert','Ros','Premium Sweet',1,450,'2024-11-07'),(112,4,'Drinks','Jak','kill',1,500,'2024-11-07'),(117,5,'Drinks','MOJO','fanta',2,70,'2024-11-08'),(118,5,'Drinks','MOJO','fanta',2,70,'2024-11-08'),(119,5,'Drinks','MOJO','fanta',3,105,'2024-11-08'),(120,6,'Drinks','Coca-cola','Sprite',1,25,'2024-11-10');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_receipt`
--

DROP TABLE IF EXISTS `customer_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_receipt` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_receipt`
--

LOCK TABLES `customer_receipt` WRITE;
/*!40000 ALTER TABLE `customer_receipt` DISABLE KEYS */;
INSERT INTO `customer_receipt` VALUES (1,2,43495,50000,6505,'2024-10-29'),(2,3,525,50000,6505,'2024-10-29'),(3,2,43495,50000,6505,'2024-10-29'),(4,3,910,1000,90,'2024-10-29'),(5,1,3700,4000,300,'2024-11-07'),(6,2,50,100,50,'2024-11-07'),(7,3,450,500,50,'2024-11-07'),(8,4,500,0,0,'2024-11-07'),(9,5,245,500,255,'2024-11-08'),(10,6,25,0,0,'2024-11-10');
/*!40000 ALTER TABLE `customer_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_login`
--

DROP TABLE IF EXISTS `employee_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_login` (
  `UserName` varchar(100) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_login`
--

LOCK TABLES `employee_login` WRITE;
/*!40000 ALTER TABLE `employee_login` DISABLE KEYS */;
INSERT INTO `employee_login` VALUES ('employee','employee');
/*!40000 ALTER TABLE `employee_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `UserName` varchar(100) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('admin','admin');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `brand` varchar(150) DEFAULT NULL,
  `productName` varchar(150) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` varchar(150) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (10,1,'Drinks','Coca-cola','Sprite',25,'Available','2024-10-24','D:\\\\Java - 2024\\\\Final 0.1\\\\Inventory Management System\\\\img\\\\logo.jpg'),(11,2,'Dessert','Ros','Premium Sweet',450,'Available','2024-10-24','D:\\\\Java - 2024\\\\Final 0.1\\\\Inventory Management System\\\\img\\\\main-logo.png'),(12,3,'Personal','Person','user',500,'Available','2024-10-24','D:\\\\Java - 2024\\\\Final 0.1\\\\Inventory Management System\\\\img\\\\user.png'),(13,4,'Gadgets','Person','user',500,'Not Available','2024-11-07','D:\\\\Java - 2024\\\\Final 0.1\\\\Inventory Management System\\\\img\\\\user.png'),(17,5,'Drinks','MOJO','fanta',35,'Available','2024-10-29','D:\\\\Java - 2024\\\\Final 0.1\\\\Inventory Management System\\\\img\\\\logo.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (1,'rahim','karim',3352221,'userkj','adasd233'),(2,'rahim','karim',3352221,'userkj','adasd233'),(3,'asdsd','sdsd',54654,'sdsd','sdsd');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-10 19:25:09
