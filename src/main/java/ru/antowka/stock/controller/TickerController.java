package ru.antowka.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.antowka.stock.model.Message;
import ru.antowka.stock.model.Ticker;
import ru.antowka.stock.service.TickerService;

/**
 * Created by Anton Nik on 07.11.15.
 */
@Controller
@MessageMapping("/ticker")
public class TickerController {

    @Autowired
    private TickerService tickerService;

    @MessageMapping("/get")
    @SendTo("/response/ticker")
    public Message login(Ticker ticker) throws Exception {

        //tickerService.updateAllTickers();

        return new Message(0, "You are authorized!");
    }
}
