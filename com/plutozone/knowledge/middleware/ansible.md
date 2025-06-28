## Hosts
- master
- server-a
- server-b
- server-c

## 설치
```bash
$ sudo dnf install https://dl.fedoraproject.org/pub/epel/epel-release-latest-9.noarch.rpm
$ sudo dnf -y install ansible tar
$ ansible  --version
```

## 설정
```bash
$ mkdir plutozone    # for Inventory 폴더
$ cd plutozone
$ vi inventory
server-a.test.com

[web]
server-b.test.com

[db]
server-c.test.com

[servers:children]
web
db
$ ansible -i inventory --list-hosts web          # Inventory(i)
$ ansible -i inventory --list-hosts servers
$ ansible -i inventory --list-hosts all
$ ansible -i inventory --list-hosts ungrouped
$ touch ansible.cfg
$ vi ansible.cfg
[defaults]
inventory = ./inventory
remote_user = guru
ask_pass = true

[privilege_escalation]
become = true
become_method = sudo
become_user = root
become_ask_pass = true
```
