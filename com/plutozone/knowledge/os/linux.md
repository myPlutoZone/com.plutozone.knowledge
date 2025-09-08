# com.plutozone.knowledge.os.Linux


- Default Setting for PlutoZone
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
- . vs. source vs. bash vs. chmod
```bash
$ touch shell.sh 
$ nano shell.sh
#!/bin/bash
ls -l
$ . shell.sh
$ source shell.sh
$ bash shell.sh
$ chmod 755 shell.sh
$ ./shell.sh
```
- 리눅스 배포판
	- Redhat 계열: Redhat, fedora, CentOS, Rocky
		- SELinux 활성 기본
  		- yum, dnf
    	- yum install httpd
	- Debian 계열: Debian, Ubuntu, Kali
		- SELinux 활성 수동
  		- apt-get, apt
      	- apt install apache2
	- 공통
		- systemctl start/enable/disable/stop/restart/status httpd(apache2)
	 	- firewall-cmd --permanent --add-port=80/tcp
	    - firewall-cmd --reload
- GPL(코드 미공개 불가 + 상업화 불가) vs. LGPL(라이브러리화 + 상업화 일부 가능) vs. MIT/BSD(코드 미공개 가능 + 상업화 가능)
