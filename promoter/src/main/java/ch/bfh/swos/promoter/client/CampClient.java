package ch.bfh.swos.promoter.client;

import ch.bfh.swos.promoter.model.Party;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

// Client will be implemented at runtime

@FeignClient(value = "camp-service", fallback = FallbackCampClient.class)
public interface CampClient {

    @GetMapping(value = "/createParty")
    HttpEntity<Party> createParty(@RequestParam("name") String partyName) throws ResponseStatusException;

}
