package com.univision.assessmentwebapi.client;

import com.univision.assessmentwebapi.dto.FeedResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FeedRESTClient {

    @Autowired
    private RestTemplate restTemplate;

    public FeedResponseDTO getFeeds(final String feedURI) {

        return restTemplate.getForObject(feedURI, FeedResponseDTO.class);

    }

}
