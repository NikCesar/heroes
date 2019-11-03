package service;

import model.Hero;

public interface HeroService {

    Hero createHero(String name);

    int countHeroByAtkGreaterThan(int value);
}
