package ch.bfh.swos.camp.service.impl;

import ch.bfh.swos.camp.exception.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.HeroType;
import ch.bfh.swos.camp.model.Party;
import ch.bfh.swos.camp.repository.PartyRepository;
import ch.bfh.swos.camp.service.PartyService;
import ch.bfh.swos.camp.util.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPartyService implements PartyService {

    private DefaultHeroService heroService;
    private PartyRepository partyRepository;

    @Autowired
    public DefaultPartyService(DefaultHeroService heroService, PartyRepository partyRepository){
        this.heroService = heroService;
        this.partyRepository = partyRepository;
    }

    @Override
    public Party createParty(String name) throws NotEnoughHeroesAvailableException {
        Party p = new Party();
        p.setName(name);
        List<Hero> allHeroes = this.heroService.findAllHeroes();

        if(allHeroes.size() < 4) {
            throw new NotEnoughHeroesAvailableException(4, allHeroes.size());
        }

        List<Hero> heroList = new ArrayList<>();
        List<Integer> fourRandomInts = Helpers.getDistinctRandomInts(0, allHeroes.size() - 1, 4);

        int pos = 0;
        for (int i: fourRandomInts) {
            Hero hero = allHeroes.get(i);
            hero.setHeroType(HeroType.convertIntToHeroType(pos));
            heroList.add(hero);
            pos++;
        }

        p.setMembers(heroList);
        return p;
    }

    @Override
    public Party findPartyById(Long id) {
        return partyRepository.findPartyById(id);
    }

    public List<Party> findAll() {
        return partyRepository.findAll();
    }

    @Override
    public Party createPlayerParty() {
        Party party = new Party();
        party.setName("Player Party");
        return partyRepository.save(party);
    }

    @Override
    public Party updateParty(Party party) {
        return partyRepository.save(party);
    }
}