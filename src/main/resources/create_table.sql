CREATE TABLE `topic` (
  `topicId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`topicId`),
  UNIQUE KEY `topicId_UNIQUE` (`topicId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `test` (
  `testId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(400) DEFAULT NULL,
  `topicId` int(10) unsigned NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`testId`),
  UNIQUE KEY `testId_UNIQUE` (`testId`),
  KEY `topicId_idx` (`topicId`),
  CONSTRAINT `topicId` FOREIGN KEY (`topicId`) REFERENCES `topic` (`topicId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `question` (
  `questionId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(400) CHARACTER SET latin1 NOT NULL,
  `testId` int(10) unsigned NOT NULL,
  `isDeleted` bit(1) NOT NULL,
  PRIMARY KEY (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) CHARACTER SET latin1 NOT NULL,
  `lastName` varchar(255) CHARACTER SET latin1 NOT NULL,
  `login` varchar(255) CHARACTER SET latin1 NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 NOT NULL,
  `roleId` int(10) unsigned NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `roleId_idx` (`roleId`),
  CONSTRAINT `roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `role` (
  `roleId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `answer` (
  `answerId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(400) DEFAULT NULL,
  `isCorrect` bit(1) NOT NULL DEFAULT b'0',
  `questionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`answerId`),
  UNIQUE KEY `answerId_UNIQUE` (`answerId`),
  KEY `questionId_idx` (`questionId`),
  CONSTRAINT `question_id` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `statistic` (
  `staticticId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `isCorrect` bit(1) NOT NULL DEFAULT b'0',
  `questionId` int(10) unsigned NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`staticticId`),
  KEY `userId_idx` (`userId`),
  KEY `questionId_idx` (`questionId`),
  CONSTRAINT `questionId` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `literature` (
  `literatureId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(400) CHARACTER SET latin1 DEFAULT NULL,
  `questionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`literatureId`),
  UNIQUE KEY `literatureId_UNIQUE` (`literatureId`),
  KEY `question_Id_idx` (`questionId`),
  CONSTRAINT `id_question` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `link` (
  `linkId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `link` varchar(400) DEFAULT NULL,
  `literatureId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`linkId`),
  UNIQUE KEY `linkId_UNIQUE` (`linkId`),
  KEY `literatureId_idx` (`literatureId`),
  CONSTRAINT `literatureId` FOREIGN KEY (`literatureId`) REFERENCES `literature` (`literatureId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;












