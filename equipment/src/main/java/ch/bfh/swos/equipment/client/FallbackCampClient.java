package ch.bfh.swos.equipment.client;

import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.model.Hero;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FallbackCampClient implements CampClient {

    @Override
    public EntityModel<Hero> findHeroById(String id) throws HeroNotFoundException {
        throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Camp not available: FallbackCampClient in Equipment module");
    }

    @Override
    public void updateHero(Hero hero) throws InvalidHeroException, HeroNotFoundException {
        throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Camp not available: FallbackCampClient in Equipment module");
    }
}
