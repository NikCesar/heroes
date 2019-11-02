package service;

import model.Hero;
import model.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.HeroRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        for (Long i = 1L; i <= this._PARTY_SIZE; i++) {
            Hero hero = heroService.getHero(i);
            if (hero != null) {
                partyHeroes.add(hero);
            }
        }

        party.setMembers(partyHeroes);
        return party;
    }
}
