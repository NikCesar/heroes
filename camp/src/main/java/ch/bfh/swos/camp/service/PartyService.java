package ch.bfh.swos.camp.service;

import ch.bfh.swos.camp.exception.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Party;

import java.util.List;

public interface PartyService {

    List<Party> findAll();
    Party createParty(String name) throws NotEnoughHeroesAvailableException;
    Party findPartyById(Long id);
    Party createPlayerParty();
    Party updateParty(Party party);
}

