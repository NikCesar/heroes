package ch.bfh.swos.arena.controller;

import ch.bfh.swos.arena.model.Party;
import ch.bfh.swos.arena.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// localhost:3333/

@RestController
// @RequestMapping("/arena")
public class ArenaController {

    @Autowired
    private BattleService battleService;

    // To test if arena-Service is working
    @GetMapping
    public String arenaServiceStatus(){
        return "Arena Service is working";
    }

    @PostMapping(value = "/battle")
    public String battle(@RequestBody List<Party> duelingParties) {

        if (duelingParties.size() != 2) {
            throw new RuntimeException("Only 2 challengers at the same time are allowed in the arena.");
        }

        Party partyHome = duelingParties.get(0);
        Party partyAway = duelingParties.get(1);
        String winner =  battleService.battle(partyHome, partyAway);
        return "The winner of the battle between '"+partyHome.getName()+"' and '"+partyAway.getName()+"' was '"+winner+"'!";
    }
}