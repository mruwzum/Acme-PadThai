package controllers.admin;

import controllers.AbstractController;
import domain.Contest;
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
import javax.persistence.criteria.CriteriaBuilder;
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

    User q4 = adminService.getUserWhoAuthoredMoreRecipes();

    Collection<Integer> q5 = adminService.minimumNumberOfRecipesQualifiedForAContest();
    Collection<Integer> q6 = adminService.averageNumberOfRecipesQualifiedForAContest();
    Collection<Integer> q7 = adminService.maximumNumberOfRecipesQualifiedForAContest();

    Collection<Contest> q8 = adminService.contestForWhichMoreRecipesHasQualified();

    Double q9 = adminService.averageOfStepsPerRecipe();
    Double q10 = adminService.standartDeviationOfNumberOfStepsPerRecipe();

    Double q11 = adminService.averageOfIngredientsPerRecipe();
    Double q12 = adminService.standartDeviationOfNumberOfIngredientsPerRecipe();

    Collection<User> q13 = adminService.usersInDescendingPopularity();
    Collection<User> q14 = adminService.usersInDescendingOrderByAverageOfLikesPerRecipe();
        res = new ModelAndView("admin/dashboard");
        res.addObject("q1", q1);
        res.addObject("q2", q2);
        res.addObject("q3", q3);
        res.addObject("q4", q4);
        res.addObject("q5", q5);
        res.addObject("q6", q6);
        res.addObject("q7", q7);
        res.addObject("q8", q8);
        res.addObject("q9", q9);
        res.addObject("q10", q10);
        res.addObject("q11", q11);
        res.addObject("q12", q12);
        res.addObject("q13", q13);
        res.addObject("q14", q14);
        return res;
    }



}
