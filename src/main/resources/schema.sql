CREATE TABLE roles (
  id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  role VARCHAR(15)
);
CREATE TABLE sexes (
  id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  sex VARCHAR(10)
);
CREATE TABLE users(
  id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  firstname VARCHAR(100),
  lastname VARCHAR(100),
  dob DATE DEFAULT NULL,
  sex int,
  phone varchar(20),
  email varchar(100),
  password VARCHAR(128),
  role int NOT NULL,
  blocked BOOLEAN DEFAULT FALSE,
  image VARCHAR(30),
  INDEX (email),
  FOREIGN KEY (role) REFERENCES roles(id),
  FOREIGN KEY (sex) REFERENCES sexes(id)
);
CREATE TABLE friendship (
  usersender int,
  userreceiver int,
  accepted BOOLEAN,
  FOREIGN KEY (usersender) REFERENCES users(id),
  FOREIGN KEY (userreceiver) REFERENCES users(id)
);
CREATE TABLE messages (
  dt DATETIME,
  usersender int,
  userreceiver int,
  message TEXT,
  INDEX (usersender),
  INDEX (userreceiver),
  FOREIGN KEY (usersender) REFERENCES users(id),
  FOREIGN KEY (userreceiver) REFERENCES users(id)
);
CREATE VIEW USERMESSAGE AS
  SELECT m.dt as dt, m.message as message, s.id as senderId, s.firstname as sfirstname, s.lastname as slastname, s.image as simage,
         r.id as rid, r.firstname as rfirstname, r.lastname as rlastname, r.image as rimage
  from messages as m
    join users as s on m.usersender =  s.id
    join users as r on m.userreceiver = r.id
  ORDER BY m.dt;

INSERT INTO roles VALUES (1, 'admin');
INSERT INTO roles VALUES (2, 'member');

INSERT INTO sexes VALUES (null, 'undefined');
INSERT INTO sexes VALUES (null, 'male');
INSERT INTO sexes VALUES (null, 'female');