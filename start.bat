set JAVA_HOME=%~dp0jdk
set CATALINA_HOME=%~dp0tomcat
set CATALINA_OPTS=-Dfile.encoding=UTF-8

set PATH=%JAVA_HOME%\bin;%PATH%
set CLASSPATH=%CATALINA_HOME%\lib\servlet-api.jar

start "tomcat"
start "compile" /d %CATALINA_HOME%\webapps\book\WEB-INF\src
