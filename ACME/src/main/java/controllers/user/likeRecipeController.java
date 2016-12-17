package controllers.user;

import controllers.AbstractController;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.RecipeService;
import services.UserService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 15/12/16.
 */
@Controller
@RequestMapping("user")
public class likeRecipeController extends AbstractController {

    @Autowired
    private UserService userService;
    @Autowired
    private RecipeService recipeService;

    //LIST ----------


    public likeRecipeController() {
        super();
    }

    @RequestMapping(value = "recipes/list/2", method = RequestMethod.GET)
    public ModelAndView listRecipes() {
        ModelAndView result;

        Collection<Recipe> recipeCollection;
        recipeCollection = recipeService.findAll();
        Assert.notEmpty(recipeCollection);
        result = new ModelAndView("recipe/list");
        result.addObject("recipe", recipeCollection);
        result.addObject("requestURI", "user/recipes/list.do");


        return result;
    }

    //EDIT (LIKE)----------------
    @RequestMapping(value = "recipe/like", method = RequestMethod.GET, params = "save")
    public ModelAndView likeRecipe(@Valid @ModelAttribute Recipe recipe, BindingResult bindingResult) {
        ModelAndView res;
        userService.rateRecipeWithLike(recipe);
        res = new ModelAndView("recipe/list");
        res.addObject("recipe", recipe);

        return res;
    }

    //SAVE ----------------------------
    @RequestMapping(value = "recipe/like/save", method = RequestMethod.POST, params = "save")
    public ModelAndView saveLiked(@Valid Recipe recipe, BindingResult bindingResult) {
        ModelAndView res;
        if (bindingResult.hasErrors()) {
            res = createEditModelAndView(recipe);
        } else {
            try {
                Assert.notNull(recipe);
                recipeService.save(recipe);
                res = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                Assert.notNull(recipe);
                res = createEditModelAndView(recipe, "item.commit.error");
            }
        }
        return res;
    }


    //DISKLIKE (DELETE) ---------------
    @RequestMapping(value = "recipe/like/dislike", method = RequestMethod.POST, params = "delete")
    public ModelAndView dislike(Recipe recipe, BindingResult bindingResult) {
        ModelAndView result;
        try {
            userService.rateRecipeWithDislike(recipe);
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
        ModelAndView res;
        Integer like = recipe.getLikesNumber();
        res = new ModelAndView("recipe/like");
        res.addObject("recipe", recipe);
        res.addObject("likesNumber", like);
        res.addObject("message", message);
        return res;
    }
}
