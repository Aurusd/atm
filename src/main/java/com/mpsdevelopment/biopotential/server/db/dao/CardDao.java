package com.mpsdevelopment.biopotential.server.db.dao;

import com.mpsdevelopment.biopotential.server.db.pojo.Card;
import com.mpsdevelopment.biopotential.server.service.IdGenerator;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CardDao extends GenericDao<Card, Long> {

	private static final Logger LOGGER = Logger.getLogger(CardDao.class);

	public Card getCardByNumber(String value) {
		Criteria query = getSession().createCriteria(Card.class).setCacheable(false);
		query.add(Restrictions.eq(Card.NUMBER_FIELD, value));
		Card card = (Card) query.uniqueResult();
		return card;
	}

	public List<Card> getCards(Integer pageSize, Integer pageNumber) {
		Criteria query = getSession().createCriteria(Card.class).setCacheable(false);
		if (pageSize != null && pageNumber != null) {
			query.setFirstResult(pageNumber * pageSize);
		}
		if (pageSize != null) {
			query.setMaxResults(pageSize);
		}
		List<Card> tempUser = query.list();
		for (Card card : tempUser) {

		}
		return tempUser;
	}

	public void blockCard(String cardNumber, boolean isBlocked) {
		Criteria criteria = getSession().createCriteria(Card.class).setCacheable(false);
		criteria.add(Restrictions.eq(Card.NUMBER_FIELD, cardNumber));
		Card card = (Card) criteria.uniqueResult();
		card.setBlocked(isBlocked);
		update(card);
	}

	public void insertNewCard(Card card, boolean isNewObject) {

		Query query;
		if (isNewObject) {
			query = getSession().createSQLQuery("INSERT INTO MAIN.CARD (id,isJustCreated, amount, isBlocked, number, pin) VALUES(:id, true, :amount, :isBlocked, :number, :pin");
			long idx = IdGenerator.nextId();
			query.setParameter("id", idx);
		} else {
			query = getSession().createSQLQuery("UPDATE MAIN.CARD SET amount = :amount, isBlocked = :isBlocked, number = :number, pin = :pin WHERE id = :id");
			query.setParameter("id", card.getId());
		}
		query.setParameter("amount", card.getBalance());
		query.setParameter("number", card.getNumber());
		query.setParameter("pin", card.getPin());
		query.setParameter("isBlocked", card.isBlocked());
		query.executeUpdate();
	}


	public Card getCardById(Long id) {

		Query query = getSession().createSQLQuery("SELECT * FROM MAIN.CARD WHERE id = :id");
		query.setParameter("id", id);
		List<Object[]> employees = (List<Object[]>) query.list();
		Card card = new Card();
		for (Object[] employee : employees) {
			card.setId(id.longValue());
			card.setJustCreated(true);
			card.setBalance((Integer) employee[2]);
			card.setBlocked((Boolean) employee[3]);
			card.setNumber((String) employee[4]);
			card.setPin((String) employee[5]);
		}
		return card;
	}

	public int getAmount(Long id) {
		Query query;
		query = getSession().createSQLQuery("SELECT amount FROM MAIN.CARD WHERE id = :id");
		query.setParameter("id", id);
		return (int) query.uniqueResult();
	}
}
