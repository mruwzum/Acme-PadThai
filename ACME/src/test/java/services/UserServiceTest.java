package services;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by daviddelatorre on 6/11/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class UserServiceTest extends AbstractTest {

    //Service under test ---------------------------------------------------------------------

    @Autowired
    private UserService userService;

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private StepsToCookService stepsToCookService;

    @Test
    public void testFindByPrincipal() {

        User u;
        authenticate("User2");
        u = userService.findByPrincipal();
        System.out.println(u.getName());
        authenticate(null);
    }

    @Test
    public void testCreateRecipe() {


        authenticate("User1");
        User u = userService.findByPrincipal();
        Assert.notNull(u, "el usuario no es correcto");
        String tickr = "121212-pepe";
        String title, summary;
        title = "nuevo";
        summary = "sumerio";
        Collection<String> pic = new ArrayList<>();
        String fd = "foto54.png";
        String ff = "fsdf.png";
        pic.add(fd);
        pic.add(ff);
        Collection<String> hints = new ArrayList<>();
        hints.add(fd);
        hints.add(ff);
        Categorie cat = categorieService.create();
        Categorie cat2 = categorieService.save(cat);
        Collection<Ingredient> ingr = ingredientService.findAll();
        Collection<StepsToCook> steps = stepsToCookService.findAll();
        userService.createRecipe(tickr, title, summary, pic, hints, ingr, cat2, steps);
        authenticate(null);
    }

    @Test
    public void testModifyRecipe() {

        authenticate("User1");
        List<User> users = new ArrayList<>(userService.findAll());
        List<Recipe> recipesOfUser1 = new ArrayList<>(users.get(0).getRecipes());
        List<String> pic = new ArrayList<>();
        List<String> hints = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>(ingredientService.findAll());
        List<Categorie> categories = new ArrayList<>(categorieService.findAll());
        List<StepsToCook> steepstocook = new ArrayList<>(stepsToCookService.findAll());
        Recipe mod = userService.modifyRecipe(recipesOfUser1.get(0), "PRUEBA_MOD", "Receta de prueba", pic, hints, ingredients,
                categories.get(2), steepstocook);
        System.out.println("La receta modificada es: " + mod.getTitle());
        authenticate(null);

    }

    @Test
    public void testGetAllRecipes() {

        authenticate("User1");
        Collection<Recipe> res = userService.getAllRecipes();
        System.out.println(res);
        authenticate(null);
    }

    @Test
    public void testDeleteRecipe() {
        authenticate("User1");
        List<Recipe> recipesOfUser1 = new ArrayList<>(userService.getAllRecipes());
        Recipe res = recipesOfUser1.get(0);
        Assert.notNull(res, "La receta es nula");
        userService.deleteRecipe(res);
        System.out.println(res);
        System.out.println(recipesOfUser1.get(0).getId());


        authenticate(null);


    }

    @Test
    public void testQualifyRecipe() {
        authenticate("User1");
        List<Recipe> recipesOfUser1 = new ArrayList<>(userService.getAllRecipes());
        //Desmarcar esto para probar el fallo
        /*Boolean fa =  new Boolean(false);
        recipesOfUser1.get(0).getRate().add(fa);*/
        userService.qualifyRecipe(recipesOfUser1.get(0));
        authenticate(null);
    }

}
