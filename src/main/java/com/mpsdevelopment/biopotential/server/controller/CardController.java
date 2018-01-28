package com.mpsdevelopment.biopotential.server.controller;

import com.mpsdevelopment.biopotential.server.db.pojo.*;
import com.mpsdevelopment.biopotential.server.db.pojo.request.CardRequest;
import com.mpsdevelopment.biopotential.server.db.pojo.request.PinRequest;
import com.mpsdevelopment.biopotential.server.db.pojo.request.WithdrawalsRequest;
import com.mpsdevelopment.biopotential.server.handler.UserContext;
import com.mpsdevelopment.biopotential.server.service.CardService;
import com.mpsdevelopment.biopotential.server.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class CardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);
    public static final String WITHDRAWAL = "102";

    @Autowired
    private CardService cardService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private UserContext userContext;

    @RequestMapping(value = "/card/check", method = RequestMethod.POST)
    public String checkCardNumber(@ModelAttribute("request") CardRequest request, RedirectAttributes redirectAttributes) {
        LOGGER.info("check card number");

        Card card = cardService.getCardByNumber(request.getNumber());
        if (card == null) {
            redirectAttributes.addFlashAttribute("error", "There isn't any registered card");
            return "redirect:/home";
        } else if (card.isBlocked()) {
            redirectAttributes.addFlashAttribute("error", "Your card is blocked");
            return "redirect:/home";
        } else {
            userContext.setCardNumber(card.getNumber());
            return "pin_page";
        }
    }

    @RequestMapping(value = "/card/pin", method = RequestMethod.GET)
    public String pinPage() {
        return "pin_page";
    }

    @RequestMapping(value = "/card/pinCheck", method = RequestMethod.POST)
    public String checkPin(@ModelAttribute("pinRequest") PinRequest request, Model model, RedirectAttributes redirectAttributes) {
        int count;
        LOGGER.info("check card number");
        Card card = cardService.getCardByNumber(userContext.getCardNumber());
        if (card != null) {
            if (userContext.getAttempt() == 3) {
                redirectAttributes.addFlashAttribute("error", "Your card now is blocked");
                cardService.blockCard(card.getNumber(), true);
                return "redirect:/home";
            } else if (request.getPassword().equals(card.getPin())) {
                userContext.setAuthentificated(true);
                return "redirect:/operation";
            } else {
                count = userContext.getAttempt();
                userContext.setAttempt(++count);
                redirectAttributes.addFlashAttribute("error", "pin is incorrect");
                return "redirect:/card/pin";
            }
        } else {
            model.addAttribute("error", "Enter your card number firstly");
            return "home";
        }
    }

    @RequestMapping(value = "/card/withdrawals", method = RequestMethod.POST)
    public String processWithdrawals(@ModelAttribute("amount") WithdrawalsRequest withdrawalsRequest, Model model, RedirectAttributes redirectAttributes) {
        int amount = withdrawalsRequest.getWithdraw();
        Card card = cardService.getCardByNumber(userContext.getCardNumber());
        if (userContext.isAuthentificated() && card.getBalance() > amount) {
            int balance = card.getBalance() - withdrawalsRequest.getWithdraw();
            card.setBalance(balance);
            cardService.updateCard(card);

            Operation operation = operationService.createOperation(card, amount, WITHDRAWAL);

            model.addAttribute("date", new Date(operation.getDate()));
            model.addAttribute("amount", operation.getAmount());
            model.addAttribute("balance", operation.getBalance());
            model.addAttribute("number", card.getNumber().replaceAll("-", " "));

            return "operations-report";
        } else if (!userContext.isAuthentificated()) {
            model.addAttribute("error", "You are not authentificated!");
            return "home";
        } else {
            LOGGER.info("operation failed");
            model.addAttribute("error", "Out of balance!");
            return "withdrawals";
        }
    }

    @RequestMapping(value = "/card/withdrawals", method = RequestMethod.GET)
    public ResponseEntity<String> processWithdrawals(@RequestParam("amount") Integer amount, Model model) {

        Card card = cardService.getCardByNumber(userContext.getCardNumber());
        if (card.getBalance() > amount) {
            return new ResponseEntity<>("amount available", null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no amount available on card", null, HttpStatus.BAD_REQUEST);
        }
    }
}
