-- MySQL dump 10.13  Distrib 5.6.24, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: hack
-- ------------------------------------------------------
-- Server version	5.6.19-0ubuntu0.14.04.1

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
-- Table structure for table `idea_status`
--

DROP TABLE IF EXISTS `idea_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idea_status` (
  `ideaNumber` varchar(45) NOT NULL,
  `ideaUpVote` int(11) DEFAULT '1',
  `ideaDownVote` int(11) DEFAULT '0',
  `ideaStatus` varchar(45) DEFAULT 'submitted',
  PRIMARY KEY (`ideaNumber`),
  CONSTRAINT `fk_idea_status_1` FOREIGN KEY (`ideaNumber`) REFERENCES `user_ideas` (`ideaNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `idea_team`
--

DROP TABLE IF EXISTS `idea_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idea_team` (
  `ideaNumber` varchar(45) NOT NULL,
  `ideaTeamEmailId` varchar(45) NOT NULL,
  PRIMARY KEY (`ideaNumber`,`ideaTeamEmailId`),
  CONSTRAINT `fk_idea_team_1` FOREIGN KEY (`ideaNumber`) REFERENCES `user_ideas` (`ideaNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `idea_vote`
--

DROP TABLE IF EXISTS `idea_vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idea_vote` (
  `ideaNumber` varchar(50) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  PRIMARY KEY (`ideaNumber`,`user_email`),
  CONSTRAINT `fk_idea_vote_1` FOREIGN KEY (`ideaNumber`) REFERENCES `user_ideas` (`ideaNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_ideas`
--

DROP TABLE IF EXISTS `user_ideas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_ideas` (
  `ideaNumber` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `ideaOverView` varchar(300) DEFAULT NULL,
  `section` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `objective` text NOT NULL,
  `submittedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `url` text,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ideaNumber`),
  UNIQUE KEY `uni_user_idea` (`ideaNumber`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`email`),
  KEY `fk_username_idx` (`email`),
  CONSTRAINT `fk_email` FOREIGN KEY (`email`) REFERENCES `users` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `fname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `sme` varchar(45) NOT NULL,
  `otherExpertise` varchar(45) DEFAULT NULL,
  `designation` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `team` varchar(45) NOT NULL,
  `employeeId` int(11) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'hack'
--
/*!50003 DROP PROCEDURE IF EXISTS `wth_count` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `wth_count`(
OUT idea_count INT,
OUT feature_count INT,
OUT upvote_count INT,
OUT downvote_count INT,
OUT totalvote_count INT


)
BEGIN
 
     select count(*) INTO idea_count from hack.user_ideas where section='idea';
     select count(*) INTO feature_count from hack.user_ideas where section='feature';
     select sum(idea_status.ideaUpVote) INTO upvote_count from idea_status;
     select sum(idea_status.ideaDownVote) INTO downvote_count from idea_status;
     select sum(hack.idea_status.ideaDownVote)+sum(idea_status.ideaUpVote)
     INTO totalvote_count from idea_status;

     
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `wth_insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `wth_insert`(ideaNumber varchar(45), 
email varchar(45), ideaOverview varchar(300),section 
varchar(45),objective text, description text,url text,category varchar(45))
BEGIN
start transaction;
  insert into user_ideas(ideaNumber,email,ideaOverview,section,objective, description,url,category) values (ideaNumber,email,ideaOverView,section,objective,description,url,category);
  insert into idea_status(ideaNumber) VALUES (ideaNumber);
  insert into idea_vote(ideaNumber,user_email) values(ideaNumber,email);
  insert into idea_team(ideaNumber,ideaTeamEmailId) VALUES (ideaNumber,email);
  commit;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-23 15:30:09
