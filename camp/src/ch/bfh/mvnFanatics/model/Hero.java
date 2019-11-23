package ch.bfh.mvnFanatics.model;

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
    private int hp;

    public Hero() {
    }

    public Hero(String name, int atk, int def, int hp) {
        this.name = name;
        this.atk = atk;
        this.def = def;
        this.hp = hp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                " / " + "HP: " + getHp() +
                " / " + "Atk: " + getAtk() +
                " / " + "Def: " + getDef();
    }
}
