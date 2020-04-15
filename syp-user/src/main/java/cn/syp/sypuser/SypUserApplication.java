package cn.syp.sypuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("cn.syp.sypuser.mapper")
public class SypUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SypUserApplication.class, args);
    }

}
