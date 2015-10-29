package ru.antowka.stock.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.antowka.stock.model.Message;
import ru.antowka.stock.model.Portfolio;
import ru.antowka.stock.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Controller
public class UserController {

    @Autowired
    private AuthenticationManager myAuthenticationManager;


    @MessageMapping("/login")
    @SendTo("/response/status")
    public Message login(User user) throws Exception {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(user.getPassword().getBytes(), 0, user.getPassword().length());
        String hashedPass = new BigInteger(1,messageDigest.digest()).toString(16);
        if (hashedPass.length() < 32) {
            hashedPass = "0" + hashedPass;
        }

        UsernamePasswordAuthenticationToken token =
                                           new UsernamePasswordAuthenticationToken(user.getLogin(), hashedPass);

        //Auth successful
        try {
            Authentication auth = myAuthenticationManager.authenticate(token);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

            return new Message(0, "You are authorized!");
        }catch (Exception e){
            return new Message(0, "Fail authorization!");
        }
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
