package com.example.ehdee.heavyweight.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.example.ehdee.heavyweight.service",
        "com.example.ehdee.heavyweight.parser"
})
public class ServiceConfig {
}
