# com.plutozone.knowledge.RPA


## RPA(Robotic Process Automation, 로봇 프로세스 자동화 = 소프트웨어 로봇) vs. 하드웨어 로봇
- RPA는 `단순하고 반복적인 일을 하는 곳에 적용 가능` vs. 창의적 업무는 우리가
- 자동화의 진화 단계(PWC 참고)
	- 자동화 스크립트 또는 매크로(예: AutoHotKey, 엑셀 등은 특정 환경에서 동작)
	- RPA(로봇 프로세스 자동화: Rule Base)
	- 프로세스 통합(여러 업무를 통합하여 자동화)
	- 지능형 자동화(IPA, Intelligent Robotic Automation: AI 기반)
	- 자율 지능(예: `JARVIS`=`J`ust `a` `R`ather `V`ery `I`ntelligent `S`ystem)
- 특징 및 기대 효과
	- 연속성(예: 365일 단순 반복 업무 처리, 인수인계 불필요 등)
	- 규칙 기반(사람의 판단이 필요 없는 작업)
	- 보안(예: 비밀번호 등 정보 유출 방지와 휴먼 에러 차단)
	- 유연성(기존 시스템 변경 없이 실행 가능)
- 적용 분야
	- 단순 반복(예: 주요 기사 수집 후 사내 이메일링) 및 대사 작업
	- 문서(예: PDF) 등 자동 인식, 클로링을 통한 환율 등 자동화 등
	- 재무/회계의 전표 및 증빙 처리 그리고 데이터 관리와 마감 결과 대사
	- 영업/마케팅에서 주문 및 가격 등 데이터 입력
- RPA 솔루션
	- AA(Automation Anywhere) vs. BluePrism vs. UiPath vs. Brity
	- 공통적으로 자동화를 위한 `로봇`과 `로봇 에디터` 그리고 `관리 시스템`으로 구성됨 + 드레그앤드랍 지원


## UiPath 개요
- UiPath 역사
- UiPath 제품군
- UiPath 플랫폼 구성(Enterprise vs. Community는 Orchestrator에서 2개의 로봇 관리만 지원)
	- UiPath Studio(Drag & Drop 방식의 `로봇 에디터`)
	- UiPath Orchestrator(원격 및 로봇에 대한 통합 `관리 시스템`)
	- UiPath Robot(`로봇`은 Attended-유인 시스템 vs. Unattended-무인 시스템으로 구분)
- UiPath 홈페이지 and 포럼의 교육 및 기술 자료 참고
- UiPath 설치
- Tip
	- Activity 안 보일 시 필터의 클래식을 체크할 것
	- Activity 수정 시 F2 키를 클릭하면 선택 전에 시간 여유가 생김
	- Activity 연결 방법은 1) 직접 라인 연결 또는 2) 연결 대상에 접근


## UiPath 시작
- Hello, UiPath at EX-01
	- 새 프로젝트 > 프로세스
	- Write Line
	- Message Box
		- 상위
		- 다음 작업 후 자동 종료
		- 버튼
		- 캡션
		- 출력
- UiPath Studio 시작 및 화면 구성


## 변수와 자료형 그리고 범위
- 데이터 타입에 따른 변수 선언 후 값 지정 및 메시지 박스에 값 출력 at EX-02
	- 새 프로젝트 > 프로세스
	- Flowchat
		- Sequence vs. Flowchat
			| 항목 | Sequence | Flowchart |
			| :---: | :--- | :--- |
			| 구조 | 순차적 실행 구조 | 분기 및 경로 중심 구조 |
			| 실행 | 위에서 아래로 순차 실행 | 여러 방향으로 흐름 구성 가능 |
			| 조건 처리 | If 등으로 조건 처리 | Flow Decision, If 등으로 조건 처리 |
			| 반복 처리 | While, For Each 등 사용 | 반복 경로를 연결하여 구성 가능 |
			| 가독성 | 단순한 작업에 적합 | 복잡한 업무 흐름을 시각화하기에 적합 |
			| 난이도 | 비교적 간단함 | 경로가 많아지면 복잡해질 수 있음 |
			| 유지 보수 | 선형 프로세스 수정이 쉬움 | 분기 및 전체 흐름 파악에 유리함 |
			| 주요 활용 | 엑셀 처리, 데이터 입력, 파일 저장 | 승인 프로세스, 성공·실패 분기, 조건별 업무 처리 |
			| 권장 사용 | 정해진 순서대로 작업할 때 | 조건에 따라 실행 경로가 달라질 때 |
	- Sequence x 2
	- Assign x 4
	- Message Box x 4
	- For Each
- 변수 선언은 1) 변수 패널 또는 2) 해당 Activity에서 단축키 이용(Ctrl + K)하여 선언
- 자료형은 .NET Framework 기반으로 제공
- 범위는 변수 패널에서 전역 또는 지역 등으로 설정


## Package와 Activity
- Package 검색 및 설치(예: PDF는 패키지 설치 필요) 그리고 관리
- 자주 사용하는 Activity와 속성 at EX-03
	- 새 프로젝트 > 프로세스 > Flowchat
	- Flowchat, Sequence, Message Box, Write Line, Assign, For Each
	- Click
		- ContinueOnError
		- DelayBefore
		- KeyModifiers
		- ...
	- Get Text
	- Set Text vs. Type Into
	- Type Into
		- Special Key
		- Activate
		- DelayBetweenKeys
		- ...
	- Send Hotkey
	- Input Dialog
	- If and 다중 If vs. Flow Decision
	- Switch
	- While
	- Break, Continus, Delay
	- Parallel for 동시적 실행-랜덤
	- Pick + PickBranch for 선택적 실행


## 데이터(.NET 기반) 조작(사용 및 관리)
- 새 프로젝트 > 프로세스 > Flowchat
- 문자열 at EX-04
	- ToString
	- Equals
	- Contains
	- Substring
	- Split
	- IndexOf
- 시간 at EX-04
	- DateTime.Now
	- ...
- 배열(Array)과 리스트(List) at EX-04
	- Count
	- ...
- (Build) Data Table at EX-04
	- ...


## 기능
- 레코딩(Recoding) at EX-05
	- 새 프로젝트 > 프로세스 > Flowchat
	- 레코딩 by 메모장
		- 메모장 실행
		- 레코딩 시작 및 종료
	- 레코딩 by 웹 브라우저
		- 웹 브라우저 실행
		- 레코딩 시작 및 종료
		- Get Text
		- Message Box
- 스크래핑(Scraping) at EX-06
	- 새 프로젝트 > 프로세스 > Flowchat
	- 웹 페이지 크롤링 by 웹 브라우저
		- 웹 브라우저 실행 및 대상 접속
		- Attach Browser
		- Maximize Windows
		- Extract Table Data
		- Excel Application Scrope vs. Write CSV
		- Close Windows
- 이메일(Email)
...
- xPDF(Portable Document Format)
- x셀렉터(Selector)


## 프로젝트
### 네이버 메인(Main)의 증시 영역(DIV)에서 TOP 3(종목명만) at PJ-01