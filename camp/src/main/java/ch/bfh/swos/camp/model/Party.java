package ch.bfh.swos.camp.model;

import java.util.List;

public class Party {

    private String name;
    private List<Hero> members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hero> getMembers() {
        return members;
    }

    public void setMembers(List<Hero> members) {
        this.members = members;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(this.getName());
        s.append("\nMembers:\n");

        getMembers().forEach(h -> {
            s.append(h);
            s.append("\n");
        });
        return s.toString();
    }

}