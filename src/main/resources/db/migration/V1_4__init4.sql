--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `id` bigint(20) NOT NULL,
  `min_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `object_id` bigint(20) DEFAULT NULL,
  `path_name` varchar(255) DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

ALTER TABLE `user`
	ALTER `openid` DROP DEFAULT;
ALTER TABLE `user`
	CHANGE COLUMN `openid` `openid` VARCHAR(32) NOT NULL COLLATE 'utf8mb4_unicode_ci' AFTER `create_time`,
	ADD UNIQUE INDEX `Index 2` (`openid`);