# Cloud
- OpenStack for IaaS vs.OpenShift for PaaS by Red Hat


# VPC(Virtual Private Cloud)
- Reference
	- Inside(=Private) for WAS vs. Outside(=Public) for WS or All Inside(=Private)
	- CIDR(Classless Inter-Domain Routing) is not modified at AWS
	- Private IP Band
		- 10.0.0.0 ~ 10.255.255.255(10.0.0.0/8)
		- 172.16.0.0 ~ 172.31.255.255(172.16.0.0/12)
		- 192.168.0.0 ~ 192.168.255.255(192.168.0.0/16)
	- 2A/2C AZ(Availability Zone) is difference from 2B/2D at ap-northeast-2
- Create Network at VPC for EC2 Instances
	1. Make VPC
		- Select Region: ap-northeast-2
		- Name Tag: PLZ-PRD-VPC(PRD or STG or DEV)
		- IPv4 CIDR: 10.255.0.0/16(65,563)
	2. Make Subnet
		- Select AZ: 2A or 2C
		- 2A
			- `PLZ-PRD-VPC-2A-BASTION`
			- Name Tag(IPv4 CIDR): PLZ-PRD-VPC-2A-PUB(10.255.0.0/24)
			- Name Tag(IPv4 CIDR): PLZ-PRD-VPC-2A-PRI(10.255.32.0/24)
		- 2C
			- Name Tag(IPv4 CIDR): PLZ-PRD-VPC-2C-PUB(10.255.128.0/24)
			- Name Tag(IPv4 CIDR): PLZ-PRD-VPC-2C-PRI(10.255.160.0/24)
	3. Make Routing Table
		- Name Tag: PLZ-PRD-RT-PUB
		- Name Tag: PLZ-PRD-RT-PRI
		- Select Routing Table at Subnet
	4. Make Internet Gateway for Public Subnet
		- Name Tag: PLZ-PRD-IGW
		- Select PLZ-PRD-IGW for Internet Gateway at PLZ-PRD-RT-PUB
	5. Make NAT Gateway for Private Subnet
		- Name Tag: PLZ-PRD-NGW-2A(and 2C)
		- Select Subnet: 2A-PUB(or 2C-PUB)
		- Make Elastic IP and Binding
		- Select PLZ-PRD-NGW-2A for NAT Gateway at PLZ-PRD-RT-PRI
	6. Make SG(Security Group) and Binding
		- For Subnet
			- `PLZ-PRD-SG-2A-BASTION`
			- PLZ-PRD-SG-2A-PUB(SSH?, HTTP?, HTTPS?)
			- PLZ-PRD-SG-2A-PUB(SSH?, HTTP?, HTTPS?)
			- PLZ-PRD-SG-2A-PRI(SSH?, HTTP?, HTTPS?)
			- PLZ-PRD-SG-2C-PUB(SSH?, HTTP?, HTTPS?)
			- PLZ-PRD-SG-2C-PRI(SSH?, HTTP?, HTTPS?)
		- For ELB and Auto Scaling
			- PLZ-PRD-SG-LB(HTTP, HTTPS)
			- PLZ-PRD-SG-AS(HTTP, HTTPS)


# EC2(Elastic Computing Cloud)
- Reference
	- AMI(Amazon Machine Image): Amazon Linux, Ubuntu, Windows, ...
	- ELB(Elastic Load Balancer) Function vs. Auto Scaling by EC2(Amazon EC2 Auto Scaling, Not Support LB and HC)
		- Load Balancer
		- Health Check
		- Auto Scaling
	- ELB(Elastic Load Balancer) Option
		- Application Load Balancer(L7 + Containers, ...)
		- Network Load Balancer(L4, ...)
		- Classic Load Balancer(L7/4 + EC2 Instances, ...)
	- Storage at EC2
		- Instance Storage(C:)
		- EBS(Elastic Block Storage, D:)
- Create EC2 Instances
	- Make PEM for ec2-user at Amazon Linux 2
	- Install Nginx or Tomcat


# Create Auto Scaling
1. Create VPCs
	- Make VPC
	- Make Subnet
	- Make Security Group
	- Make Internet Gateway and Select Public Subnet
	- Make NAT Gateway and Select Private Subnet
2. Create EC2
3. Create Target Group(PLZ-PRD-LB-TG) for ELB
4. Create ELB(PLZ-PRD-LB)
5. Create Launch Template(PLZ-PRD-AS-TP) for Auto Scaling
6. Create Auto Scaling Group(PLZ-PRD-AS)


# ECS(Elastic Container Service)


# EKS(Elastic Kubernetes Service)


# Route 53
