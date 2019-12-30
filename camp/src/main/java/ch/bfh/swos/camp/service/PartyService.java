package ch.bfh.swos.camp.service;

import ch.bfh.swos.camp.exception.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Party;

public interface PartyService {

    Party createParty(String name) throws NotEnoughHeroesAvailableException;
    Party findPartyById(Long id);
}

