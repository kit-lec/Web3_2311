SHOW tables;

SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'mydb2309'
AND TABLE_NAME LIKE 't7_%'
;

DESC t7_authority ;
INSERT INTO t7_authority(name) VALUES('aaa'), ('bbb');

SELECT * FROM t7_authority ;
SELECT * FROM t7_user;
SELECT * FROM t7_user_authorities;
SELECT * FROM t7_post ORDER BY id DESC;
SELECT * FROM t7_attachment ORDER BY id DESC;
SELECT * FROM t7_comment ORDER BY id DESC;

# 페이징 테스트용 다량의 데이터
INSERT INTO t7_post(user_id, subject, content, reg_date)
SELECT user_id, subject, content, now() FROM t7_post;

SELECT count(*) from t7_post;



