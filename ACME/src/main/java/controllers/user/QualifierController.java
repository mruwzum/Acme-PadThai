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
@RequestMapping("user")
public class QualifierController extends AbstractController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;
    public QualifierController() {
        super();
    }

    @RequestMapping(value = "/recipe/qualify", method = RequestMethod.GET)
    public ModelAndView qualify(@RequestParam int id) {
        ModelAndView res;
        Recipe recipe = recipeService.findOne(id);
        userService.qualifyRecipe(recipe);
        res = new ModelAndView("recipe/list");
        res.addObject("recipe", recipe);
        return res;

    }

    @RequestMapping(value = "/recipe/qualify/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Recipe recipe, BindingResult bindingResult) {
        ModelAndView res;
        if (bindingResult.hasErrors()) {
            res = createQualifyModelAndView(recipe);
        } else {
            try {
                recipeService.save(recipe);
                res = createQualifyModelAndView(recipe);
            } catch (Throwable oops) {
                res = createQualifyModelAndView(recipe, "recipe.commit.error");
            }
        }
        return res;
    }

    protected ModelAndView createQualifyModelAndView(Recipe recipe) {
        ModelAndView res;
        res = createQualifyModelAndView(recipe, null);
        return res;
    }

    protected ModelAndView createQualifyModelAndView(Recipe recipe, String message) {
        ModelAndView res;

        Collection<Boolean> rate = recipe.getRate();

        res = new ModelAndView("recipe/qualify");
        res.addObject("recipe", recipe);
        res.addObject("rate", rate);

        return res;
    }
}
