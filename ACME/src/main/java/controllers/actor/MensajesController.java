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
    public ModelAndView sendMessage(@RequestParam String recipient, String subject, String body, String priority) {
        ModelAndView res;
        String texto1 = "OK";
        String replacerecipient = recipient.replaceAll(",","");
        String replacesubject = subject.replaceAll(",","");
        String replacebody = body.replaceAll(",","");
        String replacepriority = priority.replaceAll(",","");



        Message message = messageService.create();
        Actor recipient2 = actorService.findUserByName(replacerecipient);
        Priority priority1 = Priority.valueOf(replacepriority);

        message.setRecipient(recipient2);
        message.setSubject(subject);
        message.setBody(body);
        message.setPriority(priority1);

       Message m = actorService.sendMessage(message);


        res = new ModelAndView("mensaje/text");
        res.addObject("texto1",texto1);
        res.addObject("red", m.getRecipient());
        res.addObject("bod", m.getSubject());
        res.addObject("sub", m.getBody());
        res.addObject("pri", m.getPriority());
        res.addObject("sen", m.getSender());
        res.addObject("date", m.getSentDate());
        return res;
    }

//    @RequestMapping(value = "/mensaje/send2")
//    public ModelAndView sendMessage2( @RequestParam Actor actor, String Sub, String body, Priority priority) {
//        ModelAndView res;
//        String texto1 = "OK";
//        Message m = messageService.create();
//        m.setRecipient(actor);
//        m.setSubject(Sub);
//        m.setBody(body);
//        m.setPriority(priority);
//        actorService.sendMessage(m);
//        res = new ModelAndView("mensaje/text");
//        res.addObject("texto1",texto1);
//        return res;
//    }

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
