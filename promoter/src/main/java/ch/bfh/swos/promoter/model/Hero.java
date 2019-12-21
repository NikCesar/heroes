package ch.bfh.swos.promoter.model;

public class Hero {

    private String id;

    private String name;
    private int atk;
    private int def;
    private double hp;

    public Hero() {
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

    @Override
    public String toString(){
        return String.format("name=%s;atk=%s;def=%s;hp=%s;", this.getName(), this.getAtk(), this.getDef(), this.getHp());
    }
}
