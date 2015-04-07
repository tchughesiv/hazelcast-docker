import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Application;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


@EnableEurekaClient
@EnableAutoConfiguration
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        Config config = new XmlConfigBuilder().build();
        NetworkConfig networkConfig = config.getNetworkConfig();
        JoinConfig join = networkConfig.getJoin();
        TcpIpConfig tcpIpConfig = join.getTcpIpConfig();

        DiscoveryClient discoveryClient = context.getBean(DiscoveryClient.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        String eurekaUrl = environment.getProperty("eureka.client.serviceUrl.defaultZone");
        String hazelcastEurekaApplicationName = environment.getProperty("eureka.instance.appname");
        System.out.println("Querying Eureka ( " + eurekaUrl + " ) for Hazelcast instances");
        Application application = discoveryClient.getApplication(hazelcastEurekaApplicationName);
        if (application != null) {
            List<InstanceInfo> instances = application.getInstances();
            System.out.println("Available instances = {");
            for (InstanceInfo instance : instances) {
                logInstance(instance);
                tcpIpConfig.addMember(instance.getIPAddr());
            }
            System.out.println("}");
        } else {
            System.out.println("No hazelcast instances found under application name : " + hazelcastEurekaApplicationName);
        }
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
    }

    private static void logInstance(InstanceInfo instance) {
        System.out.println("-------------------");
        System.out.println("Id= " + instance.getId());
        System.out.println("AppName = " + instance.getAppName());
        System.out.println("HostName = " + instance.getHostName());
        System.out.println("IPAddr = " + instance.getIPAddr());
        System.out.println("Port = " + instance.getPort());
        System.out.println("-------------------");
    }
}
