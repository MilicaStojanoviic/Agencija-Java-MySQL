/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.22-MariaDB : Database - agencija
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`agencija` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `agencija`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `AdministratorID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Ime` varchar(30) NOT NULL,
  `Prezime` varchar(30) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `administrator` */

insert  into `administrator`(`AdministratorID`,`Ime`,`Prezime`,`Username`,`Password`) values 
(1,'Milica','Stojanović','comi','comi123');

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `KlijentID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL,
  `Kontakt` varchar(50) NOT NULL,
  `TipKlijenta` varchar(50) NOT NULL,
  PRIMARY KEY (`KlijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `klijent` */

insert  into `klijent`(`KlijentID`,`Ime`,`Prezime`,`Kontakt`,`TipKlijenta`) values 
(1,'Jovan','Perunicic','0631097066','Predstavnik upravnog odbora'),
(5,'Ana ','Tosic','0642536976','Predstavnik upravnog odbora'),
(6,'Sara','Stojkovic','0692547361','Predstavnik kućnog saveta'),
(8,'Teodora','Stojanovic','0625789639','Predstavnik upravnog odbora'),
(9,'Jovana ','Ilic','0652547548','Predstavnik kućnog saveta'),
(10,'Katarina','Mitic','0672541695','Predstavnik upravnog odbora'),
(11,'Katarina','Bralovic','06235247895','Investitor');

/*Table structure for table `prostor` */

DROP TABLE IF EXISTS `prostor`;

CREATE TABLE `prostor` (
  `ProstorID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Adresa` varchar(50) NOT NULL,
  `TipProstora` varchar(50) NOT NULL,
  PRIMARY KEY (`ProstorID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

/*Data for the table `prostor` */

insert  into `prostor`(`ProstorID`,`Adresa`,`TipProstora`) values 
(20,'Tosin bunar 55','Poslovna zgrada'),
(22,'Presevska 36','Stambena zgrada'),
(25,'Internacionalnih brigada 16','Javna zgrada');

/*Table structure for table `stavkaugovora` */

DROP TABLE IF EXISTS `stavkaugovora`;

CREATE TABLE `stavkaugovora` (
  `UgovorID` bigint(10) unsigned NOT NULL,
  `RbStavke` int(10) NOT NULL,
  `Trajanje` int(10) NOT NULL,
  `UslugaID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`UgovorID`,`RbStavke`),
  KEY `fk_usluga` (`UslugaID`),
  CONSTRAINT `fk_ugovor` FOREIGN KEY (`UgovorID`) REFERENCES `ugovor` (`UgovorID`) ON DELETE CASCADE,
  CONSTRAINT `fk_usluga` FOREIGN KEY (`UslugaID`) REFERENCES `usluga` (`UslugaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `stavkaugovora` */

insert  into `stavkaugovora`(`UgovorID`,`RbStavke`,`Trajanje`,`UslugaID`) values 
(2,1,1,4),
(2,2,1,3),
(2,3,1,7),
(2,4,1,5),
(2,5,1,6),
(3,1,2,4),
(6,1,2,5),
(6,2,2,6);

/*Table structure for table `ugovor` */

DROP TABLE IF EXISTS `ugovor`;

CREATE TABLE `ugovor` (
  `UgovorID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `DatumPotpisivanja` datetime NOT NULL,
  `DatumStupanjaNaSnagu` datetime NOT NULL,
  `Napomene` varchar(200) NOT NULL,
  `KlijentID` bigint(10) unsigned NOT NULL,
  `ProstorID` bigint(10) unsigned NOT NULL,
  `AdministratorID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`UgovorID`),
  KEY `fk_klijent` (`KlijentID`),
  KEY `fk_administrator` (`AdministratorID`),
  KEY `fk_prostor` (`ProstorID`),
  CONSTRAINT `fk_administrator` FOREIGN KEY (`AdministratorID`) REFERENCES `administrator` (`AdministratorID`),
  CONSTRAINT `fk_klijent` FOREIGN KEY (`KlijentID`) REFERENCES `klijent` (`KlijentID`),
  CONSTRAINT `fk_prostor` FOREIGN KEY (`ProstorID`) REFERENCES `prostor` (`ProstorID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ugovor` */

insert  into `ugovor`(`UgovorID`,`DatumPotpisivanja`,`DatumStupanjaNaSnagu`,`Napomene`,`KlijentID`,`ProstorID`,`AdministratorID`) values 
(2,'2023-09-11 00:00:00','2023-10-01 00:00:00','Ciste se sve prostorije. UPDATEEEE. Provera ya ismenu',1,20,1),
(3,'2023-09-11 19:24:25','2023-09-12 00:00:00','Bitna napomena!',6,22,1),
(6,'2023-09-11 00:00:00','2024-01-01 00:00:00','Provera za update',6,25,1);

/*Table structure for table `usluga` */

DROP TABLE IF EXISTS `usluga`;

CREATE TABLE `usluga` (
  `UslugaID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(50) NOT NULL,
  `Cena` decimal(10,2) NOT NULL,
  `Opis` varchar(200) NOT NULL,
  PRIMARY KEY (`UslugaID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `usluga` */

insert  into `usluga`(`UslugaID`,`Naziv`,`Cena`,`Opis`) values 
(3,'Čišćenje',10000.00,'Detaljno čišćenje i dezinfekcija prosora.\nCena na mesečnom nivou.'),
(4,'Popravka kvarova',5000.00,'Popravka svih kvarova na mesečnom nivou.\nCena funkcioniše kao pretplata.'),
(5,'Provera instalacija',5000.00,'Provera instalacija na mesečnom nivou.'),
(6,'Administrativne uloge',15000.00,'Upravljanje finansijama i dokumentacijom\nna mesečnom nivou.\n'),
(7,'Pomoć pri vanrednom stanju',8000.00,'Pružanje podrške i sređivanje nastalih problema\nu slučaju požara, poplave, itd.\nPretplata na mesečnom nivou.');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
