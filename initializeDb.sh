#!/bin/bash  
psql -U jtaylor -d weg -c "DROP TABLE gun;"
psql -U jtaylor -d weg -c "DROP TABLE land;" 
psql -U jtaylor -d weg -c "DROP TABLE air;" 
psql -U jtaylor -d weg -c "DROP TABLE sea;"  
echo "DB purge complete\n" 

psql -U jtaylor -d weg -c "CREATE TABLE gun(id INT PRIMARY KEY NOT NULL, name VARCHAR(255), description TEXT, group_icon_url VARCHAR(255), individual_icon_url VARCHAR(255), photo_url VARCHAR(255), range INT, penetration INT, altitude INT);"
psql -U jtaylor -d weg -c "CREATE TABLE land(id INT PRIMARY KEY NOT NULL, name VARCHAR(255), description TEXT, group_icon_url VARCHAR(255), individual_icon_url VARCHAR(255), photo_url VARCHAR(255), primary_weapon VARCHAR(255), secondary_weapon VARCHAR(255), atgm VARCHAR(255), armor INT, speed INT, auto INT, weight INT);" 
psql -U jtaylor -d weg -c "CREATE TABLE air(id INT PRIMARY KEY NOT NULL, name VARCHAR(255), description TEXT, group_icon_url VARCHAR(255), individual_icon_url VARCHAR(255), photo_url VARCHAR(255), gun VARCHAR(255), agm VARCHAR(255), aam VARCHAR(255), asm VARCHAR(255), speed INT, auto INT, ceiling INT, weight INT);"
psql -U jtaylor -d weg -c "CREATE TABLE sea(id INT PRIMARY KEY NOT NULL, name VARCHAR(255), description TEXT, individual_icon_url VARCHAR(255), photo_url VARCHAR(255), gun VARCHAR(255), sam VARCHAR(255), asm VARCHAR(255), torpedo VARCHAR(255), transports VARCHAR(255), qty INT, dive INT, speed INT, auto INT, tonnage INT);"
echo "DB tables created\n" 

SCRIPT_DIR=`pwd`
COMMAND=$'COPY gun (id, name, description, group_icon_url, individual_icon_url, photo_url, range, penetration, altitude) FROM \''$SCRIPT_DIR$'/db/guns.csv\' CSV HEADER;' 
psql -U jtaylor -d weg -c  "$COMMAND" 
COMMAND=$'COPY air (id, name, description, group_icon_url, individual_icon_url, photo_url, gun, agm, aam, asm, speed, auto, ceiling, weight)  FROM \''$SCRIPT_DIR$'/db/air.csv\' CSV HEADER;'
psql -U jtaylor -d weg -c  "$COMMAND" 
COMMAND=$'COPY sea (id, name, description, individual_icon_url, photo_url, gun, sam, asm, torpedo, transports, qty, dive, speed, auto, tonnage)  FROM \''$SCRIPT_DIR$'/db/sea.csv\' CSV HEADER;' 
psql -U jtaylor -d weg -c  "$COMMAND"
COMMAND=$'COPY land (id, name, description, group_icon_url, individual_icon_url, photo_url, primary_weapon, secondary_weapon, atgm, armor, speed, auto, weight)  FROM \''$SCRIPT_DIR$'/db/land.csv\' CSV HEADER;'
psql -U jtaylor -d weg -c  "$COMMAND" 

echo "DB populated\n" 
