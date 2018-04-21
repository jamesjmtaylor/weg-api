#!/bin/bash 
git clone https://github.com/jamesjmtaylor/weg-api.git
apt-get install default-jre
apt-get install postgresql
apt autoremove
pg_ctlcluster 9.5 main start
adduser jtaylor
usermod -aG sudo jtaylor  #Gives root privelages to user
sudo su - postgres 
createuser --interactive -P jtaylor
createdb weg # drop with `dropdb weg` if necessary

sudo useradd jtaylor
sudo passwd trire123
sudo chown jtaylor:trire123 appName.jar #(chown stands for `CHange OWNer`)
sudo chmod 500 appName.jar
sudo ln -s /path/to/appName.jar /etc/init.d/appName (ln stands for `LiNk`)
sudo service appName start

psql weg jtaylor 

#***FOLLOW DIRECTIONS AS NORMAL FROM HERE***