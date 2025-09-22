# com.plutozone.knowledge.development.Jmeter


- 용어 정의
	- Thread Group
		- 성능 테스트의 가장 기본적인 요소로써 Test Plan 노드 아래 몇 개든 올 수 있으며 Thread Group은 논리적인 모습으로 표현하면 테스트 클라이언트들의 묶음
	- Sampler
		- 실제로 부하를 보내는 그룹으로 대표적으로 HTTP Request가 있음
	- Listener
		- 해당 Sampler에 대한 결과를 보기 위한 엘리먼드
- 다운로드
	- http://jakarta.apache.org/jmeter/
- 성능 테스트 절차
	- Thread Group 추가
		- Test Plan > Add > Threads(Users) > Thread Group
	- Sampler 추가
		- Test Plan > 추가한 Tread Group > Add > Sampler > HTTP Request
		- Server Name or IP, Port Number, Path 등을 기재
	- Listener 추가
		- 추가한 Sampler > Add > Listener > Summary Report와 View Results Tree
	- Thread Group 설정
		- 동시 접속자 수(Number of Threads), 동접자 시간 간력(Ramp-up Period), 회수(Loop Count) 등을 설정