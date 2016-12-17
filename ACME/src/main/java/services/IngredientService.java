package services;

import domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.IngredientRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class IngredientService {

    // Constructors--------------------------------------------------------------------------------------

    public IngredientService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private static IngredientRepository IngredientRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Ingredient create() {
        Ingredient res = new Ingredient();
        return res;
    }

    public Collection<Ingredient> findAll() {
        Collection<Ingredient> res = IngredientRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Ingredient findOne(int Ingredient) {
        domain.Ingredient res = IngredientRepository.findOne(Ingredient);
        Assert.notNull(res);
        return res;
    }

    public Ingredient save(Ingredient a) {
        Assert.notNull(a);
        Ingredient res = IngredientRepository.save(a);
        return res;
    }

    public void delete(Ingredient a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        IngredientRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



