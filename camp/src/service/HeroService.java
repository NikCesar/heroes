package service;

import model.Hero;

public interface HeroService {
    void createHero(String name);

    Hero getHero(String id);

    Hero getHeroByName(String name);

    int heroGt50Atk();
}
