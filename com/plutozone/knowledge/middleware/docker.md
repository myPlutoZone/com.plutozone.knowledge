# com.plutozone.knowledge.middleware.Docker


## Contents
01. [Overview .................................................. 개요](#1-overview)
02. [Container and Docker ............................ 컨테이너와 도커](#2-container-and-docker)
03. [Environments .............................................. 환경](#3-environments)
04. [Install Docker and Configuration ................... 설치와 구성](#4-install-docker-and-configuration)
05. [LifeCycle of Docker Container ......................... 생명주기](#5-lifecycle-of-docker-container)
06. [Commands ............................................... 명령어](#6-commands)
07. [Network for Container ................................ 네트워크](#7-network-for-container)
08. [Volume(Storage) for Container ........................... 볼륨](#8-volumestorage-for-container)
09. [Build for Container ..................................... 빌드](#9-build-for-container)
10. [Compose for Container ................................. 컴포즈](#10-compose-for-container)
11. [Example ................................................. 예제](#11-example)
	- [11-1. WordPress(웹 페이지 제작 및 관리용 CMS) + MySQL](#11-1-wordpress웹-페이지-제작-및-관리용-cms--mysql)
	- [11-2. Make Image(Ubuntu + Git + JDK) by docker commit](#11-2-make-imageubuntu--git--jdk-by-docker-commit)
	- [11-3. Make Image(Ubuntu + Python) by docker file](#11-3-make-imageubuntu--python-by-docker-file)
	- [11-4. Make Image(Ubuntu + Python + hello.py) by docker file](#11-4-make-imageubuntu--python--hellopy-by-docker-file)
	- [11-5. Make Image(Ubuntu + Python + hello.py + 환경 변수) by docker file](#11-5-make-imageubuntu--python--hellopy--환경-변수-by-docker-file)
	- [11-6. Make Image(Open JDK 18 + Spring Boot Project at GitHub) by docker file](#11-6-make-imageopen-jdk-18--spring-boot-project-at-github-by-docker-file)
12. [Reference ............................................... 참고](#12-reference)


## 1. Overview
> `Docker(=Container)는 충분히 검증되었지만 Host 또는 Virtual Machine이 제공하는 OS 기반 격리보다는 덜 안전하고 비효율적이다고 여겨지기도 한다.`

- docker
	- images, search, pull, inspect, tag, commit, build, login, push
	- create, start, stop, pause, unpause, run, rm
	- ps, top, system, attach vs. exec, diff, logs
	- network, volume
	- rm, rmi
- summary
	- 예제-1
		- Image는 myRegistry.com/hello-py:latest이며 로컬에 없을 경우 다운로드되어 Forground / Interactive + TTY Mode(=-i -t) 모드로 실행(-it)
		- 로컬의 /source 폴더를 컨테이너의 /data에 바인딩(-v /source:/data)
		- 컨테이너 이름은 demoHelloPy
		- 환경 변수는 APP=python
		```bash
		$ docker run -it -v /source:/data --name demoHelloPy -e APP=python myRegistry.com/hello-py:latest
		```
	- 예제-2
		- Image는 nginx이며 로컬에 없을 경우 다운로드되어 백그라운드 모드로 실행(-d)
		- 로컬의 /www 폴더를 컨테이너의 /usr/share/nginx/html에 바인딩(-v /www:/usr/share/nginx/html)
		- 로컬 1000 포트에 접속할 경우 컨테이너 80 포트로 포워딩(-p 1000:80)
		- 컨테이너 이름은 demoNginx
		- 환경 변수는 ENV=hello
		- exit(프로세스 종료) vs. [CTRL] + [p] +[q](프로세스 유지)
		```bash
		$ docker run -d -v /www:/usr/share/nginx/html -p 1000:80 --name demoNginx -e ENV=hello nginx
		$ docker exec -it demoNginx /bin/bash	# docker attach demoNginx
		```


## 2. Container and Docker
- What's Container(=경량화된 서버 가상화 기술)
	- Image vs. Container and Image Build vs. Source Build
	- Process at Source(Dockerfile, ...) + Runtime + Environment
	- Isolation
	- Containerization by Container Engine(=Docker) vs. Virtualization(base on OS) by Hypervisor
	- Bare-Metal 가상화 vs. Host 기반 가상화
- Docker(=리눅스 커널 기능을 공유하는 기술)
	- Build, Push and Run(Containerization and Container Basic Life-cycle)
	- Docker Component = Engine(Client + API + Daemon) + Object + Registry + Compose + Swarm
		- Object = Image, Container, Network, Volumes, Plugins
		- Docker(컨테이너 관리) and Swarm(클러스터 관리) vs. Kubernetes(Orchestrator=Server Cluster Tool, Multi-host 등)
- Run VM vs. Container(OCI, Open Container Initiative)
	- vdi for Oracle VirtualBox
	- vmdk for VMware
	- qcow2 for OpenSource
	- vhd/x for Windows


## 3. Environments
- Default Docker Container Network(172.17.0.0/16)
- Linux Distribution(Rocky 9.5)
- VM Tools(Virtual Box)
	- Change Host Only Network Properties at Virtual Box
		- 172.16.0.0/24(24 = 11111111.11111111.11111111.00000000 = 255.255.255.0)
		- 172.16.0.101 ~ 254 for DHCP Server(172.16.0.100)
	- Create VM
		- 1 vCPU + 2GB
		- NAT(enp0s3) for External
		- Host Only(enp0s8, 172.16.0.0/24) for Internal
		- 20GB/25GB at Rocky/Ubuntu
- Terminal(MobaXterm)


## 4. Install Docker and Configuration at Linux
- 필요 시 Docker 이전 버전 삭제(/var/lib/docker 폴더 포함)
- Install by root(#) at Rocky 9.5(https://docs.rockylinux.org/gemstones/containers/docker/)
	```bash
	# CE(Community Edition)
	$ curl -fsSL https://download.docker.com/linux/centos/docker-ce.repo -o /etc/yum.repos.d/docker-ce.repo
	$ yum install -y docker-ce
	$ systemctl status docker
	$ docker version							# Only Client Version
	$ systemctl start docker					# Server Start
	$ systemctl status docker
	$ docker version							# Client and Server Version
	$ systemctl enable docker					# Server Start on Boot
	$ docker images
	$ docker run hello-world					# Download hello-world and Print "Hello from Docker!"
	$ docker images
	$ docker run ubuntu /bin/echo 'Hello World'	# [선택] Download Ubuntu and Print "Hello World"
	$ docker images
	$ docker ps -a
	```
- Install at Ubuntu 24.04.1(https://docs.docker.com/engine/install/ubuntu/)
	```bash
	$ sudo apt update																								# Update
	$ sudo apt install apt-transport-https ca-certificates curl														# Install requried package
	$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -									# Docker official GPG Key
	$ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"	# Docker Repository
	$ sudo apt update																								# Update
	$ sudo apt-get update && sudo apt-get install docker-ce docker-ce-cli containerd.i								# Install Docker
	$ sudo systemctl status docker
	$ sudo docker version						# Only Client Version
	$ sudo systemctl start docker				# Server Start
	$ sudo systemctl status docker
	$ sudo docker version						# Client and Server Version
	$ sudo systemctl enable docker				# Server Start on Boot
	$ sudo docker images
	$ sudo docker run hello-world				# Download hello-world and Print "Hello from Docker!"
	$ sudo docker version
	# Run without sudo for Docker
	$ sudo usermod -aG docker ${USER}
	$ sudo shutdown -r now
	$ groups
	$ docker ps -a
	```
- Docker Host(=Server/Daemon) 구성
	- Images(Read Only + Version 및 Source, Runtime 등으로 구분됨)
	- Containers(Thin Read/Write)
	- Registry(Default: hub.docker.com)
- docker는 Client Tool이므로 Localhost 통신이 기본 vs. Kubernetes는 Server Tool이므로 Remote Host 통신이 기본
	```bash
	$ docker -H 172.16.0.102:2375	# Remote Host 접속 시
	```


## 5. LifeCycle of Docker Container
`도커 컨테이너의 생명주기` = build(빌드) > push(올리기) > pull(내리기) > `run(실행 = create + start) > ...`
```bash
# IMAGE_NAME		: 원격 레지스트리 또는 로컬에 저장된 이미지명
# CONTAINTER_NAME	: 이미지로부터 생성된 컨테이너명
# CONTAINTER_ID		: 이미지로부터 생성된 컨테이너의 고유 ID
$ docker run [IMAGE_NAME]																	# 실행(run = create + start)
$ docker create [IMAGE_NAME]																# 생성(대문자 사용 불가)
$ docker start [CONTAINTER_NAME or CONTAINTER_ID%]											# 시작(start는 백그라운드로 실행)
$ docker stats [CONTAINTER_NAME or CONTAINTER_ID%]											# 통계(자원 사용량 정보)
$ docker restart [CONTAINTER_NAME or CONTAINTER_ID%]										# 재시작
$ docker stop [CONTAINTER_NAME or CONTAINTER_ID%]											# 종료, SIGTERM(15)에 해당하는 안전 종료(참고: kill -l)
$ docker kill [CONTAINTER_NAME or CONTAINTER_ID%]											# 종료, SIGTERM(9)에 해당하는 강제 종료
$ docker logs -f [CONTAINTER_ID%]
$ docker inspect [CONTAINTER_NAME or CONTAINTER_ID%]
$ docker inspect -f '{{ .NetworkSettings.IPAddress }}' [CONTAINTER_NAME or CONTAINTER_ID%]	# IP 확인
$ docker rm [CONTAINTER_ID%]																# 삭제, 해당 컨테이너가 종료(stop or kill)되어 있어야 삭제 가능
$ docker run --rm																			# 실행 후 즉시 삭제
$ docker rm -f $(docker container ls -a -q)													# 모든 컨테이너 삭제(-f: 강제 삭제) or docker ps -aq(-a: 모두 -q: only ID)
$ docker rmi [IMAGE_NAME]																	# 이미지 삭제(=docker image rm [IMAGE_NAME], 해당 컨테이너가 삭제되어야 이미지 삭제 가능, -f 시 강제 삭제)
```


## 6. Commands
- Search Image at Registry and Image at Localhost
	```bash
	$ docker search nginx				# Default Registry(hub.docker.com)에서 nginx Image를 검색 = https://hub.docker.com/에서 nginx를 검색
	$ docker search plutomsw			# Default Registry(hub.docker.com)에서 plutomsw Image 또는 plutomsw 계정의 Private Image를 검색
	$ docker search quay.io/nginx		# quay.io Registry에서 nginx Image를 검색
	$ docker search quay.io/uvelyster	# quay.io Registry에서 uvelyster Image 또는 uvelyster 계정의 Private Image를 검색
	$ docker images						# Localhost의 Image 확인
	```
- System Information
	```bash
	$ docker system info		# 도커 환경
	$ docker system df			# 도커 디스크 사용량(이미지, 컨테이너 등)
	```
- Pull
	```bash
	# [중요] 하나의 공인 IP에 대해 6시간동안 100건?으로 제한 vs. 로그인 후에는 150건? at hub.docker.com
	$ docker images
	$ docker pull nginx							# Default(hub.docker.com) + nginx + Tag 생략 시 latest
	$ docker pull openjdk						# Default(hub.docker.com) + openjdk + Tag 생략 시 latest
	$ docker pull eclipse-temurin:8-jdk			# Default(hub.docker.com) + eclipse-temurin(openjdk에서는 JDK 이전 버전은 지원 중단) + 8-jdk
	$ docker pull plutomsw/demo-nginx			# hub.docker.com/plutomsw/demo-nginx(Registry/Owner/Repository, Tag 생략 시 latest)
	$ docker pull plutomsw/demo-springboot:v1	# hub.docker.com/plutomsw/demo-springboot:v1(Registry/Owner/Repository:Tag)
	$ docker pull quay.io/uvelyster/nginx		# quay.io/uvelyster/nginx(Registry/Owner/Repository, Tag 생략 시 latest)
	$ docker images
	```
- Run
	```bash
	# Forground		= stdout + stderr
	# Background	= stdout is none
	# Interactive	= stdin + stdout + stderr
	$ docker images
	$ docker run nginx								# Forground
	$ docker run eclipse-temurin:8-jdk
	$ docker ps
	$ docker ps -a

	$ docker run -d nginx							# Background
	$ docker run -d eclipse-temurin:8-jdk
	$ docker ps
	$ docker ps -a

	$ docker run -i nginx							# Interactive
	$ docker run -i eclipse-temurin:8-jdk
	$ docker ps
	$ docker ps -a
	$ curl 172.17.0.2 								# [중요] Default Container Network=172.17.0.0/16(Default: Container Host에서만 접속 가능)

	$ docker run nginx echo helloworld				# Forground + Command Parameter(echo helloworld)
	$ docker run eclipse-temurin:8-jdk echo helloworld
	$ docker ps
	$ docker ps -a

	$ docker run -d --name demoNginx-1 nginx		# Background + Alias(--name)
	$ docker run -d --name demoJdk8-1 eclipse-temurin:8-jdk
	$ docker ps
	$ docker ps -a

	$ docker run -it --name demoNginx-2 nginx		# Forground / Interactive + TTY Mode(=-i -t) + Alias(--name)
	$ docker run -it --name demoJdk8-2 eclipse-temurin:8-jdk
	$ docker ps
	$ docker ps -a
	```
- Stop vs. Kill
	```bash
	$ docker run -d --name web1 nginx
	$ docker run -d --name web2 nginx
	$ docker ps -a
	$ docker top web1					# top command at linux
	$ docker top web2
	$ ps -ef | grep nginx
	$ docker stop web1					# stop is SIGKILL(15)
	$ docker ps -a						# web1 is Exited(0) = SIGKILL(15)
	$ docker kill web2					# kill is SIGKILL(9)
	$ docker ps -a						# web2 is Exited(137) = SIGKILL(9)
	$ docker start web2
	$ docker kill -s 15 web2			# SIGTERM(-s) is 15(=docker stop)
	$ docker ps -a						# web2 is Exited(0) = SIGKILL(15)
	```
- Inspect
	```bash
	$ docker inspect demoNginx-1									# Backgroud Mode and Service
	$ docker inspect demoNginx-2 									# Forground Mode
	$ docker inspect demoJdk8-1										# Backgroud Mode and Non Service
	$ docker inspect demoJdk8-2										# Forground Mode
	$ docker run -d --name demoJdk8-3 eclipse-temurin:8-jdk bash	# Backgroud Mode and Non Service + bash
	$ docker run -it --name demoJdk8-4 eclipse-temurin:8-jdk bash	# Forground Mode + bash
	$ docker rm -f $(docker ps -aq)
	```
- Top, Port, Rename, Copy
	```bash
	$ docker run --name web1 -d nginx
	$ docker run --name web2 -d -p 8080:80 nginx
	$ docker top web1			# web1 컨테이너에 실행중인 프로세스 정보를 ps -ef 형식으로 출력(=docker container top web1)
	$ docker top web1 aux		# web1 컨테이너에 실행중인 프로세스 정보를 ps -aux 형식으로 출력
	$ docker port web1			# web1 컨테이너에 사용중인 포트 정보(=docker container port web1)
	$ docker port web2			# web2 컨테이너에 사용중인 포트 정보
	$ docker stop web1
	$ docker ps -a
	$ docker rename web1 www1								# 컨테이너명 변경(=docker container rename web1 www1)
	$ docker rename web2 www2
	$ docker container ls -a
	$ docker cp www1:/usr/share/nginx/html/index.html .		# 컨테이너 파일을 로컬(.)로 복사(=docker container cp www1:/usr/share/nginx/html/index.html .) [참고] www2 is a only live!!!
	$ nano index.html
	$ docker cp ./index.html www1:/usr/share/nginx/html/index.html		# 로컬 파일을 컨테이너 파일로 복사
	$ docker cp ./index.html www2:/usr/share/nginx/html/index.html		# 로컬 파일을 컨테이너 파일로 복사
	$ docker exec -it www2 /bin/bash						# [중요] 해당 컨테이너에 접근=exec addtional process(i: Interactive, t: TTY) after run
	$ cat /usr/share/nginx/html/index.html
	$ exit													# 해당 컨테이너에서 나가기
	$ docker rm -f $(docker ps -aq)
	```
- Diff, Attach
	```bash
	$ docker run --name web1 -d -p 8080:80 nginx
	$ docker diff web1			# 이미지와 컨테이너 차이점 확인(=docker container diff web1): A(Add) , C(Change), D(Delete)
	$ docker stop web1
	$ docker diff web1
	$ docker run --name ubuntu1 -it -d ubuntu
	$ docker diff ubuntu1
	$ docker attach ubuntu1		# 실행 중인 컨테이너의 연결(기존 프로세스) vs. docker exec(신규 프로세스)
	# useradd user1
	# passwd user1
	...				# [Ctrl] + [p] + [q]
	$ docker diff ubuntu1
	$ docker rm -f $(docker ps -aq)
	```
- Log
	```bash
	$ docker run --name web1 -d -p 8080:80 nginx
	$ docker logs web1							# 로그 확인(=docker container logs web1)
	$ docker inspect -f "{{.LogPath}}" web1		# 로그 파일 위치 확인
	$ docker logs -f web1						# 로그 계속(--follow) 확인
	$ docker logs -n 5 web1						# 로그 마지막 5줄 확인
	$ docker logs -t web1						# 로그 타임스템프도 확인
	$ docker rm -f $(docker ps -aq)
	```
- Resource and Monitoring
	```bash
	$ docker run -d -m 512m --oom-kill-disable=true nginx	# 메모리를 512MB로 제한하지만 초과해도 프로세스 강제 미종료(기본값: 초과하면 강세 종료)
	```


## 7. Network for Container
```bash
$ docker network ls										# Network(Default:bridge=호스트에 브릿지 네트워크 추가, host=호스트 네트워크 자체, none=없음) for Container
$ docker pull plutomsw/demo-busybox
$ docker tag plutomsw/demo-busybox demo-busybox2nd		# plutomsw/demo-busybox를 demo-busybox2nd로 설정(tag)
$ docker images
$ docker run --rm demo-busybox2nd ip a					# 실행 후 즉시 삭제(--rm), IP 확인(ip a = ip addr show): 172.17.0.2 from 172.17.0.0 ~ 172.17.255.255
$ docker run --rm --network host demo-busybox2nd ip a	# 실행 후 즉시 삭제(--rm), 네트워크 선택(--network), IP 확인(ip a = ip addr show)
$ docker run --rm --network none demo-busybox2nd ip a	# 실행 후 즉시 삭제(--rm), 네트워크 선택(--network), IP 확인(ip a = ip addr show)
$ docker network inspect bridge							# bridge detail
$ docker run -d demo-busybox2nd sleep 1d				# sleep 1d로 해당 컨테이너를 실행
$ docker network inspect bridge							# bridge detail
$ docker exec -it [Name or ID%] sh						# sh로 해당 컨테이너로 접근
$ docker network create -d brigde demoNet									# 기본이 brigde이므로 -d(driver) brigde 옵션 생략 가능
$ docker network ls
$ docker network rm demoNet
$ docker network create demoNet --subnet 172.20.0.0/24						# 사용자 정의 네트워크 생성
$ docker network ls
$ docker pull plutomsw/demo-nginx											# [참고] latest는 하기 10. Build에서 v1 > v2 이후의 결과임
$ docker tag plutomsw/demo-nginx demo-nginx2nd								# tag 설정
$ docker run -d --network demoNet --name demoApp demo-nginx2nd				# 사용자 정의 네트워크로 컨테이너 실행
$ docker inspect demoApp | grep IP											# IP 확인
$ docker run -d --network demoNet --name demoApp2 -p 1000:80 demo-nginx2nd	# 사용자 정의 네트워크로 컨테이너 실행(-p: 포트 포워딩, 요청 포트:응답 포트)
# http://172.16.0.101:1000 at Host
$ docker rm -f $(docker container ls -a -q)									# 모든 컨테이너 삭제(-f: 강제 중지 후 삭제) or docker ps -aq
$ docker network rm demoNet
# 기타 옵션: --add-host(/etc/hosts 설정), --dns(/etc/resolv.conf 설정), --mac-address(MAC 설정), --hostname(/etc/hostname 설정), --ip(IP 지정)
$ docker container run --rm -it --hostname www.test.com --add-host node1.test.com:172.17.0.10 --dns 192.168.10.2 centos # hotname, ip addr, ip route, cat /etc/hosts, cat /etc/hostname, df -hT, cat /etc/resolv.conf 등으로 확인
# 컨테이너 통신 방법 (1) /etc/hosts (2) --link (3) 자동 이름 검색 서비스 in 사용자 정의 네트워크
```


## 8. Volume(Storage) for Container
- EFK(Elastic Search + Fluentd + Kibana) vs. PLG(Promtail + Loki + Grafana) for Logging
- Storage 유형
	- Volume(at Docker Area, Overlay): 볼륨명(/var/lib/docker/volumes/볼륨명)으로 시작(예: ... -v demoVol1:/usr/share/nginx/html ...)
	- Bind mount(at File System, Over Mount): "/"로 시작(예: ... -v /demoVol2:/usr/share/nginx/html ...)
	- Tmpfs mount(at 메모리): ?로 시작(예: ...)
	```mermaid
	flowchart LR

		%% ===========================
		%% Host
		%% ===========================
		subgraph Host["Host Machine"]
			HD["Host Directory<br>/home/user/data"]
			DV["Docker Volume<br>/var/lib/docker/volumes/..."]
			RAM["Memory (RAM)"]
			DISK["Disk Storage"]

			HD --> DISK
			DV --> DISK
		end

		%% ===========================
		%% Container
		%% ===========================
		subgraph Container["Docker Container"]
			APP["Application"]

			DATA["/app/data"]
			CACHE["/cache"]

			APP --> DATA
			APP --> CACHE
		end

		%% ===========================
		%% Mount Types
		%% ===========================
		HD -- "Bind Mount" --> DATA
		DV -- "Volume" --> DATA
		RAM -- "Tmpfs Mount" --> CACHE

		%% ===========================
		%% Characteristics
		%% ===========================
		subgraph Features["Characteristics"]
			B["Bind Mount<br/>- Host 디렉터리 사용<br/>- 개발 환경<br/>- Host 직접 접근"]

			V["Volume<br/>- Docker 관리<br/>- 영구 저장<br/>- 백업 및 공유"]

			T["Tmpfs Mount<br/>- RAM 저장<br/>- 컨테이너 종료 시 삭제<br/>- 캐시·민감정보"]
		end

		B -.-> HD
		V -.-> DV
		T -.-> RAM

		%% Colors
		classDef bind fill:#E3F2FD,stroke:#1565C0,stroke-width:2px;
		classDef volume fill:#E8F5E9,stroke:#2E7D32,stroke-width:2px;
		classDef tmpfs fill:#FFF3E0,stroke:#EF6C00,stroke-width:2px;
		classDef app fill:#F3E5F5,stroke:#6A1B9A,stroke-width:2px;

		class HD,B bind;
		class DV,V volume;
		class RAM,T tmpfs;
		class APP,DATA,CACHE app;
	```
```bash
$ docker volume ls
$ docker volume create demoVol1
$ docker volume inspect demoVol1
$ docker run -d --name demoApp1 -p 1234:80 demo-nginx2nd
$ docker run -d --name demoApp2 -p 1235:80 -v demoVol1:/usr/share/nginx/html demo-nginx2nd
$ curl 172.17.0.1:1234
$ curl localhost:1234
$ curl 172.17.0.1:1235
$ docker volume inspect demoVol1
$ nano /var/lib/docker/volumes/demoVol1/_data/index.html
$ curl 172.17.0.1:1235																			# demoVol1 저장된 index.html(예: 동적 소스, 설정 등)
$ curl 172.17.0.1:1234
$ docker run -d --name demoApp3 -p 1236:80 -v demoVol1:/usr/share/nginx/html demo-nginx2nd		# demoVol1 volume mount
$ docker run -d --name demoApp4 -p 1237:80 -v          /usr/share/nginx/html demo-nginx2nd		# 자동 volume mount
$ curl 172.17.0.1:1236																			# demoVol1
$ docker run -d --name demoApp5 -p 1238:80 -v /demoVol2:/usr/share/nginx/html demo-nginx2nd		# "/"로 시작할 경우 by 사용자(bind mount)
$ docker run -d --name demoApp6 -p 1239:80 -v  demoVol1:/usr/share/nginx/html:ro demo-nginx2nd	# 읽기 전용 volume mount
$ docker run -d --name demoApp7 -p 1240:80 -v /demoVol3:/usr/share/nginx/html:ro demo-nginx2nd	# 읽기 전용 bind mount
$ ls /
$ docker run -d -e MYSQL_ROOT_PASSWORD=root mysql		# MySQL 설치 시 1) 볼륨을 지장하지 않을 경우 자동 생성 2) 암호 설정(-e)
$ docker volume ls
$ docker rm -f $(docker container ls -a -q)				# 모든 컨테이너 삭제(-f: 강제 중지 후 삭제) or docker ps -aq
$ docker rm -vf [Name or ID%]							# 컨테이너 삭제 시 볼륨 자동 삭제
$ docker volume ls
$ docker volume rm demoVol1								# 볼륨 수동 삭제
$ docker volume rm -f $(docker vloume ls -q)			# 모든 볼륨 삭제
$ docker volume ls
$ docker volume prune									# 생성된 모든 볼륨을 삭제
```


## 9. Build for Container
### Build 유형
- 수동 빌드(=docker commit): 명령어(commit)로 생성 또는 실행중인 컨테이너를 기반으로 이미지 빌드
- 자동 빌드(=docker build): Dockerfile을 기반으로 이미지를 빌드(**베이스 이미지 선택 또는 설정이 가장 중요**)

### 수동 빌드(docker commit)
```bash
$ docker images
$ docker ps -a
$ docker run -it --name demoSpringboot eclipse-temurin:18-jdk bash		# 베이스 이미지(eclipse-temurin:18-jdk)를 이용하여 demoSpringBoot로 실행
$ docker ps -a
$ docker commit demoSpringboot demo-springboot							# 실행중인 컨테이너(demoSpringboot)를 demo-springboot:latest 이미지로 생성(이미지명에 대문자 사용 불가, 이하 동일)
$ docker images
$ docker tag demo-springboot:latest myRegistry.com/ID/demo-springboot	# 태그 설정(latest 생략 가능, hug.docker.com일 경우는 도메인 생략, 이하 동일)
$ docker images
$ docker login -u ID													# docker.io(hub.docker.com)을 사용할 경우 로그인(이하 동일)
$ docker push myRegistry.com/ID/demo-springboot							# latest(=Open JDK 18 원본) 최초 등록 at Registry
$ docker images
$ docker rmi demo-springboot
$ docker images
$ docker tag myRegistry.com/ID/demo-springboot myRegistry.com/ID/demo-springboot:v1	# 최종 이미지를 버전 업하기 전에 태그(demo-springboot:v1) 변경
$ docker images
$ docker push myRegistry.com/ID/demo-springboot:v1						# v1(=Open JDK 18 원본) 등록 at Registry
$ docker rm -f $(docker container ls -a -q)

$ docker images
$ docker ps -a
$ docker run -d --name demoNginx -p 1000:80 nginx							# 베이스 이미지(nginx)를 이용하여 demoNginx로 실행
$ docker ps -a
$ docker commit demoNginx demo-nginx										# 실행중인 컨테이너(demoNginx)를 demo-nginx:latest 이미지로 생성
$ docker images
$ docker tag demo-nginx myRegistry.com/ID/demo-nginx						# 태그 설정(latest)
$ docker images
$ docker login -u ID
$ docker push myRegistry.com/ID/demo-nginx									# latest(=Nginx 원본) 최초 등록 at Registry
$ docker images
$ docker rmi demo-nginx
$ docker images
$ docker tag myRegistry.com/ID/demo-nginx myRegistry.com/ID/demo-nginx:v1	# 최종 이미지를 버전 업하기 전에 태그(demo-nginx:v1) 변경
$ docker images
$ docker push myRegistry.com/ID/demo-nginx:v1								# v1(=Nginx 원본) 등록 at Registry
$ echo "hello world, First Update" > index.html
$ docker cp index.html demoNginx:/usr/share/nginx/html/index.html
$ docker commit demoNginx myRegistry.com/ID/demo-nginx						# 1차 변경되어 실행중인 컨테이너(demoNginx)를 demo-nginx:latest 이미지로 생성
$ docker images
$ docker history nginx
$ docker history myRegistry.com/ID/demo-nginx
$ docker history myRegistry.com/ID/demo-nginx:v1
$ docker history myRegistry.com/ID/demo-nginx:v1 | wc -l
$ docker push myRegistry.com/ID/demo-nginx									# latest 1차 변경 등록 at Registry
$ docker ps -a
$ docker tag myRegistry.com/ID/demo-nginx myRegistry.com/ID/demo-nginx:v2	# 최종 이미지를 버전 업하기 전에 태그(demo-nginx:v2) 변경
$ docker images
$ docker push myRegistry.com/ID/demo-nginx:v2								# v2 등록 at Registry
$ echo "hello world, Second Update" > index.html
$ docker cp index.html demoNginx:/usr/share/nginx/html/index.html
$ docker commit demoNginx myRegistry.com/ID/demo-nginx						# 2차 변경되어 실행중인 컨테이너(demoNginx)를 demo-nginx:latest 이미지로 생성
$ docker images
$ docker push myRegistry.com/ID/demo-nginx									# latest 2차 변경 등록 at Registry
$ docker ps -a
$ docker rm -f demoNginx													# 컨테이너(demoNginx) 삭제(-f: 강제 중지 후 삭제)
$ docker rmi myRegistry.com/ID/demo-nginx:v1								# 모든 이미지(demo-nginx*) 삭제
$ docker rmi myRegistry.com/ID/demo-nginx:v2
$ docker rmi myRegistry.com/ID/demo-nginx
$ docker images
$ docker run -d --name demoNginx -p 1000:80 myRegistry.com/ID/demo-nginx		# demo-nginx:latest pull 및 run
$ docker run -d --name demoNginxV2 -p 1001:80 myRegistry.com/ID/demo-nginx:v2	# demo-nginx:v2 pull 및 run
$ docker run -d --name demoNginxV1 -p 1001:80 myRegistry.com/ID/demo-nginx:v1	# demo-nginx:v1 pull 및 run
$ docker ps -a
$ docker images
```

### 자동 빌드(docker build)
```bash
$ mkdir buildTest
$ vi buildTest/Dockerfile						# [참고] Docker File Instruction
FROM demo-busybox2nd							# 로컬에 해당 베이스 이미지가 다운로드되어 있어야 함
CMD echo helloworld								# CMD ["echo", "hello", "world"]
$ docker build buildTest -t testBuild:renewal	# 이미지 빌드
$ docker images
$ docker run testBuild:renewal
```

### Docker File Instruction
```bash
RUN commandl ; command2 ; command3		# 실패 여부에 관계없이 모두 실행
RUN commandl ; \
	command2 ; \
	command3							# 실패 여부에 관계없이 모두 실행 + 가독성(\)
RUN commandl && command2 && command3	# 앞 부분이 성공해야 다음 실행
RUN commandl || command2 || command3	# 앞 부분이 실패해야 다음 실행
RUN commandl | command2 | command3		# 파이프 라인 실행
```


## 10. Compose for Container
```bash
# Compose(Build 이후에 Build 자동화 툴, 예시: GitLab)
$ mkdir compose
$ cd compose
$ vi docker-compose.yaml
services:
  gitlab:
    image: 'quay.io/uvelyster/gitlab-ce:latest'
    restart: always
    hostname: 'mygitlab.com'
    container_name: gitlab
    dns: 172.16.0.200
    environment:
      GITLAB_ROOT_PASSWORD: P@ssw0rd
      GITLAB_OMNIBUS_CONFIG: |
        external_url 'http://mygitlab.com'
        registry_external_url 'https://myregistry.com'
    ports:
      - '80:80'
      - '443:443'
    volumes:
      - '/root/gitlab/config:/etc/gitlab'
      - '/auth:/etc/gitlab/ssl'
      - '/root/gitlab/logs:/var/log/gitlab'
      - '/root/gitlab/data:/var/opt/gitlab'
      - '/root/gitlab/backup:/var/opt/gitlab/backups'
      - '/root/gitlab/registry:/var/opt/gitlab/gitlab-rails/shared/registry'
$ docker compose up -d
```


## 11. Example
### 11-1. WordPress(웹 페이지 제작 및 관리용 CMS) + MySQL
```bash
$ docker run -d --name mysql -v /db:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=wordpress -e MYSQL_PASSWORD=wordpress mysql:5.7
$ docker run -d --name wordpress --link mysql:mysql -e WORDPRESS_DB_PASSWORD=wordpress -p 80:80 wordpress:4
$ docekr ps -a
# firefox http://172.17.0.3 접속 후 WordPress 설정
$ docker rm -f $(docker ps -aq)
$ rm -rf /db
```

### 11-2. Make Image(Ubuntu + Git + Open JDK 8) by docker commit
```bash
$ docker run --name myUbuntu -i -t ubuntu:20.04 /bin/bash
root@myUbuntu:/# apt update
...
root@myUbuntu:/# apt install -y git
...
root@myUbuntu:/# which git
...
root@myUbuntu:/# exit
$ docker diff myUbuntu
$ docker commit myUbuntu ubuntu_with_git
$ docker images
$ docker rm myUbuntu
$ docker run --name myUbuntu -i -t ubuntu_with_git /bin/bash
root@myUbuntu:/# apt update
...
root@myUbuntu:/# apt install -y openjdk-8-jdk
...
root@myUbuntu:/# exit
$ docker diff myUbuntu
$ docker commit myUbuntu ubuntu_with_git_and_jdk
$ docker images
$ docker rm myUbuntu
$ docker run --name myUbuntu -i -t ubuntu_with_git_and_jdk /bin/bash
root@myUbuntu:/# java -version
...
root@myUbuntu:/# exit
$ docker rm myUbuntu
```

### 11-3. Make Image(Ubuntu + Python) by docker file
```bash
$ mkdir build
$ cd build
$ nano Dockerfile
FROM ubuntu:20.04
RUN apt update && \
	apt install -y python
$ docker build -t ubuntu_with_python .
$ docker images
```

### 11-4. Make Image(Ubuntu + Python + hello.py) by docker file
```bash
$ mkdir build
$ cd build
$ nano hello.py
print "Hello, Python"
$ nano Dockerfile
FROM ubuntu:20.04
RUN apt update && \
	apt install -y python
COPY hello.py .
ENTRYPOINT ["python", "hello.py"]
$ docker build -t python_hello .
$ docker images
$ docker run python_hello
```

### 11-5. Make Image(Ubuntu + Python + hello.py + 환경 변수) by docker file
```bash
# 환경 변수와 도커 파일과 커맨드 모두에 있을 경우 커맨드가 우선 순위
$ ...
$ nano hello.py
import os
print "Hello, Python from %s!" % os.environ['NAME']
# 기존 Dockerfile
$ docker build -t python_hello_with_env .
$ docker run -e NAME=PlutoZone1st python_hello_with_env
...
$ export NAME=PlutoZone2nd
$ docker run -e NAME=$NAME python_hello_with_env
```

### 11-6. Make Image(Open JDK 18 + Spring Boot Project at GitHub) by docker file
- [com.plutozone.demo.springBoot](https://github.com/myPlutoZone/com.plutozone.demo.springBoot)


## 12. Reference
- 관리
	- 사용하지 않는 이미지 확인
		```bash
		$ docker images -a --no-trunc
		```
	- 이미지(Image), 컨테이너(Container) 그리고 태그(Tag)명에 대문자 사용 불가(영문자와 숫자, '-', '_', '.', '/' 만 허용)
- https://github.com/google/cadvisor (Docker, Kubernetes 등의 리소스 사용량과 성능을 모니터링하는 오픈소스 프로젝트)