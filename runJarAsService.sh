#!/bin/bash 
#TODO: Figure out correct order of these instructions.
cd weg-api/target/
sudo chown jtaylor spring-jpa-postgresql-spring-boot-0.0.1-SNAPSHOT.jar #(chown stands for `CHange OWNer`)
sudo chmod 500 spring-jpa-postgresql-spring-boot-0.0.1-SNAPSHOT.jar
java -jar java -jar spring-jpa-postgresql-spring-boot-0.0.1-SNAPSHOT.jar 
