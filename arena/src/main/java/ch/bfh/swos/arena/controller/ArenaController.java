package ch.bfh.swos.arena.controller;

import ch.bfh.swos.arena.model.BattleStats;
import ch.bfh.swos.arena.model.Party;
import ch.bfh.swos.arena.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public BattleStats battle(@RequestBody List<Party> duelingParties) throws ResponseStatusException {

        if (duelingParties.size() != 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Make sure there are exactly 2 parties in the arena.");
        }

        Party partyHome = duelingParties.get(0);
        Party partyAway = duelingParties.get(1);
        return battleService.battle(partyHome, partyAway);
    }
}