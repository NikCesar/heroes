package ch.bfh.swos.camp.service.impl;

import ch.bfh.swos.camp.exception.HeroNotFoundException;
import ch.bfh.swos.camp.exception.InvalidHeroException;
import ch.bfh.swos.camp.exception.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.AttributeBoundaries;
import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.HeroType;
import ch.bfh.swos.camp.repository.HeroRepository;
import ch.bfh.swos.camp.service.HeroService;
import ch.bfh.swos.camp.util.Helpers;
import ch.bfh.swos.camp.util.NameList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultHeroService implements HeroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHeroService.class);

    private HeroRepository heroRepository;

    @Autowired
    public DefaultHeroService(HeroRepository heroRepository) {
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
        if (hero == null) {
            throw new HeroNotFoundException("A hero with the specified id doesn't exist!");
        }
        return hero;
    }

    @Override
    public List<Hero> createRandomHeroes(int numberOfHeroesToCreate, HeroType heroType) throws NotEnoughHeroesAvailableException {
        String[] allNames = NameList.getNamesList();

        if(numberOfHeroesToCreate > allNames.length){
            throw new NotEnoughHeroesAvailableException(numberOfHeroesToCreate, allNames.length);
        }

        List<Hero> createdHeroes = new ArrayList<>();
        List<Integer> distinctRandomInts = Helpers.getDistinctRandomInts(0, (allNames.length - 1), numberOfHeroesToCreate);

        for(int i: distinctRandomInts){
            createdHeroes.add(createHeroByNameAndType(allNames[i], heroType));
        }
        return createdHeroes;
    }

    @Override
    public String createHero(Hero hero) {
        // Insert into database and return Id of newly created hero:
        return this.heroRepository.saveAndFlush(hero).getId();
    }

    @Override
    public Hero createHeroByNameAndType(String name, HeroType heroType) {

        Hero h = new Hero(name);
        h.setHeroType(heroType);

        AttributeBoundaries b = HeroType.getAttrBoundariesByHeroType(heroType);

        h.setAtk(Helpers.getRandomInt(b.getAtkMin(), b.getAtkMax()));
        h.setDef(Helpers.getRandomInt(b.getDefMin(), b.getDefMax()));
        h.setHp(Helpers.getRandomDouble(b.getHpMin(), b.getHpMax()));

        h.setCritChance(Helpers.getRandomDouble(b.getCritChanceMin(), b.getCritChanceMax()));
        h.setDodgeChance(Helpers.getRandomDouble(b.getDodgeChanceMin(), b.getDodgeChanceMax()));
        h.setInitiative(Helpers.getRandomInt(b.getInitiativeMin(), b.getInitiativeMax()));

        h = heroRepository.save(h);
        Hero heroFromDb = heroRepository.findById(h.getId()).get();

        LOGGER.info(heroFromDb.toString());

        return heroFromDb;
    }


    @Override
    public void updateHero(Hero hero) throws InvalidHeroException, HeroNotFoundException {

        if (hero == null || Helpers.isNullOrEmpty(hero.getId(), hero.getName())) {
            throw new InvalidHeroException("Update failed! Hero-id or hero-name are missing!");
        }

        if (hero.getId() == null) {
            throw new HeroNotFoundException("Update failed! Specified hero-id could not be found!");
        }

        heroRepository.save(hero);
    }

    @Override
    public void deleteHero(String heroId) throws HeroNotFoundException {
        // Check if hero-id is null or empty:
        if (heroId == null || heroId.isEmpty()) {
            throw new HeroNotFoundException("Id must not be null or empty");
        }
        // Check if hero-Id can be found in database:
        if (this.heroRepository.findById(heroId).get() == null) {
            throw new HeroNotFoundException("Deletion failed. No hero with the specified id was found!");
        }
        // Remove hero from database:
        this.heroRepository.deleteHeroById(heroId);
    }

    @Override
    public void deleteAllHeroes() {
        this.heroRepository.deleteAll();
    }


    @Override
    public int countHeroByAtkGreaterThan(int value) {
        return heroRepository.countHeroByAtkGreaterThan(value);
    }

}