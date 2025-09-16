# com.plutozone.knowledge.database.MariaDB 

- 버전 확인
```
C:\%MARIADB%\bin\mysql.exe --version				# cmd
$ mysql --version									# bash
SELECT VERSION();									# sql
```
- 설치
```bash
# MariaDB 다운로드 및 설치: https://downloads.mariadb.org/mariadb/repositories
# [권장] Database와 MariaDB를 관리할 별도 그룹(예: dba) 및 계정(예: mariadb, sudo 권한 포함)으로 진행
$ sudo apt-get install software-properties-common	# [선택] 소프트웨어 프로퍼티 공통 설치
$ sudo apt-get install apt-transport-https curl
$ sudo mkdir -p /etc/apt/keyrings
$ sudo curl -o /etc/apt/keyrings/mariadb-keyring.pgp 'https://mariadb.org/mariadb_release_signing_key.pgp'
$ sudo nano /etc/apt/sources.list.d/mariadb.sources
# MariaDB 12.1 repository list - created 2025-09-02 13:49 UTC
# https://mariadb.org/download/
X-Repolib-Name: MariaDB
Types: deb
# deb.mariadb.org is a dynamic mirror if your preferred mirror goes offline. See https://mariadb.org/mirrorbits/ for details.
# URIs: https://deb.mariadb.org/11.rc/ubuntu
URIs: https://tw1.mirror.blendbyte.net/mariadb/repo/12.1/ubuntu
Suites: focal
Components: main main/debug
Signed-By: /etc/apt/keyrings/mariadb-keyring.pgp
$ sudo apt-get update
$ sudo apt-get install mariadb-server
$ sudo pico /etc/mysql/my.cnf							# [참고] MariaDB 버전에 따라 include된 파일에서도 확인 필요
...
# bind-address = 127.0.0.1								# 외부 접속 허용(주석 처리 또는 0.0.0.0 또는 ::)
$ sudo mariadb-secure-installation						# [선택] 필요 시 root 설정
$ mariadb -u root -p
> show databases;
> use mysql;
> create user 'root'@'아이피' identified by '암호';		# 10.4 version 이상
> insert into user \
 (host, user, password, ssl_cipher, x509_issuer, x509_subject, authentication_string) \
 values
 ('아이피', 'root', password('암호'), '', '', '', '');	# 10.4 version 미만
> grant all privileges on *.* to 'root'@'아이피' identified by '암호' with grant option;
> flush privileges;
> exit;
$ sudo systemctl restart mariadb
```
- Slow Query 로깅 설정
- 백업과 복원
	- SQL 기반
	- Binary 기반
- HA(High Availability) vs. Galera Cluster for MariaDB or MySQL