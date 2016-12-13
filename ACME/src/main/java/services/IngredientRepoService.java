package services;

import domain.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.IngredientRepoRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class IngredientRepoService {

    // Constructors--------------------------------------------------------------------------------------

    public IngredientRepoService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private IngredientRepoRepository ingredientRepoRepository;


    // Supporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public IngredientsRepository create() {
        return null;
    }

    public Collection<IngredientsRepository> findAll() {
        return null;
    }

    public IngredientsRepository findOne(int IngredientsRepository) {
        return null;
    }

    public IngredientsRepository save(IngredientsRepository a) {
        return null;
    }

    public void delete(IngredientsRepository a) {

    }

    // Other business methods -------------------------------------------------------------------------------

}



