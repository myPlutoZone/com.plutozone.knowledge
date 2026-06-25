# com.plutozone.knowledge.development.Refactoring


## TODO
- 저작권 및 변경 이력 주석


## Overview
### 계획(안)-우선 순위 기준
- [x 시간] 과정 및 과목 분석 그리고 Prior Knowledge
	- 소개
	- 과정 및 교과목
		- 과정명: `융합` 머신 비전을 활용한 AI 기반의 첨단 제조 분야 제어 SW 개발 과정-2차(2025-12-22 ~ 2026-07-21)
		- 교과목: `https://docs.google.com/spreadsheets/...`
			- ...
			- **[120 시간] 리팩토링과 고도화**
				- 리팩토링 개론과 최적화
				- 객체와 일반화 리팩토링
			- ...
	- 교육생 및 팀 프로젝트를 참고하여 의견 문의
	- 환경 설정
		- Visual Studio 20xx vs. Code 등 + Notepad 등
		- Git 및 GitLab 설치
- [y 시간] 과정 마무리 지원
	- 프로젝트 결과물 및 포트폴리오 개선
- [60 - x 시간] 리팩토링
	- 리팩토링 개론 그리고 원칙
	- 리팩토링 대상과 검증 환경 
	- 기본적인 리팩토링 카탈로그, 캡슐화와 상속 그리고 API
	- 기능 관리와 데이터 조직화 그리고 로직 간소화
- [60 - y 시간] 고도화
	- 요구 사항, 분석, 설계, 구현, 검증 및 상용화 그리고 자동화(AI 활용 등)
	- 기능적 또는 비기능적(성능, 보안 등) 요구 사항
	- 소프트웨어 개발 및 운영 환경(OS + Network + Service + Build + Deploy or Install/Setup 등)
	- 데이터와 통신 암호화
	- CI/CD
	- Convergence(Web or Mobile or Hybrid)
	- OpenCV, YOLO 등 고도화(GPU 적용, 버전업, DETR/RT-DETR 등)
- 참고
	- 온도와 습도 그리고 먼지
	- [OpenCV + YOLO](./image/refactoring_01.jpg)
	- [IoT](./image/refactoring_02.png)

### Prior Knowledge
- Information Technology
	- Domain(도메인=영역) 전문가
	- 소프트웨어 공학 vs. 이학
	- Bit, Byte, Tab vs. Space, ASCII vs. Binary, ...
- Programming
	- 컴파일러와 달리 사람은 `코드의 미적 상태`에 대해 민감
	- [표준 개발 가이드](./README.md) vs. 클린 코드(https://github.com/Yooii-Studios/Clean-Code) vs. 리팩토링(this)
	- 프로그램, 프로그래밍, 프로그래머, 프로그래밍 언어 vs. 프레임웍 vs. 플랫폼
	- GW-BASIC, C/C++, Fortran, COBOL, JavaScript, BASIC, Pascal, PHP, C#, Java, Ruby, Python 등의 권장 코딩 스타일
	- 컴파일, 컴파일러, 인터프리터, 파서, 런타임, 버그, 디버깅
	- 객체 지향, 객체 지향 언어 vs. 절차 지향 언어
	- 변수, 함수, 클래스, 상속, 수퍼(부모) 클래스, 서브(자식) 클래스
	- 오버라이딩, 오버로딩
	- 멤버 필드/속성, 멤버 함수/메서드

## Contents
1. 리팩토링 개론 그리고 원칙
2. 리팩토링 대상과 검증 환경 
3. 기본적인 리팩토링 카탈로그, 캡슐화와 상속 그리고 API
4. 기능 관리와 데이터 조직화 그리고 로직 간소화


## 1. 리팩토링 개론 그리고 원칙
### 1-1. 개론
- 리팩토링이란?
	- 리팩토링은 스몰토크 커뮤니티에서 처음 시작
	- 겉으로 드러나는 코드의 기능(=겉보기 동작)은 바꾸지 않으면서 내부 구조를 개선하는 방식으로 소프트웨어 시스템을 수정하는 과정
	- 버그가 생길 가능성을 최소로 줄이면서 정리하는 정제된 방법
	- 코드를 작성하고 난 뒤에 구조(=설계)를 개선
	- 리팩토링의 위험성 때문에 계획적이며 체계적으로 수행
	- 리팩토링은 전문가 필요
	- **리팩토링(Refactoring)=구조 개선 and 개조/새단장(Remodeling) vs. 재건축(Reconstruction) at 건축**
	- 예시
		- 수퍼 클래스를 통한 서브 클래스의 메서드 중복 제거
		- 일부 코드를 이동하여 별도의 메서드로 생성
		- 어떤 클래스의 필드를 다른 클래스 이동
- 마틴 파울러(Martin Fowler)
	- *"컴퓨터가 이해할 수 있는 코드는 누구나 짤 수 있습니다. 사람이 이해할 수 있는 코드를 짜는 게 훌륭한 프로그래머입니다."* at Refactoring 2nd Edition
	- 수석 과학자 at ThoughtWorks
	- 제어 역전(Inversion of Control)과 의존성 주입(Dependency Injection) 용어를 대중화 and 애자일 소프트웨어 개발 선언 공동 작성자
- [실습] 외주를 전문으로 하는 극단에서 공연할 수 있는 연극의 종류와 공연장(고객) 공연 시 공연료를 계산하는 프로그램
	- `Node 기반 JavaScript 소스들`
		- plays.json(연극 정보)
		```json
		{
			"hamlet":		{"name": "Hamlet"			, "type": "tragedy"}
			, "as-Like":	{"name": "As You Like It"	, "type": "comedy"}
			, "othello":	{"name": "Othello"			, "type": "tragedy"}
		}
		```
		- invoices.json(공연장 정보)
		```json
		[
			{
				"customer": "BigCo",
				"performances": [
					{
						"playID": "hamlet",
						"audience": 55
					},
					{
						"playID": "as-Like",
						"audience": 35
					},
					{
						"playID": "othello",
						"audience": 40
					}
				]
			}
		]
		```
		- statement.js(공연료 계산)
		```js
		function statement(invoice, plays) {
			let totalAmount = 0;
			let volumeCredits = 0;
			let result = `청구 내역(고객명: ${invoice.customer})\n`;
			const format = new Intl.NumberFormat("en-US",
								{ style:"currency", currency: "USD"
									, minimumFractionDigits: 2}).format;
			
			for (let perf of invoice.performances) {
				const play = plays[perf.playID];
				let thisAmount = 0;
				
				switch (play.type) {
					case "tragedy": // 비극
						thisAmount = 40000;
						if (perf.audience > 30) {
							thisAmount += 1000 * (perf.audience - 30);
						}
						break;
					case "comedy": // 희극
						thisAmount = 30000;
						if (perf.audience > 20) {
							thisAmount += 10000 + 500 * (perf.audience - 20);
						}
						thisAmount += 300 * perf.audience;
						break;
					default:
						throw new Error(`알 수 없는 장르: ${play.type}`);
				}
				
				// 포인트 적립
				volumeCredits += Math.max(perf.audience - 30, 0);
				// 희극 관객 5명마다 추가 포인트 제공
				if ("comedy" === play.type) volumeCredits += Math.floor(perf.audience / 5);
				
				// 청구 내역 출력
				result += `${play.name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;
				totalAmount += thisAmount;
			}
			
			result += `총액 ${format(totalAmount/100)}\n`;
			result += `적립 포인트: ${volumeCredits}점\n`;
			return result;
		}
		
		module.exports = statement;
		```
		- index.js
		```js
		const plays = require("./plays.json");
		const invoices = require("./invoices.json");
		const statement = require("./statement");
		
		console.log(
			statement(invoices[0], plays)
		);
		```
	- `예상되는 개선 사항들`
		- 청구 내역에 대한 HTML 등 디자인
		- 연극, 공연장 정보 확장 그리고 정책에 따른 계산 로직 변경
		- `[참고] 필요 시 브라우저 기반으로 JavaScript를 개선 또는 다른 언어 기반으로 리뉴얼`
	- [Refactorings] `검증`
		- 리팩토링 전과 후의 기능에 대한 검증 자동화(예: 리팩토링 전과 후의 청구 내역 문자열 자동 비교 프로그램 제작)
	- [Refactorings] `함수 추출`(로직 분석을 통해 분리할 수 있는 부분 찾기 예: 연극 타입에 따른 계산을 처리하는 switch)
		- amountFor.js: 명명(예: 변수, 함수 등) + 유효 범위(예: 변수 등)와 매개 변수
		```js
		function amountFor(perf, play) {	// [명명] 함수, 매개변수
			let thisAmount = 0;			// [유효 범위] 변수 초기화
			
			switch (play.type) {
				case "tragedy": // 비극
					thisAmount = 40000;
					if (perf.audience > 30) {
						thisAmount += 1000 * (perf.audience - 30);
					}
					break;
				case "comedy": // 희극
					thisAmount = 30000;
					if (perf.audience > 20) {
						thisAmount += 10000 + 500 * (perf.audience - 20);
					}
					thisAmount += 300 * perf.audience;
					break;
				default:
					throw new Error(`알 수 없는 장르: ${play.type}`);
			}
			
			return thisAmount;		// 값 반환
		}
		
		module.exports = amountFor;
		```
		- statement.js
		```js
		const amountFor = require("./amountFor");
		
		function statement(invoice, plays) {
			let totalAmount = 0;
			let volumeCredits = 0;
			let result = `청구 내역(고객명: ${invoice.customer})\n`;
			const format = new Intl.NumberFormat("en-US",
								{ style:"currency", currency: "USD"
									, minimumFractionDigits: 2}).format;
			
			for (let perf of invoice.performances) {
				const play = plays[perf.playID];
				
				let thisAmount = amountFor(perf, play);		// 함수 추출
				/*
				let thisAmount = 0;
				
				switch (play.type) {
					case "tragedy": // 비극
						thisAmount = 40000;
						if (perf.audience > 30) {
							thisAmount += 1000 * (perf.audience - 30);
						}
						break;
					case "comedy": // 희극
						thisAmount = 30000;
						if (perf.audience > 20) {
							thisAmount += 10000 + 500 * (perf.audience - 20);
						}
						thisAmount += 300 * perf.audience;
						break;
					default:
						throw new Error(`알 수 없는 장르: ${play.type}`);
				}
				*/
				
				// 포인트 적립
				volumeCredits += Math.max(perf.audience - 30, 0);
				// 희극 관객 5명마다 추가 포인트 제공
				if ("comedy" === play.type) volumeCredits += Math.floor(perf.audience / 5);
				
				// 청구 내역 출력
				result += `${play.name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;
				totalAmount += thisAmount;
			}
			
			result += `총액 ${format(totalAmount/100)}\n`;
			result += `적립 포인트: ${volumeCredits}점\n`;
			return result;
		}
		
		module.exports = statement;
		```
	- [Refactorings] `단계별 진행 후 수시로 검증 후 commit` + `완료 후 push`
	- [Refactorings] `함수 추출 by IDE`
	- [Refactorings] amountFor.js: 재명명(예: 변수, 매개변수 등) for `자료형`(원시, 객체 등 또는 a/an, the 등 관사)
	```js
	function amountFor(aPerformance, play) {	// [재명명] aPerformance and JavaScript는 동적 타입 언어
	//function amountFor(perf, play) {
		let result		= 0
		//let thisAmount	= 0;
				
		switch (play.type) {
			case "tragedy": // 비극
				result = 40000;
				if (aPerformance.audience > 30) {
					result += 1000 * (aPerformance.audience - 30);
				}
				break;
			case "comedy": // 희극
				result = 30000;
				if (aPerformance.audience > 20) {
					result += 10000 + 500 * (aPerformance.audience - 20);
				}
				result += 300 * aPerformance.audience;
				break;
			default:
				throw new Error(`알 수 없는 장르: ${play.type}`);
		}
		
		return result;
		//return thisAmount;		// 값 반환
	}
	
	module.exports = amountFor;
	```
	- [Refactorings] 단계별 진행 후 수시로 검증 후 commit + 완료 후 push
	- [Refactorings] playFor.js: `임의 변수를 질의 함수로 바꾸기(1)`
	```js
	const plays = require("./plays.json");

	function playFor(aPerformance) {
		return plays[aPerformance.playID];
	}
	
	module.exports = playFor;
	```
	- [Refactorings] statement.js: `임의 변수를 질의 함수로 바꾸기(2)`
	```js
	const playFor = require("./playFor");
	const amountFor = require("./amountFor");
	
	function statement(invoice, plays) {
		let totalAmount = 0;
		let volumeCredits = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		const format = new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format;
		
		for (let perf of invoice.performances) {
			
			const play = playFor(perf);		// [임의 변수를 질의 함수로 바꾸기(2)]
			//const play = plays[perf.playID];
			
			let thisAmount = amountFor(perf, play);
			/*
			let thisAmount = 0;
			
			switch (play.type) {
				case "tragedy": // 비극
					thisAmount = 40000;
					if (perf.audience > 30) {
						thisAmount += 1000 * (perf.audience - 30);
					}
					break;
				case "comedy": // 희극
					thisAmount = 30000;
					if (perf.audience > 20) {
						thisAmount += 10000 + 500 * (perf.audience - 20);
					}
					thisAmount += 300 * perf.audience;
					break;
				default:
					throw new Error(`알 수 없는 장르: ${play.type}`);
			}
			*/
			
			// 포인트 적립
			volumeCredits += Math.max(perf.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === play.type) volumeCredits += Math.floor(perf.audience / 5);
			
			// 청구 내역 출력
			result += `${play.name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;
			totalAmount += thisAmount;
		}
		
		result += `총액 ${format(totalAmount/100)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		return result;
	}
	
	module.exports = statement;
	```
	- [Refactorings] statement.js and amountFor.js: `변수 인라인` and 지역 변수 vs. 반복적 함수 호출
	```js
	const playFor = require("./playFor");
	const amountFor = require("./amountFor");
	
	function statement(invoice, plays) {
		let totalAmount = 0;
		let volumeCredits = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		const format = new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format;
		
		for (let perf of invoice.performances) {
			
			let thisAmount = amountFor(perf, playFor(perf));					// [변수 인라인]
			
			// 포인트 적립
			volumeCredits += Math.max(perf.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(perf).type) volumeCredits += Math.floor(perf.audience / 5);	// [변수 인라인]
			//if ("comedy" === play.type) volumeCredits += Math.floor(perf.audience / 5);
			
			// 청구 내역 출력
			result += `${playFor(perf).name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;	// [변수 인라인]
			//result += `${play.name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;
			totalAmount += thisAmount;
		}
		
		result += `총액 ${format(totalAmount/100)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		return result;
	}
	
	module.exports = statement;
	```
	```js
	const playFor = require("./playFor");

	function amountFor(aPerformance, play) {
		let result = 0
				
		switch (playFor(aPerformance).type) {
			case "tragedy": // 비극
				result = 40000;
				if (aPerformance.audience > 30) {
					result += 1000 * (aPerformance.audience - 30);
				}
				break;
			case "comedy": // 희극
				result = 30000;
				if (aPerformance.audience > 20) {
					result += 10000 + 500 * (aPerformance.audience - 20);
				}
				result += 300 * aPerformance.audience;
				break;
			default:
				throw new Error(`알 수 없는 장르: ${playFor(aPerformance).type}`);
		}
		
		return result;
	}
	
	module.exports = amountFor;
	```
	- [Refactorings] statement.js and amountFor.js: `함수 선언 바꾸기`
	```js
	...
	let thisAmount = amountFor(perf);
	//let thisAmount = amountFor(perf, playFor(perf));
	...
	```
	```js
	...
	function amountFor(aPerformance) {
	//function amountFor(aPerformance, play) {
	...
	```
	- [Refactorings] statement.js: `변수 인라인`
	```js
	const playFor = require("./playFor");
	const amountFor = require("./amountFor");
	
	function statement(invoice, plays) {
		let totalAmount = 0;
		let volumeCredits = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		const format = new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format;
		
		for (let perf of invoice.performances) {
			
			// let thisAmount = amountFor(perf);
			
			// 포인트 적립
			volumeCredits += Math.max(perf.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(perf).type) volumeCredits += Math.floor(perf.audience / 5);
			//if ("comedy" === play.type) volumeCredits += Math.floor(perf.audience / 5);
			
			// 청구 내역 출력
			result += `${playFor(perf).name}: ${format(amountFor(perf)/100)} (${perf.audience}석)\n`;	// [변수 인라인]
			//result += `${play.name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;
			totalAmount += amountFor(perf);																	// [변수 인라인]
		}
		
		result += `총액 ${format(totalAmount/100)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		return result;
	}
	
	module.exports = statement;
	```
	- [Refactorings] volumeCreditsFor.js and statement.js: `함수 추출`(적립 포인트 계산)
	```js
	const playFor = require("./playFor");

	function volumeCreditsFor(aPerformance) {
		
		let result = 0;
		
		// 포인트 적립
		result += Math.max(aPerformance.audience - 30, 0);
		// 희극 관객 5명마다 추가 포인트 제공
		if ("comedy" === playFor(aPerformance).type) result += Math.floor(aPerformance.audience / 5);
		
		return result;
	}
	
	module.exports = volumeCreditsFor;
	```
	```js
	const volumeCreditsFor = require("./volumeCreditsFor");
	const playFor = require("./playFor");
	const amountFor = require("./amountFor");
	
	function statement(invoice, plays) {
		let totalAmount = 0;
		let volumeCredits = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		const format = new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format;
		
		for (let perf of invoice.performances) {
			
			volumeCredits += volumeCreditsFor(perf)		// [함수 호출]
			
			// 청구 내역 출력
			result += `${playFor(perf).name}: ${format(amountFor(perf)/100)} (${perf.audience}석)\n`;
			totalAmount += amountFor(perf);
		}
		
		result += `총액 ${format(totalAmount/100)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		return result;
	}
	
	module.exports = statement;
	```

### 1-2. 리팩토링 원칙

## 2. 리팩토링 대상과 검증 환경 구축
### 2-1. 리팩토링 대상

### 2-2. 검증 환경 구축


## 3. 기본적인 리팩토링 카탈로그, 캡슐화와 상속 그리고 API
### 3-1. 기본적인 리팩토링
### 3-2. 갭슐화
### 3-3. 상속
### 3-4. API

## 4. 기능 관리와 데이터 조직화 그리고 로직 간소화
### 4-1. 기능 관리
### 4-2. 데이터 조직화
### 4-3. 로직 간소화

<!--
![Refactoring 2nd Edition](./image/refactoring_book.jpg)
-->
