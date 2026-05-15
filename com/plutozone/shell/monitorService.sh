#!/bin/bash

# ---------------------------------------------------
# Configure
# ---------------------------------------------------
TO="Email-1;Email-2"
FILE_LOG="/home/USER/monitorService.log"
TIMESTAMP=$(date '+%Y-%m-%d %H:%M:%S')

URLS=(
	"https://test.google.co.kr"
	"https://www.google.com"
	"https://www.github.com"
)
TIME_OUT=5

# ---------------------------------------------------
# Message
# ---------------------------------------------------
ALERT_MESSAGE=""

# TO="receiver@example.com"
SUBJECT="Test Mail"
BODY="This is a email sent from Linux Shell using Gmail SMTP."

for URL in "${URLS[@]}"; do
	START_TIME=$(date +%s%3N)

	HTTP_CODE=$(curl -s -o /dev/null -w "%{http_code}" --max-time $TIME_OUT "$URL")
	CURL_EXIT_CODE=$?

	END_TIME=$(date +%s%3N)
	RESPONSE_TIME=$((END_TIME - START_TIME))

	# [2026-03-30 18:39:05] [ERROR] https://test.google.co.kr request failed(TIME_OUT, 112ms)
	# [2026-03-30 18:39:05] [OK] https://www.google.com(HTTP 200, 312ms)
	# [2026-03-30 18:39:05] [WARN] https://www.github.com returned(HTTP 301, 84ms)

	if [ $CURL_EXIT_CODE -ne 0 ]; then
		printf "[%s] [ERROR] %s request failed(TIME_OUT, %sms)\n" \
		"$TIMESTAMP" "$URL" "$RESPONSE_TIME" >> "$FILE_LOG"
		
		ALERT_MESSAGE="[${TIMESTAMP}] [ERROR] ${URL} request failed(TIME_OUT, ${RESPONSE_TIME}ms)"
		# ---------------------------------------------------
		# Mailing
		echo -e "Subject: [::: Error :::][$URL]\nTo: $TO\n\n$ALERT_MESSAGE" | msmtp "$TO"
		# echo -e "Subject: $SUBJECT\nTo: $TO\n\n$BODY" | msmtp "$TO"
		# ---------------------------------------------------

	elif [ "$HTTP_CODE" -eq 200 ]; then
		printf "[%s] [OK] %s(HTTP %s, %sms)\n" \
		"$TIMESTAMP" "$URL" "$HTTP_CODE" "$RESPONSE_TIME" >> "$FILE_LOG"

	else
		printf "[%s] [WARN] %s returned(HTTP %s, %sms)\n" \
		"$TIMESTAMP" "$URL" "$HTTP_CODE" "$RESPONSE_TIME" >> "$FILE_LOG"
		
		ALERT_MESSAGE="[${TIMESTAMP}] [WARN] ${URL} returned(HTTP ${HTTP_CODE}, ${RESPONSE_TIME}ms)"
		# ---------------------------------------------------
		# Mailing
		echo -e "Subject: [::: Warning :::][$URL]\nTo: $TO\n\n$ALERT_MESSAGE" | msmtp "$TO"
		# echo -e "Subject: $SUBJECT\nTo: $TO\n\n$BODY" | msmtp "$TO"
		# ---------------------------------------------------
	fi
done
