# com.plutozone.knowledge.language.NodeJs(Node.js + NestJS)


- Requirements for NestJS(NestJS 11.0.7 requires node version higher than 20.11)
	- Node.js
	- IDE(Visual Studio Code 등) or Editor

- Install NestJS CLI(Command Line Interface)
```cmd
C:\>node -v					# Node 버전 확인
C:\>node ./src/app.js		# Node 실행
C:\>npm i -g @nestjs/cli	# npm(Node Package Manager)은 Node.js의 기본 패키지 관리자 + i(install) + -g(Global)
```

- Create NestJS Project
```cmd
D:\Business\00.com.plutozone>nest new xx.com.plutozone.nestjs-first		# 프로젝트(xx.com.plutozone.nestjs-first) 생성
D:\Business\00.com.plutozone>get-ExecutionPolicy						# [참고] 보안 오류 발생 시 현재 권한 상태 확인(Restricted: default로 스크립트 파일 실행 불가) 후 프로젝트 생성 재시도
D:\Business\00.com.plutozone>Set-ExecutionPolicy RemoteSigned			# [참고] 보안 오류 발생 시 VS Code를 관리자 권한으로 실행(RemoteSigned: 본인 또는 서명된 스크립트 실행 가능) 후 프로젝트 생성 재시도
```

- NestJS Project Files
	- dist(배포 폴더=.ts가 컴파일된 .js 파일 저장 위치)
	- node_modules(사용하는 모듈 폴더)
	- src(소스 폴더)
		- app.controller.ts(컨트롤러 파일)
		- app.service.ts(서비스 파일)
		- app.modules.ts(컨트롤러, 서비스 등을 정의)
		- main.ts(프로젝트 시작 파일)
	- test(테스트 폴더)
	- package.json(프로젝트 설정 파일)
		- scripts 태그의 start, build, test 등의 스크립트가 기재됨
			- "start:prod": "node dist/main"(프로젝트 시작 파일=/src/main.ts)
		- dependencies, devDependencies에 기재된 모듈이 /node_modules에 설치됨

- Run NestJS Project
```cmd
D:\Business\00.com.plutozone\xx.com.plutozone.nestjs-first>npm start	# http://localhost:3000에서 확인
```

- NestJS 구조
	- Root Module(=Package)
		- A Module
		- B Module
		- ...
	- A Module
		- Controller
		- Provider(=Service)
	- B Module
		- Controller
		- Provider(=Service)
		
- Generate Code
```cmd
D:\Business\00.com.plutozone\xx.com.plutozone.nestjs-first>nest g module member
D:\Business\00.com.plutozone\xx.com.plutozone.nestjs-first>nest g controller member
D:\Business\00.com.plutozone\xx.com.plutozone.nestjs-first>nest g service member
```

- Middleware(=AOP + Interceptor) vs. Functional Middleware vs. Multiple Middleware vs. Global Middleware

- TypeORM
```cmd
D:\Business\00.com.plutozone\xx.com.plutozone.nestjs-first>npm install --save @nestjs/typeorm typeorm mysql2
```