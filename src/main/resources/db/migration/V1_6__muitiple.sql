--
-- Table structure for table `multiple_codes`
--

DROP TABLE IF EXISTS `multiple_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multiple_codes` (
  `id` bigint(20) NOT NULL,
  `url` varchar(512) NOT NULL,
  `times` DECIMAL(8,2) NULL DEFAULT '0.00',
  `create_time` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf0vxnh2q4uh501lqblk8c59s1` (`user_id`),
  CONSTRAINT `FKf0vxnh2q4uh501lqblk8c59s1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multiple_codes`
--

LOCK TABLES `multiple_codes` WRITE;
/*!40000 ALTER TABLE `multiple_codes` DISABLE KEYS */;
/*!40000 ALTER TABLE `multiple_codes` ENABLE KEYS */;
UNLOCK TABLES;
