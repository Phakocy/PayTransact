CREATE TABLE users (
  id int PRIMARY KEY ,
  email varchar(100) NOT NULL ,
  password varchar(30) NOT NULL ,
  date_created datetime,
)