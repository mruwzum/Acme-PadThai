package controllers.anonymus;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Authority;
import services.*;

import javax.validation.Valid;
import java.util.*;

//Estoy probando mi rama en local
/**
 * Created by daviddelatorre on 6/12/16.
 */
@Controller
@RequestMapping("anonymus")
public class AnonymusController extends AbstractController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private MasterClassService masterClassService;
    @Autowired
    private NutritionistService nutritionistService;
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private OthersService   othersService;


    public AnonymusController(){
    super();
    }



    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public ModelAndView recipes(){
        ModelAndView result;
        Collection<Recipe> aux = recipeService.findAll();
        result = new ModelAndView("recipe/list");
        result.addObject("recipe", aux);
        result.addObject("requestURI", "anonymus/recipes.do");
        return result;
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public ModelAndView author(@RequestParam int recipeID) {
        ModelAndView result;
        Recipe recipe = recipeService.findOne(recipeID);
        Assert.notNull(recipe);
        User user = recipe.getUser();
        Assert.notNull(user);
        result = new ModelAndView("user/list");
        result.addObject("user", user);
        return result;
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users(){
        ModelAndView result;
        Collection<User> aux = userService.findAll();
        result = new ModelAndView("user/list");
        result.addObject("user", aux);
        result.addObject("requestURI", "anonymus/users.do");
        return result;
    }

    @RequestMapping(value = "/others", method = RequestMethod.GET)
    public ModelAndView others(){
        ModelAndView result;
        Collection<User> aux = userService.findAll();
        Collection<Nutritionist> nutritionists =  nutritionistService.findAll();
        result = new ModelAndView("actor/list");
        result.addObject("user", aux);
        result.addObject("nutritionist", nutritionists);
        result.addObject("requestURI", "anonymus/users.do");
        return result;
    }


    @RequestMapping(value = "/userRecipes", method = RequestMethod.GET)
    public ModelAndView userRecipes(@RequestParam int userID) {
        ModelAndView modelAndView;
        Collection<Recipe> recipes = new ArrayList<>();
        recipes.addAll(userService.getMyRecipes(userID));
        modelAndView = new ModelAndView("recipe/list");
        modelAndView.addObject("recipe", recipes);
        return modelAndView;
    }
    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public ModelAndView userProfile(@RequestParam int userID) {
        ModelAndView modelAndView;
        User user = userService.findOne(userID);
        Boolean isFollowing = othersService.getFollowing().contains(user);
        Boolean isnotFollowing = !othersService.getFollowing().contains(user);
        modelAndView = new ModelAndView("user/view");
        modelAndView.addObject("id", user.getId());
        modelAndView.addObject("name", user.getName());
        modelAndView.addObject("surname", user.getSurname());
        modelAndView.addObject("emailAddress", user.getEmailAddress());
        modelAndView.addObject("phone", user.getPhone());
        modelAndView.addObject("postalAddress", user.getPostalAddress());
        modelAndView.addObject("isFollowing", isFollowing);
        modelAndView.addObject("isnotFollowing", isnotFollowing);


        return modelAndView;
    }

    @RequestMapping(value = "/nutritionistProfile", method = RequestMethod.GET)
    public ModelAndView nutritionistProfile(@RequestParam int userID) {
        ModelAndView modelAndView;
        Nutritionist user = nutritionistService.findOne(userID);
        Boolean isFollowing = othersService.getFollowing().contains(user);
        Boolean isnotFollowing = !othersService.getFollowing().contains(user);

        modelAndView = new ModelAndView("user/view");
        modelAndView.addObject("id", user.getId());
        modelAndView.addObject("name", user.getName());
        modelAndView.addObject("surname", user.getSurname());
        modelAndView.addObject("emailAddress", user.getEmailAddress());
        modelAndView.addObject("phone", user.getPhone());
        modelAndView.addObject("postalAddress", user.getPostalAddress());
        modelAndView.addObject("isFollowing", isFollowing);
        modelAndView.addObject("isnotFollowing", isnotFollowing);



        return modelAndView;
    }
    @RequestMapping(value = "/contest", method = RequestMethod.GET)
    public ModelAndView contest(){
        ModelAndView result;
        Collection<Contest> aux = contestService.findAll();
        result = new ModelAndView("contest/list");
        result.addObject("contest", aux);
        result.addObject("requestURI", "anonymus/contest.do");
        return result;
    }

    @RequestMapping(value = "/winners", method = RequestMethod.GET)
    public ModelAndView winners(@RequestParam int contestID) {
        ModelAndView result;
        Contest contest = contestService.findOne(contestID);
        Collection<Recipe> recipes = new ArrayList<>(contest.getWinners());
        Assert.notEmpty(recipes);
        result = new ModelAndView("recipe/list");
        result.addObject("recipe", recipes);
        return result;
    }
    @RequestMapping(value = "/masterclass", method = RequestMethod.GET)
    public ModelAndView masterClass(){
        ModelAndView result;
        Collection<MasterClass> aux = masterClassService.findAll();
        result = new ModelAndView("masterClass/list");
        result.addObject("masterClass", aux);
        result.addObject("requestURI", "anonymus/masterclass.do");
        return result;
    }

    @RequestMapping(value = "/searchUser", method = RequestMethod.GET)
    public ModelAndView searchUser() {
        ModelAndView res;
        User user = userService.create();
        res = new ModelAndView("user/search");
        res.addObject("user", user);
        return res;
    }

    @RequestMapping(value = "/searchRecipe", method = RequestMethod.GET)
    public ModelAndView searchRecipe() {
        ModelAndView res;
        Recipe recipe = recipeService.create();
        res = new ModelAndView("recipe/search");
        res.addObject("recipe", recipe);
        return res;
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    public ModelAndView findUser(@RequestParam String userName) {
        ModelAndView res;
        List<User> users = new ArrayList<>(userService.findAll());
        User aux = null;
        for (User u : users) {
            if (!u.getName().equals(userName)) {
                aux = u;
                break;
            }
        }
        Assert.notNull(aux, "User not found / No se ha encontrado el usuario");
        res = new ModelAndView("user/list");
        res.addObject("user", aux);
        return res;
    }

    @RequestMapping(value = "/findRecipe", method = RequestMethod.GET)
    public ModelAndView findRecipe(@RequestParam String recipeTicker, String title, String summary) {
        ModelAndView res;
        List<Recipe> recipes = new ArrayList<>(recipeService.findAll());

        Recipe aux = null;
        for (Recipe u : recipes) {
            if (!u.getTicker().equals(recipeTicker) || !u.getTitle().equals(title) ||u.getSummary().equals(summary)) {
                aux = u;
                break;
            }
        }
        Assert.notNull(aux, "Recipe not found / No se ha encontrado la receta");
        res = new ModelAndView("recipe/list");
        res.addObject("recipe", aux);
        return res;
    }

    //ANCILLARY METHODS


}
