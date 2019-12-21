package ch.bfh.swos.promoter.model;

/** Contains statistics for the duel as a whole **/

public class BattleStats {

    private Party winnerParty;
    private Party defeatedParty;

    public BattleStats(Party winnerParty, Party defeatedParty){
        this.winnerParty = winnerParty;
        this.defeatedParty = defeatedParty;
    }


    public int getNumberOfWinsOfWinner(){
        if(winnerParty != null){
            return winnerParty.getMembers().stream().mapToInt(x -> x.getFightStats().getFightsWon()).sum();
        }
        return -1;
    }

    public int getNumberOfWinsOfLoser(){
        if(defeatedParty != null){
            return defeatedParty.getMembers().stream().mapToInt(x -> x.getFightStats().getFightsWon()).sum();
        }
        return -1;
    }


    public Party getWinnerParty() {
        return winnerParty;
    }

    public Party getDefeatedParty() {
        return defeatedParty;
    }

    public void setWinnerParty(Party winnerParty) {
        this.winnerParty = winnerParty;
    }

    public void setDefeatedParty(Party defeatedParty) {
        this.defeatedParty = defeatedParty;
    }
}
