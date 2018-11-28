/*
 * Copyright 2015 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.cg.web.controller;

import org.nqcx.commons.lang.enums.BoolEO;
import org.nqcx.commons.util.StringUtils;
import org.nqcx.commons.web.WebSupport;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * @author naqichuan 15/11/23 17:17
 */
public abstract class AbstractController extends WebSupport {

    // ========================================================================

    /**
     * 绑定属性
     *
     * @param binder
     */
    protected void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(BoolEO.class, new PropertiesEditor() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                BoolEO boolEO = BoolEO.FALSE;
                try {
                    if (StringUtils.isNotBlank(text))
                        boolEO = BoolEO.valueOf(text.toUpperCase());
                } catch (IllegalArgumentException e) {
                    // nothing to do
                }

                setValue(boolEO);
            }
        });
    }
}
