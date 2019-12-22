package ch.bfh.swos.arena.service.impl;

import ch.bfh.swos.arena.model.Hero;
import ch.bfh.swos.arena.model.HeroType;
import ch.bfh.swos.arena.model.Party;
import ch.bfh.swos.arena.service.BattleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DefaultBattleService implements BattleService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultBattleService.class);
    private static final DecimalFormat f = new DecimalFormat("##.00");

    @Override
    public String battle(Party partyHome, Party partyAway) {

        List<Hero> heroesHome = new ArrayList<>(partyHome.getMembers());
        List<Hero> heroesAway = new ArrayList<>(partyAway.getMembers());

        LOG.info("Party '" + partyHome.getName() + "' fights with: ");
        heroesHome.forEach(x -> LOG.info(x.getName()));
        LOG.info("AGAINST!");
        LOG.info("Party '" + partyAway.getName() + "' fights with ");
        heroesAway.forEach(x -> LOG.info(x.getName()));

        int duelCount;
        int roundCount = 0;

        while (true) {
            roundCount++;

            LOG.info("===============================================================================================");
            LOG.info("Starting round no. " + roundCount);

            // Get a sorted list by hero initiative
            List<Hero> attackOrder = Stream.concat(heroesHome.stream(), heroesAway.stream())
                    .sorted((x, y) -> y.getInitiative() - x.getInitiative())
                    .collect(Collectors.toList());

            for (int i = 0; i < attackOrder.size(); i++) {
                Hero attacker = attackOrder.get(0);
                // Break if all heroes of a team are defeated
                if (heroesHome.size() == 0 || heroesAway.size() == 0) break;
                // Let the attacker engage the enemy team
                if (partyHome.containsHero(attacker)) attack(attacker, heroesAway);
                else attack(attacker, heroesHome);
                // Remove attacker from list
                attackOrder.remove(0);
                // Remove dead heroes from attackers list and from the teams
                attackOrder = attackOrder.stream().filter(x -> x.getHp() > 0).collect(Collectors.toList());
                heroesHome = heroesHome.stream().filter(x -> x.getHp() > 0).collect(Collectors.toList());
                heroesAway = heroesAway.stream().filter(x -> x.getHp() > 0).collect(Collectors.toList());
            }

            LOG.info("Party '" + partyHome.getName() + "' has " + heroesHome.size() + " members left.");
            LOG.info("Party '" + partyAway.getName() + "' has " + heroesAway.size() + " members left.");

            // check if a party has already lost (no members left), return the winners party name
            if (heroesHome.isEmpty()) {
                LOG.info("Party '" + partyAway.getName() + "' wins this battle in " + roundCount + " rounds.");
                return partyAway.getName();
            }

            if (heroesAway.isEmpty()) {
                LOG.info("Party '" + partyHome.getName() + "' wins this battle in " + roundCount + " rounds.");
                return partyHome.getName();
            }
        }
    }

    private void attack(Hero attacker, List<Hero> heroes) {

        // If attacker is rogue, attack enemy hero at last position, else attack enemy hero at first position
        Hero defender = attacker.getHeroType() == HeroType.ROGUE ? heroes.get(heroes.size() - 1) : heroes.get(0);

        LOG.info("---------------------------------------------------------------------------------------------------");

        // check if defender dodges
        boolean dodged = Math.random() < defender.getDodgeChance();
        LOG.info("[COMBAT]\t"+attacker.getName() + " attacks " + defender.getName());
        if (!dodged) {
            LOG.info("[DODGE]\t" + defender.getName() + " dodged " + attacker.getName() + "'s attack!");
        } else {
            boolean criticalHit = Math.random() < attacker.getCritChance();
            if (criticalHit) {
                double damage = attacker.getAtk() - defender.getDef();
                if (damage > 0) {
                    defender.setHp(defender.getHp() - damage);
                    LOG.info("[ATTACK]\t" + attacker.getName() + " deals " + damage + " damage to " + defender.getName() + "!");
                }
                else LOG.info("[BLOCK]\t" + defender.getName() + " blocked" + attacker.getName() + "'s attack");
            } else {
                double damage = (attacker.getAtk() * 2) - defender.getDef();
                if (damage > 0) {
                    defender.setHp(defender.getHp() - damage);
                    LOG.info("[CRITICAL ATTACK]\t" + attacker.getName() + " deals " + damage + " damage to " + defender.getName() + "!");
                }
                else LOG.info("[BLOCK]\t" + defender.getName() + " blocked" + attacker.getName() + "'s attack");
            }
            if (defender.getHp() <= 0) LOG.info(defender.getName() + " was defeated!");
        }
        LOG.info(attacker.getHeroType().toString() + " " + attacker.getName() + " ATK:" + attacker.getAtk() + " HP " + attacker.getHp());
        LOG.info(defender.getHeroType().toString() + " " + defender.getName() + " ATK:" + defender.getAtk() + " HP " + defender.getHp());
    }
}