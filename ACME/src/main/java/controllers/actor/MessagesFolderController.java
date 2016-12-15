package controllers.actor;

import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.FolderService;

import java.util.Collection;
import java.util.SortedSet;

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
    @Autowired
    private FolderService folderService;

    @RequestMapping("/folder/list")
    public ModelAndView list(){
        ModelAndView result;
        Collection<Folder> folders = actorService.getFolders();
        result = new ModelAndView("folder/list");
        result.addObject("folders", folders);
        return result;
    }

    @RequestMapping("/folder/view")
    public ModelAndView insideFolder(@RequestParam int folderID) {
        ModelAndView result;
        Folder folder = folderService.findOne(folderID);
        Collection<Message> messages = folder.getMessages();
        result = new ModelAndView("message/list");
        result.addObject("messages", messages);
        return result;
    }
}
