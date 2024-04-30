- ![Generic badge](https://img.shields.io/badge/Important-Contents1_Contents2-red.svg)
- ![Generic badge](https://img.shields.io/badge/Confirm-Contents1_Contents2-green.svg)
- ![Generic badge](https://img.shields.io/badge/Reference-Contents1_Contents2-blue.svg)


# Management


# Contents
1. 개요
2. 시스템 구축 및 관리
3. 서비스 개발 및 관리
4. 시스템 및 서비스 유지운영을 위한 주요 정보 및 메뉴얼


# 개요
- 보안은 네트워크, 서버, 미들웨어, 서비스 등 모든 노드에 설정할 수 있다.


# 시스템 구축 및 관리
- 시스템 성능 향상
	- 경우에 따라 CPU 절전 기능 해제 시에만 최대 Clock 적용됨
	- 경우에 따라 Memory Bank 모두 채울 때 최대 속도 적용됨
- 호스트명은 난독화할 것
- 사용하지 않는 서비스는 제거(uninstall) 또는 중지(stop)하여 가용 시스템 리소스를 최대화할 것
- 클라우드(IaaS) 도입 시
	1. 규모를 산정하라.
	2. 개발, 상용 등 모든 인프라(Firewall, LB, DNS, Network, Server, Monitoring 등)를 설계하고 클라우드를 생성하라.
	3. 템플릿, AC(Container)S/AK(Kubernetes)S로 자동화하라.
	4. S(Service, Office365)aaS, P(Platform, 개발 서버)aaS, B(Backend, Firebase)aaS, F(Front, CodeAnywhere/Azure Event)aaS, Hybrid Cloud 및 Multi Cloud를 활용하라.


# 서비스 개발 및 관리
- 빠른 응답 속도와 최소 자원 사용을 위해 코드 최적화할 것


# 시스템 및 서비스 유지운영을 위한 주요 정보 및 메뉴얼
`#FF0000` 프로젝트 폴더와 통합할 것-산출물 상세화 포함
<table>
<tr>
	<td>구분</td>
	<td>정보</td>
	<td>참고</td>
</tr>
<tr>
	<td rowspan=3>분석</td>
	<td>IDC or Cloud</td>
	<td>계약, 입출고, 출입자/사용자 등</td>
</tr>
<tr>
	<td>시스템</td>
	<td>방화벽, 서버, 네트워크 등에 대한 제품, 설정 정보, 메뉴얼, 접속 등</td>
</tr>
<tr>
	<td>담당자</td>
	<td>고객사,  협력사, 관계사 등</td>
</tr>
<tr>
	<td rowspan=2>설계</td>
	<td>시스템 구성도</td>
	<td></td>
</tr>
<tr>
	<td>서비스 구성도</td>
	<td></td>
</tr>
<tr>
	<td>구현</td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td>검증</td>
	<td></td>
	<td>프론트, 백엔드, 유지운영(시스템, 서비스 및  장애 포함) 메뉴얼</td>
</tr>
</table>