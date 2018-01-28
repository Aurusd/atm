package com.mpsdevelopment.biopotential.server.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger LOGGER = Logger.getLogger(RestAuthenticationEntryPoint.class);

	// this class just returns HTTP code 401 (Unauthorized) when authentication fails, overriding default Spring’s redirecting.

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//		LOGGER.info(" RestAuthenticationEntryPoint %s  %s  %s", response.getHeaderNames(), response.getHeader("Set-Cookie"), response.getHeader("Expires"));
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}
