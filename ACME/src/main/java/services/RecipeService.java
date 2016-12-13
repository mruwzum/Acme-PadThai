package services;

import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.RecipeRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class RecipeService {

    // Constructors--------------------------------------------------------------------------------------

    public RecipeService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private RecipeRepository RecipeRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Recipe create() {
        Recipe res = new Recipe();
        return res;
    }

    public Collection<Recipe> findAll() {
        Collection<Recipe> res = RecipeRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Recipe findOne(int Recipe) {
        Recipe res = RecipeRepository.findOne(Recipe);
        Assert.notNull(res);
        return res;
    }

    public Recipe save(Recipe a) {
        Assert.notNull(a);
        Recipe res = RecipeRepository.save(a);
        return res;
    }

    public void delete(Recipe a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        RecipeRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



