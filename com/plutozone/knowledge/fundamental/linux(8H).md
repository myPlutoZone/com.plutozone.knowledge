# 리눅스 서버 운영·관리 실무 과정 — 8시간 완성 커리큘럼

## 1\. 교육 개요

리눅스 서버를 웹 애플리케이션 서버(Web Application Server)로 구축하고, 서비스를 배포·운영하며 장애와 보안 문제에 대응하기 위한 실무 교육 과정이다.

- **교육 시간:** 총 8시간(480분)
- **대상:** 개발자, 시스템 관리자, 리눅스 서버 입문자
- **기준 환경:** Ubuntu Server / Rocky Linux
- **교육 방식:** 개념 30%, 터미널 실습 50%, 장애 대응 20%

### 교육 목표

- 리눅스 서버 접속 및 사용자·권한 관리
- 네트워크, DNS, 포트, 방화벽 점검
- Nginx 설치 및 웹 애플리케이션 배포
- systemd 기반 서비스 관리 및 자동 시작 설정
- 로그 분석 및 CPU·메모리·디스크 모니터링
- SSH 보안, HTTPS, 백업 및 복구 수행

## 2\. 전체 교육 시간표

| 교시 | 주제 | 시간 |
| --- | --- | --- |
| 1 | 리눅스 기초 및 파일·권한 관리 | 60분 |
| 2 | 네트워크와 서버 통신 | 60분 |
| 3 | 웹 서버와 애플리케이션 배포 | 90분 |
| 4 | 프로세스와 서비스 운영 | 60분 |
| 5 | 모니터링과 장애 대응 | 60분 |
| 6 | 보안·백업·업데이트 | 60분 |
| 7 | 실전 종합 프로젝트 | 90분 |
| **합계** |  | **480분** |

---

## 3\. 1교시 — 리눅스 기초 및 파일·권한 관리 (60분)

### 3.1 주요 디렉터리

| 경로 | 용도 |
| --- | --- |
| `/etc` | 시스템 및 서비스 설정 |
| `/var/log` | 시스템 및 애플리케이션 로그 |
| `/var/www` | 웹 콘텐츠 배치 경로 |
| `/opt` | 별도 애플리케이션 설치 경로 |
| `/home` | 일반 사용자 홈 디렉터리 |
| `/tmp` | 임시 파일 |
| `/usr/bin` | 일반 명령어 실행 파일 |
| `/run` | 런타임 상태 정보 |

### 3.2 기본 명령어

```
# 현재 위치와 파일 목록
pwd
ls -lah
cd /var/log

# 디렉터리 및 파일 관리
mkdir -p /opt/myapp
cp app.conf app.conf.bak
mv old.conf new.conf
cat /etc/os-release
less /var/log/syslog

# 검색
find /var/log -type f
grep -R "ERROR" /var/log/myapp/

# 용량 확인
df -h
du -sh /var/log
```

### 3.3 사용자와 권한

```
whoami
id
groups

# 시스템 서비스 전용 계정 생성 예시
sudo useradd --system --no-create-home \
  --shell /usr/sbin/nologin myapp

# 파일 소유자 및 권한 설정
sudo chown -R myapp:myapp /opt/myapp
sudo chmod 750 /opt/myapp
```

운영 원칙:

- `chmod 777`을 습관적으로 사용하지 않는다.
- 애플리케이션을 가능한 한 root가 아닌 전용 계정으로 실행한다.
- 설정 파일과 비밀정보는 최소 권한으로 보호한다.

### 3.4 패키지 관리

Ubuntu/Debian:

```
sudo apt update
sudo apt install nginx curl
sudo apt list --upgradable
```

Rocky Linux/RHEL 계열:

```
sudo dnf check-update
sudo dnf install nginx curl
sudo dnf list updates
```

### 실습 과제

- [ ]`/opt/myapp` 디렉터리 생성
- [ ]로그 파일 생성 및 검색
- [ ]사용자와 서비스 계정의 차이 확인
- [ ]디스크 사용량 확인

---

## 4\. 2교시 — 네트워크와 서버 통신 (60분)

### 4.1 핵심 개념

- IP 주소와 서브넷
- 게이트웨이와 라우팅
- DNS 이름 해석
- TCP 포트와 서비스
- HTTP 및 HTTPS
- 서버 방화벽과 클라우드 보안 그룹

### 4.2 주요 포트

| 포트 | 용도 |
| --- | --- |
| 22/TCP | SSH |
| 80/TCP | HTTP |
| 443/TCP | HTTPS |
| 3000, 8000, 8080 | 애플리케이션 포트 예시 |
| 5432/TCP | PostgreSQL |
| 3306/TCP | MySQL |

### 4.3 네트워크 진단

```
ip addr
ip route

# DNS 확인
getent hosts example.com

# 수신 대기 포트 확인
sudo ss -lntp

# HTTP 요청 확인
curl -I http://127.0.0.1:8080
curl -v https://example.com

# 네트워크 연결 확인
ping -c 4 1.1.1.1
```

`ping`이 실패해도 ICMP 차단 때문에 웹 서비스는 정상일 수 있으므로 다른 진단 결과와 함께 판단한다.

### 4.4 SSH 접속

```
ssh admin@192.0.2.10

# 키 기반 인증
ssh -i ~/.ssh/server_key admin@192.0.2.10
```

운영 원칙:

- 개인 키를 안전하게 보관한다.
- root 직접 로그인과 비밀번호 인증 제한을 검토한다.
- SSH 설정 변경 후 새 세션에서 접속을 검증한다.
- 클라우드 보안 그룹과 서버 방화벽을 모두 확인한다.

### 4.5 방화벽

Ubuntu/UFW 예시:

```
sudo ufw status verbose
sudo ufw allow OpenSSH
sudo ufw allow 80/tcp
sudo ufw allow 443/tcp
```

Rocky Linux/firewalld 예시:

```
sudo firewall-cmd --state
sudo firewall-cmd --list-all
```

방화벽을 활성화하기 전에 SSH 접근 규칙을 확인한다.

### 실습 과제

- [ ]IP 주소와 기본 게이트웨이 확인
- [ ]DNS 조회
- [ ]포트 상태 확인
- [ ]HTTP 상태 코드 확인
- [ ]방화벽 규칙 확인

---

## 5\. 3교시 — 웹 서버와 애플리케이션 배포 (90분)

### 5.1 웹 서비스 구조

일반적인 구성은 다음과 같다.

```
사용자 브라우저
      |
      v
DNS / 방화벽 / TLS
      |
      v
Nginx (Reverse Proxy)
      |
      v
애플리케이션 프로세스
(Node.js / Python / Java 등)
      |
      v
데이터베이스 / 외부 서비스
```

- **Nginx:** HTTP 처리, 정적 파일 제공, 리버스 프록시
- **애플리케이션:** 비즈니스 로직 실행
- **데이터베이스:** 영속 데이터 저장

### 5.2 Nginx 설치 및 실행

Ubuntu:

```
sudo apt update
sudo apt install nginx
sudo systemctl enable --now nginx
sudo systemctl status nginx
curl -I http://127.0.0.1
```

Rocky Linux:

```
sudo dnf install nginx
sudo systemctl enable --now nginx
sudo systemctl status nginx
```

서비스가 실행 중이어도 방화벽이나 외부 네트워크 설정에 따라 외부 접속은 실패할 수 있다.

### 5.3 Nginx 리버스 프록시 설정

Ubuntu/Debian에서는 `sites-available` 및 `sites-enabled`를 사용할 수 있고, Rocky Linux에서는 `/etc/nginx/conf.d/`를 주로 사용한다.

예시 설정:

```
server {
    listen 80;
    server_name example.com;

    location / {
        proxy_pass http://127.0.0.1:3000;

        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

이 설정은 HTTP 요청을 로컬 3000번 포트의 애플리케이션으로 전달한다.

설정 검증 및 적용:

```
sudo nginx -t
sudo systemctl reload nginx
sudo systemctl status nginx
sudo journalctl -u nginx -n 100 --no-pager
```

반드시 설정 문법 검사가 성공한 후 적용한다.

### 5.4 애플리케이션 배포 절차

1. 런타임 및 의존성 설치
2. 코드 또는 빌드 산출물 배치
3. 환경변수 및 설정 준비
4. 소유권과 파일 권한 설정
5. 애플리케이션 실행
6. 로컬 포트 응답 확인
7. Nginx 리버스 프록시 연결
8. 외부 HTTP 요청 검증

| 기술 | 실행 환경 예시 |
| --- | --- |
| Node.js | Node.js, npm |
| Python | Python 가상환경, Gunicorn/Uvicorn |
| Java | JRE/JDK, 실행 가능한 JAR |
| PHP | PHP-FPM |
| Go | 컴파일된 실행 파일 |

애플리케이션이 로컬에서는 정상인데 도메인으로 접속되지 않는다면 Nginx, DNS, 방화벽, TLS를 순서대로 점검한다.

### 실습 과제

- [ ]Nginx 설치 및 실행
- [ ]기본 페이지 응답 확인
- [ ]테스트 애플리케이션 실행
- [ ]리버스 프록시 구성
- [ ]설정 검증 및 적용
- [ ]프록시 오류 분석

---

## 6\. 4교시 — 프로세스와 서비스 운영 (60분)

### 6.1 프로세스 관리

```
ps aux
pgrep -af myapp
top
sudo ss -lntp

# 정상 종료 요청
kill -TERM <PID>
```

`kill -9`는 정상 종료가 실패했을 때 제한적으로 사용한다.

### 6.2 systemd 서비스 등록

파일 경로: `/etc/systemd/system/myapp.service`

```
[Unit]
Description=My Web Application
After=network.target

[Service]
Type=simple
User=myapp
Group=myapp
WorkingDirectory=/opt/myapp
ExecStart=/opt/myapp/venv/bin/python /opt/myapp/app.py
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
```

이 예시는 Python 애플리케이션 기준이며 실제 경로와 실행 명령은 프로젝트에 맞게 변경해야 한다.

서비스 등록 및 관리:

```
sudo systemctl daemon-reload
sudo systemctl enable --now myapp

sudo systemctl status myapp
sudo systemctl restart myapp
sudo systemctl stop myapp
sudo systemctl is-enabled myapp
```

### 6.3 로그 확인

```
# 최근 로그
sudo journalctl -u myapp -n 100 --no-pager

# 실시간 로그
sudo journalctl -u myapp -f

# 오늘의 로그
sudo journalctl -u myapp --since today

# 오류 로그
sudo journalctl -u myapp -p err
```

로그 파일을 직접 생성하는 애플리케이션은 로그 회전 정책을 설정하여 디스크 고갈을 예방한다.

### 6.4 환경변수와 비밀정보

- 비밀번호와 API 키를 소스 코드에 직접 넣지 않는다.
- 환경별 설정을 분리한다.
- 비밀정보 파일은 최소 권한으로 보호한다.
- Git 저장소에 `.env`와 비밀정보가 올라가지 않도록 한다.
- 가능하면 별도 비밀정보 관리 서비스를 활용한다.

### 실습 과제

- [ ]systemd 서비스 등록
- [ ]자동 시작 설정
- [ ]서비스 중지 및 재시작
- [ ]실행 오류 발생 후 로그 분석
- [ ]자동 재시작 동작 확인

---

## 7\. 5교시 — 모니터링과 장애 대응 (60분)

### 7.1 주요 진단 명령어

| 대상 | 명령어 | 목적 |
| --- | --- | --- |
| CPU/메모리 | `top` | 자원 사용 프로세스 확인 |
| 메모리 | `free -h` | 가용 메모리와 스왑 확인 |
| 디스크 | `df -h` | 파일시스템 사용률 확인 |
| 디렉터리 용량 | `du -xhd1 /var` | 용량이 큰 경로 확인 |
| 프로세스 | `ps aux` | 프로세스 상태 확인 |
| 포트 | `ss -lntp` | 수신 대기 포트 확인 |
| 시스템 오류 | `journalctl -p err -b` | 현재 부팅의 오류 확인 |
| 커널 메시지 | `dmesg -T` | 커널 관련 메시지 확인 |

메모리는 `free -h`의 `available` 항목도 함께 확인한다.

### 7.2 웹 서비스 확인

```
curl -sS -o /dev/null \
  -w 'HTTP %{http_code} | time %{time_total}s\n' \
  http://127.0.0.1/

sudo tail -n 100 /var/log/nginx/error.log
sudo journalctl -u myapp --since "-15 minutes"
```

### 7.3 장애 대응 표준 절차

1. 증상과 영향 범위를 파악한다.
2. CPU, 메모리, 디스크, 포트, 서비스 상태를 수집한다.
3. DNS → 네트워크 → Nginx → 애플리케이션 → DB 순서로 원인을 분석한다.
4. 원인에 맞는 최소 범위의 조치를 적용한다.
5. 서비스 복구와 정상 응답을 검증한다.
6. 장애 원인과 조치 결과를 기록한다.

### 7.4 대표 장애 유형

| 증상 | 우선 확인 항목 |
| --- | --- |
| 접속 불가 | DNS, 방화벽, 포트, Nginx |
| HTTP 502 | 백엔드 프로세스, 프록시 주소 및 포트 |
| HTTP 504 | 백엔드 지연, DB, 타임아웃 |
| HTTP 403 | 파일 권한, 접근 규칙, SELinux |
| HTTP 500 | 애플리케이션 로그와 예외 |
| 디스크 부족 | `df -h`, `du`, 로그 파일 |
| 메모리 부족 | `free -h`, `top`, OOM 로그 |

### 실습 과제

- [ ]애플리케이션 프로세스 중단 후 502 분석
- [ ]Nginx와 애플리케이션 로그 비교
- [ ]디스크 사용량 분석
- [ ]장애 조치 전후 상태 기록

---

## 8\. 6교시 — 보안·백업·업데이트 (60분)

### 8.1 서버 보안

- SSH 키 기반 인증과 관리 접근 제한
- 사용자 및 서비스 계정의 최소 권한 적용
- 외부에 공개할 포트만 허용
- HTTPS/TLS 인증서 관리
- 보안 패치와 취약점 대응
- 비밀정보의 안전한 저장
- 인증 실패와 권한 변경 로그 확인
- 애플리케이션 의존성 및 입력값 보안 점검

SSH 설정 변경 시 기존 세션을 유지하고 새 세션에서 접속을 검증한다.

### 8.2 HTTPS 관리

운영 웹 서비스에는 유효한 TLS 인증서를 사용한다.

점검 항목:

- 도메인이 올바른 서버를 가리키는가?
- 필요한 포트가 외부에서 접근 가능한가?
- 인증서 도메인과 유효기간이 올바른가?
- 자동 갱신이 정상적으로 동작하는가?
- 신뢰할 수 있는 프록시의 전달 헤더만 신뢰하는가?

Certbot 등 인증서 관리 도구를 사용할 수 있으며, 실제 설정은 서버 환경에 맞게 구성한다.

### 8.3 백업 전략

| 대상 | 백업 고려사항 |
| --- | --- |
| 애플리케이션 코드 | 버전 관리 및 배포 산출물 |
| 설정 파일 | Nginx, systemd, 환경 설정 |
| 데이터베이스 | DB 전용 백업 및 일관성 |
| 업로드 파일 | 변경 시점과 파일 권한 |
| 비밀정보 | 암호화 및 접근 제한 |
| 백업 사본 | 별도 서버·계정·리전 보관 |

백업은 실제 복구 테스트까지 수행해야 한다.

- **RPO:** 허용 가능한 데이터 손실 범위
- **RTO:** 장애 후 서비스 복구까지 허용되는 시간

### 8.4 업데이트 및 변경 관리

1. 변경 전 설정과 데이터 백업
2. 변경 내용과 영향 범위 검토
3. 테스트 환경에서 검증
4. 운영 환경에 단계적 적용
5. 서비스 상태와 로그 확인
6. 문제 발생 시 검증된 롤백 절차 실행

### 실습 과제

- [ ]공개 포트 점검
- [ ]서비스 계정 권한 확인
- [ ]설정 파일 백업 및 복구
- [ ]데이터베이스 복구 절차 설계
- [ ]HTTPS 인증서 갱신 확인

---

## 9\. 7교시 — 실전 종합 프로젝트 (90분)

### 9.1 프로젝트 목표

Nginx와 애플리케이션을 연결하고, systemd로 관리하며, 장애 발생 시 원인을 분석하고 복구한다.

| 단계 | 작업 | 시간 |
| --- | --- | --- |
| 1 | 사용자·권한·패키지·방화벽 점검 | 15분 |
| 2 | 애플리케이션 배포 및 로컬 응답 확인 | 20분 |
| 3 | Nginx 연결 및 systemd 등록 | 20분 |
| 4 | 장애 유발, 로그 분석 및 복구 | 20분 |
| 5 | 운영 점검표 작성 | 15분 |
| **합계** |  | **90분** |

### 9.2 종합 점검 명령어

```
# 운영체제 및 자원
cat /etc/os-release
uptime
free -h
df -h

# 서비스 상태
sudo systemctl status nginx
sudo systemctl status myapp

# 포트 확인
sudo ss -lntp

# 애플리케이션 응답
curl -v http://127.0.0.1:3000/

# 웹 서버 응답
curl -I http://127.0.0.1/

# 로그 확인
sudo journalctl -u myapp -n 100 --no-pager
sudo journalctl -u nginx -n 100 --no-pager

# 자동 시작 확인
sudo systemctl is-enabled nginx
sudo systemctl is-enabled myapp
```

실제 애플리케이션 포트에 맞게 주소를 변경한다.

### 9.3 장애 대응 평가

- **상황 A:** Nginx는 정상이나 HTTP 502가 발생한다.
- **상황 B:** 재부팅 후 애플리케이션이 실행되지 않는다.
- **상황 C:** 디스크 사용률이 95%를 초과한다.
- **상황 D:** 내부 접속은 되지만 외부 접속은 실패한다.
- **상황 E:** HTTPS 인증서가 만료되었다.

각 상황에 대해 원인, 진단 명령어, 조치 방법, 복구 검증 방법을 설명한다.

---

## 10\. 실무 기술 학습 우선순위

### 1순위: 기본 운영 능력

- 리눅스 셸 명령어
- 파일과 사용자 권한
- SSH 및 네트워크
- systemd
- 로그 분석

### 2순위: 웹 서비스 운영

- Nginx
- DNS 및 HTTP/HTTPS
- TLS 인증서
- 리버스 프록시
- 애플리케이션 배포

### 3순위: 안정적인 운영과 자동화

- 백업 및 복구
- 모니터링
- 배포 자동화
- 컨테이너
- 성능 분석

### 추가 학습 기술

| 기술 | 학습 목적 |
| --- | --- |
| Docker / Docker Compose | 실행 환경 표준화 |
| Git 및 CI/CD | 빌드·테스트·배포 자동화 |
| Prometheus / Grafana | 메트릭 수집과 시각화 |
| 중앙 로그 관리 | 여러 서버의 로그 통합 |
| Ansible | 서버 설정 자동화 |
| 데이터베이스 운영 | 백업, 복구, 성능 관리 |
| 로드 밸런싱 | 여러 서버로 트래픽 분산 |
| 클라우드 운영 | 서버, 스토리지, 네트워크 관리 |

---

## 11\. 최종 실무 체크리스트

### 서버 기본 관리

- [ ]SSH로 안전하게 접속한다.
- [ ]사용자·그룹·파일 권한을 관리한다.
- [ ]CPU·메모리·디스크 상태를 확인한다.

### 웹 서비스 구축

- [ ]Nginx를 설치하고 설정을 검증한다.
- [ ]애플리케이션과 리버스 프록시를 연결한다.
- [ ]systemd 서비스와 자동 시작을 설정한다.

### 장애 대응

- [ ]포트·DNS·방화벽 문제를 구분한다.
- [ ]HTTP 502·500 오류를 로그로 분석한다.
- [ ]서비스를 복구하고 정상 응답을 검증한다.

### 운영 보안

- [ ]SSH와 방화벽 접근 범위를 점검한다.
- [ ]HTTPS 인증서와 갱신 상태를 확인한다.
- [ ]설정과 데이터를 백업하고 복구를 검증한다.

---

## 12\. 마무리

리눅스 서버 운영의 핵심은 명령어를 많이 아는 것이 아니라 다음 역량을 갖추는 것이다.

1. 서비스를 안전하게 실행한다.
2. 시스템과 애플리케이션의 상태를 관찰한다.
3. 로그를 기반으로 장애 원인을 진단한다.
4. 검증된 방법으로 서비스를 복구한다.
5. 백업과 보안 정책을 통해 재발 위험을 줄인다.

8시간의 기본 교육을 마친 후 Docker와 CI/CD를 추가로 학습하면 개발부터 배포·운영까지 연결되는 실무 역량을 키울 수 있다.

위 문서의 내용을 복사해 `linux_web_server_8hour_course.md`라는 이름으로 UTF-8 인코딩 저장하면 Markdown 파일로 사용할 수 있습니다. 현재 이 대화에서는 실제 다운로드 파일을 생성해 첨부하지는 못했습니다.