package repositories;

import model.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends CrudRepository<Hero, Long> {

    int countHeroByAtkGreaterThan(int gt);
}
