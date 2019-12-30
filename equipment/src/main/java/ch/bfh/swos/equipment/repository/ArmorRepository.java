package ch.bfh.swos.equipment.repository;

import ch.bfh.swos.equipment.model.Armor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArmorRepository extends JpaRepository<Armor, Long> {

    List<Armor> findAll();

    Optional<Armor> findById(Long id);

    void deleteAll();

    void deleteById(Long id);
}
