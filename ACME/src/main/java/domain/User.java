package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Others {

    private Collection<Recipe> recipes;


    @OneToMany(targetEntity = Recipe.class)
    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }

}