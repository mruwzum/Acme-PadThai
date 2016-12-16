package controllers.actor;

import controllers.AbstractController;
import converters.ActorToStringConverter;
import domain.Actor;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.MessageService;

/**
 * Created by daviddelatorre on 15/12/16.
 */
@Controller
@RequestMapping("/actor")
public class MessagesController extends AbstractController {


    public MessagesController(){
        super();
    }

    @Autowired
    private MessageService messageService;

//    @RequestMapping(value = "/message/view")
//    public ModelAndView messageView(@RequestParam int messageID){
//        ModelAndView result;
//
//
//        return  result;
//
//    }

    @RequestMapping(value = "/message/new")
    public ModelAndView newMessage() {
        ModelAndView res;
        Message m = messageService.create();
        res = createGenericEditModelAndView(m);
        return res;
    }

    protected ModelAndView createGenericEditModelAndView(Message message) {

        return createGenericEditModelAndView(message, null);

    }

    protected ModelAndView createGenericEditModelAndView(Message message, String message2) {
        ActorToStringConverter actorToStringConverter = new ActorToStringConverter();
        String recipient = actorToStringConverter.convert(message.getRecipient());
        String subject = message.getSubject();
        String body = message.getBody();
        ModelAndView res = new ModelAndView("message/edit");
        res.addObject("message", message);
        res.addObject("recipient", recipient);
        res.addObject("subject", subject);
        res.addObject("body", body);

        return res;
    }

}
