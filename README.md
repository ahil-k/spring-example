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
ssh-keygen 
cat ~/.ssh/id_rsa.pub 
yum install git -y
git clone git@gitlab.eng.vmware.com:kahil/spring-starter.git
cd spring-starter/
  
yum install java-11-openjdk-devel wget -y
wget https://www-us.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -P /tmp
cd /tmp/
sudo tar xf apache-maven-3.6.3-bin.tar.gz -C /opt
sudo ln -s /opt/apache-maven-3.6.3 /opt/maven

vi /etc/profile.d/maven.sh

chmod +x /etc/profile.d/maven.sh
source /etc/profile.d/maven.sh

export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:/${PATH}
export JAVA_HOME=/usr/lib/jvm/jre-11-openjdk
export MYSQL_HOST=10.185.241.244

echo $JAVA_HOME

mvn --version

mvn clean install -DskipTests
```




```bash
sudo mysql --password

mysql> create database db_example; -- Creates the new database
mysql> SET GLOBAL validate_password.policy=LOW;
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database

```