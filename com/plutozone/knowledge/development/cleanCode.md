# com.plutozone.knowledge.development.CleanCode


## Overview
### 출처
- https://github.com/Yooii-Studios/Clean-Code


## Contents
- [클린 코드](#클린-코드)
- [의미 있는 이름](#의미-있는-이름)
- 함수


## 클린 코드
- 컴퓨터 언어의 추상화(고급 또는 저급 언어의 관점) 레벨이 높아지고 도메인에 특화된 언어일지라도 코드(Code)는 사라지지 않는다.
- 빨리 가기 위한 단 하나의 방법은 "최대한 깨끗한 코드를 항상 유지하는 것"이다.
- 클린 코드란?
	- “Big” Dave Thomas, founder of OTI, godfather of the Eclipse strategy
		- 다른 이가 수정하기 쉬워야 한다.
		- 테스트를 해야 한다.
		- 코드는 간결할수록 좋다.(Smaller is better)
		- 코드는 세련되어야 한다.
	- Michael Feathers, author of Working Effectively with Legacy Code
		- 코드를 Care하라.(=주의와 관심을 가지고 작성하라.)
	- Ron Jeffries, author of Extreme Programming Installed and Extreme Programming Adventures in C#
		- 중복을 없애라.
		- 클래스/메서드는 한 가지 일만 하게 하라.
		- 메서드의 이름 등으로 코드가 하는 일을 명시하라.
		- (메서드 등을) 일찍 추상화해서 프로젝트를 빠르게 진행할 수 있게 하라.
- 프로그래머는 작가다. 코드는 글과 같다.
	- 실제로 읽기와 쓰기에 들이는 시간은 대략 10:1 정도이다. 새 코드를 작성하기 위해서는 옛 코드들을 읽어야 하기 때문이다.
	- 그러므로 빨리 가고 싶다면! 쉽게 코드를 작성하고 싶다면! "읽기 쉽게 작성하라".


## 의미 있는 이름
- 의도를 분명히 밝혀라
```java
// Bad
int d; // elapsed time in days
// Good
int elapsedTimeInDays;
```
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
- 검색하기 쉬운 이름을 사용하라
	- 상수는 static final과 같이 정의해 쓰자.
	- 변수 이름의 길이는 변수의 범위에 비례해서 길어진다.
- 인코딩(변수에 부가 정보를 덧붙여 표기하는 것)을 피하라
	- 헝가리안 표기법
		- 변수명에 해당 변수의 타입(String, Int 등)을 적지 말자
	- 맴버 변수 접두어
		- 맴버 변수 접두어를 붙이지 말자.
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
- 불필요한 맥락을 없애라
	- Gas Station Delux이라는 어플리케이션을 작성한다고 해서 클래스 이름의 앞에 GSD를 붙이지는 말자. G를 입력하고 자동완성을 누를 경우 모든 클래스가 나타나는 등 효율적이지 못하다. Gas Station Delux의 주소일 경우 GSDAccountAddress 대신 Address라고만 해도 충분하다.


## 함수
- 작게 만들어라!
함수를 만들 때 최대한 ‘작게!’ 만들어라.
	- 블록과 들여쓰기
	중첩구조(if/else, while문 등)에 들어가는 블록은 한 줄이어야 한다. 각 함수별 들여쓰기 수준이 2단을 넘어서지 않고 각 함수가 명백하다면 함수는 더욱 읽고 이해하기 쉬워진다.
- 한 가지만 해라!
	- 함수 내 섹션
- 함수 당 추상화 수준은 하나로!
	- 위에서 아래로 코드 읽기 : 내려가기 규칙
- Switch문
- 서술적인 이름을 사용하라!
- 함수 인수
	- 많이 쓰는 단항 형식
	- 플래그 인수
	- 이항 함수
	- 삼항 함수
	- 인수 객체
	- 인수 목록
	- 동사와 키워드
- 부수 효과를 일으키지 마라!
	- 출력 인수
- 명령과 조회를 분리하라!
- 오류코드보다 예외를 사용하라!
	- Try/Catch 블록 뽑아내기
	- 오류 처리도 한 가지 직업이다
	- Error.java의 의존성 자석
- 반복하지마라!
- 구조적 프로그래밍
- 함수를 어떻게 짜죠?