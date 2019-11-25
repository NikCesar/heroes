package ch.bfh.swos.camp.service;

import ch.bfh.swos.camp.exceptions.HeroNotFoundException;
import ch.bfh.swos.camp.exceptions.InvalidHeroException;
import ch.bfh.swos.camp.model.Hero;

import java.util.List;
import java.util.Optional;

public interface HeroService {

    List<Hero> findAllHeroes();

    Hero findHeroById(String id) throws HeroNotFoundException;

    // Returns uuid of the created hero:
    String createHero(Hero hero);
    Hero createHero(String name);

    void updateHero(Hero hero) throws InvalidHeroException, HeroNotFoundException;

    void deleteHero(String heroId) throws HeroNotFoundException;



    int countHeroByAtkGreaterThan(int value);





}
