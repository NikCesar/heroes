package ch.bfh.swos.promoter.client;

import ch.bfh.swos.promoter.model.Party;

import java.util.List;

public interface ArenaClient {
    String battle(List<Party> duelingParties);
}
