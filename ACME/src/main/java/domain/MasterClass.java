package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class MasterClass extends DomainEntity {

    private String title;
    private String description;
    private Cook cook;
    private Collection<LearningMaterial> material;
    private Collection<Actor> registers;
    private Collection<Admin> promoters;


    public MasterClass() {
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
    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(optional = false)
    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public Collection<LearningMaterial> getMaterial() {
        return material;
    }

    public void setMaterial(Collection<LearningMaterial> material) {
        this.material = material;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Actor.class)
    public Collection<Actor> getRegisters() {
        return registers;
    }

    public void setRegisters(Collection<Actor> registers) {
        this.registers = registers;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Admin.class)
    public Collection<Admin> getPromoters() {
        return promoters;
    }

    public void setPromoters(Collection<Admin> promoters) {
        this.promoters = promoters;
    }

}
