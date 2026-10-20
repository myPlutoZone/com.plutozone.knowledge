# com.plutozone.knowledge.language.JavaScript


## Contents
01. [JavaScript 특징과 기본 문법들](#1-javascript-특징과-기본-문법들)
02. [제어문](#2-제어문)
03. 객체(Object)
04. 함수(Function)
05. Ajax(Asychronous JavaScript and XML)
06. jQuery
07. 고객 편리성 제공 화면을 위한 설계 및 구현


## 1. JavaScript 특징과 기본 문법들
### 1-1. `크롬 개발자 모드`
- 에러 시
	```html
	<!DOCTYPE html>
	<html>
	<head>
		<title></title>
		<script>aler("Hello, JavaScript");</script>
	</head>
	<body>
		JavaScript
	</body>
	</html>
	```
- 주요 패널
	- Elements(요소들)
	- Console(콘솔)
	- Sources(소스들)
	- ...
- 설정
	- 캐쉬
	- 쿠키

### 1-2. 개요
- Frontend vs. Backend
- HTML, CSS, and JavaScript are downloaded from the Server and run in the Browser.
- 탄생(Mocha at Netscape Navigator)과 표준화(ES6) 그리고 확장(jQuery, Angular, React, Vue.js, Node.js, PhoneGap 등)

### 1-3. 개발 환경
- Browser(IE, `Edge`, `Chrome` 등)
- Editor(Notepad, EditPlus, SublimeText, Brackets, [Visual Studio Code](../development/vscode.md), `Notepad++`, Eclipse 등)

### 1-4. 기본 문법
- 문장(;)과 대소문자 구분 등
- 선언문
	```html
	...
	<head>
		<script> … </script>
		<script src="common.js"></script>
	</head>
	<body>
		<script> … </script>
		<script src="common.js"></script>
	</body>
	...
	```
- 주석
	- //
	- /* … */
- 변수 범위(Scope: 전역 또는 지역)와 선언
	- var, let(중복 불가), const(상수=변경 불가)
	- 변수명 첫 글자는 ...만을 허용 and 첫 글자 다음은 ...만을 허용
	- Naming Rule(Camel 표기법 등)
	- 예약어
- 자료형
	- String(")
	- Char(')
	- Number
	- Boolean(true, false)
	- undefined and typeof
	- Object
		```html
		<script>
			var tv = new Object( );
			tv.color = "white";
			tv.price = 300000;
			tv.info = function( ) {
				document.write("tv 색상: " + this.color, "<br>");
				document.write("tv 가격: " + this.price, "<br>");
			}

			var car = { 
				color: "black",
				price: 5000000,
				info: function( ) {
					document.write("car 색상: " + this.color, "<br>");
					document.write("car 가격: " + this.price, "<br>");
				} 
			}; 

			document.write("<h1>tv 객체 메서드 호출</h1>");
			tv.info();
			document.write("<h1>car 객체 메서드 호출</h1>");
			car.info();        
		</script>
		```
- 연산자
	- 산술: +, -, *, /, %
	- 대입: = , +=, -=, *=, /=, %=
	- 증감: ++, --
	- 비교: >, <, >=, <=, ==(자료형 미확인), !=(자료형 미확인), ===(자료형 확인), !==(자료형 확인)
	- 논리: &&, ||, !
	- 삼항: 조건식 ? 코드1: 코드2
	- 형 변환과 연산자 우선 순위
- 템플릿 리터럴
	- JavaScript에서 backtick(`) 문자를 사용하여 문자열을 표현(줄바꿈 + 문자열 내부에 표현식을 포함)
		```js
		var name	= "James";
		var message	= `Dear ${name}
		My name is Pluto.
		I'm ${1+1} years old.`;
		alert(message);
		```


## 2. 제어문
### 조건문(Conditional Statements)
- if
- if/else
- if/else if
- if/else if/else
- Nested if
- Block({}) Level Scope
- switch/case/break/default

### 반복문(Loop Statements)
- while
- do/while
- for
- break(중지)와 continue(건너뛰기)
- Nested Loop

## 3. 객체(Object)
- 객체(Object) = 메소드(Method) + 속성(Property) + 이벤트(Event)
- 객체(All of Object in Browser Script Engine) in JavaScript
	- 내장 객체(Built-in Object): Object, String, Date, Array, Math, RegExp 등
	- 브라우저 객체 모델(BOM, Browser Object Model): window, screen, location, history, navigator 등
	- 문서 객체 모델(DOM, Document Object Model): HTML 문서 구조
	```mermaid
	flowchart TB
	ROOT["All of Object in Browser Script Engine"]

	ROOT --> DOM["DOM (Document Object Model)"]
	ROOT --> BOM["BOM (Browser Object Model)"]
	ROOT --> JS["Built-in Object at JavaScript"]

	%% DOM
	DOM --> DOC["<b>document</b>"]
	DOC --> HTML["Root element: &lt;html&gt;"]

	HTML --> HEAD["Element: &lt;head&gt;"]
	HTML --> BODY["Element: &lt;body&gt;"]

	HEAD --> TITLE["Element: &lt;title&gt;"]
	TITLE --> TITLETEXT["Text: &quot;My Title&quot;"]

	BODY --> A["Element: &lt;a&gt;"]
	BODY --> H1["Element: &lt;h1&gt;"]

	A --> LINKTEXT["Text: &quot;My Link&quot;"]
	A -.-> HREF["Attribute: &quot;href&quot;"]

	H1 --> H1TEXT["Text: &quot;My Header&quot;"]

	BODY -.-> MORE1["..."]

	%% Additional DOM objects
	DOC -.-> FORM["form"]
	DOC -.-> IMAGE["image"]
	DOC -.-> MORE2["..."]

	%% BOM
	BOM --> WINDOW["window"]
	WINDOW --> BOMDOC["<b>document</b>"]
	WINDOW --> HISTORY["history"]
	WINDOW --> LOCATION["location"]
	WINDOW --> NAVIGATOR["navigator"]
	WINDOW --> SCREEN["screen"]

	SCREEN -.-> MORE3["..."]

	%% JavaScript built-in objects
	JS --> OBJECT["Object"]
	JS --> STRING["String"]
	JS --> DATE["Date"]
	JS --> ARRAY["Array"]
	JS --> FUNCTION["Function"]
	JS -.-> MORE4["..."]

	%% Styling
	classDef root fill:#fff,stroke:#666,stroke-width:1.5px,color:#222;
	classDef category fill:#fff,stroke:#666,stroke-width:1.5px,color:#222;
	classDef node fill:#fff,stroke:#666,color:#222;
	class ROOT root;
	class DOM,BOM,JS category;
	class DOC,HTML,HEAD,BODY,TITLE,TITLETEXT,A,H1,LINKTEXT,HREF,H1TEXT,FORM,IMAGE,MORE1,MORE2,WINDOW,BOMDOC,HISTORY,LOCATION,NAVIGATOR,SCREEN,MORE3,OBJECT,STRING,DATE,ARRAY,FUNCTION,MORE4 node;
	```


## 4. 함수(Function)
- 정의
	```javascript
	...
	function functionName() {…}
	...
	```
- 매개 변수
- 리턴(return)과 값
- 내장 함수(Built-in Function)
	- encode*()
	- parse*()
	- eval() 등


## 5. Ajax(Asychronous JavaScript and XML)
- 비동식 방식의 JavaScript와 XML
- HyperText(화면 전환 필요) vs. Ajax 그리고 동기식(응답 대기 필요) vs. 비동기식
- Text vs. HTML vs. XML vs. JSON


## Example
- [JavaScript](/WebContent/knowledge/language/js/default.html)
- [Mojs-1](/WebContent/knowledge/language/js/mojs-1.html)
- [Mojs-2](/WebContent/knowledge/language/js/mojs-2.html)


## 6. jQuery
### jQuery란?
- JavaScript의 대표적인 Library
- 브라우저 호환성(DOM과 Event Object가 브라우저에 따라 상이함) 문제 해결?
- 애니메이션 기능 등 지원
- 사용 방식 그리고 버전, 압축 여부 등
	- Download 방식(https://jquery.com/download/)
	- CDN 방식(https://cdnjs.com/libraries/jquery)

### jQuery 기반의 선택자(Selector)
- 직접(전체, 아이디, 클래스, 요소, 그룹, 종속 등) 선택자
	```html
	<!DOCTYPE html>
	<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>아이디(#) 선택자</title>
		<script src="js/jquery.js"></script>
		<script>
			$(function() {
				$("#title").css("color", "red");
			});
		</script>
	</head>
	<body>
		<h1 id="title">제목</h1>
	</body>
	</html>
	```
	```html
	<!DOCTYPE html>
	<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>클래스(.) 선택자</title>
		<script src="js/jquery.js"></script>
		<script>
			$(function(){
				$(".tit").css("background-color", "#ff0").css("border", "2px dashed #f00");
			});
		</script>
	</head>
	<body>
		<h1>제이쿼리</h1>
		<h2 class="tit">선택자</h2>
		<h3>직접 선택자</h3>
	</body>
	</html>
	```
- 인접(부모, 상위, 인접 상위, 하위, 자식, 자식들, 이전, 이전들, 다음, 다음들 등), 위치, 속성, 콘텐츠 등 선택자

### jQuery 기반의 조작 메서드(Method)
- 속성 조작 메서드
- 수치 조작 메서드
- 객체(HTML Element) 편집(생성, 복제, 삭제 등) 메서드

### jQuery 기반의 Event 등록과 처리 그리고 제거
- Event 등록 메서드 종류
	- 로딩: load(), ready(), error()
	- 마우스: click(), dblclick(), mouseout(), mouseover(), hover(), mousedown() 등
	- 포커스: focus(), focusin(), blur(), change()
	- 키보드: keypress(), keydown(), keyup()
- Event 등록 방식
	- 단독
	- 그룹
- Event 객체와 종류
	- 마우스: clientX, clientY, pageX, pageY 등
	- 키보드: keyCode, altKey, ctrlKey 등
	- 전체: target 등

### Ajax(Asychronous JavaScript and XML)
- HyperText(화면 전환 필요) vs. Ajax 그리고 동기식(응답 대기 필요) vs. 비동기식
- Text vs. HTML vs. XML vs. JSON
- Method: load(), $.ajax(), $.post(), $.get(), $.getJSON(), $.getScript(), .ajaxStop(…), .ajaxSuccess(…), .ajaxComplete(…)
- Non Cross-Domain


## 7. 고객 편리성 제공 화면을 위한 설계 및 구현
### 메인
### 로그인
### 목록
### 등록 폼 및 등록
### 보기
### 수정 폼 및 수정
### 삭제(처리)