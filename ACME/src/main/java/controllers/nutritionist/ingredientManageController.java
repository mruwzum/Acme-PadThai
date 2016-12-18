package controllers.nutritionist;

import controllers.AbstractController;
import domain.Ingredient;
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
import services.PropertyService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by hector on 14/12/16.
 */
@Controller
@RequestMapping("nutritionist")
public class ingredientManageController extends AbstractController {

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private NutritionistService NutritionistService;

    public ingredientManageController() {
        super();
    }

    @RequestMapping(value = "/ingredient/list")
    public ModelAndView list() {
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

    @RequestMapping(value = "/ingredient/newIngredient", method = RequestMethod.GET)
    public ModelAndView newIngredient() {
        ModelAndView result;
        result = new ModelAndView("ingredient/edit");
        Ingredient ingredient = ingredientService.create();
        result.addObject("ingredient",ingredient);
        return result;
    }

    @RequestMapping(value = "/ingredient/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Ingredient ingredient, BindingResult binding) {
        ModelAndView result;
        ingredientService.save(ingredient);
        result = this.list();

        /*
        if (binding.hasErrors()) {
            result = createEditModelAndView(ingredient);
        } else {
            try {
            	ingredientService.save(ingredient);
                result = this.list();
            } catch (Throwable oops) {
                result = createEditModelAndView(ingredient, "ingredient.commit.error");
            }
        }*/

        return result;
    }

    @RequestMapping(value = "/ingredient/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam int ingredientID) {
        ModelAndView result;
        Ingredient ingredient = ingredientService.findOne(ingredientID);
        ingredientService.delete(ingredient);
        result = new ModelAndView("redirect:list.do");
        /*try {
        } catch (Throwable oops) {
            result = createEditModelAndView(ingredient, "ingredient.commit.error");
        }*/

        return result;
    }

    @RequestMapping(value = "/ingredient/deleteProperty", method = RequestMethod.GET)
    public ModelAndView deleteProperty(@RequestParam int id,int propertyID) {
        ModelAndView result;
        Ingredient ing = ingredientService.findOne(id);
        Property property = propertyService.findOne(propertyID);
        ingredientService.deleteProperty(ing,property);
        result = new ModelAndView("redirect:list.do");
        /*try {
            ingredientService.delete(ingredient);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(ingredient, "ingredient.commit.error");
        }*/

        return result;
    }

    @RequestMapping(value = "/ingredient/addProperty", method = RequestMethod.GET)
    public ModelAndView addProperty(@RequestParam int id,int propertyID) {
        ModelAndView result;
        Ingredient ing = ingredientService.findOne(id);
        Property property = propertyService.findOne(propertyID);
        ingredientService.addProperty(ing,property);
        result = new ModelAndView("redirect:list.do");
        /*try {
            ingredientService.delete(ingredient);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(ingredient, "ingredient.commit.error");
        }*/

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
        Collection<Property> properties = propertyService.findAll();
        properties.removeAll(property);

        result = new ModelAndView("ingredient/edit");
        result.addObject("ingredient", ingredient);
        result.addObject("name", name);
        result.addObject("description", description);
        result.addObject("picture", picture);
        result.addObject("property", property);
        result.addObject("properties", properties);
        result.addObject("id", ingredient.getId());

        return result;


    }


}
