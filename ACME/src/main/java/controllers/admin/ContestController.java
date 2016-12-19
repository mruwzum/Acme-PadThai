package controllers.admin;

import controllers.AbstractController;
import domain.Contest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ContestService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 19/12/16.
 */
@Controller
@RequestMapping("admin")
public class ContestController extends AbstractController {
    public ContestController(){
        super();
    }
    @Autowired
    private ContestService contestService;


    @RequestMapping(value = "/contest/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Contest> aux = contestService.findAll();
        result = new ModelAndView("contest/list");
        result.addObject("contest", aux);
        result.addObject("requestURI", "contest/list.do");
        return result;
    }

    @RequestMapping(value = "/contest/edit", method = RequestMethod.GET)
    public ModelAndView editCategorie(@RequestParam int contestID) {
        ModelAndView result;
        Contest contest = contestService.findOne(contestID);
        Assert.notNull(contest);
        //TODO hay que ver xq la fecha no se cambia bien
        result = createEditModelAndView(contest);
        return result;
    }

    @RequestMapping(value = "/contest/save", method = RequestMethod.POST, params = "save")
    public ModelAndView saveCategorie(@Valid Contest contest, BindingResult binding) {
        ModelAndView result;
        contestService.save(contest);
        result = this.list();
        return result;
    }
    @RequestMapping(value = "/contest/create", method = RequestMethod.GET)
    public ModelAndView createCategorie() {
        ModelAndView r;
        Contest m;
        m = contestService.create();
        r = createEditModelAndView(m);
        return r;
    }

    @RequestMapping(value = "/contest/delete", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Contest contest, BindingResult binding) {
        ModelAndView result;

        try {
            contestService.delete(contest);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(contest, "contest.commit.error");
        }

        return result;
    }

    protected ModelAndView createEditModelAndView(Contest contest) {
        ModelAndView result;

        result = createEditModelAndView(contest, null);

        return result;
    }
    protected ModelAndView createEditModelAndView(Contest contest, String message) {
        ModelAndView result;
        result = new ModelAndView("contest/edit");
        result.addObject("contest", contest);
        result.addObject("title",contest.getTitle());
        result.addObject("oppeningDate",contest.getOppeningDate().toString());
        result.addObject("closingDate",contest.getClosingDate().toString());
        return result;
    }
}
