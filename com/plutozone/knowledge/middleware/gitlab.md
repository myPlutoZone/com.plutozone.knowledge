# com.plutozone.knowledge.middleware.GitLab


## Installation
### Git Client
- Install **Git Client** at Ubuntu
	```bash
	$ sudo apt install git						# 설치
	$ git --version								# 버전 확인
	$ git config --global user.name pluto		# 정보 등록(이름 및 이메일)
	$ git config --global user.mail pluto#plutozone.com
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

### Git Server
- 설치 가능한 OS 및 Package
	- https://about.gitlab.com/install에서 지원 가능한 OS와 설치 방법을 참고
	- https://packages.gitlab.com/gitlab/에서 설치 가능한 Package를 확인
- Install **GitLab Server** at Ubuntu
	```bash
	$ sudo apt update && sudo apt upgrade -y										# [옵션] 사전 업데이트
	$ sudo apt install -y curl openssh-server ca-certificates tzdata perl postfix	# [옵션] CURL, Open SSH Server, SSL 인증서 처리, 서버 시간대 설정, PERL, 메일 인증 설치
	$ sudo curl -sS https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.deb.sh | sudo bash		# CURL을 통해 Gitlab CE(Community Edition) 저장소 추가, 업데이트 및 설치
	$ sudo apt update
	$ sudo apt -y install gitlab-ce				# [참고] sudo EXTERNAL_URL="https://gitlab.plutozone.com" apt-get install gitlab-ce 명령을 통하여 URL을 지정하여 설치 가능
												# [참고] gitlab-ce를 gitlab-ee(Enterprise Edition)로 변경 가능
	$ sudo cat /etc/gitlab/initial_root_password  | grep Password:			# roor 계정 초기 비밀번호 확인
	# [참고] SMTP 설정: https://docs.gitlab.com/omnibus/settings/smtp.html
	```

## Maintenance
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

## Performance


## Command


## Tip
- runsv not running 에러 발생 시
	```bash
	$ sudo systemctl enable gitlab-runsvdir.service		# gitlab-runsvdir.service 자동 실행 설정
	$ sudo systemctl start gitlab-runsvdir.service		# gitlab-runsvdir.service Foreground 즉시 실행
	$ sudo /opt/gitlab/embedded/bin/runsvdir-start &	# gitlab-runsvdir.service Background 즉시 실행
	$ sudo gitlab-ctl start
	```
- 디스플레이(=모니터)가 없으면 gitlab-rundsvdir 자동실행 에러 발생 시
	```bash
	$ sudo systemctl get-default						# Run Level 확인(Default: graphical.target)
	$ sudo systemctl set-default multi-user.target		# Run Level을 멀티유저 타켓으로 변경
	```