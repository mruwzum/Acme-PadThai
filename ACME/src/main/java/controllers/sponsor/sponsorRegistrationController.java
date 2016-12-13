package controllers.sponsor;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.SponsorService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 11/12/16.
 */
@Controller
@RequestMapping("sponsor")
public class sponsorRegistrationController extends AbstractController {

    @Autowired
    private SponsorService sponsorService;


    private sponsorRegistrationController() {
        super();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, params = "save")
    public ModelAndView saveRegistrationForm(@Valid @ModelAttribute Sponsor spon, BindingResult bindingResult) {
        ModelAndView result;

        if (bindingResult.hasErrors()) {
            result = createEditModelAndView(spon);
        } else {
            try {
                sponsorService.save(spon);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {

                result = createEditModelAndView(spon, "request.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signupasSponsor() {
        ModelAndView result;
        Sponsor sponsor = sponsorService.create();
        result = createEditModelAndView(sponsor);
        return result;

    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView sponsorsn() {
        ModelAndView result;
        Collection<Sponsor> aux = sponsorService.findAll();
        result = new ModelAndView("sponsor/list");
        result.addObject("sponsor", aux);
        result.addObject("requestURI", "sponsor/list.do");
        return result;
    }


    //ANCILLARY METHODS

    protected ModelAndView createEditModelAndView(Sponsor a) {
        ModelAndView result;

        result = createEditModelAndView(a, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Sponsor spo, String message) {
        ModelAndView result;
        String name = spo.getName();
        String surname = spo.getSurname();
        String emailAddress = spo.getEmailAddress();
        String phone = spo.getPhone();
        String postalAddress = spo.getPostalAddress();
        SocialIdentity socialIdentity = spo.getSocialIdentity();
        Collection<Message> messages = spo.getMessage();
        Collection<Folder> folders = spo.getFolders();

        result = new ModelAndView("security/registrationSponsor");
        result.addObject("sponsor", spo);
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
