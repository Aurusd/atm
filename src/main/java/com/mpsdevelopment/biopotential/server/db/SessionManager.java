package com.mpsdevelopment.biopotential.server.db;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionManager {

	private static final Logger LOGGER = Logger.getLogger(SessionManager.class);

	// session management block
	private static int openedSessions = 0;
	private static int closedSessions = 0;
	private static ThreadLocal<Session> threadLocalSession = new ThreadLocal<Session>();

	@Autowired
	private PersistUtils persistUtils;

	public SessionManager() {
		LOGGER.info("Create SessionManager");
	}

	public SessionManager(PersistUtils persistUtils) {
		this.persistUtils = persistUtils;
	}

	public void setSession(Session session) {
		threadLocalSession.set(session);
		openedSessions++;
	}

	public Session getSession() {
		if (threadLocalSession.get() == null) {
			Session session = persistUtils.openSession();
			setSession(session);
		} else {
		}
		return threadLocalSession.get();
	}

	public void openSession() {
		Session session = persistUtils.openSession();
		LOGGER.info("Open session");
		setSession(session);
	}

	public void closeSession() {
		closeSession(false);
	}

	public void closeSession(boolean force) {
		if (threadLocalSession.get() != null) {
			Session session = threadLocalSession.get();
			persistUtils.closeSession(session);
			LOGGER.info("Close session");
			closedSessions++;
		}
		threadLocalSession.remove();
	}

	public static Session getThreadLocalSession() {
		return threadLocalSession.get();
	}

	public int getOpenedSessions() {
		return openedSessions;
	}

	public int getClosedSessions() {
		return closedSessions;
	}

	public void printStatistics() {
		LOGGER.info("Session manager opened sessions count = " + openedSessions);
		LOGGER.info("Session manager closed sessions count = " + closedSessions);
	}
}
