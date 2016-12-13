package controllers.cook;

import controllers.AbstractController;
import domain.Cook;
import domain.Folder;
import domain.Message;
import domain.SocialIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.CookService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 13/12/16.
 */
@Controller
@RequestMapping("cook")
public class cookRegistrationController extends AbstractController {


    @Autowired
    private CookService cookService;
    @Autowired
    private ActorService actorService;

    public cookRegistrationController() {
        super();
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST, params = "save")
    public ModelAndView saveRegistrationForm(@Valid @ModelAttribute Cook cook, BindingResult bindingResult) {
        ModelAndView result;
        actorService.registerAsCook2(cook);
        result = new ModelAndView("cook/list");
        result.addObject("cook", cook);

        return result;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView cooks() {
        ModelAndView result;
        Collection<Cook> aux = cookService.findAll();
        result = new ModelAndView("cook/list");
        result.addObject("cook", aux);
        result.addObject("requestURI", "cook/list.do");
        return result;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signupasCook() {
        ModelAndView result;
        Cook cook = cookService.create();
        result = createEditModelAndView(cook);
        return result;

    }

    //ANCILLARY METHODS

    protected ModelAndView createEditModelAndView(Cook cook) {
        ModelAndView result;

        result = createEditModelAndView(cook, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Cook cook, String message) {
        ModelAndView result;
        String name = cook.getName();
        String surname = cook.getSurname();
        String emailAddress = cook.getEmailAddress();
        String phone = cook.getPhone();
        String postalAddress = cook.getPostalAddress();
        SocialIdentity socialIdentity = cook.getSocialIdentity();
        Collection<Message> messages = cook.getMessage();
        Collection<Folder> folders = cook.getFolders();

        result = new ModelAndView("security/registrationCook");
        result.addObject("cook", cook);
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
