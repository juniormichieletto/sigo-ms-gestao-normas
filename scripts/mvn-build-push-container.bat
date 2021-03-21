@echo off
cd ..
mvn clean package -Dquarkus.container-image.build.push=true
pause