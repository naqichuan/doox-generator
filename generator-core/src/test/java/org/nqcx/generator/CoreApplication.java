/*
 * Copyright 2020 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author naqichuan 2020-06-02 14:24
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:spring/bean-core.xml", "classpath:spring/bean-data.xml"})
public class CoreApplication {
}
