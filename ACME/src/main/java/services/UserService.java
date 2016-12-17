package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class UserService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private UserRepository userRepository;

    // Managed repository--------------------------------------------------------------------------------
    @Autowired
    private RecipeService recipeService;


    // Suporting services --------------------------------------------------------------------------------
    @Autowired
    private ContestService contestService;

    public UserService() {
        super();
    }


    // Simple CRUD method --------------------------------------------------------------------------------

    public User create() {
        User res = new User();
        return res;
    }

    public Collection<User> findAll() {
        Collection<User> res = userRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public User findOne(int User) {
        User res = userRepository.findOne(User);
        Assert.notNull(res);
        return res;
    }

    public User save(User a) {
        Assert.notNull(a);
        User res = userRepository.save(a);
        return res;
    }

    public void delete(User a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        userRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------


    public User findByPrincipal() {
        User result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    public User findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        User result;

        result = userRepository.findByUserAccountId(userAccount.getId());

        return result;
    }

    public Recipe createRecipe(String ticker, String title, String summary, Collection<String> pictures,
                               Collection<String> hints, Collection<Ingredient> ingredients,
                               Categorie categorie, Collection<StepsToCook> steepsToCook) {
        Assert.notNull(ticker);
        Assert.notNull(title);
        Assert.notNull(summary);
        Assert.notNull(ingredients);
        Assert.notNull(categorie);
        Assert.notNull(steepsToCook);
        User u = findByPrincipal();
        Assert.notNull(u, "Este user no puede crear recetas");
        List<Boolean> rates = new ArrayList<>();
        rates.add(new Boolean(true));
        List<Recipe> allRecipes = new ArrayList<>(recipeService.findAll());
        Recipe aux = recipeService.save(allRecipes.get(2));
        aux.setTicker(ticker);
        aux.setRate(rates);
        aux.setTitle(title);
        aux.setSummary(summary);
        aux.setPictures(pictures);
        aux.setHints(hints);
        aux.setIngredient(ingredients);
        aux.setCategorie(categorie);
        aux.setStepsToCook(steepsToCook);
        aux.setCreationDate(new Date(System.currentTimeMillis() - 100));
        aux.setUpdateDate(new Date(System.currentTimeMillis() - 150));
        aux.setLikesNumber(aux.getLikesNumber());
        u.getRecipes().add(aux);
        return aux;

    }

    public Collection<Recipe> getAllRecipes() {
        Collection<Recipe> res;
        User u;
        u = findByPrincipal();
        Assert.notNull(u);
        res = userRepository.getMyRecipes(u.getId());
        return res;
    }

    public Collection<Recipe> getMyRecipes(int id) {
        Collection<Recipe> res;

        res = userRepository.getMyRecipes(id);
        return res;
    }

    public Recipe modifyRecipe(Recipe r, String title, String summary, Collection<String> pictures,
                               Collection<String> hints, Collection<Ingredient> ingredients,
                               Categorie categorie, Collection<StepsToCook> steepsToCook) {

        Assert.notNull(title);
        Assert.notNull(summary);
        Assert.notNull(ingredients);
        Assert.notNull(categorie);
        Assert.notNull(steepsToCook);
        Assert.notNull(r);
        User u = findByPrincipal();
        Assert.notNull(u);
        Assert.isTrue(u.getRecipes().contains(r));
        r.setTitle(title);
        r.setSummary(summary);
        r.setPictures(pictures);
        r.setHints(hints);
        r.setIngredient(ingredients);
        r.setCategorie(categorie);
        r.setStepsToCook(steepsToCook);
        r.setUpdateDate(new Date(System.currentTimeMillis() - 100));
        return r;
    }

    public void deleteRecipe(Recipe r) {
        Assert.notNull(r);
        User u = findByPrincipal();
        Assert.notNull(u, "Recetica nula");
        Assert.isTrue(u.getRecipes().contains(r), "La receta no es propia");
        recipeService.delete(r);
    }

    public void qualifyRecipe(Recipe r) {
        User u = findByPrincipal();
        Assert.notNull(u);
        Assert.isTrue(u.getRecipes().contains(r),"No contiene");
        Assert.isTrue(canBeQualified(r), "La receta no puede ser calificada");
        List<Contest> contest = new ArrayList<>(contestService.findAll());
        contest.get(2).getWinners().add(r);
    }

    public void rateRecipeWithLike(Recipe r) {
        Assert.notNull(r);
        User u = findByPrincipal();
        Assert.notNull(u);
        Assert.isTrue(u.getRecipes().contains(r), "Un usuario no puede puntuar su propia receta");
        Boolean like = new Boolean(true);
        r.getRate().add(like);
    }

    public void rateRecipeWithDislike(Recipe r) {
        Assert.notNull(r);
        User u = findByPrincipal();
        Assert.notNull(u);
        Assert.isTrue(u.getRecipes().contains(r), "Un usuario no puede puntuar su propia receta");
        Boolean fale = new Boolean(false);
        r.getRate().add(fale);
    }

    public Boolean canBeQualified(Recipe r) {
        Boolean res = false;
        Integer likes = 0;
        Integer dislikes = 0;
        for (Boolean b : r.getRate()) {

            if (b) {

                likes++;
            } else {
                dislikes++;
            }
        }
        Assert.isTrue(likes >= 5 && dislikes == 0, "La receta no reune los requisitos para clasificarse");
        res = true;

        return res;

    }
}



