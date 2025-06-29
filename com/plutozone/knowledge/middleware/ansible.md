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
- name: create user
  hosts: all
  tasks:
    - name: create pluto
      ansible.builtin.user:
        name: pluto
        comment: member at PlutoZone
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
        web_restart
    
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
    - name: web_restart
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

## 변수
- 문자열이 먼저 시작할 경우 {{ 변수명 }}
- [권장] 변수명이 먼저 시작할 경우 "{{ 변수명 }}"
```bash
$ vi web.yaml
---
- name: install and configure web server
  hosts: web
  vars_files:
    - vars/package.yaml
#  vars:
#    - package_web: httpd
  tasks:
    - name: install by dnf
      ansible.builtin.dnf:
        name: "{{ package_web }}"
        state: present

    - name: Copy file with owner and permissions
      ansible.builtin.copy:
        src: ./index.html
        dest: "{{package_web_index}}"
      notify:
        - web_restart

    - name: permit traffic in default zone for https service
      ansible.posix.firewalld:
        service: http
        permanent: true
        state: enabled
        immediate: true
 
  handlers:
    - name: web_restart
      ansible.builtin.service:
        name: "{{ package_web }}"
        state: restarted
$ vi vars/package.yaml
package_web: nginx
package_web_index: /usr/share/nginx/html/index.html
```

# include(동적-실행 결과을 적용) vs. import(정적-실제 코드를 적용)
```bash
$ vi task_vars.yaml
- name: set name
  set_fact:
    myname: pluto

- name: get name
  debug:
    var: myname
$ vi include.yaml
---
- name: include vs. import
  hosts: localhost
  tasks:
    - name: 1st task
      debug:
        msg: this is 1st task

    - name: show me value
      include_tasks: task_vars.yaml
      # import_tasks: task_vars.yaml
      when: myname is not defined
    
    - name: 3st task
      debug:
        msg: i am 3 task
$ ansible-playbook --list-tasks include.yaml
```

## Role 작성
```bahs
$ ansible-galaxy --help
$ ansible-galaxy role --help
$ ansible-galaxy role list           # 기본 또는 저장된 롤
$ sudo dnf -y install tree
$ ansible-galaxy role init myRole
$ tree
$ vi myRole/tasks/main.yaml
---
# tasks file for myRole
- name: install by dnf
  ansible.builtin.dnf:
    name: "{{ package_web }}"
    state: present

- name: Copy file with owner and permissions
  ansible.builtin.copy:
    src: "{{web_index}}"
    dest: "{{package_web_path_index}}"
  notify:
    - web_restart

- name: permit traffic in default zone for https service
  ansible.posix.firewalld:
    service: http
    permanent: true
    state: enabled
    immediate: true
$ vi myRole/handers/main.yaml
---
# handlers file for myRole
- name: web_restart
  ansible.builtin.service:
    name: "{{ package_web }}"
    state: restarted
$ vi myRole/vars/main.yaml
---
# vars file for myRole
package_web: nginx
web_index: ../files/index.html
package_web_path_index: /usr/share/nginx/html/index.html
$ vi myRole/files/index.html
<html>
    <body bgcolor="red">
        Hello Ansible
    </body>
</html>
```

## Role 사용
- Playbook의 tasks에서 섹션 사용
```bash
$ vi role1.yaml
---
- name: role demo 1
  hosts: server-a.test.com
  tasks:
    - name: dummy msg 1
      debug:
        msg: "Let's start role"
   
    - name: import myRole
      import_role:
        name: myRole
```

- Playbook의 roles 섹션에서 사용
```bash
$ vi role2.yaml
---
- name: role demo 2
  hosts: server-b.test.com
  roles:
    - myRole
  tasks:
    - name: dummy msg 1
      debug:
        msg: "end role"
```

## Role Download
```bash
$ sudo rpm -qa | grep roles
$ sudo dnf search roles
$ sudo dnf -y install rhel-system-roles
$ ansible-galaxy role list
$ tree /usr/share/ansible/roles
$ tree /usr/share/ansible/roles/rhel-system-roles.timesync
$ less /usr/share/ansible/roles/rhel-system-roles.timesync/README.md
# http://time.ewha.or.kr/domestic.html
# ntp.ewha.or.kr / time.bora.net
$ vi role3.yaml
---
- name: time sync
  hosts: all
  vars:
    timesync_ntp_servers:
      - hostname: ntp.ewha.or.kr
        iburst: true
      - hostname: time.bora.net
        iburst: true
  roles:
    - rhel-system-roles.timesync
$ ssh guru@server-c.test.com
$ sudo chronyc sources
$ exit
# https://galaxy.ansible.com/
$ ansible-galaxy role search --help
$ ansible-galaxy role search nfs --platforms EL
$ ansible-galaxy role info asg1612.ansible-role-nfs
$ ansible-galaxy role install --help
$ ansible-galaxy role install asg1612.ansible-role-nfs -p roles    # roles 폴더에 다운로드
$ ansible-galaxy role list -p roles
```

## Collection(*.py + *.yaml)
```bash
$ ansible-galaxy collection --help
$ ansible-galaxy collection list
# download https://galaxy.ansible.com/api/v3/plugin/ansible/content/published/collections/artifacts/community-docker-4.6.1.tar.gz
$ ansible-galaxy collection install ./community-docker-4.6.1.tar.gz -p collections
$ ls -al
$ tree collections
$ ansible-galaxy collection list | grep docker
```

## Ansible AWX by K3S at Ubuntu
```bash
$ sudo ufw status                              # Ubuntu FireWall
$ curl -sfL https://get.k3s.io | sh -          # Install K3S
$ k3s --version
$ ls -l /etc/rancher/k3s/k3s.yaml
$ sudo chmod 644 /etc/rancher/k3s/k3s.yaml
$ ls -l /etc/rancher/k3s/k3s.yaml
$ source < (kubectl completion bash)            # 임시 자동 완성
$ vi ~/.bashrc                                  # 영구 자동 완성
...
source < (kubectl completion bash)
$ sudo systemctl enable k3s
# https://github.com/ansible/awx
# https://github.com/ansible/awx-operator
# https://ansible.readthedocs.io/projects/awx-operator/en/latest/
# https://ansible.readthedocs.io/projects/awx-operator/en/latest/installation/basic-install.html
$ sudo apt -y install tar git make
$ sudo git clone https://github.com/ansible/awx-operator.git
$ cd awx-operator
$ git config --global --add safe.directory /home/guru/awx-operator
$ git tag
$ sudo git checkout tags/2.19.1
$ sudo make deploy
$ sudo kubectl get ns
$ sudo kubectl get pod
$ sudo kubectl get pod -n awx
$ sudo kubectl config set-context --current --namespace=awx       # Default Name Space(ns) 변경
$ sudo vi awx-demo.yml                                            # :set nu
---
apiVersion: awx.ansible.com/v1beta1
kind: AWX
metadata:
  name: awx-demo
spec:
  service_type: nodeport
  projects_persistence: true
  projects_storage_size: 20Gi
  projects_storage_access_mode: ReadWriteOnce
  web_resource_requirements:
    requests:
      cpu: 500m
      memory: 1Gi
    limits:
      cpu: 1000m
      memory: 2Gi
$ sudo vi kustomization.yaml
apiVersion: kustomize.config.k8s.io/v1beta1
kind: Kustomization
resources:
  # Find the latest tag here: https://github.com/ansible/awx-operator/releases
  - github.com/ansible/awx-operator/config/default?ref=2.19.1
  - awx-demo.yml

# Set the image tags to match the git version from above
images:
  - name: quay.io/ansible/awx-operator
    newTag: 2.19.1

# Specify a custom namespace in which to install AWX
namespace: awx
$ kubectl apply -k .
$ kubectl get pod
$ kubectl get svc
$ kubectl get pv
$ kubectl get secret awx-demo-admin-password -o jsonpath="{.data.password}" | base64 --decode ; echo
# http://localhost:PORT/        # admin / hOgO0ySBXpp5jUGogPLKxsbIUZAsHffO
$ git config --global user.name "Sungwan Myung"
$ git config --global user.email "plutomsw@gmail.com"
$ mkdir myLast                  # This is Git Repository
$ pwd
$ git init
$ vi ansible.cfg
[defaults]
inventory = ./inventory
remote_user = guru
ask_pass = true
host_key_checking = false

[privilege_escalation]
become = true
become_method = sudo
become_user = root
become_ask_pass = true
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
# upload at GitHub
# make Tocken at GitHub
# 리소스의 인증 정보(GitHub, Linux), 프로젝트, 인벤토리, 템플릿 생성 후 템플릿 시작 at AWX
```
