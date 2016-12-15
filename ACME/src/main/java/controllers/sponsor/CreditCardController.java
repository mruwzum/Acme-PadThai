package controllers.sponsor;

import controllers.AbstractController;
import domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.CreditCardService;
import services.SponsorService;

import java.util.Collection;

/**
 * Created by daviddelatorre on 14/12/16.
 */
@Controller
@RequestMapping("sponsor")
public class CreditCardController extends AbstractController {

    public CreditCardController() {
        super();
    }

    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private SponsorService sponsorService;

    @RequestMapping(value = "/creditcard/edit")
    public ModelAndView edit() {
        ModelAndView result;
        CreditCard a = sponsorService.getMyCreditCard();
        result = createEditModelAndView(a);
        return result;
    }


    protected ModelAndView createEditModelAndView(CreditCard a) {
        ModelAndView result;

        result = createEditModelAndView(a, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(CreditCard spo, String message) {
        ModelAndView result;
        String holderName = spo.getHolderName();
        String brandName = spo.getBrandName();
        Long number = spo.getNumber();
        Integer expirationYear = spo.getExpirationYear();
        Integer expirationMonth = spo.getExpirationMonth();
        String CVV = spo.getCVV();

        result = new ModelAndView("credit-card/edit");
        result.addObject("creditCard", spo);
        result.addObject("holderName", holderName);
        result.addObject("brandName", brandName);
        result.addObject("number", number);
        result.addObject("expirationYear", expirationYear);
        result.addObject("expirationMonth", expirationMonth);
        result.addObject("CVV", CVV);
        result.addObject("message", message);

        return result;


    }
}
