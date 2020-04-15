package cn.syp.sypeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SypEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SypEurekaApplication.class, args);
    }

}
