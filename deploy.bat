@echo off
setlocal
set PROJECT_DIR=%~dp0group_project
set TOMCAT_DIR=%~dp0tomcat
set WAR_NAME=student-management-v2.war

echo ==========================================
echo   Deploy Script: Student Management System
echo ==========================================

echo [1/5] Stopping Tomcat...
call "%TOMCAT_DIR%\bin\shutdown.bat" >nul 2>&1
timeout /t 3 /nobreak > nul

echo [2/5] Building Project (mvnw clean package)...
cd /d "%PROJECT_DIR%"
call mvnw.cmd clean package
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Build failed.
    pause
    exit /b %ERRORLEVEL%
)

echo [3/5] Cleaning old deployment...
del /q "%TOMCAT_DIR%\webapps\%WAR_NAME%" >nul 2>&1
rmdir /s /q "%TOMCAT_DIR%\webapps\student-management-v2" >nul 2>&1

echo [4/5] Copying new WAR file...
copy /y "%PROJECT_DIR%\target\%WAR_NAME%" "%TOMCAT_DIR%\webapps\"

echo [5/5] Starting Tomcat...
cd /d "%TOMCAT_DIR%\bin"
start startup.bat

echo.
echo ==========================================
echo   Success! Deployment complete.
echo   URL: http://localhost:8080/student-management-v2/login.action
echo ==========================================
pause
