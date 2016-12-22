package controllers.actor;

import controllers.AbstractController;
import domain.Actor;
import domain.LearningMaterial;
import domain.MasterClass;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.MasterClassService;
import services.OthersService;
import services.RecipeService;
import services.UserService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 15/12/16.
 */
@Controller
@RequestMapping("actor")
public class likeRecipeController extends AbstractController {

    @Autowired
    private UserService userService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private OthersService othersService;
    @Autowired
    private MasterClassService masterClassService;




    public likeRecipeController() {
        super();
    }






    @RequestMapping(value = "/masterClass/view")
    public ModelAndView view(@RequestParam int id){
        ModelAndView res;
        MasterClass mc = masterClassService.findOne(id);
        String title = mc.getTitle();
        String des = mc.getDescription();
        String cook = mc.getCook().getName();
        Collection<LearningMaterial> learningMaterials = mc.getMaterial();
        Collection<Actor> registers = mc.getRegisters();

        res = new ModelAndView("masterClass/view");
        res.addObject("masterClass", mc);
        res.addObject("title", title);
        res.addObject("description", des);
        res.addObject("cook", cook);
        res.addObject("materials",learningMaterials.toString());
        res.addObject("registers", registers);  




        return res;
    }


    //(LIKE)----------------
    @RequestMapping(value = "/recipe/like", method = RequestMethod.GET)
    public ModelAndView likeRecipe(@RequestParam int recipeID) {
        ModelAndView res;
        othersService.likeRecipe(recipeService.findOne(recipeID));
        res = new ModelAndView("redirect:http://localhost:8080/user/recipes/list/my.do");
        return res;
    }




    //(DELETE) ---------------
    @RequestMapping(value = "/recipe/dislike", method = RequestMethod.GET)
    public ModelAndView dislikeRecipe(@RequestParam int recipeID) {
        ModelAndView res;
        othersService.dislikeRecipe(recipeService.findOne(recipeID));
        res = new ModelAndView("redirect:http://localhost:8080/user/recipes/list/my.do");
        return res;
    }

}
