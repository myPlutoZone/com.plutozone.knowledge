# TODO for com.plutozone.shell(crontab + curl + ...) + com.plutozone.monitor(PHP + MySQL + Email)
- Send CPU, Memory, Disk at com.plutozone.monitor
	- jwt.php
- monitorService.sh
- Service Mode: local | development | production
- Message Type: email | sms
- Message Send: yes | no


# Installation and Configuration
- msmtp + Gmail for Monitor
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