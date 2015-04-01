FROM centos:7
# Install java.. TODO: make it configuraable
RUN yum -y install java-1.7.0-openjdk-devel && yum clean all
ENV HZ_VERSION 3.4.2
ENV HZ_HOME /opt/hazelcast/
RUN mkdir -p $HZ_HOME
WORKDIR $HZ_HOME
# Download hazelcast jars from maven repo.
ADD https://repository-hazelcast-l337.forge.cloudbees.com/release/com/hazelcast/hazelcast-enterprise/$HZ_VERSION/hazelcast-enterprise-$HZ_VERSION.jar $HZ_HOME 
# Start hazelcast standalone server.
CMD java -server -cp $HZ_HOME/hazelcast-enterprise-$HZ_VERSION.jar -Dhazelcast.enterprise.license.key=$HZ_LICENSE_KEY com.hazelcast.core.server.StartServer

