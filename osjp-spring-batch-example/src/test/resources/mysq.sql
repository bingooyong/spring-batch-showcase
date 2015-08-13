create database spring_batch_example;
create user 'sbe'@'localhost' identified by 'sbe';
grant all on spring_batch_example.* to 'sbe'@'localhost';

CREATE TABLE PRODUCT (
  ID INT NOT NULL,
  NAME VARCHAR(128) NOT NULL,
  DESCRIPTION VARCHAR(128),
  QUANTITY INT,
  PRIMARY KEY(ID)
);