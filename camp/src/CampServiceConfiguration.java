import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.HeroService;
import service.HeroServiceRandom;
import service.PartyService;
import service.PartyServiceRandom;

@Configuration
public class CampServiceConfiguration {

    @Bean
    public HeroService heroService() {
        return new HeroServiceRandom();
    }

    @Bean
    public PartyService partyService() {
        return new PartyServiceRandom(heroService());
    }

}
