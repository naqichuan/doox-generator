/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author naqichuan Feb 7, 2014 10:57:53 PM
 * 
 */
public class CgConn {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final static String driver = "com.mysql.jdbc.Driver";

	private boolean success;

	private Connection connection = null;

	private CgConn(String url, String username, String password) {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			this.setSuccess(true);
		} catch (ClassNotFoundException e) {
			logger.error("", e);
			this.setSuccess(false);
		} catch (SQLException e) {
			logger.error("", e);
			this.setSuccess(false);
		}
	}

	private CgConn connection() {
		return this;
	}

	public synchronized static CgConn newInstance(String url, String username,
			String password) {
		return new CgConn(url, username, password).connection();
	}

	public Connection getConnection() {
		return connection;
	}

	public void close() throws SQLException {
		connection.close();
	}

	public Statement getStatement() {
		if (connection == null)
			return null;

		try {
			return connection.createStatement();
		} catch (SQLException e) {
			logger.error("", e);
		}

		return null;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
