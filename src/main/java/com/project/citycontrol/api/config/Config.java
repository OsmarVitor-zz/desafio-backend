package com.project.citycontrol.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.citycontrol.api.service.CitiesServiceImpl;

@Configuration
public class Config {

    @Bean
    public CitiesServiceImpl service() {
        return new CitiesServiceImpl();
    }
}
