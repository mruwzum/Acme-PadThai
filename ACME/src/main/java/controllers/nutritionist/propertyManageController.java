package controllers.nutritionist;

import controllers.AbstractController;
import domain.Property;
import domain.Nutritionist;
import domain.Quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.PropertyService;
import services.NutritionistService;
import services.QuantityService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hector on 14/12/16.
 */
@Controller
@RequestMapping("nutritionist")
public class propertyManageController extends AbstractController {

    @Autowired
    private NutritionistService nutritionistService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private QuantityService quantityService;

    public propertyManageController() {
        super();
    }

    @RequestMapping(value = "/property/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Property> aux = nutritionistService.getProperties1();
        result = new ModelAndView("property/list");
        result.addObject("property", aux);
        result.addObject("requestURI", "nutritionist/property/list.do");
        return result;


    }

    @RequestMapping(value = "/property/newProperty", method = RequestMethod.GET)
    public ModelAndView newProperty() {
        ModelAndView result;
        result = new ModelAndView("property/edit");
        Property property = propertyService.create();
        result.addObject("property",property);
        Collection<Quantity> quantities = quantityService.findAll();
        result.addObject("quantity", quantities);
        return result;
    }

    @RequestMapping(value = "/property/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int propertyID) {
        ModelAndView result;
        Property property = propertyService.findOne(propertyID);
        Assert.notNull(property);
        result = createEditModelAndView(property);
        return result;
    }

    @RequestMapping(value = "/property/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Property property) {
        ModelAndView result;
        propertyService.save(property);
        result = this.list();

        return result;
    }

    @RequestMapping(value = "/property/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam int propertyID) {
        ModelAndView result = new ModelAndView("redirect:list.do");
        Property property = propertyService.findOne(propertyID);
        try {
            propertyService.delete(property);
        }catch (DataIntegrityViolationException e){
            String texto1 =  "You can't delete any property associated to an ingredient / No puede borrar propiedades asociadas a ingredientes";
            result = new ModelAndView("sponsor/text");
            result.addObject("texto1", texto1);
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
        String name = property.getName();
        Quantity quantity = property.getQuantity();

        result = new ModelAndView("property/edit");
        result.addObject("property", property);
        result.addObject("name", name);

        Collection<Quantity> quantities = new ArrayList<Quantity>();
        quantities.add(quantity);
        result.addObject("quantity", quantities);

        return result;


    }
}
