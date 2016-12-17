package controllers.nutritionist;

import controllers.AbstractController;
import domain.Ingredient;
import domain.Nutritionist;
import domain.Property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.IngredientService;
import services.NutritionistService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by hector on 14/12/16.
 */
@Controller
@RequestMapping("nutritionist")
public class ingredientManageController extends AbstractController {

    @Autowired
    private NutritionistService NutritionistService;
    @Autowired
    private IngredientService ingredientService;

    public ingredientManageController() {
        super();
    }

    @RequestMapping(value = "/ingredient/list")
    public ModelAndView list(@RequestParam Nutritionist nutritionist) {
        ModelAndView result;
        Collection<Ingredient> aux = NutritionistService.getIngredients();
        result = new ModelAndView("ingredient/list");
        result.addObject("ingredient", aux);
        result.addObject("requestURI", "nutritionist/ingredient/list.do");
        return result;


    }


    @RequestMapping(value = "/ingredient/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int ingredientID) {
        ModelAndView result;
        Ingredient ingredient = ingredientService.findOne(ingredientID);
        Assert.notNull(ingredient);
        result = createEditModelAndView(ingredient);
        return result;
    }

    @RequestMapping(value = "/ingredient/edit/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Ingredient ingredient, BindingResult binding, @RequestParam Nutritionist nutritionist) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(ingredient);
        } else {
            try {
                Assert.notNull(ingredient);
            	ingredientService.save(ingredient);
                result = this.list(nutritionist);
            } catch (Throwable oops) {
                result = createEditModelAndView(ingredient, "campaing.commit.error");
            }
        }

        return result;
    }

    @RequestMapping(value = "/ingredient/edit/delete", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Ingredient ingredient, BindingResult binding) {
        ModelAndView result;

        try {
        	ingredientService.delete(ingredient);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(ingredient, "ingredient.commit.error");
        }

        return result;
    }

    protected ModelAndView createEditModelAndView(Ingredient ingredient) {
        ModelAndView result;

        result = createEditModelAndView(ingredient, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Ingredient ingredient, String message) {
        ModelAndView result;
        String name = ingredient.getName();
        String description = ingredient.getDescription();
        String picture = ingredient.getPicture();
        Collection<Property> property = ingredient.getProperty();

        result = new ModelAndView("ingredient/edit");
        result.addObject("ingredient", ingredient);
        result.addObject("name", name);
        result.addObject("description", description);
        result.addObject("picture", picture);
        result.addObject("property", property);

        return result;


    }
}
