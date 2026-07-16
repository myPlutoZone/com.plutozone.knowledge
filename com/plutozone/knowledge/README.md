# com.plutozone.knowledge
> 본 문서는 정보기술(IT, Information Technology)에 대한 일부 기술적 사항을 소개한다.

> 본 문서는 필자의 주관에 의해 작성된 것이므로 인용 또는 적용 시 문제가 발생하여도 필자에게는
법적 책임이 없으며 본 문서는 예고 없이 변경될 수 있다.


## Contents
01. [Fundamental of IT Education ... 정보기술 교육의 기본](./fundamental/README.md "교육 목표와 네트워크와 프로그래밍 및 데이터베이스에 대한 기본 등")
02. [OS ....................................... 운영 체제](#2-os)
03. [Language ........................... 프로그래밍 언어](#3-language)
04. [Development ................................... 개발](#4-development)
05. [Database .............................. 데이터베이스](#5-database)
06. [Middleware ................................ 미들웨어](#6-middleware)
07. [AI(ML + DL) .............................. 인공 지능](./ai/README.md "AI 기본 등")
08. [Management ..... 시스템과 서비스에 대한 관리 및 정책](#8-management)
09. [Utility ................................... 유틸리티](./utility/README.md "유틸리티와 툴들")
10. [Troubleshooting .......................... 문제 해결](#10-troubleshooting)


## 2. OS
- [DNS](./os/dns.md)
- [Linux](./os/linux.md)
- [Rocky](./os/rocky.md)
- [Ubuntu](./os/ubuntu.md)
- [Micosoft Windows](./os/windows.md)


## 3. Language
- [C/C++ and Visual C++](./language/c+cpp+vcpp.pdf)
- [CSS](./language/css.md)
- [HTML](./language/html.md)
- [Java](./language/java.md)
- [JavaScript](./language/javascript.md)
- [Laravel](./language/laravel.md)
- [Node.js](./language/nodejs.md)
- [Python](./language/python.md)
- [Spring Boot](./language/springboot.md)
- [Vue.js](./language/vuejs.md)


## 4. Development
- [Overview of Development](./development/README.md "개발 표준 가이드 등")
- [AWS](./development/cloud/aws.md)
- [Clean Code](./development/cleanCode.md)
- **[Command](./development/command.md)**
- [Eclipse](./development/eclipse.md)
- [Git](./development/git.md)
- [Apache Jmeter](./development/jmeter.md)
- [Nox](./development/nox.md)
- [Rear](./development/rear.md)
- [Refactoring](./development/refactoring.md)
- [Solution by Open Source](./development/solutionByOpens.md)
- [Oracle Virtual Box](./development/virtualbox.md)


## 5. Database
- [Overview of Database](./database/README.md "데이터베이스 개론 등")
- [HeidiSQL.html](./database/heidiSQL.html "HeidiSQL에 저장된 DB 접속 암호를 복호화")
- [MariaDB](./database/mariadb.md)
- [Oracle](./database/oracle.md)


## 6. Middleware
- [Ansible](./middleware/ansible.md)
- [Docker](./middleware/docker.md)
- [GitLab](./middleware/gitlab.md)
- [Jenkins](./middleware/jenkins.md)
- [Kubernates(K8s)](./middleware/kubernetes.md)
- [Nginx](./middleware/nginx.md)
- [Apache Tomcat](./middleware/tomcat.md)


## 8. Management
- [Overview of Management](./management/README.md "관리 개론 등")
- [Forensic](./management/forensic.md)
- [Log](./management/log.md)
- [Term](./management/term.md)


## 10. Troubleshooting
- Network
	- 리소스(예: Socket client = serverSocket.accept())를 닫지(예: client.close())하지 않은 상태를 확인하는 방법
- Database
	- 선착순 응모 이벤트에서 DB가 다운(단, Network와 Application의 가용성은 문제 없다고 가정함)되어도 응모 정보를 저장할 수 있는 방법(예: P사 Event)
	- 읽기 전용의 Application이 DB가 다운되어도 Application이 정상 동작하게 하는 방법(예: M사 M/G)