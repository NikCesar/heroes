package ch.bfh.swos.promoter.service.impl;

import ch.bfh.swos.promoter.client.ArenaClient;
import ch.bfh.swos.promoter.client.CampClient;
import ch.bfh.swos.promoter.model.Party;
import ch.bfh.swos.promoter.service.PromoterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPromoterService implements PromoterService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultPromoterService.class);

    @Autowired
    private CampClient campClient;

    @Autowired
    private ArenaClient arenaClient;

    @Override
    public String promoteFight() {

        Party partyHome = campClient.createParty("Challengee (Home)").getBody();
        Party partyAway = campClient.createParty("Challenger (Away)").getBody();
        LOG.info("Todays battle is between Party '"+partyHome.getName()+"' and Party '"+partyAway.getName()+"'.");

        List<Party> duelingParties = new ArrayList<>();
        duelingParties.add(partyHome);
        duelingParties.add(partyAway);
        String winner = arenaClient.battle(duelingParties);
        LOG.info("And the winner is: Party '"+winner+"'");

        return  winner;
    }
}
