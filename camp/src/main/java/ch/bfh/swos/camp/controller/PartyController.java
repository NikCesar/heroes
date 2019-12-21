package ch.bfh.swos.camp.controller;

import ch.bfh.swos.camp.exceptions.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.Party;
import ch.bfh.swos.camp.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

// localhost:2222/

@RestController
// @RequestMapping("/parties")
public class PartyController {

    @Autowired
    PartyService partyService;

    // Endpoint to check if Rest Controller works:
    @GetMapping
    public @ResponseBody Party getRandomParty() throws NotEnoughHeroesAvailableException {
        return partyService.createParty("Test Party");
    }

    @GetMapping(value = "/createParty")
    public Party createParty(@RequestParam(value = "name") String partyName) throws ResponseStatusException {
        try {
            Party p = partyService.createParty(partyName);
            p.add(linkTo(methodOn(PartyController.class).createParty(partyName)).withSelfRel());
            for (Hero h : p.getMembers()) {
                p.add(linkTo(methodOn(HeroController.class).findHeroById(h.getId())).withRel(h.getName()));
            }
            return p;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}