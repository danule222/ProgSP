-- MySQL dump 10.13  Distrib 8.0.23, for Linux (x86_64)
--
-- Host: localhost    Database: BnL
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

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
-- Table structure for table `ALMACENAMIENTO`
--

DROP TABLE IF EXISTS `ALMACENAMIENTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ALMACENAMIENTO` (
  `ID_Producto` int NOT NULL,
  `ID_Almacen` int NOT NULL,
  `Stock` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_Producto`,`ID_Almacen`),
  KEY `ID_Almacen_idx` (`ID_Almacen`),
  CONSTRAINT `ID_AlmacenALMACENAMIENTO` FOREIGN KEY (`ID_Almacen`) REFERENCES `ALMACENES` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `ID_ProdcutoALMACENAMIENTO` FOREIGN KEY (`ID_Producto`) REFERENCES `PRODUCTOS` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ALMACENAMIENTO`
--

LOCK TABLES `ALMACENAMIENTO` WRITE;
/*!40000 ALTER TABLE `ALMACENAMIENTO` DISABLE KEYS */;
INSERT INTO `ALMACENAMIENTO` VALUES (0,0,38),(0,1,43),(1,0,28),(2,0,15),(2,1,9),(3,0,3),(4,0,10),(5,0,1),(5,1,4);
/*!40000 ALTER TABLE `ALMACENAMIENTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ALMACENES`
--

DROP TABLE IF EXISTS `ALMACENES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ALMACENES` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) COLLATE utf8_bin NOT NULL,
  `Pais` varchar(45) COLLATE utf8_bin NOT NULL,
  `CA` varchar(45) COLLATE utf8_bin NOT NULL,
  `Provincia` varchar(45) COLLATE utf8_bin NOT NULL,
  `Ciudad` varchar(45) COLLATE utf8_bin NOT NULL,
  `Direccion` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ALMACENES`
--

LOCK TABLES `ALMACENES` WRITE;
/*!40000 ALTER TABLE `ALMACENES` DISABLE KEYS */;
INSERT INTO `ALMACENES` VALUES (0,'Montes de la Costa del Sol','España','Andalucía','Málaga','Ojén','Calle San Dionisio'),(1,'Polígono industrial de Antequera','España','Andalucía','Málaga','Antequera','Calle Infantería de Marina');
/*!40000 ALTER TABLE `ALMACENES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DETALLES_FACTURA`
--

DROP TABLE IF EXISTS `DETALLES_FACTURA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DETALLES_FACTURA` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_Factura` int NOT NULL,
  `ID_Producto` int NOT NULL,
  `Cantidad` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`,`ID_Factura`),
  KEY `ID_FacturaDETALLES_idx` (`ID_Factura`),
  KEY `ID_ProductoDETALLES_idx` (`ID_Producto`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DETALLES_FACTURA`
--

LOCK TABLES `DETALLES_FACTURA` WRITE;
/*!40000 ALTER TABLE `DETALLES_FACTURA` DISABLE KEYS */;
INSERT INTO `DETALLES_FACTURA` VALUES (1,3,0,3),(2,3,0,3),(3,3,0,3),(4,4,0,3),(5,4,0,3),(6,4,0,3),(7,5,0,3),(8,5,0,3),(9,5,0,3),(10,9,0,1),(11,10,0,1),(12,11,0,1),(13,12,0,1),(14,13,0,1),(15,14,0,1),(24,21,0,1),(25,22,0,1),(26,23,0,1),(27,24,0,1),(28,29,0,3),(29,30,0,3),(30,31,0,3),(31,32,0,3),(36,35,0,3),(37,35,0,3),(38,36,0,3),(39,36,0,3),(41,38,0,3),(42,38,0,3),(43,39,0,3),(44,39,0,3),(45,40,0,3),(46,40,0,3),(47,41,0,3),(48,41,0,3),(49,42,0,3),(50,42,0,3),(51,43,0,3),(52,43,0,3),(53,44,0,3),(54,44,0,3),(57,47,0,3),(58,48,0,2),(61,51,5,1),(62,51,3,1),(63,51,0,2),(64,52,3,1),(65,52,0,3),(66,53,1,2),(67,54,1,0),(68,55,0,2);
/*!40000 ALTER TABLE `DETALLES_FACTURA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLEADOS`
--

DROP TABLE IF EXISTS `EMPLEADOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EMPLEADOS` (
  `DNI` varchar(9) COLLATE utf8_bin NOT NULL,
  `Numero_Privado` bigint NOT NULL,
  `Nombre` varchar(45) COLLATE utf8_bin NOT NULL,
  `Apellidos` varchar(45) COLLATE utf8_bin NOT NULL,
  `Fecha_Nacimiento` date NOT NULL,
  `Vivienda` varchar(100) COLLATE utf8_bin NOT NULL,
  `Salario` double NOT NULL,
  `Fecha_Contratacion` date NOT NULL,
  `Puesto` varchar(45) COLLATE utf8_bin NOT NULL,
  `Ultima_Sesion` datetime NOT NULL,
  `ID_Tienda` int NOT NULL,
  PRIMARY KEY (`DNI`),
  KEY `ID_TiendaEMPLEADOS_idx` (`ID_Tienda`),
  CONSTRAINT `ID_TiendaEMPLEADOS` FOREIGN KEY (`ID_Tienda`) REFERENCES `TIENDAS` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPLEADOS`
--

LOCK TABLES `EMPLEADOS` WRITE;
/*!40000 ALTER TABLE `EMPLEADOS` DISABLE KEYS */;
INSERT INTO `EMPLEADOS` VALUES ('39485723A',1234554321,'Adamska','Ocelot','1944-06-06','Calle Pizarro',1680,'2010-02-02','Supervisor','2021-02-24 22:13:25',1),('63845923D',9876543210,'John','Vic Boss','1935-01-01','Av/ Bulevar Príncipe Alfonso de Hohenlohe',1345,'2008-03-02','Técnico','2021-02-24 22:13:28',0),('79119351H',1234567890,'Daniel','Ramírez Morilla','2000-02-22','Av/ de la Florida, n15',1200,'2018-09-15','Técnico','2021-02-24 22:24:45',0);
/*!40000 ALTER TABLE `EMPLEADOS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FACTURAS`
--

DROP TABLE IF EXISTS `FACTURAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FACTURAS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Fecha_Facturacion` datetime NOT NULL,
  `DNI_Empleado` varchar(9) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `DNI_EmpleadoFACTURAS` (`DNI_Empleado`),
  CONSTRAINT `DNI_EmpleadoFACTURAS` FOREIGN KEY (`DNI_Empleado`) REFERENCES `EMPLEADOS` (`DNI`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FACTURAS`
--

LOCK TABLES `FACTURAS` WRITE;
/*!40000 ALTER TABLE `FACTURAS` DISABLE KEYS */;
INSERT INTO `FACTURAS` VALUES (1,'2021-02-23 00:00:00','79119351H'),(2,'2021-02-23 00:00:00','79119351H'),(3,'2021-02-23 00:00:00','79119351H'),(4,'2021-02-23 00:00:00','79119351H'),(5,'2021-02-23 00:00:00','79119351H'),(6,'2021-02-23 00:00:00','79119351H'),(7,'2021-02-23 00:00:00','79119351H'),(8,'2021-02-23 00:00:00','79119351H'),(9,'2021-02-23 00:00:00','79119351H'),(10,'2021-02-23 00:00:00','79119351H'),(11,'2021-02-23 00:00:00','79119351H'),(12,'2021-02-23 00:00:00','79119351H'),(13,'2021-02-23 00:00:00','79119351H'),(14,'2021-02-23 00:00:00','79119351H'),(21,'2021-02-23 00:00:00','79119351H'),(22,'2021-02-23 00:00:00','79119351H'),(23,'2021-02-23 00:00:00','79119351H'),(24,'2021-02-23 00:00:00','79119351H'),(29,'2021-02-23 16:12:10','79119351H'),(30,'2021-02-23 16:25:02','79119351H'),(31,'2021-02-23 17:16:31','79119351H'),(32,'2021-02-23 17:17:35','79119351H'),(35,'2021-02-23 23:35:24','79119351H'),(36,'2021-02-23 23:41:02','79119351H'),(38,'2021-02-24 00:46:38','79119351H'),(39,'2021-02-24 00:48:14','79119351H'),(40,'2021-02-24 00:56:50','79119351H'),(41,'2021-02-24 00:58:42','79119351H'),(42,'2021-02-24 01:17:17','79119351H'),(43,'2021-02-24 01:17:31','79119351H'),(44,'2021-02-24 02:58:57','79119351H'),(47,'2021-02-24 03:27:41','79119351H'),(48,'2021-02-24 04:01:09','79119351H'),(51,'2021-02-24 15:51:02','79119351H'),(52,'2021-02-24 20:55:04','79119351H'),(53,'2021-02-24 21:00:53','79119351H'),(54,'2021-02-24 21:35:23','79119351H'),(55,'2021-02-24 21:43:09','79119351H');
/*!40000 ALTER TABLE `FACTURAS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCTOS`
--

DROP TABLE IF EXISTS `PRODUCTOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRODUCTOS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) COLLATE utf8_bin NOT NULL,
  `Marca` varchar(45) COLLATE utf8_bin NOT NULL,
  `Categoria` varchar(45) COLLATE utf8_bin NOT NULL,
  `Descripcion` varchar(100) COLLATE utf8_bin NOT NULL,
  `Precio_Venta` double NOT NULL,
  `Precio_Proveedor` double NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCTOS`
--

LOCK TABLES `PRODUCTOS` WRITE;
/*!40000 ALTER TABLE `PRODUCTOS` DISABLE KEYS */;
INSERT INTO `PRODUCTOS` VALUES (0,'Zumo de melocotón','Hacendado','Bebidas','Zumo de melocotón sin azúcares añadidos.',2.1,1.24),(1,'Bebida isotónica de naranja','Aquarius','Bebidas','Bebida isotónica',1.5,0.99),(2,'Consola Nintendo Switch','Nintendo','Videojuegos','Consola de videojuegos híbrida',320,280),(3,'Tarjeta gráfica Nvidia RTX 3080','Nvidia','Ordenadores','Tarjeta gráfica con capacidad de trazado de rayos',780,710),(4,'Pizza Ibérica','Hacendado','Pizzas','Pizza Ibérica congelada',1.35,0.98),(5,'PlayStation 5','Sony','Videojuegos','Consola de videojuegos de sobremesa',499,450);
/*!40000 ALTER TABLE `PRODUCTOS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TIENDAS`
--

DROP TABLE IF EXISTS `TIENDAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TIENDAS` (
  `ID` int NOT NULL,
  `Nombre` varchar(45) COLLATE utf8_bin NOT NULL,
  `Pais` varchar(45) COLLATE utf8_bin NOT NULL,
  `CA` varchar(45) COLLATE utf8_bin NOT NULL,
  `Provincia` varchar(45) COLLATE utf8_bin NOT NULL,
  `Ciudad` varchar(45) COLLATE utf8_bin NOT NULL,
  `Direccion` varchar(45) COLLATE utf8_bin NOT NULL,
  `ID_Almacen` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_AlmacenTIENDAS_idx` (`ID_Almacen`),
  CONSTRAINT `ID_AlmacenTIENDAS` FOREIGN KEY (`ID_Almacen`) REFERENCES `ALMACENES` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TIENDAS`
--

LOCK TABLES `TIENDAS` WRITE;
/*!40000 ALTER TABLE `TIENDAS` DISABLE KEYS */;
INSERT INTO `TIENDAS` VALUES (0,'Montes de la Costa del Sol','España','Andalucía','Málaga','Ojén','Calle Tomás Morales',0),(1,'Polígono industrial de Antequera','España','Andalucía','Málaga','Antequera','Calle la Sierpe',1);
/*!40000 ALTER TABLE `TIENDAS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-24 22:26:17
