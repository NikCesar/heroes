package service.impl;

import camp.model.Party;
import model.Hero;
import service.PartyService;

import java.util.ArrayList;
import java.util.List;

public class DefaultPartyService implements PartyService {

    DefaultHeroService heroService;

    public DefaultPartyService(DefaultHeroService heroService){
        this.heroService = heroService;
    }

    @Override
    public Party createParty(String name) {
        Party p = new Party();
        List<Hero> herosList = new ArrayList();
        herosList.add(heroService.createHero("Nikola"));
        herosList.add(heroService.createHero("Nicola"));
        herosList.add(heroService.createHero("Matthias"));
        herosList.add(heroService.createHero("Jürg"));
        p.setMembers(herosList);

        System.out.println(p);
        return p;
    }


}
