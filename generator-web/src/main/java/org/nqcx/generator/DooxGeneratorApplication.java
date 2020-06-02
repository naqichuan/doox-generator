package org.nqcx.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring/bean-commons-result.xml", "classpath:spring/bean-core.xml", "classpath:spring/bean-data.xml"})
public class DooxGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DooxGeneratorApplication.class, args);
    }

}
