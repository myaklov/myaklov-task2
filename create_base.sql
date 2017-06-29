DROP DATABASE IF EXISTS test;

CREATE DATABASE test DEFAULT CHARACTER SET 'utf8';

USE test;

create table list
(
	id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	task varchar(100),
	status varchar(10)
);



USE test;
DELIMITER $$
CREATE PROCEDURE insert_test_data()
BEGIN
  DECLARE i INT DEFAULT 1;
DECLARE j INT DEFAULT 51;
  WHILE i < 50 DO
    INSERT INTO list (id, task, status)
    values(i, 'delo','done');
    set i = i + 1;
  END WHILE;
  WHILE j < 100 DO
    INSERT INTO list (id, task, status)
    values(j, 'delo','not done');
    set j = j + 1;
  END WHILE;
END$$
DELIMITER ;
CALL insert_test_data();
DROP PROCEDURE insert_test_data;