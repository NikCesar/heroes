package ch.bfh.swos.promoter.client;

import ch.bfh.swos.promoter.model.Party;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

// Fallback-Class must have @Component-Annotation
@Component
public class FallbackCampClient implements CampClient {

    @Override
    public EntityModel<Party> createParty(String partyName) throws ResponseStatusException {
        throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "The camp is currently not available");
    }

    @Override
    public EntityModel<Party> findPartyById(@PathVariable Long id) {
        throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "The camp is currently not available");
    }
}
