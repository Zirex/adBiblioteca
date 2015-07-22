-- MySQL dump 10.13  Distrib 5.6.20, for Win32 (x86)
--
-- Host: localhost    Database: adbiblioteca
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `lector`
--

DROP TABLE IF EXISTS `lector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lector` (
  `id_lector` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `fecha_lectura` date NOT NULL,
  `devuelto` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_lector`),
  KEY `usuario_lector_fk` (`id_usuario`),
  CONSTRAINT `usuario_lector_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lector`
--

LOCK TABLES `lector` WRITE;
/*!40000 ALTER TABLE `lector` DISABLE KEYS */;
/*!40000 ALTER TABLE `lector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lector_libro`
--

DROP TABLE IF EXISTS `lector_libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lector_libro` (
  `id_lector` int(11) NOT NULL,
  `id_libro` int(11) NOT NULL,
  `prestamo` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_lector`,`id_libro`),
  KEY `libro_lector_libro_fk` (`id_libro`),
  CONSTRAINT `lector_lector_libro_fk` FOREIGN KEY (`id_lector`) REFERENCES `lector` (`id_lector`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `libro_lector_libro_fk` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lector_libro`
--

LOCK TABLES `lector_libro` WRITE;
/*!40000 ALTER TABLE `lector_libro` DISABLE KEYS */;
/*!40000 ALTER TABLE `lector_libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libro` (
  `id_libro` int(11) NOT NULL AUTO_INCREMENT,
  `nom_libro` varchar(255) NOT NULL,
  `nom_editorial` varchar(255) NOT NULL,
  `nom_autor` varchar(60) NOT NULL,
  `grado` char(2) NOT NULL,
  `area` varchar(60) NOT NULL,
  `cota` varchar(255) NOT NULL,
  `ejemplares` int(11) NOT NULL,
  `en_servicio` int(11) NOT NULL DEFAULT '0',
  `ubicacion` varchar(40) NOT NULL,
  `prestamo` tinyint(4) NOT NULL,
  `darBaja` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_libro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prestamo` (
  `id_prestamo` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_prestamo` date NOT NULL,
  `fecha_dev` date DEFAULT NULL,
  `id_lector` int(11) NOT NULL,
  PRIMARY KEY (`id_prestamo`),
  KEY `lector_prestamo_fk` (`id_lector`),
  CONSTRAINT `lector_prestamo_fk` FOREIGN KEY (`id_lector`) REFERENCES `lector` (`id_lector`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `nom_bibliotecario` varchar(160) NOT NULL,
  `username` varchar(60) NOT NULL,
  `clave` char(64) NOT NULL,
  `tipo_rol` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Richard Maldonado','Zirex','202cb962ac59075b964b07152d234b70',1);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `ced_usuario` char(9) DEFAULT NULL,
  `nombre_usu` varchar(60) NOT NULL,
  `apellido_usu` varchar(60) NOT NULL,
  `sexo` char(1) NOT NULL,
  `fecha_nac_usu` date NOT NULL,
  `telf1_usuario` char(12) DEFAULT NULL,
  `telf2_usuario` char(12) DEFAULT NULL,
  `direccion_usu` varchar(255) DEFAULT NULL,
  `estudia` tinyint(4) NOT NULL DEFAULT '0',
  `nombre_inst` varchar(255) DEFAULT NULL,
  `grado_estudio` varchar(50) NOT NULL,
  `representante` varchar(255) DEFAULT NULL,
  `trabaja` tinyint(4) NOT NULL DEFAULT '0',
  `miembro` tinyint(4) NOT NULL DEFAULT '0',
  `fecha_expedicion` date DEFAULT NULL,
  `foto_usu` longblob,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-20 20:22:43
