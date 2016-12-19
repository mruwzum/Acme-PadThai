package controllers.admin;

import controllers.AbstractController;
import domain.Categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CategorieService;
import services.RecipeService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 19/12/16.
 */
@Controller
@RequestMapping("admin")
public class CategorieController extends AbstractController {

    public CategorieController(){super();}

    @Autowired
    private CategorieService categorieService;
    @Autowired
    private RecipeService recipeService;


    @RequestMapping(value = "/categorie/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Categorie> aux = categorieService.findAll();
        result = new ModelAndView("categorie/list");
        result.addObject("categorie", aux);
        result.addObject("requestURI", "categorie/list.do");
        return result;


    }

    @RequestMapping(value = "/categorie/delete", method = RequestMethod.GET, params = "delete")
    public ModelAndView deleteProperty(@RequestParam int categorieID) {
        ModelAndView result;
        Categorie categorie = categorieService.findOne(categorieID);
        categorieService.delete(categorie);
        result = new ModelAndView("redirect:list.do");
        return result;
        //TODO peta
    }

    @RequestMapping(value = "/categorie/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int categorieID) {
        ModelAndView result;
        Categorie categorie = categorieService.findOne(categorieID);
        Assert.notNull(categorie);
        result = createEditModelAndView(categorie);
        return result;
    }

    @RequestMapping(value = "/categorie/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Categorie categorie, BindingResult binding) {
        ModelAndView result;
        categorieService.save(categorie);
        result = this.list();
        return result;
    }
    @RequestMapping(value = "/categorie/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView r;
        Categorie m;
        m = categorieService.create();
        r = createEditModelAndView(m);
        return r;
    }

//    @RequestMapping(value = "/categorie/delete", method = RequestMethod.GET, params = "delete")
//    public ModelAndView delete(Categorie categorie, BindingResult binding) {
//        ModelAndView result;
//
//        try {
//            categorieService.delete(categorie);
//            result = new ModelAndView("redirect:list.do");
//        } catch (Throwable oops) {
//            result = createEditModelAndView(categorie, "categorie.commit.error");
//        }
//
//        return result;
//    }

    protected ModelAndView createEditModelAndView(Categorie categorie) {
        ModelAndView result;

        result = createEditModelAndView(categorie, null);

        return result;
    }
    protected ModelAndView createEditModelAndView(Categorie categorie, String message) {
        ModelAndView result;
        result = new ModelAndView("categorie/edit");
        result.addObject("categorie", categorie);
        result.addObject("name",categorie.getName());
       result.addObject("description",categorie.getDescription());
       result.addObject("picture",categorie.getPicture());



        return result;


    }
}
