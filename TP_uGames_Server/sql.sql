DROP TABLE TP1_MEMBER;

CREATE TABLE TP1_MEMBER(
   Id varchar2(100),
   password varchar2(100),
   name varchar2(100),
   nickname varchar2(100),
   email varchar2(100),
   sex varchar2(100),
   recommend varchar2(100),
   question varchar2(100),
   answer varchar2(100),
   point number
);

SELECT * FROM TP1_MEMBER;

DROP TABLE horseInfo;

CREATE TABLE horseInfo(
	horseNum number,
	horseName varchar2(100),
	horsePath varchar2(100),
	physical1 number,
	physical2 number,
	horseDividendRate number
);

SELECT * FROM horseInfo;

INSERT INTO horseInfo
VALUES (0, '외계마', 'image/alien.jpg', 6, 0, 4);
INSERT INTO horseInfo
VALUES (1, '갈색마', 'image/brown.jpg', 3, 1, 4);
INSERT INTO horseInfo
VALUES (2, '지옥마', 'image/hell.jpg', 2, 2, 4);
INSERT INTO horseInfo
VALUES (3, '살인마', 'image/joker.jpg', 8, -1, 4);
INSERT INTO horseInfo
VALUES (4, '로또마', 'image/lotto.gif', 1021, -1000, 100);
INSERT INTO horseInfo
VALUES (5, '랜덤마', 'image/random.jpg', 16, -8, 30);
INSERT INTO horseInfo
VALUES (6, '적토마', 'image/red.jpg', 3, 2, 3.3);
INSERT INTO horseInfo
VALUES (7, '로봇마', 'image/robot.jpg', 5, 1, 3.3);
INSERT INTO horseInfo
VALUES (8, '해마', 'image/sea.jpg', 11, -3, 5);
INSERT INTO horseInfo
VALUES (9, '해골마', 'image/skeleton.jpg', 14, -5, 6.6);
INSERT INTO horseInfo
VALUES (10, '번개마', 'image/thunder.jpg', 2, 3, 2.5);
INSERT INTO horseInfo
VALUES (11, '유니콘', 'image/unicon.jpg', 9, -4, 10);
INSERT INTO horseInfo
VALUES (12, '얼룩말', 'image/zibla.jpg', 5, 0, 5);


insert into member(id, password)
values ('2', '2');
