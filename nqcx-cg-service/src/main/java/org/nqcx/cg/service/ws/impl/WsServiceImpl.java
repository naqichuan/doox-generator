/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.ws.impl;

import org.nqcx.cg.provide.o.ws.Ws;
import org.nqcx.cg.provide.util.CgFileUtils;
import org.nqcx.cg.service.ws.WsService;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author naqichuan Feb 9, 2014 10:13:18 PM
 */
@Service
public class WsServiceImpl implements WsService {

    @Override
    public Ws getWs(String wsPath, boolean needProject) {
        Ws ws = new Ws(wsPath);

        File file;
        if (wsPath == null
                || !(file = new File(ws.getPath())).exists()
                || !file.isDirectory())
            return ws;

        ws.setExists(true);

        if (needProject)
            ws.setCgFileList(CgFileUtils.getCgFile(wsPath, "", "",
                    false).getCgFileList());
        return ws;
    }
}
