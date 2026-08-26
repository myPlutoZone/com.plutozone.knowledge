#!/bin/bash

# ---------------------------------------------------
# CPU Usage(%) or Average(5 times + 3 second) Usage(%)
# ---------------------------------------------------
USAGE_CPU=$(mpstat | tail -1 | awk '{printf "%.0f\n", 100-$NF}')
# USAGE_CPU=$(mpstat | tail -1 | awk '{print 100-$NF}')
# USAGE_CPU=$(top -bn5 -d 3 | awk '/Cpu/ {usage=100-$8; sum+=usage; count++} END {printf("%d", sum/count)}')
# USAGE_CPU=$(top -bn1 | awk '/Cpu/ {usage=100-$8; printf("%d", usage)}')

# ---------------------------------------------------
# Memory Usage(%)
# ---------------------------------------------------
USAGE_MEMORY=$(free | awk '/Mem/ {printf("%.0f"), $3/$2 * 100}')

# ---------------------------------------------------
# Disk Usage(%)
# ---------------------------------------------------
USAGE_DISK=$(df / | awk 'NR==2 {gsub("%",""); print $5}')

# ---------------------------------------------------
# Email Subject and Content
# ---------------------------------------------------
EMAIL_PREFIX="[::: Warning :::][$HOSTNAME]"
EMAIL_SUBJECT=""
USAGE_ITEMS=()
EMAIL_CONTENT=""

# if ((USAGE_CPU >= CPU )); then
#	USAGE_ITEMS+=("CPU Average Usage($CPU%) Over")
#	EMAIL_CONTENT+="[$(date '+%Y-%m-%d %H:%M:%S')] CPU Average(5 times + 3 second) Usage: ${USAGE_CPU}%\n"
# fi

if (( USAGE_CPU >= CPU )); then
	USAGE_ITEMS+=("CPU Usage($CPU%) Over")
	EMAIL_CONTENT+="[$(date '+%Y-%m-%d %H:%M:%S')] CPU Usage: ${USAGE_CPU}%\n"
fi

if (( USAGE_MEMORY >= MEMORY )); then
	USAGE_ITEMS+=("Memory Usage($MEMORY%) Over")
	EMAIL_CONTENT+="[$(date '+%Y-%m-%d %H:%M:%S')] Memory Usage: ${USAGE_MEMORY}%\n"
fi

if (( USAGE_DISK >= DISK )); then
	USAGE_ITEMS+=("Disk Usage($DISK%) Over")
	EMAIL_CONTENT+="[$(date '+%Y-%m-%d %H:%M:%S')] Disk Usage: ${USAGE_DISK}%\n"
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