#!/bin/bash

# ---------------------------------------------------
# 1. Configure
# ---------------------------------------------------
TO="Email-1;Email-2"
FILE_LOG="/home/USER/monitorSystem.log"
TIMESTAMP=$(date '+%Y-%m-%d %H:%M:%S')

HOSTNAME=$(hostname)

# ---------------------------------------------------
# 2-1. CPU Average(5 times + 3 second) USAGE_CPUUSAGE_CPU Usage(%)
# ---------------------------------------------------
AVERAGE_CPU=$(top -bn5 -d 3 | awk '/Cpu/ {usage=100-$8; sum+=usage; count++} END {printf("%d", sum/count)}')
# USAGE_CPU=$(top -bn1 | awk '/Cpu/ {usage=100-$8; printf("%d", usage)}')

# ---------------------------------------------------
# 2-2. Memory Usage(%)
# ---------------------------------------------------
USAGE_MEMORY=$(free | awk '/Mem/ {printf("%.0f"), $3/$2 * 100}')

# ---------------------------------------------------
# 2-3. Disk Usage(%)
USAGE_DISK=$(df / | awk 'NR==2 {gsub("%",""); print $5}')

# ---------------------------------------------------
# 4. Message
# ---------------------------------------------------
ALERT_TITLE=""
ALERT_PREFIX="[::: Warning :::][Rocky-001]"
# ALERT_PREFIX="[::: Warning :::][$HOSTNAME]"
ALERT_ITEMS=()
ALERT_MESSAGE=""

# ---------------------------------------------------
# 5. Check Over
# ---------------------------------------------------
if ((AVERAGE_CPU > 30 )); then
	ALERT_ITEMS+=("CPU Average(30%) Usage Over")
	ALERT_MESSAGE+="[$TIMESTAMP] CPU Average(5 times + 3 second) Usage: ${AVERAGE_CPU}%\n"
fi

# if (( USAGE_CPU > 30 )); then
#	ALERT_ITEMS+=("CPU Usage(30%) Over")
#	ALERT_MESSAGE+="[$TIMESTAMP] CPU Usage: ${USAGE_CPU}%\n"
# fi

if (( USAGE_MEMORY > 80 )); then
	ALERT_ITEMS+=("Memory Usage(80%) Over")
	ALERT_MESSAGE+="[$TIMESTAMP] Memory Usage: ${USAGE_MEMORY}%\n"
fi

if (( USAGE_DISK > 80 )); then
	ALERT_ITEMS+=("Disk Usage(80%) Over")
	ALERT_MESSAGE+="[$TIMESTAMP] Disk Usage: ${USAGE_DISK}%\n"
fi

ALERT_TITLE="$ALERT_PREFIX"
if (( ${#ALERT_ITEMS[@]} > 0 )); then
	ALERT_TITLE+="$(printf "%s + " "${ALERT_ITEMS[@]}" | sed 's/ + $//')"
fi

# Logging
# [2026-05-08][pluto@plutozone.com][REPORT: always logging]
# if [[ -n "$ALERT_MESSAGE" ]]; then
	echo -e "$ALERT_MESSAGE" >> $FILE_LOG
# fi

# TO="receiver@example.com"
SUBJECT="Test Mail"
BODY="This is a email sent from Linux Shell using Gmail SMTP."

# echo $ALERT_TITLE
# echo $ALERT_MESSAGE

# ---------------------------------------------------
# 6. Mailing
# ---------------------------------------------------
if [[ -n "$ALERT_MESSAGE" ]]; then
	echo -e "Subject: $ALERT_TITLE\nTo: $TO\n\n$ALERT_MESSAGE" | msmtp "$TO"
fi
# echo -e "Subject: $SUBJECT\nTo: $TO\n\n$BODY" | msmtp "$TO"
