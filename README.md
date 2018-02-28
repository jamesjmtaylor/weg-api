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

1. `pg_ctl -D ./db -l logfile start` **Starts the postgres server**
2. `createdb weg` **Creates the database**
3. `psql weg` **opens the database in the termainal**
4. `\password` **Allows you to set the password (type it in twice)**
5. `CREATE TABLE gun(ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255), DESCRIPTION TEXT, GROUP_ICON_URL VARCHAR(255), INDIVIDUAL_ICON_URL VARCHAR(255),  
           PHOTO_URL VARCHAR(255), RANGE INT, PENETRATION INT, ALTITUDE INT);` **Creates the table `gun`**
6. `COPY gun FROM './guns.csv' CSV HEADER;` **copies the values in the _guns.csv_ into the psql _guns_ table.
NOTE that it must be an absolute path to the CSV.**
7. `\dt` **Shows all the tables in the database**
8. `\d gun` **Shows all the columns for the guns table**
9. `SELECT name FROM gun` **Shows all the names of the rows you just inserted**
10. `CREATE TABLE land(ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255), DESCRIPTION TEXT, GROUP_ICON_URL VARCHAR(255), INDIVIDUAL_ICON_URL VARCHAR(255),  
       PHOTO_URL VARCHAR(255), PRIMARY_WEAPON VARCHAR(255), SECONDARY_WEAPON VARCHAR(255), ATGM VARCHAR(255), 
       ARMOR INT, SPEED INT, AUTO INT, WEIGHT INT);` **Creates the table `land`**
11. `COPY land FROM './land.csv' CSV HEADER;` **copies the values in the _land.csv_ into the psql _land_ table. 
NOTE that it must be an absolute path to the CSV.**
12. `CREATE TABLE air(ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255), DESCRIPTION TEXT, GROUP_ICON_URL VARCHAR(255), INDIVIDUAL_ICON_URL VARCHAR(255),  
       PHOTO_URL VARCHAR(255), GUN VARCHAR(255), AGM VARCHAR(255), AAM VARCHAR(255), ASM VARCHAR(255),
       SPEED INT, AUTO INT, CEILING INT, WEIGHT INT);` **Creates the table `air`**
13. `COPY air FROM './air.csv' CSV HEADER;` **copies the values in the _air.csv_ into the psql _air_ table.
NOTE that it must be an absolute path to the CSV.**
14. `CREATE TABLE sea(ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255), DESCRIPTION TEXT, INDIVIDUAL_ICON_URL VARCHAR(255),  
       PHOTO_URL VARCHAR(255), GUN VARCHAR(255), SAM VARCHAR(255), ASM VARCHAR(255), TORPEDO VARCHAR(255), TRANSPORTS VARCHAR(255), QTY INT,
       DIVE INT, SPEED INT, AUTO INT, TONNAGE INT);` **Creates the table `sea`**
15. `COPY sea FROM './sea.csv' CSV HEADER;` **copies the values in the _sea.csv_ into the psql _sea_ table.
NOTE that it must be an absolute path to the CSV.**


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
  
## POSTGRESQL Lessons Learned

  * `pg_ctl` is to initialize, start, stop, or control a PostgreSQL 
  server
  * Actual postgresql command are preceded with `psql`, i.e. `psql
  
## Spark Libraries
  
  * kotlin-stdlib-jre8
  * spark-core
  * postgresql
  * slf4j <i>(logger)</i>
  * gson
  * commons-dbutil
  * javax.persistence:persistence-api
  
  