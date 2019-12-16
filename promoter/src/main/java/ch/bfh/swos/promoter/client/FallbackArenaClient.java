package ch.bfh.swos.promoter.client;

import ch.bfh.swos.promoter.model.Party;
import org.springframework.stereotype.Component;

import java.util.List;

// Fallback-Class must have @Component-Annotation
@Component
public class FallbackArenaClient implements ArenaClient {

    @Override
    public String battle(List<Party> duelingParties) {
        return "The arena is currently closed. Please come back later.";
    }
}
