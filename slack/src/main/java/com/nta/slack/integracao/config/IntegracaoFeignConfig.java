package com.nta.slack.integracao.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Contract;
import feign.Logger;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class IntegracaoFeignConfig {

    @Primary
    @Bean
    public Contract feignContract() {
        return new  SpringMvcContract();
    }

    @Primary
    @Bean
    public WebInterceptor webInterceptor(){
        return  new WebInterceptor();
    }

    @Primary
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Primary
    @Bean
    public Decoder feignDecoder() {
        return new GsonDecoder();
    }


}
