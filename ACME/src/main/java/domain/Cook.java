package domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Cook extends Actor {

    private Collection<MasterClass> masterClass;

    @OneToMany(targetEntity = MasterClass.class, cascade = CascadeType.ALL)
    public Collection<MasterClass> getMasterClass() {
        return masterClass;
    }

    public void setMasterClass(Collection<MasterClass> masterClass) {
        this.masterClass = masterClass;
    }

    @Override
    public String toString() {
        return super.getName() + " " + super.getSurname();
    }
}
