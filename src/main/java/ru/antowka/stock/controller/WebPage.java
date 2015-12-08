package ru.antowka.stock.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Anton Nikanorov on 22.10.15.
 */
@Controller
@RequestMapping("/")
public class WebPage {

    /**
     * Index page
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String indexPage(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String pageName = "index";

        if(!(auth instanceof AnonymousAuthenticationToken)){
            pageName = "redirect:/portfolio";
        }

        //set template vars
        model.addAttribute("page", pageName);

        return pageName;
    }

    @RequestMapping(value = "portfolio", method = RequestMethod.GET)
    public String portfolioPage(ModelMap model) {

        String pageName = "portfolio";

        //set template vars
        model.addAttribute("page", pageName);

        return pageName;
    }
}
