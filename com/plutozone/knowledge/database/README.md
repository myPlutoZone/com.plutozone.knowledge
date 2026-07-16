# com.plutozone.knowledge.database


## Contents
01. [개요](#1-개요)
02. [설계](#2-설계)
03. [데이터 정규화](#3-데이터-정규화)
04. [SQL(Structure Query Language) 기본](#4-sqlstructure-query-language-기본)
05. [SQL(Structure Query Language) 확장](#5-sqlstructure-query-language-고급)
06. [데이터 조작 및 관리](#6-데이터-조작-및-관리)
07. [객체](#7-객체)
08. [성능(Performance)](#8-성능performance)
09. [데이터 무결성과 보안](#9-데이터-무결성과-보안)
10. [운영](#10-운영)
11. [주요 Query](#11-주요-query)
12. [주요 Command](#12-주요-command)
13. [프로젝트](#13-프로젝트)


## 1. 개요
> Data and Information vs. DB(DataBase) vs. DBMS(DataBase Management System) vs. RDBMS(Relational DataBase Management System)

> Database for Programmer vs. DBA(Database Administrator) 

### 1-1. 데이터(Data)와 정보(Information)
```mermaid
flowchart LR
subgraph D[" "]
	A1["Data 1"]
	A2["Data 2"]
	A3["Data ..."]
end

DB[(Database)]

subgraph I[" "]
	I1["Information"]
end

D -- "체계적 저장" --> DB
DB <-- "효율적인 검색/처리" --> I

style D fill:#ffffff,stroke:#ffffff
style I fill:#ffffff,stroke:#ffffff
```

### 1-2. DB(DataBase)란?
데이터베이스는 체계(구조와 형식)적으로 저장된 자료(Data)의 집합체이며 이들 자료(Data)들은 효율적인 검색/처리가 가능하다. 그러므로 체계적으로 저장되지 못한 자료(Data)는 데이터베이스라고 할 수 없으며 정보(Information)로써의 가치가 전혀 없다. 정보의 정확성과 신속성이 요구되는 현대 사회에서 자료를 체계적으로 관리/이용하기 위한 데이터베이스의 중요성은 더해 가고 있다.

예: 단순 형식으로 저장된 자료의 집합체: *.csv(Comma Separated Value)

- Database의 이점
	- Data의 중복 통제와 일관성 유지
	- Data 취득 용이와 응답 시간의 단축
	- 표준화의 용이성
	- Programmer의 생산성 향상
	- 일관성 있는 Data 관리


### 1-3. DBMS(DataBase Management System)
DBMS(데이터베이스 관리 시스템)는 Database의 구성, Access 방법, 관리 유지에 대한 모든 Role을 지고 있는 Software System

- 개요
- 특징(무결성, 독립성, 보안, 중복 최소화, 안정성 등)
- 구조/형식에 따른 분류(관계형, 객체 지향형, 계층형, 네트워크형, 망형 등)

### 1-4. 관계형 데이터베이스(RDBMS, Relational DataBase Management System)
관계형 데이터베이스는 ...
또한 데이터베이스에 포함되는 이러한 테이블이 서로 어떤 관련이 있는가를 Relationship이라고 한다. 또한 RDB(*.csv, *.xls, *.mdb, RDBMS) 계열은 상호 변환이 용이하다.

```mermaid
flowchart LR
	A["매출 전표 TABLE<br/>────────────<br/>전표번호 | 년월 | 거래처코드"]
	B["거래처 TABLE<br/>────────────<br/>거래처코드 | 거래처명"]
	C["주문 TABLE<br/>────────────<br/>주문번호 | 전표번호 | 상품코드 | 수량"]
	D["상품 TABLE<br/>────────────<br/>상품코드 | 상품명 | 단가"]

	B -. 거래처코드 .-> A
	A -. 전표번호 .-> C
	D -. 상품코드 .-> C
```
```mermaid
erDiagram
	매출전표 {
		int 전표번호 PK
		string 년월
		string 거래처코드 FK
	}

	거래처 {
		string 거래처코드 PK
		string 거래처명
	}

	주문 {
		int 주문번호 PK
		int 전표번호 FK
		string 상품코드 FK
		int 수량
	}

	상품 {
		string 상품코드 PK
		string 상품명
		int 단가
	}

	거래처 ||--o{ 매출전표 : "거래처코드"
	매출전표 ||--o{ 주문 : "전표번호"
	상품 ||--o{ 주문 : "상품코드"
```

- DBF, Clipper, Foxplus
- Oracle
	- 역사
	- 에디션(Personal, Standard One, Standard, Enterprise 그리고 [Express](./oracle.md))
	- 설치 등
	- Client Tool
		- SQL Developer
			- GUI 기반 Client Tool at Oracle
			- Data Modeler 등
		- SQL*Plus
			- Text 기반 Client Tool at Oracle
		- Oracle Application Express
			-  Web 기반 Client Tool at Oracle
- Microsoft Access, Microsoft SQL Server
- MySQL
- MariaDB
- PostgreSQL
- IBM DB2
- ...

### 1-5. 데이터베이스 관련 업무 at 프로젝트
#### 정보 시스템 구축 그리고 모델링
- 구축 절차(요구사항 분석, 설계, 구현, 검증 그리고 유지보수)
- 데이터 모델링과 용어(데이터와 데이터베이스, 테이블과 스키마, DMBS, 열, 데이터 유형, 기본키와 외래키, SQL 등)

#### DB 설치 및 구축
- 스키마(Table Space, User 등) 생성
- Table 생성 및 데이터 입력, 조회 등

#### DB 성능과 모니터링
- Index(속도 등), View(가상과 보안 등), Stored Procedure(프로그래밍 등), Trigger(주의)
- Execution Plan

#### 백업과 복원
- exp.exe 그리고 imp.exe at Oracle

#### 응용 프로그램과 연동
데이터베이스 프로그래밍이란 데이터베이스에 접속하여 레코드 셋을 취득하거나 레코드의 추가/갱신/삭제 작업을 하는 프로그래밍이다.
- Java(Spring), .NET, PHP(Laravel) 등
```mermaid
flowchart TD
	A[DB 개체 생성]
	B[DB 접속]
	C["Recordset<br/>개체 생성 및 취득"]
	D["표시, 편집, 삭제, 찾기 등"]
	E[모든 개체 해제]

	A --> B
	B --> C
	C --> D
	D --> E
```


## 2. 설계
### 2-1. 데이터 모델링
#### 소프트웨어 개발 방법론
- Waterfall(폭포수), 애자일(Agile) 등

#### 데이터 모델링이란?
- 데이터 모델링은 프로젝트 목표를 데이터베이스 개체로 표현한 것
- 데이터 모델링은 일반적으로 프로젝트 경험이 많고 관련 지식이 많은 PM이 담당
- 데이터 모델링은 개념적, 논리적 그리고 물리적 모델링 단계를 거친다.
- 데이터 모델링과 정규화, 관계, 기본키, 외래키, 컬럼, 데이터 형식, 필수 여부 등

#### 데이터 모델링 툴(Tool)
- Data Modeler at Oracle SQL Developer
- ER Modeler at MySQL Workbench
- ERwin, exERD 등

#### 데이터 모델링 절차
- 업무 분석
- 개념적 DB 모델링 with ER Diagram
	- Entity
	- Attribute
	- Identity
	- Relation
	- Dimension
- 논리적 DB 모델링 with Case Tool
	- ER Diagram
	- Mapping Rule
	- Normalization
	- Data Type and Size
- 물리적 DB 모델링 with Case Tool
	- DBMS
	- Data Type and Size
	- 데이터 사용 및 업무 프로세스 분석
	- Denormalization
	- Index
	- Constraint
	- Stored Procedure, Function, View, Trigger, ...
	- Create DB

### 2-2. ERD(Entity Relationship Diagram)
### 2-3. 엔터티(Entity)와 속성(Attribute)
### 2-4. 관계(Relationship)
- 3가지 형태의 Relationship(관계)
	- 一 對 多: 한 쪽 테이블의 레코드 하나가 또 다른 쪽 테이블의 여러 레코드와 대응하는 릴레이션십으로 데이터를 효율적으로 축적/추출할 수 있어 가장 많이 사용된다.
	- 一 對 一: 구조는 간단하나 근본적으로 하나의 테이블과 같다.
	- 多 對 多: 구조가 복합하며 두 테이블 사이에 새로운 테이블을 설계하는 것에 의해 두 개의 一對多 또는 多對一의 릴레이션십으로 교체할 수 있다.

### 2-5. 기본키(Primary Key)와 외래키(Foreign Key)
기본 키와 외부 키는 릴레이션십을 작성할 때 사용하는 필드이다. 기본키는 등록한 데이터를 식별하기 위해 사용되므로 반드시 입력되어야 하며 자동 인덱스되며 중복될 수 없다. 외부키는 다른 테이블의 기본키와 일치하는 값을 갖고 있으며 반드시 입력되어야 한다. 기본키와 외부키를 설정할 때 필드명은 동일할 필요는 없으나 데이터형은 동일하게 할 필요가 있다.
- Primary Key: 매출 전표 TABLE의 전표번호 필드, 상품 TABLE의 상품코드 필드
- Foreign Key: 주문 TABLE의 전표번호 필드 등

### 2-6. 데이터 타입(Data Type)
- 명시적 변환(Explicit Conversion) vs. 암시적 변환(Implicit Conversion)

#### ANSI
| Oracle     | ANSI     | 범위(크기)      | Range |
|------------|----------|----------------|-------|
| NUMBER(3)  | tinyint  | 2^8  (1 바이트) | -2^7(-128) ~ 2^7-1(127) 또는 0 ~ 255 |
| NUMBER(5)  | smallint | 2^16 (2 바이트) | -2^15(-32,768) ~ 2^15-1(32,767) 또는 0 ~ 65,535 |
| NUMBER(10) | int      | 2^32 (4 바이트) | -2^31(-2,147,483,648) ~ 2^31-1(2,147,483,647) 또는 0 ~ 4,294,967,295 |
| NUMBER(19) | bigint   | 2^64 (8 바이트) | -2^63 ~ 2^63-1 |

#### Oracle
- NUMBER
- CHAR, VARCHAR2, NCHAR, NVARCHAR2, CLOB, NCLOB
- BLOB
- DATE

### 2-7. 계획과 테이블 설계
```mermaid
flowchart TD
    A(1. 정보 수집) --> B(2. 개체 확인)
    B --> C(3. 개체 모델링)
    C --> D(4. 개체 유형 확인)
    D --> E(5. 개체 관계 확인)

    A -.- A1("DB에서 수행할 작업에 대한 이해를 필요로 한다.")
    B -.- B1("DB가 관리할 유형·무형 항목의 주요 Entity를 확인하고,<br/>각 Entity에 필요한 개체를 확인한다.")
    C -.- C1("DB 디자이너와 같은 시각적 디자인 도구를 이용하여<br/>시스템에서 실제로 표현될 개체를 모델링한다.")
    D -.- D1("각 개체 테이블 컬럼에 저장할 정보의 유형을 확인한다.")
    E -.- E1("DB의 여러 항목 정보 간 연관관계를 확인하고,<br/>관련 컬럼을 추가한다.")
```

```mermaid
flowchart TD

%% =========================
%% Table 설계
%% =========================
subgraph S1["Table 설계"]

direction LR

N("Normalization<br/>(1:N, N:1)")

P1["1. 저장하는 데 필요한 Data의 모든 Table/Field를 목록화"]
P2["2. 업무별로 Table을 그룹화"]

N -.- P1
P1 --> P2

end

%% =========================
%% Field 설정
%% =========================
subgraph S2["Field 설정"]

direction LR

O("그 외 Unique, Index, Size, NULL, Default 등")

P3["3. 각 Table에 존재하는<br/>PK와 FK 확인"]
P4["4. 각 Table Field의 속성(Name, Data Type) 지정"]

O -.- P4
P3 --> P4

end

%% =========================
%% RELATION 설정
%% =========================
subgraph S3["Relation 설정"]

direction TB

P5["5. 공통된 Field를 통하여 Table들을 1:N, N:1 관계(Relation)로 연결"]
P6["6. View, Stored Procedure 설계 등"]

P5 --> P6

end

P2 --> P3
P4 --> P5
```
## 3. 데이터 정규화
정규화는 테이블의 정규화라고 볼 수 있으며 이는 데이터를 보호하고 정규화 규칙을 사용하여 테이블 사이의 관계를 정립하는 것이다. 논리적 DB 디자인을 정규화하는 것은 형식적인 방법을 사용하여 데이터를 여러 개의 관련된 테이블(1:多 또는 多:1)로 저장하는 것이다.

### 3-1. 정규화의 필요성
#### 장점(성능 향상)
- 보다 빠른 정렬과 인덱스 작성 가능- SELECT문 향상
- INSERT, UPDATE, DELETE문 향상
- NULL 값이 줄어들고, 불일치 가능성(참조 정합성) 감소

#### 단점
- 정규화가 많이 될수록 데이터를 검색하는데 필요한 JOIN의 복잡화로 인해 성능 문제 발생

#### 참조 정합성이란
릴레이션십을 설정한 테이블의 데이터 간에 관련성을 명확히 하기 위한 규칙으로 다음과 같다.
- 기본 키에 등록되어 있지 않은 데이터는 외부 키에 등록할 수 없다.
- 외부 키에 데이터가 등록되어 있는 경우에는 기본키의 데이터를 삭제할 수 없다.
- 외부 키에 데이터가 등록되어 있는 경우에는 기본키의 데이터를 변경할 수 없다.


### 3-2. 정규화 규칙
#### 테이블 식별자(PK) 지정
테이블의 다른 모든 Row 데이터로부터 하나의 Row 데이터만 구별할 수 있는 컬럼이 있어야 한다.

#### 테이블에는 한 유형의 Entity만 존재
테이블에 너무 많은 정보를 저장하면 데이터를 효율적으로 관리하기 어렵다.

#### 테이블 컬럼에 Null값 허용 자제
Null값을 허용하는 것이 유용할 때도 있지만 복잡한 데이터 작업을 요하는 특수 처리가 필요하므로 가급적 사용을 피하는 것이 좋다.

#### 테이블의 반복된 값이나 컬럼 제외
데이터베이스의 테이블에서 특정 정보에 대해 여러 값을 지정하면 안 된다.

### 3-3. 정규화 유형
#### 제1정규형(1NF)
#### 제2정규형(2NF)
#### 제3정규형(3NF)
#### 반정규화


## 4. SQL(Structure Query Language) 기본
> DDL(Data Definition Language) vs. DML(Data Manipulation Language) and DCL(Data Control Language)/TCL(Transaction Control Language)

### 4-1. SQL 개요
- SQL(구조적 질의 언어)이란?
- 특징
- ANSI SQL vs. PL/SQL vs. T-SQL

### 4-2. SQL 분류
#### DDL(Data Definition Language, 데이터 정의 언어) 
- 주요 대상
	- 스키마(Schema, DBMS의 특징과 구축 환경에 기반한 데이터베이스의 구조, 제약 조건, 개체, 속성, 관계 및 데이터 조작에 대한 총칭)
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
- [주요 제약 조건(Constraint)](#9-2-제약-조건constraint)

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

### 4-3. 조회(SELECT)
- FROM과 owner, column, alias
- CREATE TABLE … AS SELECT

### 4-4. 조건(WHERE)
- WHERE과 operator 그리고 BETWEEN, IN/NOT IN, LIKE, ANY, ALL 및 Sub Query

### 4-5. 정렬(ORDER BY)
- ORDER BY
- ROWNUM, SAMPLE

### 4-6. 그룹(GROUP BY) and 집계(HAVING) and 구분(DISTINCT)
- GROUP BY, HAVING 그리고 Aggregate Function
- DISTINCT

### 4-7. 기타
- ROLLUP(), GROUPING_ID(), CUBE()
- WITH와 CTE


## 5. SQL(Structure Query Language) 고급
### 5-1. JOIN 분류
```mermaid
flowchart TD
JOIN[JOIN]

JOIN --> INNER[INNER JOIN]
JOIN --> SELF[SELF JOIN]
JOIN --> CROSS[CROSS JOIN]
JOIN --> OUTER[OUTER JOIN]

INNER --> EQUI["EQUI JOIN<br>(=)"]
INNER --> THETA["THETA JOIN<br>(>, <, >=, <=, !=)"]

OUTER --> LEFT[LEFT OUTER JOIN]
OUTER --> RIGHT[RIGHT OUTER JOIN]
OUTER --> FULL[FULL OUTER JOIN]
```

<details>
	<summary>테이블 정보</summary>

- EMP 테이블 for Employee
	| IDX_EMP | NAME | IDX_DPT | IDX_MNG |
	| ------- | ---- | ------- | ------- |
	| 1       | Kim  | 1       | NULL    |
	| 2       | Lee  | 2       | 1       |
	| 3       | Park | 3       | 1       |
	| 4       | Choi | NULL    | 2       |

- DPT 테이블 for Department
	| IDX_DPT | NAME  |
	| ------- | ----- |
	| 1       | Sales |
	| 2       | HR    |
	| 4       | IT    |

- DDL for PostgreSQL
	```sql
	CREATE TABLE EMP (
		IDX_EMP int NOT NULL, -- 사원 일련번호
		NAME varchar(32) NOT NULL, -- 성명
		IDX_DPT int NULL, -- 부서 일련번호
		IDX_MNG int NULL, -- 직속 관리자 일련번호
		CONSTRAINT PK_EMP PRIMARY KEY (IDX_EMP)
	);
	COMMENT ON COLUMN EMP.IDX_EMP IS '사원 일련번호';
	COMMENT ON COLUMN EMP.NAME IS '성명';
	COMMENT ON COLUMN EMP.IDX_DPT IS '부서 일련번호';
	COMMENT ON COLUMN EMP.IDX_MNG IS '직속 관리자 일련번호';

	CREATE TABLE dpt (
		IDX_DPT int NOT NULL, -- 부서 일련번호
		NAME varchar(32) NULL, -- 부서명
		CONSTRAINT PK_DPT PRIMARY KEY (IDX_DPT)
	);
	COMMENT ON COLUMN DPT.IDX_DPT IS '부서 일련번호';
	COMMENT ON COLUMN DPT.NAME IS '부서명';
	```
- DML for PostgreSQL
	```sql
	INSERT INTO DPT (IDX_DPT, NAME) VALUES
	 (1,'Sales'),
	 (2,'HR'),
	 (4,'IT');

	INSERT INTO EMP (IDX_EMP, NAME, IDX_DPT, IDX_MNG) VALUES
	 (1,'Kim',1,NULL),
	 (2,'Lee',2,1),
	 (3,'Park',3,1),
	 (4,'Choi',NULL,2);
	```
</details>

#### 논리적 조인(JOIN)
> 사용자가 작성한 SQL문에 의해서 표현한 테이블 결합 방식
- **내부 조인(`INNER JOIN,` 일치하는 데이터를 기준으로 조회)**
	- 동등 조인(EQUI JOIN): 모두에 존재하는 데이터만 필요한 경우(예: 고객과 주문 정보가 모두 있는 고객의 주문 조회)
		```sql
		SELECT e.IDX_EMP, e.NAME, d.NAME
		FROM EMP e
		INNER JOIN DPT d
		ON e.IDX_DPT = d.IDX_DPT;
		/* = SELECT e.IDX_EMP, e.NAME, d.NAME FROM EMP e, DPT d WHERE e.IDX_DPT = d.IDX_DPT; */
		/* , for INNER, (+) for OUTER */
		```
		| IDX_EMP    | NAME | NAME      |
		| ------ | ---- | --------- |
		| 1      | Kim  | Sales     |
		| 2      | Lee  | HR        |
		- IDX_DPT가 같은 행(ON e.IDX_DPT = d.IDX_DPT)만 조회됨
		- Park(30)는 Department에 없으므로 제외 + Choi(NULL)도 제외
		```mermaid
		flowchart LR
		subgraph A[Table A]
		A1(( ))
		end

		subgraph B[Table B]
		B1(( ))
		end

		I((A ∩ B))

		A1 --- I
		I --- B1

		style I fill:#66cc66,color:#000
		```
	- 자연 조인(NATURAL JOIN): 이름이 같은 컬럼을 자동으로 찾아 조인(주의: 의도하지 않은 결과가 발생할 수 있어 실무에서는 미권장)
		```sql
		SELECT *
		FROM EMP
		NATURAL JOIN DPT;
		/* = SELECT * FROM EMP e JOIN DPT d ON e.IDX_DPT = d.IDX_DPT; */
		```
- **자체 조인(SELF JOIN): 자신이 자신과 조인하며 1개의 테이블을 사용(예: 조직도/직원-관리자, 댓글, 카테고리 등 계층 구조)**
	```sql
	SELECT
		e.name AS employee,
		m.name AS manager
	FROM EMP e
	JOIN EMP m
	-- LEFT JOIN EMP m
		ON e.IDX_MNG = m.IDX_EMP;
	```
	```mermaid
	flowchart LR
	T1[Employee AS e1]
	T2[Employee AS e2]

	T1 -->|manager_id = id| T2
	```
- **교차 조인(CROSS JOIN): 조인 조건이 없는 모든 데이터의 조합을 추출**
	```sql
	SELECT *
	FROM EMP e 
	CROSS JOIN DPT d ;
	/* = SELECT * FROM EMP, DPT; */
	```
	```mermaid
	flowchart TD
		A[Table A<br/>3 rows]
		B[Table B<br/>4 rows]

		C["Result<br/>3 × 4 = 12 rows"]

		A --> C
		B --> C
	```	
- **외부 조인(`OUTER JOIN`, 일치하지 않는 데이터도 포함하여 조회)**
	- 왼쪽 외부 조인(`LEFT OUTER JOIN`=LEFT JOIN): 왼쪽 테이블의 모든 데이터를 유지하면서 관련 정보가 있으면 함께 조회하는 경우(예: 모든 부서에 따른 직원 정보 조회)
		```sql
		SELECT e.IDX_EMP, e.NAME, d.NAME
		FROM EMP e
		LEFT OUTER JOIN DPT d
		-- LEFT JOIN DPT d
		ON e.IDX_DPT = d.IDX_DPT;
		```
		| IDX_EMP | NAME | NAME |
		| --- | ---- | --------- |
		| 1   | Kim  | Sales     |
		| 2   | Lee  | HR        |
		| 3   | Park | NULL      |
		| 4   | Choi | NULL      |
		- Employee의 모든 데이터가 출력
		- Department가 없으면 NULL이 출력
		```mermaid
		flowchart LR
		A((Table A))
		J((A ∩ B))
		B((Table B))

		A --- J --- B

		style A fill:#66cc66,color:#000
		style J fill:#66cc66,color:#000
		```
	- 오른쪽 외부 조인(`RIGHT OUTER JOIN`=RIGHT JOIN): 오른쪽 테이블을 기준으로 모든 데이터를 조회해야 하는 경우(실무에서는 대부분 LEFT JOIN으로 테이블 순서를 변경하여 확인
		```sql
		SELECT e.IDX_EMP, e.NAME, d.NAME
		FROM EMP e
		RIGHT OUTER JOIN DPT d
		-- RIGHT JOIN DPT d
		ON e.IDX_DPT = d.IDX_DPT;
		```
		| IDX_EMP  | NAME | NAME |
		| ---- | ---- | --------- |
		| 1    | Kim  | Sales     |
		| 2    | Lee  | HR        |
		| NULL | NULL | IT        |
		- Department의 모든 데이터가 출력
		- IT 부서는 직원이 없으므로 Employee 컬럼은 NULL
		```mermaid
		flowchart LR
		A((Table A))
		J((A ∩ B))
		B((Table B))

		A --- J --- B

		style J fill:#66cc66,color:#000
		style B fill:#66cc66,color:#000
		```
	- 완전 외부 조인(`FULL OUTER JOIN`): 전체 데이터=두 테이블 간 불일치 데이터까지 모두 확인해야 하는 경우(예: 데이터 정합성 검증, 누락 데이터 확인)
		```sql
		SELECT e.IDX_EMP, e.NAME, d.NAME
		FROM EMP e
		FULL OUTER JOIN DPT d
		ON e.IDX_DPT = d.IDX_DPT;
		```
		| IDX_EMP  | NAME | NAME |
		| ---- | ---- | --------- |
		| 1    | Kim  | Sales     |
		| 2    | Lee  | HR        |
		| 3    | Park | NULL      |
		| 4    | Choi | NULL      |
		| NULL | NULL | IT        |
		- Employee의 모든 행 + Department의 모든 행
		- FULL OUTER JOIN 지원 여부 확인(PostgreSQL과 SQL Server는 지원하지만 MySQL은 직접 지원하지 않아 LEFT JOIN과 RIGHT JOIN 결과를 UNION으로 결합하는 방식 등을 사용)
		```mermaid
		flowchart LR
		A((Table A))
		J((A ∩ B))
		B((Table B))

		A --- J --- B

		style A fill:#66cc66,color:#000
		style J fill:#66cc66,color:#000
		style B fill:#66cc66,color:#000
		```
#### 물리적 조인(JOIN)
> 데이터베이스 관리시스템의 옵티마이저 엔진에 의해 발생하는 테이블 결합 방식
- Nested Loop Join
- Merge Join
- Hash Join

### 5-2. 조인(JOIN) 문법
| 구분 | INNER JOIN | 쉼표(,) + WHERE |
|------|------------|-----------------|
| 문법 | ANSI 표준 | 구식 문법 |
| 결과 | 동일 | 동일 |
| 조인 조건 | `ON` 절 | `WHERE` 절 |
| 가독성 | 높음 | 낮음 |
| 유지 보수 | 쉬움 | 어려움 |
| 권장 여부 | 권장 | 기존 코드에서 주로 사용 |
<details>
	<summary>예제</summary>

```sql
SELECT
	e.IDX_EMP
	, e.NAME
	, d.NAME
FROM
	EMP e INNER JOIN DPT d ON e.IDX_DPT = d.IDX_DPT
WHERE
	1 = 1;

SELECT
	e.IDX_EMP
	, e.NAME
	, d.NAME
FROM
	EMP e
	, DPT d
WHERE
	e.IDX_DPT = d.IDX_DPT;
```
</details>

### 5-3. Subquery
- 동작하는 방식에 따른 서브쿼리의 종류
	- 비연관(Un-Correlated) 서브쿼리: 서브쿼리 안에 메인쿼리의 칼럼 정보를 가지고 있지 않은 형태(서브쿼리는 메인쿼리 없이 독자적으로 실행)
	- 연관(Correlated) 서브쿼리: 서브쿼리 안에 메인쿼리의 칼럼 정보를 가지고 있는 형태(메인쿼리의 실행된 결과를 통해서 서브쿼리의 조건이 맞는지 확인)
- 반환되는 데이터 형태에 따른 서브쿼리의 종류
	- Single Row(단일 행) 서브쿼리: 서브쿼리의 결과가 항상 1건 이하인 서브쿼리로 단일 행의 비교 연산자에는 =, <, <=, >, >=, <> 등이 사용
	- Multiple Row(다중 행) 서브쿼리: 서브쿼리 실행 결과가 여러 건인 서브쿼리로 다중 행의 비교 연산자에는 IN, EXISTS, ALL, ANY 등이 사용
	- Multiple Column(다중 칼럼) 서브쿼리: 서브쿼리의 실행 결과가 2개 이상 칼럼으로 반환되는 쿼리로 메인쿼리의 조건절에 다수 칼럼을 비교할 때 서브쿼리와메인쿼리에서 비교하는 칼럼 개수, 위치가 동일해야 함

### 5-4. UNION
- 집합 연산자 유형
	- UNION: 2개 이상 SQL문의 실행 결과에 대한 중복을 제거한 합집합
	- UNION ALL: 2개 이상 SQL문의 실행 결과에 대한 중복을 제거하지 않은 합집합
	- INTERSECTION: 2개 이상 SQL문의 실행 결과에 대한 중복을 제거한 교집합
	- EXCEPT(MINUS): 선행 SQL문의 실행 결과와 후행 SQL문의 실행 결과 사이의 중복을 제거한 차집합(일부 DBMS는 MINUS로 사용)


## 6. 데이터 조작 및 관리
### 6-1. INSERT and UPDATE and DELETE 그리고 MERGE
- SEQUENCE, DUAL, MERGE 등 포함
- DELETE(TR Logging) vs. TRUNCATE(TR Not Logging) vs. DROP(Table delete)

### 6-2. 트랜잭션(Transaction)
### 6-3. COMMIT vs. ROLLBACK


## 7. 객체
### 7-1. Programming
#### PL/SQL at Oracle
- Oracle에서 제공하는 SQL Programming(Stored Procedure, Function, Trigger에도 적용 가능)
- DECLARE … BEGIN … END
- Control Statement: IF, CASE
- Loop Statement: WHILE LOOP, FOR LOOP, CONTINUE, EXIT, GOTO
- DBMS_LOCK.SLEEP()
- EXCEPTION
- Dynamic SQL

### 7-2. (Built-in) Function
- 명시적 변환(Explicit Conversion) vs. 암시적 변환(Implicit Conversion)
#### 문자 및 문자열
#### 숫자
#### 수학
#### 날짜 및 시간
#### 변환
- Oracle
	- CAST(), TO_CHAR, TO_NUMBER(), TO_DATE()

#### 분석 및 순위
#### 피벗 함수
####  ...

### 7-3. Table
- GUI vs. SQL
- Constraint: PK, FK, UNIQUE(NULL allow), CHECK, DEFAULT, NULL 그리고 CK(Composite Key)
- Temporary Table과 DROP, ALTER

### 7-4. View
- 장점
	- 논리적 독립성 제공(논리 테이블이므로 물리 테이블의 구조가 변경되더라도 뷰를 활용하는 응용프로그램도 항상 수정할 필요는 없음)
	- 사용자 데이터 관리 용이(쿼리 단순화=다수 테이블에 있는 다양한 데이터에 대해 단순한 질의어 활용 가능)
	- 데이터 보안(중요한 보안 데이터가 있는 테이블은 접근하지 못하도록 설정하고 접근이 가능한 일부 데이터만을 조회할 수 있도록 뷰를 생성)
- 단점
	- 뷰 인덱스 사용 불가(논리적 생성한 뷰에는 인덱스를 만들 수 없음)
	- 뷰 구조 변경 불가(뷰는 삭제 후 재생성을 통해서 뷰의 구조 변경이 가능함)
	- 데이터 변경 제약 존재(뷰로 조회된 데이터에 대한 삽입, 변경, 삭제 제약이 있음)
- 참고
	- 읽기 전용 설정과 Material View at Oracle

### 7-5. Index
인덱스를 시스템에서 재구축하는 시간과 Load로 인하여 인덱스의 장점인 고속 검색에 반하여 레코드의 증감이나 데이터의 갱신이 많은 테이블에서는 인덱스의 작성을 신중하게 할 필요가 있다.

- 장점과 단점
- Index vs. Unique 그리고 PK 차이점
- CREATE/ALTER/DROP Index

#### 인덱스 설계 과정
1. 접근 경로(=검색 조건) 수집
	- 접근 경로는 테이블에서 데이터를 찾는 방법을 의미
	- 수집 방법에는 테이블 스캔과 인덱스 스캔이 있음
	- 접근 경로 수집 의미는 SQL이 최적화되었을 때 인덱스 스캔을 해야 하는 검색 조건들을 수집하는 것
2. 분포도 조사를 통한 후보 칼럼 선정
	- 수집된 접근 경로에 대해 분포도 조사
	- 분포도 = 데이터별 평균 로 수 / 테이블의 총 로 수
	- 일반적으로 분포도가 10~15% 정도이면 인덱스 칼럼 후보로 사용
3. 접근 경로 결정
	- 인덱스 후보 목록을 이용하여 접근 유형에 따라 어떤 인덱스 후보를 사용할 것인가를 결정
	- 만약 누락된 접근 경로가 있다면 분포도 조사를 실시하고 인덱스 후보 목록에 추가 작업을 반복
4. 칼럼 조합 및 순서 결정
	- 단일 칼럼의 분포도가 양호하면 단일 칼럼 인덱스로 확정
	- 하지만 하나 이상의 칼럼 조합이 필요한 경우는 추가 고려하여 인덱스 칼럼 순서를 결정
5. 적용 시험
	- 설계된 인덱스를 적용하고 접근 경로별로 인덱스가 사용되는지 시험해야 함
	- 여러 개의 접근 경로가 존재하는 테이블은 여러 개의 인덱스가 만들어지므로 의도한 실행 계획으로 동작하는지 확인해야 함

#### 접근 경로(=검색 조건) 유형(=인덱스 대상)
##### - 반복 수행되는 접근 경로
대표적으로 조인 칼럼을 후보로 선택(주문 1건당 평균 50개의 주문 내역을 가진다면 주문 테이블과 주문 내역 테이블을 이용하여 주문서를 작성하는 SQL은 조인을 위해 평균 50번의 주문 내역 테이블을 반복 액세스하기 때문)

##### - 분포도가 양호한 칼럼
주문 번호, 청구 번호, 주민 번호 등은 단일 칼럼 인덱스로도 충분한 수행 속도를 보장받을 수 있는 후보임

##### - 조회 조건에 사용되는 칼럼
성명, 상품명, 고객명 등 명칭이나 주문 일자, 판매일, 입고일 등 일자와 같은 칼럼은 조회 조건으로 많이 이용되는 칼럼

##### - 자주 결합되어 사용되는 칼럼
판매일 + 판매 부서, 급여일 + 급여 부서와 같이 조합에 의해 사용되는 칼럼

##### - 데이터 정렬 순서와 그룹핑 칼럼
조건뿐만 아니라 순방향, 역방향 등의 정렬 순서 고려(인덱스는 구성 칼럼 값들이 정렬되어 있어 인덱스를 이용하면 별도의 ORDER BY, 정렬 작업이 불필요함) 동일한 원리로 그룹핑 단위(GROUP BY)로 사용된 칼럼도 조사

##### - 일련번호를 부여한 칼럼
이력을 관리하기 위해서 일련번호를 부여한 칼럼에 대해서도 조사(마지막 일련번호를 찾는 경우가 빈번히 발생하므로 효과적인 액세스를 위해 필요)

##### - 통계 자료 추출 조건
통계 자료는 결과를 추출하기 위해서 넓은 범위의 데이터가 필요.(다양한 추출 조건을 사전에 확보하여 인덱스 생성에 반영)

##### - 조회 조건이나 조인 조건 연산자
위에 제시되는 유형의 칼럼과 함께 적용된 =, between, like 등의 비교 연산자를 병행 조사하여 인덱스 결합 순서를 결정할 때중요한 정보로 사용하도록 함

#### 칼럼 조합 및 순서 결정(선두 칼럼 요건)
복수 개의 칼럼(결합 칼럼)에 대해 인덱스를 사용할 때 순서가 중요하다. 인덱스 칼럼의 순서를 결정할 때 선두에 두어야 할 칼럼을 선택하는 판단 기준은 다음과 같다.

##### - 항상 사용되는 칼럼
칼럼 A, B가 인덱스로 사용될 때 칼럼 A에는 항상 값이 있어야 함(인덱스로 사용될 때 선행되는 칼럼값이 Nulll인 경우 인덱스를 이용하지 못함)

##### - 등치(=) 조건으로 사용되는 칼럼
부등호나 범위 연산(<, >, <=, >=, BETWEEN, LIKE)보다는 등호(=) 연산을 사용하는 칼럼을 선두에 배치하는 것이 좋음.

##### - 분포도가 좋은 칼럼
분포도가 좋다는 의미는 데이터값의 중복이 적은 것을 의미함 즉, 값의 중복이 적은 칼럼을 선두로 사용하는 것이 좋음

##### - ORDER BY, GROUP BY 순서
ORDER BY나 GROUP BY 절에 사용되는 칼럼 순으로 인덱스를 생성하는 것이 좋음

### 7-6. Sequence
### 7-7. Synonym
### 7-8. Stored Procedure
- Stored Procedure(In/Out Parameter, EXECUTE) vs. Function(only In Parameter, required Return Value and in SQL)

### 7-9. Function
- only In Parameter, required Return Value and in SQL

### 7-10. Trigger
- 종류와 주의 사항 등



## 8. 성능(Performance)
### 최적화를 위한 권장 사항
#### 설계(Design) 시
- Data
	- 형식, 입력 마스크, 유효성 검사 규칙 등의 Business Rule 적용 방안을 결정한다.(Server vs. Client vs. 전용 Application Server)
	- 기준 정보는 저장된 Query를 통해 생성한다.
- Database
	- Table 생성 전에 정규화 및 각 Column의 데이터 유형을 정의한다.	
	- 블록 영역을 위해 INITIAL, NEXT, EXTENTS, PCTFREE, PCTUSED를 충분히 고려한다.
	- 지정(사용자 데이터, 롤백 데이터, 분류 데이터 등)된 Table Space를 반드시 지정한다.
	- 필요한 경우 No Logging, Cache 키워드를 한다.
	- Table의 Partition 여부를 결정한다.
- Table
	- 효율적 구현을 위해 정규화 정책에 의한 중복 데이터를 허용할 수 있다.
	- 반복률이 높은 Column은 Code Table로 작성한다.
	- Table 구조 정보가 저장된 Query를 통한 Table 생성한다.
- Column
	- 필요 시 검색 속도 및 성능을 향상 시키기 위해 최대한 Data를 세분화하여 저장한다. 예) YEAR(NOW(), MONTH(NOW()), DAY(NOW()), HOUR(NOW()), MINUTE(NOW()), WEEK(NOW())
	- Primary Key와 Index는 적절한 활용한다.
	- 약어 접두어를 한다. 예) DT_LOGIN, FLG_TOP
	- 문자열, 숫자 등은 Default Size를 사용하는 것이 대부분 Load 측면에서 유리하며 일반적으로 숫자 정보를 숫자 Data Type으로 저장하고 검색하는 것이 더 좋은 성능을 나타낸다. 예) 생년월일: VARCHAR(8)보다 SMALLDATETIME(4 Byte)가 공간 절약 및 날짜 연산에 유리하다.
	- 설명(Comment) 항목을 통한 Field에 대한 자세한 정보 표기한다.
- 객체(제약 조건)에 따른 권장 접두어

	| 객체 | 접두어 | 예 | 비고 |
	| :--- | :-----: | :------------------------ | :------------------------ |
	| Primary Key           | `PK_`  | PK_USER                     | 기본 키 |
	| Foreign Key           | `FK_`  | FK_ORDER_USER               | 외래 키 |
	| Index                 | `IX_`  | IX_USER_NAME                | 일반 인덱스(IDX_도 사용) |
	| Composite Index       | IX_    | IX_ORDER_STATUS_CREATED_AT  | 컬럼명을 순서대로 작성 |
	| Composite Index       | CIX_   | CIX_ORDER_STATUS_CREATED_AT | 복합 인덱스를 구분할 때 |
	| Unique Index          | `UX_`  | UX_USER_EMAIL               | 유일 인덱스(가장 많이 사용하는 관례) |
	| Unique Index          | UIX_   | UIX_USER_EMAIL              | 유일 인덱스를 구분할때(일부 프로젝트에서 사용) |
	| Unique Constraint     | `UQ_`  | UQ_USER_EMAIL               | 유일 제약 조건 |
	| Check Constraint      | `CK_`  | CK_USER_AGE                 | 체크 제약 조건 |
	| Default Constraint    | DF_    | DF_USER_CREATED_AT          | 기본 제약 조건 |

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
데이터베이스에 저장된 모든 데이터 값의 정확성 보장해야 하며 안전하게 관리되어야 한다.

### 9-1. 데이터 무결성
#### Entity 무결성
인덱스, UNIQUE 제약 조건, PRIMARY KEY 제약 조건 또는 IDENTITY 속성을 통해 테이블의 식별자 열 또는 기본 키의 무결성을 보장한다.

#### 도메인 무결성
주어진 컬럼에 있는 데이터가 유효하도록 한다. 데이터 형식을 통해 유형을 제한하고 CHECK 제약 조건 및 규칙을 통해 형식을 제한하거나 FOREIGN KEY 제약 조건, CHECK 제약 조건, DEFAULT 정의, NOT NULL정의 및 규칙을 통해 가능한 값의 범위를 제한하여 도메인 무결성을 보장한다.

#### 참조 무결성
데이터가 입력되거나 삭제될 때 테이블 간 관계를 유지하고, 여러 테이블에서 키 값이 일관성을 유지한다.

#### 사용자 정의 무결성
사용자가 다른 무결성 범주에 포함되지 않는 특정 업무 규칙을 정의할 수 있도록 한다.

### 9-2. 제약 조건(Constraint)
제약 조건을 두는 이유는 테이블을 계획할 때 각 열에서 유효한 값을 확인하고 각 컬럼의 데이터에 대한 무결성을 강제로 적용하기 위해서다.

#### PRIMARY KEY(PK)
테이블의 기본 키를 정의하며 기본적으로 NOT NULL, UNIQUE 제약 사항이 설정되며 Row의 고유한 값을 가진 컬럼을 기본 키(PRIMARY KEY)라 한다. 테이블을 만들거나 변경할 때 PRIMARY KEY 제약 조건을 정의하여 기본 키를 만들 수 있다.

#### FOREIGN KEY(FK)
두 테이블의 데이터 사이의 연결을 설정하고 강제 적용하는데 사용되는 컬럼을 외래 키(FOREIGN KEY)라 한다. 테이블에 외래 키를 정의하며 참조 대상을 테이블명(칼럼명) 형식으로 작성해야 하며 참조 무결성이 위배되는 상황 발생 시 옵션(CASCADE, NO ACTION, SET NULL, SET DEFAULT)으로 처리 가능하다.

#### UNIQUE(UI)
기본 키가 아닌 특정 열에서 값이 중복되지 않도록 할 수 있다. 테이블에서 해당하는 열값은 유일해야 함을 의미하며 테이블에서 모든 값이 다르게 적재되어야 하는 열에 설정된다.

#### NULL 값 허용(NN)
특정 컬럼의 Row 데이터가 NULL 값을 포함할 수 있는지 여부를 결정한다. 테이블에서 해당하는 열의 값은 NULL 불가능하며 필수적으로 입력해야 하는 항목에 설정한다.

#### CHECK
사용자가 직접 정의하는 제약 조건으로 발생 가능한 상황에 따라 여러 가지 조건을 설정 가능하며 열에서 허용되는 값을 제한하여 도메인 무결성을 강제 적용한다.

#### Default 정의
NULL 값 허용이 아닌 컬럼에서 Row값이 지정되지 않는 경우 기본값으로 정의한 값이 지정된다.


### 9-3. 사용자 및 권한 관리
### 9-4. 백업과 복구(Backup & Recovery)
### 9-5. 데이터 암호화

## 10. 운영
### 10-1. 주요 운영 업무
- 유지보수
- 장애 대응과 모니터링
- 사용자 및 권한 관리
- 백업과 복구(Backup & Recovery)

### 10-2. 데이터베이스 운영 프로세스
### 10-3. 유지보수
- 성능 및 데이터 무결성과 보안
- System 및 User Database
- 저장 공간과 인덱스
- 로그

### 10-4. 장애 대응
### 10-5. 모니터링


## 11. 주요 Query
### Recursive Query at Oracle and ANSI
<details>
	<summary>소스</summary>

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
</details>

## 12. 주요 Command
### MariaDB
<details>
	<summary>최대 허용 및 접속 그리고 현재 접속</summary>

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
</details>

### PostgreSQL
<details>
	<summary>설치 등</summary>

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
</details>

## 13. 프로젝트
### 13-1. 요구사항 분석
### 13-2. 데이터 모델링
### 13-3. 테이블 생성
### 13-4. SQL 작성
### 13-5. 데이터 입력
### 13-6. 조회 및 분석
### 13-7. 검증 및 개선