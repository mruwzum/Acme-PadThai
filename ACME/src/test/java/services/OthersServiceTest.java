package services;

import domain.Comment;
import domain.Others;
import domain.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by daviddelatorre on 12/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class OthersServiceTest extends AbstractTest {

    //Service under test ---------------------------------------------------------------------

    @Autowired
    private OthersService othersService;
    @Autowired
    private RecipeService recipeService;

    //Tests -------------------------------------------------

    @Test
    public void findByPrincipal() throws Exception {


        authenticate("User2");
        Others u = othersService.findByPrincipal();
        System.out.println(u.getName());
        authenticate(null);
    }

    @Test
    public void followOther() throws Exception {
        authenticate("User1");
        List<Others> aux = new ArrayList<>(othersService.findAll());
        othersService.followOther(aux.get(2));
        authenticate(null);

    }

    @Test
    public void unfollowOther() throws Exception {
        authenticate("User1");
        List<Others> aux = new ArrayList<>(othersService.findAll());
        othersService.unfollowOther(aux.get(3));
        authenticate(null);

    }

    @Test
    public void getRecipesOfFollowers() throws Exception {

        authenticate("User1");

        try {
            Collection<Recipe> res = othersService.getRecipesOfFollowers();

        } catch (NoSuchElementException e) {
            System.out.println("Colección vacía");
        }

        authenticate(null);
    }

    @Test
    public void likeRecipe() throws Exception {
        authenticate("User2");
        List<Recipe> aux = new ArrayList<>(recipeService.findAll());
        othersService.likeRecipe(aux.get(0));
        authenticate(null);
    }

    @Test
    public void dislikeRecipe() throws Exception {
        authenticate("User2");
        List<Recipe> aux = new ArrayList<>(recipeService.findAll());
        othersService.dislikeRecipe(aux.get(0));
        authenticate(null);
    }

    @Test
    public void writeCommentToRecipe() throws Exception {

        List<Recipe> recipes = new ArrayList<>(recipeService.findAll());
        String title = "PRUEBA";
        String text = "Esto es una prueba de comentario";
        Integer number = 3;
        Comment res = othersService.writeCommentToRecipe(recipes.get(1), title, text, number);
        System.out.println(res);
        System.out.println(recipes.get(1).getComments());

    }

}