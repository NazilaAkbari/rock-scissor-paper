package com.github.naziii.rsp.rockscissorpaper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RockScissorPaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(RockScissorPaperApplication.class, args);
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
