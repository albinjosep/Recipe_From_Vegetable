/*
SQLyog Community Edition- MySQL GUI v8.03 
MySQL - 5.1.36-community-log : Database - recipe
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`recipe` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `recipe`;

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `FeedbackID` int(11) NOT NULL AUTO_INCREMENT,
  `Feedback` varchar(999) DEFAULT NULL,
  `LID` int(30) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`FeedbackID`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`FeedbackID`,`Feedback`,`LID`,`Date`) values (1,'COol',1,'2019-12-12'),(2,'\"+details+\"',0,'2020-01-17'),(3,'gghh',2,'2020-01-17'),(4,'edfuggfufssughgjc',2,'2020-01-17'),(5,'gfufxycdrtzwz',2,'2020-01-17'),(6,'ttt',2,'2020-01-18'),(7,'tt',2,'2020-01-21'),(8,'aaa',2,'2020-01-21'),(9,'sssss',2,'2020-01-21'),(10,'nice',2,'2020-01-21'),(11,'*****',2,'2020-01-21'),(12,'gfg',2,'2020-02-01'),(13,'fjf',2,'2020-02-01'),(14,'bbb',2,'2020-02-08');

/*Table structure for table `ingredients` */

DROP TABLE IF EXISTS `ingredients`;

CREATE TABLE `ingredients` (
  `IngrID` int(11) NOT NULL AUTO_INCREMENT,
  `VegID` int(11) DEFAULT NULL,
  `RecipeID` int(40) DEFAULT NULL,
  `Quantity` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`IngrID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `ingredients` */

insert  into `ingredients`(`IngrID`,`VegID`,`RecipeID`,`Quantity`) values (1,1,1,'1kg'),(2,2,1,'500g'),(4,5,2,'500g'),(5,4,2,'2kg'),(6,3,2,'3kg'),(7,3,1,'5kg');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `Loginid` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Loginid`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`Loginid`,`Username`,`Password`,`type`) values (1,'admin','admin','admin'),(2,'xxx','xxx','user'),(3,'','','pending'),(19,'9995559990','qwerty','user'),(18,'1244','t','user'),(17,'1244','t','user'),(16,'1','g','user'),(15,'123456','g','user'),(9,'jj','tt','pending'),(23,'','','user'),(22,'ghh','t','user'),(21,'','','user'),(20,'','','user'),(14,'shradz','666','pending'),(24,'','','user'),(25,'4567','t','user'),(26,'4567','t','user'),(27,'0562828','t','user'),(28,'5557777999','q','user'),(29,'5557777999','q','user'),(30,'5557777999','q','user'),(31,'5557777999','q','user'),(32,'5557777999','q','user'),(33,'4555667777','v','user'),(34,'4555667777','u','user'),(35,'9995559990','zoo','user'),(36,'56874778','ggg','user'),(37,'3333555678','c','user'),(38,'ppgggg','c','user'),(39,'ppgggg','c','user'),(40,'www','m','user');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rating` varchar(20) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rid`,`rating`,`userid`,`date`) values (1,'3.0',2,'2020-01-18'),(2,'5.0',2,'2020-01-21'),(3,'5.0',2,'2020-01-21'),(4,'5.0',2,'2020-01-21'),(5,'4.5',2,'2020-02-01'),(6,'4.5',2,'2020-02-01'),(7,'5.0',2,'2020-02-08'),(8,'2.5',2,'2020-02-08');

/*Table structure for table `recipe` */

DROP TABLE IF EXISTS `recipe`;

CREATE TABLE `recipe` (
  `RecipeID` int(11) NOT NULL AUTO_INCREMENT,
  `RecipeName` varchar(30) DEFAULT NULL,
  `Image` varchar(99) DEFAULT NULL,
  `Description` varchar(9999) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`RecipeID`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `recipe` */

insert  into `recipe`(`RecipeID`,`RecipeName`,`Image`,`Description`,`user_id`) values (8,'Mutton Stew','20200201_112927.jpg','Clean the mutton by washing it 3-4 times. Soak it in 1 tbsp vinegar for 5 minutes for removing any unwanted smell. Rinse the mutton thoroughly and allow the water to drain.\r\nPressure cook the mutton in 1 cup of water along with potatoes, carrots, salt, green chillies, crushed ginger and garlic for 4-5 whistles on medium high heat. Keep aside and allow the pressure to subside naturally.\r\nMeanwhile, crush the whole spices for garam masala powder using a Mortar & Pestle (You can also use a spice grinder). Keep aside.\r\nHeat a big wok and add 2 tbsp coconut oil.\r\nAdd the finely crushed garam masala powder and saute for 2 minutes.\r\nNext, add the sliced onions and bay leaf to the pan. Saute for a few minutes.\r\nAdd a sprig of curry leaves, 2 slit green chillies, 2 tbsp crushed ginger & garlic and a little salt. Saute for a few minutes until the ginger and garlic is fragrant.\r\nAdd whole cashews to the pan. Saute for 5-7 minutes until the onions soften\r\nAdd the cooked mutton along with it’s gravy to the pan. Mix everything.\r\nGently add 2 cups of thin coconut milk to the pan. Add salt to taste. Stir well. Cover and simmer everything for 10 minutes.\r\nNext, add 1 cup of frozen green peas followed by 1 cup of thick coconut milk. Cover and simmer for 5 more minutes.\r\nAdd 2 tsp vinegar to the stew and simmer for a couple more minutes. Remove from heat.\r\nAllow the Mutton Stew to rest for 2-3 hours for the flavors to mingle. Serve with Appam, Idiyappam, Roti etc.\r\n    ',16),(20,'navya','20200125_145531.jpg','gfuf',0),(10,'xxxxxxxx','20200124_154340.jpg','Clean the mutton by washing it 3-4 times. Soak it in 1 tbsp vinegar for 5 minutes for removing any unwanted smell. Rinse the mutton thoroughly and allow the water to drain.\nPressure cook the mutton in 1 cup of water along with potatoes, carrots, salt, green chillies, crushed ginger and garlic for 4-5 whistles on medium high heat. Keep aside and allow the pressure to subside naturally.\nMeanwhile, crush the whole spices for garam masala powder using a Mortar & Pestle (You can also use a spice grinder). Keep aside.\nHeat a big wok and add 2 tbsp coconut oil.\nAdd the finely crushed garam masala powder and saute for 2 minutes.\nNext, add the sliced onions and bay leaf to the pan. Saute for a few minutes.\nAdd a sprig of curry leaves, 2 slit green chillies, 2 tbsp crushed ginger & garlic and a little salt. Saute for a few minutes until the ginger and garlic is fragrant.\nAdd whole cashews to the pan. Saute for 5-7 minutes until the onions soften\nAdd the cooked mutton along with it’s gravy to the pan. Mix everything.\nGently add 2 cups of thin coconut milk to the pan. Add salt to taste. Stir well. Cover and simmer everything for 10 minutes.\nNext, add 1 cup of frozen green peas followed by 1 cup of thick coconut milk. Cover and simmer for 5 more minutes.\nAdd 2 tsp vinegar to the stew and simmer for a couple more minutes. Remove from heat.\nAllow the Mutton Stew to rest for 2-3 hours for the flavors to mingle. Serve with Appam, Idiyappam, Roti etc.\n',16),(21,'kai','20200125_145622.jpg','ufj',0),(19,'navyatv','20200124_155900.jpg','fstyt  ttt ',0);

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `ReviewID` int(11) NOT NULL AUTO_INCREMENT,
  `Review` varchar(99) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`ReviewID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `review` */

insert  into `review`(`ReviewID`,`Review`,`UserID`,`Date`) values (1,'YOyo',2,'2019-12-12');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `bornday` date DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `bio` varchar(100) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`name`,`bornday`,`gender`,`number`,`username`,`image`,`bio`) values (2,'xxx','2000-06-05','male',2147483647,'jjjjj_pppppp','/static/image/image20191213_122736.jpg','yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy'),(9,'jjjj','2020-02-02','female',2147483647,'jjjjj_pppppp',NULL,NULL),(14,'shradha','2020-02-06','female',2147483647,NULL,NULL,NULL),(15,'xxx','2020-09-23','male',123456,'bbb',NULL,NULL),(16,'xxx','0000-00-00','female',1,'b',NULL,NULL),(17,'y','0000-00-00','male',1244,'u',NULL,NULL),(18,'y','0000-00-00','female',1244,'u',NULL,NULL),(0,'\"+name+\"','0000-00-00','\"+gender+\"',0,'\"+user+\"','\"+path+\"','\"+bio+\"'),(25,'hgh','0000-00-00','male',4567,'ghjjj','/static/recipe/20200201_153210.jpg','ghjjk'),(26,'hgh','0000-00-00','male',4567,'ghjjj','/static/recipe/20200201_153225.jpg','ghjjk'),(27,'aaa','0000-00-00','male',562828,'asdd','/static/recipe/20200201_153449.jpg','kl'),(28,'aaa','0000-00-00','male',2147483647,'aaaa','/static/recipe/20200208_115505.jpg','qwe'),(29,'aaa','0000-00-00','male',2147483647,'aaaa','/static/recipe/20200208_115559.jpg','qwe'),(30,'aaa','0000-00-00','male',2147483647,'aaaa','/static/recipe/20200208_115703.jpg','qwe'),(31,'yyy','0000-00-00','male',2147483647,'www','/static/user/20200208_115939.jpg','qwe'),(32,'yyy','0000-00-00','male',2147483647,'www','/static/user/20200208_115939.jpg','qwe'),(33,'gg','2000-05-15','male',2147483647,'hh','/static/user/20200208_120435.jpg','ll'),(34,'Vishnu','2000-05-15','male',2147483647,'hh','/static/user/20200208_120807.jpg','ll'),(35,'sk','1999-03-03','male',2147483647,'sk','/static/user/20200208_121850.jpg','jj'),(36,'ron','1985-06-07','male',56874778,'ron','/static/user/20200208_124508.jpg','hhh'),(37,'joel','1997-05-03','male',2147483647,'pp','/static/user/20200208_124846.jpg','kk'),(38,'joelgghh','1997-05-03','male',2147483647,'ppgggg','/static/user/20200208_125417.jpg','kk'),(39,'joelgghh','1997-05-03','male',2147483647,'ppgggg','/static/user/20200208_125417.jpg','kk'),(40,'qqq','2000-06-09','male',2147483647,'www','/static/user/20200208_151036.jpg','dd');

/*Table structure for table `veg` */

DROP TABLE IF EXISTS `veg`;

CREATE TABLE `veg` (
  `VegID` int(11) NOT NULL AUTO_INCREMENT,
  `VegName` varchar(30) DEFAULT NULL,
  `Image` varchar(99) DEFAULT NULL,
  `Description` varchar(999) DEFAULT NULL,
  `Category` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`VegID`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `veg` */

insert  into `veg`(`VegID`,`VegName`,`Image`,`Description`,`Category`) values (3,'potato1','20200124_160612.jpg','The potato is a root vegetable native to the Americas, a starchy tuber of the plant Solanum tuberosum, and the plant itself, a perennial in the family Solanaceae. Wild potato species can be found throughout the Americas, from the United States to southern Chile.\r\n\r\nFamily?: ?Solanaceae	Genus?: ?Solanum\r\nSpecies?: ?S. tuberosum	Kingdom?: ?Plantae\r\n','Marrow'),(15,'navya','20200124_155200.jpg','dssedf','Marrow'),(14,'Tomato','20200124_160541.jpg','The tomato is the edible, often red, berry of the plant Solanum lycopersicum, commonly known as a tomato plant. The species originated in western South America and Central America. The Nahuatl word tomatl gave rise to the Spanish word tomate, from which the English word tomato derived','Edible'),(16,'hhhh','20200124_155941.jpg','mdmdm','Allium');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
