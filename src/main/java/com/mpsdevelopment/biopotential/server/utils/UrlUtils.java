package com.mpsdevelopment.biopotential.server.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class UrlUtils {

	private static final Logger LOGGER = Logger.getLogger(UrlUtils.class);

	public static void printAllHeaders(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			Enumeration<String> headers = request.getHeaders(headerName);
			while (headers.hasMoreElements()) {
				String headerValue = headers.nextElement();
				LOGGER.info("Header " + headerName + headerValue);
			}
		}
	}

	public static String getOriginDomain(HttpServletRequest request) {
		String originHeader = request.getHeader("Origin");
		if (StringUtils.isNotBlank(originHeader)) {
			return originHeader.replaceAll(".*\\.(?=.*\\.)", "");
		} else {
			return null;
		}
	}
}
