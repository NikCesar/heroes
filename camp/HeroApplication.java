import config.ServiceConfig;
import model.Party;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.HeroService;
import service.PartyService;

public class HeroApplication {

    public static void main(String[] args) {

        // Basic configuration for log4j:
        BasicConfigurator.configure();

        /*
        AnnotationConfigApplicationContext javaContext = new AnnotationConfigApplicationContext();
        javaContext.scan("service.impl");
        javaContext.refresh();
        */

       AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(ServiceConfig.class);

        PartyService partyService = configurationContext.getBean(PartyService.class);
        HeroService heroService = configurationContext.getBean(HeroService.class);

        Party p = partyService.createParty("Hero Party");
        int herosWithAtkGreaterThan = heroService.countHeroByAtkGreaterThan(50);
        System.err.println(p);
        System.err.println("Number of Heroes with ATK > 50: " + herosWithAtkGreaterThan);

        configurationContext.close();
    }
}
