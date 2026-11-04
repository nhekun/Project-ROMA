@echo off
set "JAVA_HOME=D:\Tech Stuff\Project ROMA\Android DEV App\jbr"
set "GRADLE_OPTS=--enable-native-access=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED"
"C:\Users\Samuel\.gradle\wrapper\dists\gradle-8.10.2-bin\7b141d7a9a83cf5292d4500c62f644b48d080984736f2c044a687e189bcaa841\gradle-8.10.2-bin\bin\gradle.bat" -p "D:\Tech Stuff\Project ROMA\Project ROMA" :roman:test --no-daemon --console=plain --stacktrace > "D:\Tech Stuff\Project ROMA\Project ROMA\gradle_test_output.log" 2>&1
echo Exit code: %ERRORLEVEL%
type "D:\Tech Stuff\Project ROMA\Project ROMA\gradle_test_output.log"