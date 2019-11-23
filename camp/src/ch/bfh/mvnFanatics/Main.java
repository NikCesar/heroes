package ch.bfh.mvnFanatics;

import ch.bfh.mvnFanatics.model.Hero;
import ch.bfh.mvnFanatics.model.Party;
import ch.bfh.mvnFanatics.service.HeroService;
import ch.bfh.mvnFanatics.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private HeroService heroService;
    @Autowired
    private PartyService partyService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Party randomParty = partyService.createParty("Party of Parties");
        for (Hero hero : randomParty.getMembers()) {
            System.out.println(hero.toString());
        }
        System.out.println(heroService.heroGt50Atk());
    }
}
