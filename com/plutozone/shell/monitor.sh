#!/bin/bash

# ---------------------------------------------------
# System
# ---------------------------------------------------
HOSTNAME=$(hostname)

# ---------------------------------------------------
# Environment
# ---------------------------------------------------
FILE_LOG="/home/USER"
TIMESTAMP=$(date '+%Y-%m-%d %H:%M:%S')

# ---------------------------------------------------
# Messaging
# ---------------------------------------------------
IS_SEND="false"
METHOD="Email"
EMAILS="Email-1;Email-2"

# ---------------------------------------------------
# Checking
# ---------------------------------------------------
if [[ "$EMAILS" == "Email-1;Email-2" || "$FILE_LOG" == "/home/USER" ]]; then
	echo "Required ..."
	exit 1
fi

TARGET="$1"
case "$TARGET" in
	system)
		FILE_LOG="$FILE_LOG/monitorSystem.log"

		CPU=50
		MEMORY=50
		DISK=50
		. ./monitorSystem.sh

		LOG_FILE="./access.log"
		# LOG_FILE="/var/log/apache2/access.log"
		RPS=100
		. ./monitorSystemApache.sh
		;;
	service)
		FILE_LOG="$FILE_LOG/monitorService.log"
		TIME_OUT=5
		URLS=(
			"https://test.google.co.kr"
			"https://www.google.com"
			"https://www.github.com"
		)
		. ./monitorService.sh
		;;
	security)
		echo "security"
		;;
	inspection)
		echo "inspection"
		;;
	*)
		echo "Usages: $0 {system | service | security | inspection}"
		exit 1
		;;
esac
