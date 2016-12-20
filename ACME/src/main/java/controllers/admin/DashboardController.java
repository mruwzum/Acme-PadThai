package controllers.admin;

import controllers.AbstractController;
import domain.Recipe;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.AdminService;
import services.RecipeService;
import services.UserService;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mruwzum on 19/12/16.
 */
@Controller
@RequestMapping("admin")
public class DashboardController extends AbstractController {

    public DashboardController(){super();}

    @Autowired
    private UserService userService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "/dashboard")
    public ModelAndView dashboard(){
        ModelAndView res;
    Double q1 = adminService.minimumRecipesOfUser();
    Double q2 = adminService.averageRecipesOfUser();
    Double q3 = adminService.maximumRecipesOfUser();




        res = new ModelAndView("admin/dashboard");
        res.addObject("q1", q1);
        res.addObject("q2", q2);
        res.addObject("q3", q3);
        return res;
    }



}
