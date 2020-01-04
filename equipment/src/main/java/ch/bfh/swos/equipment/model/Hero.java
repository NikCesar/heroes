package ch.bfh.swos.equipment.model;

import javax.persistence.*;

public class Hero {

    private String id;

    private String name;

    private int atk;

    private int def;

    private double hp;

    private int initiative;

    private double dodgeChance;

    private double critChance;

    private HeroType heroType;

    private Long armorId;

    private Long weaponId;

    private Long mountId;

    // Do not persist:
    @Transient
    private HeroFightStats fightStats;

    public Hero() {
        this.fightStats = new HeroFightStats();
    }

    public Hero(String name){
        this.name = name;
    }

    public String getId() {
        return id;
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

    public HeroFightStats getFightStats() {
        return fightStats;
    }

    public void setFightStats(HeroFightStats fightStats) {
        this.fightStats = fightStats;
    }

    public Long getArmorId() {
        return armorId;
    }

    public void setArmorId(Long armorId) {
        this.armorId = armorId;
    }

    public Long getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Long weaponId) {
        this.weaponId = weaponId;
    }

    public Long getMountId() {
        return mountId;
    }

    public void setMountId(Long mountId) {
        this.mountId = mountId;
    }

    @Override
    public String toString(){
        return String.format("name=%s;atk=%s;def=%s;hp=%s;initiative=%s;dodgeChance=%s;critChance=%s;", this.getName(), this.getAtk(), this.getDef(), this.getHp(), this.getInitiative(), this.getDodgeChance(), this.getCritChance());
    }
}
