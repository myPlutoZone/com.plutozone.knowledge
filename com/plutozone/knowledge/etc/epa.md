# com.plutozone.knowledge.RPA


## RPA(Robotic Process Automation, 로봇 프로세스 자동화 = 소프트웨어 로봇) vs. 하드웨어 로봇
- RPA vs. 자동화 스크립트 or 매크로
- RPA는 단순하고 반복적인 일을 하는 곳에 적용 가능 vs. 창의적 업무는 우리가
- 자동화의 진화 단계(PWC 참고)
	- 매크로(예: 엑셀)
	- RPA(로봇 프로세스 자동화: Rule Base)
	- 프로세스 통합
	- 지능형 자동화(IPA, Intelligent Robotic Automation: AI 기반)
	- 자율 지능
- 특징 및 기대 효과
	- 단순 반복 업무 처리
	- 규칙 기반(사람의 판단은 안 됨)
	- 보안(비밀번호 등 정보 유출 방지와 휴먼 에러 차단)
	- 유연성(기존 시스템 변경 없음)
- 적용 분야
	- 단순 반복 및 대사 작업
	- 문서 등 자동 인식, 클로링을 통한 환율 등 자동화 등
- RPA 솔루션
	- AA(Automation Anywhere) vs. BluePrism vs. UiPath
	- 공통적으로 자동화를 위한 로봇과 로봇 에디터 그리고 관리시스템으로 구성됨 + 드레그앤드랍 지원


## UiPath
- UiPath 플랫폼 구성(Enterprise vs. Community는 Orchestrator에서 2개의 로봇 관리만 지원)
	- UiPath Studio(Drag & Drop 방식의 로봇 에디터)
	- UiPath Orchestrator(원격 및 로봇에 대한 통합 관리 시스템)
	- UiPath Robot(로봇은 Attended-유인 시스템 vs. Unattended-무인 시스템으로 구분)
- UiPath 홈페이지 또는 포럼의 교육 및 기술 자료 참고
- Tip	
	- Activity 안 보일 시 필터의 클래식을 체크할 것
	- Activity 수정 시 F2 키를 클릭하면 선택 전에 시간 여유가 생김


## Hello, UiPath
- Hello, UiPath(메시지 박스 및 라인 출력)
	- 새 프로젝트 > 프로세스
	- Flowchat
	- Message Box
	- Write Line
- UiPath Studio 시작 및 화면 구성
- 변수와 자료형 그리고 범위(데이터 타입에 따른 변수 선언 후 값 지정 및 메시지 박스에 값 출력)
	- Sequence x 2
	- Asign x 4
	- Message Box x 4
	- For Each


## Activity와 Package
- 자주 사용하는 Activity와 속성
	- Package 검색 및 설치Flowchart
	- Flowchat, Sequence, Message Box, Write Line, Assign, For Each
	- Click
	- Get Text
	- Set Text
	- Type Into
	- Send Hotkey
	- Input Dialog
	- If
	- switch
	- Flow Decision
	- while
	- break, continus, delay
	- Parallel
	- Pick + PickBranch
- Package 관리


## 데이터(.NET 기반) 조작(사용 및 관리)
- 문자열
- 시간
- 배열과 리스트
- 데이터 테이블


## 레코딩(Recoding)


## 스크래핑(Scraping)


## 셀렉터(Selector)


## PDF


## 이메일(Email)