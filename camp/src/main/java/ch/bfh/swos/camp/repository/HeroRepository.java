package ch.bfh.swos.camp.repository;

import ch.bfh.swos.camp.model.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends CrudRepository<Hero, Long> {
    Long countByAtkIsGreaterThan(int atk);
    List<Hero> findAll();
}
