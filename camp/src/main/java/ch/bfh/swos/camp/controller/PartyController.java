package ch.bfh.swos.camp.controller;

import ch.bfh.swos.camp.exception.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.Party;
import ch.bfh.swos.camp.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

// localhost:2222/

@RestController
@RequestMapping("/parties")
public class PartyController {

    @Autowired
    PartyService partyService;

    @GetMapping
    public List<Party> findAll() {
        return partyService.findAll();
    }

    @GetMapping("/{id}")
    public Party findPartyById(@PathVariable Long id) {
        return partyService.findPartyById(id);
    }

    // Endpoint to check if Rest Controller works:
    @GetMapping("/random")
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

    @PutMapping("/{id}")
    public Party updateParty(@PathVariable Long id, @RequestBody Party party) {
        Party existingParty = partyService.findPartyById(id);
        existingParty.setName(party.getName());
        existingParty.setMembers(party.getMembers());
        return partyService.updateParty(existingParty);
    }

    @PatchMapping(value = "/{id}/swapHero")
    public Party swapHeroes(@PathVariable Long id, @RequestParam(name = "hero1") int hero1, @RequestParam(name = "hero2") int hero2) {
        Party party = partyService.findPartyById(id);
        Collections.swap(party.getMembers(), hero1, hero2);
        return party;
    }
}
