package ch.bfh.swos.camp.repositories;

import ch.bfh.swos.camp.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    Party findPartyById(Long id);
}
