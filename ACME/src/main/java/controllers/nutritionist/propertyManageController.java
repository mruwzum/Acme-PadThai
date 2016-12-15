package controllers.nutritionist;

import controllers.AbstractController;
import domain.Property;
import domain.Nutritionist;
import domain.Quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.PropertyService;
import services.NutritionistService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by hector on 14/12/16.
 */
@Controller
@RequestMapping("nutritionist")
public class propertyManageController extends AbstractController {

    @Autowired
    private NutritionistService NutritionistService;

    public propertyManageController() {
        super();
    }

    @RequestMapping(value = "/property/list")
    public ModelAndView list(@RequestParam Nutritionist nutritionist) {
        ModelAndView result;
        Collection<Property> aux = NutritionistService.getProperties1();
        result = new ModelAndView("property/list");
        result.addObject("property", aux);
        result.addObject("requestURI", "nutritionist/property/list.do");
        return result;


    }


    @RequestMapping(value = "/property/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int propertyID) {
        ModelAndView result;
        Property property = PropertyService.findOne(propertyID);
        Assert.notNull(property);
        result = createEditModelAndView(property);
        return result;
    }

    @RequestMapping(value = "/property/edit/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Property property, BindingResult binding, @RequestParam Nutritionist nutritionist) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(property);
        } else {
            try {
            	PropertyService.save(property);
                result = this.list(nutritionist);
            } catch (Throwable oops) {
                result = createEditModelAndView(property, "campaing.commit.error");
            }
        }

        return result;
    }

    @RequestMapping(value = "/property/edit/delete", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Property property, BindingResult binding) {
        ModelAndView result;

        try {
        	PropertyService.delete(property);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(property, "property.commit.error");
        }

        return result;
    }

    protected ModelAndView createEditModelAndView(Property property) {
        ModelAndView result;

        result = createEditModelAndView(property, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Property property, String message) {
        ModelAndView result;
        String name = property.getName().toString();
        Quantity quantity = property.getQuantity();

        result = new ModelAndView("property/edit");
        result.addObject("property", property);
        result.addObject("name", name);
        result.addObject("quantity", quantity);

        return result;


    }
}
