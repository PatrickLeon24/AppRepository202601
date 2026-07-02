@echo off
setlocal

rem === Definir ruta base de Tomcat ===
set "CATALINA_HOME=C:\ProgramasInstalados\apache-tomcat-11.0.5"
set "TOMCAT_WEBAPPS=%CATALINA_HOME%\webapps"

echo === [1] Deteniendo Tomcat ===
start "" "%CATALINA_HOME%\bin\shutdown.bat"

echo Esperando 30 segundos para asegurar que Tomcat se detenga completamente...
ping -n 31 127.0.0.1 >nul

echo === [2] Eliminando carpeta y WAR de DemoBackEnd ===

if exist "%TOMCAT_WEBAPPS%\DemoBackEnd" (
    echo Eliminando carpeta: %TOMCAT_WEBAPPS%\DemoBackEnd
    rmdir /S /Q "%TOMCAT_WEBAPPS%\DemoBackEnd"
) else (
    echo La carpeta DemoBackEnd no existe.
)

if exist "%TOMCAT_WEBAPPS%\DemoBackEnd.war" (
    echo Eliminando WAR: %TOMCAT_WEBAPPS%\DemoBackEnd.war
    del /Q "%TOMCAT_WEBAPPS%\DemoBackEnd.war"
) else (
    echo El archivo DemoBackEnd.war no existe.
)

echo === [3] Iniciando Tomcat nuevamente ===
start "" "%CATALINA_HOME%\bin\startup.bat"

echo Esperando 30 segundos para que Tomcat arranque completamente...
ping -n 31 127.0.0.1 >nul

echo === Proceso completado ===
endlocal


