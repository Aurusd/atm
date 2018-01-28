package com.mpsdevelopment.biopotential.server.service;


import com.mpsdevelopment.biopotential.server.db.dao.CardDao;
import com.mpsdevelopment.biopotential.server.db.pojo.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.bytedeco.javacpp.presets.opencv_core;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CardService {

    private static final Logger LOGGER = Logger.getLogger(CardService.class);

    @Autowired
    private CardDao cardDao;

    public Card getCardByNumber(String value) {
        return cardDao.getCardByNumber(value);
    }

    public Card updateCard(Card card) {
        Card newCard = cardDao.save(card);
        LOGGER.info("save, card amount = " + newCard.getBalance());
        return cardDao.saveOrUpdate(card);
    }

    public void blockCard(String cardNumber, boolean isBlock) {
        cardDao.blockCard(cardNumber, isBlock);
    }
}
