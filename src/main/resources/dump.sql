INSERT INTO USERS VALUES (null, 'Тирион', 'Ланистер', '1987-09-28', 2, '+7 999 999 99 99', 'tyrion@adm.ru', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 1, 'false', '1.jpg');
INSERT INTO USERS VALUES (null, 'Серсея', 'Ланистер', '1986-05-23', 3, '+7 888 888 88 88', 'cersei@user.ru', 'f6b07b6c1340e947b861def5f8b092d8ee710826dc56bd175bdc8f3a16b0b8acf853c64786a710dedf9d1524d61e32504e27d60de159af110bc3941490731578', 2, 'false', '2.png');
INSERT INTO USERS VALUES (null, 'Джейми', 'Ланистер', '1985-03-05', 2, '+7 777 777 77 77', 'jaime@new.ru', 'ca9879bd727ba3bd815f05fe6b9e4640c774b61cc8f141295542cefc1b7b8fc6e3daf3f656555cdec355894e7af48964e88994d960f41ba8f61f7a05d5ddbd07', 2, 'false', '3.jpeg');

INSERT INTO friendship VALUES (null, 1, 2, 'true');
INSERT INTO friendship VALUES (null, 1, 3, 'true');
INSERT INTO friendship VALUES (null, 2, 3, 'true');

INSERT INTO messages VALUES (null, '2018-07-09 10:11:12', 1, 2, 'Привет! Как дела?');
INSERT INTO messages VALUES (null, '2018-07-10 10:11:12', 2, 1, 'Привет! Спасибо, хорошо=)');
INSERT INTO messages VALUES (null, '2018-07-11 10:11:12', 3, 1, 'Привет! Отец недоволен тобой! Он хочет тебя казнить');
INSERT INTO messages VALUES (null, '2018-07-11 10:12:34', 1, 3, 'Что мне делать? Я не хочу умирать! У меня есть арбалет. Может из арбалета его?');
INSERT INTO messages VALUES (null, '2018-07-12 10:11:12', 3, 2, 'Привет! Я помогу тебе с Вестерросом! Ведь Ланистеры всегда платят свои долги=)!');
INSERT INTO messages VALUES (null, '2018-07-13 10:11:12', 1, 2, 'Чего делаешь?');
INSERT INTO messages VALUES (null, '2018-07-14 10:11:12', 2, 1, 'Вестеррос захватываю! Ах-ха-хаа-хааа');
