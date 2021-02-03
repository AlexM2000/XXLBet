package com.epam.xxlbet.milto.domain;

import java.util.Objects;

/**
 * Sport model.
 *
 * @author Aliaksei Milto
 */
public class Sport {
    private Long id;
    private String name;

    public Sport() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sport sport = (Sport) o;
        return id.equals(sport.id) &&
                name.equals(sport.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

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
