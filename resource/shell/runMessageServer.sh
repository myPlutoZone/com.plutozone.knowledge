#!/bin/bash

PRC_NM=com.plutozone.util.messenger.MessageServer
# PRC_VER=1.0.0

export JAVA_HOME=/usr/local/jdk1.8.0_201
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=.:$JAVA_HOME/jre/lib/ext:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin
export GC_PATH=~/gc

# echo $JAVA_HOME
# echo $PATH
# java -version

PID=`ps -ef | grep $PRC_NM | grep -v grep | awk '{print $2}'`
# PID=`ps -ef | grep $PRC_NM-$PRC_VER.jar | grep -v grep | awk '{print $2}'`
DATETIME=$(date "+%Y%m%d_%H%M%S")

if [ "x$PID" != "x" ] && kill -0 $PID 2>/dev/null; then
	RUNNING=1
else
	RUNNING=0
fi

case "$1" in
	start)
		if [ $RUNNING -eq 1 ]; then
			echo "-------------------------------------------------------"
			echo " [WARNING] The $PRC_NM is already running!"
			echo "-------------------------------------------------------"
		else
			if [ -e $GC_PATH/$PRC_NM.log ]; then
				cp $GC_PATH/$PRC_NM.log $GC_PATH/$PRC_NM.$DATETIME.backup.log
			fi
			
			java -server -Xms128M -Xmx256M -Xloggc:$GC_PATH/$PRC_NM.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -classpath ./bin:./lib/log4j-1.2.15.jar:./lib/slf4j-api-1.6.6.jar:./lib/slf4j-log4j12-1.6.6.jar $PRC_NM 8888 > /dev/null &
			# java -jar -server -Xms128M -Xmx256M -Xloggc:$GC_PATH/$PRC_NM.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps $PRC_NM-$PRC_VER.jar > /dev/null &
			echo "-----------------------------------------------------------"
			echo " [SUCCESS] The $PRC_NM has started successful."
			echo "-----------------------------------------------------------"
		fi
		;;
	stop)
		if [ $RUNNING -eq 1 ]; then
			kill -9 $PID
			cp $GC_PATH/$PRC_NM.log $GC_PATH/$PRC_NM.$DATETIME.log
			rm $GC_PATH/$PRC_NM.log
			echo "----------------------------------------------------------"
			echo " [SUCCESS] The $PRC_NM has stoped successful."
			echo "----------------------------------------------------------"
		else
			echo "-----------------------------------------------------------"
			echo " [WARNING] The $PRC_NM process is not nunning!"
			echo "-----------------------------------------------------------"
		fi
		;;
	restart)
		if [ $RUNNING -eq 1 ]; then
			$0 stop
			sleep 3
			echo "----------------------------------------"
			echo " [SUCCESS] Sleep 3 Seconds."
			echo "----------------------------------------"
			$0 start
		else
			echo "-----------------------------------------------------------"
			echo " [WARNING] The $PRC_NM process is not nunning!"
			echo "-----------------------------------------------------------"
		fi
		;;
	status)
		if [ $RUNNING -eq 1 ]; then
			echo "-------------------------------------------------------------------"
			echo " [INFORMATION] The $PRC_NM process is already running!"
			echo "-------------------------------------------------------------------"
		else
			echo "---------------------------------------------------------------"
			echo " [INFORMATION] The $PRC_NM process is not nunning!"
			echo "---------------------------------------------------------------"
		fi
		;;
	*)
		echo "[USAGE] $0 { start | stop | restart | status }"
		exit 1
		;;
esac