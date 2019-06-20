package net.asgardsolutions.dollgirl.serverdb.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import net.asgardsolutions.dollgirl.serverdb.Configuration;
import net.asgardsolutions.dollgirl.serverdb.model.IServerDatabase;
import net.asgardsolutions.dollgirl.serverdb.model.IServerModel;
import net.asgardsolutions.dollgirl.serverdb.model.ServerDatabase;
import net.asgardsolutions.dollgirl.serverdb.model.ServerModel;
import net.asgardsolutions.dollgirl.serverdb.view.IServerDbView;
import net.asgardsolutions.dollgirl.serverdb.view.ServerDbView;


@Path("/")
@Singleton
public class ServerDbController implements IServerDbController {

	private static Logger logger = Logger.getLogger(ServerDbController.class.getCanonicalName());

	private IServerDbView viewer = null;

	private IServerModel model = null;

	public static String ServerVersion = "Elfving";

	protected void finalize() throws Throwable {
		logger.debug("signing off ...");
	}

	public ServerDbController() {

		if (ServerDbController.ServerVersion == null || ServerDbController.ServerVersion.equals("Elfving")) {
			try {
				ServerDbController.ServerVersion = ServerDbController.class.getPackage().getImplementationVersion();
			} catch (Exception e) {
				ServerDbController.ServerVersion = "unknown";
			}
			if (ServerDbController.ServerVersion == null) {
				ServerDbController.ServerVersion = "debug";
			}
			logger.info("Running server version " + ServerDbController.ServerVersion + "...");
		}

		IServerDatabase db = new ServerDatabase();
		model = new ServerModel(db);

		viewer = new ServerDbView(ServerDbController.ServerVersion, Configuration.getServerRoot());
	}

	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Test";
	}

	@GET
	@Path("version")
	@Produces(MediaType.TEXT_PLAIN)
	public String version() {

		return "Test";
	}

	@GET
	@Path("isAlive")
	@Produces(MediaType.TEXT_PLAIN)
	public String isAlive() {
		logger.info("get version");
		return ServerDbController.ServerVersion;
	}

}
