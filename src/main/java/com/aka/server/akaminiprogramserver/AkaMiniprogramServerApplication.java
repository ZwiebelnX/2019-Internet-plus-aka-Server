package com.aka.server.akaminiprogramserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //启用审计，可自动生成时间
public class AkaMiniprogramServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkaMiniprogramServerApplication.class, args);
    }

}
