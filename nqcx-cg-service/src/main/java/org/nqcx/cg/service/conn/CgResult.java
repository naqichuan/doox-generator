/* 
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.conn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author naqichuan Feb 19, 2014 11:45:02 AM
 * 
 */
public class CgResult {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean success = false;
	private String msg;
	private Statement statement;
	private ResultSet resultSet;

	private CgResult(Statement statement) {
		this.statement = statement;
	}

	public static CgResult newResult(Statement statement) {
		return new CgResult(statement);
	}

	public boolean isSuccess() {
		return success;
	}

	public CgResult setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public CgResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public CgResult setStatement(Statement statement) {
		this.statement = statement;
		return this;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public CgResult setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
		return this;
	}

	public void close() {
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			logger.error("", e);
		}
	}
}
