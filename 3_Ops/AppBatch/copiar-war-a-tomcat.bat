@echo off
setlocal

echo === [1] Copiando DemoBackEnd.war a Tomcat ===

set "WAR_SOURCE=C:\Jenkins\AppPipeline\1_Dev\AppBackEnd\target\DemoBackEnd.war"
set "TOMCAT_WEBAPPS=C:\ProgramasInstalados\apache-tomcat-11.0.5\webapps"

if exist "%WAR_SOURCE%" (
    echo Copiando WAR desde: %WAR_SOURCE%
    echo Hacia: %TOMCAT_WEBAPPS%
    copy /Y "%WAR_SOURCE%" "%TOMCAT_WEBAPPS%"
    echo WAR copiado correctamente.
) else (
    echo El archivo WAR no existe en la ruta: %WAR_SOURCE%
    exit /b 1
)

echo Esperando 30 segundos para que Tomcat despliegue el WAR...
ping -n 31 127.0.0.1 >nul

echo === Proceso completado ===

endlocal
