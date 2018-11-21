INSERT INTO USERS VALUES (null, 'Дима', 'Ч', '1987-09-28', 2, '+7 999 235 6695', 'admin@adm.ru', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 1, 'false', null);
INSERT INTO USERS VALUES (null, 'Аня', 'Ч', '1988-05-23', 3, '+7 888 236 9485', 'user@user.ru', 'f6b07b6c1340e947b861def5f8b092d8ee710826dc56bd175bdc8f3a16b0b8acf853c64786a710dedf9d1524d61e32504e27d60de159af110bc3941490731578', 2, 'false', null);
INSERT INTO USERS VALUES (null, 'Марк', 'Ч', '2015-03-17', 2, '', 'new@new.ru', 'ca9879bd727ba3bd815f05fe6b9e4640c774b61cc8f141295542cefc1b7b8fc6e3daf3f656555cdec355894e7af48964e88994d960f41ba8f61f7a05d5ddbd07', 2, 'false', null);

INSERT INTO friendship VALUES (1, 2, 'true');
INSERT INTO friendship VALUES (1, 3, 'true');
INSERT INTO friendship VALUES (2, 3, 'true');

INSERT INTO messages VALUES ('2018-07-09 10:11:12', 1, 2, 'Привет! Как дела?');