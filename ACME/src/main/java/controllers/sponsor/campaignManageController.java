package controllers.sponsor;

import controllers.AbstractController;
import domain.Campaing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CampaingService;
import services.SponsorService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by daviddelatorre on 14/12/16.
 */
@Controller
@RequestMapping("sponsor")
public class campaignManageController extends AbstractController {

    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private CampaingService campaingService;

    public campaignManageController() {
        super();
    }

    @RequestMapping(value = "/campaing/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Campaing> aux = sponsorService.getAllCampaings();
        result = new ModelAndView("campaign/list");
        result.addObject("campaign", aux);
        result.addObject("requestURI", "sponsor/campaing/list.do");
        return result;


    }


    @RequestMapping(value = "/campaing/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int recipeID) {
        ModelAndView result;
        Campaing campaing = campaingService.findOne(recipeID);
        Assert.notNull(campaing);
        result = createEditModelAndView(campaing);
        return result;
    }

    @RequestMapping(value = "/campaing/edit/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Campaing campaing, BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(campaing);
        } else {
            try {
                campaingService.save(campaing);
                result = this.list();
            } catch (Throwable oops) {
                result = createEditModelAndView(campaing, "campaing.commit.error");
            }
        }

        return result;
    }

    @RequestMapping(value = "/campaing/edit/delete", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Campaing campaing, BindingResult binding) {
        ModelAndView result;

        try {
            campaingService.delete(campaing);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(campaing, "campaign.commit.error");
        }

        return result;
    }

    protected ModelAndView createEditModelAndView(Campaing campaing) {
        ModelAndView result;

        result = createEditModelAndView(campaing, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Campaing campaing, String message) {
        ModelAndView result;
        String startDate = campaing.getStartDate().toString();
        String endDate = campaing.getEndDate().toString();
        Integer numberOfBanenrs = campaing.getNumberOfBanners();
        Integer maximum = campaing.getMaximumDisplayed();

        result = new ModelAndView("campaign/edit");
        result.addObject("campaign", campaing);
        result.addObject("startDate", startDate);
        result.addObject("endDate", endDate);
        result.addObject("numberOfBanners", numberOfBanenrs);
        result.addObject("maximumDisplayed", maximum);

        return result;


    }
}
