INSERT INTO roles (role) VALUES ('admin');
INSERT INTO roles (role) VALUES ('user');

INSERT INTO USERS (firstname, lastname, dob, phone, email, passwordhash, role, blocked) VALUES ('Дима', 'Ч', '1987-09-28', '+7 999 235 6695', 'admin@adm.ru', '123', 1, 'false');
INSERT INTO USERS (firstname, lastname, dob, phone, email, passwordhash, role, blocked) VALUES ('Аня', 'Ч', '1988-05-23', '+7 888 236 9485', 'user@user.ru', '456', 2, 'false');
INSERT INTO USERS (firstname, lastname, dob, phone, email, passwordhash, role, blocked) VALUES ('Марк', 'Ч', '2015-03-17', '', 'new@new.ru', '789', 2, 'false');

INSERT INTO friendship(usersender, userreceiver, accepted) VALUES (1, 2, 'true');
INSERT INTO friendship(usersender, userreceiver, accepted) VALUES (1, 3, 'true');
INSERT INTO friendship(usersender, userreceiver, accepted) VALUES (2, 3, 'true');

INSERT INTO messages(dt, usersender, userreceiver, message) VALUES ('2018-07-09 10:11:12', 1, 2, 'Привет! Как дела?');