# com.plutozone.knowledge.development.Refactoring


## TODO
- 저작권 및 변경 이력 주석


## Overview
### 계획(안)-우선 순위 기준
- [x 시간] 과정 및 과목 분석 그리고 Prior Knowledge
	- 소개 그리고 과정 및 교과목
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
		- Git 및 GitHub + Web Hook(Slack 등) vs. GitLab 설치
- [y 시간] 과정 마무리 지원
	- 프로젝트 결과물 및 포트폴리오 개선
- [60 - x 시간] 리팩토링
	- Refactoring Mini-Project
	- 개론 그리고 원칙
	- 대상과 검증 환경 
	- 기법
- [60 - y 시간] 고도화
	- 요구 사항, 분석, 설계, 구현, 검증(기능과 성능 등) 및 상용화 그리고 자동화(AI 활용 등)
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
	- 프로그램, 프로그래밍, 프로그래머, 프로그래밍 언어 vs. 프레임웍 vs. 플랫폼
	- GW-BASIC, C/C++, Fortran, COBOL, JavaScript, BASIC, Pascal, PHP, C#, Java, Ruby, Python 등의 권장 코딩 스타일
	- [표준 개발 가이드](./README.md) vs. 클린 코드(https://github.com/Yooii-Studios/Clean-Code) vs. 리팩토링(this)
	- 네이밍 및 네이밍 룰과 명사, 동사, 부사 등 그리고 지속적 개선
	- 컴파일, 컴파일러, 인터프리터, 파서, 런타임, 버그, 디버깅 그리고 컴파일러와 달리 사람은 `코드의 미적 상태`에 대해 민감
	- 객체 지향, 객체 지향 언어 vs. 절차 지향 언어
	- 변수, 매개 변수, 임시 변수, 파생 변수, 함수, 변환 함수, 질의 함수, 참조 vs. 값
	- 제어문, 조건문, 반복문, 파이프라인
	- 기본형, 클래스, 객체, 상속, 수퍼(부모) 클래스, 서브(자식) 클래스, 생성자, 위임, 다형성
	- 예외, 오버라이딩, 오버로딩
	- 멤버 필드/속성, 멤버 함수/메서드
	- 오류 코드

## Contents
1. Refactoring Mini-Project
2. 개론 그리고 원칙
3. 대상과 검증 환경 
4. 기법

## 1. Refactoring Mini-Project
외주를 전문으로 하는 극단에서 공연할 수 있는 연극의 종류와 공연장(고객) 공연 시 공연료를 계산하는 프로그램
- `JavaScript 소스들`
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
	- statement.js(공연료 계산, 계산 시는 센트 단위)
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
		
		result += `총액: ${format(totalAmount/100)}\n`;
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
	- 청구 내역에 대한 only Text, HTML 등 디자인
	- 연극, 공연장 정보 확장 그리고 정책에 따른 계산 로직 변경
	- ![Generic badge](https://img.shields.io/badge/참고-브라우저_기반으로_JavaScript를_개선_또는_다른_언어_기반으로_리뉴얼-blue.svg)
- `검증 환경`
	- 리팩토링 전과 후의 기능에 대한 검증 자동화(예: 리팩토링 전과 후의 청구 내역 문자열 자동 비교 프로그램 제작)
	- ![Generic badge](https://img.shields.io/badge/참고-본_과정에서는_실행_결과를_비교함_필요_시_비교_프로그램_제작_예정임-blue.svg)
- `추출` 함수(예: 연극 타입에 따른 계산을 처리하는 switch)
	- statement.js
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
			let thisAmount = amountFor(perf, play);		// 1-1. [함수 추출]
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
		
		result += `총액: ${format(totalAmount/100)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		
		return result;
		
		function amountFor(perf, play) {				// 1-2. [중첩 함수, 매개 변수]
			let thisAmount = 0;							// 1-3. [유효 범위, 변수 초기화]
			
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
			
			return thisAmount;							// 1-4. [값 반환]
		}
	}
	
	module.exports = statement;
	```
- ![Generic badge](https://img.shields.io/badge/중요-수시_검증_및_형상_관리-red.svg) `이하 단계별 진행 후 commit` + `완료 후 push`
- `함수 추출 by IDE`
- `교체` 변수(예: thisAmount), 매개 변수(예: perf) 등 for 자료형(원시, 객체 등 또는 a/an, the 등 관사)
	- statement.js
	```js
	...
	function amountFor(aPerformance, play) {	// 1-1. [매개 변수 교체]
		let result = 0;					// 1-2. [변수 교체]
		
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
	}
	...
	
	```
- `교체` 임시 변수를 질의 함수로(예: const play = playFor(perf))
	- statement.js
	```js
	function statement(invoice, plays) {
		let totalAmount = 0;
		let volumeCredits = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		const format = new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format;
		
		for (let perf of invoice.performances) {
			const play = playFor(perf);						// 1-2. [임시 변수를 질의 함수로 교체]
			// const play = plays[perf.playID];
			let thisAmount = amountFor(perf, play);
			
			// 포인트 적립
			volumeCredits += Math.max(perf.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === play.type) volumeCredits += Math.floor(perf.audience / 5);
			
			// 청구 내역 출력
			result += `${play.name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;
			totalAmount += thisAmount;
		}
		
		result += `총액: ${format(totalAmount/100)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		
		return result;
		
		function playFor(aPerformance) {					// 1-1. [임시 변수를 질의 함수로 교체]
			return plays[aPerformance.playID];
		}
		
		function amountFor(aPerformance, play) {
			let result = 0;
			
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
		}
	}
	
	module.exports = statement;
	```
- `전개(인라인)` 변수(예: const play = playFor(perf))
	- statement.js
	```js
	function statement(invoice, plays) {
		let totalAmount = 0;
		let volumeCredits = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		const format = new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format;
		
		for (let perf of invoice.performances) {
			// const play = playFor(perf);															// 1-3. [변수 전개(인라인) - 불필요]
			let thisAmount = amountFor(perf, play);
			
			// 포인트 적립
			volumeCredits += Math.max(perf.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(perf).type) volumeCredits += Math.floor(perf.audience / 5);	// 1-1. [변수 전개(인라인)]
			// if ("comedy" === play.type) volumeCredits += Math.floor(perf.audience / 5);
			
			// 청구 내역 출력
			result += `${playFor(perf).name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;	// 1-2. [변수 전개(인라인)]
			// result += `${play.name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;
			totalAmount += thisAmount;
		}
		
		result += `총액: ${format(totalAmount/100)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		
		return result;
		
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
		
		function amountFor(aPerformance, play) {
			let result = 0;
			
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
		}
	}
	
	module.exports = statement;
	```
- `교체` 함수 선언(예: amountFor) + `교체` 함수 호출(예: play.type) 그리고 함수 호출 증가에 따른 성능 확인
	- statement.js
	```js
	function statement(invoice, plays) {
		let totalAmount = 0;
		let volumeCredits = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		const format = new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format;
		
		for (let perf of invoice.performances) {
			let thisAmount = amountFor(perf);													// 1-1. [함수 선언 교체]
			//let thisAmount = amountFor(perf, playFor(perf));
			
			// 포인트 적립
			volumeCredits += Math.max(perf.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(perf).type) volumeCredits += Math.floor(perf.audience / 5);
			
			// 청구 내역 출력
			result += `${playFor(perf).name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;
			totalAmount += thisAmount;
		}
		
		result += `총액: ${format(totalAmount/100)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		
		return result;
		
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
		
		function amountFor(aPerformance) {														// 1-1. [함수 선언 교체]
		// function amountFor(aPerformance, play) {
			let result = 0;
			
			switch (playFor(aPerformance).type) {												// 2-1. [함수 호출로 교체]
			// switch (play.type) {
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
					throw new Error(`알 수 없는 장르: ${playFor(aPerformance).type}`);			// 2-2. [함수 호출로 교체]
					// throw new Error(`알 수 없는 장르: ${play.type}`);
			}
			
			return result;
		}
	}
	
	module.exports = statement;
	```
- `전개(인라인)` 변수(예: thisAmount)
	- statement.js
	```js
	function statement(invoice, plays) {
		let totalAmount = 0;
		let volumeCredits = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		const format = new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format;
		
		for (let perf of invoice.performances) {
			// let thisAmount = amountFor(perf);																// 1-1. [변수 인라인]
			
			// 포인트 적립
			volumeCredits += Math.max(perf.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(perf).type) volumeCredits += Math.floor(perf.audience / 5);
			
			// 청구 내역 출력
			result += `${playFor(perf).name}: ${format(amountFor(perf)/100)} (${perf.audience}석)\n`;	// 1-2. [변수 인라인]
			totalAmount += amountFor(perf);
			/*
			result += `${playFor(perf).name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;
			totalAmount += thisAmount;
			*/
		}
		
		result += `총액: ${format(totalAmount/100)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		
		return result;
		
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
		
		function amountFor(aPerformance) {
			let result = 0;
			
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
	}
	
	module.exports = statement;
	```
- `추출` 함수(예: volumeCreditsFor)
	- statement.js
	```js
	function statement(invoice, plays) {
		let totalAmount = 0;
		let volumeCredits = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		const format = new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format;
		
		for (let perf of invoice.performances) {
			
			// 적립 포인트
			volumeCredits += volumeCreditsFor(perf)		// [함수 호출]
			/*
			volumeCredits += Math.max(perf.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(perf).type) volumeCredits += Math.floor(perf.audience / 5);
			*/
			
			// 청구 내역 출력
			result += `${playFor(perf).name}: ${format(amountFor(perf)/100)} (${perf.audience}석)\n`;
			totalAmount += amountFor(perf);
		}
		
		result += `총액: ${format(totalAmount/100)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		
		return result;
		
		function volumeCreditsFor(aPerformance) {		// [함수 추출]
			let result = 0;
			
			// 포인트 적립
			result += Math.max(aPerformance.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(aPerformance).type) result += Math.floor(aPerformance.audience / 5);
			
			return result;
		}
		
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
		
		function amountFor(aPerformance) {
			let result = 0;
			
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
	}
	
	module.exports = statement;
	```
- `제거` 변수(예: const format) + `교체` 함수명(예: format)
	- statement.js
	```js
	function statement(invoice, plays) {
		let totalAmount = 0;
		let volumeCredits = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		// [변수 제거 및 함수명 교체]
		/*
		const format = new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format;
		*/
		
		for (let perf of invoice.performances) {
			
			// 적립 포인트
			volumeCredits += volumeCreditsFor(perf)	
			
			// 청구 내역 출력
			result += `${playFor(perf).name}: ${usd(amountFor(perf))} (${perf.audience}석)\n`;
			totalAmount += amountFor(perf);
		}
		
		result += `총액: ${usd(totalAmount)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		
		return result;
		
		function usd(aNumber) {
			return new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format(aNumber/100);
		}
		
		function volumeCreditsFor(aPerformance) {
			let result = 0;
			
			// 포인트 적립
			result += Math.max(aPerformance.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(aPerformance).type) result += Math.floor(aPerformance.audience / 5);
			
			return result;
		}
		
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
		
		function amountFor(aPerformance) {
			let result = 0;
			
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
	}
	
	module.exports = statement;
	```
- `분할` 반복문(예: for)
	- statement.js
	```js
	function statement(invoice, plays) {
		let totalAmount = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		
		// 청구 내역
		for (let perf of invoice.performances) {
			
			// 적립 포인트
			// volumeCredits += volumeCreditsFor(perf)												// 1-1. [반복문 분할]
			
			result += `${playFor(perf).name}: ${usd(amountFor(perf))} (${perf.audience}석)\n`;
			totalAmount += amountFor(perf);
		}	
		
		// 적립 포인트
		let volumeCredits = 0;																		// 변수 이동
		for (let perf of invoice.performances) {
			volumeCredits += volumeCreditsFor(perf)													// 1-2. [반복문 분할]
		}
		
		result += `총액: ${usd(totalAmount)}\n`;
		result += `적립 포인트: ${volumeCredits}점\n`;
		
		return result;
		
		function usd(aNumber) {
			return new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format(aNumber/100);
		}
		
		function volumeCreditsFor(aPerformance) {
			let result = 0;
			
			// 포인트 적립
			result += Math.max(aPerformance.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(aPerformance).type) result += Math.floor(aPerformance.audience / 5);
			
			return result;
		}
		
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
		
		function amountFor(aPerformance) {
			let result = 0;
			
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
	}
	
	module.exports = statement;
	```
- `추출` 함수(totalVolumeCredits) + `교체` 임시 변수를 질의 함수로 후 `전개(인라인)` 변수
	- statement.js
	```js
	function statement(invoice, plays) {
		let totalAmount = 0;
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		
		// 청구 내역
		for (let perf of invoice.performances) {
			
			result += `${playFor(perf).name}: ${usd(amountFor(perf))} (${perf.audience}석)\n`;
			totalAmount += amountFor(perf);
		}
		
		// 적립 포인트
		// let volumeCredits = totalVolumeCredits();					// [임시 변수를 질의 함수로 교체]
		
		result += `총액: ${usd(totalAmount)}\n`;
		result += `적립 포인트: ${totalVolumeCredits()}점\n`;			// [변수 전개(인라인)]
		// result += `적립 포인트: ${volumeCredits}점\n`;
		
		return result;
		
		function totalVolumeCredits() {									// [함수 추출]
			let result = 0;
			
			for (let perf of invoice.performances) {
				result += volumeCreditsFor(perf);
			}
			
			return result;
		}
		
		function usd(aNumber) {
			return new Intl.NumberFormat("en-US",
							{ style:"currency", currency: "USD"
								, minimumFractionDigits: 2}).format(aNumber/100);
		}
		
		function volumeCreditsFor(aPerformance) {
			let result = 0;
			
			// 포인트 적립
			result += Math.max(aPerformance.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(aPerformance).type) result += Math.floor(aPerformance.audience / 5);
			
			return result;
		}
		
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
		
		function amountFor(aPerformance) {
			let result = 0;
			
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
	}
	
	module.exports = statement;
	```
- 적립 포인트와 동일하게 총액에도 "`추출` 함수(totalAmount) + `교체` 임시 변수를 질의 함수로 후 `전개(인라인)` 변수"를 및 `코드 가독성` 적용
	- statement.js
	```js
	function statement(invoice, plays) {
		
		let result = `청구 내역(고객명: ${invoice.customer})\n`;
		
		// 청구 내역들
		for (let perf of invoice.performances) {		
			result += `${playFor(perf).name}: ${usd(amountFor(perf))} (${perf.audience}석)\n`;
		}
		
		result += `총액: ${usd(totalAmount())}\n`;
		result += `적립 포인트: ${totalVolumeCredits()}점\n`;
		
		return result;
		
		// 총액
		function totalAmount() {
			let result = 0;
			
			for (let perf of invoice.performances) {
				result += amountFor(perf);
			}
			
			return result;
		}
		
		// 적립 포인트
		function totalVolumeCredits() {
			let result = 0;
			
			for (let perf of invoice.performances) {
				result += volumeCreditsFor(perf);
			}
			
			return result;
		}
		
		// 달러 표시
		function usd(aNumber) {
			return new Intl.NumberFormat("en-US",
							{style:"currency"
							, currency: "USD"
							, minimumFractionDigits: 2}).format(aNumber/100);
		}
		
		// 공연별 적립 포인트
		function volumeCreditsFor(aPerformance) {
			let result = 0;
			
			// 포인트 적립
			result += Math.max(aPerformance.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === playFor(aPerformance).type) {
				result += Math.floor(aPerformance.audience / 5);
			}
			
			return result;
		}
		
		// 연극
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
		
		// 공연별 금액
		function amountFor(aPerformance) {
			let result = 0;
			
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
	}
	
	module.exports = statement;
	```
- `추출` 함수 + `분할` 단계별(기능별)(예: 계산과 포맷팅-Text) + 데이터 구조 + `이동` 함수 + `교체` 반복문을 파이프라인으로
	- statement.js
	```js
	function statement(invoice, plays) {						// 1-1. [함수 추출 + 단계별 분할]

		const statementData = {};								// 2. 데이터 구조
		statementData.customer				= invoice.customer;
		statementData.performances			= invoice.performances.map(enrichPerformance);
		statementData.totalAmount			= totalAmount(statementData);
		statementData.totalVolumeCredits	= totalVolumeCredits(statementData);
		
		return renderText(statementData, plays);				// [단계별 분할]
		
		function enrichPerformance(aPerformance) {
			const result = Object.assign({}, aPerformance);		// 얕은 복사
			result.play				= playFor(result);
			result.amount			= amountFor(result);
			result.volumeCredits	= volumeCreditsFor(result);
			
			return result;
		}
		
		// 총액													// 3-5. [함수 이동(명시적으로 인수 전달 포함)]
		function totalAmount(data) {
			
			return data.performances							// 4-1. 반복문을 파이프라인으로 교체
				.reduce((total, p) => total + p.amount, 0);
			/*
			let result = 0;
			
			for (let perf of data.performances) {
				result += perf.amount;
			}
			
			return result;
			*/
		}
		
		// 적립 포인트											// 3-4. [함수 이동(명시적으로 인수 전달 포함)]
		function totalVolumeCredits(data) {
			
			return data.performances							// 4-2. 반복문을 파이프라인으로 교체
				.reduce((total, p) => total + p.volumeCredits, 0);
			/*
			let result = 0;
			
			for (let perf of data.performances) {
				result += perf.volumeCredits;
			}
			
			return result;
			*/
		}
		
		// 공연별 적립 포인트									// 3-3. [함수 이동]
		function volumeCreditsFor(aPerformance) {
			let result = 0;
			
			// 포인트 적립
			result += Math.max(aPerformance.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === aPerformance.play.type) {
				result += Math.floor(aPerformance.audience / 5);
			}
			
			return result;
		}
		
		// 공연별 금액
		function amountFor(aPerformance) {						// 3-2. [함수 이동]
			let result = 0;
			
			switch (aPerformance.play.type) {
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
		
		// 연극
		function playFor(aPerformance) {						// 3-1. [함수 이동]
			return plays[aPerformance.playID];
		}
	}
	
	function renderText(data, plays) {							// 1-2. [함수 추출 + 단계별 분할]
		
		let result = `청구 내역(고객명: ${data.customer})\n`;
		
		// 청구 내역들
		for (let perf of data.performances) {		
			result += `${perf.play.name}: ${usd(perf.amount)} (${perf.audience}석)\n`;
		}
		
		result += `총액: ${usd(data.totalAmount)}\n`;
		result += `적립 포인트: ${data.totalVolumeCredits}점\n`;
		
		return result;
		
		// 달러 표시
		function usd(aNumber) {
			return new Intl.NumberFormat("en-US",
							{style:"currency"
							, currency: "USD"
							, minimumFractionDigits: 2}).format(aNumber/100);
		}
	}
	
	module.exports = statement;
	```
- 파일 분리와 재명명 및 HTML 버전 추가
	- statement.js
	```js
	const calculate = require("./calculate");
	
	function text(invoice, plays) {
		return renderText(calculate(invoice, plays));
	}
	
	function renderText(data) {
		
		let result = `청구 내역(고객명: ${data.customer})\n`;
		
		// 청구 내역들
		for (let perf of data.performances) {		
			result += `${perf.play.name}: ${usd(perf.amount)}(${perf.audience}석)\n`;
		}
		
		result += `총액: ${usd(data.totalAmount)}\n`;
		result += `적립 포인트: ${data.totalVolumeCredits}점\n`;
		
		return result;
	}
	
	function html(invoice, plays) {
		return renderHTML(calculate(invoice, plays));
	}
	
	function renderHTML(data) {
		
		let result = `<h1>청구 내역(고객명: ${data.customer})</h1>\n`;
		result += "<table>\n";
		result += "<tr><th>연극</th><th>금액(좌석수)</th></tr>";
		for (let perf of data.performances) {
			result += `<tr><td>${perf.play.name}</td><td>${usd(perf.amount)}`;
			result += `(${perf.audience}석)</td></tr>\n`;
		}
		result += "</table>\n";
		
		result += `<p>총액: <em>${usd(data.totalAmount)}</em></p>\n`;
		result += `<p>적립 포인트: <em>${data.totalVolumeCredits}</em>점</p>\n`;
		
		return result
		
	}
	
	// 달러 표시
	function usd(aNumber) {
		return new Intl.NumberFormat("en-US",
						{style:"currency"
						, currency: "USD"
						, minimumFractionDigits: 2}).format(aNumber/100);
	}
	
	module.exports = {text, html};
	```
	- calculate.js
	```js
	function calculate(invoice, plays) {
	
		const result = {};
		result.customer				= invoice.customer;
		result.performances			= invoice.performances.map(enrichPerformance);
		result.totalAmount			= totalAmount(result);
		result.totalVolumeCredits	= totalVolumeCredits(result);
		
		return result;
		
		function enrichPerformance(aPerformance) {
			const result = Object.assign({}, aPerformance);
			result.play				= playFor(result);
			result.amount			= amountFor(result);
			result.volumeCredits	= volumeCreditsFor(result);
			
			return result;
		}
		
		// 총액
		function totalAmount(data) {
			
			return data.performances
				.reduce((total, p) => total + p.amount, 0);
		}
		
		// 적립 포인트
		function totalVolumeCredits(data) {
			
			return data.performances
				.reduce((total, p) => total + p.volumeCredits, 0);
		}
		
		// 공연별 적립 포인트
		function volumeCreditsFor(aPerformance) {
			let result = 0;
			
			// 포인트 적립
			result += Math.max(aPerformance.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === aPerformance.play.type) {
				result += Math.floor(aPerformance.audience / 5);
			}
			
			return result;
		}
		
		// 공연별 금액
		function amountFor(aPerformance) {
			let result = 0;
			
			switch (aPerformance.play.type) {
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
		
		// 연극
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
	}
	
	module.exports = calculate;
	```
	- index.js
	```js
	const plays = require("./plays.json");
	const invoices = require("./invoices.json");
	const statement = require("./statement");
	
	console.log(
		statement.text(invoices[0], plays)
	);
	console.log(
		statement.html(invoices[0], plays)
	);	
	```
- 클래스화
	- calculate.js
	```js
	class Calculator {
		constructor(aPerformance, aPlay) {
			this.performance	= aPerformance;
			this.play			= aPlay;
		}
		
		get amount() {
			let result = 0;
			
			switch (this.play.type) {
				case "tragedy": // 비극
					result = 40000;
					if (this.performance.audience > 30) {
						result += 1000 * (this.performance.audience - 30);
					}
					break;
				case "comedy": // 희극
					result = 30000;
					if (this.performance.audience > 20) {
						result += 10000 + 500 * (this.performance.audience - 20);
					}
					result += 300 * this.performance.audience;
					break;
				default:
					throw new Error(`알 수 없는 장르: ${this.performance.type}`);
			}
			
			return result;
		}
		
		get volumeCredits() {
			let result = 0;
			
			// 포인트 적립
			result += Math.max(this.performance.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === this.play.type) {
				result += Math.floor(this.performance.audience / 5);
			}
			
			return result;
		}
	}
	
	function calculate(invoice, plays) {
	
		const result = {};
		result.customer				= invoice.customer;
		result.performances			= invoice.performances.map(enrichPerformance);
		result.totalAmount			= totalAmount(result);
		result.totalVolumeCredits	= totalVolumeCredits(result);
		
		return result;
		
		function enrichPerformance(aPerformance) {
			const calculator		= new Calculator(aPerformance, playFor(aPerformance));			// 계산기 클래스
			const result			= Object.assign({}, aPerformance);
			
			result.play				= calculator.play;
			result.amount			= calculator.amount;
			result.volumeCredits	= calculator.volumeCredits;
			// 클래스로 변경
			/*
			result.play				= calculator(result);
			result.amount			= amountFor(result);
			result.volumeCredits	= volumeCreditsFor(result);
			*/
			
			return result;
		}
		
		// 총액
		function totalAmount(data) {
			
			return data.performances
				.reduce((total, p) => total + p.amount, 0);
		}
		
		// 적립 포인트
		function totalVolumeCredits(data) {
			
			return data.performances
				.reduce((total, p) => total + p.volumeCredits, 0);
		}
		
		// 공연별 적립 포인트
		function volumeCreditsFor(aPerformance) {
			
			return new Calculator(aPerformance, playFor(aPerformance)).volumeCredits;
			// [함수 이동]
			/*
			let result = 0;
			
			// 포인트 적립
			result += Math.max(aPerformance.audience - 30, 0);
			// 희극 관객 5명마다 추가 포인트 제공
			if ("comedy" === aPerformance.play.type) {
				result += Math.floor(aPerformance.audience / 5);
			}
			
			return result;
			*/
		}
		
		// 공연별 금액
		function amountFor(aPerformance) {
			
			return new Calculator(aPerformance, playFor(aPerformance)).amount;
			// [함수 이동]
			/*
			let result = 0;
			
			switch (aPerformance.play.type) {
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
			*/
		}
		
		// 연극
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
	}
	
	module.exports = calculate;
	```
- `교체` 생성자를 팩터리 함수로 + `교체` 타입 코드를 서브 클래스로 + `교체` 조건문 로직을 다형성(상속 포함)으로
	- calculate.js
	```js
	class Calculator {
		constructor(aPerformance, aPlay) {
			this.performance	= aPerformance;
			this.play			= aPlay;
		}
		
		get amount() {
			throw new Error("서브 클래스에서 처리하도록 설계되었습니다.");
		}
		
		get volumeCredits() {
			return Math.max(this.performance.audience - 30, 0);
		}
	}
	
	class TragedyCalculator extends Calculator {
		
		get amount() {
			let result = 40000;
			if (this.performance.audience > 30) {
				result += 1000 * (this.performance.audience - 30);
			}
			
			return result;
		}
	}
	
	class ComedyCalculator extends Calculator {
		
		get amount() {
			let result = 30000;
			if (this.performance.audience > 20) {
				result += 10000 + 500 * (this.performance.audience - 20);
			}
			result += 300 * this.performance.audience;
			
			return result;
		}
		
		get volumeCredits() {
			return super.volumeCredits + Math.floor(this.performance.audience / 5);
		}
	}
	
	function calculate(invoice, plays) {
	
		const result = {};
		result.customer				= invoice.customer;
		result.performances			= invoice.performances.map(enrichPerformance);
		result.totalAmount			= totalAmount(result);
		result.totalVolumeCredits	= totalVolumeCredits(result);
		
		return result;
		
		function enrichPerformance(aPerformance) {
			
			const calculator		= createCalculator(aPerformance, playFor(aPerformance));	// [생성자를 팩터리 함수로 교체]
			const result			= Object.assign({}, aPerformance);
			
			result.play				= calculator.play;
			result.amount			= calculator.amount;
			result.volumeCredits	= calculator.volumeCredits;
			
			return result;
		}
		
		// 타입 코드를 서브 클래스로 교체
		function createCalculator(aPerformance, aPlay) {
			switch (aPlay.type) {
				case "tragedy"	: return new TragedyCalculator(aPerformance, aPlay);
				case "comedy"	: return new ComedyCalculator(aPerformance, aPlay);
				default:
					throw new Error(`알 수 없는 장르: ${aPlay.type}`);
			}
		}
		
		// 총액
		function totalAmount(data) {
			
			return data.performances
				.reduce((total, p) => total + p.amount, 0);
		}
		
		// 적립 포인트
		function totalVolumeCredits(data) {
			
			return data.performances
				.reduce((total, p) => total + p.volumeCredits, 0);
		}
		
		// 연극
		function playFor(aPerformance) {
			return plays[aPerformance.playID];
		}
	}
	
	module.exports = calculate;
	```
## 2. 개론 그리고 원칙
### 2-1. 개론
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

### 1-2. 원칙


## 3. 대상과 검증 환경
### 3-1. 대상

### 3-2. 검증 환경


## 4. 기법
- 추출(Extract)
	- 변수
	- @함수
	- @클래스
	- @수퍼 클래스
- 전개(In-line/인라인)
	- @변수
	- 함수
	- 클래스
- 교체(Change, Rename, Replace, Substitute)
	- 변수명
	- @함수명
	- @함수 선언
	- @임시 변수를 질의 함수로
	- 파생 변수를 질의 함수로
	- @전개(인라인) 코드를 함수 호출로
	- 참조를 값으로 and 값을 참조로
	- 중첩 조건문을 보호 구문으로
	- @조건문 로직을 다형성으로
	- @반복문을 파이프라인으로
	- @기본형을 객체로
	- @타입 코드를 서브 클래스로
	- 서브 클래스를 위임으로
	- 수퍼 클래스를 위임으로
	- [API] 매개 변수를 질의 함수로 and 질의 함수를 매개 변수로
	- [API] 함수를 명령으로 and 명령을 함수로
	- [API] 오류 코드를 예외로
	- [API] 예외를 사전 확인으로
- 분할(Split)
	- 변수
	- @반복문
	- @단계별(기능별)
- 이동(Move)
	- 필드
	- @함수
	- 문장을 함수로
	- 문장을 호출한 곳으로
- 제거(Remove)
	- ...
- 묶기(Combine)
	- ...
- 숨김(Hide)
	- ...
- 분해(Decompose)
	- ...
- 통합(Consolidate)
	- ...
- 분리(Separate)
	- ...
- 캡슐화(Encapsulate)
	- ...

<!--
![Refactoring 2nd Edition](./image/refactoring_book.jpg)
-->
