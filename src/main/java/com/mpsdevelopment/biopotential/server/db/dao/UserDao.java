package com.mpsdevelopment.biopotential.server.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.mpsdevelopment.biopotential.server.db.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserDao extends GenericDao<User, Long> {
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);

	public User getByLogin(String value) {
		Criteria query = getSession().createCriteria(User.class).setCacheable(false);
		query.add(Restrictions.eq(User.LOGIN_FIELD, value));
		return (User) query.uniqueResult();
	}

	public User getByLoginAndPassword(String login, String password) {
		Criteria query = getSession().createCriteria(User.class).setCacheable(false);
		query.add(Restrictions.eq(User.LOGIN_FIELD, login));
		query.add(Restrictions.eq(User.PASSWORD_FIELD, password));
		return (User) query.uniqueResult();
	}

	public Long getAllCount() {
		Criteria query = getSession().createCriteria(User.class).setCacheable(false);
		query.setProjection(Projections.rowCount());
		return (Long) query.uniqueResult();
	}

	public List<User> getUsers(Integer pageSize, Integer pageNumber) {
		Criteria query = getSession().createCriteria(User.class).setCacheable(false);
		if (pageSize != null && pageNumber != null) {
			query.setFirstResult(pageNumber * pageSize);
		}
		if (pageSize != null) {
			query.setMaxResults(pageSize);
		}
		List<User> tempUser = query.list();
		for (User user : tempUser) {
		}
		// return query.list();
		return tempUser;
	}

	public void insertNewUser(User user, boolean isNewObject) {

		Query query;
		if (isNewObject) {
			query = getSession().createSQLQuery("INSERT INTO MAIN.USER (id,isJustCreated, login, password, name, surname, patronymic, role, rank, division, call, admin, tel, email, bornPlace, bornDate, gender) VALUES(:id, true, :login, :password, :name, :surname, :patronymic, :role, :rank, :division, :call, :admin, :tel, :email, :bornPlace, :bornDate, :gender)");
//            long idx = IdGenerator.nextId();
			query.setParameter("id", user.getId());
//            query.setParameter("time", folder.getTime());
		} else {
			query = getSession().createSQLQuery("UPDATE MAIN.USER SET login = :login, password = :password, name = :name, surname = :surname, patronymic = :patronymic, role = :role, rank = :rank, division = :division, call = :call, admin = :admin, tel = :tel, email = :email, bornPlace = :bornPlace, bornDate = :bornDate, gender = :gender  WHERE id = :id");
			query.setParameter("id", user.getId());
		}
		query.setParameter("login", user.getLogin());
		query.setParameter("password", user.getPassword());
		query.setParameter("name", user.getName());
		query.setParameter("surname", user.getSurname());
		query.setParameter("patronymic", user.getPatronymic());
		query.setParameter("role", user.getRole());
		query.setParameter("rank", user.getRank());
		query.setParameter("division", user.getDivision());
		query.setParameter("call", user.getCall());
		query.setParameter("admin", user.getAdmin());
		query.setParameter("tel", user.getTel());
		query.setParameter("email", user.getEmail());
		query.setParameter("bornPlace", user.getBornPlace());
		query.setParameter("bornDate", user.getBornDate());
		query.setParameter("gender", user.getGender());
		query.executeUpdate();
	}
}
