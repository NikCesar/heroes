package ch.bfh.swos.camp.model;


import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Party extends RepresentationModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany
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