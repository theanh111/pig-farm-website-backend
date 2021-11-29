package com.school.core;

import com.school.core.config.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.school")
public class CoreApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(CoreApplication.class, args);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
