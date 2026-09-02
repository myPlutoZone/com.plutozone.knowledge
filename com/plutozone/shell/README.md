# com.plutozone.shell

> YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS
DOCUMENT IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF
PLUTOZONE.COM.
PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS DOCUMENT.
COPYRIGHT © 2026 PLUTOZONE.COM ALL RIGHTS RESERVED
***
> 하기 문서에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며 plutozone.com이 명시적으
로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
plutozone.com의 지적재산권 침해에 해당된다.
***
> Copyright © 2026 plutozone.com All Rights Reserved

## 1. TODO

- `Send CPU, Memory, Disk from monitorSystem.sh to com.plutozone.monitor`
- monitorService.sh + monitorSecurity.sh + monitorInspection.sh
- Service Mode: local | development | production
- Messaging Type: email | sms

## 2. Overview

- ...

## 3. Requirement

### 3-1. Service

- 개발 및 운영 리소스(물적, 인적 등)가 없어도 시스템과 서비스를 효율적으로 관리할 수 있다.
- 모든 것이 아닌 꼭 필요한 리소스(CPU, Memory, Disk, Access Traffic 등)만을 모니터링할 수 있다.
- 모니터링 이후에 이상징후와 장애 탐지, 원인 분석 및 장애 대응을 자동화할 수 있다.
- 장애 또는 현황을 실시간으로 확인할 수 있다.
- com.plutozone.shell에서는 Linux만 지원하며 com.plutozone.agent는 여러 OS를 지원한다.

### 3-2. System

- com.plutozone.shell(Command + Msmtp + Gmail + Crontab + Curl) for monitorSystem.sh or monitorService.sh or ...
- com.plutozone.monitor(PHP + MySQL + Email or SMS)

## 4. Installation and Configuration

- msmtp + Gmail

```bash
$ sudo yum install -y msmtp # for Redhat(Rocky, Amazon Linux) and If necessary, Use $ sudo yum install -y epel-release
$ sudo apt install -y msmtp # for Ubuntu
$ nano ~/.msmtprc
$ chmod 600 ~/.msmtprc
$ nano monitor.sh
$ chmod +x monitor.sh
$ ~/monitor.sh
$ crontab -e
*/10 * * * * /home/USER/monitor.sh system
*/20 * * * * /home/USER/monitor.sh service
...
```

- for Monitor(yum or apt)

```bash
$ sudo yum install sysstat # for mpstat
...
```

## 5. History

- 2026-05-14 [CREATE] Initial Release
