package ch.bfh.mvnFanatics.repositories;


import ch.bfh.mvnFanatics.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, String> {

    int countHeroByAtkGreaterThan(int gt);

    Optional<Hero> findByName(String name);
}
