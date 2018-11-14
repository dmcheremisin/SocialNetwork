CREATE TABLE roles (
  id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  role VARCHAR(15)
);
CREATE TABLE users(
  id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  firstname VARCHAR(100),
  lastname VARCHAR(100),
  dob DATE NOT NULL,
  phone varchar(20),
  email varchar(100),
  passwordhash VARCHAR(100),
  role int NOT NULL,
  blocked BOOLEAN DEFAULT FALSE,
  INDEX (email),
  FOREIGN KEY (role) REFERENCES roles(id)
);
CREATE VIEW userrole AS SELECT users.id as userid, roles.role as role FROM users JOIN roles ON users.role=roles.id;
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
  INDEX (dt),
  FOREIGN KEY (usersender) REFERENCES users(id),
  FOREIGN KEY (usersender) REFERENCES users(id)
);