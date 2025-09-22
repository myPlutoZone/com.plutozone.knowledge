# com.plutozone.knowledge.os.Windows


## 개발 시
- Curl(Client URL)
```cmd
# 상세(-v)
C:\>curl -X GET  http://localhost:8080 -v
# POST + JSON
C:\>curl -X POST http://localhost:8080/api/board -d "{\"title\":\"test\", \"contents\":\"test\"}" -H "Content-type: application/json" -v
```


## 최적화
- 장치 Driver 청소하기
```
1. 시스템 등록정보> 고급 > 환경변수 > 새로 만들기를 통하여 환경 변수를 설정
DEVMGR_SHOW_NONPRESENT_DEVICES: 1
또는
cmd에서 SET DEVMGR_SHOW_NONPRESENT_DEVICES=1

2. 장치관리자에서 보기> 숨김 장치표시를 통하여 숨겨진 장치와 제거된 장치를 청소
```

- Microsoft Office x.x
```
"문서가 저장되었지만 저장할 공간이 충분하지 않기 때문에 음성 인식 데이터가 손실되었습니다. 녹음하지 않을 경우 마이크를 끄고 디스크에서 사용 가능한 저장 공간을 확인하십시오" 발생 시
도구 > 옵션 > 저장 > 저장 옵션에서 "언어 데이터 포함" 체크 해제
```

- 필요 없는 파일 삭제
	- C:\WINDOWS\SoftwareDistribution
	- C:\WINDOWS\Temp
	- C:\Documents and Settings\%USER%\Local Settings\Temp

- 탐색기에서 Copilot 제거
```cmd
C:\>regedit		# KEY_CURRENT_USER\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\Advanced를 찾아서 ShowCopilotButton라는 DWORD(32bit) 이름을 추가하고 0을 설정
```


## Tip
### File
- 지워지는 않는 폴더/파일 삭제
```cmd
D:\Temp>rmdir \\.\<DRIVE>:\<PATH>\<DIRECTORY>		# 예) rmdir \\.\C:\Temp\A /s
```

- 여러 개의 파일명을 동시에 수정 at PowerShell
```cmd
PS D:\Temp>dir | rename-item -newname {$_.name -replace "OLD 단어", "NEW 단어"}	# D:\Temp의 모든 파일명에서 "OLD 단어"를 "NEW 단어"로 편집
```

- tree
```cmd
D:\Temp>tree /f /a		# D:\Temp 하위 경로의 폴더 구조 또는 파일(/f)을 그래픽 또는 텍스트 문자(/a)로 화면에 표시
```