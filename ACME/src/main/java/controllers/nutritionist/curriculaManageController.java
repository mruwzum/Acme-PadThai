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
public class curriculaManageController extends AbstractController {

    @Autowired
    private NutritionistService nutritionistService;
    @Autowired
    private CurriculaService curriculaService;

    public curriculaManageController() {
        super();
    }

    @RequestMapping(value = "/curricula/list")
    public ModelAndView list() {
        ModelAndView result;
        Curricula aux = nutritionistService.findByPrincipal().getCurricula();
        result = new ModelAndView("curricula/list");
        result.addObject("curricula", aux);
        result.addObject("requestURI", "nutritionist/curricula/list.do");
        return result;


    }


    @RequestMapping(value = "/curricula/edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        ModelAndView result;
        Nutritionist nutritionist = nutritionistService.findByPrincipal();
        Curricula curricula = nutritionist.getCurricula();
        if(curricula!=null) {
            result = createEditModelAndView(curricula);
        } else {
            result = createEditModelAndView();
        }
        return result;
    }

    @RequestMapping(value = "/curricula/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Curricula curricula, BindingResult binding) {
        ModelAndView result;
        nutritionistService.findByPrincipal().setCurricula(curriculaService.save(curricula));
        result = this.edit();


        return result;
    }

    @RequestMapping(value = "/curricula/delete", method = RequestMethod.POST, params = "delete")
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
        String photo = curricula.getPhoto();
        String educationSection = curricula.getEducationSection();
        String experienceSection = curricula.getExperienceSection();
        String hobbiesSection = curricula.getHobbiesSection();
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

    protected ModelAndView createEditModelAndView() {
        ModelAndView result;
        Curricula curricula = curriculaService.create();

        result = new ModelAndView("curricula/edit");
        result.addObject("curricula", curricula);

        return result;


    }
}
