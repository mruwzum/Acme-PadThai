package controllers.admin;

import com.sun.javafx.sg.PGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import java.util.*;

/**
 * Created by daviddelatorre on 19/12/16.
 */
@Controller
@RequestMapping("admin")
public class UtilitiesController extends AbstractController{

    public UtilitiesController(){
        super();
    }

    @Autowired
    private ContestService contestService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CampaingService campaingService;
    @Autowired
    private MasterClassService masterClassService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private SponsorService sponsorService;

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
        res = new ModelAndView("recipe/list");
        res.addObject("recipe", winners);
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

    @RequestMapping(value = "/masterClass/promote")
    public ModelAndView promoteMC(@RequestParam int mcID){
        ModelAndView res = new ModelAndView("redirect:http://localhost:8080/admin/masterClass/list.do");
        MasterClass mc = masterClassService.findOne(mcID);
        try {
            adminService.promoteMasterClas(mc);
        }catch (IllegalArgumentException e){
            res = new ModelAndView("sponsor/text");
            res.addObject("texto1", e.getMessage());
        }

        return res;
    }

    @RequestMapping(value = "/masterClass/demote")
    public ModelAndView demoteMC(@RequestParam int mcID){
        ModelAndView res = new ModelAndView("redirect:http://localhost:8080/admin/masterClass/list.do");
        MasterClass mc = masterClassService.findOne(mcID);
        try {
            adminService.demoteMasterClass(mc);
        }catch (IllegalArgumentException e){
            res = new ModelAndView("sponsor/text");
            res.addObject("texto1", e.getMessage());
        }
        return res;
    }
    @RequestMapping(value = "/masterClass/attend")
    public ModelAndView attendMC(@RequestParam int mcID) {
        ModelAndView res;
        MasterClass mc = masterClassService.findOne(mcID);
        actorService.registerToMasterClass(mc);
        res = new ModelAndView("redirect:http://localhost:8080/admin/masterClass/list.do");
        return res;
    }
    @RequestMapping(value = "/masterClass/attendNutri")
    public ModelAndView attendNutriMC(@RequestParam int mcID){
        ModelAndView res;
        MasterClass mc = masterClassService.findOne(mcID);
        actorService.registerToMasterClassNutri(mc);
        res = new ModelAndView("redirect:http://localhost:8080/admin/masterClass/list.do");
        return res;
    }
    @RequestMapping(value = "/masterClass/attendSpon")
    public ModelAndView attendSponMC(@RequestParam int mcID){
        ModelAndView res;
        MasterClass mc = masterClassService.findOne(mcID);
        actorService.registerToMasterClassSpon(mc);
        res = new ModelAndView("redirect:http://localhost:8080/admin/masterClass/list.do");
        return res;
    }
    @RequestMapping(value = "/banner/editCost")
    public ModelAndView editBannerCost(@RequestParam int campID){
        ModelAndView res;
        Campaing aux = campaingService.findOne(campID);
        adminService.setBannerCost(aux,0.25);
        res = new ModelAndView("redirect:http://localhost:8080/admin/campaing/listAll.do");
        return res;
    }
    @RequestMapping(value = "/campaing/listAll")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Campaing> aux = campaingService.findAll();
        result = new ModelAndView("campaign/list");
        result.addObject("campaign", aux);
        result.addObject("requestURI", "admin/campaing/listAll.do");
        return result;

    }


    @RequestMapping(value = "masterClass/list", method = RequestMethod.GET)
    public ModelAndView listMasterClasses() {
        ModelAndView result;
        Collection<MasterClass> masterClasses;
        masterClasses = masterClassService.findAll();
        result = new ModelAndView("masterClass/list");
        result.addObject("masterClass", masterClasses);
        result.addObject("requestURI", "admin/masterClass/list.do");

        return result;
    }

    @RequestMapping(value = "/edit/save")
    public ModelAndView UserSave(@RequestParam String name, String surname, String emailAddress, String phone,
                                 String postalAddress) {
        ModelAndView res;
        actorService.editPersonalData(name,surname,emailAddress,phone,postalAddress, null);
        res = new ModelAndView("redirect:http://localhost:8080");
        return res;
    }


    @RequestMapping(value = "/sponsor/list", method = RequestMethod.GET)
    public ModelAndView sponsorList() {
        ModelAndView result;
        Collection<Sponsor> sponsors = sponsorService.findAll();
        result = new ModelAndView("sponsor/list");
        result.addObject("sponsor", sponsors);
        return result;
    }

    @RequestMapping(value = "/sponsor/monthly", method = RequestMethod.GET)
    public ModelAndView computeMB(@RequestParam int id) {
        ModelAndView result = sponsorList();
        Sponsor sponsor = sponsorService.findOne(id);

        try {
            adminService.computeMonthlyBills(sponsor);
        }catch (IllegalArgumentException e){
            result = new ModelAndView("sponsor/text");
            result.addObject("texto1", e.getMessage());
        }

        return result;
    }
    @RequestMapping(value = "/sponsor/bulk", method = RequestMethod.GET)
    public ModelAndView sendBulk() {
        ModelAndView result = sponsorList();
        try {
            adminService.sendBulkMessage();
        }catch (IllegalArgumentException e){
            result = new ModelAndView("sponsor/text");
            result.addObject("texto1", e.getMessage());
        }

        return result;
    }



}


