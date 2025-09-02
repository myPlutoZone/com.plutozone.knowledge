# com.plutozone.knowledge.os.Ubuntu(아프리카 반투어의 말로 "네가 있어 내가 있다"라는 뜻)

- User, Group and sudo(root)
```bash
# 그룹 추가, 삭제 및 현재 계정의 그룹 확인
$ sudo groupadd GROUP
$ sudo groupdel GROUP
$ sudo groups

# 계정 추가(GROUP 포함)
$ sudo useradd -g GROUP USER

# 계정 암호 설정, 삭제(계정만) 그리고 삭제(전체)
$ sudo passwd USER
$ sudo userdel USER
$ sudo userdel -r USER

# 계정의 홈 폴더 생성
$ sudo mkdir /home/ USER

# 파일 또는 폴더의 소유권 변경
$ sudo chown USER:GROUP FILE_OR_DIR

# 계정의 셀(bash) 변경
$ sudo usermod -s /bin/bash USER

# 현재 계정 및 폴더 확인
$ whoami
$ pwd

# [중요] sudo 권한 부여 방안(1) - visudo(반드시 기존 이름인 /etc/sudoers로 저장)
$ sudo visudo
…
root ALL=(ALL:ALL) ALL
# 계정 단위 추가("계정명" "현재 시스템에 접속 허용된 시스템"=(가능 그룹:가능 계정)[가능 명령])
USER ALL=(ALL:ALL) ALL
…
%admin ALL=(ALL) ALL
# 그룹 단위 추가(%"그룹명" "현재 시스템에 접속 허용된 시스템]=(가능 계정)[가능 명령])
%GROUP ALL=(ALL) ALL
…

# [중요] sudo 권한 부여 방안(2) - 계정을 sudo(=root) 그룹에 추가
$ sudo usermod -aG sudo USER
```

- Firewall
```bash
# ufw status									# 방화벽 상태
# ufw disable									# 방화벽 미사용
# ufw allow 22									# Allow 22
# ufw allow from 192.168.0.100
# ufw allow from 192.168.0.0/24
# ufw allow from 192.168.0.100 to any port 22
# ufw allow from 192.168.0.0/24 to any port 22
# ufw enable									# 방화벽 사용
# ufw status									# 방화벽 상태
# ufw deny 22									# Deny 22
# ufw delete deny 22							# Delete Deny 22
# pico /etc/default/ufw
...
IPV6=no
...
# ufw disable
# ufw enable
# ufw status
# ufw reset										# 모든 규칙 삭제
```

- stress
```bash
$ sudo apt install stress
$ stress --cpu 2 --timeout 300		# 2개의 CPU 코어에 300초(5분) 동안 100% 부하
```

- top vs. htop

- 호스트 네임 변경
```bash
$ sudo hostnamectl set-hostname plutozone
```

- cloud-init
```bash
# cloud-init를 비활성화(disabled) 또는 제거
# 방법 1) cloud-init를 비활성화(disabled)하는 파일을 생성하고 재부팅한다.
sudo touch /etc/cloud/cloud-init.disabled
sudo reboot

# 방법 2) cloud-init 패키지와 폴더를 삭제하고 재부팅한다.
sudo apt purge cloud-init -y
sudo rm -rf /etc/cloud && sudo rm -rf /var/lib/cloud/
sudo reboot
sudo hostnamectl set-hostname plutozone
```

- 시간대(TimeZone) 설정
```bash
# 시간대를 서울(KST)로 변경
pluto@ubuntu:~$ sudo timedatectl set-timezone Asia/Seoul

# 변경된 시간대 확인
pluto@ubuntu:~$ date
```

- 계정(su: Switch User) 전환
```bash
pluto@ubuntu:~$ su 계정명		# 계정명으로만 전환
pluto@ubuntu:~$ su - 계정명		# 계정명+환경으로 전환
pluto@ubuntu:~$ su -			# root로 계정(환경 포함) 전환
pluto@ubuntu:~$ sudo -i			# root로 계정(환경 포함) 전환
root@ubutu:~# cd /
root@ubutu:/#
```

- 고정(Static) IP 설정 over Ubuntu 20.04.x
```bash
# 고정 IP 설정
pluto@ubuntu:~$ sudo nano /etc/netplan/*.yaml
network:
 ethernets:
  enp0s3:
   dhcp4: no
   addresses: [192.168.0.100/24]
   gateway4: 192.168.0.1
   nameservers:
    addresses: [168.126.63.1,8.8.8.8]
   routes:
   - to: 100.100.100.0/24
     via: 192.168.0.1
 version: 2

# 설정 적용
pluto@ubuntu:~$ sudo netplan apply

# 설정 확인
pluto@ubuntu:~$ netstat -rn
```

- Ubuntu Desktop(X-Windows) Software 업데이트 실패 시(=Snap Store 업데이트 오류)
```bash
$ sudo killall snap-store			# Snap Store 종료
$ sudo snap refresh					# Snap Store 갱신
$ snap refresh snap-store			# 상기(Snap Store 갱신) 오류 발생 시
# Setting > Ubuntu Software에서 Snap Store 업데이트 확인
```