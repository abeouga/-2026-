set JAVA_HOME=%~dp0jdk
set PATH=%JAVA_HOME%\bin;%PATH%
java -cp "%~dp0h2\bin\h2-2.1.214.jar" org.h2.tools.Server -ifNotExists
