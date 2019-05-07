package com.univision.assessmentwebapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GenericRESTClient {

    private static final Logger logger = LoggerFactory.getLogger(GenericRESTClient.class);

    @Autowired
    private RestTemplate restTemplate;

    public <T> T getRequest(final String feedURI, Class<T> clazz) {
        logger.info("Making GET request to {}", feedURI);
        return restTemplate.getForObject(feedURI, clazz);

    }

}
