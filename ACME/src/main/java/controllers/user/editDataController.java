package controllers.user;

import controllers.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by daviddelatorre on 13/12/16.
 */

@Controller
@RequestMapping("user")
public class editDataController extends AbstractController {

    public editDataController() {
        super();
    }


    @RequestMapping("/editView")
    public ModelAndView editView(Throwable oops) {
        return null;
    }
}
