package ch.bfh.mvnFanatics.service;

import ch.bfh.mvnFanatics.model.Hero;

public interface HeroService {
    void createHero(String name);

    Hero getHero(String id);

    Hero getHeroByName(String name);

    int heroGt50Atk();
}
