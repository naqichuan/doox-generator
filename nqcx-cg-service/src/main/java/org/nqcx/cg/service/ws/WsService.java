/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.ws;

import org.nqcx.cg.entity.ws.Ws;

/**
 * @author naqichuan Feb 9, 2014 10:12:24 PM
 * 
 */
public interface WsService {

	/**
	 * 
	 * @return
	 */
	public Ws getWs();

	/**
	 * @param needProject
	 * @return
	 */
	public Ws getWs(boolean needProject);
}
