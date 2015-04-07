#!/bin/sh
if [ -z "$HOST_IP" ]; then
	echo "No HOST_IP environment variable set, you should pass your host ip address to the docker image with -e \"HOST_IP=x.x.x.x\""
else
    if [ -z "$EUREKA_URL" ]; then
        echo "No EUREKA_URL environment variable set, you should pass your Eureka URL to the docker image with -e \"EUREKA_URL=http://x.x.x.x:8080/eureka/v2/\""
    else
        if [ -z "$LICENSE_KEY" ]; then
            echo "No LICENSE_KEY environment variable set, you should pass your Hazelcast License key to the docker image with -e \"LICENSE_KEY=xxxxxxx\""
        fi
        echo "HOST_IP is -> $HOST_IP"
        echo "EUREKA_URL is -> $EUREKA_URL"
        echo "LICENSE_KEY is -> $LICENSE_KEY"
        sed -i  s/##PUBLIC_ADDRESS##/$HOST_IP/g /opt/hazelcast/hazelcast.xml
        sed -i  s/##LICENSE_KEY##/$LICENSE_KEY/g /opt/hazelcast/hazelcast.xml
        echo "Starting hazelcast-eureka application"
        java -Dlog4j.configuration=file:///opt/hazelcast/log4j.properties -jar /opt/hazelcast/hazelcast-eureka-v1.jar
    fi
fi
