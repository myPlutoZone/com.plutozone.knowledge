# com.plutozone.knowledge.middleware.GitLab


1. [Installation](#1-installation)
2. [Maintenance](#2-maintenance)
3. [Tip](#3-tip)


## 1. Installation
### 1-1.  Git Client
- Install **Git Client** at Ubuntu
	```bash
	$ sudo apt install git						# 설치
	$ git --version								# 버전 확인
	$ git config --global user.name pluto		# 정보 등록(이름 및 이메일)
	$ git config --global user.mail pluto@plutozone.com
	$ git config -l								# 정보 확인
	$ cat ~/.gitconfig
	$ git config --unset --global user.name		# 정보 삭제(또는 pico ~/.gitconfig)
	$ git config --unset --global user.mail
	$ git clone URL								# Git Clone 및 확인
	$ ls -al
	$ git pull									# Git Pull at Repository Directory
	```
- 접속된 계정 정보 삭제
	```bash
	git config --local --unset credential.helper	# 특정 저장소(local)만
	git config --global --unset credential.helper	# 시스템의 특정 사용자(global)만
	git config --system --unset credential.helper	# 시스템의 모든 사용자와 모든 저장소(system)
	git config --global credential.helper store		# 접속된 계정 정보를 저장(~/.git-credentials에 텍스트로 저장되므로 저장이 필요할 경우 Keychain 등을 통해 암호화를 권장)
	```

### 1-2. Git Server
- 설치 가능한 OS 및 Package
	- https://about.gitlab.com/install에서 지원 가능한 OS와 설치 방법을 참고
	- https://packages.gitlab.com/gitlab/에서 설치 가능한 Package를 확인
- Install **GitLab Server** at Ubuntu
	```bash
	$ sudo apt update && sudo apt upgrade -y										# [옵션] 사전 업데이트
	$ sudo apt install -y curl openssh-server ca-certificates tzdata perl postfix	# [옵션] CURL, Open SSH Server, SSL 인증서 처리, 서버 시간대 설정, PERL, 메일 인증 설치
	# CURL을 통해 Gitlab CE(Community Edition) 저장소 추가, 업데이트 및 설치
	$ sudo curl -sS https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.deb.sh | sudo bash
	$ sudo apt update
	# [참고] sudo EXTERNAL_URL="https://gitlab.plutozone.com" apt-get install gitlab-ce 명령을 통하여 URL을 지정하여 설치 가능
	# [참고] gitlab-ce를 gitlab-ee(Enterprise Edition)로 변경 가능
	$ sudo apt -y install gitlab-ce
	$ sudo cat /etc/gitlab/initial_root_password  | grep Password:			# roor 계정 초기 비밀번호 확인
	# [참고] SMTP 설정: https://docs.gitlab.com/omnibus/settings/smtp.html
	```

## 2. Maintenance
```bash
$ sudo pico /etc/gitlab/gitlab.rb				# 1-1. 설정 변경
...
external_url 'https://gitlab.plutozone.com'		# [참고] 도메인 또는 IP 변경 설정
...
$ sudo gitlab-ctl reconfigure					# 1-2. 설정 적용
$ sudo gitlab-ctl uninstall						# 2. Uninstall
$ sudo gitlab-ctl start							# 3. Start
$ sudo gitlab-ctl restart						# 4. Restart
$ sudo gitlab-rails console						# 5. 콘솔 접속
# Using the console find the the id of the ghost user and delete it
> user = User.find_by(username: "ghost")		# 예) ghost 계정 정보 확인
> User.delete(user.id)							# 예) 해당 계정의 ID로 계정 삭제
# If the user is removed then output would be 1, if 0 then user is not removed
```
### 2-1. 사용자 관리
- 사용자 프로젝트 생성 권한 제거
	- 사용자 등록 정보에서 프로젝트 생성 제한 갯수를 0(기본값: 100000)으로 설정 등

### 2-2. 환경 설정
- 로그인 및 신규 프로젝트 생성 화면 관리 at %GitLab%/admin/appearance

### 2-3. SSL by Let's Encrypt
```bash
$ sudo pico /etc/gitlab/gitlab.rb
...
external_url 'https://gitlab.plutozone.com/'
letsencrypt['enable'] = true
# [옵션] 이메일
letsencrypt['contact_emails'] = ['thepluto@hotmail.com']
# 예) 매월 13일 14시 50분에 인증서를 자동 갱신
letsencrypt['auto_renew_hour'] = "14"
letsencrypt['auto_renew_minute'] = "50"
letsencrypt['auto_renew_day_of_month'] = "*/13"
...
# 즉시 설정 적용
$ sudo gitlab-ctl reconfigure
# 설치된 SSL 확인
$ ls -al /etc/gitlab/
...
drwxr-xr-x 2 root root 4096 Mar 2 14:24 ssl
drwxr-xr-x 2 root root 4096 Mar 2 12:35 trusted-certs
...
# [참고] 인증서가 자동 갱신되지 않을 경우 필요 시 sudo gitlab-ctl reconfigure
# [참고] 인증서가 자동 갱신되지 않을 경우 필요 시 방화벽 확인(sudo ufw disable)
# [참고] 인증서가 자동 갱신되지 않을 경우 필요 시 80 및 443 확인
```

## 3. Tip
- runsv not running 에러 발생 시
	```bash
	$ sudo systemctl enable gitlab-runsvdir.service		# gitlab-runsvdir.service 자동 실행 설정
	$ sudo systemctl start gitlab-runsvdir.service		# gitlab-runsvdir.service Foreground 즉시 실행
	$ sudo /opt/gitlab/embedded/bin/runsvdir-start &	# gitlab-runsvdir.service Background 즉시 실행
	$ sudo gitlab-ctl start
	```
- **디스플레이(=모니터)가 없으면 gitlab-rundsvdir 자동실행 에러 발생 시**
	```bash
	$ sudo systemctl get-default						# Run Level 확인(Default: graphical.target)
	$ sudo systemctl set-default multi-user.target		# Run Level을 멀티유저 타켓으로 변경
	```
- 소스 충돌(Conflict) 시 처리(기본적으로 로컬 소스, 로컬 저장소 그리고 원격 저장소에 대한 개념을 이해해야 한다.)
	- 충돌(Synchronize 또는 Pull로 확인)이 발생한 로컬 소스에 대해 원격 저장소의 소스 조각만을 Pull한다(사유: 로컬 저장소로 Commit을 위해).
	- 조각이 적용된 로컬 소스를 로컬 저장소로 Commit한다(사유: 원격 저장소에서 Pull를 위해).
	- 원격 소스 전체를 Pull한다(참고: 이를 통해 소스가 자동 머지).
	- 로컬 소스 작업(참고: 충돌 제거 등) 후 Commit + Push한다.