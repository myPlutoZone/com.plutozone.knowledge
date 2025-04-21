#!/bin/bash

# [2025-04-21][pluto#brightsoft.co.kr][REPORT: 아파치 웹 서버의 액세스 로그에서 비정상적인 접속을 확인할 수 있는 셀 스크립트를 생성해 주세요. at ChatGPT]

# Apache access log 파일 경로
LOG_FILE="/var/log/apache2/access.log"

# 비정상적인 접속을 찾기 위한 설정
TIME_LIMIT="60"  # 60초 내의 요청을 다수 보내는 IP를 추적
ERROR_THRESHOLD=20  # 404 오류가 20번 이상 발생하는 IP를 추적

# 1. 짧은 시간 내에 다수의 요청을 보내는 IP 추출
echo "### 비정상적인 접속 (짧은 시간 내 다수 요청) ###"
awk '{print $1}' $LOG_FILE | sort | uniq -c | sort -nr | awk -v limit=$TIME_LIMIT '$1 > limit {print $2, "->", $1 " requests"}'

# 2. 404 오류를 많이 발생시킨 IP 추출
echo -e "\n### 비정상적인 접속 (404 오류가 많은 IP) ###"
awk '$9 == "404" {print $1}' $LOG_FILE | sort | uniq -c | sort -nr | awk -v threshold=$ERROR_THRESHOLD '$1 > threshold {print $2, "->", $1 " 404 errors"}'

# 3. 의심스러운 User-Agent 추출 (예시: "bot", "curl", "spider" 등)
echo -e "\n### 비정상적인 접속 (의심스러운 User-Agent) ###"
awk -F\" '{print $6}' $LOG_FILE | grep -i -E 'bot|curl|spider|wget|nmap' | sort | uniq -c | sort -nr

# 4. 특정 IP에서 다수의 요청을 보낸 기록 (같은 IP가 너무 많이 요청)
echo -e "\n### 특정 IP에서 다수 요청한 기록 ###"
awk '{print $1, $7}' $LOG_FILE | sort | uniq -c | sort -nr | head -n 20
