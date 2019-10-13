package service.impl;

import model.Hero;
import service.HeroService;
import util.Helpers;

public class DefaultHeroService implements HeroService {

    @Override
    public Hero createHero(String name) {

        Hero h = new Hero(name);
        h.setAtk(Helpers.getRandomInt(1,100));
        h.setDef(Helpers.getRandomInt(1,100));
        h.setHp(100);

        System.out.println(String.format("atk=%s;def=%s;hp=%s;", h.getAtk(), h.getDef(), h.getHp()));
        return h;
    }
}
