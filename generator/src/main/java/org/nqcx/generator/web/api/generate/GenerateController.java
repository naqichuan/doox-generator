/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.web.api.generate;

import org.nqcx.doox.commons.util.orika.Orika;
import org.nqcx.generator.data.vo.GenerateVO;
import org.nqcx.generator.provide.o.Generate;
import org.nqcx.generator.service.generate.IGenerateService;
import org.nqcx.generator.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author naqichuan Feb 8, 2014 9:33:03 PM
 */
@Controller
@RequestMapping("/generator-api/generate")
public class GenerateController extends AbstractController {

    @Autowired
    private IGenerateService generateService;

    @InitBinder
    @Override
    public void initBinder(ServletRequestDataBinder binder) {
        super.initBinder(binder);
    }

    @RequestMapping(value = "/doit", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public Map<?, ?> doit(GenerateVO vo) {
        return buildResult(generateService.generate(Orika.o2o(vo, Generate.class)));
    }
}
