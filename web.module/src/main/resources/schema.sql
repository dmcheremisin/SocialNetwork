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
  email varchar(100) UNIQUE,
  password VARCHAR(128),
  role int NOT NULL,
  blocked BOOLEAN DEFAULT FALSE,
  image VARCHAR(30),
  INDEX (email),
  FOREIGN KEY (role) REFERENCES roles(id),
  FOREIGN KEY (sex) REFERENCES sexes(id)
);
CREATE TABLE friendship (
  id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  usersender int,
  userreceiver int,
  accepted BOOLEAN,
  INDEX (usersender),
  INDEX (userreceiver),
  FOREIGN KEY (usersender) REFERENCES users(id),
  FOREIGN KEY (userreceiver) REFERENCES users(id)
);
CREATE TABLE messages (
  id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  dt DATETIME,
  usersender int,
  userreceiver int,
  message TEXT,
  INDEX (usersender),
  INDEX (userreceiver),
  FOREIGN KEY (usersender) REFERENCES users(id),
  FOREIGN KEY (userreceiver) REFERENCES users(id)
);
CREATE VIEW user_message AS
  SELECT m.id, m.dt as dt, m.message as message, s.id as sid, s.firstname as sfirstname, s.lastname as slastname, s.image as simage,
         r.id as rid, r.firstname as rfirstname, r.lastname as rlastname, r.image as rimage
  from messages as m
    join users as s on m.usersender =  s.id
    join users as r on m.userreceiver = r.id;

CREATE VIEW last_user_message AS
  SELECT m.id, m.dt as dt, m.message as message, s.id as sid, s.firstname as sfirstname, s.lastname as slastname, s.image as simage,
               r.id as rid, r.firstname as rfirstname, r.lastname as rlastname, r.image as rimage
  from messages as m
    join users as s on m.usersender =  s.id
    join users as r on m.userreceiver = r.id
  WHERE m.id IN
        (
          SELECT MAX(id)
          FROM messages
          GROUP BY usersender, userreceiver
        );

CREATE VIEW user_friends_requests AS
  SELECT f.id, f.accepted as accepted, s.id as sid, s.firstname as sfirstname, s.lastname as slastname, s.image as simage,
               r.id as rid, r.firstname as rfirstname, r.lastname as rlastname, r.image as rimage
  from friendship as f
    join users as s on f.usersender =  s.id
    join users as r on f.userreceiver = r.id;

INSERT INTO roles VALUES (1, 'admin');
INSERT INTO roles VALUES (2, 'member');

INSERT INTO sexes VALUES (null, 'undefined');
INSERT INTO sexes VALUES (null, 'male');
INSERT INTO sexes VALUES (null, 'female');

INSERT INTO USERS VALUES (null, 'Tyrion', 'Lannister', '1987-09-28', 2, '+7 999 999 99 99', 'tyrion@adm.ru', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 1, 'false', '1.jpg');