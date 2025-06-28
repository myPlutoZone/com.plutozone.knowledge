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

## 설정(Inventory and Configure) at master
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
$ ansible -i inventory --list-hosts web          # Inventory(i) 파일 지정
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
$ ansible --list-hosts db                         # 설정된 Inventory at ansible.cfg
SSH password:                                     # SSH 암호
BECOME password[defaults to SSH password]:        # sudo 암호
```

## Run Playbook at master
```bash
$ ansible-doc -l                                  # 엔서블 모듈 목록(q: 종료)
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
$ ansible-doc dnf                                # 엔서블 모듈 dnf(설치), service(서비스), copy(복사), firewalld(방화벽), blockinfile(파일)에 대한 설명(q: 종료)
$ vi index.html
<html>
    <body bgcolor="blue">
        Hello Ansible
    </body>
</html>
$ vi web.yaml
---
- name: install and configure web server
  hosts: web
  tasks:
    - name: install by dnf
      ansible.builtin.dnf:
        name: httpd
        state: present

    - name: copy file with owner and permissions
      ansible.builtin.copy:
        src: ./index.html
        dest: /var/www/html/index.html
      notify:
        restart_web
    
#    - name: Start service httpd, if not started
#      ansible.builtin.service:
#        name: httpd
#        state: started
    
    - name: permit traffic in default zone for http service
      ansible.posix.firewalld:
        service: http
        permanent: true
        state: enabled
        immediate: true
  
  handlers:
    - name: restart_web
      ansible.builtin.service:
        name: httpd
        state: restarted
$ ansible all -m command -a 'poweroff'
$ vi block.yaml
---
- name: block and rescue
  hosts: web
  tasks:
    - name: install APM
      block:
        - name: install web pkg
          dnf:
            name: nginx
            state: present

      rescue:
        - name: install ftp pkg  
          dnf:
            name: vsftpd
            state: latest

      always:
        - name: install db pkg  
          dnf:
            name: mariadb-server
            state: latest
# Restore web
$ vi block2nd.yaml
---
- name: block and rescue
  hosts: web
  tasks:
    - name: install APM
      block:
        - name: install web pkg
          dnf:
            name: X-nginx
            state: present

      rescue:
        - name: install ftp pkg  
          dnf:
            name: vsftpd
            state: latest

      always:
        - name: install db pkg  
          dnf:
            name: mariadb-server
            state: latest

```
