/*
 * Copyright 2020 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator.common;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.nqcx.doox.commons.util.server.ServerUtil;

/**
 * @author naqichuan 2020-05-25 15:50
 */
public class IPLogConfig extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return ServerUtil.serverIp();
    }
}
