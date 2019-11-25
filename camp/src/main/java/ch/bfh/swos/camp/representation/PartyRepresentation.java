package ch.bfh.swos.camp.representation;

import org.springframework.hateoas.RepresentationModel;

public class PartyRepresentation extends RepresentationModel {

    private Long id;
    private String name;

    public PartyRepresentation(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
