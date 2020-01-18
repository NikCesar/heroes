package ch.bfh.swos.camp.service;

import ch.bfh.swos.camp.exception.HeroNotFoundException;
import ch.bfh.swos.camp.exception.InvalidHeroException;
import ch.bfh.swos.camp.exception.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.HeroType;

import java.util.List;

public interface HeroService {

    List<Hero> findAllHeroes();

    Hero findHeroById(String id) throws HeroNotFoundException;

    // Returns uuid of the created hero:
    String createHero(Hero hero);
    Hero createHeroByNameAndType(String name, HeroType heroType);

    List<Hero> createRandomHeroes(int numberOfHeroesToCreate, HeroType heroType) throws NotEnoughHeroesAvailableException;

    void updateHero(Hero hero) throws InvalidHeroException, HeroNotFoundException;

    void deleteHero(String heroId) throws HeroNotFoundException;

    void deleteAllHeroes();

    int countHeroByAtkGreaterThan(int value);

}
