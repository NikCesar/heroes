package ch.bfh.swos.arena.model;

public class Hero {

    private String name;
    private int atk;
    private int def;
    private double hp;
    private int initiative;
    private double dodgeChance;
    private double critChance;
    private HeroType heroType;
    private boolean isAlive = true;
    private HeroFightStats fightStats;

    public Hero() {
    }

    public Hero(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtk() { return atk; }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public double getHp() { return hp; }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public void setHeroType(HeroType heroType) {
        this.heroType = heroType;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public HeroFightStats getFightStats() {
        return fightStats;
    }

    public void setFightStats(HeroFightStats fightStats) {
        this.fightStats = fightStats;
    }

    @Override
    public String toString(){
        return String.format("name=%s;atk=%s;def=%s;hp=%s;initiative=%s;dodgeChance=%s;critChance=%s;Class=%s", this.getName(), this.getAtk(), this.getDef(), this.getHp(), this.getInitiative(), this.getDodgeChance(), this.getCritChance(), this.getHeroType().toString());
    }
}
