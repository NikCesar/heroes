package ch.bfh.swos.camp.service.impl;

import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.Party;
import ch.bfh.swos.camp.service.PartyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPartyService implements PartyService {

    DefaultHeroService heroService;

    public DefaultPartyService(DefaultHeroService heroService){
        this.heroService = heroService;
    }

    @Override
    public Party createParty(String name) {
        Party p = new Party();
        p.setName(name);
        List<Hero> heroList = new ArrayList();
        heroList.add(heroService.createHero("Nikola"));
        heroList.add(heroService.createHero("Nicola"));
        heroList.add(heroService.createHero("Matthias"));
        heroList.add(heroService.createHero("Jürg"));
        p.setMembers(heroList);
        return p;
    }
}