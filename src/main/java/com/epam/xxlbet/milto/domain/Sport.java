package com.epam.xxlbet.milto.domain;

/**
 * Sport model.
 *
 * @author Aliaksei Milto
 */
public class Sport {
    private Long id;
    private String name;

    public Sport() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
