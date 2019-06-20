package net.asgardsolutions.dollgirl.serverdb.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;


public class ServerDatabase implements IServerDatabase {

	public static final String DbAsgardTimeDataSessionid = "sessionId";
	public static final String DbAsgardTimeDataAddress = "address";
	public static final String DbAsgardTimeDataTimestamp = "timestamp";
	public static final String DbAsgardTimeDataUserid = "userid";
	public static final String DbAsgardTimeDataEntrystatus = "entrystatus";
	public static final String DbAsgardTimeDataActivity = "activity";
	public static final String DbAsgardTimeDataCustomer = "customer";
	public static final String DbAsgardTimeDataProject = "project";
	public static final String DbAsgardTimeDataActionTime = "actiontime";
	public static final String DbAsgardTimeDataEntryOrigin = "entryorigin";

	public static final String DbAsgardTimeDataTravelTime = "traveltime";

	private static Logger logger = Logger.getLogger(ServerDatabase.class.getCanonicalName());

	public static final String ORIGIN_MOBILE = "M";
	public static final String ORIGIN_COMPUTER = "X";
	public static final String ORIGIN_SYNCHRONIZED = "S";

	private static void createConnection() {

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			// Get a connection
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception except) {
			logger.error("Unable to create db connection, " + except.getMessage());
		}
	}

	private static String dbName = "server.db";
	private static String dbURL = "jdbc:derby:"+ dbName +";create=true;user=me;password=mine";

	// jdbc Connection
	private static Connection conn = null;
	// private static Statement stmt = null;

	public ServerDatabase() {
		logger.info("started db");

		createConnection();
		CreateTable();
		CreateTable_Users();
		UpdateUserTable();
	}

	private boolean UpdateUserTable() {
		String sql;

		sql = "ALTER TABLE TimeSyncUsers ";
		sql += "ADD column traveltime int default 1";
		try {
			Statement stem = conn.createStatement();
			stem.execute(sql);
			logger.info("update user table TimeSyncUsers");
		} catch (SQLException e) {
			logger.warn("Already updated table TimeSyncUsers");
			return false;
		}
		return true;
	}

	private boolean CreateTable_Users() {
		String sql;

		sql = "CREATE TABLE TimeSyncUsers (";
		sql += "email varchar(64) not null primary key,";
		sql += "name varchar(64) not null,";
		sql += "pwd varchar(64) not null,";
		sql += "defaultCustomer varchar(64) not null,";
		sql += "defaultProject varchar(64) not null,";
		sql += "defaultAction varchar(64) not null";

		sql += ")";
		try {
			Statement stem = conn.createStatement();
			stem.execute(sql);
			logger.info("Created table TimeSyncUsers");
		} catch (SQLException e) {
			logger.warn("Already created table TimeSyncUsers");
			return false;
		}
		return true;
	}

	private boolean CreateTable() {
		String sql;

		sql = "CREATE TABLE TimeSyncMobile (";
		sql += String.format("%s varchar(64) not null primary key,", DbAsgardTimeDataSessionid);
		sql += String.format("%s varchar(64) not null,", DbAsgardTimeDataUserid);
		sql += String.format("%s varchar(64) not null,", DbAsgardTimeDataCustomer);
		sql += String.format("%s varchar(64) not null,", DbAsgardTimeDataActivity);
		sql += String.format("%s varchar(64) not null,", DbAsgardTimeDataProject);
		sql += String.format("%s varchar(32) not null,", DbAsgardTimeDataActionTime);
		sql += String.format("%s varchar(32) not null,", DbAsgardTimeDataTimestamp);
		sql += String.format("%s varchar(64) not null,", DbAsgardTimeDataAddress);
		sql += String.format("%s SMALLINT    not null,", DbAsgardTimeDataEntryOrigin);
		sql += String.format("%s SMALLINT    not null", DbAsgardTimeDataEntrystatus);
		sql += ")";
		try {
			Statement stem = conn.createStatement();
			stem.execute(sql);
			logger.info("Created table TimeSyncMobile");
		} catch (SQLException e) {
			logger.warn("Already created table TimeSyncMobile");
			return false;
		}
		return true;
	}


	@Override
	public boolean StoreData(String text) {
		// TODO Auto-generated method stub
		return true;
	}

}
