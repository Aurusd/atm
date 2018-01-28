package com.mpsdevelopment.biopotential.server.service;


import com.mpsdevelopment.biopotential.server.db.dao.OperationDao;
import com.mpsdevelopment.biopotential.server.db.pojo.Card;
import com.mpsdevelopment.biopotential.server.db.pojo.Operation;
import com.mpsdevelopment.biopotential.server.db.pojo.response.OperationResponse;
import com.mpsdevelopment.biopotential.server.exceptions.NotOperationFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Component
public class OperationService {

    private static final Logger LOGGER = Logger.getLogger(OperationService.class);
    public static final String BALANCE = "101";
    public static final String WITHDRAWAL = "102";

    @Autowired
    private OperationDao operationDao;

    public Operation createOperation(Card card, int amount, String operationCode) throws NotOperationFoundException {

        switch(operationCode) {
            case BALANCE: {
                Operation operation = createOperationObject(card, 0, operationCode);
                operationDao.save(operation);
                return operationDao.save(operation);
            }
            case WITHDRAWAL: {
                Operation operation = createOperationObject(card, amount, operationCode);
                operationDao.save(operation);
                return operationDao.save(operation);
            }
            default:{
                throw new NotOperationFoundException("No such operation");
            }
        }
    }

    private Operation createOperationObject(Card card, int amount, String operationCode) {
        Operation operation = new Operation();
        operation.setCard(card);
        operation.setOperationCode(operationCode);
        operation.setBalance(card.getBalance());
        operation.setAmount(amount);
        operation.setDate(System.currentTimeMillis());
        return operation;
    }

    public List<OperationResponse> getOperationsByCardNumber(String cardNumber) {

        return operationDao.getOperationsResponses(cardNumber);

    }
}
