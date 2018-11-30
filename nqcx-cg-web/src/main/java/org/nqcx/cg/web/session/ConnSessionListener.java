///*
// * Copyright 2014 nqcx.org All right reserved. This software is the
// * confidential and proprietary information of nqcx.org ("Confidential
// * Information"). You shall not disclose such Confidential Information and shall
// * use it only in accordance with the terms of the license agreement you entered
// * into with nqcx.org.
// */
//
//package org.nqcx.cg.web.session;
//
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//
//import org.nqcx.cg.common.consts.CgConst;
//import org.nqcx.cg.service.conn.ConnService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
///**
// *
// * @author naqichuan Feb 7, 2014 3:21:59 PM
// *
// */
//public class ConnSessionListener implements HttpSessionListener {
//
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Override
//	public void sessionCreated(HttpSessionEvent se) {
//		// nothing to do
//	}
//
//	@Override
//	public void sessionDestroyed(HttpSessionEvent se) {
//		WebApplicationContext ctx = WebApplicationContextUtils
//				.getWebApplicationContext(se.getSession().getServletContext());
//
//		String connNum = (String) se.getSession().getAttribute(
//				CgConst.CONNECTION_KEY);
//
//		logger.info("A session(connNum: {}) is about to be invalidated.",
//				connNum);
//
//		ConnService connService = (ConnService) ctx.getBean("connService");
//		if (connService != null)
//			connService.destroyConn(connNum);
//	}
//}
