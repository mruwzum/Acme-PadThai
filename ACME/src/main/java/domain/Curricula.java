package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Curricula extends DomainEntity {
    private String photo;
    private String educationSection;
    private String experienceSection;
    private Collection<String> referencias;
    private String hobbiesSection;

    public Curricula() {
        super();
    }

    @NotBlank
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String path) {
        this.photo = path;
    }

    public String getEducationSection() {
        return educationSection;
    }

    public void setEducationSection(String educationSection) {
        this.educationSection = educationSection;
    }

    public String getExperienceSection() {
        return experienceSection;
    }

    public void setExperienceSection(String experienceSection) {
        this.experienceSection = experienceSection;
    }

    public String getHobbiesSection() {
        return hobbiesSection;
    }

    public void setHobbiesSection(String hobbiesSection) {
        this.hobbiesSection = hobbiesSection;
    }
    @ElementCollection(targetClass = String.class)
    public Collection<String> getReferencias() {
        return referencias;
    }

    public void setReferencias(Collection<String> references) {
        this.referencias = references;
    }

}