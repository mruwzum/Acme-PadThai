package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@Entity
@Access(AccessType.PROPERTY)
public class LearningMaterial extends DomainEntity {

    private String title;
    private String abstracts;
    private Collection<String> attachment;
    private String type;

    public LearningMaterial() {
        super();
    }

    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank
    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    @ElementCollection(targetClass = String.class)
    public Collection<String> getAttachment() {
        return attachment;
    }

    public void setAttachment(Collection<String> attachment) {
        this.attachment = attachment;
    }

    @NotNull
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
