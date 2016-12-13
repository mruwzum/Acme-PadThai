package controllers.nutritionist;

import controllers.AbstractController;
import domain.Folder;
import domain.Message;
import domain.Nutritionist;
import domain.SocialIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.NutritionistService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 12/12/16.
 */
@Controller
@RequestMapping("nutritionist")
public class nutritionistRegistrationController extends AbstractController {


    @Autowired
    private NutritionistService nutritionistService;
    @Autowired
    private ActorService actorService;


    public nutritionistRegistrationController() {
        super();
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST, params = "save")
    public ModelAndView saveRegistrationForm(@Valid @ModelAttribute Nutritionist nutritionist, BindingResult bindingResult) {
        ModelAndView result;
        actorService.registerAsNutritionist2(nutritionist);
        result = new ModelAndView("nutritionist/list");
        result.addObject("nutritionist", nutritionist);


        return result;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView nutritionists() {
        ModelAndView result;
        Collection<Nutritionist> aux = nutritionistService.findAll();
        result = new ModelAndView("nutritionist/list");
        result.addObject("nutritionist", aux);
        result.addObject("requestURI", "nutritionist/list.do");
        return result;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signupasNutritionist() {
        ModelAndView result;
        Nutritionist nutritionist = nutritionistService.create();
        result = createEditModelAndView(nutritionist);
        return result;

    }

    //ANCILLARY METHODS

    protected ModelAndView createEditModelAndView(Nutritionist a) {
        ModelAndView result;

        result = createEditModelAndView(a, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Nutritionist nutritionist, String message) {
        ModelAndView result;
        String name = nutritionist.getName();
        String surname = nutritionist.getSurname();
        String emailAddress = nutritionist.getEmailAddress();
        String phone = nutritionist.getPhone();
        String postalAddress = nutritionist.getPostalAddress();
        SocialIdentity socialIdentity = nutritionist.getSocialIdentity();
        Collection<Message> messages = nutritionist.getMessage();
        Collection<Folder> folders = nutritionist.getFolders();

        result = new ModelAndView("security/registrationNutri");
        result.addObject("nutritionist", nutritionist);
        result.addObject("name", name);
        result.addObject("surname", surname);
        result.addObject("emailAddress", emailAddress);
        result.addObject("phone", phone);
        result.addObject("postalAddress", postalAddress);
        result.addObject("socialIdentity", socialIdentity);
        result.addObject("messages", messages);
        result.addObject("folders", folders);

        return result;


    }


}
