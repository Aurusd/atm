package com.mpsdevelopment.biopotential.server.controller;

import com.mpsdevelopment.biopotential.server.db.pojo.request.CardRequest;
import com.mpsdevelopment.biopotential.server.handler.UserContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @Autowired
    private UserContext userContext;

    public IndexController() {
        LOGGER.info("IndexController");
    }

    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMessage() {
        LOGGER.info("main");
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(Model model) {
        LOGGER.info("/home");
        userContext.setAuthentificated(false);
        userContext.setAttempt(0);
        userContext.setCardNumber("");
        model.addAttribute("request", new CardRequest());
        return "home";
    }

}
