# Hazelcast Eureka Sample Application

In this repository you can find an example Spring Boot application that uses Eureka service discovery for Hazelcast.

The Docker image created from this repository can be found under `hazelcast/hazelcast-eureka` at Docker Registry.

## Configuring and Running the Application

To run the container you need to provide environment variables below.

- `HOST_IP` -- Ip address of the machine that docker containers are running
- `EUREKA_URL` -- URL of the Eureka instance
- `LICENSE_KEY` --  Hazelcast enterprise license key

You need to map the hazelcast port (`5701`) to the docker host.

Here is an example container configuration.
```
docker run -it -e "HOST_IP=192.168.2.104"  -e "EUREKA_URL=http://192.168.2.104:8080/eureka/v2/" -e "LICENSE_KEY=XXXXXXXXXXXXXXXX" -p 5701:5701 hazelcast/hazelcast-eureka
```

## Logging Configuration
By default this application uses `log4j` for logging. You can find `log4j.properties` file at `/opt/hazelcast` folder. You can edit that file to configure log4j.



## Building Application
If you'd like to customize the container, you can build a new docker image containing the application like below.

```
mvn clean package docker:build
```

Then you should be able to find a docker image on your local docker service tagged with `hazelcast-eureka`.
