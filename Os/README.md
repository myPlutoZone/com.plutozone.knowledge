- ![Generic badge](https://img.shields.io/badge/Important-Contents1_Contents2-red.svg)
- ![Generic badge](https://img.shields.io/badge/Confirm-Contents1_Contents2-green.svg)
- ![Generic badge](https://img.shields.io/badge/Reference-Contents1_Contents2-blue.svg)


# Introduction to OS


# Contents


# Ubuntu Desktop(X-Windows)
- Ubuntu Software 업데이트 실패 시(=Snap Store 업데이트 오류 시)

```bash
$ sudo killall snap-store			# Snap Store 종료
$ sudo snap refresh				# Snap Store 갱신
$ snap refresh snap-store			# 상기(Snap Store 갱신) 오류 발생 시
# Setting > Ubuntu Software에서 Snap Store 업데이트 확인
```