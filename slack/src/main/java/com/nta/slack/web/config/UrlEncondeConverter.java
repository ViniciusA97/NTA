package com.nta.slack.web.config;

import feign.form.FormEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

public class UrlEncondeConverter {

    @Bean
    @Primary
    Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
        return new FormEncoder(new SpringEncoder(converters));
    }

}
