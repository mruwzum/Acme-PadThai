package controllers.actor;

import controllers.AbstractController;
import converters.ActorToStringConverter;
import domain.Actor;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.MessageService;

import javax.validation.Valid;

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

    @RequestMapping(value = "/message/new")
    public ModelAndView newMessage() {
        ModelAndView res;
        Message m = messageService.create();
        res = createGenericEditModelAndView(m);
        return res;
    }
    @RequestMapping(value = "/message/send")
    public ModelAndView sendMessage(@Valid Message message, BindingResult bindingResult) {
        ModelAndView res;
        actorService.sendMessage(message);
        MessagesFolderController fold = new MessagesFolderController();
        res = fold.list();
        return res;
    }

    protected ModelAndView createGenericEditModelAndView(Message message) {

        return createGenericEditModelAndView(message, null);

    }

    protected ModelAndView createGenericEditModelAndView(Message message3, String message2) {
        ActorToStringConverter actorToStringConverter = new ActorToStringConverter();
        String recipient = actorToStringConverter.convert(message3.getRecipient());
        String subject = message3.getSubject();
        String body = message3.getBody();
        ModelAndView res = new ModelAndView("message/edit");
        res.addObject("message3", message3);
        res.addObject("recipient", recipient);
        res.addObject("subject", subject);
        res.addObject("body", body);

        return res;
    }

}
