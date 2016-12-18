package controllers.actor;

import controllers.AbstractController;
import converters.ActorToStringConverter;
import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.MessageService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by daviddelatorre on 15/12/16.
 */
@Controller
@RequestMapping("/actor")
public class MensajesController extends AbstractController {


    public MensajesController(){
        super();
    }

    @Autowired
    private MessageService messageService;
    @Autowired
    private ActorService actorService;

//    @RequestMapping(value = "/message/view")
//    public ModelAndView messageView(@RequestParam int messageID){
//        ModelAndView result;
//
//
//        return  result;
//
//    }

    @RequestMapping(value = "/mensaje/new")
    public ModelAndView newMessage() {
        ModelAndView res;
        Message m = messageService.create();
        res = createGenericEditModelAndView(m);
        return res;
    }
    @RequestMapping(value = "/mensaje/send")
    public ModelAndView sendMessage(@Valid Message mensaje3, BindingResult bindingResult) {
        ModelAndView res;
        String texto1 = "OK";
        Actor recipient = actorService.findUserByName("user2");
        mensaje3.setRecipient(recipient);
        Actor sender = actorService.findByPrincipal();
        mensaje3.setSender(sender);
        mensaje3.setBody("GENERIC");
        mensaje3.setSentDate(new Date(System.currentTimeMillis() - 100));
        mensaje3.setPriority(Priority.HIGH);
        mensaje3.setSubject("GENERIC");
        List<Folder> folders = new ArrayList<>(sender.getFolders());

        mensaje3.setFolder(folders.get(0));
        messageService.save(mensaje3);
        actorService.sendMessage(mensaje3);;
        res = new ModelAndView("mensaje/text");
        res.addObject("texto1",texto1);
        return res;
    }

    protected ModelAndView createGenericEditModelAndView(Message mensaje3) {

        return createGenericEditModelAndView(mensaje3,null);

    }

    protected ModelAndView createGenericEditModelAndView(Message mensaje3, String message2) {
        ActorToStringConverter actorToStringConverter = new ActorToStringConverter();
        String recipient = actorToStringConverter.convert(mensaje3.getRecipient());
        String subject = mensaje3.getSubject();
        String body = mensaje3.getBody();
        ModelAndView res = new ModelAndView("mensaje/edit");
        res.addObject("mensaje3", mensaje3);
        res.addObject("recipient", recipient);
        res.addObject("subject", subject);
        res.addObject("body", body);

        return res;
    }

}
