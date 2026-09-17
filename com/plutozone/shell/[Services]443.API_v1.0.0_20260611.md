# RESTful API Version 1.0.0 for com.plutozone.services
> YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
COPYRIGHT © 2026 PLUTOZONE.COM ALL RIGHTS RESERVED.
***
> 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
plutozone.com의 지적재산권 침해에 해당된다.
***
> Copyright © 2026 plutozone.com All Rights Reserved.


## 1. History(이력)
| Version | Date | Contents |
| :---: | :---: | :--- |
| 1.0.0 | 2026-06-11 | [CREATE]Initial Release |


## 2. Overview(개요)
> 본 문서(com.plutozone.services API 규격서)는 com.plutozone.services의 RESTful API Server와 통신하기 위한 연동(Interface) 규격에 대해 기술한다.

### 2.1 Document Version(문서 버전)
본 문서의 버전은 3개 영역으로 나뉘며 각 자리의 의미는 다음과 같으며 빌드 버전은 사용하지 않는다.
- 첫째 자리: Major 버전(규격서 Major 변경 시)
- 둘째 자리: Minor 버전(규격서 Minor 변경 시)
- 셋째 자리: Patch 버전(규격 변경 없이 코드, 설명 등 추가 또는 변경 시)

### 2.2 Terms(용어)
자사의 정책에 의거하여 하기 용어는 대체 또는 혼용될 수 있습니다.
| Term | Abbreviation | Description |
| :--- | :---: | :--- |
| RESTful API Server | - | com.plutozone.services의 대외용 RESTful API 서버로 인가 후 접속 가능하다. |
| Alliance | ALI | 제휴사는 고객사(Client)와 협력사(Partner)를 포함한다. |
| Channel | CHN | 채널사는 자사의 서비스를 게시할 수 있는 플랫폼을 의미하여 대표적으로 통신사가 있다. |
| Manager | MNG | `관리자`는 com.plutozone.services의 관리용 서비스를 이용하는 `사용자`를 말한다. |
| Member | MBR | `회원`는 com.plutozone.services의 고객용 서비스를 이용하는 `사용자`를 말한다. |


## 3. Interface Architecture(연동 구조)
### 3-1. 환경 및 통신 규격
| Category | Details | Remarks |
| :--- | :--- | :--- |
| Protocol | HTTP/S | |
| Server | `별도 문의` | 개발, 상용  등 |
| Base URL | `/v1` | |
| Data Format | JSON | 필요 시 데이터 암호화 |
| Character Encoding | UTF-8 | |
| Authentication Method | Bearer Token | |
| HTTP Method | GET, POST, PUT, PATCH, DELETE | |

- `엔티티는 명사` 형태의 URL로 표현하며 `행위는 HTTP Method`로 표현한다.
- 요청 및 응답 데이터는 JSON 형식을 사용한다.
- HTTP Status Code를 이용하여 처리 결과를 표현한다.
- API 버전은 URL에 포함하고 문서 버전은 요청 헤더에 포함한다.
- 날짜/시간은 ISO 8601 형식(예: YYYY-MM-DD, `YYYY-MM-DDTHH:MM:SS`, YYYY-MM-DDTHH:MM:SS+09:00)을 사용한다.
- 인증이 필요한 API는 Authorization Header를 사용한다.

### 3-2. HTTP Method(권장)
| Method | Usage | URL |
| :---: | :---: | :--- |
| GET | 목록 | /members?page=1&size=10&mbr_nm=홍길동&mbr_age=20 |
| POST | 생성 | /members |
| GET | 조회 | /members/{seq_mbr} |
| PATCH | 일부 수정 | /members/{seq_mbr} |
| PUT | 전체 수정 | /members/{seq_mbr} |
| DELETE | 삭제 | /members/{seq_mbr} |


## 4. 연동 정의(Interface Define)
### 4.1 HTTP Header
- HTTP Header의 Content-Type과 Accept 속성에 "application/json; charset=UTF-8"을 지정하여야 한다.
	- Content-Type: application/json; charset=UTF-8
	- Accept: application/json; charset=UTF-8
	- Authorization: Bearer {Token}
- 파일 다운로드일 경우 application/octect_stream를 사용한다.

### 4.2 Request(요청)
Request 시 JSON 구조는 하기 형식과 같으며 1) header의 `#0000FF` seq_srv=`별도 문의(이하 포함)`, ver="문서 버전", lang="ko", token="`별도 문의`"하고 2) body는 하기 인터페이스 목록를 참고 바랍니다.
```json
{
    "header": {
        "seq_srv": 0
        , "ver": "1.0.0"
        , "lang": "ko"
        , "token": "JSON Web Token(JWT) is ..."
    },
    "body": {
        ...
    }
}
```

### 4.3 Response(응답)
Response 시 JSON 구조는 하기 형식과 같으며 1) header의 code, message는 하기 `코드 목록`를 참고하고 2) body는 하기 `인터페이스 목록`를 참고 바랍니다.
```json
{
    "header": {
        "code": "0000"
        , "message": "성공"
    },
    "body": {
        ...
    }
}
```


## 5. List of Interface(인터페이스 목록)
- 연동처의 정책에 의거하여 하기 기능은 선택적으로 연동할 수 있다.

| NO | Entity | Method | Function | URL | Etc |
| :---: | :--- | :---: | :--- | :--- | :--- |
| 3-1 | Monitoring | GET | [목록](#모니터링-목록) | /monitors?page=&size=&search_field=&search_term=&sort_field=&sort_order= | |
| 3-2 | Monitoring | POST | [등록](#모니터링-등록) | /monitors | |
<!--
Query Parameter
Path Parameter
Request Field

| 1-1 | Token  | POST | [발급](#토큰-발급) | /tokens | |
| 1-2 | Token  | DELETE | [폐기](#토큰-폐기) | /tokens | |
| 1-3 | Token  | POST | [갱신](#토큰-갱신) | /tokens/refresh | 기존 토큰을 사용해서 신규 토큰을 발급 |
| 1-4 | Token  | POST | [재발급](#토큰-재발급) | /tokens/reissue | 만료·폐기·분실 등의 이유로 신규 토큰을 다시 발급 |
| 1-5 | Token  | GET | [조회](#토큰-조회) | /tokens | 생성일, 상태(활성, 만료, 폐기, 갱신, 재발급 등), 만료일, 최종 사용 일시 등 |
| 1-6 | Token  | GET | [사용 이력](#토큰-사용-이력) | /tokens/use/ | |
| 2-1 | Member | GET | [약관](#회원-약관) | /members/terms/ | 마케팅 활용, 제3자 제공 동의 등 |
| 2-2 | Member | GET | [가입 여부](#회원-가입-여부) | /members/exists | |
| 2-3 | Member | POST | [가입](#회원-가입) | /members | |
| 2-4 | Member | GET | [조회](#회원-조회) | /members/me | |
| 2-5 | Member | PATCH | [변경](#회원-변경) | /members/me | |
| 2-6 | Member | DELETE | [탈퇴](#회원-탈퇴) | /members/me | |
-->


## 6. Interface List
<!--
### 토큰 발급
- id=`별도 문의`, passwd=`별도 문의`
- 연동처에 따라 토큰 발급에 관한 정책이 상이할 수 있습니다.

| NO    | Request Body         | Data Type(Size) | Required | Description |
| :---: | :------------------- | :-------------- | :------: | :---------- |
| 1     | id                   | VARCHAR(16)     | Y        | 아이디 |
| 2     | passwd               | VARCHAR(16)     | Y        | 암호 |

| NO    | Response Body        | Data Type(Size) | Required | Description |
| :---: | :------------------- | :-------------- | :------: | :---------- |
| 1     | Token        | VARCHAR(256)    | Y        | 토큰 |
| 2     | expired              | CHAR(19)        | Y        | 토큰 만료 일시 |

```json
Request 
{
    "header": {
        "seq_srv": 0
        , "ver": "1.0.0"
        , "lang": "ko"
        , "token": ""
    },
    "body": {
        "id": "[ID]"
        , "passwd": "[PASSWD]"
    }
}

Response 
{
    "header": {
        "code": "0000"
        , "message": "성공"
    },
    "body": {
        "token": "JSON Web Token(JWT) is ..."
        , "expired": "2019-08-17 16:34:20"
    }
}
```
-->
### 모니터링 목록
| NO | `Query Parameter` | Data Type(Size) | Required | Description |
| :---: | :--- | :--- | :---: | :--- |
| 1 | page | SMALLINT | N | 페이지 번호(기본값: 1) |
| 2 | size | TINYINT | N | 페이지 크기(기본값: 10) |
| 3 | search_field | VARCHAR(16) | N | 검색 대상(seq_mon_target 등) |
| 4 | search_term | VARCHAR(16) | N | 검색어 |
| 5 | sort_field | VARCHAR(8) | N | 정렬 대상(reg_svr_dt 등) |
| 6 | sort_order | VARCHAR(8) | N | 정렬 방식(asc or desc) |

| NO | Response Body | Data Type(Size) | Required | Description |
| :---: | :--- | :--- | :---: | :--- |
| 1 | seq_mon | BIGINT | Y | 모니터링 일련번호 |

```json
Request 
{
    "header": {
        "seq_srv": 0
        , "ver": "1.0.0"
        , "lang": "ko"
        , "token": "JSON Web Token(JWT) is ..."
    },
    "body": {
        "seq_srv": 0
        , "seq_mon_target": 0
        , "seq_fail_code": 0
        , "flg_fail": "N"
        , "memo": "10"
        , "reg_svr": "PLZ_WAS_001"
        , "reg_svr_dt": "2026-08-28 17:38:09"
        , "upt_svr": ""
        , "upt_svr_dt": ""
    }
}

Response 
{
    "header": {
        "code": "0000"
        , "message": "성공"
    },
    "body": {
        "seq_mon": 1
    }
}
```

### 모니터링 등록
- seq_mon_target=`별도 문의`, reg_svr=`별도 문의`

| NO | `Request Body` | Data Type(Size) | Required | Description |
| :---: | :--- | :--- | :---: | :--- |
| 1 | seq_srv | SMALLINT | Y | 서비스 일련번호 |
| 2 | seq_mon_target | INTEGER | Y | 모니터링 대상 일련번호 |
| 3 | seq_fail_code | SMALLINT | Y | 장애 코드 일련번호(`코드 목록`) |
| 4 | flg_fail | CHAR(1) | Y | 장애 여부(Y or N) |
| 5 | memo | VARCHAR(1024) | O | 메모 |
| 6 | reg_svr | VARCHAR(16) | Y | 등록 서버 |
| 7 | reg_svr_dt | CHAR(19) | Y | 등록 서버 일시(YYYY-MM-DD HH:MM:SS) |
| 8 | upt_svr | VARCHAR(16) | N | 수정 서버(단, 수정 시 필수) |
| 9 | upt_svr_dt | CHAR(19) | N | 수정 서버 일시(YYYY-MM-DD HH:MM:SS 단, 수정 시 필수) |

| NO | Response Body | Data Type(Size) | Required | Description |
| :---: | :--- | :--- | :---: | :--- |
| 1 | seq_mon | BIGINT | Y | 모니터링 일련번호 |

```json
Request 
{
    "header": {
        "seq_srv": 0
        , "ver": "1.0.0"
        , "lang": "ko"
        , "token": "JSON Web Token(JWT) is ..."
    },
    "body": {
        "seq_srv": 0
        , "seq_mon_target": 0
        , "seq_fail_code": 0
        , "flg_fail": "N"
        , "memo": "10"
        , "reg_svr": "PLZ_WAS_001"
        , "reg_svr_dt": "2026-08-28 17:38:09"
        , "upt_svr": ""
        , "upt_svr_dt": ""
    }
}

Response 
{
    "header": {
        "code": "0000"
        , "message": "성공"
    },
    "body": {
        "seq_mon": 1
    }
}
```

<!--
# 회원 가입
* 필요 시 회원 약관 및 가입 여부를 추가 연동할 수 있습니다.

| NO    | Request Body         | Data Type(Size) | Required | Description |
| :---: | :------------------- | :-------------- | :------: | :---------- |
| 1     | seq_ali              | Integer         | Y        | 제휴사 일련번호 |
| 2     | seq_mbs              | Integer         | Y        | 멤버십 일련번호 |
| 3     | mbs_card_num         | Big Integer     | -        | 멤버십 카드 번호: 휴대폰 번호가 없을 경우 |
| 4     | cellphone            | varchar(16)     | -        | 휴대폰 번호: 멤버십 카드 번호가 있어도 휴대폰 번호 조회가 우선 |

| NO    | Response Body        | Data Type(Size) | Required | Description |
| :---: | :------------------- | :-------------- | :------: | :---------- |
| 1     | mbs_card_num         | Big Integer     | Y        | 멤버십 카드 번호 |
| 2     | seq_mbs_grade        | Small Integer   | Y        | 멤버십 등급 일련번호 |
| 3     | mbs_grade_nm         | varchar(64)     | Y        | 멤버십 등급 자국명 |
| 4     | mbs_grade_en         | varchar(64)     | Y        | 멤버십 등급 영문명 |
| 5     | total                | Integer         | Y        | 누적 포인트 |
| 6     | usable               | Integer         | Y        | 가용 포인트 |
| 7     | expect               | Integer         | Y        | 예정 포인트 |
```
Request 
{
    "header": {
        "seq_srv": 0
        , "ver": "1.0.0"
        , "lang": "ko"
        , "token": "JSON Web Token(JWT) is ..."
    },
	"body": {
        "seq_ali": 3
        , "seq_mbs": 1
        , "mbs_card_num": 7008190000783650
        , "cellphone": "01099471973"
	}
}

Response 
{
    "header": {
        "code": "0000"
        , "message": "성공"
    },
    "body": {
        "mbs_card_num": 7008190000783650
        , "seq_mbs_grade": 1
        , "mbs_grade_nm": "일반"
        , "mbs_grade_en": "Gernal"
        , "total": 11
        , "usable": 1
        , "expect": 0
    }
}
```
-->

## 7. Code List(코드 목록)
### 7-1. HTTP Response Code
| NO | Code | Description |
| :---: | :---: | :--- |
| 1-1 | 200 | OK |

<!--
| NO    | Code                 | Data Type(Size) | Value  | Description |
| :---: | :------------------- | :-------------- | :----: | :---------- |
| 1-1   | tc_channel           | Small Integer   | 65     | 자사(강화리조트) |
| 2-1   | tc_pay_mth           | Small Integer   | 66     | 신용/체크 카드 |
| 2-2   | tc_pay_mth           | Small Integer   | 67     | 현금 |
| 2-3   | tc_pay_mth           | Small Integer   | 68     | 계좌 이체 |
| 2-4   | tc_pay_mth           | Small Integer   | 69     | 자사 포인트 |
| 2-5   | tc_pay_mth           | Small Integer   | 70     | 자사 쿠폰 |
| 2-6   | tc_pay_mth           | Small Integer   | 71     | T-멤버십 |
| 3-1   | tc_secede_reason     | Small Integer   | 72     | 서비스 미사용 |
| 3-2   | tc_secede_reason     | Small Integer   | 73     | 서비스 불만족 |
| 3-3   | tc_secede_reason     | Small Integer   | 74     | 타 서비스 사용 |
| 3-4   | tc_secede_reason     | Small Integer   | 75     | 개인정보 유출 우려 |
| 3-5   | tc_secede_reason     | Small Integer   | 76     | 기타 |
| 4-1   | seq_mbs_grade        | Integer         | 1      | 멤버십 등급-루키 |
| 4-2   | seq_mbs_grade        | Integer         | 2      | 멤버십 등급-매니아 |
| 4-3   | seq_mbs_grade        | Integer         | 3      | 멤버십 등급-레이서 |
| 4-4   | seq_mbs_grade        | Integer         | 4      | 멤버십 등급-마스터 |
| 5-1   | seq_mbs_shp          | Integer         | 1      | 멤버십 매장(적립/사용)-강화리조트 오프라인 |
| 5-2   | seq_mbs_shp          | Integer         | 2      | 멤버십 매장(적립/사용)-강화리조트 온라인 |
| 6     | seq_use_shp          | Integer         | 1      | 사용처-강화리조트 |
-->