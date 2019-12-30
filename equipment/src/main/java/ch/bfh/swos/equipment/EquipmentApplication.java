package ch.bfh.swos.equipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// Enables this microservice to register at Eureka-server (== registry-module)
// Config-data for registering is in Application.properties
@EnableEurekaClient
public class EquipmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentApplication.class, args);
    }
}
