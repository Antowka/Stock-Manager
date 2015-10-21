package ru.antowka.stock.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import ru.antowka.stock.model.Portfolio;

import java.security.Principal;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Controller
public class UserController {

    private static final Log logger = LogFactory.getLog(UserController.class);

    @SubscribeMapping("/get-portfolio")
    public Portfolio getPortfolio(Principal principal) throws Exception {
        logger.debug("Positions for " + principal.getName());
        return null;
    }

    @MessageMapping("/add-ticket-to-portfolio")
    public void executeTrade(Principal principal) {
        logger.debug("Trade: " + 123);
    }

}
