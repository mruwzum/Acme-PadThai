package controllers.user;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 12/12/16.
 */

@Controller
@RequestMapping("user")
public class userRegistrationController extends AbstractController {


    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private MasterClassService masterClassService;
    @Autowired
    private NutritionistService nutritionistService;
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private ActorService actorService;


    public userRegistrationController() {
        super();
    }


    @RequestMapping(value = "/user/registration", method = RequestMethod.POST, params = "save")
    public ModelAndView saveRegistrationForm(@Valid User actor, BindingResult bindingResult) {
        ModelAndView result;
        actorService.registerAsUser2(actor);
        result = new ModelAndView("user/list");
        result.addObject("user", actor);


        //  if (bindingResult.hasErrors()) {
//            result = createEditModelAndView(actor);
//        }else{
//            try{
//                userService.save(actor);
//                result = new ModelAndView("redirect:users.do");
//            } catch (Throwable oops){
//
//                result = createEditModelAndView(actor, "request.commit.error");
//            }
//        }
        return result;
    }


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signupasUser() {
        ModelAndView result;
        User user = userService.create();
        result = createEditModelAndView(user);
        return result;
    }


    //ANCILLARY METHODS

    protected ModelAndView createEditModelAndView(User a) {
        ModelAndView result;

        result = createEditModelAndView(a, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(User a, String message) {
        ModelAndView result;
        String name = a.getName();
        String surname = a.getSurname();
        String emailAddress = a.getEmailAddress();
        String phone = a.getPhone();
        String postalAddress = a.getPostalAddress();
        SocialIdentity socialIdentity = a.getSocialIdentity();
        Collection<Message> messages = a.getMessage();
        Collection<Folder> folders = a.getFolders();

        result = new ModelAndView("security/registration");
        result.addObject("user", a);
        result.addObject("nane", name);
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



