INSERT INTO USERS VALUES (null, 'Cersei', 'Lannister', '1986-05-23', 3, '+7 888 888 88 88', 'cersei@user.ru', '38107f7fb68f8f7ddd5b16ddf9eee21f97fad236a86300be12f07677f92a512a472f87a2ccab1314938fdfa4a67b6b542ef1011ec6f639a922230049cb0adfee', 2, 'false', '2.png');
INSERT INTO USERS VALUES (null, 'Jaime', 'Lannister', '1985-03-05', 2, '+7 777 777 77 77', 'jaime@new.ru', '6d908c2d1988ad42c39d400c667bfd143e394dc2d56e9dd951ade35824165db8b153abe38d5a1f963a1aafc605ba94467bb1f38af59f07b82b99d78d5358e079', 2, 'false', '3.jpeg');
INSERT INTO USERS VALUES (null, 'Ned', 'Stark', '1965-11-15', 2, '+7 111 111 11 11', 'stark@north.no', '8877122ca69030de3fda1cbbc571ed58cd2e96ae70c0fe858d38eca3e1c4ebc1e4df01736ac2122bdd4b7a3a4b398f1f0bc0fc40b0223078e44e39fe9a725ff5', 1, 'false', '4.jpg');
INSERT INTO USERS VALUES (null, 'Sansa', 'Stark', '1989-01-07', 3, '+7 222 222 22 22', 'sanst@north.no', '1afd61dc53aacfa04379bb29772022a9382818803ee7c64a9f0c6bf695b92e24bccde78cd9de015c757648747c6b6991ca2aac164015386a6120a011d4337994', 2, 'false', '5.jpg');
INSERT INTO USERS VALUES (null, 'Arya', 'Stark', '1992-04-26', 3, '+7 222 222 22 22', 'astark@north.no', '568589d138ba59b486de9049f653932791ce27d26842eac3bd7450ddb885497d54fa345f10c23bdebc7b606040441ca841d9ae92aa24e489cd56707416bb6f91', 2, 'false', '6.jpg');
INSERT INTO USERS VALUES (null, 'Jon', 'Snow', '1988-10-11', 2, '+7 333 333 33 33', 'john@snow.no', 'c01496fff62534877ac2a409f073fc8353b1ded49b0aa70f4c78c5d255309d1e89115804c71756030143498ec746e83c08593324a7851537374fe197ef4c25b2', 2, 'true', '7.jpg');
INSERT INTO USERS VALUES (null, 'Daenerys', 'Targaryen', '1981-03-17', 3, '+7 444 444 44 44', 'dayneris@targarien.ta', '569dfa8790405d0e8e20e212d689171bc1c4cf9d49e2cef02ac7f6426f35412cc68500095ecf2710ab04f12a3d013cda10f143e6082509f064c27643af758669', 2, 'false', '8.jpg');
INSERT INTO USERS VALUES (null, 'Rob', 'Stark', '1988-10-13', 2, '+7 567 465 44 46', 'rob@stark.ta', '21520a55c310166f9365ab6f1981d410a77568af0ec71a623e27e78751f4268c8371663a2e1674369272fff0cca8b1f3e885d064631f17077a81d5460919ad54', 2, 'false', '9.jpg');
INSERT INTO USERS VALUES (null, 'Tywin', 'Lannister', '1952-08-27', 2, '+7 123 323 22 44', 'tywin@lannister.la', '6a04fd2f633b024745bb7cc3afa9dea30cbf49c3b953e80304690bae2fbb960168219a4e3519e0f47f312ce26cfd0f65e65d716a504c1c26ce7942bf6c52aae1', 2, 'false', '10.jpg');
INSERT INTO USERS VALUES (null, 'Brienne', 'Tarth', '1967-02-19', 3, '+7 132 4567', 'brienne@tarth.ta', '28afafba05a42eb3b3ab163b161adace186d74be3e59424dfd1e185533578201af22feb8925ddffe9984244ae88c988f55a3b133b18f4c55f73214b7dc2cce67', 2, 'false', '11.jpg');
INSERT INTO USERS VALUES (null, 'Lord', 'Varys', '1954-12-01', 2, '+7 131 123 9875', 'lord@varys.va', '1f7fbf10e06c4fd15d94f305ae41f386bc04653b425ad1f87330a5a129faaa79cc27ae392773c12e8ebb883dda0b3230cf45e905ab230f76860f9c7bcbadf548', 2, 'false', '12.jpeg');

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
INSERT INTO messages VALUES (null, '2018-07-12 10:11:12', 3, 2, 'Привет! Я помогу тебе с Вестерросом! Ведь Lannisterы всегда платят свои долги=)!');
INSERT INTO messages VALUES (null, '2018-07-13 10:11:12', 1, 2, 'Чего делаешь?');
INSERT INTO messages VALUES (null, '2018-07-14 10:11:12', 2, 1, 'Вестеррос захватываю! Ах-ха-хаа-хааа');
