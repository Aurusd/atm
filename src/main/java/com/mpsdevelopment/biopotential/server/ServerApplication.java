package com.mpsdevelopment.biopotential.server;

import org.apache.log4j.Logger;

public class ServerApplication {

	private static final Logger LOGGER = Logger.getLogger(ServerApplication.class);
 
	   public static void main(String args[]) {

		JettyServer server = JettyServer.getInstance();
		try {
			server.start();
			LOGGER.info("Server started");
			server.join();

			LOGGER.info("Server stopped");
		} catch (Exception e) {
			LOGGER.error("Failed to start server. = %s", e);
		}
	}

}
