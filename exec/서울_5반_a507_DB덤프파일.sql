CREATE DATABASE  IF NOT EXISTS `moweb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `moweb`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: i7a507.p.ssafy.io    Database: moweb
-- ------------------------------------------------------
-- Server version	5.7.39

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
-- Table structure for table `room_info`
--

DROP TABLE IF EXISTS `room_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_info` (
  `room_no` int(11) NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`room_no`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_info`
--

LOCK TABLES `room_info` WRITE;
/*!40000 ALTER TABLE `room_info` DISABLE KEYS */;
INSERT INTO `room_info` VALUES (1,_binary '\0','2022.08.16 23:50:53','/room/8fee2197-7f17-4988-9f14-0a6f99a2e62f'),(2,_binary '\0','2022.08.16 23:55:06','/room/7b90f79b-b404-44c4-b754-3f8cba2e357b'),(3,_binary '\0','2022.08.16 23:59:10','/room/acc435e9-0346-424b-83a8-67a92ec7386e'),(4,_binary '\0','2022.08.16 23:59:12','/room/4dea67dc-c59e-4205-948a-cecc6c0e4298'),(5,_binary '\0','2022.08.17 00:04:11','/room/b6b3b5f6-e5db-4264-93a8-970d98f17686'),(6,_binary '\0','2022.08.17 00:07:21','/room/4f4d4bee-7c70-4aef-85f2-f2c1c7f45fbb'),(7,_binary '\0','2022.08.17 00:09:43','/room/ac9e9fff-59ac-4b78-9db3-7db8c59454b3'),(8,_binary '\0','2022.08.17 00:30:39','/room/b732fa92-d705-4812-ae9c-003144cc4d04'),(9,_binary '\0','2022.08.17 00:33:01','/room/1a5e9431-eff5-45cb-b2b8-c4de3a811598'),(10,_binary '\0','2022.08.17 00:56:55','/room/06816b3d-93f7-4850-9756-2675d9921905'),(11,_binary '\0','2022.08.17 00:58:51','/room/be72bff2-a0a6-4a9f-9299-dcab3a1f3d42'),(12,_binary '\0','2022.08.17 01:03:11','/room/4948b48e-693f-4dd6-afb4-fc1d85c9d8d3'),(13,_binary '\0','2022.08.17 01:08:57','/room/d75587d6-b529-4b4e-87cb-d3f6390229c8'),(14,_binary '\0','2022.08.17 01:34:46','/room/0ffbb08a-0a49-4b40-88ec-64d476b2a630'),(15,_binary '\0','2022.08.17 01:35:14','/room/403c6554-71ea-43fa-8c5b-a17abd71ed2c'),(16,_binary '\0','2022.08.17 01:45:45','/room/83f90f37-e700-4ae7-91cc-6dc6ae1c1698'),(17,_binary '\0','2022.08.17 01:49:54','/room/08a9cdd0-b1a8-4ee4-a72c-d49834471668'),(18,_binary '\0','2022.08.17 01:50:57','/room/170ac6af-be29-4109-8866-8c42ec741f4b'),(19,_binary '\0','2022.08.17 02:02:13','/room/277d75d3-e4b9-4a92-94ce-d8736e0bf813'),(20,_binary '\0','2022.08.17 02:04:29','/room/401179a4-df33-4d4f-b600-7854b4a8a105'),(21,_binary '\0','2022.08.17 02:06:08','/room/7f4f9dbc-3a4b-4896-aedf-80e1057068e1'),(22,_binary '\0','2022.08.17 02:08:32','/room/d24bbbdb-1361-4ab2-ba7a-6325e7ffc96d'),(23,_binary '\0','2022.08.17 10:23:52','/room/64f21062-0638-42dd-b8d7-533455309b4d'),(24,_binary '\0','2022.08.17 10:25:31','/room/e63b2dc4-95fb-40f5-9b63-790c3e57f364'),(25,_binary '\0','2022.08.17 10:28:55','/room/f9d447a4-4280-4b21-a30f-cabf3460dc81'),(26,_binary '\0','2022.08.17 10:30:15','/room/229efb8f-c3ec-4e90-82ab-d3fbeef957dd'),(27,_binary '\0','2022.08.17 10:31:46','/room/4f55610b-5d43-4411-ac23-05fced3d46b8'),(28,_binary '\0','2022.08.17 10:32:05','/room/eecfb011-1bfc-40a5-82de-1c1f82087be3'),(29,_binary '\0','2022.08.17 10:32:11','/room/5f654c57-d7f4-4b9c-940a-08c6f2bc559e'),(30,_binary '\0','2022.08.17 10:32:21','/room/a0f7a475-705c-41b7-b07b-e0d8a77bd4ca'),(31,_binary '\0','2022.08.17 10:32:24','/room/07d8cd1c-64cf-44dd-8fee-3cd7d3738e54'),(32,_binary '\0','2022.08.17 10:35:23','/room/9b06c814-0d47-4984-8ce4-f45c48df4452'),(33,_binary '\0','2022.08.17 10:37:02','/room/04b31fec-a763-4007-936b-605e628ad4fb'),(34,_binary '\0','2022.08.17 10:41:09','/room/d0317e9c-92b1-4cd4-b128-389e9c428a75'),(35,_binary '\0','2022.08.17 10:41:15','/room/073c95e6-94ed-4f01-b674-b05e4c457d36'),(36,_binary '\0','2022.08.17 10:41:26','/room/928cdbce-684c-4ef9-81f7-d2f18db77453'),(37,_binary '\0','2022.08.17 10:42:16','/room/c7b00b0a-c877-4d66-b8ed-f707be0cb693'),(38,_binary '\0','2022.08.17 10:42:24','/room/b041e23d-6bd8-41d4-9ac1-333feacea230'),(39,_binary '\0','2022.08.17 10:43:04','/room/4c17ad50-f666-480e-98f3-59247a01296c'),(40,_binary '\0','2022.08.17 10:43:04','/room/b4f9cff0-bf80-426e-9fea-0117d1c5febf'),(41,_binary '\0','2022.08.17 10:45:00','/room/f78e4ca6-e259-43a5-b3e7-94ee5c30cbfa'),(42,_binary '\0','2022.08.17 10:47:17','/room/a023eb06-5444-44f5-8ede-98223ab0157d'),(43,_binary '\0','2022.08.17 10:47:46','/room/fc51c5af-e1eb-430e-a162-f7cb93ea6b45'),(44,_binary '\0','2022.08.17 10:48:15','/room/940d8365-ecc5-47c4-8429-c0caa5ec94bc'),(45,_binary '\0','2022.08.17 10:48:28','/room/0100ea87-0101-4bd6-b246-13c51604305e'),(46,_binary '\0','2022.08.17 10:48:29','/room/76ce4c46-6cce-4d79-9a85-cedcbd080894'),(47,_binary '\0','2022.08.17 10:49:04','/room/d1c78d0a-c94f-43fc-b83b-90fc2aaab5ac'),(48,_binary '\0','2022.08.17 10:49:09','/room/abe345c1-73a2-44ad-931f-125c17f50c22'),(49,_binary '\0','2022.08.17 10:49:14','/room/1cac452d-3fe7-4c21-87b1-424e5aa807c3'),(50,_binary '\0','2022.08.17 10:49:33','/room/52f5843a-3c42-4b06-9fd1-1a1b85978b71'),(51,_binary '\0','2022.08.17 10:50:52','/room/7c82e7fa-c63a-4f27-936c-6a3a37611dd1'),(52,_binary '\0','2022.08.17 10:51:13','/room/5678116d-39a2-4ea8-a8be-fba8ce243ad3'),(53,_binary '\0','2022.08.17 10:51:43','/room/fb38ae1c-3c7a-47b9-b67b-1fb2dd901281'),(54,_binary '\0','2022.08.17 10:51:52','/room/f83e2dc4-2850-4af9-a9ff-612237dcce57'),(55,_binary '\0','2022.08.17 10:53:03','/room/0d50b88f-ed62-42c3-b3e2-03d82c06ae84'),(56,_binary '\0','2022.08.17 10:53:56','/room/69f18e16-9e62-4976-8c32-671120950327'),(57,_binary '\0','2022.08.17 10:57:50','/room/ce166358-473c-4b9d-9db2-2ed940500f07'),(58,_binary '\0','2022.08.17 10:57:58','/room/1bcabd6d-8da1-4580-9804-158ef6c0ac47'),(59,_binary '\0','2022.08.17 10:58:08','/room/ae21e8f0-1fe9-4020-a1a2-eddb987586e6'),(60,_binary '\0','2022.08.17 10:58:15','/room/d97b8bf6-9a80-457a-8ef8-ed2569f9d3da'),(61,_binary '\0','2022.08.17 10:58:51','/room/87d8f869-7e55-4499-b3de-c565a902e44b'),(62,_binary '\0','2022.08.17 10:58:59','/room/9b22d0a6-d8b9-4ecc-b876-6fead30d9c25'),(63,_binary '\0','2022.08.17 10:59:02','/room/fab526ad-3709-48a7-bac0-03d64d650c65'),(64,_binary '\0','2022.08.17 10:59:05','/room/4c155340-6f27-4841-b540-1ecd4176ca18'),(65,_binary '\0','2022.08.17 10:59:10','/room/f0ea1a60-7806-4283-99db-18429aebccd0'),(66,_binary '\0','2022.08.17 10:59:14','/room/793da0c5-cfd8-4c5e-8653-fae7795ddd48'),(67,_binary '\0','2022.08.17 10:59:17','/room/fe1e3769-0929-4235-9d34-6b96dbd6f40a'),(68,_binary '\0','2022.08.17 10:59:18','/room/a9a169dc-493e-4064-a32c-a317ae523837'),(69,_binary '\0','2022.08.17 10:59:21','/room/28055b60-82db-46f9-a841-8ef4dab92fed'),(70,_binary '\0','2022.08.17 11:00:13','/room/7d448cdc-84ea-4610-8efe-c38caf43adf7'),(71,_binary '\0','2022.08.17 11:00:17','/room/19161828-ae1b-415e-8cc3-1013dde1dab8'),(72,_binary '\0','2022.08.17 11:00:21','/room/d3942644-3bb2-4898-a396-1bfe56215fcd'),(73,_binary '\0','2022.08.17 11:00:22','/room/1e784969-a059-42e4-bade-b585b01bbd64'),(74,_binary '\0','2022.08.17 11:02:36','/room/f83e2cbb-4c25-40b7-b2c0-cd782f28bd05'),(75,_binary '\0','2022.08.17 11:04:32','/room/03ea84ca-cfcb-401f-b7de-ebeef2b056c0'),(76,_binary '\0','2022.08.17 11:06:45','/room/aa2fc387-b943-4d24-b3b2-e6baf09882d9'),(77,_binary '\0','2022.08.17 11:07:03','/room/29dd53ed-041e-4ec4-bf4d-5c940b4df97b'),(78,_binary '\0','2022.08.17 11:07:33','/room/515591a7-e1a5-4da0-a565-4e29ead6dc24'),(79,_binary '\0','2022.08.17 11:08:01','/room/a19cb003-1ae1-461b-9aea-22970366c5e4'),(80,_binary '\0','2022.08.17 11:08:30','/room/fff2accf-8212-4cc3-867d-cfac75ed0225'),(81,_binary '\0','2022.08.17 11:08:38','/room/6129274d-cc6e-42da-9da2-dd7bbe93a7bc'),(82,_binary '\0','2022.08.17 11:09:34','/room/09adca55-0f95-4f3f-bfbb-8dccb283819a'),(83,_binary '\0','2022.08.17 11:10:15','/room/6d4d3605-9054-40bd-95ad-504c7d288452'),(84,_binary '\0','2022.08.17 11:10:31','/room/90811078-20c2-4fa8-a95c-4ab6e0b0a8a3'),(85,_binary '\0','2022.08.17 11:10:50','/room/e0043a3c-7bf6-477b-9e1a-bcf249ad72ed'),(86,_binary '\0','2022.08.17 13:14:58','/room/27ac7ea2-4897-45f0-985b-6dfd2a424cf5'),(87,_binary '\0','2022.08.17 13:16:29','/room/e8be449f-c288-4b35-a863-9d37de9ba0da'),(88,_binary '\0','2022.08.17 13:16:41','/room/6418703b-9fed-4228-b99e-e39bc5d80d61'),(89,_binary '\0','2022.08.17 13:18:44','/room/24cef125-f526-44a4-9371-696592d8b9ee'),(90,_binary '\0','2022.08.17 14:57:42','/room/c9a4dc14-a353-4f26-a9af-70b1927387df'),(91,_binary '\0','2022.08.17 15:02:33','/room/cd529e92-93e4-44dc-a356-e805185eb3f4'),(92,_binary '\0','2022.08.17 16:00:14','/room/860068ab-16b7-4b1e-9246-d2c0d64b97e9'),(93,_binary '\0','2022.08.17 16:08:06','/room/7e398ae4-9613-456b-8620-991bb8994516'),(94,_binary '\0','2022.08.17 16:35:44','/room/809c6480-aeb2-4998-8452-4168c42ed8ab'),(95,_binary '\0','2022.08.17 16:41:18','/room/f393dcdf-f318-4ae9-ba1b-e9df2be00ee2'),(96,_binary '\0','2022.08.17 16:54:15','/room/18d60cfc-00f7-47e1-920d-0f75fd7ce607'),(97,_binary '\0','2022.08.17 17:09:43','/room/cdaf49c7-fd4f-4322-a5be-e770690e6234'),(98,_binary '\0','2022.08.17 17:12:17','/room/0f9bf5e7-e3af-433c-83e2-4424813d1140'),(99,_binary '\0','2022.08.17 17:22:36','/room/27a5a3d8-8cf4-4f6c-ad23-c4b25d03d956'),(100,_binary '\0','2022.08.17 17:37:39','/room/1702a291-33f2-47e0-aaac-921d5733d690'),(101,_binary '\0','2022.08.17 20:05:43','/room/ed9737df-c7b0-4940-b930-363c18ed29cc'),(102,_binary '\0','2022.08.17 21:36:15','/room/639a74dd-d516-4dfe-8190-91b925f1c93a'),(103,_binary '\0','2022.08.17 21:36:39','/room/4dca451b-b135-4dd0-b236-087e2d5f9aa1'),(104,_binary '\0','2022.08.17 21:38:37','/room/5c2f5891-3ce8-4ac6-9d0a-10970fa10ba3'),(105,_binary '\0','2022.08.17 22:32:00','/room/ea2b5091-bc16-4f02-bba6-3b207c6dc1ba'),(106,_binary '\0','2022.08.17 22:43:52','/room/9e9e28e7-6040-4627-9b42-b9fc85c8e1b4'),(107,_binary '\0','2022.08.18 01:22:47','/room/bb0b0f0b-674e-468c-b416-71f4642e9dc1'),(108,_binary '\0','2022.08.18 03:13:40','/room/2f13570b-f6a4-4c2a-a05a-49fd2c3b9acf'),(109,_binary '\0','2022.08.18 09:20:43','/room/1689be1a-cae5-42df-8e47-39577a48623e');
/*!40000 ALTER TABLE `room_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'moweb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-18 10:06:41
