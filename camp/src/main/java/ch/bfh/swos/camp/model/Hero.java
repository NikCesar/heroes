package ch.bfh.swos.camp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
public class Hero {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column
    private String name;
    @Column
    private int atk;
    @Column
    private int def;
    @Column
    private double hp;
    @Column
    private int position;
    @Column
    private int initiative;
    @Column
    private double dodgeChance;
    @Column
    private double critChance;
    @Column(name = "hero_class")
    @Enumerated(EnumType.STRING)
    private HeroType heroType;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public HeroFightStats getFightStats() { return fightStats; }

    public void setFightStats(HeroFightStats fightStats) { this.fightStats = fightStats; }


    @Override
    public String toString(){
        return String.format("name=%s;atk=%s;def=%s;hp=%s;position=%s;initiative=%s;dodgeChance=%s;critChance=%s;", this.getName(), this.getAtk(), this.getDef(), this.getHp(), this.getPosition(), this.getInitiative(), this.getDodgeChance(), this.getCritChance());
    }
}
