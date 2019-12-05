SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema coffeeMachine
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `coffee_machine` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci ;
USE `coffee_machine` ;

--
-- Table structure for table `account_users`
--
DROP TABLE IF EXISTS `account_users`;
CREATE TABLE `account_users` (
  `id_account_user` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_account_user`),
  UNIQUE KEY `id_account_user_UNIQUE` (`id_account_user`),
  KEY `acUs_idx` (`id_user`),
  CONSTRAINT `acUs` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;


--
-- Dumping data for table `account_users`
--

LOCK TABLES `account_users` WRITE;
INSERT INTO `account_users` VALUES (1,47),(2,48);
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `id_account` int(11) NOT NULL AUTO_INCREMENT,
  `id_account_user` int(11) NOT NULL,
  `id_payment_method` int(11) NOT NULL DEFAULT '2',
  `payment_date` date NOT NULL,
  `amount_of_money` int(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_account`),
  UNIQUE KEY `id_account_UNIQUE` (`id_account`),
  KEY `idAcUser_idx` (`id_account_user`),
  KEY `paym_idx` (`id_payment_method`),
  CONSTRAINT `ac_Us` FOREIGN KEY (`id_account_user`) REFERENCES `account_users` (`id_account_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `paym` FOREIGN KEY (`id_payment_method`) REFERENCES `payment_methods` (`id_payment_method`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
INSERT INTO `accounts` VALUES (1,10,4,'2019-11-26',2),(2,10,3,'2019-11-26',12),(3,10,1,'2019-11-26',6),(4,10,5,'2019-11-26',506),(5,11,1,'2019-11-26',5),(6,10,6,'2019-11-26',511),(7,12,1,'2019-11-27',5),(8,12,4,'2019-11-27',15);
UNLOCK TABLES;

--
-- Table structure for table `additional_ingredients`
--

DROP TABLE IF EXISTS `additional_ingredients`;
CREATE TABLE `additional_ingredients` (
  `id_additional_ingredient` int(4) NOT NULL AUTO_INCREMENT,
  `additional_ingredient` varchar(20) NOT NULL,
  `portion` int(11) NOT NULL DEFAULT '50',
  `calories` int(11) DEFAULT NULL,
  `picture_path` varchar(80) DEFAULT NULL,
  `additional_ingredientscol` varchar(45) DEFAULT 'pictures/4212.jpg',
  PRIMARY KEY (`id_additional_ingredient`),
  UNIQUE KEY `additional_ingredient_UNIQUE` (`additional_ingredient`),
  UNIQUE KEY `id_additional_ingredient_UNIQUE` (`id_additional_ingredient`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `additional_ingredients`
--

LOCK TABLES `additional_ingredients` WRITE;
INSERT INTO `additional_ingredients` VALUES (101,'Сахар',1958,387,'pictures/additionalIngredient/additionalIngredient101.jpg','pictures/4212.jpg'),(102,'Сливки',375,196,'pictures/additionalIngredient/additionalIngredient102.jpg','pictures/4212.jpg'),(103,'Шоколадная крошка',391,546,'pictures/additionalIngredient/additionalIngredient103.jpg','pictures/4212.jpg');
UNLOCK TABLES;

--
-- Table structure for table `cart_additional_ingredients`
--

DROP TABLE IF EXISTS `cart_additional_ingredients`;
CREATE TABLE `cart_additional_ingredients` (
  `id_cart_additional_ingredient` int(11) NOT NULL AUTO_INCREMENT,
  `id_cart` int(11) NOT NULL,
  `id_Additional_Ingredient` int(11) NOT NULL,
  PRIMARY KEY (`id_cart_additional_ingredient`),
  UNIQUE KEY `id_cart_additional_ingredient_UNIQUE` (`id_cart_additional_ingredient`),
  KEY `id ad_idx` (`id_Additional_Ingredient`),
  KEY `idB_idx` (`id_cart`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Dumping data for table `cart_additional_ingredients`
--

LOCK TABLES `cart_additional_ingredients` WRITE;
/*!40000 ALTER TABLE `cart_additional_ingredients` DISABLE KEYS */;
INSERT INTO `cart_additional_ingredients` VALUES (1,3,102),(2,4,101),(3,4,102),(4,4,103),(5,5,103),(6,6,102),(7,7,103),(8,8,102),(9,9,103),(10,10,102),(11,11,101),(12,11,102),(13,11,103),(14,12,101),(15,12,102),(16,12,103),(17,13,102),(18,13,103),(19,14,102),(20,14,103),(21,15,101),(22,15,102),(23,15,103),(24,16,102),(25,16,103),(26,17,102),(27,17,103),(28,18,103),(29,19,102),(30,19,103),(31,20,102),(32,20,103),(33,21,102),(34,21,103),(35,22,102),(36,22,103),(37,23,102),(38,23,103),(39,24,102),(40,24,103),(41,25,102),(42,25,103),(43,26,102),(44,26,103),(45,27,101),(46,27,102),(47,27,103),(48,29,101),(49,30,102),(50,30,103),(51,31,102),(52,32,102),(53,32,103),(54,33,102),(55,33,103),(56,35,103),(57,36,103),(58,37,103),(59,38,103),(60,39,102),(61,40,103),(62,41,102),(63,42,103),(64,43,103),(65,44,103),(66,45,103),(67,46,102),(68,47,103),(69,48,103),(70,49,103),(71,51,101),(72,51,102),(73,51,103),(74,52,0),(75,52,0),(76,52,0),(77,53,0),(78,53,0),(79,53,0),(80,54,0),(81,54,0),(82,54,0),(83,54,0),(84,55,0),(85,55,0),(86,55,0),(87,55,0),(88,56,0),(89,56,0),(90,56,0),(91,56,0),(92,57,0),(93,57,0),(94,57,0),(95,57,0),(96,58,0),(97,58,0),(98,58,0),(99,58,0),(100,60,103),(101,61,101),(102,61,102),(103,61,103),(104,64,102),(105,64,103),(106,66,102),(107,69,102),(108,70,103),(109,75,103),(110,79,103),(111,86,103),(112,88,103),(113,89,101),(114,95,103),(115,96,103),(116,97,103),(117,98,103),(118,101,103),(119,102,103),(120,106,102),(121,106,103),(122,107,103),(123,108,102),(124,108,103),(125,109,101),(126,109,102),(127,109,103),(128,111,103),(129,112,102),(130,113,103),(131,114,102),(132,115,103),(133,117,101),(134,117,102),(135,117,103),(136,122,103),(137,125,103),(138,128,103),(139,133,103),(140,134,101),(141,134,103),(142,135,103),(143,136,102),(144,138,103),(145,142,103),(146,143,101),(147,143,102),(148,143,103),(149,144,102),(150,144,103),(151,145,103),(152,147,102),(153,147,103),(154,149,102),(155,149,103),(156,150,102),(157,151,103),(158,152,103),(159,153,103),(160,154,103),(161,155,103),(162,157,101),(163,157,103),(164,158,103),(165,159,101),(166,159,102),(167,159,103),(168,161,103),(169,162,103),(170,163,103),(171,169,103),(172,173,103),(173,178,101),(174,178,102),(175,178,103),(176,180,103),(177,181,102),(178,182,101),(179,182,102),(180,182,103),(182,200,102),(183,201,103),(184,206,103),(185,219,102),(186,223,103),(187,224,103),(190,226,102),(191,227,102),(192,229,103),(193,232,103);
/*!40000 ALTER TABLE `cart_additional_ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_users`
--

DROP TABLE IF EXISTS `cart_users`;
CREATE TABLE `cart_users` (
  `id_cart_User` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_cart_User`),
  UNIQUE KEY `id_cart_User_UNIQUE` (`id_cart_User`),
  KEY `idUs_idx` (`id_user`),
  CONSTRAINT `idUs` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Dumping data for table `cart_users`
--

LOCK TABLES `cart_users` WRITE;
/*!40000 ALTER TABLE `cart_users` DISABLE KEYS */;
INSERT INTO `cart_users` VALUES (1,1);
/*!40000 ALTER TABLE `cart_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts` (
  `id_cart` int(11) NOT NULL AUTO_INCREMENT,
  `id_cart_user` int(11) NOT NULL,
  `id_drink` int(11) NOT NULL,
  `portion` int(11) NOT NULL,
  PRIMARY KEY (`id_cart`),
  UNIQUE KEY `id_cart_UNIQUE` (`id_cart`),
  KEY `basket_idx` (`id_cart_user`),
  KEY `drink_idx` (`id_drink`),
  CONSTRAINT `drink` FOREIGN KEY (`id_drink`) REFERENCES `drinks` (`id_drink`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
INSERT INTO `carts` VALUES (1,1,1,1);
UNLOCK TABLES;

--
-- Table structure for table `drinks`
--

DROP TABLE IF EXISTS `drinks`;
CREATE TABLE `drinks` (
  `id_Drink` int(11) NOT NULL AUTO_INCREMENT,
  `drink` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `portion` int(11) NOT NULL DEFAULT '50',
  `price` decimal(10,2) NOT NULL,
  `description` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `picture_path` varchar(45) COLLATE utf8_unicode_ci DEFAULT 'pictures/drinks/default.jpg',
  PRIMARY KEY (`id_Drink`),
  UNIQUE KEY `id_Drink_UNIQUE` (`id_Drink`),
  UNIQUE KEY `drink_UNIQUE` (`drink`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drinks`
--

LOCK TABLES `drinks` WRITE;
INSERT INTO `drinks` VALUES (1,'Латте макиато',600,3.00,'Горячий кофейный напиток, приготавливаемый путём вливания в молоко кофе-эспрессо в пропорции 3:1. Колорийность 29ккал','pictures/drinks/1.jpg'),(2,'Американо',360,2.00,'Чёрный кофе, приготовленный в специальной кофеварке, где сквозь спрессованные молотые зёрна пропускают горячую воду под давлением. Калорийность 10ккал','pictures/drinks/2.jpg'),(3,'Эспрессо',500,1.50,'Чёрный кофе, приготовленный в специальной кофеварке, где сквозь спрессованные молотые зёрна пропускают горячую воду под давлением. Концентрированее, чем американо. Калорийность 10ккал. ','pictures/drinks/3.jpg'),(4,'Капучино',294,2.50,'Итальянский кофейный напиток содержит кофе с молоком в равных количествах и густой молочной пеной. Каллорийность 40ккал','pictures/drinks/4.jpg'),(5,'Ирландский кофе',550,3.00,'Кофейный напиток, на основе ароматизатора \"Ирландского виски\", чёрного кофе, взбитых сливок и коричневого сахара. Колорийность 150ккал.','pictures/drinks/5.jpg'),(6,'Венский кофе',345,2.80,'Кофе со взбитыми сливками.  Калорийность 175 ккал','pictures/drinks/6.jpg'),(7,'Горячий шоколад',404,3.00,'Напиток, в состав которого обязательно входит какао, а также молоко и сахар. калорийность 77ккал','pictures/drinks/7.jpg'),(8,'Чай фруктовый',1,1.50,'Чай из сочного букета ягод и фруктов. Калорийность 2 ккал.','pictures/drinks/8.jpg');
UNLOCK TABLES;

--
-- Table structure for table `filling_operations`
--

DROP TABLE IF EXISTS `filling_operations`;
CREATE TABLE `filling_operations` (
  `id_Operation` int(11) NOT NULL AUTO_INCREMENT,
  `id_drink` int(11) DEFAULT NULL,
  `id_additional_ingredient` int(11) DEFAULT NULL,
  `MAXPortion` int(11) NOT NULL DEFAULT '50',
  PRIMARY KEY (`id_Operation`),
  UNIQUE KEY `id_Operation_UNIQUE` (`id_Operation`),
  KEY `add_idx` (`id_additional_ingredient`),
  KEY `bas_idx` (`id_drink`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `filling_operations`
--

LOCK TABLES `filling_operations` WRITE;
INSERT INTO `filling_operations` VALUES (1,1,NULL,600),(2,2,NULL,400),(3,3,NULL,500),(4,4,NULL,300),(5,5,NULL,550),(6,6,NULL,350),(7,7,NULL,420),(8,8,NULL,150),(9,NULL,101,2000),(10,NULL,102,400),(12,NULL,103,400);
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `id_cart_user` int(11) NOT NULL,
  `date_Order` date NOT NULL,
  `total_Cost` decimal(6,2) NOT NULL,
  PRIMARY KEY (`id_order`),
  KEY `cartUser_idx` (`id_cart_user`),
  CONSTRAINT `cartUser` FOREIGN KEY (`id_cart_user`) REFERENCES `cart_users` (`id_cart_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'2019-11-11',4.00),(2,2,'2019-11-11',3.00);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_methods`
--

DROP TABLE IF EXISTS `payment_methods`;
CREATE TABLE `payment_methods` (
  `id_payment_method` int(11) NOT NULL AUTO_INCREMENT,
  `name_payment_method` varchar(45) COLLATE utf8_general_mysql500_ci NOT NULL,
  PRIMARY KEY (`id_payment_method`),
  UNIQUE KEY `id_payment_method_UNIQUE` (`id_payment_method`),
  UNIQUE KEY `name_payment_method_UNIQUE` (`name_payment_method`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Dumping data for table `payment_methods`
--

LOCK TABLES `payment_methods` WRITE;
/*!40000 ALTER TABLE `payment_methods` DISABLE KEYS */;
INSERT INTO `payment_methods` VALUES (5,'ERIP'),(4,'MasterCard'),(6,'VISA'),(3,'WebMoney'),(1,'Бонус за регистрацию'),(2,'Списание');
/*!40000 ALTER TABLE `payment_methods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_statuses`
--

DROP TABLE IF EXISTS `user_statuses`;
CREATE TABLE `user_statuses` (
  `id_user_status` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(35) NOT NULL,
  PRIMARY KEY (`id_user_status`),
  UNIQUE KEY `id_user_status_UNIQUE` (`id_user_status`),
  UNIQUE KEY `status_UNIQUE` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_statuses`
--

LOCK TABLES `user_statuses` WRITE;
INSERT INTO `user_statuses` VALUES (1,'admin'),(2,'customer');
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `id_status` int(11) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `idStatus_idx` (`id_status`),
  CONSTRAINT `idStatus` FOREIGN KEY (`id_status`) REFERENCES `user_statuses` (`id_user_status`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'chipollina@gmail.com','Чиполлино','333','Егор',2),(2,'karabasss@gmail.ru','Буратино','333','Виктор',2),(3,'kot@mail.ru','Матроскин','333','Максим',2),(4,'fedor@gmail.com','ДядяФедор','333','Федор',2),(6,'malvina@yandex.ru','Мальвина','546','Екатерина',2),(7,'mult@mail.ru','Пьеро','333','1328rt',2),(8,'tema@gmail.com','Артемон','888','Артем',2);
UNLOCK TABLES;

