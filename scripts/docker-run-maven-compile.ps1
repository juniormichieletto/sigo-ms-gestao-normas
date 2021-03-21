docker run -it --rm -v ${PWD}/../:/usr/src/ms -v ${HOME}/.m2:/root/.m2 -w /usr/src/ms maven:3-openjdk-16-slim mvn clean compile
Read-Host -Prompt "Press any key to continue"