package controllers.user;

import controllers.AbstractController;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.RecipeService;
import services.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;

/**
 * Created by mruwzum on 14/12/16.
 */
@Controller
@RequestMapping("user")


public class managingRecipesController extends AbstractController {
    // Services ---------------------------------------------------------------


    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActorService actorService;


    // Constructors -----------------------------------------------------------

    public managingRecipesController() {
        super();
    }

    // Listing ----------------------------------------------------------------
    @RequestMapping(value = "recipes/list", method = RequestMethod.GET)
    public ModelAndView listRecipes() {
        ModelAndView result;

        Collection<Recipe> recipeCollection;
        recipeCollection = userService.getAllRecipes();
        Assert.notEmpty(recipeCollection);
        result = new ModelAndView("recipe/list");
        result.addObject("recipe", recipeCollection);
        result.addObject("requestURI", "user/recipes/list.do");

        return result;
    }

    @RequestMapping(value = "recipes/list/my", method = RequestMethod.GET)
    public ModelAndView listMyRecipes() {
        ModelAndView result;
        Collection<Recipe> myrecipes= userService.getAllRecipes();
       Collection<Recipe> allrecipes = recipeService.findAll();
       allrecipes.removeAll(myrecipes);
       result = new ModelAndView("recipe/list");
       result.addObject("recipe", allrecipes);
        return result;
    }

    //Creation----------------------------------------------------------------

    @RequestMapping(value = "recipes/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Recipe recipe;
        String creationDate = "hola";
        String updateDate = "hola";
        recipe = recipeService.create();
        result = createEditModelAndView(recipe);
        result.addObject("creationDate", creationDate);
        result.addObject("updateDate", creationDate);





        return result;
    }

    // Edition ------------------------------------------------------------------------

    @RequestMapping(value = "recipes/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int id) {
        ModelAndView result;
        Recipe recipe;
        recipe = recipeService.findOne(id);
        Assert.notNull(recipe);
        result = createEditModelAndView(recipe);
        return result;
    }


    //SAVE ----------------------------------

    @RequestMapping(value = "recipes/edit/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Recipe recipe, BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(recipe);
        } else {
            try {
                recipeService.save(recipe);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(recipe, "item.commit.error");
            }
        }
        return result;
    }

    //DELETE ----------------------------------

    @RequestMapping(value = "recipes/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Recipe recipe, BindingResult binding) {
        ModelAndView result;

        try {
            recipeService.delete(recipe);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(recipe, "item.commit.error");
        }
        return result;
    }

    protected ModelAndView createEditModelAndView(Recipe recipe) {

        ModelAndView result;

        result = createEditModelAndView(recipe, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Recipe recipe, String message) {
        ModelAndView result;
        String title = recipe.getTitle();
        String sumary = recipe.getSummary();
        String creationDate;
        if(recipe.getCreationDate() == null){
            creationDate = new Date(System.currentTimeMillis() - 100).toString();
        }else{
            creationDate = recipe.getCreationDate().toString();
        }
        String updateDate;
        if(recipe.getUpdateDate() == null){
            updateDate = new Date(System.currentTimeMillis() - 100).toString();
        }else{
            updateDate = recipe.getUpdateDate().toString();
        }
        Collection<String> pictures = recipe.getPictures();
        result = new ModelAndView("recipe/edit");

        result.addObject("recipe", recipe);
        result.addObject("title", title);
        result.addObject("summary", sumary);
        result.addObject("creationDate", creationDate);
        result.addObject("updateDate", updateDate);
        result.addObject("pictures", pictures);
        result.addObject("message", message);

        return result;
    }
}


