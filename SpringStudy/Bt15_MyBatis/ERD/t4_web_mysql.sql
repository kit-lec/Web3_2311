DROP TABLE IF EXISTS t4_post;

CREATE TABLE t4_post
(
	id int PRIMARY KEY AUTO_INCREMENT,
	user varchar(20) NOT NULL,
	subject varchar(200) NOT NULL,
	content LONGTEXT,
	viewcnt int DEFAULT 0,
	regdate datetime DEFAULT now()
);