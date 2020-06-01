/*
 * Copyright 2018 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator.data.converter;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.nqcx.doox.commons.util.orika.Orika;
import org.nqcx.generator.data.vo.GenerateVO;
import org.nqcx.generator.provide.o.Generate;
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

    static {
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
