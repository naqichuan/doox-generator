/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.common.web;

/**
 * 
 * @author naqichuan Feb 7, 2014 4:18:33 PM
 * 
 */
public class ConnContext {

	private String connNum;

	private final static ThreadLocal<ConnContext> holder = new ThreadLocal<ConnContext>() {

		@Override
		protected ConnContext initialValue() {
			return new ConnContext();
		}
	};

	public String getConnNum() {
		return connNum;
	}

	public void setConnNum(String connNum) {
		this.connNum = connNum;
	}

	public static ConnContext getConnContext() {
		return holder.get();
	}

	public static void setConnContext(ConnContext connectionContext) {
		holder.set(connectionContext);
	}
}
