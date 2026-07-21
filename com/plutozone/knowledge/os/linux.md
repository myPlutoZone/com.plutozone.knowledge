# com.plutozone.knowledge.os.Linux

## License
- 대부분의 라이센스는 `소스 공개 의무`를 수행 시 `상업적 사용 가능`
- 카피레프트(Copyleft)는 `지식재산권에 대한 독점을 의미하는 '카피라이트(Copyright)'에 반대되는 개념`으로 창작자의 저작권은 인정하되 누구나 그 창작물을 자유롭게 복제, 수정, 배포할 수 있도록 하여 정보와 지식을 공유하자는 운동이나 라이선스 방식
- `권장 기준`
	- `Apache License 2.0`: 특허 관련 보호가 중요하고 기업 친화적인 라이선스를 원할 때
	- MIT/BSD/ISC: 최대한 간단하고 제약이 적은 라이선스를 원할 때
	- MPL 2.0: 수정한 파일은 공개하되 전체 프로젝트 공개는 원하지 않을 때
	- GPL/LGPL: 오픈소스 기여와 파생 저작물의 공개를 강하게 또는 일부 보장하고 싶을 때

| License      | 상업 사용  | 수정 가능  | 소스 공개 의무     | 특허 조항       | Copyleft |
| ------------ | :-------: | :-------: | :-------------: | :------------: | ----------- |
| Apache 2.0   |     O     |     O     |       X         |       O        |     X       |
| MIT          |     O     |     O     |       X         |       △       |     X       |
| BSD 2-Clause |     O     |     O     |       X         |       △       |     X       |
| BSD 3-Clause |     O     |     O     |       X         |       △       |     X       |
| ISC          |     O     |     O     |       X         |       △       |     X       |
| MPL 2.0      |     O     |     O     | 수정한 파일만     |       O        | 약한 Copyleft |
| LGPL         |     O     |     O     | 라이브러리 수정 시 |       O        | 약한 Copyleft |
| GPL v3       |     O     |     O     |       O         |        O       | 강한 Copyleft |


## 리눅스 배포판 and Apache Web Server
- Redhat 계열: Redhat, fedora, CentOS, Rocky
	- SELinux 활성 기본
	- yum, dnf
	- yum install httpd
- Debian 계열: Debian, Ubuntu, Kali
	- SELinux 활성 수동
	- apt-get, apt
	- apt install apache2
- Apache Web Server
	- systemctl start/enable/disable/stop/restart/status httpd(apache2)
	- firewall-cmd --permanent --add-port=80/tcp
	- firewall-cmd --reload


## Default Setting for Linux System
```bash
# KST
$ sudo timedatectl set-timezone Asia/Seoul

# YYYY-MM-DD HH:mm:SS
$ nano ~/.bashrc
...
alias ls='ls --color=auto --time-style="+%Y-%m-%d %H:%M:%S"'
$ source ~/.bashrc

# Login Information
$ sudo echo 'pluto ALL=NOPASSWD:/usr/bin/lastb, /var/log/btmp' >> /etc/sudoers
$ nano ~/.profile
...
# 최근 실패한 로그인(9)
echo "-------------------------------------------------------"
echo " 9 Recent Failed Logins"
echo "-------------------------------------------------------"
sudo lastb -9

# 최근 마지막 로그인(9)
echo "-------------------------------------------------------"
echo " Last 9 Recent Logins"
echo "-------------------------------------------------------"
lastlog -t 9

# 최근 로그인과 로그아웃(9)
echo "-------------------------------------------------------"
echo " 9 Most Recent Logins and Logouts"
echo "-------------------------------------------------------"
last -9
```

## . vs. source vs. bash vs. chmod
```bash
$ touch shell.sh 
$ nano shell.sh
#!/bin/bash
ls -l
$ . shell.sh
$ source shell.sh
$ bash shell.sh			# sh shell.sh
$ chmod 755 shell.sh
$ ./shell.sh
```