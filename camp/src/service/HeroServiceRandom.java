package service;

import model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.HeroRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class HeroServiceRandom implements HeroService {

    private final int _HP_AMOUNT = 100;
    private final int _RND_MAX_VALUE = 100;
    @Autowired
    private HeroRepository heroRepository;

    public HeroServiceRandom() {
    }

    @Autowired
    public HeroServiceRandom(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public void createHero(String name) {
        Random rn = new Random();
        Hero hero = new Hero();
        hero.setName(name);
        hero.setHp(_HP_AMOUNT);
        hero.setAtk(rn.nextInt(_RND_MAX_VALUE) + 1);
        hero.setDef(rn.nextInt(_RND_MAX_VALUE) + 1);
        heroRepository.save(hero);
    }

    @Override
    public Hero getHero(Long id) {
        Optional<Hero> optional = heroRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public int heroGt50Atk() {
        return heroRepository.countHeroByAtkGreaterThan(50);
    }
}
