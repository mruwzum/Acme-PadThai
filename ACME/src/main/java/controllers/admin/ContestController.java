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
import java.util.Date;

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
    public ModelAndView edit(@RequestParam int contestID) {
        ModelAndView result;
        Contest contest = contestService.findOne(contestID);
        Assert.notNull(contest);
        //TODO hay que ver xq la fecha no se cambia bien
        result = createEditModelAndView(contest);
        return result;
    }

    @RequestMapping(value = "/contest/save", method = RequestMethod.POST, params = "save")
    public ModelAndView saveContest(@Valid Contest contest, BindingResult binding) {
        ModelAndView result;
        contestService.save(contest);
        result = this.list();
        return result;
    }
    @RequestMapping(value = "/contest/create", method = RequestMethod.GET)
    public ModelAndView createContest() {
        ModelAndView r;
        Contest m;
        m = contestService.create();
        m.setTitle("GENERIC");
        m.setOppeningDate(new Date(System.currentTimeMillis() -100));
        m.setClosingDate(new Date(System.currentTimeMillis() -100));
     //TODO peta
        r = createEditModelAndView(m);
        return r;
    }
    @RequestMapping(value = "/contest/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam int contestID) {
        ModelAndView result;
        Contest contest = contestService.findOne(contestID);
        contestService.delete(contest);
        result = new ModelAndView("redirect:list.do");
        return result;
        //TODO peta
    }

//    @RequestMapping(value = "/contest/delete", method = RequestMethod.GET, params = "delete")
//    public ModelAndView delete(Contest contest, BindingResult binding) {
//        ModelAndView result;
//
//        try {
//            contestService.delete(contest);
//            result = new ModelAndView("redirect:list.do");
//        } catch (Throwable oops) {
//            result = createEditModelAndView(contest, "contest.commit.error");
//        }
//
//        return result;
//    }

    protected ModelAndView createEditModelAndView(Contest contest) {
        ModelAndView result;

        result = createEditModelAndView(contest, null);

        return result;
    }
    protected ModelAndView createEditModelAndView(Contest contest, String message) {
        ModelAndView result;
        String oppeningDate = contest.getOppeningDate().toString();
        String closingDate = contest.getClosingDate().toString();
        String title = contest.getTitle();
        result = new ModelAndView("contest/edit");
        result.addObject("contest", contest);
        result.addObject("title",title);
        result.addObject("oppeningDate",oppeningDate);
        result.addObject("closingDate",closingDate);
        return result;
    }
}
