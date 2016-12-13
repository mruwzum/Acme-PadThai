package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class StepsToCook extends DomainEntity {
    private String description;
    private String picture;
    private Collection<String> hint;


    public StepsToCook() {
        super();
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

    @ElementCollection(targetClass = String.class)
    public Collection<String> getHint() {
        return hint;
    }

    public void setHint(Collection<String> hint) {
        this.hint = hint;
    }

}
