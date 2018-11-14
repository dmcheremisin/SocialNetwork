INSERT INTO USERS VALUES (null, 'Дима', 'Ч', '1987-09-28', 1, '+7 999 235 6695', 'admin@adm.ru', '123', 1, 'false');
INSERT INTO USERS VALUES (null, 'Аня', 'Ч', '1988-05-23', 2, '+7 888 236 9485', 'user@user.ru', '456', 2, 'false');
INSERT INTO USERS VALUES (null, 'Марк', 'Ч', '2015-03-17', 2, '', 'new@new.ru', '789', 2, 'false');

INSERT INTO friendship VALUES (1, 2, 'true');
INSERT INTO friendship VALUES (1, 3, 'true');
INSERT INTO friendship VALUES (2, 3, 'true');

INSERT INTO messages VALUES ('2018-07-09 10:11:12', 1, 2, 'Привет! Как дела?');