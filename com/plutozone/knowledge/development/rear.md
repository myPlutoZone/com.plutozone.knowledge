# com.plutozone.knowledge.development.Rear


# 목차
- 설치 at Redhat or CentOS
- 백업 설정 및 옵션
- 백업 실행과 로그
- 복원 실행


# 설치 at Redhat or CentOS
- 다운로드(RPM): http://relax-and-recover.org
```bash
# yum install rear
# rpm -ivh rear-2.6-1.el7.x86_64.rpm					// 상기 다운로드 참고
```
- 설치 시 Dependency가 존재하고 Yum 사용이 불가능할 경우
	1. CD-ROM 또는 ISO를 Repository로 설정	
	```bash
	# mkdir /media/dvd
	# mount -t iso9660 /dev/sr0 /media/dvd				// CD-ROM
	# mount -o loop RHEL-7.0_Server.iso /media/dvd		// ISO
	```
	2. Repository 생성
	```bash
	# vi /etc/yum.repos.d/rhel-dvd.repo
	[rhel-dvd]
	name=Red Hat Enterprise Linux $releasever - $basearch - DVD
	baseurl=file:///media/dvd
	gpgcheck=0
	```
	3. 기존 캐쉬 삭제 및 Repository 정보 갱신
	```bash
	# yum clean all 
	```
	4. Dependency 설치
	```bash
	# yum install attr
	# yum install bc
	# yum install genisoimage
	# yum install syslinux
	```
	5. Umount
	```bash
	# umount /media/dvd
	```


# 백업 설정 및 옵션
```bash
# vi /etc/rear/local.conf
```
- OUTPUT		: 부팅 이미지 생성 옵션(ISO, USB, PXE, ...)
- OUTPUT_URL	: 부팅 이미지 저장 경로(file://, nfs://, sftp://, ...)
- BACKUP		: 백업 데이터 생성 옵셥(NETFS, ISO, ...)
- BACKUP_URL	: 백업 데이터 저장 경로(files://, nfs://, sftp://, iso://, cifs://, usb://, ...)
- 예) [추후] ISO 형태의 부팅 이미지를 NFS의 /rearBoot 폴더에 생성 + NFS의 /rearBackup 폴더에 백업 데이터 생성
	- OUTPUT=ISO
	- OUTPUT_URL=nfs://172.16.0.1/rearBoot
	- BACKUP=NETFS
	- BACKUP_URL=nfs://172.16.0.1/rearBackup
- 예) [실패] ISO 형태의 부팅 이미지를 /rearBoot 폴더에 생성 + /rearBackup 폴더에 백업 데이터 생성
	- OUTPUT=ISO
	- OUTPUT_URL=file:///rearBoot
	- BACKUP=NETFS
	- BACKUP_URL=file:///rearBackup
- 예) [성공] ISO 형태의 부팅 이미지를 /rearBoot 폴더에 생성한 후 생성된 ISO 파일 내에 /fullBackup 폴더를 생성 후 백업 데이터 추가
	- OUTPUT=ISO
	- OUTPUT_URL=file:///rearBoot
	- BACKUP=NETFS
	- BACKUP_URL=iso://fullBackup
- 예) [미정] 기타
	- BACKUP_RUL=sftp://backup:password@192.168.0.0/


# 백업 실행과 로그
```bash
# rear -v mkbackup									// 부팅 이미지 + system backup(Internal 데이터만)를 백업
# rear -v mkbackuponly								// system backup(Internal 데이터만)를 백업
# rear -v mkrescue									// 부팅 이미지만을 백업
# tail -f /var/log/rear/rear-[HostName].log			// Rear 로그에서 확인
# cat /var/log/messages | grep rear 				// System 로그에서 확인
# vi /etc/crontab									// 필요 시 cron에 등록하여 정기적 백업
30 1 * * * root /usr/sbin/rear mkbackup
# vi /etc/cron.d/rear								// 필요 시 cron에 등록하여 정기적 백업
30 1 * * * root /usr/sbin/rear checklayout || /usr/sbin/rear mkbackup
```


# 복원 실행
- 저장된 ISO를 이용하여 부팅(필요 시 프롬프트 상에서 Network을 설정)하고 복원 실행(rear -v recover)
```bash
# rear -v recover
```