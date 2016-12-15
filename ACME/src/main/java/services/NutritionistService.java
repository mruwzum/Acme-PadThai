package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.NutritionistRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class NutritionistService {

    // Constructors--------------------------------------------------------------------------------------

    public NutritionistService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private NutritionistRepository nutritionistRepository;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private CurriculaService curriculaService;
    @Autowired
    private LoginService loginService;

    // Supporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Nutritionist create() {
        Nutritionist res = new Nutritionist();
        return res;
    }

    public Collection<Nutritionist> findAll() {
        Collection<Nutritionist> res = nutritionistRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Nutritionist findOne(int Nutritionist) {
        Nutritionist res = nutritionistRepository.findOne(Nutritionist);
        Assert.notNull(res);
        return res;
    }

    public Nutritionist save(Nutritionist a) {
        Assert.notNull(a);
        Nutritionist res = nutritionistRepository.save(a);
        return res;
    }

    public void delete(Nutritionist a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        nutritionistRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------
    public Nutritionist findByPrincipal() {
        Nutritionist result;
        UserAccount userAccount;
        userAccount = loginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    public Nutritionist findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);
        Nutritionist result;
        result = nutritionistRepository.findByUserAccountId(userAccount.getId());
        return result;
    }

    public Curricula getCurricula(Nutritionist a) {
        Curricula res;
        Nutritionist aux = nutritionistRepository.findOne(a.getId());
        res = aux.getCurricula();
        return res;
    }

    Curricula editCurricula(Curricula a, String educationSection, String experienceSection, String photo, String hobbiesSection) {
        Nutritionist u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.NUTRITIONIST), "No es nutricionista");
        Assert.isTrue(!a.equals(u.getCurricula()), "No tiene curriculum");
        a.setEducationSection(educationSection);
        a.setExperienceSection(experienceSection);
        a.setPhoto(photo);
        a.setHobbiesSection(hobbiesSection);
        return a;

    }

    void deleteCurricula(Curricula a) {

        Nutritionist u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.NUTRITIONIST));

        Nutritionist us = findByPrincipal();
        Assert.isTrue(!a.equals(u.getCurricula()));
        Assert.notNull(us, "El actor no existe");
        Curricula aux = curriculaService.findOne(a.getId());
        Assert.notNull(aux);
        curriculaService.delete(a);
    }

    public Collection<Ingredient> getIngredients() {
        Collection<Ingredient> aux = ingredientService.findAll();
        return aux;
    }

    Collection<Ingredient> removeAllIngredients() {

        Nutritionist u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.NUTRITIONIST), "prob");

        Collection<Ingredient> aux = ingredientService.findAll();
        aux.removeAll(aux);
        return aux;
    }

    Collection<Ingredient> removeIngredient(Ingredient a) {

        Nutritionist u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.NUTRITIONIST));
        Collection<Ingredient> aux = ingredientService.findAll();
        Assert.notNull(aux);
        Assert.isTrue(hasARecipe(a));
        aux.remove(a);
        return aux;
    }

    Ingredient editIngredient(Ingredient a, String name, String description, String picture) {

        Nutritionist u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.NUTRITIONIST));
        Ingredient aux = ingredientService.findOne(a.getId());
        Assert.isTrue(hasARecipe(a));
        Assert.notNull(aux);
        aux.setName(name);
        aux.setDescription(description);
        aux.setPicture(picture);
        return aux;
    }

    private Boolean hasARecipe(Ingredient a) {
        Boolean res = false;

        Collection<Recipe> aux = recipeService.findAll();
        for (Recipe r : aux) {
            if (r.getIngredient().contains(a)) {
                res = true;

            }
        }
        return res;
    }

    public Collection<Property> getProperties1() {
        Collection<Property> aux;
        aux = propertyService.findAll();
        return aux;

    }

    Collection<Property> deleteAllProperties() {

        Nutritionist u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.NUTRITIONIST));
        Collection<Property> aux = propertyService.findAll();
        Assert.notNull(aux);
        aux.removeAll(aux);
        return aux;
    }

    void deleteProperty(Property p) {

        Nutritionist u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.NUTRITIONIST));
        propertyService.findOne(p.getId());
        Assert.notNull(p);
        Assert.isTrue(p.getId() != 0);
        Assert.isTrue(!usedToDescribeAnIngredient(p));
        propertyService.delete(p);
    }

    Property editProperty(Property p, String name, Quantity q) {

        Nutritionist u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.NUTRITIONIST));
        Property aux = propertyService.findOne(p.getId());
        Assert.notNull(aux);
        aux.setName(name);
        aux.setQuantity(q);
        return aux;
    }

    private Boolean usedToDescribeAnIngredient(Property p) {
        Boolean res = false;
        Collection<Ingredient> aux = ingredientService.findAll();
        for (Ingredient i : aux) {
            if (i.getProperty().equals(p)) {
                res = true;
            }
        }
        return res;
    }


}


