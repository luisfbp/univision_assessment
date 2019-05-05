package com.univision.univisionTest.client;

import com.univision.univisionTest.dto.FeedResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FeedRESTClient {

    @Autowired
    private RestTemplate restTemplate;

    public FeedResponseDTO getFeeds() {

        String url = "https://syndicator.univision.com/web-api/content?url=https://www.univision.com&lazyload=false";

        return restTemplate.getForObject(url, FeedResponseDTO.class);

    }

}
