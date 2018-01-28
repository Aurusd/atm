package com.mpsdevelopment.biopotential.server.db.dao;

import com.mpsdevelopment.biopotential.server.db.pojo.Card;
import com.mpsdevelopment.biopotential.server.db.pojo.Operation;
import com.mpsdevelopment.biopotential.server.db.pojo.response.OperationResponse;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OperationDao extends GenericDao<Operation, Long> {

	private static final Logger LOGGER = Logger.getLogger(OperationDao.class);
	public static final String BALANCE = "102";
	private String number;

	public List<OperationResponse> getOperationsResponses(String cardNumber) {
		number = cardNumber;
		Criteria query = getSession().createCriteria(Operation.class).setCacheable(false);
		query = query.createCriteria(Operation.CARD)/*.createCriteria(PatternsFolders.PATTERNS)*/;
		query.add(Restrictions.in(Card.NUMBER_FIELD, cardNumber));
		List<Operation> list = query.list();
		List<OperationResponse> operationResponses = list.stream().map(this::createResponse).filter(p -> p.getOperationCode().equals(BALANCE)).collect(Collectors.toList());
		return operationResponses.stream().sorted(Comparator.comparing(OperationResponse::getDate).reversed()).collect(Collectors.toList());
	}

	private OperationResponse createResponse(Operation operation) {
		OperationResponse response = new OperationResponse();
		response.setBalance(operation.getBalance());
		response.setDate(new Date(operation.getDate()));
		response.setAmount(operation.getAmount());
		response.setOperationCode(operation.getOperationCode());
		response.setCardNumber(number);
		return response;
	}
}
