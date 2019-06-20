package net.asgardsolutions.dollgirl.serverdb.model;

import java.util.List;

public interface IServerModel {

	/**
	 * 
	 * @param user
	 * @return
	 */
	List<String> GetData(String input);

}
