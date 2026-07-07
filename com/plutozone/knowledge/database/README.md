# com.plutozone.knowledge.database


## Contents
1. [개요](#1-개요)
2. [설계](#2-설계)
3. [데이터 정규화](#3-데이터-정규화)
4. [SQL 기본](#4-sql-기본)
5. [SQL 확장](#5-sql-확장)
6. [데이터 조작 및 관리](#6-데이터-조작-및-관리)
7. [객체](#7-객체)
8. [성능(Performance)](#8-성능performance)
9. [데이터 무결성과 보안](#9-데이터-무결성과-보안)
10. [운영](#10-운영)
11. [주요 Query](#11-주요-query)
12. [주요 Command](#12-주요-command)
13. [프로젝트](#13-프로젝트)


## 1. 개요
> Data vs. DB(DataBase) vs. DBMS(DataBase Management System) vs. RDBMS(Relational DataBase Management System)
### 데이터(Data)와 정보(Information)
### DB(DataBase)란?
### DBMS(DataBase Management System)
### 관계형 데이터베이스(RDBMS, Relational DataBase Management System)


## 2. 설계
### 데이터 모델링 개념
### ERD(Entity Relationship Diagram)
### 엔터티(Entity)와 속성(Attribute)
### 관계(Relationship)
### 기본키(Primary Key)와 외래키(Foreign Key)


## 3. 데이터 정규화
### 정규화의 필요성
### 제1정규형(1NF)
### 제2정규형(2NF)
### 제3정규형(3NF)
### 반정규화


## 4. SQL 기본
### SQL 개요
- DDL(Data Definition Language) vs. DML(Data Manipulation Language) and DCL(Data Control Language)

### 조회(SELECT)
### 조건(WHERE)
### 정렬(ORDER BY)
### 그룹(GROUP BY)
### 집계(HAVING)
### 구분(DISTINCT)


## 5. SQL 고급
### Overview of SQL Joins
- (Inner) Join은 내부 조인으로 테이블을 조인할 때 모든 테이블의 지정된 열에 데이터가 있어야 한다.
- Left/Right/Full (Outer) Join은 외부 조인으로 1개의 테이블에만 데이터가 있어도 조인한다.
- Self Join은 자체 조인으로 자신이 자신과 조인하며 1개의 테이블을 사용한다.
- Cross Join은 상호 조인으로 한 쪽 테이블의 모든 행과 다른 쪽 테이블의 모든 행을 조인한다.
- Natural Join은 등가 조인으로 두 테이블 간의 동일한 이름을 갖는 모든 컬럼들에 대해 조인한다.

### Subquery
### UNION
### 제어문(IF, CASE, ...)
### 함수(집계, 문자열, 날짜, ...)


## 6. 데이터 조작 및 관리
### INSERT
### UPDATE
### DELETE
### MERGE
### 트랜잭션(Transaction)
### COMMIT vs. ROLLBACK


## 7. 객체
### Table
### View
### Index
### Sequence
### Synonym
### Stored Procedure
### Function
### Trigger


## 8. 성능(Performance)
### 최적화를 위한 권장 사항
#### 설계(Design) 시
- 반복률이 높은 Column은 Code Table로 작성한다.
- Table 설계 시
	- Table 생성 전에 정규화 및 각 Column의 데이터 유형을 계획하라.
	- 블록 영역을 위해 INITIAL, NEXT, EXTENTS, PCTFREE, PCTUSED를 충분히 고려하라.
	- 지정된 Table Space를 반드시 지정하라(사용자 데이터, 롤백 데이터, 분류 데이터 등).
	- 필요한 경우 No Logging 키워드를 사용하라.
	- 필요한 경우 Cache 키워드를 사용하라.
	- Table의 Partition 여부를 결정하라.

#### 개발(Development) 시
- 필요한 Table의 항목만 추출한다.
	- (X) SELECT * FROM Table1 WHERE …
	- (O) SELECT FieldName1, FieldName2, … FROM Table1 WHERE …
- Index가 설정된 항목을 활용한다.
	- WHERE절에는 Index가 있는 항목조건을 사용한다.
- 대량의 Data를 처리할 경우 Stored Procedure를 사용한다.
- 복잡한 조건으로 Data를 추출하는 것보다 SQL문을 나누어서 사용한다.
	- 일반적으로 4개 이상의 Table을 조인하면 Performance 저하가 발생한다.
- 전체 Data를 검색할 경우 WHERE절에 Index가 설정된 항목에 결과에 상관없는 조건을 부여한다.
	- (X) SELECT * FROM Table1
	- (O) SELECT * FROM Table1 WHERE FieldName_Indexed
- WHERE절에 LIKE '%성' 같은 문장이나 LEFT(FieldName, 2) = '12' 같은 문장은 가급적 사용하지 않는다.
	- 이러한 경우 Index가 설정되어 있어도 전체 Table Scan을 실행하기 때문이다.
- 불필요한 문장을 사용하지 않는다.
	- BETWEEN
		- SELECT * FROM EMP WHERE SALARY BETWEEN 10 AND 20;
		- [권장] SELECT * FROM EMP WHERE SALARY >= 10 AND SALARY <= 20;
	- Sub Query 문
		- SELECT * FROM EMP WHERE DEPTNO IN (SELECT DEPTNO FROM DEPT);
		- [권장] SELECT * FROM EMP, DEPT WHERE EMP.DEPTNO = DEPT.DEPTNO;
	- 조건에 맞지 않는 VIEW
		- CREATE VIEW VIEW_EMP AS SELECT * FROM EMP WHERE SAL > 10000;
		- SELECT ENAME FROM VIEW_EMP WHERE DEPTNO = 20;
		- [권장] SELECT ENAME FROM EMP WHERE SAL > 10000 AND DEPTNO = 20;

### 인덱스 활용
### 실행 계획(Execution Plan)
### SQL 튜닝
### 성능 저하 원인 분석


## 9. 데이터 무결성과 보안
### 데이터 무결성
### 제약조건(Constraint)
### 사용자 및 권한 관리
### 백업과 복구(Backup & Recovery)
### 데이터 암호화

## 10. 운영
### 주요 운영 업무
- 유지보수
- 장애 대응과 모니터링
- 사용자 및 권한 관리
- 백업과 복구(Backup & Recovery)

### 데이터베이스 운영 프로세스
### 유지보수
- 성능 및 데이터 무결성과 보안
- System 및 User Database
- 저장 공간과 인덱스
- 로그
### 장애 대응
### 모니터링


## 11. 주요 Query
### Recursive Query for Oracle and ANSI
```sql
/*******************************
1. 재귀 쿼리를 위한 테이블 생성과 데이터 적재	
*******************************/
CREATE TABLE TB_CTG
	(SEQ_CTG INT NOT NULL
	, NM VARCHAR(32)
	, SEQ_CTG_PARENT INT
	, FLG_USE CHAR(1)
	, ORDER_DISPLAY SMALLINT);
	
INSERT INTO TB_CTG VALUES (1, 'Asia', NULL, 'Y', 1);
INSERT INTO TB_CTG VALUES (2, 'Europe', NULL, 'Y', 2);
INSERT INTO TB_CTG VALUES (3, 'America', NULL, 'Y', 3);
INSERT INTO TB_CTG VALUES (4, 'Korea', 1, 'Y', 1);
INSERT INTO TB_CTG VALUES (5, 'China', 1, 'Y', 2);
INSERT INTO TB_CTG VALUES (6, 'Japan', 1, 'Y', 3);
INSERT INTO TB_CTG VALUES (7, 'The UK', 2, 'Y', 1);
INSERT INTO TB_CTG VALUES (8, 'USA', 3, 'Y', 1);
INSERT INTO TB_CTG VALUES (9, 'Canada', 3, 'Y', 2);
INSERT INTO TB_CTG VALUES (10, 'Vietnam', 1, 'Y', 4);
INSERT INTO TB_CTG VALUES (11, 'Brazil', 3, 'Y', 3);

/*******************************
2-1. For Oracle
*******************************/
SELECT
	LEVEL
	, NM
	, SEQ_CTG_PARENT
	, SEQ_CTG
	, ORDER_DISPLAY
FROM
	TB_CTG
WHERE
	FLG_USE = 'Y'	
START WITH
	SEQ_CTG_PARENT IS NULL
CONNECT BY PRIOR
	SEQ_CTG = SEQ_CTG_PARENT
ORDER BY
	LEVEL, SEQ_CTG_PARENT, ORDER_DISPLAY;
-- 하기는 계층형으로 표시	
-- ORDER SIBLINGS BY SEQ_CTG, ORDER_DISPLAY ASC;

/*******************************
2-2. For ANSI(remove RECURSIVE at Oracle)
*******************************/
WITH RECURSIVE WITH_CTG (LVL, NM, SEQ_CTG, SEQ_CTG_PARENT, FLG_USE, ORDER_DISPLAY) AS
(
	-- 초기값(LVL = 1 AND SEQ_CTG_PARENT = NULL) 설정하는 서브 쿼리
	SELECT
		1, NM, SEQ_CTG, SEQ_CTG_PARENT, FLG_USE, ORDER_DISPLAY
	FROM
		TB_CTG
	WHERE
		-- 오라클의 START_WITH와 동일한 조건
		SEQ_CTG_PARENT IS NULL
	UNION ALL
	-- 초기값 이후를 정의한 Recursive 서브 쿼리(초기값의 SEQ_CTG의 값이 SEQ_CTG_PARENT와 같은 행을 찾아 LVL + 1)
	SELECT
		B.LVL + 1 AS LVL
		, A.NM
		, A.SEQ_CTG
		, A.SEQ_CTG_PARENT
		, A.FLG_USE
		, A.ORDER_DISPLAY
	FROM
		TB_CTG A, WITH_CTG B
	WHERE
		-- 오라클의 CONNECT BY PRIOR와 동일한 조건
		A.SEQ_CTG_PARENT = B.SEQ_CTG
)
SELECT
	LVL, NM, SEQ_CTG, SEQ_CTG_PARENT, ORDER_DISPLAY
FROM
	WITH_CTG
WHERE
	FLG_USE = 'Y'
ORDER BY
	LVL, SEQ_CTG_PARENT, ORDER_DISPLAY;
```


## 12. 주요 Command
### MariaDB
```sql
-- 데이터베이스 생성
CREATE DATABASE backoffice DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Connection Pool 최대 허용 수
SHOW VARIABLES LIKE '%max_connection%';
-- Connection Pool 최대 접속 수
SHOW STATUS LIKE 'Max_used_connections';
-- Connection Pool 현재 접속 수
SHOW STATUS LIKE 'Threads_connected';
```


## 13. 프로젝트
### 요구사항 분석
### 데이터 모델링
### 테이블 생성
### SQL 작성
### 데이터 입력
### 조회 및 분석
### 검증 및 개선