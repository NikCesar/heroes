package ch.bfh.swos.arena.service;

import ch.bfh.swos.arena.model.Party;

public interface BattleService {
    String battle(Party challengeeParty, Party challengerParty);
}
