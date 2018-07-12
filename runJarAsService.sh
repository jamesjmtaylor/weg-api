#!/bin/bash 
#cd weg-api/target/
cd /target/
sudo chown jtaylor spring-jpa-postgresql-spring-boot-0.0.1-SNAPSHOT.jar #(chown stands for `CHange OWNer`)
sudo chmod 500 spring-jpa-postgresql-spring-boot-0.0.1-SNAPSHOT.jar
java -jar spring-jpa-postgresql-spring-boot-0.0.1-SNAPSHOT.jar --logging.file=./spring-boot-app.log

#To run in background, see https://www.digitalocean.com/community/tutorials/how-to-install-and-use-screen-on-an-ubuntu-cloud-server