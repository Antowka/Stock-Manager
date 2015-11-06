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
public class TickerController {

    @Autowired
    private TickerService tickerService;

    @MessageMapping("/ticker")
    @SendTo("/response/status")
    public Message login(Ticker ticker) throws Exception {

        tickerService.updateTickerPrices(ticker);

        return new Message(0, "You are authorized!");
    }
}
