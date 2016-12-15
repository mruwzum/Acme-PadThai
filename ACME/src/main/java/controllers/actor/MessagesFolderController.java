package controllers.actor;

import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;

import java.util.Collection;

/**
 * Created by daviddelatorre on 15/12/16.
 */
@Controller
@RequestMapping("/actor")
public class MessagesFolderController extends AbstractController {


    public MessagesFolderController(){
        super();
    }

    @Autowired
    private ActorService actorService;

    @RequestMapping("/folder/list")
    public ModelAndView list(){
        ModelAndView result;
        Collection<Folder> folders = actorService.getFolders();
        result = new ModelAndView("folder/list");
        result.addObject("folders", folders);
        return result;
    }
//    @RequestMapping("/folder/view")
//    public ModelAndView insideFolder(@RequestParam int folderID){
//        ModelAndView result;
//
//
//        return result
//    }
}
