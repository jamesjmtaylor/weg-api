#!/bin/bash  
psql -U jtaylor -d weg -c "DROP TABLE land; DROP TABLE sea; DROP TABLE air; DROP TABLE gun;"
echo "DB purge complete"  
psql -U jtaylor -d weg -c "CREATE TABLE gun(ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255), DESCRIPTION TEXT, GROUP_ICON_URL VARCHAR(255), INDIVIDUAL_ICON_URL VARCHAR(255), PHOTO_URL VARCHAR(255), RANGE INT, PENETRATION INT, ALTITUDE INT);
CREATE TABLE land(ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255), DESCRIPTION TEXT, GROUP_ICON_URL VARCHAR(255), INDIVIDUAL_ICON_URL VARCHAR(255), PHOTO_URL VARCHAR(255), PRIMARY_WEAPON VARCHAR(255), SECONDARY_WEAPON VARCHAR(255), ATGM VARCHAR(255), ARMOR INT, SPEED INT, AUTO INT, WEIGHT INT); 
CREATE TABLE air(ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255), DESCRIPTION TEXT, GROUP_ICON_URL VARCHAR(255), INDIVIDUAL_ICON_URL VARCHAR(255), PHOTO_URL VARCHAR(255), GUN VARCHAR(255), AGM VARCHAR(255), AAM VARCHAR(255), ASM VARCHAR(255), SPEED INT, AUTO INT, CEILING INT, WEIGHT INT);
CREATE TABLE sea(ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255), DESCRIPTION TEXT, INDIVIDUAL_ICON_URL VARCHAR(255), PHOTO_URL VARCHAR(255), GUN VARCHAR(255), SAM VARCHAR(255), ASM VARCHAR(255), TORPEDO VARCHAR(255), TRANSPORTS VARCHAR(255), QTY INT, DIVE INT, SPEED INT, AUTO INT, TONNAGE INT); "
echo "DB tables created" 
SCRIPT_DIR=`pwd`
COMMAND=$'COPY gun FROM \''$SCRIPT_DIR$'/db/guns.csv\' CSV HEADER; 
COPY land FROM \''$SCRIPT_DIR$'/db/land.csv\' CSV HEADER; 
COPY air FROM \''$SCRIPT_DIR$'/db/air.csv\' CSV HEADER;
COPY sea FROM \''$SCRIPT_DIR$'/db/sea.csv\' CSV HEADER;' 
psql -U jtaylor -d weg -c  "$COMMAND"
echo "DB populated" 