package ch.bfh.swos.camp.service;

import ch.bfh.swos.camp.exceptions.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Party;
import org.springframework.stereotype.Repository;

public interface PartyService {

    Party createParty(String name) throws NotEnoughHeroesAvailableException;
    Party findPartyById(Long id);
}

