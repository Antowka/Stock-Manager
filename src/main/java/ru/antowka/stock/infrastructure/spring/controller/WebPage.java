package ru.antowka.stock.infrastructure.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Nikanorov on 22.10.15.
 */
@RestController
@RequestMapping("/api")
public class WebPage {

    /**
     * Index page
     *
     * @return
     */
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public @ResponseBody List<String> indexPage() {

        List<String> test = new ArrayList<>();
        test.add("Test str 1");
        test.add("Test str 2");

        return test;
    }
}
