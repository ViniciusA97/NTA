package com.nta.slack.integracao.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        log.info(template.url());
    }
}
