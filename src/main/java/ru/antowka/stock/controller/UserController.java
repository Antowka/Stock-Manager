package ru.antowka.stock.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import ru.antowka.stock.model.Message;
import ru.antowka.stock.model.User;


/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Controller
public class UserController {

    @MessageMapping("/login")
    @SendTo("/response/status")
    public Message login(User user) throws Exception {

        return new Message(0, "You are authorized!");
    }

    @MessageMapping("/login2")
    public void loginTest(User user) throws Exception {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String test = "";
    }

/*
    @SubscribeMapping("/get-portfolio")
    public Portfolio getPortfolio(Principal principal) throws Exception {
        return null;
    }
*/

}
