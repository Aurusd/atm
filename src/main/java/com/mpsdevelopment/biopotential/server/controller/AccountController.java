package com.mpsdevelopment.biopotential.server.controller;

import com.mpsdevelopment.biopotential.server.handler.UserContext;
import com.mpsdevelopment.biopotential.server.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private CardService cardService;

    @Autowired
    private UserContext userContext;



}
