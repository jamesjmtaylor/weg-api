#Worldwide Equipment Guide API
This is the API for the native worldwide equipment guide mobile applications.  It's written in Kotlin, accesses a Postgresql database with JPA, serializes it into JSON with jackson, and serves it on port 8080 with Tomcat.  The final stack was decided upon after experimentation with Ktor and Spark.  Ktor was decided against because of the immaturity of the stack and lack of documentation.  Spark was decided against primarily for the sake of expediency (Spark did everything SpringBoot is capable of, but required more code overall).   
