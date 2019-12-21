package ch.bfh.swos.arena.service;

import ch.bfh.swos.arena.model.BattleStats;
import ch.bfh.swos.arena.model.Party;

public interface BattleService {
    BattleStats battle(Party challengeeParty, Party challengerParty);
}
