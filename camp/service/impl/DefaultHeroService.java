package service.impl;

import model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.HeroRepository;
import service.HeroService;
import util.Helpers;

@Service
public class DefaultHeroService implements HeroService {

    private static int MIN_ATK = 1;
    private static int MAX_ATK = 100;
    private static int MIN_DEF = 1;
    private static int MAX_DEF = 100;
    private static int HP = 100;

    @Autowired
    private HeroRepository heroRepository;

    public DefaultHeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }

    @Override
    public Hero createHero(String name) {

        Hero h = new Hero(name);
        h.setAtk(Helpers.getRandomInt(MIN_ATK,MAX_ATK));
        h.setDef(Helpers.getRandomInt(MIN_DEF,MAX_DEF));
        h.setHp(HP);

        h = heroRepository.insert(h);
        Hero heroFromDb = heroRepository.findById(h.getId()).get();
        System.err.println(heroFromDb);

        return heroFromDb;
    }

    @Override
    public int countHeroByAtkGreaterThan(int value) {
        return heroRepository.countHeroByAtkGreaterThan(value);
    }
}
