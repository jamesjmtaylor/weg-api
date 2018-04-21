# Worldwide Equipment Guide API

This is the API for the native worldwide equipment guide mobile applications.
It's written in Kotlin, accesses a Postgresql database with JPA, serializes it into JSON with jackson, and serves it 
on port 8080 with Tomcat.  

## Running the application

Follow the steps below in the 'Future Docker Setup' section to initialize the postgres db. After that you can clone the project
and, assuming you're using the IntelliJ IDEA, go to the menu bar and select **Run** > **Run...** The api will be available
at **http://localhost:8080/**

## Future Docker setup

These steps are enumerated to facilitate dockerization.  The postgresql db, though encapsulated in the github project 
for the time being, will eventually need to be placed on it's own persistent docker container.

1. `bash installPostgres.sh` **Installs the postgres dependancies**
2. `pg_ctl -D ./db -l logfile start` **Starts the postgres server**
3. `createdb weg` **Creates the database**
4. `psql weg` **opens the database in the termainal**
5. `\password` **Allows you to set the password (type it in twice)**
6. `bash initializeDb.sh` **Creates and populates all the tables**

NOTE: When you're operating in the psql command prompt, in order to 
execute multi-line queries, i.e. 
<br><br>`SELECT *`
<br>`FROM guns`
<br>`WHERE id = 5`<br><br>
You can press enter after each line (or not).  The only thing that psql really cares about is that you terminate the query
with a semicolon (`;`) VARCHARacter, i.e. `SELECT * FROM guns WHERE id = 5;`
 
## Spike Findings
The final stack was decided upon after experimentation with Ktor, Spark, and SpringBoot.  Ktor was decided against because of the
immaturity of the stack and lack of documentation.  Spark was decided against primarily for the sake of expediency
(Spark did everything SpringBoot is capable of, but required more code overall).

Ultimately Spark appears to be the ideal choice for longterm development because less functionality is 'automagically'
generated and it's more lightweight overall. SpringBoot relies pretty heavily on annotations.  Classes with 
the `@Repository` annotation are automatically converted to tables if they don't exist already in the database.  
Similiarly rows are represented by classes with the `@Entity` annotation, with each class attribute forming a column in 
the table that contains it.  
  
Spark on the other hand uses Data Access Objects with SQL queries (i.e. "SELECT * FROM table) thinly wrapped with 
Kotlin methods.  This forces the developer to do a lot of hand-crafting of SQL statements upfront, but means that you can
minimize the size of your external libraries overall.
  
## UNIX/POSTGRESQL Lessons Learned
  * `su - userName` (su stands for `Switch User`)
  * `adduser jtaylor` (Creates user)
  * `usermod -aG sudo jtaylor` (Gives root privelages to user)
  * If you get 'root doesn't exist' error, `sudo -u postgres createuser owning_user -s`
  * `pg_ctl` is to initialize, start, stop, or control a PostgreSQL server
  * If you need to know which psql servers are running use `pg_lsclusters`
  * You can interact with the psql terminal directly by using `psql dbNameHere userNameHere`
  * To login with a password use `psql -U userNameHere -h 127.0.0.1 dbNameHere`
  * `\du` (Lists all users and their roles
  * `\dt` (Lists all tables)
  * `\q` (quit)
  
## Spark Libraries
  
  * kotlin-stdlib-jre8
  * spark-core
  * postgresql
  * slf4j <i>(logger)</i>
  * gson
  * commons-dbutil
  * javax.persistence:persistence-api
  
## Load Testing

The server was load tested with the script below.  The script simulates 500 simultaneous connections to the server. Amazingly,
 all 500 of the requests were resolved in 4 seconds, generating a plaintext output file 253mb in size. Any more than 500
 simeltaneous attempts causes a server error of _**org.postgresql.util.PSQLException: A connection could not be made using the requested protocol null.**_
 Whether that was a failure caused by the server or the environment hosting the script is as of yet undetermined.  Regardless,
 the server continued to respond successfully to subsequent GET requests even after the exception.

```#!/bin/bash
   i=1
   SECONDS=0
   while [ $i -le 500 ]
    do
       ((i++))
       (response=$(curl -i --verbose \
       --request GET \
       --url 'http://localhost:8080/getAllCombined' \
       )
   	loop=$'\n Request Loop: '$i
       resp=$'\n Response: '$response
       echo "$loop$resp" >> output.txt) &
    done
    echo $'\n Total seconds: '$SECONDS >> output.txt
    ```   
  
  
  