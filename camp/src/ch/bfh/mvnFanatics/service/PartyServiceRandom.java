package ch.bfh.mvnFanatics.service;

import ch.bfh.mvnFanatics.model.Hero;
import ch.bfh.mvnFanatics.model.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PartyServiceRandom implements PartyService {

    private final int _PARTY_SIZE = 4;
    private HeroService heroService;

    @Autowired
    public PartyServiceRandom(HeroService heroService) {
        this.heroService = heroService;
    }

    @Override
    public Party createParty(String name) {
        Party party = new Party();
        List<Hero> partyHeroes = new LinkedList<>();
        for (int i = 0; i < this._PARTY_SIZE; i++) {
            heroService.createHero("Hercules " + i);
        }
        for (int i = 0; i <= this._PARTY_SIZE; i++) {
            Hero hero = heroService.getHeroByName("Hercules " + i);
            if (hero != null) {
                partyHeroes.add(hero);
            }
        }

        party.setMembers(partyHeroes);
        return party;
    }
}
