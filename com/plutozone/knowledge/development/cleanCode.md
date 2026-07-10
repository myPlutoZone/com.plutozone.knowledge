# com.plutozone.knowledge.development.CleanCode


## Overview
### 출처
- https://github.com/Yooii-Studios/Clean-Code


## Contents
- [클린 코드란?](#클린-코드란?)
- [의미 있는 이름](#의미-있는-이름)
- [함수](#함수)
- [주석](#주석)
- [형식 맞추기](#형식-맞추기)
- [자료 구조와 객체형](#자료-구조와-객체형)
- [Summary](#summary)


## 클린 코드란?
- 컴퓨터 언어의 추상화(고급 또는 저급 언어의 관점) 레벨이 높아지고 도메인에 특화된 언어일지라도 코드(Code)는 사라지지 않는다.
- 빨리 가기 위한 단 하나의 방법은 "최대한 깨끗한 코드를 항상 유지하는 것"이다.
- 클린 코드란?
	- “Big” Dave Thomas, founder of OTI, godfather of the Eclipse strategy
		- *다른 이가 수정하기 쉬워야 한다.*
		- *테스트를 해야 한다.*
		- *코드는 간결할수록 좋다.(Smaller is better)*
		- *코드는 세련되어야 한다.*
	- Michael Feathers, author of Working Effectively with Legacy Code*
		- *코드를 Care하라.(=주의와 관심을 가지고 작성하라.)*
	- Ron Jeffries, author of Extreme Programming Installed and Extreme Programming Adventures in C#
		- *중복을 없애라.*
		- *클래스/메서드는 한 가지 일만 하게 하라.*
		- *메서드의 이름 등으로 코드가 하는 일을 명시하라.*
		- *(메서드 등을) 일찍 추상화해서 프로젝트를 빠르게 진행할 수 있게 하라.*
- 프로그래머는 작가다. 코드는 글과 같다.
	- 실제로 읽기와 쓰기에 들이는 시간은 대략 10:1 정도이다. 새 코드를 작성하기 위해서는 옛 코드들을 읽어야 하기 때문이다.
	- 그러므로 빨리 가고 싶다면! 쉽게 코드를 작성하고 싶다면! "읽기 쉽게 작성하라".


## 의미 있는 이름
- 의도를 분명히 밝혀라
	<details>
	<summary>예제</summary>

	```java
	// Bad
	int d; // elapsed time in days
	// Good
	int elapsedTimeInDays;
	```
	</details>
- 그릇된 정보를 피하라
	- 중의적으로 해석될 수 있는 이름 지양하기.
	- 특수한 의미를 가지는 단어(List 등)는 실제 컨테이너가 List가 아닌 이상 accountList와 같이 변수명에 붙이지 말자. accountGroup, bunchOfAccounts, accounts 등으로 명명하자.
	- 비슷해 보이는 명명에 주의하자.
- 의미 있게 구분하라(불용어=noise word를 쓰지 말자)
	- 말이 안되는 단어(예: 한 글자만 바꾼다던지 한 단어), [a1, a2, …]과 같이 숫자로 구분하는 경우 주의하자.
	- 클래스 이름에 Info, Data와 같은 불용어를 붙이지 말자. 정확한 개념 구분이 되지 않는다. 이들이 혼재할 경우 서로의 역할을 정확히 구분하기 어렵다.
		- Name vs. NameString
		- getActiveAccount() vs. getActiveAccounts() vs. getActiveAccountInfo()
		- money vs. moneyAmount
		- message vs. theMessage
- 발음하기 쉬운 이름을 사용하라
	<details>
	<summary>예제</summary>
	
	```java
	// Bad
	class DtaRcrd102 {
		private Date genymdhms;
		private Date modymdhms;
		private final String pszqint = "102";
	}
	// Good
	class Customer {
		private Date generationTimestamp;
		private Date modificationTimestamp;
		private final String recordId = "102";
	}
	```
	</details>
- 검색하기 쉬운 이름을 사용하라
	- 상수는 static final과 같이 정의해 쓰자.
	- 변수 이름의 길이는 변수의 범위에 비례해서 길어진다.
- 인코딩(변수에 부가 정보를 덧붙여 표기하는 것)을 피하라
	- 헝가리안 표기법
		- 변수명에 해당 변수의 타입(String, Int 등)을 적지 말자
	- 멤버 변수 접두어
		- 멤버 변수 접두어를 붙이지 말자.
	- 인터페이스와 구현
		- 인터페이스 클래스와 구현 클래스를 나눠야 한다면 구현 클래스의 이름에 정보를 인코딩하자.

|       | Interface class | Concrete(Implementation) class |
| ----- | --------------- | ------------------------------ |
| Don't | IShapeFactory   | ShapeFactory    |
| Do    | ShapeFactory    | ShapeFactoryImp |
| Do    | ShapeFactory    | CShapeFactory   |

- 자신의 기억력을 자랑하지 마라.
	- 독자가 머리속으로 한번 더 생각해 변환해야 할만한 변수명을 쓰지 말라.(예: URL에서 호스트와 프로토콜을 제외한 소문자 주소를 r이라는 변수로 명명하는 일 등)
	- 똑똑한 프로그래머와 전문가 프로그래머를 나누는 기준 한가지는 "Clarity(명료함)"이다.
- 클래스 이름
	- 명사 혹은 명사구를 사용하라.(Customer, WikiPage, Account, AddressParser)
	- Manager, Processor, Data, Info와 같은 단어는 피하자.
	- 동사는 사용하지 않는다.
- 메서드 이름
	- 동사 혹은 동사구를 사용하라.(postPayment, deletePayment, deletePage, save 등)
	- 접근자, 변경자, 조건자는 get, set, is로 시작하자.(추가: should, has 등도 가능)
	- 생성자를 오버로드할 경우 정적 팩토리 메서드를 사용하고 해당 생성자를 private으로 선언한다.
	```java
	// 첫번째 보다 두 번째 방법이 더 좋다.
	Complex fulcrumPoint = new Complex(23.0);  
	Complex fulcrumPoint = Complex.FromRealNumber(23.0);  
	```
- 기발한 이름은 피하라
	- 특정 문화에서만 사용되는 재미있는 이름보다 의도를 분명히 표현하는 이름을 사용하라
		- HolyHandGrenade → DeleteItems
		- whack() → kill()
- 한 개념에 한 단어를 사용하라
	- 추상적인 개념 하나에 단어 하나를 사용하자.
		- fetch, retrieve, get 중에서 하나만 사용하자.
		- controller, manager, driver 중에서 하나만 사용하자.
- 말장난을 하지 마라.
	- 한 단어를 두 가지 목적으로 사용하지 말자. 아래와 같은 경우에는 2)를 append 혹은 insert로 바꾸는게 옳겠다.
	```java
	public static String add(String message, String messageToAppend)	// 1)
	public List<Element> add(Element element)							// 2)
	```
- 해법 영역(Solution Domain) 용어를 사용하자
	- 개발자라면 알고 있을 JobQueue, AccountVisitor(Visitor pattern) 등을 사용하지 않을 이유는 없다. 전산용어, 알고리즘 이름, 패턴 이름, 수학 용어 등은 사용하자.
- 문제 영역(Problem Domain) 용어를 사용하자
	- 적절한 프로그래머(=해법 영역) 용어가 없거나 문제 영역과 관련이 깊은 용어의 경우 문제 영역 용어를 사용하자.
- 의미 있는 맥락을 추가하라
	- 클래스, 함수, namespace 등으로 감싸서 맥락(Context)을 표현하라.
	- 그래도 불분명하다면 접두어를 사용하자.
	<details>
	<summary>예제</summary>
		
	```java
	// Bad
	private void printGuessStatistics(char candidate, int count) {
		String number;
		String verb;
		String pluralModifier;

		if (count == 0) {
			number = "no";
			verb = "are";
			pluralModifier = "s";
		} else if (count == 1) {
			number = "1";
			verb = "is";
			pluralModifier = "";
		} else {
			number = Integer.toString(count);
			verb = "are";
			pluralModifier = "s";
		}
		String guessMessage = String.format("There %s %s %s%s", verb, number, candidate, pluralModifier );

		print(guessMessage);
	}

	// Good
	public class GuessStatisticsMessage {
		private String number;
		private String verb;
		private String pluralModifier;

		public String make(char candidate, int count) {
			createPluralDependentMessageParts(count);
			return String.format("There %s %s %s%s", verb, number, candidate, pluralModifier );
		}

		private void createPluralDependentMessageParts(int count) {
			if (count == 0) {
				thereAreNoLetters();
			} else if (count == 1) {
				thereIsOneLetter();
			} else {
				thereAreManyLetters(count);
			}
		}

		private void thereAreManyLetters(int count) {
			number = Integer.toString(count);
			verb = "are";
			pluralModifier = "s";
		}

		private void thereIsOneLetter() {
			number = "1";
			verb = "is";
			pluralModifier = "";
		}

		private void thereAreNoLetters() {
			number = "no";
			verb = "are";
			pluralModifier = "s";
		}
	}
	```
	</details>
- 불필요한 맥락을 없애라
	- Gas Station Delux이라는 어플리케이션을 작성한다고 해서 클래스 이름의 앞에 GSD를 붙이지는 말자. G를 입력하고 자동완성을 누를 경우 모든 클래스가 나타나는 등 효율적이지 못하다. Gas Station Delux의 주소일 경우 GSDAccountAddress 대신 Address라고만 해도 충분하다.


## 함수
- 작게 만들어라!
- 블록과 들여쓰기
	- 중첩 구조(if/else, while문 등)에 들어가는 블록은 한 줄이어야 한다.
	- 각 함수별 들여쓰기 수준이 2단을 넘어서지 않고 각 함수가 명백하다면 함수는 더욱 읽고 이해하기 쉬워진다.
- 한 가지만 해라!
	- 함수를 여러 섹션으로 나눌 수 있다면 그 함수는 여러 작업을 하는 것이다.
- 서술적인 이름(=동사)을 사용하라!
- 함수 인수는 단항 또는 객체 인수로 변경하여 단순화한다.
- 한 가지만 해야 하므로 부수 효과를 일으키지 마라!
- 명령과 조회를 분리(예: list, writeFrom, writeProc 등)하라!
- 오류 코드보다 예외를 사용하라!(API도 고려할 것)
	<details>
	<summary>예제</summary>
	
	```java
	// Before
	if (deletePage(page) == E_OK) {
		if (registry.deleteReference(page.name) == E_OK) {
			if (configKeys.deleteKey(page.name.makeKey()) == E_OK) {
				logger.log("page deleted");
			}
			else {
				logger.log("configKey not deleted");
			}
		}
		else {
			logger.log("deleteReference from registry failed"); 
		} 
	}
	else {
		logger.log("delete failed"); return E_ERROR;
	}
	
	// After
	// try/catch를 사용하면 오류 처리 코드가 원래 코드에서 분리되므로 코드가 깔끔해 진다.
	public void delete(Page page) {
		try {
			deletePageAndAllReferences(page);
		}
		catch (Exception e) {
			logError(e);
		}
	}

	private void deletePageAndAllReferences(Page page) throws Exception { 
		deletePage(page);
		registry.deleteReference(page.name); 
		configKeys.deleteKey(page.name.makeKey());
	}
	private void logError(Exception e) { 
		logger.log(e.getMessage());
	}
	```
	</details>
- 반복(=중복 코드) 하지마라!
- 구조적 프로그래밍에서 함수는 하나의 리턴만 있어야 하며 break나 continue는 지양하고 goto는 절대 사용하지 않는다.


## 주석
- 주석은 나쁜 코드를 보완하지 못한다
- 주석이 아닌 코드로 의도를 표현하라
- 좋은 주석
	- 법적인(=저작권 등) 주석
	- 정보를 제공하는 주석
	- 의미를 설명하는 주석
	- 의미를 명료하게 밝히는 주석
	- 결과를 경고하는 주석
	- TODO 주석
	- 중요성을 강조하는 주석
	- 공개 API에서 Java Docs
- 나쁜 주석
	- 주절거리는 주석
	- 같은 이야기를 중복하는 주석
	- 오해할 여지가 있는 주석
	- 의무적으로 다는 주석
			<details>
			<summary>예제</summary>
	
			```java
			/**
			 *
			* @param title CD 제목
			* @param author CD 저자
			* @param tracks CD 트랙 숫자
			* @param durationInMinutes CD 길이(단위: 분)
			*/
			public void addCD(String title, String author, int tracks, int durationInMinutes) {
				CD cd = new CD();
				cd.title = title;
				cd.author = author;
				cd.tracks = tracks;
				cd.duration = durationInMinutes;
				cdList.add(cd);
			}
			```
			</details>
	- 이력을 기록하는 주석(소스 코드 관리 시스템의 이력 vs. 인라인 주석의 이력)
	- 있으나 마나 한 주석
			<details>
			<summary>예제</summary>
	
			```java
			/*
			* 기본 생성자
			*/
			protected AnnualDateRule() {

			}
			</details>
	- 무서운 잡음
	```java
	// UnNice
	// KOLO? 멍청한 개발자
	```
	- 함수나 변수로 표현할 수 있다면 주석을 달지 마라
	```java
	// Bad
	// 전역 목록 <smodule>에 속하는 모듈이 우리가 속한 하위 시스템에 의존하는가?
	if (module.getDependSubsystems().contains(subSysMod.getSubSystem()))
	
	// Good
	ArrayList moduleDependees	= smodule.getDependSubsystems();
	String ourSubSystem			= subSysMod.getSubSystem();
	if (moduleDependees.contains(ourSubSystem))
	```
	- 위치를 표시하는 주석
	```java
	// Actions /////////////////////////////////////////////
	```
	- 닫는 괄호에 다는 주석
	- 공로를 돌리거나 저자를 표시하는 주석
	- 주석으로 처리한 코드(참고: 필요한 주석 vs. 불필요한 주석)
		<details>
		<summary>예제</summary>

		```java
		this.bytePos = writeBytes(pngIdBytes, 0);
		//hdrPos = bytePos;
		writeHeader();
		writeResolution();
		//dataPos = bytePos;
		if (writeImageData()) {
			wirteEnd();
			this.pngBytes = resizeByteArray(this.pngBytes, this.maxPos);
		} else {
			this.pngBytes = null;
		}
		return this.pngBytes;
		```
		</details>
	- HTML 주석
	- 너무 많은 정보
	- 비공개 코드에서 Java Docs


## 형식 맞추기
- 형식을 맞추는 목적
- 적절한 행 길이(=코드의 세로 길이)를 유지하라
	- 신문 기사(제목, 개요, 목차 등)처럼 작성하라
	- 개념은 빈 행으로 분리하라
		<details>
		<summary>예제</summary>

		```java
		// 빈 행을 넣지 않을 경우
		package fitnesse.wikitext.widgets;
		import java.util.regex.*;
		public class BoldWidget extends ParentWidget {
			public static final String REGEXP = "'''.+?'''";
			private static final Pattern pattern = Pattern.compile("'''(.+?)'''",
				Pattern.MULTILINE + Pattern.DOTALL);
			public BoldWidget(ParentWidget parent, String text) throws Exception {
				super(parent);
				Matcher match = pattern.matcher(text); match.find(); 
				addChildWidgets(match.group(1));}
			public String render() throws Exception { 
				StringBuffer html = new StringBuffer("<b>"); 		
				html.append(childHtml()).append("</b>"); 
				return html.toString();
			} 
		}
		```
		```java
		// 빈 행을 넣을 경우
		package fitnesse.wikitext.widgets;

		import java.util.regex.*;

		public class BoldWidget extends ParentWidget {
			public static final String REGEXP = "'''.+?'''";
			private static final Pattern pattern = Pattern.compile("'''(.+?)'''", 
				Pattern.MULTILINE + Pattern.DOTALL
			);
			
			public BoldWidget(ParentWidget parent, String text) throws Exception { 
				super(parent);
				Matcher match = pattern.matcher(text);
				match.find();
				addChildWidgets(match.group(1)); 
			}
			
			public String render() throws Exception { 
				StringBuffer html = new StringBuffer("<b>"); 
				html.append(childHtml()).append("</b>"); 
				return html.toString();
			} 
		}
		```
		</details>
	- 세로 밀집도와 거리
	- 세로 순서
- 가로 형식(=코드의 가로 길이) 맞추기
	- 들여쓰기
	- 가로 밀집도와 공백
	- 가로 정렬
		<details>
		<summary>예제</summary>

		```java
		// Code Formatter 대부분들은 이렇게 해놔봤자 무시하고 원래대로 돌려놓는다. vs. 선언문과 할당문을 별도로 정렬할 필요가 없다.
		public class FitNesseExpediter implements ResponseSender {
		private		Socket			socket;
		private 	InputStream		input;
		private 	OutputStream 	output;
		private 	Reques			request; 		
		private 	Response 		response;	
		private 	FitNesseContex	context; 
		protected 	long			requestParsingTimeLimit;
		private 	long			requestProgress;
		private 	long			requestParsingDeadline;
		private 	boolean			hasError;
		... 
		```
		</details>
- 팀 규칙


## 자료 구조와 객체형
- 절차 지향적 객체 and 다형 지향적 객체
	<details>
	<summary>절차 지향적 객체</summary>
		
	```java
	public class Square { 
		public Point topLeft; 
		public double side;
	}

	public class Rectangle { 
		public Point topLeft; 
		public double height; 
		public double width;
	}

	public class Circle { 
		public Point center; 
		public double radius;
	}

	public class Geometry {
		public final double PI = 3.141592653589793;
		
		public double area(Object shape) throws NoSuchShapeException {
			if (shape instanceof Square) { 
				Square s = (Square)shape; 
				return s.side * s.side;
			} else if (shape instanceof Rectangle) { 
				Rectangle r = (Rectangle)shape; 
				return r.height * r.width;
			} else if (shape instanceof Circle) {
				Circle c = (Circle)shape;
				return PI * c.radius * c.radius; 
			}
			throw new NoSuchShapeException(); 
		}
	}
	```
	</details>
	<details>
	<summary>다형 지향적 객체</summary>

	```java
	public class Square implements Shape { 
		private Point topLeft;
		private double side;
		
		public double area() { 
			return side * side;
		} 
	}

	public class Rectangle implements Shape { 
		private Point topLeft;
		private double height;
		private double width;
		
		public double area() { 
			return height * width;
		} 
	}

	public class Circle implements Shape { 
		private Point center;
		private double radius;
		public final double PI = 3.141592653589793;

		public double area() {
			return PI * radius * radius;
		} 
	}
	```
	</details>
- 절차 지향적 객체 vs. 다형 지향적 객체
	- 도형의 둘레(method)를 추가할 경우
		- 절차 지향적 객체: 1) Geometry에 perimeter()만 추가
		- 다형 지향적 객체: 1) 도형(class) 추가 2) `모든 도형 클래스에 perimeter()를 추가`
	- 도형을 추가(class)할 경우
		- 절차 지향적 객체: 1) 도형(class) 추가 2) `Geometry의 모든 method 수정`
		- 다형 지향적 객체: 1) 도형(class)만 추가
- DTO vs. VO

| 구분 | DTO (Data Transfer Object) | VO (Value Object) |
|------|----------------------------|-------------------|
| 목적 | 계층 간 데이터 전달 | 값을 표현하는 객체 |
| 식별자(ID) | 있을 수도 있고 없을 수도 있음 | 식별자가 없음 |
| 변경 가능성 | 보통 변경 가능(Mutable) | 일반적으로 불변(Immutable) |
| 비교 기준 | 객체의 참조 또는 ID | 객체가 가진 값 자체 |
| 비즈니스 로직 | 거의 없음 | 값과 관련된 간단한 로직 포함 가능 |


## Summary
<details>
<summary>예제</summary>

```java
/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2026 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2026 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.knowledge
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Hello.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20260702114900][pluto#plutozone.com][CREATE: Initial Release]
 */

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2026-07-02
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class Hello {
	
	public static void main(String[] args) {
		
		System.out.println("Hello, Java");
		
	}

}
```
</details>