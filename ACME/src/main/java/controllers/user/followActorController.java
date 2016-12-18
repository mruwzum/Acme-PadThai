package controllers.user;

import controllers.AbstractController;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.OthersService;

import javax.validation.Valid;

/**
 * Created by mruwzum on 15/12/16.
 */
@Controller
@RequestMapping("user")
public class followActorController extends AbstractController {

    @Autowired
    private ActorService actorService;
    @Autowired
    private OthersService othersService;


    public followActorController() {
        super();
    }




}
