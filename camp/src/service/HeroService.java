package service;

import model.Hero;

public interface HeroService {
    void createHero(String name);

    Hero getHero(Long id);

    int heroGt50Atk();
}
