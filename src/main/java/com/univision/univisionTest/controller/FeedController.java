package com.univision.univisionTest.controller;

import com.univision.univisionTest.dto.FeedSummaryResponseDTO;
import com.univision.univisionTest.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/v1/feeds")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/summary")
    public ResponseEntity<FeedSummaryResponseDTO> parseFeed () {
        return new ResponseEntity<>(feedService.getFeedSummary(), HttpStatus.ACCEPTED);
    }

}