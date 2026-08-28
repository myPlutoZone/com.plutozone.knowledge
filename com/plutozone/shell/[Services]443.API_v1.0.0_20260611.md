# API Version 1.0.0 for com.plutozone.services

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

| Version | Date       | Contents |
| :-----: | :---------:| :-------:|
| 1.0.0   | 2026-06-11 | [CREATE]Initial Release |

## 2. Overview(개요)

본 문서는 com.plutozone.services의 API Server와 통신하기 위한 연동(Interface) 규격에 대해 기술한다.

### 2.1 Document Version(문서 버전)

본 문서의 버전은 3개 영역으로 나뉘며 각 자리의 의미는 다음과 같으며 빌드 버전은 사용하지 않는다.

- 첫째 자리: Major 버전(규격서 Major 변경 시)
- 둘째 자리: Minor 버전(규격서 Minor 변경 시)
- 셋째 자리: Patch 버전(규격 변경 없이 코드, 설명 등 추가 또는 변경 시)

### 2.2 Terms(용어)

`자사의 정책에 의거하여 하기 용어는 대체 또는 혼용될 수 있습니다.`

| Term                         | Abbreviation | Description                                                                   |
| :--------------------------- | :----------: | :---------------------------------------------------------------------------- |
| API Server                   |  -           | com.plutozone.services의 대외용 API 서버(API Server)로 인가 후 접속 가능하다. |
| Alliance                     | ALI          | 제휴사는 고객사(Client)와 협력사(Partner)를 포함한다. |
| Channel                      | CHN          | 채널사는 자사의 서비스를 게시할 수 있는 플랫폼을 의미하여 대표적으로 통신사가 있다. |
| Manager                      | MNG          | `관리자`는 com.plutozone.services의 관리용 서비스를 이용하는 `사용자`를 말한다. |
| Member                       | MBR          | `회원`는 com.plutozone.services의 고객용 서비스를 이용하는 `사용자`를 말한다. |

## 3. Interface Architecture(연동 구조)

기본적으로 `HTTPS + POST` 방식을 사용하며 Request, Response 시 JSON 타입으로 데이터(필요 시 데이터 암호화)를 송수신한다.

## 4. 연동 정의(Interface Define)

### 4.1 HTTP Header

- HTTP Header의 Content-Type과 Accept 속성에 "application/json; charset=UTF-8"을 지정하여야 한다.
  - Content-Type: application/json; charset=UTF-8
  - Accept: application/json; charset=UTF-8
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

`연동처의 정책에 의거하여 하기 기능은 선택적으로 연동할 수 있다.`

- 개발 서버: `별도 문의`
- 상용 서버: `별도 문의`

| NO     | Entity        | Function                       | Path                          | Etc |
| :----: | :------------ | :----------------------------- | :---------------------------- | :-- |
| 3-1    | Monitoring    | [등록](#모니터링-등록)          | /monitor/writeProc.api        | |

<!--
| 1-1    | Token          | [발급](#토큰-발급)          | /security/token/issue.api     | |
| 1-2    | Token          | [조회](#토큰-조회)          | /security/token/?.api         | 생성일, 상태(활성, 만료, 폐기, 재발급, 갱신 등), 만료일, 최종 사용일시 등 |
| 1-3    | Token          | [폐기](#토큰-폐기)          | /security/token/?.api         | |
| 1-4    | Token          | [재발급](#토큰-재발급)       | /security/token/?.api         | |
| 1-5    | Token          | [갱신](#토큰-갱신)          | /security/token/?.api         | |
| 1-6    | Token          | [사용 이력](#토큰-사용 이력) | /security/token/?.api         | |
| 2-1    | Member         | [약관](#회원-약관)                 | /member/terms.api             | 마케팅 활용, 제3자 제공 동의 등 |
| 2-2    | Member         | [가입 여부](#회원-가입 여부)        | /member/exist.api             | |
| 2-3    | Member         | [가입](#회원-가입)                 | /member/register.api          | 이용자(Join) vs. 회원(Register) |
| 2-4    | Member         | [조회](#회원-조회)                 | /member/inquiry.api           | |
| 2-5    | Member         | [변경](#회원-변경)                 | /member/alter.a               | |
| 2-6    | Member         | [탈퇴](#회원-탈퇴)                 | /member/withdraw.api          | |
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
| 1     | token                | VARCHAR(256)    | Y        | 토큰 |
| 2     | expired              | CHAR(19)        | Y        | 토큰 만료 일시 |

```json
REQUEST
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

RESPONSE
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

### 모니터링 등록

<!--
[ANSI 데이터 타입]
- TINYINT, SMALLINT, INTEGER, BIGINT
- REAL, DOUBLE PRECISION, DECIMAL(p,s)
- CHAR(n), VARCHAR(n), CLOB
- DATE, TIME, TIMESTAMP
- BOOLEAN
- BLOB
-->

- seq_mon_target=`별도 문의`, reg_svr=`별도 문의`

| NO    | Request Body         | Data Type(Size) | Required | Description |
| :---: | :------------------- | :-------------- | :------: | :---------- |
| 1     | seq_srv              | SMALLINT        | Y        | 서비스 일련번호 |
| 2     | seq_mon_target       | INTEGER         | Y        | 모니터링 대상 일련번호 |
| 3     | seq_fail_code        | SMALLINT        | Y        | 장애 코드 일련번호(`코드 목록`) |
| 4     | flg_fail             | CHAR(1)         | Y        | 장애 여부(Y or N) |
| 5     | memo                 | VARCHAR(1024)   | O        | 메모 |
| 6     | reg_svr              | VARCHAR(16)     | Y        | 등록 서버 |
| 7     | reg_svr_dt           | CHAR(19)        | Y        | 등록 서버 일시(YYYY-MM-DD HH:MM:SS) |
| 8     | upt_svr              | VARCHAR(16)     | N        | 수정 서버(단, 수정 시 필수) |
| 9     | upt_svr_dt           | CHAR(19)        | N        | 수정 서버 일시(YYYY-MM-DD HH:MM:SS 단, 수정 시 필수) |

| NO    | Response Body        | Data Type(Size) | Required | Description |
| :---: | :------------------- | :-------------- | :------: | :---------- |
| 1     | seq_mon              | BIGINT          | Y        | 모니터링 일련번호 |

```json
REQUEST
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

RESPONSE
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
REQUEST
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

RESPONSE
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