/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.service.ws.impl;

import org.nqcx.generator.common.util.CgFileUtils;
import org.nqcx.generator.provide.o.ws.Ws;
import org.nqcx.generator.service.ws.WsService;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author naqichuan Feb 9, 2014 10:13:18 PM
 */
@Service
public class WsServiceImpl implements WsService {

    @Override
    public Ws getWs(String basedir, boolean needProject) {
        Ws ws = new Ws(basedir);

        File file;
        if (basedir == null
                || !(file = new File(ws.getPath())).exists()
                || !file.isDirectory())
            return ws;

        ws.setExists(true);

        if (needProject)
            ws.setCgFileList(CgFileUtils.getCgFile(basedir, "", "",
                    false).getCgFileList());
        return ws;
    }
}
