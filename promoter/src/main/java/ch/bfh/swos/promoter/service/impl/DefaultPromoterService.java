package ch.bfh.swos.promoter.service.impl;

import ch.bfh.swos.promoter.client.ArenaClient;
import ch.bfh.swos.promoter.client.CampClient;
import ch.bfh.swos.promoter.model.BattleStats;
import ch.bfh.swos.promoter.model.Hero;
import ch.bfh.swos.promoter.model.Party;
import ch.bfh.swos.promoter.service.PromoterService;
import ch.bfh.swos.promoter.util.Helpers;
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
    public BattleStats promoteFight(String tHomeName, String tAwayName) {

        Party partyHome = campClient.createParty(tHomeName).getContent();
        Party partyAway = campClient.createParty(tAwayName).getContent();

        addDistinctNameExtensions(partyHome, partyAway);

        LOG.info("Todays battle is between Party '"+partyHome.getName()+"' and Party '"+partyAway.getName()+"'.");

        List<Party> duelingParties = new ArrayList<>();
        duelingParties.add(partyHome);
        duelingParties.add(partyAway);
        BattleStats battleStats = arenaClient.battle(duelingParties);
        LOG.info("And the winner is: Party '"+battleStats.getWinnerParty().getName()+"'");

        return  battleStats;
    }


    /** Makes sure all participating heroes have a surname that starts with a different letter (in case their first name is identical) **/
    private void addDistinctNameExtensions(Party partyHome, Party partyAway){
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int totalNumberOfHeroes = partyHome.getMembers().size() + partyAway.getMembers().size();

        // Make sure it's possible to find a different letter for every hero:
        if(totalNumberOfHeroes > alphabet.length){
            return;
        }

        List<Integer> randomInts = Helpers.getDistinctRandomInts(0, alphabet.length-1, totalNumberOfHeroes);
        int count = 0;
        for(Hero h: partyHome.getMembers()){
            h.setName(h.getName()+ " " +alphabet[randomInts.get(count)] +".");
            count++;
        }
        for(Hero h: partyAway.getMembers()){
            h.setName(h.getName()+ " " +alphabet[randomInts.get(count)] +".");
            count++;
        }
    }


}
