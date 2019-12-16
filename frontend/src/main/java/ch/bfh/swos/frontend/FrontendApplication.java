package ch.bfh.swos.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/*** Zuul:
     Zuul, among many other things, fetches from Eureka service locations and does server-side load balancing.
 ***/

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class FrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }

}
