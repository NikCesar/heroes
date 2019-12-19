package ch.bfh.swos.promoter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
// Enables this microservice to register at Eureka-server (== registry-module)
// Config-data for registering is in Application.properties
@EnableEurekaClient
@EnableFeignClients(basePackages = {"ch.bfh.swos.promoter.service.impl"})
@EnableHystrix
@EnableHystrixDashboard
public class PromoterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromoterApplication.class, args);
    }

}
