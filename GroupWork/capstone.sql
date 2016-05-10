CREATE TABLE IF NOT EXISTS `BlogPost` (
 `BlogId` int(11) NOT NULL AUTO_INCREMENT,
 `BlogTitle` varchar(50) NOT NULL,
 `Category` varchar(50) NOT NULL,
 `UserName` varchar(50) NOT NULL,
 `BlogEntry` TEXT NOT NULL,
 `Approved` char(1) NOT NULL DEFAULT 'N',
 `StartDate` char(10) DEFAULT NULL,
 `EndDate` char(10) DEFAULT NULL,
 PRIMARY KEY (`BlogId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

CREATE TABLE IF NOT EXISTS `Tag` (
 `TagId` int(11) NOT NULL AUTO_INCREMENT,
 `Tag` varchar(50) NOT NULL,
 `Approved` char(1) NOT NULL DEFAULT 'N',
 PRIMARY KEY (`TagId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

CREATE TABLE IF NOT EXISTS `BlogTag` (
 `BlogId` int(11) NOT NULL,
 `TagId` int(11) NOT NULL,
 PRIMARY KEY (`BlogId`,`TagId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `StaticPage` (
 `PageId` int(11) NOT NULL AUTO_INCREMENT,
 `PageName` varchar(60) NOT NULL,
 `PageContent` TEXT NOT NULL,
 PRIMARY KEY (`PageId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(20) NOT NULL,
  `authority` varchar(20) NOT NULL,
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` char(60) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;
