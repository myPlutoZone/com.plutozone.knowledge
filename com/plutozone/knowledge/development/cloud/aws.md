# com.plutozone.knowledge.cloud.AWS(Amazon Web Service)


## 1. Fundamental
- On-Premise vs. Cloud
- ISP(Internet Service Provider) vs. CSP(Cloud Service Provider) vs. MSP(Managed Service Provider)
- OpenStack for IaaS vs.OpenShift for PaaS by Red Hat
- Inside(=Private) Zone for WAS + Outside(=Public) Zone for WS vs. All Inside(=Private)
- Private IP Band
	- 10.0.0.0 ~ 10.255.255.255(10.0.0.0/8)
	- 172.16.0.0 ~ 172.31.255.255(172.16.0.0/12)
	- 192.168.0.0 ~ 192.168.255.255(192.168.0.0/16)

## 2. Term, Login and Service
### 2-1. Term
- CIDR(Classless Inter-Domain Routing)
- EIP(Elastic IP=Public and Static IP, 무료는 최대 5개까지) vs. PIP(Public IP=Public and Dynamic IP)
- SLA(Service Level Agreement, 서비스 수준 계약)

### 2-2. Login
- Sign in as the root user
- Sign in as an IAM user(비용 설정 및 사용량 확인 불가 등)

### 2-3. Service
- IAM(Identity and Access Management=Account)
- VPC(Virtual Private Cloud=Network, 최대 5개)
	- `The generated VPC(=Default VPC) information is not modified at AWS.`
	- `2a(롯?정보통신/현?정보기술 at 용인 마북리)/2c(L? U+ at 평촌) AZ(Availability Zone) is difference from 2B(K? at 목동)/2D(SK? at 일산) at ap-northeast-2(아시아 태평양-서울)`
- EC2(Elastic Computing Cloud=Host)
	- Type: Micro(M), Free Tier(T), ...
	- AMI(Amazon Machine Image): Amazon Linux, Ubuntu, Windows, ...
	- ELB(Elastic Load Balancer, Support L4 and L7) vs. Auto Scaling by EC2(Amazon EC2 Auto Scaling, Not Support LB and HC)
		- Load Balancer(LB)
		- Health Check(HC)
		- Auto Scaling
	- Storage at EC2
		- Instance Storage(Default Disk로 예를 들어 C:\)
		- EBS(Elastic Block Storage, Additional Disk로 예를 들어 D:\)
	- Create EC2 Instances
		- Make PEM for ec2-user at Amazon Linux 2
		- Install Nginx or Tomcat
	- `Scale up and down` for a Instance vs. `Scale out and in` by Instance
	- EC2 vs. AWS Managed Service
- RDS(MySQL, PostgreSQL, MariaDB, ..) and Aurora, DynamoDB(=No SQL)
- Route53(=DNS, 자체 또는 외부 DNS 관리)
- 버킷(=폴더) + 객체(=파일) at S3(Simple Storage Service=NAS or External Storage by HTTPS for File Upload/Download)
	- 객체 업로드 시 폴더를 추가하면 해당 폴더에 객체가 저장
- SNS(Simple Notification Service)
- SES(Simple Email Service)
- ELB(Elastic Load Balancing=L7/4) Options
	- Network Load Balancer(L4, ...)
	- Classic Load Balancer(L7/4 + EC2 Instances, ...)
	- Application Load Balancer(L7 + Containers, ...)
- ACM(Amazon Certificate Manager=HTTPS/SSL 인증서 관리)
	- 상용(com, co.kr 등)이 아닌 개인(store, shop 등) 도메인에 대한 SSL 인증서 발급 무료 지원
- ECS(Elastic Container Service) vs. EKS(Elastic Kubernetes Service)
- Lambda(=FaaS and Serverless at Computing or Anonymous functions at Programming)와 Trigger
- Amazon Rekognition(Recognition=Machine and Deep Learning), Polly(=TTS), Lex(=Chatbot)
- Nework ACL vs. Security Group
| 항목      | Nework ACL | Security Group    |
| :-------: |:---------: | :---------------: |
| 적용 범위 | 서브넷     | 인스턴스 |
| 분류      | 차단, 허용 | 허용만 |
| 우선 순위 | 낮은 번호  | - |
| 상태      | Stateless  | Stateful |

## 3. Step for Create Network and EC2 Instances
### 3-1. Make `VPC`(=전체 인프라 네트워크)
- Select Region		: ap-northeast-2(=아시아 태평양-서울)
- Name Tag			: PLZ-PRD-VPC(PRD or STG or DEV)
- IPv4 CIDR			: 10.0.0.0/16(=2^16=2^8*2^8=256*256=65,563)
- VPN 설정에서 DNS 호스트 이름을 활성화

### 3-2. Make `Subnet`(=서비스별 네트워크) at VPC
- Select AZ(`Availability Zone`): 2a and 2c(하기는 장애 방지를 위해 Free Tier를 지원하는 2개의 Region에 Subnet을 생성)
- BST(Bastion)은 선택적으로 생성
- Name Tag(IPv4 CIDR) for 2a
	- PLZ-PRD-SUB-2a-PUB(10.0.10.0/24)
	- PLZ-PRD-SUB-2a-WS (10.0.11.0/24)
	- PLZ-PRD-SUB-2a-WAS(10.0.12.0/24)
	- PLZ-PRD-SUB-2a-DB (10.0.13.0/24)
- Name Tag(IPv4 CIDR) for 2c
	- PLZ-PRD-SUB-2c-PUB(10.0.0.0/24)
	- PLZ-PRD-SUB-2c-WS (10.0.1.0/24)
	- PLZ-PRD-SUB-2c-WAS(10.0.2.0/24)
	- PLZ-PRD-SUB-2c-DB (10.0.3.0/24)

### 3-3. Make `Routing Table`(=AZ간의 통신을 위한 라우팅 테이블) at VPC
- Name Tags: PLZ-PRD-RT-PUB, PLZ-PRD-RT-WS, PLZ-PRD-RT-WAS, PLZ-PRD-RT-DB
- Setting Subnet(PLZ-PRD-SUB-2c-PUB, PLZ-PRD-SUB-2a-PUB) for PLZ-PRD-RT-PUB at `Subnet Connection`
- Setting Subnet(PLZ-PRD-SUB-2c-WS,  PLZ-PRD-SUB-2a-WS)  for PLZ-PRD-RT-WS  at `Subnet Connection`
- Setting Subnet(PLZ-PRD-SUB-2c-WAS, PLZ-PRD-SUB-2a-WAS) for PLZ-PRD-RT-WAS at `Subnet Connection`
- Setting Subnet(PLZ-PRD-SUB-2c-DB,  PLZ-PRD-SUB-2a-DB)  for PLZ-PRD-RT-DB  at `Subnet Connection`

### 3-4. Make `Internet Gateway` for Public Subnet(=Public Subnet을 위한 Outbound 네트워크) at VPC
- Name Tag: PLZ-PRD-IGW
- Setting PLZ-PRD-IGW for Internet Gateway at PLZ-PRD-VPC

### 3-5. Make `NAT Gateway` for Private Subnet(=Private Subnet을 위한 Outbound 네트워크, 비용 절감을 위해 2c에만 생성) at VPC
- Name Tag		: PLZ-PRD-NGW-2c(and 2a)
- Select Subnet	: PLZ-PRD-SUB-2c-PUB(and 2a-PUB)
- Assign Elastic IP(참고: 최대 5개의 EIP에서 1개 사용됨) and Binding
- Update Elastic IP Name to WYC-DEV-EIP-NGW-2c

### 3-6. Setting up Routing(Public/Private Subnet를 위한 Outbound 설정 등) at `Routing Table` at VPC
- Select PLZ-PRD-RT-PUB and Setting PLZ-PRD-IGW    at `Routing`(Insert Outbound for 0.0.0.0)
- Select PLZ-PRD-RT-WS  and Setting PLZ-PRD-NGW-2c at `Routing`(Insert Outbound for 0.0.0.0)
- Select PLZ-PRD-RT-WAS and Setting PLZ-PRD-NGW-2c at `Routing`(Insert Outbound for 0.0.0.0)
- Select PLZ-PRD-RT-DB  and Setting PLZ-PRD-NGW-2c at `Routing`(Insert Outbound for 0.0.0.0)

### 3-7. Make SG(`Security Group`) and Binding(Inbound 설정) at VPC or EC2 or ALB
- Create PLZ-PRD-SG-ALB-WS는  필수 설정(HTTP/0.0.0.0, HTTPS/0.0.0.0 등)
- Create PLZ-PRD-SG-ALB-WAS는 필수 설정(Tomcat/WS 등)

- Create PLZ-PRD-SG-2c-PUB는 선택적으로 설정(SSH/IP 등)
- Create PLZ-PRD-SG-2c-WS는  필수 설정(SSH/IP, HTTP/0.0.0.0, HTTPS/0.0.0.0 등)
- Create PLZ-PRD-SG-2c-WAS는 선택적으로 설정(SSH/IP, Tomcat/WYC-PRD-SG-ALB-WAS 등)
- Create PLZ-PRD-SG-2c-DB는  선택적으로 설정(SSH/IP, MySQL/WAS 등)

- Create PLZ-PRD-SG-2a-PUB는 선택적으로 설정(SSH/IP 등)
- Create PLZ-PRD-SG-2a-WS는  필수 설정(SSH/IP, HTTP/0.0.0.0, HTTPS/0.0.0.0 등)
- Create PLZ-PRD-SG-2a-WAS는 선택적으로 설정(SSH/IP, Tomcat/WYC-PRD-SG-ALB-WAS 등)
- Create PLZ-PRD-SG-2a-DB는  선택적으로 설정(SSH/IP, MySQL/WAS 등)

### 3-8. Make EC2 for Bastion, WS(예: PLZ-PRD-EC2-2c-PUB-BASTION-001), WAS(예: PLZ-PRD-EC2-2c-PRI-NGNIX-001), DB 등 at EC2
- Configure Hostname
- Select Amazon Linux2nd(t2.micro)
- Create or Select Key Pair
- Select Public IP Enable
- Select Subnet & SG(Security Group)
- Configure Private IP

### 3-9. Make ELB(CLB or ALB) at EC2
#### 3-9-1. Make CLB(Classic LB, Support L4) for only HTTP
- Make SG(PLZ-PRD-SG-CLB-WS: HTTP)
- Select Classic LB(PLZ-PRD-CLB-WS) and Setting up ...
- Setting up Instances

#### 3-9-2. Make ALB(Application LB, Support L7) for HTTPS
- Make SG(PLZ-PRD-SG-ALB-WS: HTTP, HTTPS)
- Make Target Group(PLZ-PRD-ALB-WS-TG) and Setting up Instances
- Make Classic(참고: Instance vs. IP/Base on Container) LB(PLZ-PRD-ALB-WS) and Setting up ...

- Make SG(PLZ-PRD-SG-ALB-WAS: HTTP, HTTPS)
- Make Target Group(PLZ-PRD-ALB-WAS-TG) and Setting up Internal
- Make Classic(참고: Instance vs. IP/Base on Container) LB(PLZ-PRD-ALB-WAS) and Setting up ...

### 3-10. Make RDS
- ...

### 3-11. Make A Record for Domain Service at Route53
- Setting up ...


## 4. Step for Auto Scaling
1. `Create Network and EC2 Instances`
2. Make Start or Launch Template(PLZ-PRD-AS-TP: 자동 증설 서버 정보 설정) for Auto Scaling at Instance
3. Make Auto Scaling Group(ASG, PLZ-PRD-AS: 상기 설정된 시작 템플릿 기반으로 Min/Max수, 대상 지표 등을 설정)
4. Make Health Check 등


## 5. Step for Remove
1. Remove EC2
2. Remove Security Group(Delete Configuration, First!)
3. Remove Target Group and Load Balance
4. Remove Routing Table(Delete Configuration, First!)
5. Remove Internet and NAT Gateway
6. Remove Subnet
7. Remove VPC


## 6. Tip
### 6-1. DDNS(Dynamic DNS) with Route 53 + Lambda + CLI
1. A Record 설정 at Route 53(예: iot.plutozone.com + 192.168.0.1)
2. IAM 권한 설정(IAM > Role > Create Role > Select AWS and EC2 > 정책) and 저장(EC2-Route53-Update-Role) for 해당 EC2
```json
{
	"Version": "2012-10-17",
	"Statement": [
	{
		"Effect": "Allow",
		"Action": [
			"route53:ChangeResourceRecordSets",
			"route53:ListHostedZones",
			"route53:ListResourceRecordSets"
		],
		"Resource": "*"
	}
	]
}
```
3. Select 해당 EC2 > 작업 > 보안 > IAM 역할에서 상기 설정된 Role(EC2-Route53-Update-Role) 선택 후 확인
```bash
$ curl http://169.254.169.254/latest/meta-data/iam/info
$ aws sts get-caller-identity
```
4. Public IP를 조회 + AWS CLI를 이용해 Route 53의 DNS 레코드 업데이트 at 해당 EC2
```bash
$ vi updateIP.sh
#!/bin/bash

# 설정 변수
DOMAIN_NAME="iot.plutozone.com"
HOSTED_ZONE_ID="Z123456ABCDEFG"	# Route 53의 Hosted Zone ID 입력
TTL=300

# 현재 퍼블릭 IP 조회(checkip.amazonaws.com=현재 EC2 Public DNS 값)
IP=$(curl -s http://checkip.amazonaws.com)

# 기존 IP와 비교하여 업데이트 여부 판단
CURRENT_IP=$(dig +short $DOMAIN_NAME)

if [ "$IP" = "$CURRENT_IP" ]; then
	echo "IP unchanged: $IP"
	# 미변경 시 종료(exit 0)
	exit 0
fi

# DNS 레코드 업데이트
echo "Updating DNS from $CURRENT_IP to $IP"

aws route53 change-resource-record-sets --hosted-zone-id $HOSTED_ZONE_ID --change-batch '{
	"Comment": "Auto updating A record via script",
	"Changes": [{
		"Action": "UPSERT",
		"ResourceRecordSet": {
			"Name": "'"$DOMAIN_NAME"'",
			"Type": "A",
			"TTL": '"$TTL"',
			"ResourceRecords": [{"Value": "'"$IP"'"}]
		}
	}]
}'
$ chmod +x updateIP.sh
$ crontab -e
# 5분마다 실행
$ */5 * * * * /home/ec2-user/updateIP.sh >> /home/ec2-user/updateIP.log 2>&1
```


## 7. 주의, 권장 및 참고 사항
### 7-1. 주의
- **특정 조건 하에 최대 6개월까지 무료**
- **t2.micro는 720시간 동안만 무료로 사용 가능**
- **EIP(Elastic IP)를 생성 후 EC2 등에 할당하지 않을 경우 별도 추가 과금 발생**

### 7-2. 권장
- Default VPC 삭제(필요 시 Subnet, Routing Table, Internet Gateway, ACL, Security Group 등 포함) for 보안
- 생성한 VPCs, SGs, EC2s, LBs 등의 삭제는 역순으로
- 일반적으로 Bastion Server(관제용), NAT Gateway(패치용), EKS Management Server(관리용)만을 Public Zone에 배치

### 7-3. 참고
- ssh -i keyPair.pem id@targetHost at Bastion Server(# chmod 400 keyPair.pem)
- 새로운 SG(Security Group) 생성 후 Inbound를 설정할 경우 Outbound는 Any로 자동 설정됨
- Amazon Linux, Ubuntu의 기본 계정은 ec2-user, ubuntu
- EC2, NAT Gateway는 화면 상에서 즉시 삭제되지 않고 추후 삭제됨