# spring-starter

# Mysql Guide
https://spring.io/guides/gs/accessing-data-mysql/

# Local test

```bash
docker run -it -d --restart=always -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -e MYSQL_USER=springuser -e MYSQL_DATABASE=user_db -e MYSQL_PASSWORD=ThePassword --name some-mysql mysql
mvn spring-boot:run
```

# Deploy

```bash
mvn spring-boot:repackage
# copy war to /usr/local/tomcat
```

```bash
sudo mysql --password

mysql> create database db_example; -- Creates the new database
mysql> SET GLOBAL validate_password.policy=LOW;
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database

```