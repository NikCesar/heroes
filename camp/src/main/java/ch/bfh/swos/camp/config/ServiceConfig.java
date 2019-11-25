package ch.bfh.swos.camp.config;

import ch.bfh.swos.camp.repositories.HeroRepository;
import ch.bfh.swos.camp.service.impl.DefaultHeroService;
import ch.bfh.swos.camp.service.impl.DefaultPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Autowired
    private HeroRepository heroRepository;

    @Bean
    public DefaultHeroService heroService(){
        return new DefaultHeroService(heroRepository);
    }

    @Bean
    public DefaultPartyService partyService() {
        return new DefaultPartyService(heroService());
    }

}
