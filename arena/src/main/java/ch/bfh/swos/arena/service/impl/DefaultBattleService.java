package ch.bfh.swos.arena.service.impl;

import ch.bfh.swos.arena.model.BattleStats;
import ch.bfh.swos.arena.model.Hero;
import ch.bfh.swos.arena.model.HeroFightStats;
import ch.bfh.swos.arena.model.Party;
import ch.bfh.swos.arena.service.BattleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultBattleService implements BattleService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultBattleService.class);
    private static final DecimalFormat f = new DecimalFormat("##.00");

    @Override
    public BattleStats battle(Party partyHome, Party partyAway) {

        resetFightStats(partyHome);
        resetFightStats(partyAway);

        List<Hero> heroesHome = new ArrayList<>(partyHome.getMembers());
        List<Hero> heroesAway = new ArrayList<>(partyAway.getMembers());

        LOG.info("Party '"+partyHome.getName()+"' fights against party '"+partyAway.getName()+"'.");

        int duelCount;
        int roundCount = 0;

        while (true) {

            List<Hero> losers = new ArrayList<>();
            roundCount++;

            LOG.info("============================================================================================================");
            LOG.info("Starting round no. "+roundCount);

            // there can be only as much duels as the count of remaining members in the smallest party
            if (heroesHome.size() > heroesAway.size()) {
                duelCount = heroesAway.size();
            } else {
                duelCount = heroesHome.size();
            }

            // execute the duels
            for (int i = 0; i < duelCount; i++) {
                Hero fighterHome = heroesHome.get(i);
                Hero fighterAway = heroesAway.get(i);
                Hero loser = duel(fighterHome, fighterAway);
                losers.add(loser);
            }

            // remove the losers from both parties
            heroesHome.removeAll(losers);
            heroesAway.removeAll(losers);

            LOG.info("Party '"+partyHome.getName()+"' has "+heroesHome.size()+" members left.");
            LOG.info("Party '"+partyAway.getName()+"' has "+heroesAway.size()+" members left.");

            // check if a party has already lost (no members left), return the winners party name
            if (heroesHome.isEmpty()) {
                LOG.info("Party '"+partyAway.getName()+"' wins this battle in "+roundCount+" rounds.");
                return new BattleStats(partyAway, partyHome);
            }

            if (heroesAway.isEmpty()) {
                LOG.info("Party '"+partyHome.getName()+"' wins this battle in "+roundCount+" rounds.");
                return new BattleStats(partyHome, partyAway);
            }
        }
    }

    private Hero duel(Hero fighterHome, Hero fighterAway) {

        // fighterHome will attack first => attacker and defender will be swapped before first duel
        Hero attacker = fighterAway;
        Hero defender = fighterHome;

        LOG.info("------------------------------------------------------------------------------------------------------------");
        LOG.info(attacker.getHeroType().toString()+" "+attacker.getName()+" at position:"+attacker.getPosition()+"(ATK:"+attacker.getAtk()+", DEF:"+attacker.getDef()+", DodgeChance:"+attacker.getDodgeChance()+", INI:"+attacker.getInitiative()+", CritChance:"+attacker.getCritChance()+") with "+f.format(attacker.getHp())+" hp left fights against "+defender.getHeroType().toString()+" "+defender.getName()+" at position:"+defender.getPosition()+"(ATK:"+defender.getAtk()+", DEF:"+defender.getDef()+", DodgeChance:"+defender.getDodgeChance()+", INI:"+defender.getInitiative()+", CritChance:"+defender.getCritChance()+") with "+f.format(defender.getHp())+" hp left.");

        // battle until hp runs out
        while(defender.getHp() >= 0) {

            // swap attacker and defender
            Hero temp = attacker;
            attacker = defender;
            defender = temp;

            // duel round
            round(attacker, defender);
        }
        LOG.info(defender.getName()+" has lost the duel against "+attacker.getName()+".");

        // Update fight-stats of involved heroes (+1 win resp. +1 defeat):
        addFightResultToStats(attacker, defender);

        // return loser
        return defender;
    }

    private void round(Hero attacker, Hero defender) {
        double defenderHp = defender.getHp();

        // damage is 1/10 of ATK
        double damage = attacker.getAtk();
        if (damage < 1) damage++;
        damage = damage/10.0;
        LOG.info("Attacking "+attacker.getName()+" caused "+damage+" damage.");

        // defense blocks DEF percent of ATK
        double defense = defender.getDef();
        if (defense > 1) defense--;
        LOG.info("Defending "+defender.getName()+" blocked "+defense+" percent of damage.");

        // harm = damage - defense
        double harm = damage - (damage * (defense/100));
        LOG.info("Defending "+defender.getName()+" lost "+f.format(harm)+" of health.");

        // harm is drawn from defenders hp
        defenderHp -= harm;
        LOG.info("Defending "+defender.getName()+" has "+f.format(Math.max(0,defenderHp))+" health points left.");

        // Update fight-stats of involved heroes:
        addHarmPointsToStats(attacker, defender, harm);

        defender.setHp(defenderHp);
    }

    private void resetFightStats(Party party){
        for(int i=0; i < party.getMembers().size(); i++){
            party.getMembers().get(i).setFightStats(new HeroFightStats());
        }
    }

    private void addHarmPointsToStats(Hero attacker, Hero defender, double harmInCurrentAttack){
        attacker.getFightStats().addHarmCaused(harmInCurrentAttack);
        defender.getFightStats().addHarmTaken(harmInCurrentAttack);
    }

    private void addFightResultToStats(Hero winner, Hero loser){
        winner.getFightStats().addWin();
        loser.getFightStats().addDefeat();
    }

}
