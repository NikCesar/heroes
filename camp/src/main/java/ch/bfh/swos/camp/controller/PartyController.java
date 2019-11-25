package ch.bfh.swos.camp.controller;

import ch.bfh.swos.camp.exceptions.NotEnoughHeroesAvailableException;
import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.Party;
import ch.bfh.swos.camp.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parties")
public class PartyController {

    @Autowired
    PartyService partyService;

    @PostMapping
    public @ResponseBody
    Party createParty(@PathVariable String partyName) throws NotEnoughHeroesAvailableException {

        return this.partyService.createParty(partyName);
    }

}
