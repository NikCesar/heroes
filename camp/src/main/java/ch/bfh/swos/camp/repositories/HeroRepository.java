package ch.bfh.swos.camp.repositories;

import ch.bfh.swos.camp.model.Hero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends MongoRepository<Hero, String> {

    public Optional<Hero> findById(String id);

    public List<Hero> findByName(String name);

    public Hero insert(Hero hero);

    public int countHeroByAtkGreaterThan(int value);

}
