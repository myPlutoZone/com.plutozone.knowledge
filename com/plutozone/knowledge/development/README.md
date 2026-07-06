# com.plutozone.knowledge.development


## AI 시대! 개발자의 길을 찾다! = 시대의 변화(..., 닷컴, 검색과 포탈, 클라우드, 인공지능, ...)
- AI 코딩 도구: Cursor AI, Windsurf 등
- 전통적 코딩 vs. 바이브 코딩 and Low Code vs. No Code
- 신속한 개발과 분석 및 보고를 원하는 대표와 팀장 vs. 담당자


## 요구 사항, 분석, 설계, 구현, 검증 그리고 고도화
- 요구 사항, 분석, 설계, 구현, 검증(기능 등) 및 상용화
	- 기능적 또는 비기능적(성능, 보안 등) 요구 사항
	- 요구사항 정의서와 명세서 그리고 차이점


## Standard Guide for Development
- Common
	- 개발 시 쉽지 않은 2가지 문제는 1) 네이밍(최적의 명명은 쉽지 않으므로 지속적으로 개선) 2) 캐쉬(삭제 자동화)
	- Workspace vs. Project vs. Package vs. Class
	- 저작권(Copyright)과 주석 at File, Class, Method, Attribute
	- TODO Comment for 필수, 개선 그리고 향후
	- Code Rule for Insert, Update, Delete
	- Tab vs. Space for File Size and Usage
	- 로컬 개발 환경 설정(Static vs. Dynamic Resouce)
	- 효율적 기준 정보(예: 코드와 값)와 입력값(예: 로그인 아이디와 암호, 회원 가입 정보) 관리
- HTML
	- POST vs. GET
- CSS
	- 난독화
	- 기본적으로 속성들은 스페이스(Space)로 분리하지만 include일 경우 스페이스(Space)를 생략(예: border:1px solid red;text-aling:center;)한다. 특히 min 파일일 경우
- JavaScript
	- 난독화
- JSP
- Java
	- Variable Initialization(Primitive vs. Object) at DTO/VO
	- RESTful(POST, GET, PUT, DELETE, HEAD, ...) API vs. HTTP only POST API
- Framework for MVC
	- Dependency for Spring Web(Spring Version + Java Version) vs. Spring Boot(Spring Boot Version + Spring Version + Java Version)
	- MVC = M(Service + Dao) + V(View) + C(Controller)
	- [Controller] CRUD Page(list, writeForm, writeProc, view, modifyForm, modifyProc, remove, ...)
	- [Controller] Frist Error for View and try/catch/finally
	- [Controller] Service Message for Write, Update, ... and Redirect or Forward
	- [Service] System Transation vs. User Transaction
	- Componet Name(ex: @Controller("com.plutozone.common.controller.SystemExceptionCtrl) at Spring Object
	- Session or Token vs. Filter vs. AOP vs. Interceptor 그리고 방어 코드
	- Default Error Page(ex: Tomcat) vs. User Defined Error Page for 404, 500, ...
	- Static and Dynamic Properties
	- Logging(DEBUG, INFO, ...)
	- Support Multi Database and Language


## Architecture and Design for Development
- 식별(ID) vs. 인증(Password) vs. 인가(Using)
- MPA(Multiple Page Application = JSP + Data) vs. SPA(Single Page Application = Script + Ajax + JSON)


## Co-Work at Web Development
- Web Developer vs. 사업
- Web Developer vs. 기획
- Web Developer vs. 디자인
- Web Developer vs. Publisher or App Developer or DBA or SE
