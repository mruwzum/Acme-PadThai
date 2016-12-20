package controllers.actor;

import controllers.AbstractController;
import converters.ActorToStringConverter;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.MessageService;
import services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by daviddelatorre on 15/12/16.
 */
@Controller
@RequestMapping("actor")
public class MensajesController extends AbstractController {


    public MensajesController(){
        super();
    }

    @Autowired
    private MessageService messageService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/mensaje/new")
    public ModelAndView newMessage() {
        ModelAndView res;
        Message m = messageService.create();
        res = createGenericEditModelAndView(m);
        return res;
    }
    @RequestMapping(value = "/mensaje/send")
    public ModelAndView sendMessage(@RequestParam String recipient, String subject, String body, String priority) {
        ModelAndView res;
        String texto1 = "OK";
        String replacerecipient = recipient.replaceAll(",","");
        String replacesubject = subject.replaceAll(",","");
        String replacebody = body.replaceAll(",","");
        String replacepriority = priority.replaceAll(",","");

        Actor recipient2 = actorService.findByName(replacerecipient);
        Priority priority1 = Priority.valueOf(replacepriority);
        Message message = actorService.textMessage(replacesubject,replacebody,recipient2,priority1);


        res = new ModelAndView("mensaje/text");
        res.addObject("texto1",texto1);
        return res;
    }


    @RequestMapping(value = "/mensaje/delete")
    public ModelAndView deleteMessage(@RequestParam int id) {
        ModelAndView res;
        String text1 = "Message Delete";
        Message message = messageService.findOne(id);

        res = new ModelAndView("mensaje/text");
        res.addObject("text1", text1);
        return res;


    }
    @RequestMapping(value = "/mensaje/delete/perm")
    public ModelAndView deleteMessagePerm(@RequestParam int id) {
        ModelAndView res;
        String text1 = "Message Delete Permanently";
        Message message = messageService.findOne(id);
        messageService.delete(message);
        res = new ModelAndView("mensaje/text");
        res.addObject("text1", text1);
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
