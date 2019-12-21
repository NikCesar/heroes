package ch.bfh.swos.camp.service.impl;

import ch.bfh.swos.camp.exceptions.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.Party;
import ch.bfh.swos.camp.service.PartyService;
import ch.bfh.swos.camp.util.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPartyService implements PartyService {

    private DefaultHeroService heroService;

    @Autowired
    public DefaultPartyService(DefaultHeroService heroService){
        this.heroService = heroService;
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

        for (int i: fourRandomInts) {
            heroList.add(allHeroes.get(i));
        }

        p.setMembers(heroList);
        return p;
    }

}