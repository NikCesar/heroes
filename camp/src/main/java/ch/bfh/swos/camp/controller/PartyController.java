package ch.bfh.swos.camp.controller;

import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.Party;
import ch.bfh.swos.camp.representation.PartyRepresentation;
import ch.bfh.swos.camp.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/party", produces = "application/hal+json")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping
    public List<PartyRepresentation> getAll() {
        return partyService.getAll().stream()
                .map(party -> new PartyRepresentation(party.getId(), party.getName()))
                .peek(rep -> {
                    rep.add(linkTo(methodOn(PartyController.class).getParty(rep.getId())).withSelfRel());
                    rep.add(linkTo(methodOn(PartyController.class).getHeroByParty(rep.getId())).withRel("heroes"));
                })
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public PartyRepresentation getParty(@PathVariable Long id) {
        Party party = partyService.getParty(id);
        PartyRepresentation representation = new PartyRepresentation(party.getId(), party.getName());
        representation.add(linkTo(methodOn(PartyController.class).getParty(party.getId())).withSelfRel());
        representation.add(linkTo(methodOn(PartyController.class).getHeroByParty(party.getId())).withRel("heroes"));
        return representation;
    }

    @GetMapping(value = "/{id}/heroes")
    public List<Hero> getHeroByParty(@PathVariable Long id) {
        return partyService.getParty(id).getHeroes();
    }
}
