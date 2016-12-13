package domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class IngredientsRepository extends DomainEntity {
    private Collection<Ingredient> ingredient;

    public IngredientsRepository() {
        super();
    }

    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    public Collection<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Collection<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }
}
