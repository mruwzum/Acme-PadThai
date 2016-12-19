package controllers.sponsor;

import com.sun.org.apache.regexp.internal.RE;
import controllers.AbstractController;
import domain.MonthlyBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.MonthlyBillService;
import services.SponsorService;

import java.util.Collection;

/**
 * Created by daviddelatorre on 14/12/16.
 */
@Controller
@RequestMapping("sponsor")
public class MonthlyBillController extends AbstractController {


    public MonthlyBillController() {
        super();
    }

    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private MonthlyBillService monthlyBillService;


    @RequestMapping(value = "/monthlyBillunpaid/list")
    public ModelAndView listUnpaidMB() {
        ModelAndView res;
        Collection<MonthlyBill> monthlyBills = sponsorService.getUnpaidMonthlyBills();
        res = new ModelAndView("monthly-bill/list");
        res.addObject("mb", monthlyBills);
        res.addObject("requestURI","sponsor/monthlyBillunpaid/list.do");
        return res;
    }

    @RequestMapping(value = "/monthlyBill/pay", method = RequestMethod.GET)
    public ModelAndView pay(@RequestParam int monthID){
        ModelAndView res;
        MonthlyBill monthlyBill = monthlyBillService.findOne(monthID);
        Assert.notNull(monthlyBill,"CONTROLLER: Monthly nula");
        sponsorService.setAsPaid(monthlyBill);
        res = listUnpaidMB();
        return res;
    }


}
