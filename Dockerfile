FROM bitnami/tomcat:9.0

LABEL maintainer="kahil@vmware.com"

COPY target/hello-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/hello.war

