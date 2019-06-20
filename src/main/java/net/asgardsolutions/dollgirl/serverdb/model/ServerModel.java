package net.asgardsolutions.dollgirl.serverdb.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;


public class ServerModel implements IServerModel {

	
	public static final long HOUR = 3600 * 1000;
	private static Logger logger = Logger.getLogger(ServerModel.class.getCanonicalName());
	
	
	private static IServerDatabase ttmDb;

	public ServerModel(IServerDatabase db) {
		if (ttmDb == null) {

			String currentDirectory1;
			File file = new File(".");
			currentDirectory1 = file.getAbsolutePath();
			logger.info("Current working directory : " + currentDirectory1);

			logger.info("Server ready for rock'n'roll ...");
			ttmDb = db;

			// Get user data

			

		}

	}


	@Override
	public List<String> GetData(String input) {
		// TODO Auto-generated method stub
		List<String> data = new ArrayList<String>();
		data.add("Simon");
		data.add("Ludvig");
		return data;
	}
}
