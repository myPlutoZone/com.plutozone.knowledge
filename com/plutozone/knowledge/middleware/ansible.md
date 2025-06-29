## TODO
- IP, Domain 변경
- Account Group and UID

## Need hosts(Rocky 9.5)
- master at 192.168.11.10
- server-a at 192.168.11.11
- server-b at 192.168.11.12
- server-c at 192.168.11.13

## Installation at master
```bash
$ sudo dnf install https://dl.fedoraproject.org/pub/epel/epel-release-latest-9.noarch.rpm
$ sudo dnf -y install ansible tar
$ ansible  --version
```

## Inventory and Configure at master
```bash
$ sudo vi /etc/hosts
...
192.168.11.10  master.test.com
192.168.11.11  server-a.test.com
192.168.11.12  server-b.test.com
192.168.11.13  server-c.test.com
$ mkdir plutozone     # Inventory 폴더
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
$ ansible -i inventory --list-hosts web          # Inventory(i) 파일 지정하여 web 그룹의 호스트 확인
$ ansible -i inventory --list-hosts servers      # Inventory(i) 파일 지정하여 servers 그룹의 호스트 확인
$ ansible -i inventory --list-hosts all          # Inventory(i) 파일 지정하여 전체(all) 호스트 확인
$ ansible -i inventory --list-hosts ungrouped    # Inventory(i) 파일 지정하여 미그룹된 호스트 확인
$ touch ansible.cfg
$ vi ansible.cfg                                  # 사용자 정의 Ansible 설정 파일
[defaults]
inventory = ./inventory
remote_user = guru
ask_pass = true

[privilege_escalation]
become = true
become_method = sudo
become_user = root
become_ask_pass = true
$ ansible --list-hosts db                         # db 그룹의 호스트 확인 by ansible.cfg
SSH password:                                     # SSH 암호
BECOME password[defaults to SSH password]:        # sudo 암호
```

## Run Playbook at master
```bash
$ ansible-doc -l                                  # 엔서블 전체 모듈 목록(q: 종료)
$ ansible-doc user                                # 엔서블 user 모듈 설명(q: 종료)
$ vi user.yaml                                    # user라는 Playbook 작성
---
- name: Playbook exUser
  hosts: all
  tasks:
    - name: Create User exUser
      ansible.builtin.user:
        name: pluto
        comment: Member at PlutoZone
        uid: 1212
        group: wheel
$ ansible-playbook -C user.yaml                 # Syntax Check Mode(-C or --syntax-check)
$ ansible-playbook user.yaml                    # Run Playbook
$ ssh guru@server-a.test.com
$ cat /etc/passwd
$ exit
$ ansible-doc dnf                                # 엔서블 모듈 dnf(설치), service(서비스), copy(복사), firewalld(방화벽), file(파일), blockinfile(블럭인파일), lineinfile(라인인파일)에 대한 설명(q: 종료)
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
$ ansible all -m command -a 'poweroff'          # 모든 서버 Power off
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
$ vi commnad.yaml
---
- name: print msg
  hosts: server-a.test.com
  tasks:
    - name: print msg 1
      debug:
        msg:
          hello ansible
          how are
          you
          fine thank

    - name: print msg 2
      debug:
        msg: >
          hello ansible
          how are
          you
          fine thank

    - name: print msg 1
      debug:
        msg: |
          hello ansible
          how are
          you
          fine thank
$ vi file.yaml
---
- name: file module demo
  hosts: db
  tasks:
    - name: create Dir
      file:
        path: /oracle
        state: directory
        mode: '0750'
       
    - name: create empty file to /oracle
      ansible.builtin.file:
        path: /oracle/docs.txt
        owner: guru
        group: wheel
        state: touch
    
    - name: insert multi line
      ansible.builtin.blockinfile:
        path: /oracle/docs.txt
        block: |
          192.168.11.10 master.test.com
          192.168.11.11 server-a.test.com
          192.168.11.12 server-b.test.com

    - name: change 1 line
      ansible.builtin.lineinfile:
        path: /oracle/docs.txt
        regexp: '^192.168.11.10'
        line: 8.8.8.8 www.google.co.kr
$ vi noPass.yaml
---
- name: no password playbook
  hosts: localhost
  tasks:
    - name: ssh key gen
      user:
        name: guru
        generate_ssh_key: yes
        ssh_key_bits: 2048
        ssh_key_file: /home/guru/.ssh/id_rsa

- name: upload public key to node
  hosts: all
  tasks:
    - name: upload public key
      ansible.posix.authorized_key:
        user: guru
        state: present
        key: "{{ lookup('file', '/home/guru/.ssh/id_rsa.pub') }}"

    - name: change /etc/sudoers
      ansible.builtin.lineinfile:
        path: /etc/sudoers
        regexp: '^%wheel'
        line: '%wheel  ALL=(ALL)       NOPASSWD: ALL'
$ ssh guru@server-c.test.com
$ sudo cat /etc/shadow
$ vi ansible.cfg
[defaults]
inventory = ./inventory
remote_user = guru
ask_pass = false
host_key_checking = false

[privilege_escalation]
become = true
become_method = sudo
become_user = root
become_ask_pass = false
$ ansible-playbook user.yaml
```
