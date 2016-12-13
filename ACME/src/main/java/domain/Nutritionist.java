package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Nutritionist extends Others {

    private Curricula curricula;

    private IngredientsRepository ingredientsRepository;

    @OneToOne
    public Curricula getCurricula() {
        return curricula;
    }

    public void setCurricula(Curricula curricula) {
        this.curricula = curricula;
    }

    @OneToOne(targetEntity = IngredientsRepository.class)
    public IngredientsRepository getIngredientsRepository() {
        return ingredientsRepository;
    }

    public void setIngredientsRepository(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }


}
