package project.config;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({ "project" })
@SpringBootApplication
public class StartApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StartApplication.class).properties(getProperties());
    }

    static Properties getProperties() {
        Properties props = new Properties();
        props.put("spring.config.location", "classpath:shareExpenses/");
        return props;
    }
}
