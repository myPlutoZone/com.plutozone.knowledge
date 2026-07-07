# com.plutozone.knowledge.database

<!--
## TODO
- 정리
- 다이어그램 for join by MarkDown
- 기존 문서들 추가
- NCS 20 페이지부터 재시작
-->

## Contents
1. [개요](#1-개요)
2. [설계](#2-설계)
3. [데이터 정규화](#3-데이터-정규화)
4. [SQL(Structure Query Language) 기본](#4-sqlstructure-query-language-기본)
5. [SQL(Structure Query Language) 확장](#5-sqlstructure-query-language-고급)
6. [데이터 조작 및 관리](#6-데이터-조작-및-관리)
7. [객체](#7-객체)
8. [성능(Performance)](#8-성능performance)
9. [데이터 무결성과 보안](#9-데이터-무결성과-보안)
10. [운영](#10-운영)
11. [주요 Query](#11-주요-query)
12. [주요 Command](#12-주요-command)
13. [프로젝트](#13-프로젝트)
14. Tips

## 1. 개요
> Data and Information vs. DB(DataBase) vs. DBMS(DataBase Management System) vs. RDBMS(Relational DataBase Management System)
### 데이터(Data)와 정보(Information)
### DB(DataBase)란?
### DBMS(DataBase Management System)
### 관계형 데이터베이스(RDBMS, Relational DataBase Management System)
- Oracle
- Microsoft SQL Server
- MySQL
- MariaDB
- PostgreSQL
- ...


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


## 4. SQL(Structure Query Language) 기본
> DDL(Data Definition Language) vs. DML(Data Manipulation Language) and DCL(Data Control Language)/TCL(Transaction Control Language)
### SQL 개요
- 구조적 질의 언어

### SQL 분류
#### DDL(Data Definition Language, 데이터 정의 언어) 
- 주요 대상
	- 스키마(Schema, DBMS의 특징과 구축 환경에 기반한 데이터 구조로 이루어진 하나의 데이터베이스)
	- 도메인(Domain, 개체 속성의 데이터 타입과 범위, 제약 조건 등을 설정한 정보 예: 주소를 varchar(256)로 정의)
	- 테이블(Table, 데이터 저장 공간)
	- 뷰(View, 1개 이상의 물리 테이블을 통해서 만드는 가상의 논리 테이블)
	- 인덱스(Index, 빠른 검색을 위한 데이터 구조)
	- ...
- 주요 유형
	- 생성(CREATE)
	- 변경(ALTER)
	- 삭제(DROP)
	- 비움(TRUNCATE, 로깅하지 않고 데이터 삭제)
	- ...
- 주요 제약 조건(Constraint)
	- PRIMARY KEY(PK, 테이블의 기본 키를 정의하며 기본적으로 NOT NULL, UNIQUE 제약 사항이 설정됨)
	- FOREIGN KEY(FK, 테이블에 외래 키를 정의하며 참조 대상을 테이블명(칼럼명) 형식으로 작성해야 하며 참조 무결성이 위배되는 상황 발생 시 옵션(CASCADE, NO ACTION, SET NULL, SET DEFAULT)으로 처리 가능)
	- UNIQUE(UI, 테이블에서 해당하는 열값은 유일해야 함을 의미하며 테이블에서 모든 값이 다르게 적재되어야 하는 열에 설정됨)
	- NOT NULL(NN, 테이블에서 해당하는 열의 값은 NULL 불가능하며 필수적으로 입력해야 하는 항목에 설정함)
	- CHECK(CK, 사용자가 직접 정의하는 제약 조건으로 발생 가능한 상황에 따라 여러 가지 조건을 설정 가능)

#### DML(Data Manipulation Language)
- 조회(SELECT), 추가(INSERT), 수정(UPDATE), 삭제(DELETE)
- 다중 테이블 조회(SELECT)
	- 조인(JOIN, 두 개의 테이블을 결합하여 데이터를 추출하는 기법)
	- 서브쿼리(Subquery, SQL문 안에 포함된 SQL문 형태의 사용 기법)
	- 집합(UNION, 연산자 테이블을 집합 개념으로 조작하는 기법)

#### DCL(Data Control Language)/TCL(Transcation Control Language)
- 대상과 오브젝트 유형(목적)
	- 사용자 권한(접근 통제, DBMS에 접근할 사용자를 등록하고 특정 사용자에게 데이터베이스의 사용 권한을 부여하는 작업)
	- 트랜잭션(안전하고 무결한 거래 보장, 동시 다발적으로 발생하는 다수 작업을 독립적이고 안전하게 처리하기 위한 데이터베이스 작업 단위)
- 유형에 따른 명령
	- DCL
		- GRANT: 데이터베이스 사용자 권한 부여
		- REVOKE: 데이터베이스 사용자 권한 회수
	- TCL
		- COMMIT: 트랜잭션 확정
		- ROLLBACK: 트랜잭션 취소
		- CHECKPOINT: 복귀지점 설정
- 권한 유형과 명령
	- 시스템
		- CREATE USER: 계정 생성
		- DROP USER: 계정 삭제
		- DROP ANY TABLE: 테이블 삭제
		- CREATE SESSION: 데이터베이스 접속
		- CREATE TABLE: 테이블 생성
		- CREATE VIEW: 뷰 생성
		- CREATE SEQUENCE: 시퀀스 생성
		- CREATE PROCEDURE: 함수 생성
		- ...
	- 객체
		- ALTER: 변경
		- INSERT: 추가
		- DELETE: 삭제
		- SELECT: 조회
		- UPDATE: 수정
		- EXECUTE PROCEDURE: 실행
		- ...

### 조회(SELECT)
### 조건(WHERE)
### 정렬(ORDER BY)
### 그룹(GROUP BY)
### 집계(HAVING)
### 구분(DISTINCT)


## 5. SQL(Structure Query Language) 고급
### JOINS 분류
- emp for Employee 테이블

| eid | name | did  |
| --- | ---- | ---- |
| 1   | Kim  | 1    |
| 2   | Lee  | 2    |
| 3   | Park | 3    |
| 4   | Choi | NULL |

- dpt for Department 테이블
| did | name  |
| --- | ----- |
| 1   | Sales |
| 2   | HR    |
| 4   | IT    |

|                              | Result |
| ---------------------------- | ------ |
| INNER JOIN                   | 모두 일치하는 데이터만 |
| LEFT OUTER JOIN(LEFT JOIN)   | 왼쪽 테이블 전체 + 일치하는 오른쪽 데이터 |
| RIGHT OUTER JOIN(RIGHT JOIN) | 오른쪽 테이블 전체 + 일치하는 왼쪽 데이터 |
| FULL OUTER JOIN              | 전체 데이터 |

- INNER JOIN: 모든에 존재하는 데이터만 필요한 경우(예: 주문과 고객 정보가 모두 있는 주문 조회)
- LEFT JOIN: 기준 테이블의 모든 데이터를 유지하면서 관련 정보가 있으면 함께 조회하는 경우(예: 모든 직원과 부서 정보 조회)
- RIGHT JOIN: 오른쪽 테이블을 기준으로 모든 데이터를 조회해야 하는 경우(실무에서는 LEFT JOIN으로 테이블 순서를 바꿔 작성하는 경우가 더 많음)
- FULL OUTER JOIN: 두 테이블 간 불일치 데이터까지 모두 확인해야 하는 경우(예: 데이터 정합성 검증, 누락 데이터 확인)

- 논리적 조인(사용자가 작성한 SQL문에 의해서 표현한 테이블 결합 방식)
	- 내부 조인(Inner Join, 일치하는 데이터만 조회)
	```sql
	SELECT e.emp_id,
       e.name,
       d.dept_name
FROM Employee e
INNER JOIN Department d
ON e.dept_id = d.dept_id;
	```
| emp_id | name | dept_name |
| ------ | ---- | --------- |
| 1      | Kim  | Sales     |
| 2      | Lee  | HR        |

- dept_id가 같은 행만 조회됩니다.
- Park(30)는 Department에 없으므로 제외됩니다.
- Choi(NULL)도 제외됩니다.

		- 동등 조인(Equi Join, 공통으로 있는 칼럼 값이 같은 경우에 레코드 추출)
		- 자연 조인(Natural Join, 두 테이블에 있는 동일한 칼럼명을 기준으로 모든 칼럼 값이 같은 경우에 레코드 추출)
		- 교차 조인(Cross Join, 조인 조건이 없는 모든 데이터의 조합을 추출)
		- 자체 조인(Self Join, 자신이 자신과 조인하며 1개의 테이블을 사용)
	- 외부 조인(Outer Join, 특정한 테이블의 모든 데이터를 기준으로 다른 테이블의 정보와 비교하여 추출(단, 다른 테이블에 동일한 값이 없어도 특정한 테이블은 출력됨)
		- 왼쪽 외부 조인(Left Outer Join, 왼쪽 테이블의 모든 데이터를 조회 + 일치하는 데이터가 없으면 오른쪽 컬럼은 NULL)
		```sql
		SELECT e.emp_id,
       e.name,
       d.dept_name
FROM Employee e
LEFT JOIN Department d
-- LEFT OUTER JOIN Department d
ON e.dept_id = d.dept_id;
		```
| emp_id | name | dept_name |
| ------ | ---- | --------- |
| 1      | Kim  | Sales     |
| 2      | Lee  | HR        |
| 3      | Park | NULL      |
| 4      | Choi | NULL      |
- Employee의 모든 데이터가 출력됩니다.
- Department가 없으면 NULL이 출력됩니다.
		- 오른쪽 외부 조인(Right Outer Join, 오른쪽 테이블의 모든 데이터를 조회)
		```sql
		SELECT e.emp_id,
       e.name,
       d.dept_name
FROM Employee e
RIGHT OUTER JOIN Department d
ON e.dept_id = d.dept_id;
		```
| emp_id | name | dept_name |
| ------ | ---- | --------- |
| 1      | Kim  | Sales     |
| 2      | Lee  | HR        |
| NULL   | NULL | IT        |
- Department의 모든 데이터가 출력됩니다.
- IT 부서는 직원이 없으므로 Employee 컬럼은 NULL입니다.
		- 완전 외부 조인(Full Outer Join, 양쪽 테이블의 모든 데이터를 조회 + 일치하지 않는 데이터는 NULL로 표시)
		```sql
		SELECT e.emp_id,
       e.name,
       d.dept_name
FROM Employee e
FULL OUTER JOIN Department d
ON e.dept_id = d.dept_id;
		```
| emp_id | name | dept_name |
| ------ | ---- | --------- |
| 1      | Kim  | Sales     |
| 2      | Lee  | HR        |
| 3      | Park | NULL      |
| 4      | Choi | NULL      |
| NULL   | NULL | IT        |
- Employee의 모든 행
- Department의 모든 행
- 둘 다 포함됩니다

- FULL OUTER JOIN은 데이터베이스마다 지원 여부가 다릅니다. 예를 들어 PostgreSQL과 SQL Server는 지원하지만, MySQL은 직접 지원하지 않아 LEFT JOIN과 RIGHT JOIN 결과를 UNION으로 결합하는 방식 등을 사용합니다.

- 물리적 조인: 데이터베이스 관리시스템(DBMS)의 옵티마이저 엔진에 의해 발생하는 테이블 결합 방식
	- Nested Loop Join
	- Merge Join
	- Hash Join

### Subquery
- 동작하는 방식에 따른 서브쿼리의 종류
	- 비연관(Un-Correlated) 서브쿼리: 서브쿼리 안에 메인쿼리의 칼럼 정보를 가지고 있지 않은 형태(서브쿼리는 메인쿼리 없이 독자적으로 실행)
	- 연관(Correlated) 서브쿼리: 서브쿼리 안에 메인쿼리의 칼럼 정보를 가지고 있는 형태(메인쿼리의 실행된 결과를 통해서 서브쿼리의 조건이 맞는지 확인)
- 반환되는 데이터 형태에 따른 서브쿼리의 종류
	- Single Row(단일 행) 서브쿼리: 서브쿼리의 결과가 항상 1건 이하인 서브쿼리로 단일 행의 비교 연산자에는 =, <, <=, >, >=, <> 등이 사용
	- Multiple Row(다중 행) 서브쿼리: 서브쿼리 실행 결과가 여러 건인 서브쿼리로 다중 행의 비교 연산자에는 IN, EXISTS, ALL, ANY 등이 사용
	- Multiple Column(다중 칼럼) 서브쿼리: 서브쿼리의 실행 결과가 2개 이상 칼럼으로 반환되는 쿼리로 메인쿼리의 조건절에 다수 칼럼을 비교할 때 서브쿼리와메인쿼리에서 비교하는 칼럼 개수, 위치가 동일해야 함

### UNION
- 집합 연산자 유형
	- UNION: 2개 이상 SQL문의 실행 결과에 대한 중복을 제거한 합집합
	- UNION ALL: 2개 이상 SQL문의 실행 결과에 대한 중복을 제거하지 않은 합집합
	- INTERSECTION: 2개 이상 SQL문의 실행 결과에 대한 중복을 제거한 교집합
	- EXCEPT(MINUS): 선행 SQL문의 실행 결과와 후행 SQL문의 실행 결과 사이의 중복을 제거한 차집합(일부 DBMS는 MINUS로 사용)

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
### 제약 조건(Constraint)
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


## 14. Tips
### PostgreSQL
	- 설치 시
		- Stack Builder: Disable
		- Locale: Korean, Korea
	- 설치 확인
	```cmd
	C:\> netstat -ano | findstr 5432
	```
	- DB 생성
	```cmd
	C:\> cd C:\Program Files\PostgreSQL\18\bin
	C:\Program Files\PostgreSQL\18\bin> psql -U postgres
	...
	postgres=# \l							# DB 목록 조회
	postgres=# CREATE DATABASE "PlutoZone";	# DB 생성
	postgres=# \l
	```
	- Connect by DBeaver
	- ...