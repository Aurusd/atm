package com.mpsdevelopment.biopotential.server.service;

import com.mpsdevelopment.biopotential.server.db.SessionManager;
import com.mpsdevelopment.biopotential.server.db.advice.Adviceable;
import com.mpsdevelopment.biopotential.server.db.dao.UserDao;
import com.mpsdevelopment.biopotential.server.db.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsersService {
	
	private static final Logger LOGGER = Logger.getLogger(UsersService.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SessionManager sessionManager;
	
	@Adviceable
    public void checkScripts() {
        List<User> users = userDao.findAll();
        users.forEach(user -> LOGGER.info("User is %s" + user));
        sessionManager.printStatistics();
    }
}
