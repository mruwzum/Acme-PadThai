package domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Actor {
    private Collection<Contest> contest;

    //promoted Master clases
    private Collection<MasterClass> masterClass;
    private Collection<Categorie> categorie;
    private Collection<String> keywor;


    @OneToMany(targetEntity = MasterClass.class)
    public Collection<MasterClass> getMasterClass() {
        return masterClass;
    }

    public void setMasterClass(Collection<MasterClass> masterClass) {
        this.masterClass = masterClass;
    }

    @OneToMany
    public Collection<Contest> getContest() {
        return contest;
    }

    public void setContest(Collection<Contest> contest) {
        this.contest = contest;
    }

    @OneToMany
    public Collection<Categorie> getCategorie() {
        return categorie;
    }

    public void setCategorie(Collection<Categorie> categorie) {
        this.categorie = categorie;
    }

    @ElementCollection(targetClass = String.class)
    public Collection<String> getKeywor() {
//        keywor.add("sex");
//        keywor.add("viagra");
//        keywor.add("cialis");
//        keywor.add("love");
        return keywor;
    }

    public void setKeywor(Collection<String> keywor) {
        this.keywor = keywor;
    }


}
