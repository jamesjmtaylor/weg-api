#!/bin/bash  
#Comment below was to create a docker container that could run indepedently from docker-compose (didn't work, psql: FATAL:  role "root" does not exist)
#psql -U root -d weg -c "CREATE USER root; CREATE DATABASE weg; GRANT ALL PRIVILEGES ON DATABASE weg TO root; ALTER USER root WITH PASSWORD 'trire123';"#
psql -U root -d weg -c "DROP TABLE gun;"
psql -U root -d weg -c "DROP TABLE land;" 
psql -U root -d weg -c "DROP TABLE air;" 
psql -U root -d weg -c "DROP TABLE sea;"  
echo "DB purge complete\n" 

psql -U root -d weg -c "CREATE TABLE gun(id INT PRIMARY KEY NOT NULL, name VARCHAR(255), description TEXT, group_icon_url VARCHAR(255), individual_icon_url VARCHAR(255), photo_url VARCHAR(255), range INT, penetration INT, altitude INT);"
psql -U root -d weg -c "CREATE TABLE land(id INT PRIMARY KEY NOT NULL, name VARCHAR(255), description TEXT, group_icon_url VARCHAR(255), individual_icon_url VARCHAR(255), photo_url VARCHAR(255), primary_weapon VARCHAR(255), secondary_weapon VARCHAR(255), atgm VARCHAR(255), armor INT, speed INT, auto INT, weight INT);" 
psql -U root -d weg -c "CREATE TABLE air(id INT PRIMARY KEY NOT NULL, name VARCHAR(255), description TEXT, group_icon_url VARCHAR(255), individual_icon_url VARCHAR(255), photo_url VARCHAR(255), gun VARCHAR(255), agm VARCHAR(255), aam VARCHAR(255), asm VARCHAR(255), speed INT, auto INT, ceiling INT, weight INT);"
psql -U root -d weg -c "CREATE TABLE sea(id INT PRIMARY KEY NOT NULL, name VARCHAR(255), description TEXT, individual_icon_url VARCHAR(255), photo_url VARCHAR(255), gun VARCHAR(255), sam VARCHAR(255), asm VARCHAR(255), torpedo VARCHAR(255), transports VARCHAR(255), qty INT, dive INT, speed INT, auto INT, tonnage INT);"
echo "DB tables created\n" 

psql -U root -d weg -c "COPY gun (id, name, description, group_icon_url, individual_icon_url, photo_url, range, penetration, altitude) FROM '/sql/docker-entrypoint-initdb.d/guns.csv' CSV HEADER;"
psql -U root -d weg -c "COPY sea (id, name, description, individual_icon_url, photo_url, gun, sam, asm, torpedo, transports, qty, dive, speed, auto, tonnage)  FROM '/sql/docker-entrypoint-initdb.d//sea.csv' CSV HEADER;"
psql -U root -d weg -c "COPY air (id, name, description, group_icon_url, individual_icon_url, photo_url, gun, agm, aam, asm, speed, auto, ceiling, weight) FROM '/sql/docker-entrypoint-initdb.d//air.csv' CSV HEADER;"
psql -U root -d weg -c "COPY land (id, name, description, group_icon_url, individual_icon_url, photo_url, primary_weapon, secondary_weapon, atgm, armor, speed, auto, weight)  FROM '/sql/docker-entrypoint-initdb.d//land.csv' CSV HEADER;"

echo "DB populated\n" 
