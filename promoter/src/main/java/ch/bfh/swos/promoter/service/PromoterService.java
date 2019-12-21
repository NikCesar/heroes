package ch.bfh.swos.promoter.service;

import ch.bfh.swos.promoter.model.BattleStats;

public interface PromoterService {

    BattleStats promoteFight(String tHomeName, String tAwayName);
}
