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
import services.RecipeService;
import services.UserService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 14/12/16.
 */
@Controller
@RequestMapping("recipes/user")

public class managingRecipesController extends AbstractController {
    // Services ---------------------------------------------------------------

    @Autowired
    private UserService userService;
    @Autowired
    private RecipeService recipeService;


    // Constructors -----------------------------------------------------------

    public managingRecipesController() {
        super();
    }

    // Listing ----------------------------------------------------------------
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listRecipes() {
        ModelAndView result;

        Collection<Recipe> recipeCollection;

        recipeCollection = recipeService.findAll();

        //Assert.notEmpty(items);

        result = new ModelAndView("recipes/list");
        result.addObject("recipes", recipeCollection);
        result.addObject("requestURI", "recipes/user/list.do");


        return result;
    }

    //Creation----------------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Recipe recipe;

        recipe = recipeService.create();
        result = createEditModelAndView(recipe);

        return result;
    }

    // Edition ------------------------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int itemId) {
        ModelAndView result;
        Recipe recipe;

        recipe = recipeService.findOne(itemId);
        Assert.notNull(recipe);
        result = createEditModelAndView(recipe);

        return result;
    }


    //SAVE ----------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
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

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
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

        result = new ModelAndView("recipe/edit");
        result.addObject("recipe", recipe);
        result.addObject("message", message);

        return result;
    }
}


