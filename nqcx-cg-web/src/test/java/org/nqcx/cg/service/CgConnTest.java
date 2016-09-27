/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.nqcx.cg.service.conn.CgConn;

/**
 * 
 * @author naqichuan Feb 7, 2014 11:45:55 PM
 * 
 */
@RunWith(JUnit4.class)
public class CgConnTest extends TestCase {

	@Test
	public void conn() {

		// String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/passport";
		String username = "nqcx";
		String password = "nqcx";
		CgConn cc = CgConn.newInstance(url, username, password);

		Statement stmt = cc.getStatement();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("show tables");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while (rs != null && rs.next()) {
				System.out.println(rs.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			rs.close();
			stmt.close();
			cc.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
