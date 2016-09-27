/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.conn;


/**
 * 
 * @author naqichuan Feb 7, 2014 10:53:49 PM
 * 
 */
public interface ConnService {

	/**
	 * 
	 * @param hostname
	 * @param port
	 * @param username
	 * @param password
	 * @param schema
	 * @return
	 */
	public String createConn(String hostname, int port, String username,
			String password, String schema);

	/**
	 * 
	 * @param connNum
	 */
	public void destroyConn(String connNum);

	/**
	 * @param connNum
	 * @param sql
	 * @return
	 */
	public CgResult query(String connNum, String sql);

	/**
	 * @param connNum
	 * @return
	 */
	public boolean checkConn(String connNum);
}
