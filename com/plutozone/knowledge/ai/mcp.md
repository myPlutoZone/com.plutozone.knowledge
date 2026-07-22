# com.plutozone.knowledge.ai.MCP


## Model Context Protocol(MCP)이란?
- MCP는 AI 모델이 외부 도구, 데이터, 서비스와 표준화된 방식으로 연결되도록 하는 프로토콜입니다.
- 쉽게 말하면, AI를 위한 USB-C 같은 표준 인터페이스라고 생각하면 됩니다.
- 기존 API 방식(각각의 서비스마다 별도의 API 연동 코드를 작성)
	```mermaid
	graph TD
		AI[AI Application]

		AI --> G[GitHub API]
		AI --> S[Slack API]
		AI --> D[Database API]
		AI --> F[File System API]
		AI --> C[Google Drive API]

		style AI fill:#4CAF50,color:#fff
	```
- MCP 방식(모든 서비스가 동일한 규칙을 따르므로 AI는 하나의 방식으로 다양한 도구를 사용 가능)
	```mermaid
	graph TD
		AI[AI Application]
		MCP[MCP Client / Protocol]

		AI --> MCP

		MCP --> G[GitHub MCP Server]
		MCP --> S[Slack MCP Server]
		MCP --> D[Database MCP Server]
		MCP --> F[File System MCP Server]
		MCP --> C[Google Drive MCP Server]

		style AI fill:#4CAF50,color:#fff
		style MCP fill:#2196F3,color:#fff
	```


## MCP의 구성
일반적으로 다음과 같은 구성 요소로 이루어집니다.

- MCP Host: AI를 실행하는 애플리케이션(예: AI IDE, 데스크톱 앱 등)
- MCP Client: Host 내부에서 MCP 서버와 통신하는 역할
- MCP Server: 실제 기능을 제공하는 프로그램(예: GitHub 서버, 파일 시스템 서버, 데이터베이스 서버)


## MCP Server가 제공하는 것
MCP 서버는 주로 다음과 같은 기능을 제공합니다.

- Tools: AI가 실행할 수 있는 함수(예: read_file(), search_database())
- Resources: AI가 읽을 수 있는 데이터(예: 문서, 로그, 파일)
- Prompts: 미리 정의된 작업 템플릿


## 왜 중요한가?
MCP의 장점은 다음과 같습니다.

- 표준화된 인터페이스
- 도구를 쉽게 추가 가능
- 여러 AI 모델에서 재사용 가능
- AI 애플리케이션과 도구의 결합도를 낮춤
- 새로운 서비스 연동이 쉬워짐

예를 들어 GitHub용 MCP 서버를 한 번 만들어 두면, MCP를 지원하는 다양한 AI 클라이언트에서 별도 수정 없이 사용할 수 있습니다.


## 간단한 예시
사용자가 다음과 같이 요청한다고 가정해 보겠습니다.

- "프로젝트 README를 읽고 TODO를 정리해 줘."

AI의 처리 과정은 다음과 같습니다.

- MCP 서버에 read_file("README.md") 요청
- README 내용 수신
- AI가 분석
- 필요하면 create_issue() 같은 다른 도구 호출
- 결과를 사용자에게 반환


## 누가 사용하나?
최근에는 다양한 AI 개발 도구와 에이전트 프레임워크에서 MCP를 지원하거나 도입하고 있습니다. 이를 통해 하나의 MCP 서버를 여러 AI 환경에서 공통으로 활용할 수 있습니다.


## 언제 배우면 좋은가?

다음과 같은 경우라면 MCP를 익히는 것이 큰 도움이 됩니다.

- AI 에이전트를 개발하고 싶을 때
- LLM에 외부 시스템을 연결하고 싶을 때
- 사내 데이터베이스나 문서와 AI를 연동하고 싶을 때
- AI 기반 자동화 시스템을 구축하려고 할 때

특히 AI 애플리케이션에서 `도구 호출(Tool Calling)`과 `외부 시스템 연동`을 다룬다면 MCP는 점점 더 중요한 표준으로 자리 잡고 있습니다.