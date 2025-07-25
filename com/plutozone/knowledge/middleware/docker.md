# com.plutozone.knowledge.middleware.Docker


## TODO
- `코드(탭 vs. 스페이스 포함) 및 예제 정규화`


## Overview
- Docker(=Container)는 충분히 검증되었지만 Host 또는 Virtual Machine이 제공하는 OS 기반 격리보다는 덜 안전하고 비효율적이다고 여겨지기도 한다.


## Environments
- VM Tools(Virtual Box)
- Linux Distribution(Rocky 9.5)
- Terminal(MobaXterm)


## Docker Container Basic
- Containerization and Container Basic Life-cycle
- Image Build vs. Source Build
- Network and Storage
- Compose(Docker=Container Engine, Kubernetes=Orchestrator=Server Cluster Tool)


## Change `Host Only Network` Properties at Virtual Box
- 172.16.0.0/24(24 = 11111111.11111111.11111111.00000000 = 255.255.255.0)
- `172.16.0.101 ~ 254 for DHCP(172.16.0.100)`


## Create VM
- 1 vCPU + 2GB + `NAT(enp0s3) for External` + `Host Only(enp0s8, 172.16.0.0/24) for Internal` + 20GB/25GB at Rocky/Ubuntu


## What's Container
- Process at Source + Runtime + Environment
- Isolation
- Virtualization(base on OS) by Hypervisor vs. Containerization by Container Engine(=Docker)


## Run VM vs. Container(OCI, Open Container Initiative)
- vdi for Oracle VirtualBox
- vmdk for VMware
- qcow2 for OpenSource
- vhd/x for Windows


## Install Docker(CE, Community Edition)
- Install by root(#) at Rocky 9.5(https://docs.rockylinux.org/gemstones/containers/docker/)
```bash
$ curl -fsSL https://download.docker.com/linux/centos/docker-ce.repo -o /etc/yum.repos.d/docker-ce.repo
$ yum install -y docker-ce
$ systemctl status docker
$ docker version                              	# Only Client Version
$ systemctl start docker                      	# Server Start
$ systemctl status docker
$ docker version                              	# Client and Server Version
$ systemctl enable docker	          	# Server Start on Boot
$ docker images
$ docker run hello-world		        # Download hello-world and Print "Hello from Docker!"
$ docker images
$ docker run ubuntu /bin/echo 'Hello World'	# Download ubuntu and Print "Hello World"
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


## Server & Client
- docker는 Client Tool이므로 Localhost 통신이 기본 vs. Kubernetes는 Server Tool이므로 Remote Host 통신이 기본
```bash
$ docker -H 172.16.0.102:2375      # Remote Host 접속 시
```


## Docker Host(=Server/Daemon) 구성
- Containers(Thin Read/Write)
- Images(Read Only + Version 및 Source, Runtime 등으로 구분됨)
- Registry(Default: hub.docker.com)


## Commands
- Search Image at Registry and Image at Localhost
```bash
$ docker search nginx			# Default Registry(hub.docker.com)에서 nginx Image를 검색 = https://hub.docker.com/에서 nginx를 검색
$ docker search quay.io/nginx		# quay.io Registry에서 nginx Image를 검색
$ docker images				# Localhost의 Image 확인
```

- System Information
```bash
$ docker system info			# 실행 환경
$ docker system df			# 디스크 사용량
```

- Pull
```bash
# [중요] 하나의 공인 IP에 대해 6시간동안 100건?으로 제한 vs. 로그인 후에는 150건? at hub.docker.com
$ docker images
$ docker pull nginx                                        # Default(hub.docker.com) + nginx + Tag 생략 시 latest
$ docker pull openjdk                                      # Default(hub.docker.com) + openjdk + Tag 생략 시 latest
$ docker pull openjdk:8-alpine                             # Default(hub.docker.com) + openjdk + 8-apline
$ docker pull plutomsw/demo-nginx                          # hub.docker.com/plutomsw/demo-nginx(Registry/Owner/Repository, Tag 생략 시 latest)
$ docker pull plutomsw/cicd_guestbook:20240107064001_24    # [UPDATE for demo-springboot from openjdk:8-alpine] hub.docker.com/plutomsw/cicd_guestbook:20240107064001_24(Registry/Owner/Repository:Tag)
$ docker pull quay.io/uvelyster/nginx                      # quay.io/uvelyster/nginx(Registry/Owner/Repository, Tag 생략 시 latest)
$ docker images
```

- Run
```bash
$ docker images
$ docker run nginx                                               # Forground(stdout + stderr) Mode
$ docker run openjdk
$ docker run openjdk:8-alpine
$ docker run plutomsw/demo-nginx
$ docker run quay.io/uvelyster/nginx
$ docker ps
$ docker ps -a

$ docker run -d nginx                                            # Background(stdout is none) Mode
$ docker run -d openjdk
$ docker run -d openjdk:8-alpine
$ docker run -d plutomsw/demo-nginx
$ docker run -d quay.io/uvelyster/nginx
$ docker ps
$ docker ps -a

$ docker run -i nginx                                            # Interactive(stdin + stdout + stderr) Mode
$ docker run -i openjdk
$ docker run -i openjdk:8-alpine
$ docker run -i plutomsw/demo-nginx
$ docker run -i quay.io/uvelyster/nginx
$ docker ps
$ docker ps -a
$ curl 172.17.0.2                                                # [중요] Default Container Network=172.17.0.0/16(Default: Container Host에서만 접속 가능)

$ docker run nginx echo helloworld                               # Forground(stdout + stderr) Mode + Command Parameter(echo helloworld)
$ docker run openjdk echo helloworld
$ docker run openjdk:8-alpine echo helloworld
$ docker run plutomsw/demo-nginx echo helloworld
$ docker run quay.io/uvelyster/nginx echo helloworld
$ docker ps
$ docker ps -a

$ docker run -d --name demoNginx-1 nginx                         # Background Mode(stdout is none) + Alias Name(--name)
$ docker run -d --name demoOpenJdk-1 openjdk
$ docker run -d --name demoOpenJdk8-1 openjdk:8-alpine
$ docker run -d --name myDemoNginx-1 plutomsw/demo-nginx
$ docker run -d --name demoApp-1 quay.io/uvelyster/nginx
$ docker ps
$ docker ps -a

$ docker run -it --name demoNginx-2 nginx                        # Forground Mode(stdin + stdout + stderr) / Interactive + TTY Mode(=-i -t) + Alias Name(--name)
$ docker run -it --name demoOpenJdk-2 openjdk
$ docker run -it --name demoOpenJdk8-2 openjdk:8-alpine
$ docker run -it --name myDemoNginx-2 plutomsw/demo-nginx
$ docker run -it --name demoApp-2 quay.io/uvelyster/nginx
$ docker ps
$ docker ps -a
```

- Inspect
```bash
$ docker inspect demoNginx-1                                      # Backgroud Mode and Service
$ docker inspect demoNginx-2                                      # Forground Mode
$ docker inspect demoOpenJdk-1                                    # Backgroud Mode and Non Service
$ docker inspect demoOpenJdk-2                                    # Forground Mode
$ docker inspect demoOpenJdk8-1                                   # Backgroud Mode and Non Service
$ docker inspect demoOpenJdk8-2                                   # Forground Mode
$ docker inspect myDemoNginx-1                                    # Backgroud Mode and Service
$ docker inspect myDemoNginx-2                                    # Forground Mode
$ docker rm -f $(docker ps -aq)
```

- Top, Port, Rename, Copy
```bash
$ docker run --name web1 -d nginx
$ docker run --name web2 -d -p 8080:80 nginx
$ docker top web1			# web1 컨테이너에 실행중인 프로세스 정보를 ps -ef 형식으로 출력(=docker container top web1)
$ docker top web1 aux			# web1 컨테이너에 실행중인 프로세스 정보를 ps -aux 형식으로 출력
$ docker port web1			# web1 컨테이너에 사용중인 포트 정보(=docker container port web1)
$ docker port web2			# web2 컨테이너에 사용중인 포트 정보
$ docker stop web1
$ docker ps -a
$ docker rename web1 www1						# 컨테이너명 변경(=docker container rename web1 www1)
$ docker rename web2 www2
$ docker container ls -a
$ docker cp www1:/usr/share/nginx/html/index.html .			# 컨테이너 파일을 로컬(.)로 복사(=docker container cp www1:/usr/share/nginx/html/index.html .) [참고] www2 is a only live!!!
$ nano index.html
$ docker cp ./index.html www1:/usr/share/nginx/html/index.html		# 로컬 파일을 컨테이너 파일로 복사
$ docker cp ./index.html www2:/usr/share/nginx/html/index.html		# 로컬 파일을 컨테이너 파일로 복사
$ docker exec -it www2 /bin/bash					# [중요] 해당 컨테이너에 접근=exec addtional process(i: Interactive, t: TTY) after run
$ cat /usr/share/nginx/html/index.html
$ exit									# 해당 컨테이너에서 나가기
$ docker rm -f $(docker ps -aq)
```

- Diff, Attach
```bash
$ docker run --name web1 -d -p 8080:80 nginx
$ docker diff web1		# 이미지와 컨테이너 차이점 확인(=docker container diff web1): A(Add) , C(Change), D(Delete)
$ docker stop web1
$ docker diff web1
$ docker run --name ubuntu1 -it -d ubuntu
$ docker diff ubuntu1
$ docker attach ubuntu1
# useradd user1
# passwd user1
...				# [Ctrl] + [p] + [q]
$ docker diff ubuntu1
$ docker rm -f $(docker ps -aq)
```

- Log
```bash
$ docker run --name web1 -d -p 8080:80 nginx
$ docker logs web1							# 컨테이너 로그 확인(=docker container logs web1)
$ docker inspect -f "{{.LogPath}}" web1		# 로그 파일 위치 확인
$ docker logs -f web1						# 컨테이너 로그 계속(--follow) 확인
$ docker logs -n 5 web1						# 컨테이너 로그의 마지막 5줄 확인
$ docker logs -t web1						# 컨테이너 로그 타임스템프도 확인
$ docker rm -f $(docker ps -aq)
```

- Resource and Monitoring
```bash
$ docker run -d -m 512m --oom-kill-disable=true nginx	# 메모리를 512MB로 제한하지만 초과해도 프로세스 강제 미종료(기본값: 초과하면 강세 종료)
```

## LifeCycle for Container(build > push/ship > pull > (create) > run)
```bash
$ docker run [IMAGE_NAME]                                                                   # 실행(run = create + start)
$ docker create [IMAGE_NAME]                                                                # 생성(대문자 사용 불가)
$ docker start [CONTAINTER_NAME or CONTAINTER_ID%]                                          # 시작
$ docker stats [CONTAINTER_NAME or CONTAINTER_ID%]                                          # 통계(자원 사용량 정보)
$ docker restart [CONTAINTER_NAME or CONTAINTER_ID%]                                        # 재시작
$ docker stop [CONTAINTER_NAME or CONTAINTER_ID%]                                           # SIGTERM(15)에 해당하는 안전 종료(참고: kill -l)
$ docker kill [CONTAINTER_NAME or CONTAINTER_ID%]                                           # SIGTERM(9)에 해당하는 강제 종료
$ docker logs -f [CONTAINTER_ID%]
$ docker inspect [CONTAINTER_NAME or CONTAINTER_ID%]
$ docker inspect -f '{{ .NetworkSettings.IPAddress }}' [CONTAINTER_NAME or CONTAINTER_ID%]  # IP 확인
$ docker rm [CONTAINTER_ID%]                                                                # 종료(stop or kill)되어 있어야 삭제 가능
$ docker run --rm                                                                           # 실행 후 즉시 삭제
$ docker rm -f $(docker container ls -a -q)                                                 # 모든 컨테이너 삭제(-f: 강제 중지 후 삭제) or docker ps -aq
$ docker rmi [IMAGE_NAME]                                                                   # 이미지 삭제(=docker image rm [IMAGE_NAME], 해당 컨테이너가 삭제되어야 이미지 삭제 가능, -f 시 강제 삭제)
```


## Storage(Volume) for Container
- EFK(Elastic Search + Fluentd + Kibana) vs. PLG(Promtail + Loki + Grafana) for Logging
- Storage Type
	- /var/lib/docker/volumes by Docker(volume mount)
	- ... by 사용자(bind mount)
	- ... by 메모리(tempfs mount)
```bash
$ docker volume ls
$ docker volume create demoVol1
$ docker volume inspect demoVol1
$ docker run -d --name demoApp3 -p 1235:80 -v demoVol1:/usr/share/nginx/html nginx2nd
$ curl 172.17.0.1:1235
$ curl 172.17.0.1:1234
$ curl localhost:1234
$ docker volume inspect demoVol1
$ nano /var/lib/docker/volumes/demoVol1/_data/index.html
$ curl 172.17.0.1:1235															   	# demoVol1 저장된 index.html(예: 동적 소스, 설정 등)
$ curl 172.17.0.1:1234
$ docker run -d --name demoApp4 -p 1236:80 -v demoVol1:/usr/share/nginx/html nginx2nd
$ curl 172.17.0.1:1236    																				  	# demoVol1
$ docker run -d --name demoApp5 -p 1237:80 -v /demoVol2:/usr/share/nginx/html nginx2nd  	# "/"로 시작할 경우 by 사용자(bind mount)
$ docker run -d --name demoApp6 -p 1236:80 -v  demoVol1:/usr/share/nginx/html:ro nginx2nd	# 읽기 전용 volume mount
$ docker run -d --name demoApp7 -p 1237:80 -v /demoVol3:/usr/share/nginx/html:ro nginx2nd	# 읽기 전용 bind mount
$ ls /
$ docker run -d -e MYSQL_ROOT_PASSWORD=root mysql	                                    	# MySQL 설치 시 1) 볼륨을 지장하지 않을 경우 자동 생성 2) 암호 설정(-e)
$ docker volume ls
$ docker rm -f $(docker container ls -a -q)                                             	# 모든 컨테이너 삭제(-f: 강제 중지 후 삭제) or docker ps -aq
$ docker rm -vf [Name or ID%]                                                           	# 컨테이너 삭제 시 볼륨 자동 삭제
$ docker volume ls
$ docker volume rm demoVol1                                                             	# 볼륨 수동 삭제
$ docker volume rm -f $(docker vloume ls -q)							# 모든 볼륨 삭제
$ docker volume ls
$ docker volume prune                                                                   	# 생성된 모든 볼륨을 삭제
```

	
## Network for Container
```bash
$ docker network ls                                			# Network(Default:bridge=호스트에 브릿지 네트워크 추가, host=호스트 네트워크 자체, none=없음) for Container
$ docker pull quay.io/uvelyster/busybox
$ docker tag quay.io/uvelyster/busybox busybox2nd  			# quay.io/uvelyster/busybox를 busybox2nd로 설정(tag)
$ docker images
$ docker run --rm busybox2nd ip a                  			# 실행 후 즉시 삭제(--rm), 이미지, IP 확인(ip a): 172.17.0.2 from 172.17.0.0 ~ 172.17.255.255
$ docker run --rm --network host busybox2nd ip a   			# 실행 후 즉시 삭제(--rm), 네트워크 선택(--network), 이미지(=docker.io/library/busybox:latest), IP 확인(ip a = ip addr show)
$ docker run --rm --network none busybox2nd ip a   			# 실행 후 즉시 삭제(--rm), 네트워크 선택(--network), 이미지(=docker.io/library/busybox:latest), IP 확인(ip a = ip addr show)
$ docker network inspect bridge                    			# bridge detail
$ docker tag quay.io/uvelyster/nginx nginx2nd      			# tag 설정
$ docker images
$ docker run -d busybox2nd sleep 1d                			# sleep 1d로 해당 컨테이너를 실행
$ docker network inspect bridge                    			# bridge detail
$ docker exec -it [Name or ID%] bash               			# bash로 해당 컨테이너로 접근
$ docker network create -d brigde demoNet				# 기본이 brigde이므로 -d(driver) brigde 옵션 생략 가능
$ docker network ls
$ docker network rm demoNet
$ docker network create demoNet --subnet 172.20.0.0/24                  # 사용자 정의 네트워크 생성
$ docker network ls
$ docker run -d --network demoNet --name demoApp nginx2nd               # 사용자 정의 네트워크로 컨테이너 실행
$ docker inspect demoApp | grep IP                                      # IP 확인
$ docker run -d --network demoNet --name demoApp2 -p 1000:80 nginx2nd   # 사용자 정의 네트워크로 컨테이너 실행(-p: 포트 포워딩, 요청 포트:응답 포트)
                                                                        # http://172.16.0.101:1000
$ docker rm -f $(docker container ls -a -q)                             # 모든 컨테이너 삭제(-f: 강제 중지 후 삭제) or docker ps -aq
$ docker network rm demoNet
# 기타 옵션: --add-host(/etc/hosts 설정), --dns(/etc/resolv.conf 설정), --mac-address(MAC 설정), --hostname(/etc/hostname 설정), --ip(IP 지정)
$ docker container run --rm -it --hostname www.test.com --add-host node1.test.com:172.17.0.10 --dns 192.168.10.2 centos # hotname, ip addr, ip route, cat /etc/hosts, cat /etc/hostname, df -hT, cat /etc/resolv.conf 등으로 확인
# 컨테이너 통신 방법 (1) /etc/hots (2) --link (3) 자동 이름 검색 서비스 in 사용자 정의 네트워크
```


## Summary
```bash
# Image는 myRegistry.com/hello-py:latest이며 로컬에 없을 경우 다운로드되어 백그라운드 모드로 실행(-d)
# 로컬의 /source 폴더를 컨테이너의 /data에 바인딩(-v /source:/data)
# 로컬 1000 포트에 접속할 경우 컨테이너 5000 포트로 포워딩(-p 1000:5000)
# 컨테이너 이름은 demoHelloPy
# 환경 변수는 APP=python
$ docker run -d -v /source:/data -p 1000:5000 --name demoHelloPy -e APP=python myRegistry.com/hello-py:latest

# Image는 nginx이며 로컬에 없을 경우 다운로드되어 백그라운드 모드로 실행(-d)
# 로컬의 /source 폴더를 컨테이너의 /data에 바인딩(-v /source:/data)
# 로컬 1000 포트에 접속할 경우 컨테이너 80 포트로 포워딩(-p 1000:80)
# 컨테이너 이름은 demoNginx
# 환경 변수는 ENV=hello
$ docker run -d -v /source:/data -p 1000:80 --name demoNginx -e ENV=hello nginx
$ docker exec -it demoNginx /bin/bash		# docker attach demoNginx
# exit(프로세스 종료) vs. [CTRL] + [p] +[q](프로세스 유지)
```


## Build for Container
- Build Type
  - 수동 빌드(=docker commit): 명령어(commit)로 생성 또는 실행중인 컨테이너를 기반으로 이미지 빌드
  - **자동 빌드(=docker build)**: Dockerfile을 기반으로 대부분 신규 이미지를 빌드(**베이스 이미지 선택 또는 설정이 가장 중요**)
```bash
# 수동 빌드(docker commit)
$ docker images
$ docker ps -a
$ docker run -d --name demoSpringboot -p 1000:8080 openjdk:8-alpine                     # 베이스 이미지(openjdk:8-alpine)를 이용하여 demoSpringBoot로 실행
$ docker ps -a
$ docker commit demoSpringboot demo-springboot                                          # 실행중인 컨테이너(demoSpringboot)를 demo-springboot:latest 이미지로 생성(이미지명에 대문자 사용 불가, 이하 동일)
$ docker images
$ docker tag demo-springboot:latest myRegistry.com/ID/demo-springboot                   # 태그 설정(latest 생략 가능) [참고] hug.docker.com일 경우는 도메인 생략(myRegistry.com/, 이하 동일)
$ docker images
$ docker login -u ID                                                                    # [참고] docker.io(hub.docker.com)을 사용할 경우 로그인(이하 동일)
$ docker push myRegistry.com/ID/demo-springboot                                         # [Registry] latest(=Open JDK 8 원본) 최초 등록
$ docker images
$ docker rmi demo-springboot
$ docker images
$ docker tag myRegistry.com/ID/demo-springboot myRegistry.com/ID/demo-springboot:v1     # 최종 이미지를 버전 업하기 전에 태그(demo-springboot:v1) 변경
$ docker images
$ docker push myRegistry.com/ID/demo-springboot:v1                                      # [Registry] v1(=latest=Open JDK 8 원본) 등록

$ docker rm -f $(docker container ls -a -q)
$ docker images
$ docker ps -a
$ docker run -d --name demoNginx -p 1000:80 nginx                             # 베이스 이미지(nginx)를 이용하여 demoNginx로 실행
$ docker ps -a                                                                
$ docker commit demoNginx demo-nginx                                          # 실행중인 컨테이너(demoNginx)를 demo-nginx:latest 이미지로 생성
$ docker images
$ docker tag demo-nginx myRegistry.com/ID/demo-nginx                          # 태그 설정(latest)
$ docker images
$ docker login -u ID
$ docker push myRegistry.com/ID/demo-nginx                                    # [Registry] latest(=Nginx 원본) 최초 등록
$ docker images
$ docker rmi demo-nginx
$ docker images
$ docker tag myRegistry.com/ID/demo-nginx myRegistry.com/ID/demo-nginx:v1     # 최종 이미지를 버전 업하기 전에 태그(demo-nginx:v1) 변경
$ docker images
$ docker push myRegistry.com/ID/demo-nginx:v1                                 # [Registry] v1(=latest=Nginx 원본) 등록
$ echo "hello world, First Update" > index.html
$ docker cp index.html demoNginx:/usr/share/nginx/html/index.html
$ docker commit demoNginx myRegistry.com/ID/demo-nginx                        # 1차 변경되어 실행중인 컨테이너(demoNginx)를 demo-nginx:latest 이미지로 생성
$ docker images
$ docker history nginx
$ docker history myRegistry.com/ID/demo-nginx
$ docker history myRegistry.com/ID/demo-nginx:v1
$ docker history myRegistry.com/ID/demo-nginx:v1 | wc -l
$ docker push myRegistry.com/ID/demo-nginx                                    # [Registry] latest 1차 변경 등록
$ docker ps -a
$ docker tag myRegistry.com/ID/demo-nginx myRegistry.com/ID/demo-nginx:v2     # 최종 이미지를 버전 업하기 전에 태그(demo-nginx:v2) 변경
$ docker images
$ docker push myRegistry.com/ID/demo-nginx:v2                                 # [Registry] v2 등록
$ echo "hello world, Second Update" > index.html
$ docker cp index.html demoNginx:/usr/share/nginx/html/index.html
$ docker commit demoNginx myRegistry.com/ID/demo-nginx                        # 2차 변경되어 실행중인 컨테이너(demoNginx)를 demo-nginx:latest 이미지로 생성
$ docker images
$ docker push myRegistry.com/ID/demo-nginx                                    # [Registry] latest 2차 변경 등록
$ docker ps -a
$ docker rm -f demoNginx                                                      # 컨테이너(demoNginx) 삭제(-f: 강제 중지 후 삭제)
$ docker rmi myRegistry.com/ID/demo-nginx:v1                                  # 모든 이미지(demo-nginx*) 삭제
$ docker rmi myRegistry.com/ID/demo-nginx:v2
$ docker rmi myRegistry.com/ID/demo-nginx
$ docker images
$ docker run -d --name demoNginx -p 1000:80 myRegistry.com/ID/demo-nginx      # demo-nginx:latest pull 및 run
$ docker run -d --name demoNginxV2 -p 1001:80 myRegistry.com/ID/demo-nginx:v2 # demo-nginx:v2 pull 및 run
$ docker run -d --name demoNginxV1 -p 1001:80 myRegistry.com/ID/demo-nginx:v1 # demo-nginx:v1 pull 및 run
$ docker ps -a
$ docker images

# 자동 빌드(docker build)
$ mkdir buildTest
$ vi buildTest/Dockerfile                     # [참고] Docker File Instruction
FROM busybox2nd                               # 로컬에 해당 베이스 이미지가 다운로드되어 있어야 함
CMD echo helloworld                           # CMD ["echo", "hello", "world"]
$ docker build buildTest                      # 이미지 빌드
$ docker images
$ docker tag [ID%] testimage                  # 네임 부여(대문자 제외)
$ docker run testimage
```


## Docker File Instruction
```bash
RUN commandl ; command2 ; command3      # 실패 여부에 관계없이 모두 실행
RUN commandl ; \
    command2 ; \
    command3                            # 실패 여부에 관계없이 모두 실행 + 가독성(\)
RUN commandl && command2 && command3    # 앞 부분이 성공해야 다음 실행
RUN commandl || command2 || command3    # 앞 부분이 실패해야 다음 실행
RUN commandl | command2 | command3      # 파이프 라인 실행
```

## Compose for Container
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

## Example
- WordPress(웹 페이지 제작 및 관리용 CMS) + MySQL
```bash
$ docker run -d --name mysql -v /db:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=wordpress -e MYSQL_PASSWORD=wordpress mysql:5.7
$ docker run -d --name wordpress --link mysql:mysql -e WORDPRESS_DB_PASSWORD=wordpress -p 80:80 wordpress:4
$ docekr ps -a
# firefox http://172.17.0.3 접속 후 WordPress 설정
$ docker rm -f $(docker ps -aq)
$ rm -rf /db
```

## Reference
- https://github.com/google/cadvisor
