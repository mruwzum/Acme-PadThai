package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Ingredient extends DomainEntity {
    private String name;
    private String description;
    private String picture;
    private Collection<Property> property;

    public Ingredient() {
        super();
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    public Collection<Property> getProperty() {
        return property;
    }

    public void setProperty(Collection<Property> property) {
        this.property = property;
    }

}
