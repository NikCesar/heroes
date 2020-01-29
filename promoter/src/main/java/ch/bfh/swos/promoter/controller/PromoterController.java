package ch.bfh.swos.promoter.controller;

import ch.bfh.swos.promoter.model.BattleStats;
import ch.bfh.swos.promoter.model.Hero;
import ch.bfh.swos.promoter.model.Party;
import ch.bfh.swos.promoter.service.PromoterService;
import ch.bfh.swos.promoter.util.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080/promoter/

@RestController
public class PromoterController {

    private PromoterService promoterService;

    @Autowired
    public PromoterController(PromoterService promoterService){
        this.promoterService = promoterService;
    }

    // To test if promoter-service is working
    @GetMapping
    public String promoterServiceStatus(){
        return "Promoter-Service is working";
    }


    /** e.g. http://localhost:8080/promoter/promoteFight?tHome=TeamTrump&tAway=TeamBiden **/

    @GetMapping(value = "/promoteFight")
    public String promoteFight(@RequestParam("tHome") String tHomeName, @RequestParam("tAway") String tHomeAway) {

        BattleStats battleStats = promoterService.promoteFight(tHomeName, tHomeAway);

        StringBuilder sb = new StringBuilder();
        sb.append("<br>");
        sb.append("The Promoter is proud to proclaim the following result of today's battle: ");
        sb.append(battleStats.getWinnerParty().getName());
        sb.append(" against ");
        sb.append(battleStats.getDefeatedParty().getName());
        sb.append(" ");
        sb.append(battleStats.getNumberOfWinsOfWinner());
        sb.append(" : ");
        sb.append(battleStats.getNumberOfWinsOfLoser());
        sb.append("<br>");
        sb.append(getStatsForParty(battleStats.getWinnerParty()));
        sb.append("<br>");
        sb.append(getStatsForParty(battleStats.getDefeatedParty()));
        return sb.toString();
    }

    @GetMapping(value = "/promotePlayerFight")
    public String promoteFight() {

        BattleStats battleStats = promoterService.promotePlayerFight();

        StringBuilder sb = new StringBuilder();
        sb.append("<br>");
        sb.append("The Promoter is proud to proclaim the following result of today's battle: ");
        sb.append(battleStats.getWinnerParty().getName());
        sb.append(" against ");
        sb.append(battleStats.getDefeatedParty().getName());
        sb.append(" ");
        sb.append(battleStats.getNumberOfWinsOfWinner());
        sb.append(" : ");
        sb.append(battleStats.getNumberOfWinsOfLoser());
        sb.append("<br>");
        sb.append(getStatsForParty(battleStats.getWinnerParty()));
        sb.append("<br>");
        sb.append(getStatsForParty(battleStats.getDefeatedParty()));
        return sb.toString();
    }

    private String getStatsForParty(Party party){
        String outputStr = "<br><b>" + party.getName()+"</b><br>";
        for (Hero h : party.getMembers()) {
            outputStr += "<br>" + getStatsForHero(h);
        }
        return outputStr;
    }

    private String getStatsForHero(Hero hero){
        return "<b>" + hero.getName() + "</b>: " +
               "[fights: " + hero.getFightStats().getFightsTotal()+ "]; " +
               "[wins: " + hero.getFightStats().getFightsWon()+ "]; " +
               "[defeats: " + hero.getFightStats().getFightsLost() + "]; " +
               "[Average harm caused: " + Helpers.round(hero.getFightStats().getAvgDamageCaused(), 2)+"]; " +
               "[Average harm taken: " + Helpers.round(hero.getFightStats().getAvgDamageTaken(), 2) +"]";
    }
}


