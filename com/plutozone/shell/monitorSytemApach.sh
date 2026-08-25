#!/bin/bash

# ---------------------------------------------------
# RPS(Requests Per Second)
# ---------------------------------------------------
LIMIT="25/Aug/2026:12:00:00"
# LIMIT=$(date -d '1 hour ago' '+%d/%b/%Y:%H:%M:%S')

STATUS_RPS=$(awk -v rps="$RPS" -v limit="$LIMIT" '
{
	t=substr($4,2,20)
	if (t >= limit)
		c[t]++
}
END {
	for (t in c)
		if (c[t] >= rps)
			print c[t], t
}
' "$LOG_FILE")

# ---------------------------------------------------
# 404(File Not Found)
# ---------------------------------------------------

# ---------------------------------------------------
# User-Agent("bot", "curl", "spider", ...)
# ---------------------------------------------------

# ---------------------------------------------------
# Email Subject and Content
# ---------------------------------------------------
EMAIL_PREFIX="[::: Warning :::][Apache Web Server at $HOSTNAME]"
EMAIL_SUBJECT=""
USAGE_ITEMS=()
EMAIL_CONTENT=""

if [[ -n "$STATUS_RPS" ]]; then
	USAGE_ITEMS+=("Requests Per Second($RPS) Over")
	EMAIL_CONTENT+="[$TIMESTAMP] Requests Per Second\n${STATUS_RPS}\n"
fi

EMAIL_SUBJECT="$EMAIL_PREFIX"
if (( ${#USAGE_ITEMS[@]} > 0 )); then
	EMAIL_SUBJECT+="$(printf "%s + " "${USAGE_ITEMS[@]}" | sed 's/ + $//')"
fi

# ---------------------------------------------------
# Logging
# ---------------------------------------------------
# [2026-05-08][pluto@plutozone.com][REPORT: Always logging]
# if [[ -n "$EMAIL_CONTENT" ]]; then
	printf '%b' "$EMAIL_CONTENT" >> "$FILE_LOG"
	# echo -e "$EMAIL_CONTENT" >> $FILE_LOG
# fi

# ---------------------------------------------------
# Emailing
# ---------------------------------------------------
if [[ "$IS_SEND" == "true" && -n "$EMAIL_CONTENT" ]]; then
	echo -e "Subject: $EMAIL_SUBJECT\nTo: $EMAILS\n\n$EMAIL_CONTENT" | msmtp "$EMAILS"
fi