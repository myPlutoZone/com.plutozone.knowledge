## Need hosts
- master at 192.168.11.10
- server-a at 192.168.11.11
- server-b at 192.168.11.12
- server-c at 192.168.11.13

## 설치 at master
```bash
$ sudo dnf install https://dl.fedoraproject.org/pub/epel/epel-release-latest-9.noarch.rpm
$ sudo dnf -y install ansible tar
$ ansible  --version
```

## 설정(Inventory, Config and Playbook) at master
```bash
$ sudo vi /etc/hosts
...
192.168.11.10  master.test.com
192.168.11.11  server-a.test.com
192.168.11.12  server-b.test.com
192.168.11.13  server-c.test.com
$ mkdir plutozone     # for Inventory 폴더
$ cd plutozone
$ vi inventory        # Inventory 파일
server-a.test.com

[web]
server-b.test.com

[db]
server-c.test.com

[servers:children]
web
db
$ ansible -i inventory --list-hosts web          # Inventory(i) wlwjd
$ ansible -i inventory --list-hosts servers
$ ansible -i inventory --list-hosts all
$ ansible -i inventory --list-hosts ungrouped
$ touch ansible.cfg
$ vi ansible.cfg                                  # Ansible 설정 파일
[defaults]
inventory = ./inventory
remote_user = guru
ask_pass = true

[privilege_escalation]
become = true
become_method = sudo
become_user = root
become_ask_pass = true
$ ansible --list-hosts db
SSH password:                                     # SSH 접속 암호
BECOME password[defaults to SSH password]:        # sudo 명령 암호
$ ansible-doc -l                                  # 엔서블 모듈 설명(q: 종료)
$ ansible-doc user                                # 엔서블 모듈 user에 대한 설명(q: 종료)
$ vi example.yaml
---
- name: Playbook Example
  hosts: all
  tasks:
    - name: Create User Example
      ansible.builtin.user:
        name: pluto
        comment: Sungwan Myung
        uid: 1212
        group: wheel
$ ansible-playbook -C example.yaml                # Syntax-check Mode(-C or --syntax-check)
$ ansible-playbook example.yaml
$ ssh guru@server-a.test.com
$ cat /etc/passwd
$ exit
$ ansible-doc dnf          # 엔서블 모듈 dnf(설치)에 대한 설명(q: 종료)
$ vi web.yaml
---
- name: Install and Configure web server
  hosts: web
  tasks:
    - name: Install by dnf
      ansible.builtin.dnf:
        name: httpd
        state: present
$ ansible-playbook web.yaml
```
