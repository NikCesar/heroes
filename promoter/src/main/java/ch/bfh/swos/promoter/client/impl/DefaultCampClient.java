package ch.bfh.swos.promoter.client.impl;


import ch.bfh.swos.promoter.client.CampClient;
import ch.bfh.swos.promoter.model.Party;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DefaultCampClient implements CampClient {

    // after ':' follows default-value
    @Value("${camp.party.service.url:http://localhost:9999/camp/parties}")
    private String campPartyServiceUrl;

    @Override
    public EntityModel<Party> createParty(String name) {
        ResponseEntity<EntityModel<Party>> response=  new RestTemplate().exchange(
                campPartyServiceUrl + "/createParty?name={name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<EntityModel<Party>>() {}
                , name);
        return response.getBody();
    }
}