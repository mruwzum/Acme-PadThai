package services;

import domain.Ingredient;
import domain.Property;
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
    private IngredientRepository ingredientRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------
    public Ingredient create() {
        Ingredient res = new Ingredient();
        return res;
    }

    public Collection<Ingredient> findAll() {
        Collection<Ingredient> res = ingredientRepository.findAll();
        Assert.notNull(res,"No lo encuentro");
        return res;
    }

    public Ingredient findOne(int Ingredient) {
        domain.Ingredient res = ingredientRepository.findOne(Ingredient);
        Assert.notNull(res);
        return res;
    }

    public Ingredient save(Ingredient a) {
        Assert.notNull(a);
        Ingredient res = ingredientRepository.save(a);
        return res;
    }

    public void delete(Ingredient a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        ingredientRepository.delete(a);
    }

    public void deleteProperty(Ingredient a, Property p) {
        Assert.notNull(a);
        Assert.notNull(p);
        a.getProperty().remove(p);
    }

    public void addProperty(Ingredient a, Property p) {
        Assert.notNull(a);
        Assert.notNull(p);
        a.getProperty().add(p);
    }

    // Other business methods -------------------------------------------------------------------------------

}



