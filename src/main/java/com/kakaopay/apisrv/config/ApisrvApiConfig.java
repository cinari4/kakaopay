package com.kakaopay.apisrv.config;

import com.kakaopay.apisrv.api.ParseDataApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApisrvApiConfig {
    @Bean
    public ParseDataApi parseDataApi() {
        return new ParseDataApi();
    }
}
