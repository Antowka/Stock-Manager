package ru.antowka.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anton Nikanorov on 22.10.15.
 */
@Controller
@RequestMapping("/")
public class WebPage {

    /**
     * Index page
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String indexPage(ModelMap model) {
        model.addAttribute("message", "Index Page");
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        model.addAttribute("message", "Login Page");
        return "login";
    }

    @RequestMapping(value = "portfolio", method = RequestMethod.GET)
    public String portfolioPage(ModelMap model) {
        model.addAttribute("message", "Portfolio Page");
        return "portfolio";
    }
}
