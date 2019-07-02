package kr.seoulmaas.ieye;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class IeyeApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:openapi.properties,";

    public static void main(String[] args) {
        new SpringApplicationBuilder(IeyeApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
