package domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Others {

    private Collection<Recipe> recipes;


    @OneToMany(targetEntity = Recipe.class, cascade = CascadeType.ALL)
    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }

}