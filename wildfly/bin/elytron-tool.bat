@echo off
rem -------------------------------------------------------------------------
rem WildFly Elytron Tool Script for Windows
rem -------------------------------------------------------------------------
rem
rem A tool for management securing sensitive strings

@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT" setlocal

if "%OS%" == "Windows_NT" (
  set "DIRNAME=%~dp0%"
) else (
  set DIRNAME=.\
)

pushd "%DIRNAME%.."
set "RESOLVED_JBOSS_HOME=%CD%"
popd

if "x%JBOSS_HOME%" == "x" (
  set "JBOSS_HOME=%RESOLVED_JBOSS_HOME%"
)

pushd "%JBOSS_HOME%"
set "SANITIZED_JBOSS_HOME=%CD%"
popd

if /i "%RESOLVED_JBOSS_HOME%" NEQ "%SANITIZED_JBOSS_HOME%" (
   echo.
   echo   WARNING:  JBOSS_HOME may be pointing to a different installation - unpredictable results may occur.
   echo.
   echo       JBOSS_HOME: "%JBOSS_HOME%"
   echo.
)

rem Setup JBoss specific properties
if "x%JAVA_HOME%" == "x" (
  set  JAVA=java
  echo JAVA_HOME is not set. Unexpected results may occur.
  echo Set JAVA_HOME to the directory of your local JDK to avoid this message.
) else (
  set "JAVA=%JAVA_HOME%\bin\java"
)

rem Find wildfly-elytron-tool.jar, or we can't continue
set "JBOSS_RUNJAR=%JBOSS_HOME%\bin\wildfly-elytron-tool.jar"
if not exist "%JBOSS_RUNJAR%" (
  echo Could not locate "%JBOSS_RUNJAR%".
  echo Please check that you are in the bin directory when running this script.
  goto END
)


"%JAVA%" %JAVA_OPTS% ^
    -jar "%JBOSS_RUNJAR%" ^
     %*

:END