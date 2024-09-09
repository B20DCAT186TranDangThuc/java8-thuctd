package org.example.config;


import org.example.lang.Language;
import org.example.lang.impl.Vietnamese;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.example.bean"})
public class AppConfiguration {

    @Bean(name = "language")
    public Language getLanguage() {

        return new Vietnamese();
    }

}