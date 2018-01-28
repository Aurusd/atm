package com.mpsdevelopment.biopotential.server.controller;

import com.mpsdevelopment.biopotential.server.db.pojo.*;
import com.mpsdevelopment.biopotential.server.db.pojo.response.OperationResponse;
import com.mpsdevelopment.biopotential.server.handler.UserContext;
import com.mpsdevelopment.biopotential.server.service.CardService;
import com.mpsdevelopment.biopotential.server.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/operation")
@Controller
public class OperationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationController.class);
    public static final int BALANCE = 101;
    public static final int WITHDRAWAL = 102;

    @Autowired
    private CardService cardService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private UserContext userContext;

    @RequestMapping(method = RequestMethod.GET)
    public String getOperationMainPage(Model model) {
        if (userContext.isAuthentificated()) {
            List<OperationResponse> responses = operationService.getOperationsByCardNumber(userContext.getCardNumber());
            model.addAttribute("operations", responses);
            return "operations";
        }
        model.addAttribute("error", "Enter your card number firstly");
        return "home";
    }

    @RequestMapping(value = "/{operationCode}", method = RequestMethod.GET)
    public String getOperationPage(@PathVariable("operationCode") Integer operationCode, Model model) {
        if (userContext.isAuthentificated()) {
            switch (operationCode) {
                case BALANCE: {
                    Card card = cardService.getCardByNumber(userContext.getCardNumber());
                    operationService.createOperation(card, 0, Integer.toString(BALANCE));
                    model.addAttribute("date", new Date(System.currentTimeMillis()).toString());
                    model.addAttribute("card", card);
                    return "balance";
                }
                case WITHDRAWAL: {
                    return "withdrawals";
                }
                default: {
                    return "redirect:/operation";
                }
            }
        }
        model.addAttribute("error", "Enter your card number firstly");
        return "home";
    }

}
