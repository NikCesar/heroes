package ch.bfh.swos.promoter.client.impl;

import ch.bfh.swos.promoter.client.ArenaClient;
import ch.bfh.swos.promoter.model.Party;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class DefaultArenaClient implements ArenaClient {

    // after ':' follows default-value
    @Value("${arena.service.url:http://localhost:1111/arena}")
    private String arenaServiceUrl;

    @Override
    public String battle(List<Party> duelingParties) {
        ResponseEntity<String> response =  new RestTemplate().exchange(
                arenaServiceUrl + "/battle",
                HttpMethod.POST,
                new HttpEntity<>(duelingParties),
                String.class);
        return response.getBody();
    }
}