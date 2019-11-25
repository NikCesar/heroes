package ch.bfh.swos.camp;

import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.Party;
import ch.bfh.swos.camp.service.HeroService;
import ch.bfh.swos.camp.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CampApplicationRunner implements ApplicationRunner {

    @Autowired
    private HeroService heroService;

    @Autowired
    private PartyService partyService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Hero hero = new Hero();
        hero.setAtk(10);
        hero.setDef(20);
        hero.setHp(100);
        hero.setName("Test");
        heroService.createHero(hero);
        System.out.println(heroService.getAll());

        Hero hero2 = new Hero();
        hero2.setAtk(20);
        hero2.setDef(30);
        hero2.setHp(50);
        hero2.setName("Bob");
        heroService.createHero(hero2);
        System.out.println(heroService.getAll());

        Party party = new Party();
        party.setName("Party1");
        party.setHeroes(Arrays.asList(hero, hero2));
        partyService.createParty(party);
        System.out.println(partyService.getAll());
    }
}