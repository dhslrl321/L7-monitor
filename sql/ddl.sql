CREATE DATABASE bob_l7;

CREATE TABLE `bob_l7`.`mal_code` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `mal_name` varchar(100) NOT NULL,
  PRIMARY KEY (`code`)
);

CREATE TABLE `bob_l7`.`total` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(10) NOT NULL,
  `userid` varchar(10) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `uri` text DEFAULT NULL,
  `protocol` varchar(20) DEFAULT NULL,
  `res_code` int(11) DEFAULT NULL,
  `res_data_size` int(11) DEFAULT NULL,
  `referer` text DEFAULT NULL,
  `user_agent` text DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `abnormal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mal_code` int(11) DEFAULT NULL,
  `ip` varchar(10) NOT NULL,
  `userid` varchar(10) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `uri` text DEFAULT NULL,
  `protocol` varchar(20) DEFAULT NULL,
  `res_code` int(11) DEFAULT NULL,
  `res_data_size` int(11) DEFAULT NULL,
  `referer` text DEFAULT NULL,
  `user_agent` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mal_code_idx` (`mal_code`),
  CONSTRAINT `fk_mal_code` FOREIGN KEY (`mal_code`) REFERENCES `mal_code` (`code`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE `bob_l7`.`unknown` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ip` VARCHAR(10) NOT NULL,
  `data` TEXT NULL,
  PRIMARY KEY (`id`));
  

INSERT INTO `bob_l7`.`mal_code` (`code`, `mal_name`) VALUES (1, "sql injection");
INSERT INTO `bob_l7`.`mal_code` (`code`, `mal_name`) VALUES (2, "rfi");
INSERT INTO `bob_l7`.`mal_code` (`code`, `mal_name`) VALUES (3, "webshell");
INSERT INTO `bob_l7`.`mal_code` (`code`, `mal_name`) VALUES (4, "xss");



# IP0319288 - - [25/Aug/2017:09:18:27 +0900] "GET /images/pharming_img.png HTTP/1.1" 200 53529 "http://211.233.33.1/" "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko"
INSERT INTO total SET 
ip="0319288",
userid=NULL,
timestamp="2017:08:25 18:18:27",
method="GET",
uri="/images/pharming_img.png",
protocol="HTTP/1.1",
res_code=200,
res_data_size=53529,
referer="http://211.233.33.1/",
user_agent="Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko";

# IP0107107 - - - [23/Jun/2019:23:55:40 +0900] "GET /board/fileDown.jsp?pageId=060100&bbsId=7&itemId=821&athSeq=2 HTTP/1.1" 200 73880198 "https://isis.kisa.or.kr/board/?pageId=060100&bbsId=7&itemId=821&searchKey=&searchTxt=&pageIndex=1" "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"
INSERT INTO total SET 
ip="0107107",
userid=NULL,
timestamp="2019:06:24 08:55:40",
method="GET",
uri="/board/fileDown.jsp?pageId=060100&bbsId=7&itemId=821&athSeq=2",
protocol="HTTP/1.1",
res_code=200,
res_data_size=73880198,
referer="https://isis.kisa.or.kr/board/?pageId=060100&bbsId=7&itemId=821&searchKey=&searchTxt=&pageIndex=1",
user_agent="Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko";

# IP0273313 - - - [12/Jun/2019:00:01:54 +0900] "GET /main/isms/issue;jsessionid=BD2753F54E7DEBA9E4EA554226ACF586/?certificationMode=list&crtfYear=&searchCondition=2&searchKeyword=%EC%8A%A4%ED%83%80%EB%B2%85%EC%8A%A4&__encrypted=U8oaEwTLg12yqNDZuCwRPMiDRLgcrZjlbxomU5uctoZc1kXWONBhXaf0KhG%2BaV6wpp2zSeTry6yKT1QpQPP4n6Wl4JbzPyTKSn7s1FoRr90UnnwTp%2BW928V1TpyMuwFVMU8D270RkIg564CRAF0bUnkvpnfyAxjhbyn0pSpjvw%2BMlXuoQnR3oJUfvVi%2B1dac8Gnd7jHhSmiDLOX09CuWhVRPx40RuMcaT%2FHbItyyZvQECWvcAvRb36C1zB%2FnwnWRJNfv74iaCKBgpNE%2BERnypNyBfcqQSKf%2BfDsW9aHcpTOO1K747YgBrg%3D%3D HTTP/1.1" 200 18432
INSERT INTO total SET 
ip="0273313",
userid=NULL,
timestamp="2019:06:12 09:01:54",
method="GET",
uri="/main/isms/issue;jsessionid=BD2753F54E7DEBA9E4EA554226ACF586/?certificationMode=list&crtfYear=&searchCondition=2&searchKeyword=%EC%8A%A4%ED%83%80%EB%B2%85%EC%8A%A4&__encrypted=U8oaEwTLg12yqNDZuCwRPMiDRLgcrZjlbxomU5uctoZc1kXWONBhXaf0KhG%2BaV6wpp2zSeTry6yKT1QpQPP4n6Wl4JbzPyTKSn7s1FoRr90UnnwTp%2BW928V1TpyMuwFVMU8D270RkIg564CRAF0bUnkvpnfyAxjhbyn0pSpjvw%2BMlXuoQnR3oJUfvVi%2B1dac8Gnd7jHhSmiDLOX09CuWhVRPx40RuMcaT%2FHbItyyZvQECWvcAvRb36C1zB%2FnwnWRJNfv74iaCKBgpNE%2BERnypNyBfcqQSKf%2BfDsW9aHcpTOO1K747YgBrg%3D%3D",
protocol="HTTP/1.1",
res_code=200,
res_data_size=18432,
referer=NULL,
user_agent=NULL;