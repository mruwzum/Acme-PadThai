package controllers.cook;

import controllers.AbstractController;
import domain.MasterClass;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CookService;
import services.MasterClassService;
import services.UserService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 14/12/16.
 */
@Controller
@RequestMapping("cook")
public class managingMasterClassesController extends AbstractController {

    @Autowired
    private MasterClassService masterClassService;
    @Autowired
    private UserService userService;
    @Autowired
    private CookService cookService;


    public managingMasterClassesController() {
        super();
    }


    //LISTING  -----------------------------------------------

    @RequestMapping(value = "masterClasses/list", method = RequestMethod.GET)
    public ModelAndView listMasterClasses() {
        ModelAndView result;
        Collection<MasterClass> masterClasses;
        User u = userService.findByPrincipal();
        masterClasses = cookService.getMyMasterClasses();
        result = new ModelAndView("masterClasses/List");
        result.addObject("masterClass", masterClasses);
        result.addObject("requestURI", "cook/masterClasses/list.do");

        return result;
    }

    //CREATION --------------------------------------------------

    @RequestMapping(value = "masterClasses/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView r;
        MasterClass m;
        m = masterClassService.create();
        r = createEditModelAndView(m);
        return r;
    }

    //EDITION -----------------------------

    @RequestMapping(value = "masterClasses/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int id) {
        ModelAndView result;
        MasterClass masterClass;
        masterClass = masterClassService.findOne(id);
        Assert.notNull(masterClass);
        result = createEditModelAndView(masterClass);

        return result;
    }


    //SAVE ----------------------------------

    @RequestMapping(value = "masterClasses/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid MasterClass masterClass, BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(masterClass);
        } else {
            try {
                Assert.notNull(masterClass);
                masterClassService.save(masterClass);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(masterClass, "item.commit.error");
            }
        }
        return result;
    }

    //DELETE ----------------------------------

    @RequestMapping(value = "masterClasses/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(MasterClass masterClass, BindingResult binding) {
        ModelAndView result;

        try {
            masterClassService.delete(masterClass);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(masterClass, "item.commit.error");
        }
        return result;
    }

    protected ModelAndView createEditModelAndView(MasterClass masterClass) {

        ModelAndView result;

        result = createEditModelAndView(masterClass, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(MasterClass masterClass, String message) {
        ModelAndView result;

        result = new ModelAndView("mclass/edit");
        result.addObject("masterClass", masterClass);
        result.addObject("message", message);

        return result;
    }


}
