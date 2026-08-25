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

- `Send CPU, Memory, Disk from monitorSystem.sh to com.plutozone.monitor by API Document`
- monitor.service.sh
- monitor.security.sh
- monitor.inspection.sh
- Service Mode: local | development | production
- Message Type: email | sms

## 2. Overview

- ...

## 3. Requirement

### 3-1. Service

- 개발과 운영이 없이 시스템과 서비스를 모니터링할 수 있다.
- 모든 것이 아닌 원하는 리소스(CPU, Memory, Disk, Access Traffic 등)만을 모니터링할 수 있다.
- 장애 또는 현황을 실시간으로 확인할 수 있다.
- com.plutozone.shell에서는 Linux만 지원한다.

### 3-2. System

- com.plutozone.shell(Command + Msmtp + Gmail + Crontab + Curl) for monitorSystem.sh or monitorService.sh or ...
- com.plutozone.monitor(PHP + MySQL + Email or SMS)

## 4. Installation and Configuration

- msmtp + Gmail

```bash
$ sudo yum install -y msmtp # for Redhat(Rocky, Amazon Linux) and If necessary, Use $ sudo yum install -y epel-release
$ sudo apt install -y msmtp # for Ubuntu
$ nano ~/.msmtprc
...
$ chmod 600 ~/.msmtprc
$ nano monitor.sh
...
$ chmod +x monitor.sh
$ touch monitorSystem.log
# $ touch monitorService.log
# $ touch monitorSecurity.log
# $ touch monitorInspection.log
$ ~/monitor.sh
$ crontab -e
*/10 * * * * /home/USER/monitor.sh system
*/20 * * * * /home/USER/monitor.sh service
...
```

## 5. History

- 2026-05-14 [CREATE] Initial Release
