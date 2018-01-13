# Worldwide Equipment Guide API

This is the API for the native worldwide equipment guide mobile applications.
It's written in Kotlin, accesses a Postgresql database with JPA, serializes it into JSON with jackson, and serves it 
on port 8080 with Tomcat. 
 
## Spike Findings
The final stack was decided upon after experimentation with Ktor and Spark.  Ktor was decided against because of the
 immaturity of the stack and lack of documentation.  Spark was decided against primarily for the sake of expediency
  (Spark did everything SpringBoot is capable of, but required more code overall).

Ultimately Spark appears to be the ideal choice for longterm development because less functionality is 'automagically'
 generated.  One such example is JSON serlialization.  In SpringBoot classes with the `@Repository` annotation are
  automatically converted to tables if they don't exist already in the database.  Similiarly rows are represented by
  classes with the `@Entity` annotation, with each class attribute forming a column in the table that contains it.  
  
  Spark on the other hand uses Data Access Objects with SQL queries (i.e. "SELECT * FROM table) thinly wrapped with 
  Kotlin methods.  This forces the developer to do a lot of hand-crafting of SQL statements upfront, but ultimately 
  provides more flexibility in the long run.
  
  ## Spark Libraries
  
  * kotlin-stdlib-jre8
  * spark-core
  * postgresql
  * slf4j `(logger)`
  * gson
  * commons-dbutil
  * javax.persistence:persistence-api