SHOW tables;

SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'mydb2309'
AND TABLE_NAME LIKE 't7_%'
;

DESC t7_authority ;
INSERT INTO t7_authority(name) VALUES('aaa'), ('bbb');

SELECT * FROM t7_authority ;
