package ch.bfh.swos.promoter.client;

import ch.bfh.swos.promoter.model.Party;
import org.springframework.hateoas.EntityModel;

public interface CampClient {
    EntityModel<Party> createParty(String name);
}
