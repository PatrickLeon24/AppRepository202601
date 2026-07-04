@echo off
setlocal

rem === Variables ===
set "CATALINA_HOME=C:\ProgramasInstalados\apache-tomcat-11.0.22"
set "TOMCAT_WEBAPPS=%CATALINA_HOME%\webapps"
set "WAR_SOURCE=C:\Jenkins\AppPipeline\1_Dev\AppBackEnd\target\DemoBackEnd.war"
set "WAR_DEST=%TOMCAT_WEBAPPS%\DemoBackEnd.war"

echo === [1] Deteniendo Tomcat ===
start "" "%CATALINA_HOME%\bin\shutdown.bat"
echo Esperando 30 segundos para asegurar que Tomcat se detenga completamente...
ping -n 31 127.0.0.1 >nul

echo === [2] Eliminando carpeta y WAR previos ===
if exist "%TOMCAT_WEBAPPS%\DemoBackEnd" (
    echo Eliminando carpeta desplegada...
    rmdir /S /Q "%TOMCAT_WEBAPPS%\DemoBackEnd"
)
if exist "%WAR_DEST%" (
    echo Eliminando WAR anterior...
    del /Q "%WAR_DEST%"
)

echo === [3] Copiando nuevo WAR ===
if exist "%WAR_SOURCE%" (
    echo Copiando WAR desde: %WAR_SOURCE%
    copy /Y "%WAR_SOURCE%" "%WAR_DEST%"
    echo WAR copiado correctamente.
) else (
    echo No se encontró el archivo WAR en %WAR_SOURCE%
    exit /b 1
)

echo === [4] Iniciando Tomcat ===
start "" "%CATALINA_HOME%\bin\startup.bat"
echo Esperando 30 segundos para que Tomcat arranque...
ping -n 31 127.0.0.1 >nul

echo === Despliegue completado ===
endlocal
exit /b 0
