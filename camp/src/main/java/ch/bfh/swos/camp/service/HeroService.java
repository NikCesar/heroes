package ch.bfh.swos.camp.service;

import ch.bfh.swos.camp.model.Hero;

public interface HeroService {

    Hero createHero(String name);

    int countHeroByAtkGreaterThan(int value);
}
