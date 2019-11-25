package ch.bfh.swos.camp.service;

import ch.bfh.swos.camp.exceptions.HeroNotFoundException;
import ch.bfh.swos.camp.exceptions.InvalidHeroException;
import ch.bfh.swos.camp.exceptions.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Hero;

import java.util.List;

public interface HeroService {

    List<Hero> findAllHeroes();

    Hero findHeroById(String id) throws HeroNotFoundException;

    // Returns uuid of the created hero:
    String createHero(Hero hero);
    Hero createHeroByName(String name);

    List<Hero> createRandomHeroes(int numberOfHeroesToCreate) throws NotEnoughHeroesAvailableException;

    void updateHero(Hero hero) throws InvalidHeroException, HeroNotFoundException;

    void deleteHero(String heroId) throws HeroNotFoundException;

    void deleteAllHeroes();

    int countHeroByAtkGreaterThan(int value);





}
