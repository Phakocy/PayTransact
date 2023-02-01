CREATE TABLE paytransact.dbo.users (
  id int PRIMARY KEY IDENTITY(101, 1),
  email varchar(100) NOT NULL UNIQUE ,
  password varchar(30) NOT NULL ,
  date_created datetime,
)