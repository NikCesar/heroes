package repositories;

import model.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroRepository extends CrudRepository<Hero, String> {

    int countHeroByAtkGreaterThan(int gt);

    Optional<Hero> findByName(String name);
}
