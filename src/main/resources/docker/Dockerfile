FROM java:7
MAINTAINER Emin Demirci (emin@hazelcast.com)
ENV HZ_HOME /opt/hazelcast
ADD $HZ_HOME/hazelcast-eureka-v1.jar $HZ_HOME/hazelcast-eureka-v1.jar
ADD $HZ_HOME/application.yml $HZ_HOME/application.yml
ADD $HZ_HOME/hazelcast.xml $HZ_HOME/hazelcast.xml
ADD $HZ_HOME/initialize.sh $HZ_HOME/initialize.sh
ADD $HZ_HOME/log4j.properties $HZ_HOME/log4j.properties
WORKDIR $HZ_HOME
RUN chmod +x initialize.sh
CMD sh initialize.sh
