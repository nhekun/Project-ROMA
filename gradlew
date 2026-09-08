#!/bin/bash
# Gradle startup script for POSIX

# Determine the Java command to use
if [ -n "$JAVA_HOME" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

# Maximum number of arguments
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

APP_BASE_NAME="Gradle"
APP_HOME="$( cd "$( dirname "$0" )" && pwd )"

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

exec "$JAVACMD" $DEFAULT_JVM_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"