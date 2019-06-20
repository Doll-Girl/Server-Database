package net.asgardsolutions.dollgirl.serverdb;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Configuration {

	private static Logger logger = Logger.getLogger(Configuration.class.getCanonicalName());

	private static HashMap<String, String> data;

	/**
	 * 
	 * @return
	 */
	public static String getContextPath() {
		return data.get("context.path");
	}

	/**
	 * 
	 * @return
	 */
	public static int getServerPort() {
		return Integer.parseInt(data.get("config.server.port"));
	}

	/**
	 * 
	 * @return
	 */
	public static String getServerRoot() {
		return data.get("config.server.root");
	}

	/**
	 * Initialize configuration once
	 */
	static {
		new Configuration();
	}

	private Configuration() {
		data = new HashMap<String, String>();

		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			logger.debug("Current relative path is: " + s);
			// System.out.println("Current relative path is: " + s);

			File f = new File(propFileName);
			if (f.exists()) {
				prop.load(new FileReader(f));
			} else {
				logger.error("no properties file found");
				return;
			}

			Set(prop, "context.path", "/");
			Set(prop, "config.server.root", "");
			Set(prop, "config.server.port", 7991);
		} catch (Exception e) {
			logger.error("Exception: " + e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.error("IOException: " + e);
			}
		}
	}

	private void Set(Properties prop, String propName, String defaultValue) {
		String s = prop.getProperty(propName);

		if (s == null) {
			s = defaultValue;
		}
		data.put(propName, s);
		logger.info(propName + "=" + s);
	}

	private void Set(Properties prop, String propName, int intValue) {
		String s = prop.getProperty(propName);

		int val;
		try {
			val = Integer.parseInt(s);
		} catch (Exception e) {
			val = intValue;
		}

		data.put(propName, val + "");
		logger.info(propName + "=" + val);
	}
}
