import config.ServiceConfig;
import model.Party;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.HeroService;
import service.PartyService;

public class HeroApplication {

    public static void main(String[] args) {

        /*
        AnnotationConfigApplicationContext javaContext = new AnnotationConfigApplicationContext();
        javaContext.scan("service.impl");
        javaContext.refresh();
        */

       AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(ServiceConfig.class);

        PartyService partyService = configurationContext.getBean(PartyService.class);
        HeroService heroService = configurationContext.getBean(HeroService.class);

        Party p = partyService.createParty("Hero Party");

        System.out.println(p);

    }
}
