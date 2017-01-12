package controllers.actor;

import com.sun.javafx.sg.PGShape;
import controllers.AbstractController;
import domain.Comment;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.CommentService;
import services.OthersService;
import services.RecipeService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public ModelAndView newComment(@RequestParam int id){
        ModelAndView res;
        Recipe recipe = recipeService.findOne(id);
        Comment c = commentService.create();
        c.setTitle("GENERIC");
        c.setText("GENERIC");
        c.setNumberOfStars(3);
        Comment saved = commentService.save(c);
        List<Comment> comments = new ArrayList<>(recipe.getComments());
        comments.add(saved);
        recipe.setComments(comments);
        res = new ModelAndView("comment/write");
        res.addObject("comment", saved);
        return res;
    }


    @RequestMapping(value = "/comment/write", method = RequestMethod.GET)
    public ModelAndView write(@RequestParam String title, String text, Integer numberOfStars, int recipeID){
        ModelAndView res;
        Recipe recipe = recipeService.findOne(recipeID);
        Collection<Comment> comments = recipe.getComments();
        Comment comment = commentService.create();
        comment.setTitle(title);
        comment.setText(text);
        comment.setNumberOfStars(numberOfStars);
        Comment saved = commentService.save(comment);
        comments.add(saved);
        recipe.setComments(comments);
        res =  new ModelAndView("redirect:http://localhost:8080/");
        return res;
    }









}
