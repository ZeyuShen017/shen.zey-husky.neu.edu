# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 8.0.19)
# Database: netshop
# Generation Time: 2020-04-09 08:33:16 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table books
# ------------------------------------------------------------

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `bid` int NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `price` double NOT NULL,
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `ISBN` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `cid` int NOT NULL,
  PRIMARY KEY (`bid`),
  KEY `FK_1` (`cid`),
  CONSTRAINT `FK_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;

INSERT INTO `books` (`bid`, `title`, `price`, `author`, `ISBN`, `cid`)
VALUES
	(1,'Java',12.99,'AA','123',1),
	(2,'Java',12.99,'AA','12334',1),
	(3,'Java',12.99,'AAA','12333',1);

/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `description` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;

INSERT INTO `category` (`cid`, `name`, `description`)
VALUES
	(1,'A','ABC');

/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table orderItem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `orderItem`;

CREATE TABLE `orderItem` (
  `itemId` int NOT NULL AUTO_INCREMENT,
  `oid` bigint NOT NULL,
  `bid` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`itemId`),
  KEY `FK_4` (`bid`),
  KEY `FK_3` (`oid`),
  CONSTRAINT `FK_3` FOREIGN KEY (`oid`) REFERENCES `orders` (`oId`),
  CONSTRAINT `FK_4` FOREIGN KEY (`bid`) REFERENCES `books` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `orderItem` WRITE;
/*!40000 ALTER TABLE `orderItem` DISABLE KEYS */;

INSERT INTO `orderItem` (`itemId`, `oid`, `bid`, `quantity`)
VALUES
	(1573124265,20200409030736489,3,1),
	(1574247618,20200409032619842,3,1),
	(1574314536,20200409032726760,3,1),
	(1574314537,20200409032726760,2,1),
	(1574351833,20200409032804057,1,1),
	(1574351834,20200409032804057,3,1),
	(1574351835,20200409032804057,2,1),
	(1574491434,20200409033023658,2,1),
	(1574605550,20200409033217773,3,1),
	(1574605551,20200409033217773,2,1);

/*!40000 ALTER TABLE `orderItem` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table orders
# ------------------------------------------------------------

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `oId` bigint NOT NULL,
  `userId` int NOT NULL,
  `zipcode` varchar(10) NOT NULL DEFAULT '',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `total` double NOT NULL,
  PRIMARY KEY (`oId`),
  KEY `FK_2` (`userId`),
  CONSTRAINT `FK_2` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;

INSERT INTO `orders` (`oId`, `userId`, `zipcode`, `address`, `total`)
VALUES
	(20200409030736489,1,'02148','160 Pleasant St, APT 611',12.99),
	(20200409032619842,1,'02148','160 Pleasant St, APT 611',12.99),
	(20200409032726760,1,'02148','seyuanlu, chongchuan',25.98),
	(20200409032804057,1,'02148','160 Pleasant St, APT 611',38.97),
	(20200409033023658,1,'02148','seyuanlu, chongchuan',12.99),
	(20200409033217773,1,'02148','160 Pleasant St, APT 611',25.98);

/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table userinfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `Rright` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;

INSERT INTO `userinfo` (`userId`, `username`, `password`, `Rright`)
VALUES
	(1,'abc','cba','0'),
	(2,'abc','cbaaaasa','1'),
	(3,'1222','cbaaaa','0'),
	(4,'123','321','1');

/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
