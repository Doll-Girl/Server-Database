package net.asgardsolutions.dollgirl.serverdb;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import net.asgardsolutions.dollgirl.serverdb.controller.ServerDbController;


public class ApplicationRunner {

	private static Logger logger = Logger.getLogger(ApplicationRunner.class.getCanonicalName());

	public static void main(String[] args) throws Exception {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath(Configuration.getContextPath());

		Server jettyServer = new Server(Configuration.getServerPort());

		jettyServer.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		// Tells the Jersey Servlet which REST service/class to load.
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
				ServerDbController.class.getCanonicalName());

		try {
			jettyServer.start();
			logger.info("server is starting up...");
			jettyServer.join();
		} catch (Exception e) {
			logger.error("Application start/join error, reason " + e.getMessage() + " & " + e.getLocalizedMessage());
		} finally {
			try {
				jettyServer.destroy();
			} catch (Exception e) {
				logger.error("Application destroy error, reason " + e.getMessage() + " & " + e.getLocalizedMessage());
			}

		}
	}
}
