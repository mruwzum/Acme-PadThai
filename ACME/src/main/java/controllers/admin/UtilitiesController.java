package controllers.admin;

import domain.Campaing;
import domain.Contest;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.AdminService;
import services.CampaingService;
import services.ContestService;

import java.util.*;

/**
 * Created by daviddelatorre on 19/12/16.
 */
@Controller
@RequestMapping("admin")
public class UtilitiesController {

    public UtilitiesController(){
        super();
    }

    @Autowired
    private ContestService contestService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/computewinners")
    public ModelAndView computeWinners(){
        ModelAndView res;
        List<Contest> contests = new ArrayList<>(contestService.findAll());
        Map<Date, Contest> map = new HashMap<>();
        for(Contest c: contests){
            map.put(c.getClosingDate(),c);
        }
        List<Date> dates = new ArrayList<>(map.keySet());
        Date nearestDate = getNearestDate(dates, new Date(System.currentTimeMillis()-100));
        Contest c = map.get(nearestDate);
        adminService.computeWinners(c);
        Collection<Recipe> winners = c.getWinners();
        res = new ModelAndView("contest/list");
        res.addObject("contest", winners);
        return res ;
    }



    private static Date getNearestDate(List<Date> dates, Date currentDate) {
        long minDiff = -1, currentTime = currentDate.getTime();
        Date minDate = null;
        for (Date date : dates) {
            long diff = Math.abs(currentTime - date.getTime());
            if ((minDiff == -1) || (diff < minDiff)) {
                minDiff = diff;
                minDate = date;
            }
        }
        return minDate;
    }
}


