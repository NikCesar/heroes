package ch.bfh.swos.arena.model;

/** Contains statistics for the duel as a whole **/

public class BattleStats {

    private Party winnerParty;
    private Party defeatedParty;

    public BattleStats(Party winnerParty, Party defeatedParty){
        this.winnerParty = winnerParty;
        this.defeatedParty = defeatedParty;
    }

    public Party getWinnerParty() {
        return winnerParty;
    }

    public void setWinnerParty(Party winnerParty) {
        this.winnerParty = winnerParty;
    }

    public Party getDefeatedParty() {
        return defeatedParty;
    }

    public void setDefeatedParty(Party defeatedParty) {
        this.defeatedParty = defeatedParty;
    }
}
