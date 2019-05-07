package com.univision.assessmentwebapi.controller;

import com.univision.assessmentwebapi.dto.FeedSummaryResponseDTO;
import com.univision.assessmentwebapi.exception.APIThrowable;
import com.univision.assessmentwebapi.service.FeedService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/v1/feeds")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/summary")
    public ResponseEntity<FeedSummaryResponseDTO> getFeedSummary (@RequestParam String feedURI) {

        if (Strings.isEmpty(feedURI)) throw new APIThrowable(HttpStatus.BAD_REQUEST, "Query param feedURI cannot be empty");

        return new ResponseEntity<>(feedService.getFeedSummary(feedURI), HttpStatus.OK);
    }

}
