package controllers.actor;

import controllers.AbstractController;
import domain.Actor;
import domain.SocialIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;

import java.util.Collection;

/**
 * Created by daviddelatorre on 15/12/16.
 */
@Controller
@RequestMapping("/actor")
public class EditPersonalDataController extends AbstractController {


    public EditPersonalDataController() {
        super();
    }

    @Autowired
    private ActorService actorService;

    @RequestMapping(value = "/userPersonalData")
    public ModelAndView UserEditPersonalData() {
        ModelAndView result;
        Actor a = actorService.findByPrincipal();
        result = new ModelAndView("security/registration");
        result.addObject("user", a);
        createGenericEditModelAndView(a, result);
        return result;
    }

    @RequestMapping(value = "/userPersonalData/save")
    public ModelAndView UserSave(@RequestParam String name, String surname, String emailAddress, String phone,
                                 String postalAddress, String nickname, String socialNet,
                                 String link) {
        ModelAndView res;
        Assert.notNull(link, "link");
        actorService.editPersonalData2(name,surname,emailAddress,phone,postalAddress,nickname,socialNet,link);
        res = UserEditPersonalData();
        return res;
    }

    @RequestMapping(value = "/NutritionistPersonalData")
    public ModelAndView NutriEditPersonalData() {
        ModelAndView result;
        Actor a = actorService.findByPrincipal();
        result = new ModelAndView("security/registrationNutri");
        result.addObject("nutritionist", a);
        createGenericEditModelAndView(a, result);
        return result;
    }

    @RequestMapping(value = "/SponsorPersonalData")
    public ModelAndView SponsorEditPersonalData() {
        ModelAndView result;
        Actor a = actorService.findByPrincipal();
        result = new ModelAndView("security/registrationSponsor");
        result.addObject("sponsor", a);
        createGenericEditModelAndView(a, result);
        return result;
    }

    @RequestMapping(value = "/CookPersonalData")
    public ModelAndView CookEditPersonalData() {
        ModelAndView result;
        Actor a = actorService.findByPrincipal();
        result = new ModelAndView("security/registrationCook");
        result.addObject("cook", a);
        createGenericEditModelAndView(a, result);
        return result;
    }


    protected void createGenericEditModelAndView(Actor actor, ModelAndView modelAndView) {

        createGenericEditModelAndView(actor, null, modelAndView);

    }

    protected void createGenericEditModelAndView(Actor actor, String message, ModelAndView modelAndView) {
        String name = actor.getName();
        String surname = actor.getSurname();
        String emailAddress = actor.getEmailAddress();
        String phone = actor.getPhone();
        String postalAddress = actor.getPostalAddress();
        SocialIdentity socialIdentity = actor.getSocialIdentity();


        modelAndView.addObject("name", name);
        modelAndView.addObject("surname", surname);
        modelAndView.addObject("emailAddress", emailAddress);
        modelAndView.addObject("phone", phone);
        modelAndView.addObject("postalAddress", postalAddress);
        modelAndView.addObject("socialIdentity", socialIdentity);
        modelAndView.addObject("message", message);


    }
}
