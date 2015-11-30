package ru.antowka.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.antowka.stock.model.Message;
import ru.antowka.stock.model.Ticker;
import ru.antowka.stock.service.OperationService;

/**
 * Created by Anton Nik on 01.12.15.
 */
@Controller
@MessageMapping("/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @MessageMapping("add")
    @SendTo("/response/operation")
    public Message login(Ticker ticker) throws Exception {

        //tickerService.updateAllTickers();

        return new Message(0, "You are create new operation!");
    }
}
