INSERT INTO USERS VALUES (null, 'Тирион', 'Ланистер', '1987-09-28', 2, '+7 999 999 99 99', 'tyrion@adm.ru', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 1, 'false', '1.jpg');
INSERT INTO USERS VALUES (null, 'Серсея', 'Ланистер', '1986-05-23', 3, '+7 888 888 88 88', 'cersei@user.ru', 'f6b07b6c1340e947b861def5f8b092d8ee710826dc56bd175bdc8f3a16b0b8acf853c64786a710dedf9d1524d61e32504e27d60de159af110bc3941490731578', 2, 'false', '2.png');
INSERT INTO USERS VALUES (null, 'Джейми', 'Ланистер', '1985-03-05', 2, '+7 777 777 77 77', 'jaime@new.ru', 'ca9879bd727ba3bd815f05fe6b9e4640c774b61cc8f141295542cefc1b7b8fc6e3daf3f656555cdec355894e7af48964e88994d960f41ba8f61f7a05d5ddbd07', 2, 'false', '3.jpeg');
INSERT INTO USERS VALUES (null, 'Нед', 'Старк', '1965-11-15', 2, '+7 111 111 11 11', 'stark@north.no', 'fb131bc57a477c8c9d068f1ee5622ac304195a77164ccc2d75d82dfe1a727ba8d674ed87f96143b2b416aacefb555e3045c356faa23e6d21de72b85822e39fdd', 2, 'false', '4.jpg');
INSERT INTO USERS VALUES (null, 'Санса', 'Старк', '1989-01-07', 3, '+7 222 222 22 22', 'sanst@north.no', '5f28f24f5520230fd1e66ea6ac649e9f9637515f516b2ef74fc90622b60f165eafca8f34db8471b85b9b4a2cdf72f75099ae0eb8860c4f339252261778d406eb', 2, 'false', '5.jpg');
INSERT INTO USERS VALUES (null, 'Арья', 'Старк', '1992-04-26', 3, '+7 222 222 22 22', 'astark@north.no', '5e3155774d39d97c5f9e17c108c2b3e0485a43ae34ebd196f61a6f8bf732ef71a49e5710594cfc7391db114edf99f5da3ed96ef1d6ca5e598e85f91bd41e7eeb', 2, 'false', '6.jpg');
INSERT INTO USERS VALUES (null, 'Джон', 'Сноу', '1988-10-11', 2, '+7 333 333 33 33', 'john@snow.no', 'a5e4209e841321ae706ee84b94b38088a18acc7643250e4bb0af543c9d7599a0854c8e08c2283ec0ee338806cca171206340a510c5c406beb6ec3b6f18150c4b', 2, 'false', '7.jpg');
INSERT INTO USERS VALUES (null, 'Даянерис', 'Таргариен', '1981-03-17', 3, '+7 444 444 44 44', 'dayneris@targarien.ta', '4e2589ee5a155a86ac912a5d34755f0e3a7d1f595914373da638c20fecd7256ea1647069a2bb48ac421111a875d7f4294c7236292590302497f84f19e7227d80', 2, 'false', '8.jpg');

INSERT INTO friendship VALUES (null, 1, 2, 'true');
INSERT INTO friendship VALUES (null, 1, 3, 'true');
INSERT INTO friendship VALUES (null, 2, 3, 'true');
INSERT INTO friendship VALUES (null, 5, 1, 'false');
INSERT INTO friendship VALUES (null, 6, 1, 'false');
INSERT INTO friendship VALUES (null, 4, 5, 'true');
INSERT INTO friendship VALUES (null, 4, 6, 'true');

INSERT INTO messages VALUES (null, '2018-07-09 10:11:12', 1, 2, 'Привет! Как дела?');
INSERT INTO messages VALUES (null, '2018-07-10 10:11:12', 2, 1, 'Привет! Спасибо, хорошо=)');
INSERT INTO messages VALUES (null, '2018-07-11 10:11:12', 3, 1, 'Привет! Отец недоволен тобой! Он хочет тебя казнить');
INSERT INTO messages VALUES (null, '2018-07-11 10:12:34', 1, 3, 'Что мне делать? Я не хочу умирать! У меня есть арбалет. Может из арбалета его?');
INSERT INTO messages VALUES (null, '2018-07-12 10:11:12', 3, 2, 'Привет! Я помогу тебе с Вестерросом! Ведь Ланистеры всегда платят свои долги=)!');
INSERT INTO messages VALUES (null, '2018-07-13 10:11:12', 1, 2, 'Чего делаешь?');
INSERT INTO messages VALUES (null, '2018-07-14 10:11:12', 2, 1, 'Вестеррос захватываю! Ах-ха-хаа-хааа');
