# Cloud
- OpenStack for IaaS vs.OpenShift for PaaS by Red Hat

# VPC(Virtual Private Cloud)
- Reference
	- Inside(=Private) vs. Outside(=Public) or All Inside(=Private)
	- CIDR(Classless Inter-Domain Routing) is not modified at AWS
	- Private IP Band
		- 10.0.0.0 ~ 10.255.255.255(10.0.0.0/8)
		- 172.16.0.0 ~ 172.31.255.255(172.16.0.0/12)
		- 192.168.0.0 ~ 192.168.255.255(192.168.0.0/16)
	- 2A/2C AZ(Availability Zone) is difference from 2B/2D at ap-northeast-2
- Create Network for EC2 Instances
	1. Make VPC at VPC
		- Select Region: ap-northeast-2
		- Name Tag: PLZ-VPC-PRD(PRD or STG or DEV)
		- IPv4 CIDR: 10.255.0.0/16(65,563)
	2. Make Subnet at VPC
		- Select AZ: 2A or 2C
		- 2A
			- Name Tag(IPv4 CIDR): PLZ-VPC-PRD-2A-PUB-WS(10.255.0.0/24)
			- Name Tag(IPv4 CIDR): PLZ-VPC-PRD-2A-PRI-WAS(10.255.32.0/24)
		- 2C
			- Name Tag(IPv4 CIDR): PLZ-VPC-PRD-2C-PUB-WS(10.255.128.0/24)
			- Name Tag(IPv4 CIDR): PLZ-VPC-PRD-2C-PRI-WAS(10.255.160.0/24)
	3. Make Routing Table at VPC
		- Name Tag: PLZ-VPC-PRD-RT-PUB
		- Name Tag: PLZ-VPC-PRD-RT-PRI
		- Select Routing Table at Subnet
	4. Make Internet Gateway for Public Subnet
		- Name Tag: PLZ-VPC-PRD-IGW
		- Select PLZ-VPC-PRD-IGW for Internet Gateway at PLZ-VPC-PRD-RT-PUB

5. NAT Gateway 생성(Private Subnet에 있는 인스턴스가 인터넷을 사용할 수 있도록 해주기 위함)	
	1) VEC-PRD-NGW-2A
	2) Public인 PUB-2A 또는 PUB-2C로 지정
	3) Elastic IP 지정 또는 추가
	4) PRI 영역에 NAT Gateway 지정
	

# EC2
- LB(Load Balancer)

# Route 53
