# Introduction to OS



## Contents
- Ubuntu
- Windows

## Ubuntu
### Desktop(X-Windows)
- Ubuntu Software 업데이트 실패 시(=Snap Store 업데이트 오류 시)

```bash
$ sudo killall snap-store			# Snap Store 종료
$ sudo snap refresh				# Snap Store 갱신
$ snap refresh snap-store			# 상기(Snap Store 갱신) 오류 발생 시
# Setting > Ubuntu Software에서 Snap Store 업데이트 확인
```


## Windows
- 여러 개의 파일명을 동시에 편집 at PowerShell

```bash
PS D:\Temp> dir | rename-item -newname {$_.name -replace "OLD 단어", "NEW 단어"}	# D:\Temp의 모든 파일명에서 "OLD 단어"를 "NEW 단어"로 편집
```

- tree

```bash
D:\Temp>tree /f /a		# D:\Temp 하위 경로의 폴더 구조 또는 파일(/f)을 그래픽 또는 텍스트 문자(/a)로 화면에 표시
```