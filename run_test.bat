@echo off
cd /d "%~dp0"
set "JAVA_HOME=D:\Tech Stuff\Project ROMA\Android DEV App\jbr"
set "GRADLE_OPTS=--enable-native-access=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED"
call "%~dp0gradlew.bat" :roman:test --no-daemon --console=plain --stacktrace > gradle_test_output.log 2>&1
echo Exit code: %ERRORLEVEL%
type gradle_test_output.log