package ch.bfh.swos.camp.service;

import ch.bfh.swos.camp.exceptions.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Party;

public interface PartyService {

    Party createParty(String name) throws NotEnoughHeroesAvailableException;
}

