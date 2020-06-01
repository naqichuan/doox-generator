/*
 * Copyright 2019 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator.web.interceptor;

import org.apache.commons.lang3.time.StopWatch;
import org.nqcx.doox.commons.lang.url.UrlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author naqichuan 2019-08-02 10:18
 */
public class AppContextInterceptor extends AbstractInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(AppContextInterceptor.class);

    //    private final UrlPathHelper uph = new UrlPathHelper();
//    private final AntPathMatcher apm = new AntPathMatcher();
    private final List<UrlBuilder> needProtocolUrls = new ArrayList<>();
    private final List<UrlBuilder> needDomainUrls = new ArrayList<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始

        // 设置 protocol
        needDomainUrls.forEach(ub -> ub.setBaseUrl(super.getServerName()));
        // 设置 domain
        needProtocolUrls.forEach(ub -> ub.setProtocol(super.getScheme()));

        clock.stop();  //计时结束
        if (clock.getTime() > 1000)
            LOGGER.info("AppContextInterceptor Takes: " + clock.getTime());

        return true;
    }

    // ========================================================================

    public void putNeedProtocolUrls(List<UrlBuilder> list) {
        if (list == null || list.size() == 0)
            return;
        needProtocolUrls.addAll(list);
    }

    public void putNeedDomainUrls(List<UrlBuilder> list) {
        if (list == null || list.size() == 0)
            return;
        needDomainUrls.addAll(list);
    }
}
