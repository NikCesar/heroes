import model.Party;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.HeroService;
import service.PartyService;

public class Main {

    public static void main(String[] args) {
        ApplicationContext javaContext = new AnnotationConfigApplicationContext(CampServiceConfiguration.class);

        PartyService partyService = javaContext.getBean(PartyService.class);
        HeroService heroService = javaContext.getBean(HeroService.class);
        Party party = partyService.createParty("test1");

        party.getMembers().forEach(h -> System.out.println(h.toString()));

        System.out.println(heroService.heroGt50Atk());

    }
}
