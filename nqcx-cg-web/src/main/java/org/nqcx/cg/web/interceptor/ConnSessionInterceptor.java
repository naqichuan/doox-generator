/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.interceptor;

import static org.nqcx.cg.common.consts.CgConst.CONNECTION_KEY;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nqcx.cg.common.web.ConnContext;
import org.nqcx.cg.service.conn.ConnService;
import org.nqcx.commons.web.WebContext;
import org.nqcx.commons.web.interceptor.WebContextInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author naqichuan Feb 7, 2014 3:26:42 PM
 * 
 */
public class ConnSessionInterceptor extends WebContextInterceptor {

	private final static String NEED_CONNECTION_ERROR_CODE = "11";

	@Autowired
	private ConnService connService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String connValue = (String) request.getSession().getAttribute(
				CONNECTION_KEY);
		if (connValue != null && connService.checkConn(connValue)) {
			ConnContext.getConnContext().setConnNum(
					(String) request.getSession().getAttribute(CONNECTION_KEY));
			return true;
		} else {
			ConnContext.getConnContext().setConnNum(null);
			if (WebContext.getWebContext().isAjax())
				responseJsonResult(response, "{\"" + SUCCESS + "\":false,\""
						+ ERROR_CODE + "\":\"" + NEED_CONNECTION_ERROR_CODE
						+ "\",\"" + ERROR_TEXT + "\":\""
						+ e(NEED_CONNECTION_ERROR_CODE) + "\"}");
			else
				// 去登录
				response.sendRedirect(getLoginUrl(request));

			return false;
		}
	}

	protected String getLoginUrl(HttpServletRequest request)
			throws MalformedURLException {
		return request.getContextPath() + "/login";
	}
}
