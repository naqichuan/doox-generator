/*
 * Copyright 2018 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.cg.data.converter;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.nqcx.cg.data.vo.GenerateVO;
import org.nqcx.cg.provide.o.Generate;
import org.nqcx.commons3.util.orika.Orika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Only for register mapper class
 * <p>
 * 需要在 spring 里配置或  new OrikaWeb()
 *
 * @author naqichuan 2018/11/29 11:45
 */
@Service
public class OrikaWeb {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrikaWeb.class);

    static {
        LOGGER.info("1. Orika 添加 classMap(Generate.class, GenerateVO.class)。");
        Orika.instance().classMap(Generate.class, GenerateVO.class)
                .byDefault()
                .customize(new CustomMapper<Generate, GenerateVO>() {
                    @Override
                    public void mapAtoB(Generate o, GenerateVO vo, MappingContext context) {
                        super.mapAtoB(o, vo, context);
                    }

                    @Override
                    public void mapBtoA(GenerateVO vo, Generate o, MappingContext context) {
                        super.mapBtoA(vo, o, context);
                    }
                })
                .register();
    }
}
