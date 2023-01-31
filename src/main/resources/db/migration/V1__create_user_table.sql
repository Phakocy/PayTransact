CREATE TABLE users (
  id int PRIMARY KEY UNIQUE,
  email varchar(100) NOT NULL UNIQUE ,
  password varchar(30) NOT NULL ,
  date_created datetime,
)