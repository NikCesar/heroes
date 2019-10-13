package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.impl.DefaultHeroService;
import service.impl.DefaultPartyService;

@Configuration
public class ServiceConfig {

    @Bean
        public DefaultHeroService heroService(){
        return new DefaultHeroService();
    }

    @Bean
    public DefaultPartyService partyService() {
        return new DefaultPartyService(heroService());
    }

}
