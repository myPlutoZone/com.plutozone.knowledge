#!/bin/bash

EMAIL_CONTENT=""

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
		# ---------------------------------------------------
		# Logging
		# ---------------------------------------------------
		printf "[%s] [ERROR] %s request failed(TIME_OUT, %sms)\n" "$(date '+%Y-%m-%d %H:%M:%S')" "$URL" "$RESPONSE_TIME" >> "$FILE_LOG"
		
		EMAIL_CONTENT="[${TIMESTAMP}] [ERROR] ${URL} request failed(TIME_OUT, ${RESPONSE_TIME}ms)"
		# ---------------------------------------------------
		# Mailing
		# ---------------------------------------------------
		if [[ "$IS_SEND" == "true" ]]; then
			echo -e "Subject: [::: Error :::][$URL]\nTo: $EMAILS\n\n$EMAIL_CONTENT" | msmtp "$EMAILS"
		fi
	elif [ "$HTTP_CODE" -eq 200 ]; then
		# ---------------------------------------------------
		# Logging
		# ---------------------------------------------------
		printf "[%s] [OK] %s(HTTP %s, %sms)\n" "$(date '+%Y-%m-%d %H:%M:%S')" "$URL" "$HTTP_CODE" "$RESPONSE_TIME" >> "$FILE_LOG"
	else
		# ---------------------------------------------------
		# Logging
		# ---------------------------------------------------
		printf "[%s] [WARN] %s returned(HTTP %s, %sms)\n" "$(date '+%Y-%m-%d %H:%M:%S')" "$URL" "$HTTP_CODE" "$RESPONSE_TIME" >> "$FILE_LOG"
		
		EMAIL_CONTENT="[${TIMESTAMP}] [WARN] ${URL} returned(HTTP ${HTTP_CODE}, ${RESPONSE_TIME}ms)"
		# ---------------------------------------------------
		# Mailing
		# ---------------------------------------------------
		if [[ "$IS_SEND" == "true" ]]; then
			echo -e "Subject: [::: Warning :::][$URL]\nTo: $EMAILS\n\n$EMAIL_CONTENT" | msmtp "$EMAILS"
		fi
	fi
done
