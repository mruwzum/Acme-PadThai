package controllers.user;

import controllers.AbstractController;
import domain.Others;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/follow")
    public ModelAndView follow(@RequestParam int id){
        ModelAndView res;
        Others other = othersService.findOne(id);
        othersService.followOther(other);
        res = new ModelAndView("redirect:http://localhost:8080/");
        return res;

    }

    @RequestMapping(value = "/unfollow")
    public ModelAndView unfollow(@RequestParam int id){
        ModelAndView res;
        Others other = othersService.findOne(id);
        othersService.unfollowOther(other);
        res = new ModelAndView("redirect:http://localhost:8080/");
        return res;

    }


}
