@echo off
cd ..
mvn clean package clean package -Dquarkus.container-image.build=true
pause
