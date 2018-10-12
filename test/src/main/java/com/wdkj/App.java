package com.wdkj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Hello world!
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Configuration
@ComponentScan
public class App {
    public static void main(String[] args) {

        long stime = System.currentTimeMillis();

        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        Environment env = context.getEnvironment();
        String port = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");

        long time = System.currentTimeMillis() - stime;

        System.out.println("启动耗时：" + time / 1000d + "s");
        System.out.println("URL : http://127.0.0.1:" + port + contextPath);
    }
}
