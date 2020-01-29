package ch.bfh.swos.promoter.client;

import ch.bfh.swos.promoter.model.Party;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

// Client will be implemented at runtime

@FeignClient(value = "camp-service", fallback = FallbackCampClient.class)
public interface CampClient {

    @GetMapping(value = "parties/{id}")
    EntityModel<Party> findPartyById(@PathVariable Long id);

    @GetMapping(value = "parties/createParty")
    EntityModel<Party> createParty(@RequestParam(value = "name") String partyName) throws ResponseStatusException;
}
