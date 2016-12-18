package controllers.actor;

import com.sun.javafx.sg.PGShape;
import controllers.AbstractController;
import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.CommentService;
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


    @RequestMapping(value = "/comment/write")
    public ModelAndView write(){
        ModelAndView res;
        Comment comment = commentService.create();
        res = new ModelAndView("comment/write");
        res.addObject("comment",comment);
        return res;
    }









}
