DROP TABLE A_POST;
DROP TABLE QNA_POST;
DROP TABLE P_TAG;
DROP TABLE R_POST;
DROP TABLE LIKE_LIST;
DROP TABLE P_COMMENT;
DROP TABLE M_POST;
DROP TABLE TAG_BD;
DROP TABLE TAG_HD;
DROP TABLE LANDMARK;
DROP TABLE CITY;
DROP TABLE FOLLOW;
DROP TABLE USER_INFO;

DROP SEQUENCE CITY_SQ;
DROP SEQUENCE LA_SQ;
DROP SEQUENCE APOST_SQ;
DROP SEQUENCE COMM_SQ;
DROP SEQUENCE FL_SQ;
DROP SEQUENCE TB_SQ;
DROP SEQUENCE LIKE_SQ;
DROP SEQUENCE MPO_SQ;
DROP SEQUENCE PTAG_SQ;
DROP SEQUENCE QNA_SQ;
DROP SEQUENCE RPOST_SQ;
DROP SEQUENCE TH_SQ;
DROP SEQUENCE USER_SQ;






-- CREATE TABLE


-- 국가
CREATE TABLE COUNTRY(
    C_CODE NUMBER PRIMARY KEY,
    C_NAME VARCHAR2(20) NOT NULL UNIQUE
);

-- 도시
CREATE TABLE CITY(
  CT_CODE NUMBER PRIMARY KEY,
  CT_NAME VARCHAR2(30) NOT NULL UNIQUE,
  C_CODE NUMBER CONSTRAINT CITY_FK REFERENCES COUNTRY(C_CODE)
  
);

-- 랜드마크
CREATE TABLE LANDMARK(
    L_CODE NUMBER PRIMARY KEY,
    L_NAME VARCHAR2(100) NOT NULL UNIQUE,
    CT_CODE CONSTRAINT LAND_FK REFERENCES CITY(CT_CODE)
);


-- 태그(분류)
CREATE TABLE TAG_HD(
    TH_CODE NUMBER PRIMARY KEY,
    TH_NAME VARCHAR2(20) NOT NULL UNIQUE
);


-- 태그(상세)
CREATE TABLE TAG_BD(
    TB_CODE NUMBER PRIMARY KEY,
    TB_NAME VARCHAR2(20) NOT NULL,
    TH_CODE CONSTRAINT TAG_FK REFERENCES TAG_HD(TH_CODE)
);


-- 회원정보
CREATE TABLE USER_INFO(
    U_NUM NUMBER PRIMARY KEY,
    U_ID VARCHAR2(20) NOT NULL UNIQUE,
    U_PW VARCHAR2(20) NOT NULL,
    U_NAME VARCHAR2(20) NOT NULL,
    U_BT DATE NOT NULL,
    U_JDATE DATE NOT NULL,
    U_MAIL VARCHAR2(40) NOT NULL UNIQUE,
    U_PHONE NUMBER(11) NOT NULL UNIQUE,
    U_PROPIC VARCHAR2(200) 
);

ALTER TABLE USER_INFO MODIFY(U_GENDER VARCHAR2(3));
ALTER TABLE USER_INFO MODIFY(U_M_CK VARCHAR2(4));


-- 팔로우
CREATE TABLE FOLLOW(
    F_NO NUMBER PRIMARY KEY,
    F_CALL_USER CONSTRAINT F_CALL_FK REFERENCES USER_INFO(U_NUM) NOT NULL,              -- 팔로우 대상 아이디
    F_TARGET_USER CONSTRAINT F_TARGET_FK REFERENCES USER_INFO(U_NUM) NOT NULL           -- 팔로우 요청 아이디
);


-- 게시글
CREATE TABLE M_POST(
    P_NO NUMBER PRIMARY KEY,
    P_TITLE VARCHAR2(100) NOT NULL,
    P_DATE DATE NOT NULL,
    P_TEXT VARCHAR2(200) NOT NULL,
    P_PIC VARCHAR2(200) NOT NULL,
    CT_CODE CONSTRAINT POST_CT_CODE_FK REFERENCES CITY(CT_CODE),
    C_CODE CONSTRAINT POST_C_CODE_FK REFERENCES COUNTRY(C_CODE),
    L_CODE CONSTRAINT POST_L_CODE_FK REFERENCES LANDMARK(L_CODE),
    U_NUM CONSTRAINT POST_U_NUM_FK REFERENCES USER_INFO(U_NUM) NOT NULL,
    M_VIEW_COUNT NUMBER DEFAULT 0 NOT NULL
);


-- 게시글 태그
CREATE TABLE P_TAG(
    PT_NO NUMBER PRIMARY KEY,
    P_NO CONSTRAINT TAG_POST_NO_FK REFERENCES M_POST(P_NO),
    TB_CODE CONSTRAINT TAG_POST_BD_CODE_FK REFERENCES TAG_BD(TB_CODE)
);


-- 댓글
CREATE TABLE P_COMMENT(
    CM_NO NUMBER PRIMARY KEY,
    CM_TEXT VARCHAR2(300) NOT NULL,
    CM_DATE DATE NOT NULL,
    U_NUM CONSTRAINT P_COMM_U_NUM_FK REFERENCES USER_INFO(U_NUM) NOT NULL,
    P_NO CONSTRAINT P_COMM_POST_FK REFERENCES M_POST(P_NO) NOT NULL
);


-- 신고
CREATE TABLE R_POST(
    R_NO NUMBER PRIMARY KEY,
    R_TITLE VARCHAR2(200) NOT NULL,
    R_DATE DATE NOT NULL,
    R_TEXT VARCHAR2(400) NOT NULL,
    P_NO CONSTRAINT P_REPORT_NO_FK REFERENCES M_POST(P_NO),
    CM_NO CONSTRAINT R_REPORT_NO_FK REFERENCES P_COMMENT(CM_NO),
    U_NUM CONSTRAINT P_REPORT_USER_NO_FK REFERENCES USER_INFO(U_NUM) NOT NULL
);

-- REPORT 답글
CREATE TABLE A_POST(
    A_NO NUMBER PRIMARY KEY,
    A_DATE DATE NOT NULL,
    A_TITLE VARCHAR2(200) NOT NULL,
    A_TEXT VARCHAR2(400) NOT NULL,
    R_NO CONSTRAINT A_POST_R_NO REFERENCES R_POST(R_NO) NOT NULL,
    A_VIEW_COUNT NUMBER DEFAULT 0 NOT NULL
);


-- 좋아요
CREATE TABLE LIKE_LIST(
    L_NO NUMBER PRIMARY KEY,
    L_DATE DATE NOT NULL,
    P_NO CONSTRAINT LIKE_POST_FK REFERENCES M_POST(P_NO) NOT NULL,
    U_NUM CONSTRAINT LIKE_USER_FK REFERENCES USER_INFO(U_NUM) NOT NULL
);


-- QNA
CREATE TABLE QNA_POST(
    Q_NO NUMBER PRIMARY KEY,
    Q_TITLE VARCHAR2(100) NOT NULL,
    Q_TEXT VARCHAR2(300) NOT NULL,
    Q_DATE DATE NOT NULL,
    Q_PIC VARCHAR2(200),
    U_NUM CONSTRAINT QNA_USER_FK REFERENCES USER_INFO(U_NUM) NOT NULL,
    QNA_VIEW_COUNT NUMBER DEFAULT 0 NOT NULL
);




-- CREATE SEQUENCE


-- 도시는 600으로 시작
CREATE SEQUENCE CITY_SQ START WITH 600;

-- 랜드마크는 2000으로 시작
CREATE SEQUENCE LA_SQ START WITH 2001;

-- 태그(분류)는 4000 시작
CREATE SEQUENCE TH_SQ START WITH 4001;

-- 태그(상세)는 6000 시작
CREATE SEQUENCE TB_SQ START WITH 6001;

-- 회원 정보는 10300으로 시작
CREATE SEQUENCE USER_SQ START WITH 10301;

-- 팔로우는 20200으로 시작
CREATE SEQUENCE FL_SQ START WITH 20201;

-- 게시글 번호는 1부터 시작
CREATE SEQUENCE MPO_SQ START WITH 1;

-- 게시글(태그) 번호는 20300부터 시작
CREATE SEQUENCE PTAG_SQ START WITH 20301;

-- 댓글 번호 40001부터 시작
CREATE SEQUENCE COMM_SQ START WITH 40001;

-- 신고 번호 2100 시작
CREATE SEQUENCE RPOST_SQ START WITH 2101;

-- 답글 번호 3100시작
CREATE SEQUENCE APOST_SQ START WITH 3100;

-- 좋아요는 44001부터 시작
CREATE SEQUENCE LIKE_SQ START WITH 44001;

-- QNA 6000시작
CREATE SEQUENCE QNA_SQ START WITH 6000;




COMMIT;

-- INSERT


-- 국가
INSERT INTO COUNTRY VALUES (82, '한국');

-- 미국과 캐나다는 같은 국가번호 사용으로 01, 02로 분류 
INSERT INTO COUNTRY VALUES (101, '미국');
INSERT INTO COUNTRY VALUES (102, '캐나다');
INSERT INTO COUNTRY VALUES (101, '미국');
INSERT INTO COUNTRY VALUES (81, '일본');
INSERT INTO COUNTRY VALUES (20, '이집트');
INSERT INTO COUNTRY VALUES (30, '그리스');
INSERT INTO COUNTRY VALUES (33, '프랑스');
INSERT INTO COUNTRY VALUES (44, '영국');
INSERT INTO COUNTRY VALUES (41, '스위스');
INSERT INTO COUNTRY VALUES (45, '덴마크');
INSERT INTO COUNTRY VALUES (49, '독일');



-- 도시
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '서울', 82);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '부산', 82);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '뉴욕', 101);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '워싱턴D.C', 101);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '캘리포니아', 101);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '도쿄', 81);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '오타와', 102);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '카이로', 20);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '아테네', 30);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '파리', 33);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '런던', 44);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '베른', 41);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '코펜하겐', 45);
INSERT INTO CITY VALUES(CITY_SQ.NEXTVAL, '베를린', 49);


-- 랜드마크
INSERT INTO LANDMARK VALUES(LA_SQ.NEXTVAL, '남산타워', 600);
INSERT INTO LANDMARK VALUES(LA_SQ.NEXTVAL, '광안대교', 601);
INSERT INTO LANDMARK VALUES(LA_SQ.NEXTVAL, '자유의여신상', 602);
INSERT INTO LANDMARK VALUES(LA_SQ.NEXTVAL, '백악관', 603);
INSERT INTO LANDMARK VALUES(LA_SQ.NEXTVAL, '기타', null);


-- 태그(대분류)
INSERT INTO TAG_HD VALUES(TH_SQ.NEXTVAL, '기분');
INSERT INTO TAG_HD VALUES(TH_SQ.NEXTVAL, '유명인');
INSERT INTO TAG_HD VALUES(TH_SQ.NEXTVAL, '동물');
INSERT INTO TAG_HD VALUES(TH_SQ.NEXTVAL, '식물');
INSERT INTO TAG_HD VALUES(TH_SQ.NEXTVAL, '음식');
INSERT INTO TAG_HD VALUES(TH_SQ.NEXTVAL, '기타');


-- 태그(소분류)
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '행복', 4001);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '슬픔', 4001);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '윤동주', 4002);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '고양이', 4003);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '강아지', 4003);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '햄스터', 4003);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '사자', 4003);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '호랑이', 4003);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '늑대', 4003);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '장미', 4004);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '포도나무', 4004);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '가로수', 4004);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '소나무', 4004);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '대나무', 4004);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '튤립', 4004);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '감자칩', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '아메리카노', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '파스타', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '치킨', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '스테이크', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '볶음밥', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '와플', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '커피', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '과자', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '녹차', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '홍차', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '주스', 4005);
INSERT INTO TAG_BD VALUES(TB_SQ.NEXTVAL, '아이스티', 4005);


-- 회원정보
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'admin', '1234', '관리자', '1994/01/01','2021/07/01', 'admin@abc.com',0101234567, NULL);
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test1', '1111', '가나다', '1995/12/11','2021/07/08', 'test@ttest1.com',01011112222, NULL);
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test2', '2222', '테스트', '1998/06/22','2021/07/11', 'test2n@dsd.com',01033334444, NULL);
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test3', '3333', '김유저', '1991/05/15','2021/07/01', 'test3@abcd.com',01099997777, NULL);
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test4', '1234', '여행자', '1996/01/15','2021/05/02', 'test4@abcd.com',01099227777, 'upropic4.jpg');
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test5', '1234', 'like__to', '1997/06/25', '2021/05/06', 'test5@abcd.com',01033997777, 'upropic5.jpg');
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test6', '1234', 'sun_rize', '1992/06/19', '2021/02/08', 'test6@abcd.com',01094497777,'upropic6.jpg');
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test7', '1234', '코세글자', '1995/02/11', '2021/05/11', 'test7@abcd.com',01096697777,'upropic7.jpg');
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test8', '1234', '기록', '1992/01/15', '2021/05/02', 'test8@abcd.com',01099998877,'upropic8.jpg');
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test9', '1234', 'home___out', '1998/12/05', '2021/07/12', 'test9@abcd.com',01092297777,'upropic9.jpg');
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test10', '1234', '식물왕', '1994/08/15', '2021/06/22', 'test10@abcd.com',01092297347,'upropic10.jpg');
INSERT INTO USER_INFO VALUES(USER_SQ.NEXTVAL, 'test11', '1234', '아장', '1996/08/25', '2021/03/19', 'test11@abcd.com',01092291277,'upropic11.jpg');


-- 팔로우
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10302, 10304);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10304, 10302);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10304, 10305);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10303, 10306);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10302, 10303);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10306, 10309);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10307, 10312);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10307, 10302);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10308, 10302);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10309, 10308);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10310, 10304);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10311, 10308);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10312, 10309);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10311, 10312);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10311, 10304);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10311, 10306);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10311, 10307);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10311, 10308);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10308, 10309);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10308, 10303);
INSERT INTO FOLLOW VALUES(FL_SQ.NEXTVAL, 10308, 10304);


COMMIT;

-- 게시글
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목', '2021/06/12','TEST용 내용','testimg.jpg',600, 82,2001,10301, 12);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목1', '2021/06/29','TEST용 내용111','testimg.jpg',601, 82,2001,10302, 55);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목2', '2021/05/18','TEST용 내용222','testimg.jpg',602, 101,2003,10304, 67);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목3', '2021/02/05','TEST용 내용333','testimg.jpg',603, 101,2004,10304, 71);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목4', '2021/06/12','TEST용 내용444','testimg.jpg',603, 101,2004,10303, 64);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목', '2021/06/12','TEST용 내용','testimg.jpg',600, 82,2001,10301, 122);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목1', '2021/06/29','TEST용 내용111','testimg.jpg',601, 82,2001,10302, 855);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목2', '2021/05/18','TEST용 내용222','testimg.jpg',602, 101,2003,10304, 567);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목3', '2021/02/05','TEST용 내용333','testimg.jpg',603, 101,2004,10304, 171);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목4', '2021/06/12','TEST용 내용444','testimg.jpg',603, 101,2004,10303, 4);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목', '2021/06/12','TEST용 내용','testimg.jpg',600, 82,2001,10301, 12);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목1', '2021/06/29','TEST용 내용111','testimg.jpg',601, 82,2001,10302, 56);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목2', '2021/05/18','TEST용 내용222','testimg.jpg',602, 101,2003,10304, 17);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목3', '2021/02/05','TEST용 내용333','testimg.jpg',603, 101,2004,10304, 71);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목4', '2021/06/12','TEST용 내용444','testimg.jpg',603, 101,2004,10303, 24);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목', '2021/06/12','TEST용 내용','testimg.jpg',600, 82,2001,10301, 12);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목1', '2021/06/29','TEST용 내용111','testimg.jpg',601, 82,2001,10302, 95);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목2', '2021/05/18','TEST용 내용222','testimg.jpg',602, 101,2003,10304, 17);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목3', '2021/02/05','TEST용 내용333','testimg.jpg',603, 101,2004,10304, 651);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'TEST용 제목4', '2021/06/12','TEST용 내용444','testimg.jpg',603, 101,2004,10303, 634);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, '부산 벽화마을에서', '2021/06/11','하늘이 맑아서 더 행복!','test1.jpg', 601, 82, 2005, 10311, 1532);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'paris', '2021/06/11','두번째 밤인데 낮보다 밤이 예뻐요','test2.jpg', 609, 33, 2005 ,10310, 1225);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, '동대문', '2021/07/14','흐린 날이라 기분이 좋지 않았는데 마음에 드는 사진이 나와 기쁘다','test3.jpg', 600, 82, 2005 ,10309, 998);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, '비행기 탔음!', '2021/06/13','제일 두근거리는 순간','test4.jpg', 600, 82, 2005 ,10307, 321);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, 'Nam-san', '2021/06/21','오랜만에 등산했더니 다음날 근육통으로 쓰러졌지만 야경은 최고','test5.jpg', 600, 82, 2001 ,10304, 3422);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, '미국에서 첫 사진', '2021/07/28','카페에서 바삐 가는 사람을 보고 있으니 묘한 기분이 들더라고요','test6.jpg', 603, 101, 2004 ,10305, 531);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, '해운대보다 광안리', '2021/08/14','개인적으로 해운대보다 광안대교를 더 좋아해','test7.jpg', 601, 82, 2002 ,10308, 1231);
INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, '또 가고 싶다', '2021/08/08','여행병이 또 도지고 있는 중~~','test8.jpg', 603, 101, 2003 ,10306, 1112);


-- 게시글(태그)
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 1, 6001);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 1, 6004);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 2, 6002);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 3, 6001);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 3, 6005);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 3, 6006);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 4, 6002);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 5, 6003);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 21, 6001);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 22, 6001);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 23, 6001);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 24, 6001);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 25, 6001);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 26, 6001);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 27, 6001);
INSERT INTO P_TAG VALUES(PTAG_SQ.NEXTVAL, 28, 6001);

-- 댓글
INSERT INTO P_COMMENT VALUES(COMM_SQ.NEXTVAL, '댓글테스트', '2021/07/15',10303,2);
INSERT INTO P_COMMENT VALUES(COMM_SQ.NEXTVAL, '댓글테스트22', '2021/07/15',10304,3);
INSERT INTO P_COMMENT VALUES(COMM_SQ.NEXTVAL, '댓글테스트33', '2021/07/16',10304,2);
INSERT INTO P_COMMENT VALUES(COMM_SQ.NEXTVAL, '댓글테스트44', '2021/07/17',10303,4);
INSERT INTO P_COMMENT VALUES(COMM_SQ.NEXTVAL, '댓글테스트55', '2021/07/18',10302,4);


-- 신고
INSERT INTO R_POST VALUES(RPOST_SQ.NEXTVAL, 'RE제목TEST', '2021/06/20', '신고접수내용', NULL, NULL, 10303);
INSERT INTO R_POST VALUES(RPOST_SQ.NEXTVAL, 'RE제목TEST1', '2021/06/23', '신고접수내용11', 3, NULL, 10303);
INSERT INTO R_POST VALUES(RPOST_SQ.NEXTVAL, 'RE제목TEST2', '2021/06/28', '신고접수내용22', NULL, 40002, 10302);
INSERT INTO R_POST VALUES(RPOST_SQ.NEXTVAL, 'RE제목TEST3', '2021/07/10', '신고접수내용33', NULL, NULL, 10302);
INSERT INTO R_POST VALUES(RPOST_SQ.NEXTVAL, 'RE제목TEST4', '2021/07/11', '신고접수내용44', NULL, NULL, 10304);


-- 답글
INSERT INTO A_POST VALUES(APOST_SQ.NEXTVAL, '2021/06/22','답변제목','답변내용',2101, 1);
INSERT INTO A_POST VALUES(APOST_SQ.NEXTVAL, '2021/06/24','답변제목11','답변내용11',2102, 1);
INSERT INTO A_POST VALUES(APOST_SQ.NEXTVAL, '2021/06/29','답변제목22','답변내용22',2103, 1);
INSERT INTO A_POST VALUES(APOST_SQ.NEXTVAL, '2021/07/12','답변제목33','답변내용33',2104, 1);
INSERT INTO A_POST VALUES(APOST_SQ.NEXTVAL, '2021/07/20','답변제목44','답변내용33',2105, 1);


-- 좋아요 ( 최소 20번 이상 반복해서 insert 해주세요)
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/07/04', 2, 10303);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/14', 6, 10301);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/05/18', 20, 10301);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/05/11', 11, 10304);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/29', 12, 10303);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/04/01', 21, 10303);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/02/17', 22, 10301);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/16', 8, 10301);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/04/11', 23, 10304);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/03/30', 24, 10311);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/01/07', 25, 10310);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/07/11', 7, 10309);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/15', 26, 10308);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/02/01', 27, 10306);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/16', 3, 10305);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/02/11', 28, 10301);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/04/15', 21, 10304);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/05/12', 22, 10303);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/16', 23, 10301);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/04/11', 24, 10304);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/22', 25, 10312);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/03/30', 26, 10311);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/01/07', 27, 10310);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/07/11', 28, 10309);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/15', 21, 10308);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/01/07', 25, 10310);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/07/11', 7, 10309);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/15', 26, 10308);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/13', 2, 10307);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/02/01', 27, 10306);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/16', 3, 10305);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/02/11', 28, 10301);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/04/15', 21, 10304);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/05/12', 22, 10303);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/16', 23, 10301);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/04/11', 24, 10304);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/04/11', 24, 10304);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/22', 25, 10312);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/03/30', 26, 10311);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/01/07', 27, 10310);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/07/11', 28, 10309);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/06/15', 21, 10308);
INSERT INTO LIKE_LIST VALUES(LIKE_SQ.NEXTVAL, '2021/01/07', 25, 10310);


-- QNA
INSERT INTO QNA_POST VALUES(QNA_SQ.NEXTVAL, 'QNA제목', 'QNA내용','2021/06/01',NULL,10301, 125);
INSERT INTO QNA_POST VALUES(QNA_SQ.NEXTVAL, 'QNA제목1', 'QNA내용111','2021/06/01',NULL,10301, 634);
INSERT INTO QNA_POST VALUES(QNA_SQ.NEXTVAL, 'QNA제목2', 'QNA내용222','2021/06/01',NULL,10301, 32);
INSERT INTO QNA_POST VALUES(QNA_SQ.NEXTVAL, 'QNA제목3', 'QNA내용333','2021/06/01',NULL,10301, 315);
INSERT INTO QNA_POST VALUES(QNA_SQ.NEXTVAL, 'QNA제목4', 'QNA내용444','2021/06/01',NULL,10301, 332);
INSERT INTO QNA_POST VALUES(QNA_SQ.NEXTVAL, 'QNA제목5', 'QNA내용555','2021/06/01',NULL,10301, 123);




commit;

SELECT * FROM USER_INFO;
