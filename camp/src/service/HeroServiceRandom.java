package service;

import model.Hero;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HeroServiceRandom implements HeroService {

    private final int _HP_AMOUNT = 100;
    private final int _RND_MAX_VALUE = 100;

    @Override
    public Hero createHero(String name) {
        Random rn = new Random();
        Hero hero = new Hero();
        hero.setName(name);
        hero.setHp(_HP_AMOUNT);
        hero.setAtk(rn.nextInt(_RND_MAX_VALUE) + 1);
        hero.setDef(rn.nextInt(_RND_MAX_VALUE) + 1);
        return hero;
    }
}
