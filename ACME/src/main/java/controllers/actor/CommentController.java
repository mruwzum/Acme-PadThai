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
import services.ActorService;
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
    @Autowired
    private ActorService actorService;


    @RequestMapping(value = "/comment/new")
    public ModelAndView newComment(){
        ModelAndView res;
        Comment saved = commentService.create();
        res = new ModelAndView("comment/write");
        res.addObject("comment", saved);
        return res;
    }


    @RequestMapping(value = "/comment/write")
    public ModelAndView write(@RequestParam int id, String title, String text, String stars){
        ModelAndView res;

        res = new ModelAndView("mensaje/text");
        res.addObject("id", id);
        res.addObject("title", title);
        res.addObject("text", text);
        res.addObject("stars", stars);
        return res;
    }









}
