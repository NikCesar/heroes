package ch.bfh.swos.camp.service.impl;

import ch.bfh.swos.camp.exceptions.HeroNotFoundException;
import ch.bfh.swos.camp.exceptions.InvalidHeroException;
import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.repositories.HeroRepository;
import ch.bfh.swos.camp.service.HeroService;
import ch.bfh.swos.camp.util.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultHeroService implements HeroService {

    private static int MIN_ATK = 1;
    private static int MAX_ATK = 100;
    private static int MIN_DEF = 1;
    private static int MAX_DEF = 100;
    private static int HP = 100;

    @Autowired
    private HeroRepository heroRepository;

    public DefaultHeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }

    @Override
    public List<Hero> findAllHeroes() {
        return this.heroRepository.findAll();
    }

    @Override
    public Hero findHeroById(String id) throws HeroNotFoundException {
        Hero hero = this.heroRepository.findById(id).get();

        // Check if hero-id exists in database:
        if(hero == null){
            throw new HeroNotFoundException("A hero with the specified id doesn't exist!");
        }
        return hero;
    }

    @Override
    public String createHero(Hero hero){
        // Insert into database and return Id of newly created hero:
        return this.heroRepository.insert(hero).getId();
    }

    @Override
    public Hero createHero(String name) {

        Hero h = new Hero(name);
        h.setAtk(Helpers.getRandomInt(MIN_ATK,MAX_ATK));
        h.setDef(Helpers.getRandomInt(MIN_DEF,MAX_DEF));
        h.setHp(HP);

        h = heroRepository.insert(h);
        Hero heroFromDb = heroRepository.findById(h.getId()).get();
        System.err.println(heroFromDb);

        return heroFromDb;
    }

    @Override
    public void updateHero(Hero hero) throws InvalidHeroException, HeroNotFoundException {

        if(hero == null || Helpers.isNullOrEmpty(hero.getId(), hero.getName())){
            throw new InvalidHeroException("Update failed! Hero-id or hero-name are missing!");
        }

        Hero h = this.heroRepository.findById(hero.getId()).get();

        if(h == null){
            throw new HeroNotFoundException("Update failed! Specified hero-id could not be found!");
        }

        h.setName(hero.getName());
        h.setAtk(hero.getAtk());
        h.setDef(hero.getDef());
        h.setHp(hero.getHp());

        heroRepository.save(h);
    }

    @Override
    public void deleteHero(String heroId) throws HeroNotFoundException {
        // Check if hero-id is null or empty:
        if (heroId == null || heroId.isEmpty()){
            throw new HeroNotFoundException("Id must not be null or empty");
        }
        // Check if hero-Id can be found in database:
        if(this.heroRepository.findById(heroId).get() == null){
            throw new HeroNotFoundException("Deletion failed. No hero with the specified id was found!");
        }
        // Remove hero from database:
        this.heroRepository.deleteHeroById(heroId);
    }


    @Override
    public int countHeroByAtkGreaterThan(int value) {
        return heroRepository.countHeroByAtkGreaterThan(value);
    }

}