package ch.bfh.swos.camp.service;

import ch.bfh.swos.camp.model.Party;
import ch.bfh.swos.camp.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService {

    @Autowired
    PartyRepository repository;

    public Party createParty(Party party) {
        return repository.save(party);
    }

    public List<Party> getAll() {
        return repository.findAll();
    }

    public Party getParty(Long id) {
        return repository.findPartyById(id);
    }
}
