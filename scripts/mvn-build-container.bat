@echo off
cd ..
mvn clean package clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.group=juniormichieletto -Dquarkus.container-image.tag=latest
pause
