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

    @RequestMapping(value = "/folder/list")
    public ModelAndView list(){
        ModelAndView result;
        Collection<Folder> folders = actorService.getFolders();
        result = new ModelAndView("folder/list");
        result.addObject("folders", folders);
        return result;
    }

    @RequestMapping(value = "/folder/view")
    public ModelAndView insideFolder(@RequestParam int folderID) {
        ModelAndView result;
        Folder folder = folderService.findOne(folderID);
        Collection<Message> messages = folder.getMessages();
        result = new ModelAndView("message/list");
        result.addObject("messages", messages);
        return result;
    }

    @RequestMapping(value = "/folder/new")
    public ModelAndView newFolder() {
        ModelAndView res;
        Folder folder = folderService.create();
        res = createGenericEditModelAndView(folder);
        return res;


    }

    @RequestMapping(value = "/folder/new/save")
    public ModelAndView saveFolder(@RequestParam String name) {
        ModelAndView res;
        actorService.createFolder(name);
        res = list();
        return res;
    }

    protected ModelAndView createGenericEditModelAndView(Folder folder) {

        return createGenericEditModelAndView(folder, null);

    }

    protected ModelAndView createGenericEditModelAndView(Folder folder, String message) {
        String name = folder.getName();

        ModelAndView res = new ModelAndView("folder/edit");
        res.addObject("folder", folder);
        res.addObject("name", name);

        return res;
    }

}
