# com.plutozone.knowledge.os.Linux


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
