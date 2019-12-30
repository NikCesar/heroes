package ch.bfh.swos.equipment.repository;

import ch.bfh.swos.equipment.model.Mount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MountRepository extends JpaRepository<Mount, Long> {

    List<Mount> findAll();

    Optional<Mount> findById(Long id);

    void deleteAll();

    void deleteById(Long id);
}
