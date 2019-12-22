package ch.bfh.swos.arena.model;


import org.springframework.hateoas.RepresentationModel;

import java.util.List;


public class Party extends RepresentationModel {

    private Long id;
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

    public boolean containsHero(Hero hero) {
        return members.contains(hero);
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