package controllers.sponsor;

import controllers.AbstractController;
import domain.MonthlyBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping(value = "mb/unpaid/list")
    public ModelAndView listUnpaidMB() {
        ModelAndView res;
        Collection<MonthlyBill> monthlyBills = sponsorService.getUnpaidMonthlyBills();
        res = new ModelAndView("monthly-bill/list");
        res.addObject("mb", monthlyBills);
        return res;
    }
}
