package ch.bfh.swos.equipment.client;

import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.model.Hero;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "camp-service", fallback = FallbackCampClient.class)
public interface CampClient {

    @GetMapping("/{id}")
    EntityModel<Hero> findHeroById(@PathVariable String id) throws HeroNotFoundException;

    @PutMapping
    void updateHero(@RequestBody Hero hero) throws InvalidHeroException, HeroNotFoundException;
}
