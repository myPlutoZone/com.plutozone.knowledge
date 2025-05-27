# com.plutozone.knowledge.management.forensic(디지털 포렌식)

- 이미지와 파일 병합
```cmd
C:\> copy /b 파일.jpg + 파일.txt/doc/zip 결과.jpg
```

- 파일 해쉬 명령어 at Windows PowerShell
```cmd
C:\> Get-FileHash -Path "C:\Path\FileName"															# 파일
C:\> Get-ChildItem -Path "C:\Path" | ForEach-Object {Get-FileHash $_.FullName -Algorithm SHA256}	# 폴더 내의 모든 파일
```

- 포렌식 도구 및 사용법(김도촬 at 구글)
	- FTK Imager
	- OpenStego
	- HashCalc(파일 해쉬 생성 및 확인)
	- Resource Hacker(파일 아이콘 변경)
	- TriDNet(파일 형식/시그니처 확인, https://mark0.net/soft-tridnet-e.html)