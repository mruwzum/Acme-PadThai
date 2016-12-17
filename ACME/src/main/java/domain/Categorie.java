package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Categorie extends DomainEntity {
    private String name;
    private String description;
    private String picture;
    private Collection<String> tag;

    public Categorie() {
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

    public void setPicture(String path) {
        this.picture = path;
    }

    @ElementCollection(targetClass = String.class)
    public Collection<String> getTag() {
        return tag;
    }

    public void setTag(Collection<String> tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return name;
    }
}