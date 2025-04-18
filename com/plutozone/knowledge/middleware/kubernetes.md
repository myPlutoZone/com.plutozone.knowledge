# com.plutozone.knowledge.middleware.Kubernetes


## Enviroments
- Virtual Box and 4VM(3 for Kubernetes Cluster + 1 for Docker)
- Rocky(rockylinux.org vs. mirror.navercorp.com)
- MobaXterm
- Visual Studio Code for Yaml and Extentions(Kubernetes + Kubernetes Support + Remote SSH)


## Overview(https://kubernetes.io/ko/docs/concepts/overview/)
- Container Cluster = Container Orchestrator = Kubernetes(k8s)
- Control-Plane(API + Scheduler + Etcd + Controller Manager + Cloud Conroller Manager) + Node(Kubelet + Kube-Proxy + Container Runtime) = Master + Worker Node
- CNCF(https://landscape.cncf.io/)


## Installation
### master@192.168.56.10 + node1@192.168.56.11 + node2@192.168.56.12 by Kubeadm(vs. Local vs. Cloud)
#### config and install Containerd(vs. Docker) at master, node1, node2
```bash
# config enviroment
$ yum -y update
$ timedatectl set-timezone Asia/Seoul
$ cat << EOF >> /etc/hosts
192.168.56.10 master
192.168.56.11 node1
192.168.56.12 node2
EOF
$ systemctl stop firewalld && systemctl disable firewalld            # disable Firewall
$ swapoff -a && sed -i '/ swap / s/^/#/' /etc/fstab                  # disable Swap
$ cat <<EOF |tee /etc/modules-load.d/k8s.conf                        # run automatically Kernal Modules(overlay와 br_netfilter) for Kubernetes
overlay
br_netfilter
EOF
$ modprobe overlay
$ modprobe br_netfilte
$ cat <<EOF |tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-iptables  = 1            # config bridge iptable
net.bridge.bridge-nf-call-ip6tables = 1            # support IPv6
net.ipv4.ip_forward                 = 1            # enable IP forware
EOF
$ sysctl --system

# install Containerd(vs. Docker)
$ yum install -y yum-utils
$ yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
$ yum install -y containerd.io
$ systemctl daemon-reload
$ systemctl enable --now containerd

# config Containerd for Kubernetes
$ containerd config default > /etc/containerd/config.toml
$ sed -i 's/ SystemdCgroup = false/ SystemdCgroup = true/' /etc/containerd/config.toml
$ systemctl restart containerd
```

#### install Kubernetes at master, node1, node2
```bash
$ cat <<EOF | sudo tee /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://pkgs.k8s.io/core:/stable:/v1.29/rpm/
enabled=1
gpgcheck=1
gpgkey=https://pkgs.k8s.io/core:/stable:/v1.29/rpm/repodata/repomd.xml.key
exclude=kubelet kubeadm kubectl cri-tools kubernetes-cni
EOF
$ setenforce 0
$ sed -i 's/^SELINUX=enforcing$/SELINUX=permissive/' /etc/selinux/config
$ sudo yum install -y kubelet kubeadm kubectl --disableexcludes=kubernetes
$ systemctl restart kubelet   # restart
$ systemctl enable kubelet    # autostart
```

#### config Kubernetes Cluster `at only Master`
```bash
# Node Join Command(include Token) for nodes
$ kubeadm init --pod-network-cidr=192.168.0.0/16 --apiserver-advertise-address 192.168.56.10

$ mkdir -p $HOME/.kube
$ cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
$ chown $(id -u):$(id -g) $HOME/.kube/config

# install CNI(Container Network Interface)
kubectl create -f https://raw.githubusercontent.com/projectcalico/calico/v3.28.1/manifests/tigera-operator.yaml
kubectl create -f https://raw.githubusercontent.com/projectcalico/calico/v3.28.1/manifests/custom-resources.yaml

# [OPTION] recreate Token for nodes
$ kubeadm token create --print-join-command
```

#### join node at only node1, node2
```bash
# EX) kubeadm join <control-plane-host>:<port> --token <token> --discovery-token-ca-cert-hash sha256:<hash>
```

------------------------------------------------------------------------------------------------------------------------------------------
#### [OPTION] install Tools for Kubernetes `at only Master`
```bash
$ yum -y install bash-completion
$ cd ~
$ echo "source <(kubectl completion bash)" >> ~/.bashrc
$ echo 'alias k=kubectl' >>~/.bashrc
$ echo 'complete -o default -F __start_kubectl k' >>~/.bashrc
$ source ~/.bashrc
```

#### Confirm at Master
```bash
$ kubectl get node
```

### docker@192.168.56.100
- install Docker at Rocky or Ubuntu
- create REPOSITORY(EX: app) at hub.docker.com
- build image and push
```bash
# build image
$ cd ~
$ mkdir build
$ cd build
$ vi app.py
from flask import Flask, request
import socket

app = Flask(__name__)

@app.route('/')
def index():
    # 클라이언트 IP 주소 추출
    client_ip = request.remote_addr
    xff_header = request.headers.get('X-Forwarded-For', None)
    if xff_header:
        # X-Forwarded-For 헤더에서 첫 번째 IP 주소 추출
        client_ip = xff_header.split(',')[0].strip()

    client_user_agent = request.headers.get('User-Agent')

    # 서버 정보
    server_ip = socket.gethostbyname(socket.gethostname())
    server_name = socket.gethostname()

    return f"""
    <html>
    <head>
        <style>
            .client-info {{
                color: blue;
            }}
            .server-info {{
                color: green;
            }}
        </style>
    </head>
    <body>
        <h1> - 접속 정보 Version 1 - </h1>

        <h2 class="client-info">1. 클라이언트 정보</h2>
        <p class="client-info">IP 주소: {client_ip}</p>
        <p class="client-info">X-Forwarded-For: {xff_header if xff_header else '없음'}</p>
        <p class="client-info">User-Agent: {client_user_agent}</p>

        <h2 class="server-info">2. 서버 정보</h2>
        <p class="server-info">IP 주소: {server_ip}</p>
        <p class="server-info">이름: {server_name}</p>
    </body>
    </html>
    """

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
$ vi Dockerfile
# 베이스 이미지
FROM python:3.8

# 작업 디렉토리 설정
WORKDIR /app

# 종속성 파일 복사 및 설치
RUN pip install flask
# 애플리케이션 코드 복사
COPY app.py .

# 컨테이너 시작 시 실행할 명령어
CMD ["python", "app.py"]
$ docker build -t [ACCOUNT]/[REPOSITORY]:[TAG] .                         # EX) docker build -t ID/app:v1 .
$ docker images

# push image
$ docker login -u ID
$ docker push [ACCOUNT]/[REPOSITORY]:[TAG]                               # EX) docker push ID/app:v1
$ docker run -d -p 80:5000 --name my-app [ACCOUNT]/[REPOSITORY]:[TAG]    # EX) docker run -d -p 80:5000 --name my-app ID/app:v1
$ docker ps -a
- http://192.168.56.100
```


## Client at Windows
### install Kube Command(kubectl)
- https://kubernetes.io/ko/docs/tasks/tools/install-kubectl-windows/#install-nonstandard-package-tools
- copy to %USER%.kube\config from ~/.kube/config
- C:\kubectl get node

### Create POD by Command(kubectl)
```cmd
C:\k8s\pods> kubectl run nginx --image=nginx
C:\k8s\pods> kubectl get pod
C:\k8s\pods> kubectl get pod -o wide
C:\k8s\pods> kubectl expose pod nginx --name nginx-svc1st --port 80
C:\k8s\pods> kubectl get svc                                                           # ClusterIP vs. NodePort vs. LoadBalancer
C:\k8s\pods> kubectl expose pod nginx --name nginx-svc2nd --port 80 --type NodePort
C:\k8s\pods> kubectl get svc
C:\k8s\pods> kubectl expose pod nginx --name nginx-svc3rd --port 80 --type LoadBalancer
C:\k8s\pods> kubectl get svc
...
... PORT(S) ...
... 80:31843/TCP ...
...
```
- http://master or node1 or node2:31843/

### create YAML
```cmd
C:\k8s\pods> type pod.yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-app
  labels:
    targetApp: my-app
spec:
  containers:
  - name: my-app
    image: [ACCOUNT]/[REPOSITORY]:[TAG]
    ports:
      - containerPort: 5000

# Create POD by File(YAML or JSON)
C:\k8s\pods> kubectl apply -f .\pod.yaml
C:\k8s\pods> kubectl get pod

# Expose POD by File(YAML or JSON)
C:\k8s\pods> type srv.yaml
apiVersion: v1
kind: Service
metadata:
  name: my-app-svc
spec:
  selector:
    targetApp: my-app                                # Name of labels at my-app and my-app2nd
  ports:
  - port: 80
    targetPort: 5000
  type: LoadBalancer
C:\k8s\pods> kubectl apply -f .\srv.yaml
C:\k8s\pods> kubectl get svc
C:\k8s\pods> kubectl get ep
C:\k8s\pods> type pod2nd.yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-app2nd
  labels:
    targetApp: my-app
spec:
  containers:
  - name: my-app2nd
    image: [ACCOUNT]/[REPOSITORY]:[TAG]
    ports:
      - containerPort: 5000

# Create POD by File(YAML or JSON)
C:\k8s\pods> kubectl apply -f .\pod.yaml
C:\k8s\pods> kubectl get pod
C:\k8s\pods> kubectl get svc                                                # LoadBalancer for Pods(my-app and my-app2nd)
C:\k8s\pods> kubectl get ep

# Delete POD
C:\k8s\pods> kubectl delete pod [NAME]                                      # kubectl delete pod nginx
C:\k8s\pods> kubectl delete pod --all

# Expose POD
C:\k8s\pods> type pod.yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-app3rd
  labels:
    name: my-app3rd
spec:
  containers:
  - name: my-app3rd
    image: [ACCOUNT]/[REPOSITORY]:[TAG]
    ports:
      - containerPort: 5000
C:\k8s\pods> kubectl apply -f .\pod.yaml                                                        # EX) name=my-app3rd
C:\k8s\pods> kubectl expose pod [NAME_POD] --name [NAME_SERVICE] --port 80                      # EX) kubectl expose pod my-app3rd --name my-app3rd-srv1st --port 80
C:\k8s\pods> kubectl get svc
C:\k8s\pods> kubectl expose pod [NAME_POD] --name [NAME_SERVICE] --port 5000 --type NodePort    # EX) kubectl expose pod my-app3rd --name my-app3rd-srv2nd --port 5000 --type NodePort
C:\k8s\pods> kubectl get svc
C:\k8s\pods> kubectl delete svc [NAME_SERVICE]
C:\k8s\pods> kubectl expose pod [NAME_POD] --name [NAME_SERVICE] --port 80 --type LoadBalancer  # EX) kubectl expose pod my-app3rd --name my-app3rd-srv3rd --port 80 --type LoadBalancer
C:\k8s\pods> kubectl get svc
C:\k8s\pods> kubectl delete all --all                                                            # Delete All
C:\k8s\pods> kubectl api-resources

C:\k8s\rs> type rs.yaml
apiVersion: apps/v1
kind: ReplicaSet
metadata:
  name: app-rs
  labels:
    app: myApp
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myApp
  template:
    metadata:
      labels:
        app: myApp
    spec:
      containers:
        - name: myapp
          image: ID/REPOSITORY:TAG
C:\k8s\rs> kubectl -f .\rs.yaml
C:\k8s\rs> kubectl get pod
C:\k8s\rs> type svc.yaml
apiVersion: v1
kind: Service
metadata:
  name: app-svc
spec:
  selector:
    app: myApp
  ports:
  - port: 80
    targetPort: 5000
  type: LoadBalancer
C:\k8s\rs> kubectl apply -f .\svc.yaml
C:\k8s\rs> kubectl get svc
C:\k8s\rs> kubectl get rs
C:\k8s\rs> kubectl delete pod app-rs-*        # delete a pod but restart a new pod
C:\k8s\rs> kubectl get rs
C:\k8s\rs> kubectl get ns
C:\k8s\rs> kubectl get pod -n kube-system
C:\k8s\rs> kubectl get pod --show-labels
C:\k8s\rs> kubectl scale rs app-rs --replicas 4

# upgrade or downgrade application by RS vs. Deploy for NON-Downtime
C:\k8s\deploy> type deploy.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp
  labels:
    app: myApp
spec:
  replicas: 5
  selector:
    matchLabels:
      app: myApp
  template:
    metadata:
      labels:
        app: myApp
    spec:
      containers:
      - name: myapp
        image: plutomsw/app:v1
        ports:
        - containerPort: 5000
C:\k8s\deploy> kubectl apply -f .\deploy.yaml
C:\k8s\deploy> type srv.yaml
apiVersion: v1
kind: Service
metadata:
  name: my-app-svc
spec:
  selector:
    app: myApp
  ports:
  - port: 80
    targetPort: 5000
  type: LoadBalancer
C:\k8s\deploy> kubectl apply -f .\svc.yaml
C:\k8s\deploy> type deploy.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp
  labels:
    app: myApp
spec:
  replicas: 5
  selector:
    matchLabels:
      app: myApp
  template:
    metadata:
      labels:
        app: myApp
    spec:
      containers:
      - name: myapp
        image: plutomsw/app:v2
        ports:
        - containerPort: 5000
C:\k8s\deploy> kubectl apply -f .\deploy.yaml
C:\k8s\deploy> kubectl get deploy
C:\k8s\deploy> kubectl get deploy -o yaml
C:\k8s\deploy> kubectl edit deploy myapp
C:\k8s\deploy> kubectl describe deploy myapp
C:\k8s\deploy> kubectl rollout history deploy myapp
C:\k8s\deploy> kubectl rollout undo deploy myapp
# mkdir /log/nginx at node1 and node2
C:\k8s\storage> storage.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp
  labels:
    app: myApp
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myApp
  template:
    metadata:
      labels:
        app: myApp
    spec:
      containers:
      - name: myapp
        image: nginx
        volumeMounts:
          - name: log
            mountPath: /var/log/nginx
      volumes:
        - name: log
          hostPath:
            path: /log/nginx
            type: Directory
```

### config for Terminal(Docker) at Windows
```bash
# create Key Pair(RSA)
$ yum install -y tar
$ ssh-keygen -t rsa                    # generate private and public key
$ cp id_rsa.pub authorized_keys        # copy for sshd_config at /etc/ssh/sshd_config

# install Kubernetes Control
$ cat <<EOF | sudo tee /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://pkgs.k8s.io/core:/stable:/v1.29/rpm/
enabled=1
gpgcheck=1
gpgkey=https://pkgs.k8s.io/core:/stable:/v1.29/rpm/repodata/repomd.xml.key
exclude=kubelet kubeadm kubectl cri-tools kubernetes-cni
EOF
$ setenforce 0
$ sed -i 's/^SELINUX=enforcing$/SELINUX=permissive/' /etc/selinux/config
$ sudo yum install -y kubectl --disableexcludes=kubernetes

# Copy Certification
$ mkdir .kube
$ scp root@192.168.56.10:/root/.kube/config .
```

### config for Windows
- copy to %USER%.ssh\ from ~/.ssh/id_rsa    # copy private key
```cmd
C:\ssh ID@192.168.56.100
```
- config SSH at Visual Studio Code
- install Extenstion(Kubenetes, Kubenetes Support) for Remote SSH


## Metal LB설치
```bash
# kube-proxy configmap 수정
$ kubectl get configmap kube-proxy -n kube-system -o yaml | \
sed -e "s/strictARP: false/strictARP: true/" | \
kubectl apply -f - -n kube-system

# Installation By Manifest
$ kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/v0.14.4/config/manifests/metallb-native.yaml

# config yaml  생성
cat <<EOF | sudo tee /root/my-config.yaml
apiVersion: metallb.io/v1beta1
kind: IPAddressPool
metadata:
  name: ip-pool
  namespace: metallb-system
spec:
  addresses:
  - 192.168.56.200-192.168.56.250
  autoAssign: true

---

apiVersion: metallb.io/v1beta1
kind: L2Advertisement
metadata:
  name: l2-network
  namespace: metallb-system
spec:
  ipAddressPools:
    - ip-pool
EOF

# 재생성
# 설정 확인
$ kubectl get validatingwebhookconfigurations
# 지우기
$ kubectl delete validatingwebhookconfigurations  metallb-webhook-configuration
# 설정 적용
$ kubectl apply -f my-config.yaml
# 최종 확인
$ kubectl describe ipaddresspool.metallb.io --namespace metallb-system
# 
```


## Reference
- 내도메인 at 구글 for 무료 도메인
- GKE(Google Kubernetes Engine)
```cmd
C:\> kubectl config get-contexts
```

- NONE Ready(reset, rm and reconfig) at Node
```bash
$ kubeadm reset		# at master/node1/node2
$ rm -Ff .kube		# at master
```

- Hostname and Network
```bash
$ vi /etc/hostname                                     # update Hostname
$ cd /etc/NetworkManager/system-connections            # select NIC and update IP at Rocky(Redhat)
```
