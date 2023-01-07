package com.danny.makewebalone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// https://github.com/jojoldu/freelec-springboot2-webservice
// https://do5do.tistory.com/12?category=1047109

// @EnableJpaAuditing - p.221 HelloController Test
@SpringBootApplication
public class MakeWebAloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakeWebAloneApplication.class, args);
    }

}
