#!/bin/bash 
echo Y | apt-get install default-jre
echo Y | apt-get install postgresql
echo Y | apt-get install screen
echo Y | apt autoremove
sudo apt-get update

pg_ctlcluster 9.5 main start
sudo su - postgres -c 'psql -c "CREATE USER jtaylor WITH PASSWORD '\''trire123'\'';"'
sudo su - postgres -c 'psql -c "ALTER USER jtaylor WITH SUPERUSER;"'
sudo su - postgres -c 'createdb weg' # drop with `dropdb weg` if necessary
sudo ufw allow OpenSSH # Stands for Uncomplicated FireWall
sudo ufw allow 8080/tcp # allow access to Tomcat server
sudo ufw enable


sudo useradd jtaylor
echo jtaylor:trire123 | chpasswd
usermod -aG sudo jtaylor  # Gives root privelages to user
su - jtaylor

# sudo git clone https://github.com/jamesjmtaylor/weg-api.git //This must be a manual step
#***Execute initializeDB.sh here***