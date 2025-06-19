# com.plutozone.knowledge.os.Windows


- 탐색기에서 Copilot 제거
```cmd
C:\>regedit		# KEY_CURRENT_USER\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\Advanced를 찾아서 ShowCopilotButton라는 DWORD(32bit) 이름을 추가하고 0을 설정
```

- 여러 개의 파일명을 동시에 수정 at PowerShell
```cmd
PS D:\Temp>dir | rename-item -newname {$_.name -replace "OLD 단어", "NEW 단어"}	# D:\Temp의 모든 파일명에서 "OLD 단어"를 "NEW 단어"로 편집
```

- tree
```cmd
D:\Temp>tree /f /a		# D:\Temp 하위 경로의 폴더 구조 또는 파일(/f)을 그래픽 또는 텍스트 문자(/a)로 화면에 표시
```