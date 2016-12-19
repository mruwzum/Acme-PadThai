package controllers.actor;

import com.sun.javafx.sg.PGShape;
import controllers.AbstractController;
import domain.Comment;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CommentService;
import services.OthersService;
import services.RecipeService;

/**
 * Created by mruwzum on 18/12/16.
 */
@Controller
@RequestMapping("actor")
public class CommentController extends AbstractController {

    public CommentController(){super();}

    @Autowired
    private CommentService commentService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private OthersService othersService;


    @RequestMapping(value = "/comment/write")
    public ModelAndView write(Comment comment){
        ModelAndView res;
        Comment saved = commentService.save(comment);
        Recipe recipe = recipeService.findOne(182);
        recipe.getComments().add(saved);
        res = new ModelAndView("redirect:http://localhost:8080/user/recipe/view.do");
        return res;
    }









}
