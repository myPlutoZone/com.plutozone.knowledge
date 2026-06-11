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


# TODO
- `com` vs. `resource` vs. `src + WebContent` at com.plutozone.knowledge
- Send CPU, Memory, Disk from monitorSystem.sh to com.plutozone.monitor `by API Document`
- monitorService.sh
- Service Mode: local | development | production
- Message Type: email | sms
- Message Send: yes | no


# Overview
- ...


# History
- 2026-05-14 [CREATE] Initial Release


# Installation and Configuration
- msmtp + Gmail
```bash
$ sudo yum install -y msmtp		# for Redhat(Rocky, Amazon Linux) and If necessary, Use $ sudo yum install -y epel-release
$ sudo apt install -y msmtp		# for Ubuntu
$ nano ~/.msmtprc
...
$ chmod 600 ~/.msmtprc
$ nano monitorSystem.sh
...
$ chmod +x monitorSystem.sh
$ touch monitorSystem.log
$ ~/monitorSystem.sh
$ crontab -e
# */10 * * * * /home/USER/monitorSystem.sh
# 59 12 * * * /home/USER/monitorSystem.sh at GitLab
$ nano monitorService.sh
...
```


# Reference
- com.plutozone.shell(Command + Msmtp + Gmail + Crontab + Curl) for monitorSystem.sh or monitorService.sh
- com.plutozone.monitor(PHP + MySQL + Email or SMS)