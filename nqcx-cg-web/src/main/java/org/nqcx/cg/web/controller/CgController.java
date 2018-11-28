/*
 * Copyright 2014 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.controller;

import org.nqcx.cg.common.web.ConnContext;

/**
 * @author naqichuan Feb 8, 2014 12:12:29 PM
 */
public class CgController extends AbstractController {

    /**
     * @return
     */
    protected String getConnNum() {
        return ConnContext.getConnContext().getConnNum();
    }
}
