package controllers.others;

import controllers.AbstractController;
import domain.Comment;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by mruwzum on 14/12/16.
 */
@Controller
@RequestMapping("user")


public class managingRecipesController extends AbstractController {
    // Services ---------------------------------------------------------------


    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private OthersService   othersService;


    // Constructors -----------------------------------------------------------

    public managingRecipesController() {
        super();
    }

    // Listing ----------------------------------------------------------------
    @RequestMapping(value = "/recipes/list", method = RequestMethod.GET)
    public ModelAndView listRecipes() {
        ModelAndView result;

        Collection<Recipe> recipeCollection;
        recipeCollection = userService.getAllRecipes();
        Assert.notEmpty(recipeCollection);
        result = new ModelAndView("recipe/list");
        result.addObject("recipe", recipeCollection);
        result.addObject("requestURI", "user/recipes/list.do");

        return result;
    }
    public Comparator<Recipe> comp = new Comparator<Recipe>() {
        @Override
        public int compare(Recipe o1, Recipe o2) {

            return o1.getCreationDate().compareTo(o2.getCreationDate());
        }
    };

    @RequestMapping(value = "recipes/stream", method = RequestMethod.GET)
     public ModelAndView streamRecipes() {
        ModelAndView result;

        Collection<Recipe> res = new ArrayList<>();
        List<Recipe> aux = new ArrayList<>(recipeService.findAll());
        Collections.sort(aux,comp);
        res.add(aux.get(0));
        res.add(aux.get(1));
        res.add(aux.get(2));
        res.add(aux.get(3));
        res.add(aux.get(4));
        result = new ModelAndView("recipe/stream");
        result.addObject("recipe", res);
        result.addObject("requestURI", "user/recipes/stream.do");

        return result;
    }



    @RequestMapping(value = "recipe/view", method = RequestMethod.GET)
    public ModelAndView viewRecipe(@RequestParam int recipeID) {
        ModelAndView result;
        int likes = 0;
        int dislikes = 0;
        Boolean liked = true;
        Boolean notLiked = false;
        Recipe res = recipeService.findOne(recipeID);
        Comment comment = commentService.create();
        for(Boolean b : res.getRate()){
            if(b){
                likes ++;
            }else{
                dislikes++;
            }
        }
        //TODO esto funciona solo con usuarios registrados, cuando entras sin registrarte te dice que findbyprincipal tiene que ser true, no llega a comprobar si es nulo
        if(othersService.findByPrincipal()!=null){
            liked = othersService.findByPrincipal().getLikes().contains(res);
            notLiked = !othersService.findByPrincipal().getLikes().contains(res);
        }
        result = new ModelAndView("recipe/view");
        result.addObject("titler",res.getTitle());
        result.addObject("summary",res.getSummary());
        result.addObject("creationDate",res.getCreationDate().toString());
        result.addObject("updateDate",res.getUpdateDate().toString());
        result.addObject("categorie",res.getCategorie());
        result.addObject("user",res.getUser().getName());
        result.addObject("idr", res.getId());
        result.addObject("likes", likes);
        result.addObject("dislikes", dislikes);
        result.addObject("comments", res.getComments());
        result.addObject("comment", comment);
        result.addObject("liked", liked);
        result.addObject("notLiked", notLiked);



        return result;
    }
    @RequestMapping(value = "recipes/list/my", method = RequestMethod.GET)
    public ModelAndView listMyRecipes() {
        ModelAndView result;
        Collection<Recipe> allrecipes = recipeService.findAll();
        try {
            Collection<Recipe> myrecipes= userService.getAllRecipes();
            allrecipes.removeAll(myrecipes);
        } catch (Throwable oops) {

        }
       result = new ModelAndView("recipe/list");
       result.addObject("recipe", allrecipes);
        return result;
    }

    //Creation----------------------------------------------------------------

    @RequestMapping(value = "recipes/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Recipe recipe;
        String creationDate = "hola";
        String updateDate = "hola";
        recipe = recipeService.create();
        result = createEditModelAndView(recipe);
        result.addObject("creationDate", creationDate);
        result.addObject("updateDate", creationDate);





        return result;
    }

    // Edition ------------------------------------------------------------------------

    @RequestMapping(value = "recipes/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int id) {
        ModelAndView result;
        Recipe recipe;
        recipe = recipeService.findOne(id);
        Assert.notNull(recipe);
        recipe.setUpdateDate(new Date(System.currentTimeMillis() - 10000));
        result = createEditModelAndView(recipe);
        return result;
    }


    //SAVE ----------------------------------

    @RequestMapping(value = "recipes/edit/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Recipe recipe, BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(recipe);
        } else {
            try {
                recipeService.save(recipe);
                result = new ModelAndView("redirect:http://localhost:8080/user/recipes/list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(recipe, "item.commit.error");
            }
        }
        return result;
    }

    //DELETE ----------------------------------

    @RequestMapping(value = "recipes/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Recipe recipe, BindingResult binding) {
        ModelAndView result;

        try {
            recipeService.delete(recipe);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(recipe, "item.commit.error");
        }
        return result;
    }

    protected ModelAndView createEditModelAndView(Recipe recipe) {

        ModelAndView result;

        result = createEditModelAndView(recipe, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Recipe recipe, String message) {
        ModelAndView result;
        String title = recipe.getTitle();
        String sumary = recipe.getSummary();
        String creationDate;
        if(recipe.getCreationDate() == null){
            creationDate = new Date(System.currentTimeMillis() - 100).toString();
        }else{
            creationDate = recipe.getCreationDate().toString();
        }
        String updateDate;
        if(recipe.getUpdateDate() == null){
            updateDate = new Date(System.currentTimeMillis() - 100).toString();
        }else{
            updateDate = recipe.getUpdateDate().toString();
        }
        Collection<String> pictures = recipe.getPictures();
        result = new ModelAndView("recipe/edit");

        result.addObject("recipe", recipe);
        result.addObject("title", title);
        result.addObject("summary", sumary);
        result.addObject("creationDate", creationDate);
        result.addObject("updateDate", updateDate);
        result.addObject("pictures", pictures);
        result.addObject("message", message);

        return result;
    }


}


