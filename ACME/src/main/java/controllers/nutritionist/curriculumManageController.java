package controllers.nutritionist;

import controllers.AbstractController;
import domain.Curricula;
import domain.Nutritionist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CurriculaService;
import services.NutritionistService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by hector on 14/12/16.
 */
@Controller
@RequestMapping("nutritionist")
public class curriculumManageController extends AbstractController {

    @Autowired
    private NutritionistService NutritionistService;
    @Autowired
    private CurriculaService curriculaService;

    public curriculumManageController() {
        super();
    }

    @RequestMapping(value = "/curricula/list")
    public ModelAndView list(@RequestParam Nutritionist nutritionist) {
        ModelAndView result;
        Curricula aux = NutritionistService.getCurricula(nutritionist);
        result = new ModelAndView("curricula/list");
        result.addObject("curricula", aux);
        result.addObject("requestURI", "nutritionist/curricula/list.do");
        return result;


    }


    @RequestMapping(value = "/curricula/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int curriculaID) {
        ModelAndView result;
        Curricula curricula = curriculaService.findOne(curriculaID);
        Assert.notNull(curricula);
        result = createEditModelAndView(curricula);
        return result;
    }

    @RequestMapping(value = "/curricula/edit/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Curricula curricula, BindingResult binding, @RequestParam Nutritionist nutritionist) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(curricula);
        } else {
            try {
            	curriculaService.save(curricula);
                result = this.list(nutritionist);
            } catch (Throwable oops) {
                result = createEditModelAndView(curricula, "campaing.commit.error");
            }
        }

        return result;
    }

    @RequestMapping(value = "/curricula/edit/delete", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Curricula curricula, BindingResult binding) {
        ModelAndView result;

        try {
        	curriculaService.delete(curricula);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(curricula, "curricula.commit.error");
        }

        return result;
    }

    protected ModelAndView createEditModelAndView(Curricula curricula) {
        ModelAndView result;

        result = createEditModelAndView(curricula, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Curricula curricula, String message) {
        ModelAndView result;
        String photo = curricula.getPhoto().toString();
        String educationSection = curricula.getEducationSection().toString();
        String experienceSection = curricula.getExperienceSection().toString();
        String hobbiesSection = curricula.getHobbiesSection().toString();
        Collection<String> referencias = curricula.getReferencias();

        result = new ModelAndView("curricula/edit");
        result.addObject("curricula", curricula);
        result.addObject("photo", photo);
        result.addObject("educationSection", educationSection);
        result.addObject("experienceSection", experienceSection);
        result.addObject("hobbiesSection", hobbiesSection);
        result.addObject("referencias", referencias);

        return result;


    }
}
