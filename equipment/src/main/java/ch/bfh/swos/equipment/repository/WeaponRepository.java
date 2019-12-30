package ch.bfh.swos.equipment.repository;

import ch.bfh.swos.equipment.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    List<Weapon> findAll();

    Optional<Weapon> findById(Long id);

    void deleteAll();

    void deleteById(Long id);
}
