package controllers.admin;

import controllers.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mruwzum on 19/12/16.
 */
@Controller
@RequestMapping("admin")
public class DashboardController extends AbstractController {

    public DashboardController(){super();}


    @RequestMapping(value = "/dashboard")
    public ModelAndView dashboard(){
        ModelAndView res;





        res = new ModelAndView("admin/dashboard");



        return res;
    }


}
