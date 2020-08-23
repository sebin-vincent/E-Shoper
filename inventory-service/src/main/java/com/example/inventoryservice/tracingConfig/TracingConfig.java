package com.example.inventoryservice.tracingConfig;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    @Bean
    public Sampler sampler(){

        return Sampler.ALWAYS_SAMPLE;
    }

}
