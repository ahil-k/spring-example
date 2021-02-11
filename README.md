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

## Tomcat

https://linuxize.com/post/how-to-install-tomcat-9-on-centos-7/

```bash
yum install java-11-openjdk-devel wget git -y
wget https://www-us.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -P /tmp
cd /tmp/
sudo tar xf apache-maven-3.6.3-bin.tar.gz -C /opt
sudo ln -s /opt/apache-maven-3.6.3 /opt/maven

cat > /etc/profile.d/maven.sh <<EOF
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:/${PATH}
export JAVA_HOME=/usr/lib/jvm/jre-11-openjdk
export MYSQL_HOST={{MYSQL_HOST}}
EOF

chmod +x /etc/profile.d/maven.sh
source /etc/profile.d/maven.sh

echo $JAVA_HOME
mvn --version


ssh-keygen 
cat ~/.ssh/id_rsa.pub
git clone git@gitlab.eng.vmware.com:kahil/spring-starter.git
cd spring-starter/

mvn clean install -DskipTests spring-boot:repackage
```

## MySQL


```bash
sudo mysql --password
sudo grep 'temporary password' /var/log/mysqld.log

SET GLOBAL validate_password.policy=LOW;
SET GLOBAL validate_password.length = 6;
SET GLOBAL validate_password.number_count = 0;
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root123';


mysql> 
create database user_db; -- Creates the new database
SET GLOBAL validate_password.policy=LOW;
create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
grant all privileges on *.* to 'springuser'@'localhost';
FLUSH PRIVILEGES;

CREATE USER 'springuser'@'%' IDENTIFIED BY 'ThePassword';
GRANT ALL PRIVILEGES ON *.* TO 'springuser'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;



ALTER USER 'springuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'ThePassword';
FLUSH PRIVILEGES;
```

```bash
worker,Admin!23
```


# Problems:

- mysql bind address has to be set to 0.0.0.0
- tomcat is not picking ip from env variables, so needs to be manually set in app.yml and rebuilt