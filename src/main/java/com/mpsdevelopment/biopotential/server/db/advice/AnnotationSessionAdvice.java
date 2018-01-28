package com.mpsdevelopment.biopotential.server.db.advice;

import com.mpsdevelopment.biopotential.server.db.SessionManager;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class AnnotationSessionAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

	private static final Logger LOGGER = Logger.getLogger(AnnotationSessionAdvice.class);

	@Autowired
	private SessionManager sessionManager;

	@Override
	public void before(Method method, Object[] objects, Object o) throws Throwable {
		if (method.isAnnotationPresent(Adviceable.class)) {
			LOGGER.info("Method %s Invoked before aop method " + method.getName());
			sessionManager.openSession();
		}
	}

	@Override
	public void afterReturning(Object o, Method method, Object[] objects, Object o2) throws Throwable {
		if (method.isAnnotationPresent(Adviceable.class)) {
			LOGGER.info("Method %s Invoked after aop method " + method.getName());
			sessionManager.closeSession();
		}
	}
}
