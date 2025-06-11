# com.plutozone.knowledge.management.Term(용어)


## Contents
1. [Business ............. 업무](#1-business)
2. [Development .......... 개발](#2-development)
3. [Process .............. 처리](#3-process)


## 1. Business
- Full Name = Package or Class or Varialbe at Java
- Short Name = Table or Attribute at Database

### 1-1. 상태(Status)
| 용어 | 설명 |
| :--- | :--- |
| 예정 | |
| 진행 | |
| 취소 | 예정 또는 진행을 취소(변경 불가) |
| 보류 | 예정 또는 진행을 보류(취소 상태로 변경 가능성이 있을 경우) |
| 대기 | 예정 또는 진행을 대기(진행 상태로 변경 가능성이 있을 경우) |
| 종료 | 부정적 결과 |
| 완료 | 긍정적 결과 |

### 1-2. 사유(Reasoon)
| 용어 | 설명 |
| :--- | :--- |
| 추가 | 뭔가 새로운 것 |
| 개선 | 뭔가 더 향상 시킨 것 |
| 결함 | 잘못된 것을 수정 |
| 삭제 | 뭔가 제거 |
| 참고 | 부연 설명 |

### 1-3. Fundamental(기준)
| 용어 | 약어  | 설명 |
| :--- | :---: | :--- |
| User                      | USR | 사용자(서비스를 사용하는 자 = 회원 + 관리자) |
| Customer                  |  -  | 고객(회원 + 고객사) | 
| Member                    | MBR | 회원(서비스를 제공 받는 자) |
| Manager                   | MNG | 관리자(서비스를 제공 하는 자=자사 + 제휴사) |
| Alliance                  | ALI | 제휴사(고객사 + 협력사) |
| Client                    |  -  | 고객사(서비스 개발을 요청한 제휴사) |
| Partner                   |  -  | 협력사(서비스 개발을 협업한 제휴사 |
| BackOffice(Console)       |  -  | 백오피스(통합 관리자만 사용하는 서비스 영역) |
| FrontOffice               |  -  | 프론트오피스 또는 관리자(개별 관리자만 사용하는 서비스영역) |
| Front                     |  -  | 프론트(회원이 사용하는 서비스 영역) |
| Product                   | PRD | 상품 |
| Coupon                    | CPN | 쿠폰 |
| Master                    | MST | 개요(엔티티의 개요) |
| Detail                    | DTL | 상세(엔티티의 상세) |
| Board                     | BBS | 게시판 |      
| Register(Join)            |  -  | 서비스/회원/관리자에 가입 |
| CellPhone                 |  -  | 휴대폰 번호 |
| TelPhone                  |  -  | 연락처 번호 |

### 1-4. Division(구분)
| 용어 | 약어  | 설명 |
| :--- | :---: | :--- |
| Classification    | CLSS | 분류(수평적 관계 구분) 예) 제휴사의 종류는 협력사 또는 파트너사, 결제 시 구분은 전결 또는 합의 등 |
| Type              | TYPE | 유형(종속적 관계 구분) 예) 주소의 구분은 지번 또는 도로명 등 |
| Form              | FORM | 형태(물리적 구분) 예) 실물 상품 vs. 디지털콘텐츠 상품, 포인트 멤버십 vs. 스템프 멤버십 |
| Attribute         | ATTR | 속성 예) SS vs. FW, L vs. XL |

### 1-5. Product(상품)
| 용어 | 약어  | 설명 |
| :--- | :---: | :--- |

### 1-6. Membership(멤버십)
| 용어 | 약어  | 설명 |
| :--- | :---: | :--- |

### 1-7. Coupon(쿠폰)
| 용어 | 약어  | 설명 |
| :--- | :---: | :--- |


## 2. Development
### 2-1. Code Unit(코드 단위)
| 용어 | 약어  | 설명 |
| :--- | :---: | :--- |
| Class        | CLS | 클래스 |
| Library      | LIB | 라이브러리 |
| Package      | PKG | 패키지 |
| Framework    | FRM | 프레임웍 |

### 2-2. Software(소프트웨어)
| 용어 | 약어  | 설명 |
| :--- | :---: | :--- |
| Module       | MDL | 모듈(독자 실행 불가한 프로그램) |
| Daemon       | DMN | 데몬(독자 실행 가능한 프로그램) 예) kr.co.mcom.main |
| Service      | SRV | 서비스(의존 실행형 소스 프로그램 ) 예) kr.co.mcomplus.channel |
| Solution     | SLT | 솔루션(서비스들과 유틸리티 기능들의 집합체) 예) com.plutozone.services |
| Platform     | PLT | 플랫폼(통합 비지니스 서비스 솔루션) 예) 모바일 플랫폼, 광고 플랫폼 |

### 2-3. Hardware(하드웨어)
| 용어 | 약어  | 설명 |
| :--- | :---: | :--- |
| Server | SVR   | 서버 |


## 3. Process
- Full Name = Method and View

| 용어 | 약어  | 설명 |
| :--- | :---: | :--- |
| success       |  -  | 성공|
| failure       |  -  | 실패 |
| list          |  -  | 페이징 있을 시 목록 |
| listing       |  -  | 페이징 없을 시 목록 | 
| write         |  -  | 등록 |
| writeForm     |  -  | 등록 양식 |
| writeProc     |  -  | 등록 처리 |
| view          |  -  | 보기 |
| modifyForm    |  -  | 수정 양식 |
| modifyProc    |  -  | 수정 처리 |
| removeForm    |  -  | 삭제 양식 |
| removeProc    |  -  | 삭제 양식 |
| confirm       |  -  | 확인 |
| cancel        |  -  | 취소 |
| duplication   |  -  | 중복 |
| search        |  -  | 조회 |
| show          |  -  | 보이기 |
| hide          |  -  | 숨기기 |
| shorten       |  -  | 간략 |
| details       |  -  | 상세 |
