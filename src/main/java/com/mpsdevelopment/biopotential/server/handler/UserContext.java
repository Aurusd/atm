package com.mpsdevelopment.biopotential.server.handler;

import org.bytedeco.javacpp.presets.opencv_core;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("userContext")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserContext {

    private String cardNumber;
    private int attempt = 0;
    private boolean authentificated;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public boolean isAuthentificated() {
        return authentificated;
    }

    public void setAuthentificated(boolean authentificated) {
        this.authentificated = authentificated;
    }

}
