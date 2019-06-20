package net.asgardsolutions.dollgirl.serverdb.view;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.log4j.Logger;


public class ServerDbView implements IServerDbView {

	private static Logger logger = Logger.getLogger(ServerDbView.class.getCanonicalName());

	private String ServerVersion;
	private String ServerRoot;


	


	public ServerDbView(String serverVersion,  String serverRoot) {
		this.ServerVersion = serverVersion;
		this.ServerRoot = serverRoot;
	}

	
	public String GetIpAddress() {

		InetAddress ip;
		String hostname = "";
		try {
			ip = InetAddress.getLocalHost();

			hostname = ip.getHostName();
			// System.out.println("Your current IP address : " + ip);
			// System.out.println("Your current Hostname : " + hostname);

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		return hostname;
	}


}
