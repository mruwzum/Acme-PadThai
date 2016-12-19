package controllers.actor;

import controllers.AbstractController;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.OthersService;
import services.RecipeService;
import services.UserService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 15/12/16.
 */
@Controller
@RequestMapping("actor")
public class likeRecipeController extends AbstractController {

    @Autowired
    private UserService userService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private OthersService othersService;

    //LIST ----------


    public likeRecipeController() {
        super();
    }



    //(LIKE)----------------
    @RequestMapping(value = "/recipe/like", method = RequestMethod.GET)
    public ModelAndView likeRecipe(@RequestParam int recipeID) {
        ModelAndView res;
        Recipe recipe = recipeService.findOne(recipeID);
        othersService.findByPrincipal().getLikes().add(recipe);
        res = new ModelAndView("redirect:http://localhost:8080/user/recipes/list/my.do");
        return res;
    }




    //(DELETE) ---------------
    @RequestMapping(value = "/recipe/dislike", method = RequestMethod.GET)
    public ModelAndView dislikeRecipe(@RequestParam int recipeID) {
        ModelAndView res;
        Recipe recipe = recipeService.findOne(recipeID);
        othersService.findByPrincipal().getLikes().remove(recipe);
        res = new ModelAndView("redirect:http:://localhost:8080/user/recipes/list/my.do");
        return res;
    }

}
