# com.plutozone.knowledge.development.Command


- 환경 변수 설정 및 확인
	| Linux                                     | Windows                               | 설명 |
	| :---------------------------------------- | :------------------------------------ | :--- |
	| pluto@UbuntuS24:~$ export                 | C:\Users\pluto>set	                  | 모든 환경 변수 확인 |
	| pluto@UbuntuS24:~$ export MY_ENV=hello    | C:\Users\pluto>set MY_ENV=hello       | 환경 변수 설정(export를 생략하면 local 환경 변수 at Linux) |
	| pluto@UbuntuS24:~$ echo $MY_ENV           | C:\Users\pluto>echo %MY_ENV%	        | 환경 변수 확인 |
	| pluto@UbuntuS24:~$ echo $PATH             | C:\Users\pluto>PATH	                  | 개별 환경 변수 확인(예: PATH) |
- 포트 확인
	```cmd
	C:\>netstat -ano | find "80"		# "80" 확인 vs. "LISTEN" 등
	```