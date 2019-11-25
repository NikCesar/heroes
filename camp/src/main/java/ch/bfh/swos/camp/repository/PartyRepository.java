package ch.bfh.swos.camp.repository;

import ch.bfh.swos.camp.model.Party;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends CrudRepository<Party, Long> {
    List<Party> findAll();
    Party findPartyById(Long id);
}
