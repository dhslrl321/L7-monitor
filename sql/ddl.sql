CREATE DATABASE bob_l7;

CREATE TABLE `bob_l7`.`mal_code` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `mal_name` varchar(100) NOT NULL,
  PRIMARY KEY (`code`)
);

CREATE TABLE `bob_l7`.`total` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(128) NOT NULL,
  `userid` varchar(100) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `method` varchar(100) DEFAULT NULL,
  `uri` text DEFAULT NULL,
  `protocol` varchar(100) DEFAULT NULL,
  `res_code` int(11) DEFAULT NULL,
  `res_data_size` int(11) DEFAULT NULL,
  `referer` text DEFAULT NULL,
  `user_agent` text DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `bob_l7`.`abnormal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mal_code` int(11) DEFAULT NULL,
  `ip` varchar(128) NOT NULL,
  `userid` varchar(100) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `method` varchar(100) DEFAULT NULL,
  `uri` text DEFAULT NULL,
  `protocol` varchar(100) DEFAULT NULL,
  `res_code` int(11) DEFAULT NULL,
  `res_data_size` int(11) DEFAULT NULL,
  `referer` text DEFAULT NULL,
  `user_agent` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mal_code_idx` (`mal_code`)
);

CREATE TABLE `bob_l7`.`unknown_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(128) NOT NULL,
  `data` text DEFAULT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `bob_l7`.`mal_code` (`code`, `mal_name`) VALUES (1, "sql injection");
INSERT INTO `bob_l7`.`mal_code` (`code`, `mal_name`) VALUES (2, "rfi");
INSERT INTO `bob_l7`.`mal_code` (`code`, `mal_name`) VALUES (3, "webshell");
INSERT INTO `bob_l7`.`mal_code` (`code`, `mal_name`) VALUES (4, "xss");