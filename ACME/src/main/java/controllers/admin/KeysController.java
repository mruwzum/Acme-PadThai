package controllers.admin;

import controllers.AbstractController;
import domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.AdminService;


/**
 * Created by mruwzum on 18/12/16.
 */
@Controller
@RequestMapping("admin")
public class KeysController extends AbstractController{

    public KeysController(){super();}

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/key/create")
    public ModelAndView create(){
        ModelAndView res;
        Admin u = adminService.findByPrincipal();

        String key = "";
        res = new ModelAndView("admin/createKey");
       // res.addObject("key",key);
        res.addObject("admin",u);
        return res;
    }
    @RequestMapping(value = "/key/save")
    public ModelAndView save(@RequestParam String keywor){
        ModelAndView res;
        adminService.createKeyword(keywor);

        res = new ModelAndView("redirect:http://localhost:8080/");

        return res;
    }

}
