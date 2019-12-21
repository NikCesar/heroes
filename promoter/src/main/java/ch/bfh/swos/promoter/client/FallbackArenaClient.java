package ch.bfh.swos.promoter.client;

import ch.bfh.swos.promoter.model.BattleStats;
import ch.bfh.swos.promoter.model.Party;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// Fallback-Class must have @Component-Annotation
@Component
public class FallbackArenaClient implements ArenaClient {

    @Override
    public BattleStats battle(List<Party> duelingParties) throws ResponseStatusException {
        throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Battle could not be executed!");
    }
}
